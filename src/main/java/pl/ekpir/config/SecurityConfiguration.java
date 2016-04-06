package pl.ekpir.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

	@Autowired
	DataSource dataSource;
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        	.httpBasic()
        	.and()
            .authorizeRequests()
            .antMatchers("/api/registration").permitAll()
            .antMatchers("/api/**").authenticated()      
            .anyRequest().permitAll()
            .and()
            .logout().deleteCookies("JSESSIONID").logoutSuccessUrl("/")
            .and().csrf().disable(); 
        
        http.headers().frameOptions().disable();
    }
    
    @Override
    public void configure(WebSecurity web) throws Exception {
    	web.ignoring()
        .antMatchers("/h2-console/**");
    }
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	auth.jdbcAuthentication().dataSource(dataSource)
    		.usersByUsernameQuery("select login, password, 'true' from user where login=?")
    		.authoritiesByUsernameQuery(
    				"select user.login, user.role as role from user where user.login=?");
    }
}
