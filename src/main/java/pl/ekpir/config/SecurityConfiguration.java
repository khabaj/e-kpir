package pl.ekpir.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

	@Autowired
	DataSource dataSource;
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic()
        		.and()
                .authorizeRequests()
                .anyRequest().permitAll()
                .antMatchers("/api/**", "/h2-console/**").authenticated()
                .and().logout().logoutSuccessUrl("/")
                .and().csrf().disable();
        
        http.headers().frameOptions().disable();
    }
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	auth.jdbcAuthentication().dataSource(dataSource)
    		.usersByUsernameQuery("select login, password, 'true' from user where login=?")
    		.authoritiesByUsernameQuery(
    				"select user.login, role.role_name from user, role where user.login=? and user.role_id=role.role_id");
    }
}
