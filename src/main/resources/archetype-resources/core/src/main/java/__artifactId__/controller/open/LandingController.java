#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${artifactId}.controller.open;

import ${package}.commons.enums.ResponseCode;
import ${package}.commons.models.appBasic.Response;
import ${package}.commons.models.response.ServerStatus;
import ${package}.commons.utils.IOSFUtils;
import ${package}.${artifactId}.appBasic.i18n.MessageKey;
import ${package}.${artifactId}.controller.BaseController;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class LandingController extends BaseController {

	private final LocalDateTime serverStartupDatetime = LocalDateTime.now();

	@GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public Response<ServerStatus> ${parentArtifactId}Core() {
		Response<ServerStatus> response = new Response<>();

		ServerStatus serverStatus = new ServerStatus();
		serverStatus.setServerTime(IOSFUtils.getDateTimeString(LocalDateTime.now()));
		serverStatus.setStartupTime(IOSFUtils.getDateTimeString(this.serverStartupDatetime));

		response.setItems(serverStatus);
		response.setResponseCode(ResponseCode.OPERATION_SUCCESSFUL.getCode());
		response.setResponseMessage(getMessage(MessageKey.SERVICE_RUNNING));

		return response;
	}
}
