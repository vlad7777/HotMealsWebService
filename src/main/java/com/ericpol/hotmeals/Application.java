package com.ericpol.hotmeals;

import java.util.Properties;

import javax.sql.DataSource;

import com.ericpol.hotmeals.auth.OAuth2SecurityConfiguration;
import com.ericpol.hotmeals.json.ResourcesMapper;

import org.hibernate.ejb.HibernatePersistence;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import com.fasterxml.jackson.databind.ObjectMapper;

//Tell Spring to automatically inject any dependencies that are marked in
//our classes with @Autowired
@EnableAutoConfiguration
// Tell Spring that this object represents a Configuration for the
// application
@Configuration
// Tell Spring to go and scan our controller package (and all sub packages) to
// find any controllers or other components that are part of our applciation.
// Any class in this package that is annotated with @Controller is going to be
// automatically discovered and connected to the DispatcherServlet.
@ComponentScan
// We use the @Import annotation to include our OAuth2SecurityConfiguration
// as part of this configuration so that we can have security and oauth
// setup by Spring
@Import(OAuth2SecurityConfiguration.class)
public class Application extends RepositoryRestMvcConfiguration {

    // The app now requires that you pass the location of the keystore and
    // the password for your private key that you would like to setup HTTPS
    // with. In Eclipse, you can set these options by going to:
    //    1. Run->Run Configurations
    //    2. Under Java Applications, select your run configuration for this app
    //    3. Open the Arguments tab
    //    4. In VM Arguments, provide the following information to use the
    //       default keystore provided with the sample code:
    //
    //       -Dkeystore.file=src/main/resources/private/keystore -Dkeystore.pass=changeit
    //
    //    5. Note, this keystore is highly insecure! If you want more securtiy, you
    //       should obtain a real SSL certificate:
    //
    //       http://tomcat.apache.org/tomcat-7.0-doc/ssl-howto.html
    //
    // Tell Spring to launch our app!
    public static void main(String[] args) {
    	SpringApplication.run(Application.class, args);
    }

    // We are overriding the bean that RepositoryRestMvcConfiguration
    // is using to convert our objects into JSON so that we can control
    // the format. The Spring dependency injection will inject our instance
    // of ObjectMapper in all of the spring data rest classes that rely
    // on the ObjectMapper. This is an example of how Spring dependency
    // injection allows us to easily configure dependencies in code that
    // we don't have easy control over otherwise.
    @Override
    public ObjectMapper halObjectMapper(){
        return new ResourcesMapper();
    }
    
    @Bean
    public DataSource dataSource() {

    	DriverManagerDataSource dataSource = new DriverManagerDataSource();
 
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:file:hotmeals;FILE_LOCK=FS;DB_CLOSE_DELAY=-1;MODE=Oracle;TRACE_LEVEL_SYSTEM_OUT=2");
        dataSource.setUsername("sa");
        dataSource.setPassword("sa");
 
        return dataSource;
    }

    private Properties hibernateProperties() {

    	Properties properties = new Properties();
        //properties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("hibernate.ejb.naming_strategy", "org.hibernate.cfg.ImprovedNamingStrategy");
        return properties;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

    	LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        entityManagerFactoryBean.setJpaProperties(hibernateProperties());
         
        return entityManagerFactoryBean;
    }
}
