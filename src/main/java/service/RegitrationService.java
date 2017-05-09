package service;

import entity.Registration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by mihail on 09.05.17.
 */
public class RegitrationService {
    EntityManagerFactory factory = Persistence.createEntityManagerFactory("Tirety");
    EntityManager manager = factory.createEntityManager();

    public void addRegistrationData(Registration registrationData) {
        manager.getTransaction().begin();
        manager.merge(registrationData);
        manager.getTransaction().commit();
    }

    public Registration findUserByEmail(String email){
        manager.getTransaction().begin();
        Registration registrationData = null;
        try {
            registrationData = manager.createQuery("SELECT a from entity.Registration a where a.email='" + email + "'", Registration.class).getSingleResult();
        }catch (Exception e) {

        }
        return registrationData;
    }
}
