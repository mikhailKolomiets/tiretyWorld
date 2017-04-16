package service;

import entity.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by mihail on 16.04.17.
 */
public class TestService {

    EntityManagerFactory factory = Persistence.createEntityManagerFactory("Tirety");
    EntityManager manager = factory.createEntityManager();

    public void setTestNumber(String some) {
        manager.getTransaction().begin();
        Test test = new Test();
        test.setSome(some);
        manager.persist(test);
        manager.getTransaction().commit();
    }
}
