#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${artifactId}.service;

import ${package}.commons.enums.ResponseCode;
import ${package}.commons.enums.Status;
import ${package}.commons.models.appBasic.KeyValue;
import ${package}.commons.models.appBasic.Response;
import ${package}.${artifactId}.appBasic.i18n.MessageKey;
import ${package}.${artifactId}.appBasic.mapper.DbObjectMapper;
import ${package}.entityRepo.entity.UserGroup;
import ${package}.entityRepo.repository.SystemUserRepo;
import ${package}.entityRepo.repository.UserGroupRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class LookupService extends BaseService{

	@Autowired private DbObjectMapper dbObjectMapper;
	@Autowired private UserGroupRepo userGroupRepo;
	@Autowired private SystemUserRepo systemUserRepo;
	
	public Response<List<KeyValue>> getRoleList() {
		Response<List<KeyValue>> response = new Response<>();	
		
		response.setItems(this.makeRoleList());
		response.setResponseCode(ResponseCode.OPERATION_SUCCESSFUL.getCode());
		response.setResponseMessage(getMessage(MessageKey.OPERATION_SUCCESSFUL));
		
		return response;
	}
	
	public Response<List<KeyValue>> getStatusList() {
		Response<List<KeyValue>> response = new Response<>();	
		
		response.setItems(this.makeStatusList());
		response.setResponseCode(ResponseCode.OPERATION_SUCCESSFUL.getCode());
		response.setResponseMessage(getMessage(MessageKey.OPERATION_SUCCESSFUL));
		
		return response;
	}

	private List<KeyValue> makeStatusList() {
		List<KeyValue> statusList = new ArrayList<>();
		
		statusList.add(new KeyValue(String.valueOf(Status.INACTIVE.getCode()), Status.INACTIVE.getDescription()));
		statusList.add(new KeyValue(String.valueOf(Status.ACTIVE.getCode()), Status.ACTIVE.getDescription()));
		
		return statusList;
	}

	private List<KeyValue> makeRoleList() {
		List<KeyValue> roleList = new ArrayList<>();

		for(UserGroup userGroup : userGroupRepo.getAllActiveUserGroup()){
			roleList.add(new KeyValue(userGroup.getId(), userGroup.getName()));
		}

		return roleList;
	}

	public Response<List<KeyValue>> getAllSystemUser() {
		Response<List<KeyValue>> response = new Response<>();
		response.setItems(systemUserRepo.getKeyValue("id", "username", null));
		response.setResponseCode(ResponseCode.OPERATION_SUCCESSFUL.getCode());
		response.setResponseMessage(getMessage(MessageKey.OPERATION_SUCCESSFUL));

		return response;
	}
}
