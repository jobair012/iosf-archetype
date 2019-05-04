#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${artifactId}.repository;

import ${package}.${artifactId}.entity.UserRole;
import org.springframework.stereotype.Repository;

@Repository
public class RoleRepo extends BaseRepository<UserRole> {

}
