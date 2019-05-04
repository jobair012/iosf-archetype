#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${artifactId}.controller.open;

import ${package}.commons.enums.ResponseCode;
import ${package}.commons.models.appBasic.Response;
import ${package}.commons.models.request.TokenRequest;
import ${package}.commons.models.response.Token;
import ${package}.${artifactId}.appBasic.i18n.MessageKey;
import ${package}.${artifactId}.controller.BaseController;
import ${package}.${artifactId}.service.AccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccessController extends BaseController {
	
	@Autowired private AccessService accessService;

	@PostMapping("access-token")
	public Response<Token> getAccessToken(@RequestBody @Validated TokenRequest tokenRequest, BindingResult result){
		if(result.hasErrors()) {
			return new Response<>(ResponseCode.INVALID_ARGUMENT.getCode(), getMessage(MessageKey.INVALID_ARGUMENTS));
		}
		
		return accessService.getTokenResponse(tokenRequest);
	}
	
	@PostMapping("delete-token")
	public Response<?> getAccessToken(){
		return accessService.deleteToken();
	}
}
