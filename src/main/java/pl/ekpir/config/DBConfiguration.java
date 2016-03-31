package pl.ekpir.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DBConfiguration {

	@Value("${spring.datasource.driverClassName}")
	private String databaseDriverClassName;
	 
	@Value("${spring.datasource.url}")
	private String datasourceUrl;
	 
	@Value("${spring.datasource.username}")
	private String databaseUsername;
	 
	@Value("${spring.datasource.password}")
	private String databasePassword;
	
    @Bean
    public DataSource datasource() {
        org.apache.tomcat.jdbc.pool.DataSource ds = new org.apache.tomcat.jdbc.pool.DataSource();
        ds.setDriverClassName(databaseDriverClassName);
        ds.setUrl(datasourceUrl);
        ds.setUsername(databaseUsername);
        ds.setPassword(databasePassword);
     
        return ds;
    }
	
}
