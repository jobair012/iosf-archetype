#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${artifactId}.service;

import ${package}.commons.enums.ResponseCode;
import ${package}.commons.models.appBasic.IOSFSearchResult;
import ${package}.commons.models.appBasic.Response;
import ${package}.commons.models.request.SystemUserSearchRequest;
import ${package}.commons.models.request.UserCrupRequest;
import ${package}.commons.models.response.*;
import ${package}.${artifactId}.appBasic.rester.IOSFRestTemplate;
import ${package}.${artifactId}.appBasic.rester.ServiceUrl;
import ${package}.${artifactId}.models.SystemUserSearchModel;
import ${package}.${artifactId}.models.viewModel.UserCrup;
import org.springframework.beans.BeanUtils;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

@Service
public class UserManagementService extends BaseService {

	public UserCrup initUserCRUPModel() {
		UserCrup user = new UserCrup();
		
		Response<UserCrupBasicInfo> basicInfo = this.getUserCrupBasicInfo();
		if(ResponseCode.OPERATION_SUCCESSFUL.getCode() == basicInfo.getResponseCode()) {
			user.setRoleList(basicInfo.getItems().getRoleList());
			user.setStatusList(basicInfo.getItems().getStatusList());
			user.setStatus(0);
			user.setActionState("init");
		}
		
		return user;
	}

	private Response<UserCrupBasicInfo> getUserCrupBasicInfo(){
		IOSFRestTemplate<Response<UserCrupBasicInfo>> restTemplate = new IOSFRestTemplate<>(serviceMessage);
		return restTemplate.get(ServiceUrl.USER_CRUP_BASIC_INFO, null, new ParameterizedTypeReference<Response<UserCrupBasicInfo>>() {});
	}

	public Response<Crup> crupUser(UserCrup user){
		IOSFRestTemplate<Response<Crup>> restTemplate = new IOSFRestTemplate<>(serviceMessage);
		UserCrupRequest requestBody = new UserCrupRequest();
		BeanUtils.copyProperties(user, requestBody);
		return restTemplate.post(ServiceUrl.USER_CRUP, requestBody, new ParameterizedTypeReference<Response<Crup>>() {});
	}

    public Response<SystemUserDetailsResponse> getSystemUserDetails(Long id) {
		IOSFRestTemplate<Response<SystemUserDetailsResponse>> restTemplate = new IOSFRestTemplate<>(serviceMessage);
		String fullUrl = String.format(ServiceUrl.USER_DETAILS, id);
		return restTemplate.get(fullUrl, null, new ParameterizedTypeReference<Response<SystemUserDetailsResponse>>() {});
    }

	public Response<SystemUserStatusResponse> getUserStatus(String username) {
		IOSFRestTemplate<Response<SystemUserStatusResponse>> restTemplate = new IOSFRestTemplate<>(serviceMessage);
		String fullUrl = String.format(ServiceUrl.USER_STATUS, username);
		return restTemplate.get(fullUrl, null, new ParameterizedTypeReference<Response<SystemUserStatusResponse>>() {});
	}

	public Response<SystemUserSearchData> getSystemUserSearchData(){
		IOSFRestTemplate<Response<SystemUserSearchData>> restTemplate = new IOSFRestTemplate<>(serviceMessage);
		return restTemplate.get(ServiceUrl.USER_SEARCH_DATA, null, new ParameterizedTypeReference<Response<SystemUserSearchData>>() {});
	}

	public SystemUserSearchModel initSystemUserSearchModel() {
		SystemUserSearchModel searchModel = new SystemUserSearchModel();

		Response<SystemUserSearchData> searchDataResponse = this.getSystemUserSearchData();
		if(ResponseCode.OPERATION_SUCCESSFUL.getCode() == searchDataResponse.getResponseCode()){
			searchModel.setSearchData(searchDataResponse.getItems());
		}

		return searchModel;
	}

    public Response<IOSFSearchResult> getSystemUserSearchResult(SystemUserSearchModel systemUserSearchModel) {
		IOSFRestTemplate<Response<IOSFSearchResult>> restTemplate = new IOSFRestTemplate<>(serviceMessage);
		SystemUserSearchRequest requestBody = new SystemUserSearchRequest();
		BeanUtils.copyProperties(systemUserSearchModel, requestBody);
		return restTemplate.post(ServiceUrl.USER_SEARCH_RESULT, requestBody, new ParameterizedTypeReference<Response<IOSFSearchResult>>() {});
	}
}
