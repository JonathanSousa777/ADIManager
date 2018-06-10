package com.adisolucoes.adimanager.dao;

import javax.persistence.EntityManagerFactory;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 *
 * @author ADI Soluções
 */
@ApplicationScoped
public class CreateEntityManagerProducer {

    EntityManagerFactory factory;

    public CreateEntityManagerProducer() {
        factory = Persistence.createEntityManagerFactory("ADIManagerPU");
    }

    @Produces
    @RequestScoped
    public EntityManager ceateEntityManager() {
        return factory.createEntityManager();
    }

    public void closeEntityManager(EntityManager manager) {
        manager.close();
    }
}
