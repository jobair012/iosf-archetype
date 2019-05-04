#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${artifactId}.repository;

import ${package}.${artifactId}.entity.UserRoleGroup;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class UserRoleGroupRepo extends BaseRepository<UserRoleGroup>{

    public List<UserRoleGroup> getUserRoleGroupsByUserId(Long userId){

        String hql = "FROM UserRoleGroup URG WHERE URG.systemUser.id = :userId";
        Query<UserRoleGroup> query = getSession().createQuery(hql);
        query.setParameter("userId", userId);

        return query.list();
    }
}


