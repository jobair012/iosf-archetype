#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${artifactId}.appBasic.rester;

import ${package}.commons.enums.ResponseCode;
import ${package}.commons.models.appBasic.Response;
import ${package}.${artifactId}.appBasic.i18n.MessageKey;
import ${package}.${artifactId}.appBasic.i18n.ServiceMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.GenericTypeResolver;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.${artifactId}.client.HttpClientErrorException;
import org.springframework.${artifactId}.client.HttpServerErrorException;
import org.springframework.${artifactId}.client.ResourceAccessException;
import org.springframework.${artifactId}.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Component
public class IOSFRestTemplate<T extends Response<?>> {

	@Autowired private ServiceMessage serviceMessage;

	public IOSFRestTemplate() {
	}

	public IOSFRestTemplate(ServiceMessage serviceMessage) {
		this.serviceMessage = serviceMessage;
	}

	private List<ClientHttpRequestInterceptor> getInterceptors() {
		List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
		interceptors.add(new IOSFRestInterceptor());
		return interceptors;
	}

	@SuppressWarnings("rawtypes")
	public T getWithoutHeader(String url, Object requestData, ParameterizedTypeReference parameterizedTypeReference) {
		return this.request(url, requestData, HttpMethod.GET, false, parameterizedTypeReference);
	}

	@SuppressWarnings("rawtypes")
	public T postWithoutHeader(String url, Object requestData, ParameterizedTypeReference parameterizedTypeReference) {
		return this.request(url, requestData, HttpMethod.POST, false, parameterizedTypeReference);
	}

	@SuppressWarnings("rawtypes")
	public T get(String url, Object requestData, ParameterizedTypeReference parameterizedTypeReference) {
		return this.request(url, requestData, HttpMethod.GET, true, parameterizedTypeReference);
	}

	@SuppressWarnings("rawtypes")
	public T post(String url, Object requestData, ParameterizedTypeReference parameterizedTypeReference) {
		return this.request(url, requestData, HttpMethod.POST, true, parameterizedTypeReference);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private T request(String url, Object requestData, HttpMethod httpMethod, Boolean withHeader, ParameterizedTypeReference parameterizedTypeReference) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<T> responseEntity;
		T result;

		try {

			if (withHeader) {
				restTemplate.setInterceptors(getInterceptors());
			}

			responseEntity = restTemplate.exchange(getFullUrl(url), httpMethod, new HttpEntity<>(requestData), parameterizedTypeReference);
			result = responseEntity.getBody();

			if (ResponseCode.AUTHENTICATION_FAILED.getCode() == result.getResponseCode()) {
				this.springLogout();
			} else {
				return result;
			}

		} catch (ResourceAccessException ex) {
			result = this.getExceptionResult(ResponseCode.RUNTIME_ERROR.getCode(),
					serviceMessage.getMessage(MessageKey.SERVICE_DOWN));
			ex.printStackTrace();
		} catch (HttpServerErrorException ex) {
			result = this.getExceptionResult(ResponseCode.RUNTIME_ERROR.getCode(),
					serviceMessage.getMessage(MessageKey.SERVICE_ERROR));
			ex.printStackTrace();
		}
		catch (HttpClientErrorException ex) {
			result = this.getExceptionResult(ResponseCode.UNEXPECTED_EXCEPTION.getCode(),
					serviceMessage.getMessage(MessageKey.ERROR_OCCURED));
			ex.printStackTrace();
		}catch (Exception ex) {
			result = this.getExceptionResult(ResponseCode.RUNTIME_ERROR.getCode(),
					serviceMessage.getMessage(MessageKey.SERVICE_DOWN));
			ex.printStackTrace();
		}

		return result;
	}

	private String getFullUrl(String url) {
		Locale locale = LocaleContextHolder.getLocale();
		return ServiceUrl.BASE_URL.concat(url).concat("?lang=" + locale.getLanguage());
	}

	private void springLogout() {
		SecurityContextHolder.getContext().getAuthentication().setAuthenticated(false);
	}

	@SuppressWarnings("unchecked")
	private T getExceptionResult(int responseCode, String message) { 
		Class<T>  genericType = (Class<T>) GenericTypeResolver.resolveTypeArgument(getClass(), IOSFRestTemplate.class);
        T gObj = null;
        try {
            gObj = genericType.newInstance();
            gObj.setResponseCode(responseCode);
            gObj.setResponseMessage(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return gObj;
    }

}
