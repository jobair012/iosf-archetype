#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${artifactId}.models.response;

import ${package}.${artifactId}.models.appBasic.KeyValue;

import java.util.List;

public class SystemUserSearchData {

    private List<KeyValue> usernameList;
    private List<KeyValue> roleList;
    private List<KeyValue> statusList;

    public List<KeyValue> getUsernameList() {
        return usernameList;
    }

    public void setUsernameList(List<KeyValue> usernameList) {
        this.usernameList = usernameList;
    }

    public List<KeyValue> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<KeyValue> roleList) {
        this.roleList = roleList;
    }

    public List<KeyValue> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<KeyValue> statusList) {
        this.statusList = statusList;
    }
}
