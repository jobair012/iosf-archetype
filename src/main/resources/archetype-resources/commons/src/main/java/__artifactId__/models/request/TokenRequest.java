#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${artifactId}.models.request;

import ${package}.${artifactId}.models.appBasic.IOSFRestClient;
import org.hibernate.validator.constraints.NotBlank;

public class TokenRequest extends IOSFRestClient {

	@NotBlank
	private String username;
	
	@NotBlank
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
