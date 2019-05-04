#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${artifactId}.appBasic.provider;

import ${package}.commons.enums.ResponseCode;
import ${package}.commons.models.appBasic.Response;
import ${package}.commons.models.response.Token;
import ${package}.${artifactId}.appBasic.i18n.MessageKey;
import ${package}.${artifactId}.appBasic.i18n.ServiceMessage;
import ${package}.${artifactId}.models.UserDetails;
import ${package}.${artifactId}.service.TokenService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class IOSFAuthenticationProvider implements AuthenticationProvider{
	
	@Autowired private TokenService tokenService;
	@Autowired private ServiceMessage serviceMessage;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		if(!StringUtils.isEmpty(authentication.getName()) && !StringUtils.isEmpty(authentication.getCredentials().toString())) {
			Response<Token> tokenResponse = tokenService.getToken(authentication.getName(), authentication.getCredentials().toString());
			if (ResponseCode.OPERATION_SUCCESSFUL.getCode() == tokenResponse.getResponseCode() && tokenResponse.getItems() != null) {
				Token token = tokenResponse.getItems();
				UserDetails userDetails = new UserDetails();
            	BeanUtils.copyProperties(tokenResponse.getItems(), userDetails);

				List<GrantedAuthority> AUTHORITIES = this.getAuthorities(userDetails.getUserRoles());

                return new UsernamePasswordAuthenticationToken(userDetails, token, AUTHORITIES);

			}else {
				throw new IOSFUserStatusException(tokenResponse.getResponseMessage());
			}
		}else {
			throw new IOSFUserStatusException(serviceMessage.getMessage(MessageKey.USERNAME_PASSWORD_REQUIRED));
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

	private List<GrantedAuthority> getAuthorities(Set<String> userRoles){
		List<GrantedAuthority> AUTHORITIES = new ArrayList<>();
		for(String role : userRoles){
			AUTHORITIES.add(new SimpleGrantedAuthority(role));
		}
		return AUTHORITIES;
	}
}
