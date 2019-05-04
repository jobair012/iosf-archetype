#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${artifactId}.controller.restricted;

import ${package}.commons.models.appBasic.KeyValue;
import ${package}.commons.models.appBasic.Response;
import ${package}.${artifactId}.controller.BaseController;
import ${package}.${artifactId}.service.LookupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("lookup")
public class LookupController extends BaseController {

    @Autowired private LookupService lookupService;

    @GetMapping("systemuser")
    public Response<List<KeyValue>> systemUserStatus() {
        return lookupService.getAllSystemUser();
    }

}
