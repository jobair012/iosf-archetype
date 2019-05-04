#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${artifactId}.appBasic.provider;

import org.springframework.security.authentication.AccountStatusException;

@SuppressWarnings("serial")
public class IOSFUserStatusException extends AccountStatusException{
	public IOSFUserStatusException(String exceptionMessage) {
		super(exceptionMessage);
	}
}
