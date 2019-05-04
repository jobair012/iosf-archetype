#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${artifactId}.repository;

import ${package}.commons.enums.Status;
import ${package}.${artifactId}.entity.RoleGroup;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class RoleGroupRepo extends BaseRepository<RoleGroup>{

    public List<RoleGroup> getAllActiveByUserGroup(String userGroupId){

        String hql = "FROM RoleGroup roleGroup WHERE roleGroup.status = :status AND roleGroup.userGroup.id = :userGroupId";
        Query<RoleGroup> query = getSession().createQuery(hql, RoleGroup.class);
        query.setParameter("status", Status.ACTIVE.getCode());
        query.setParameter("userGroupId", userGroupId);

        return query.list();
    }
}
