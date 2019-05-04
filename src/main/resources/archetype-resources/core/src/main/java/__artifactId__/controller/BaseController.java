#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${artifactId}.controller;

import ${package}.${artifactId}.appBasic.i18n.ServiceMessage;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseController {

    @Autowired
    private ServiceMessage serviceMessage;

    protected String getMessage(String messageKey) {
        return serviceMessage.getMessage(messageKey);
    }


}
