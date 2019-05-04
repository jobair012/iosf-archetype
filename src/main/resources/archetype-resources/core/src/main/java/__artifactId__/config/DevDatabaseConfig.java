#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${artifactId}.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Profile("dev")
@Configuration
@EnableTransactionManagement
@PropertySource("classpath:db/db-dev.properties")
@ComponentScan("${package}.entityRepo")
public class DevDatabaseConfig extends DbConfig{

//

}

