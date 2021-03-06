package br.com.professordanilo.ecommerceyouteste.util;

import java.io.Serializable;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@ApplicationScoped
public class PersistenceFactory implements Serializable {

    private EntityManager em;
    
    private EntityManagerFactory emf = 
            Persistence.createEntityManagerFactory("imobiliariaPU");

    @Produces
    public EntityManager getEntityManager() {
	if(em == null || !em.isOpen()) {
	    this.em = emf.createEntityManager();
	}
        return em;
    }
}
