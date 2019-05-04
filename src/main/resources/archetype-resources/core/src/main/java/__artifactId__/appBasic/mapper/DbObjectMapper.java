#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${artifactId}.appBasic.mapper;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.impl.ConfigurableMapper;
import ${package}.commons.enums.Status;
import ${package}.commons.models.response.SystemUserDetailsResponse;
import ${package}.commons.utils.IOSFUtils;
import ${package}.entityRepo.entity.SystemUser;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class DbObjectMapper extends ConfigurableMapper {

    protected final void configure(final MapperFactory factory) {

        factory.classMap(SystemUser.class, SystemUserDetailsResponse.class)
                .field("id", "id")
                .field("username", "username")
                .field("status", "status")
                .field("createdBy", "createdBy")
                .field("updatedBy", "updatedBy")
                .field("systemUserDetails.fullName", "fullName")
                .field("systemUserDetails.emailAddress", "emailAddress")
                .field("systemUserDetails.contactNo", "contactNo")
                .customize(new CustomMapper<SystemUser, SystemUserDetailsResponse>() {
                    @Override
                    public void mapAtoB(SystemUser systemUser, SystemUserDetailsResponse systemUserDetailsResponse, MappingContext context) {
                        systemUserDetailsResponse.setStatusDescription(Status.getDescriptionByCode(systemUser.getStatus()));
                        systemUserDetailsResponse.setCreatedOn(IOSFUtils.getDateTimeString(systemUser.getCreatedDate()));
                        systemUserDetailsResponse.setUpdatedOn(IOSFUtils.getDateTimeString(systemUser.getUpdatedDate()));
                        Set<String> roles = new HashSet<>();
                        systemUser.getUserRoleGroup().forEach(role -> {
                            if(Status.ACTIVE.getCode() == role.getStatus()){
                                roles.add(role.getRoleGroup().getUserGroup().getId());
                            }
                        });
                        systemUserDetailsResponse.setUserRoles(roles);

                    }
                })
                .register();

    }
}
