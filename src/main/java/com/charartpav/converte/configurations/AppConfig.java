package com.charartpav.converte.configurations;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*@author Artem Charykov*/
@Configuration
public class AppConfig {
	@Bean
    public EntityManager entityManager(EntityManagerFactory entityManagerFactory) {
        return entityManagerFactory.createEntityManager( );
    }
	
	@Bean
    public EntityManagerFactory entityManagerFactory() {
	    return Persistence.createEntityManagerFactory("com.charartpav_converte_jar_0.0.1-SNAPSHOTPU");
    }

}
