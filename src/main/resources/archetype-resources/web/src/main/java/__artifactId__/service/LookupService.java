#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${artifactId}.service;

import ${package}.commons.models.appBasic.KeyValue;
import ${package}.commons.models.appBasic.Response;
import ${package}.${artifactId}.appBasic.rester.IOSFRestTemplate;
import ${package}.${artifactId}.appBasic.rester.ServiceUrl;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LookupService extends BaseService {

    public Response<List<KeyValue>> getAllSystemUser(){
        IOSFRestTemplate<Response<List<KeyValue>>> restTemplate = new IOSFRestTemplate<>(serviceMessage);
        return restTemplate.get(ServiceUrl.USERNAME_LOOKUP, null, new ParameterizedTypeReference<Response<List<KeyValue>>>() {});
    }

}
