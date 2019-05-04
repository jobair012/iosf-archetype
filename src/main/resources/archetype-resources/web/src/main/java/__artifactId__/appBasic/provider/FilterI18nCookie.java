#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${artifactId}.appBasic.provider;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.${artifactId}.servlet.i18n.CookieLocaleResolver;
import org.springframework.${artifactId}.util.WebUtils;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Locale;

/**
 * Transfer the value of user Locale into LocaleContextHolder which is used by spring-security
 */
public class FilterI18nCookie implements Filter{

	/**
     * @see Filter${symbol_pound}doFilter(ServletRequest,
     *      ServletResponse, FilterChain)
     */
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		if (!(servletRequest instanceof HttpServletRequest)) {
			filterChain.doFilter(servletRequest, servletResponse);
			return;
		}

		HttpServletRequest request = (HttpServletRequest) servletRequest;

		Cookie cookie = WebUtils.getCookie(request, "${parentArtifactId}LocaleCookie");
		
		if (cookie != null) {
			Locale locale = StringUtils.parseLocaleString(cookie.getValue());
			if (locale != null) {
				request.setAttribute(CookieLocaleResolver.LOCALE_REQUEST_ATTRIBUTE_NAME, locale);
				LocaleContextHolder.setLocale(locale, true);
			}
		}

		filterChain.doFilter(servletRequest, servletResponse);
	}
	
	/**
     * @see Filter${symbol_pound}init(FilterConfig)
     */
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	/**
     * @see Filter${symbol_pound}destroy()
     */
	public void destroy() {
	}
}
