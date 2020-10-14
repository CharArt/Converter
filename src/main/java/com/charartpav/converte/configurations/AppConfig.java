package com.charartpav.converte.configurations;

import javax.persistence.Persistence;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;

/*@author Artem Charykov*/

@Configuration
public class AppConfig {

	@Bean
    public EntityManagerFactory entityManagerFactory() {
	    return Persistence.createEntityManagerFactory("com.charartpav_converte_jar_0.0.1-SNAPSHOTPU");
    }

	@Bean
    public EntityManager entityManager(EntityManagerFactory entityManagerFactory) {
        return entityManagerFactory.createEntityManager( );
    }

	@Bean (name = "transactionManager")
	public JpaTransactionManager jpaTarnsactionManager (EntityManagerFactory entityManagerFactory){
		JpaTransactionManager TransactionManager = new JpaTransactionManager();
		TransactionManager.setEntityManagerFactory(entityManagerFactory);
		return TransactionManager;
	}
}