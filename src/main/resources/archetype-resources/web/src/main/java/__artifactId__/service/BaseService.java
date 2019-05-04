#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${artifactId}.service;

import ${package}.${artifactId}.appBasic.i18n.ServiceMessage;
import ${package}.${artifactId}.appBasic.rester.ServiceUrl;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseService {
	
	@Autowired
	protected ServiceMessage serviceMessage;
	
	protected String  getMessageString(String id) {
		return serviceMessage.getMessage(id);
	}
	
	protected static String getFullUrl(String url) {
		return ServiceUrl.BASE_URL.concat(url);
	}
	
}
