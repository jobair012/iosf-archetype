#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${artifactId}.controller.restricted;

import ${package}.commons.enums.ResponseCode;
import ${package}.commons.models.appBasic.IOSFSearchResult;
import ${package}.commons.models.appBasic.Response;
import ${package}.commons.models.request.SystemUserSearchRequest;
import ${package}.commons.models.request.UserCrupRequest;
import ${package}.commons.models.response.*;
import ${package}.${artifactId}.appBasic.i18n.MessageKey;
import ${package}.${artifactId}.controller.BaseController;
import ${package}.${artifactId}.service.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserManagementController extends BaseController {

    @Autowired
    private UserManagementService userManagementService;

    @GetMapping("user/crup/basic-info")
    public Response<UserCrupBasicInfo> userCRUPBasicInfo() {
        return userManagementService.getUserCRUPBasicInfo();
    }

    @PostMapping("user/crup")
    public Response<Crup> createSystemUser(@Validated @RequestBody UserCrupRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return new Response(ResponseCode.INVALID_ARGUMENT.getCode(), getMessage(MessageKey.INVALID_ARGUMENTS));
        }
        return userManagementService.crupSystemUser(request);
    }

    @GetMapping("user/details/{id}")
    public Response<SystemUserDetailsResponse> systemUserDetails(@PathVariable(value = "id") Long id) {
        return userManagementService.getSystemUser(id);
    }

    @GetMapping("user/status/{username}")
    public Response<SystemUserStatusResponse> systemUserStatus(@PathVariable(value = "username") String username) {
        return userManagementService.getSystemUserStatus(username);
    }

    @GetMapping("user/search-data")
    public Response<SystemUserSearchData> systemUserSearchData() {
        return userManagementService.getSystemUserSearchData();
    }

    @PostMapping("user/search")
    public Response<IOSFSearchResult> searchSystemUser(@RequestBody SystemUserSearchRequest request) {
        return userManagementService.getSystemUserSearchResult(request);
    }

}
