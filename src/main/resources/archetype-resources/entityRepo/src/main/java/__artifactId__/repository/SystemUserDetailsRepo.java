#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${artifactId}.repository;

import ${package}.commons.models.response.SystemUserDetailsResponse;
import ${package}.${artifactId}.entity.SystemUserDetails;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SystemUserDetailsRepo extends BaseRepository<SystemUserDetails> {

    public List<SystemUserDetailsResponse> getUserByUsername(String username){
        String hql = "FROM SystemUser systemUser WHERE systemUser.username = :username";
        Query query = getSession().createQuery(hql, SystemUserDetailsResponse.class);
        query.setParameter("username", username);

        return query.getResultList();
    }
}
