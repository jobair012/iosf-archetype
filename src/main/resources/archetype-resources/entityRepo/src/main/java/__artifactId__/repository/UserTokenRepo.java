#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${artifactId}.repository;

import ${package}.${artifactId}.entity.UserToken;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class UserTokenRepo extends BaseRepository<UserToken> {

    public UserToken getByUserToken(String token){

        String hql = "FROM UserToken userToken WHERE userToken.token = :token";
        Query<UserToken> query = getSession().createQuery(hql);
        query.setParameter("token", token);

        return query.uniqueResult();
    }
}
