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

    public void setTestNumber(int number) {
        manager.getTransaction().begin();
        Test test = new Test();
        test.setSome(number);
        manager.persist(number);
        manager.getTransaction().commit();
    }
}
