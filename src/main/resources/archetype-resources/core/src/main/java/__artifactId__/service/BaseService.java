#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${artifactId}.service;

import ${package}.${artifactId}.appBasic.i18n.ServiceMessage;
import ${package}.${artifactId}.service.messaging.SmsService;
import ${package}.entityRepo.entity.SystemUser;
import ${package}.entityRepo.entity.UserToken;
import ${package}.entityRepo.repository.UserTokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

public class BaseService {

	@Autowired private HttpServletRequest httpServletRequest;
	@Autowired private ServiceMessage serviceMessage;
	@Autowired private UserTokenRepo userTokenRepo;
	@Autowired protected SmsService smsService;

	protected String  getMessage(String id) {
		return serviceMessage.getMessage(id);
	}

	protected String getUserToken() {
		return httpServletRequest.getHeader("AuthenticationToken");
	}

	protected SystemUser getSystemUser(){
		if(!StringUtils.isEmpty(this.getUserToken())){
			UserToken userToken = userTokenRepo.getByUserToken(this.getUserToken());
			return userToken.getSystemUser();
		}
		return null;
	}
}
