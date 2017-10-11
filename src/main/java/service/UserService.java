package service;

import entity.Registration;
import entity.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by mihail on 09.10.17.
 */
public class UserService {
    EntityManagerFactory factory = Persistence.createEntityManagerFactory("Tirety");
    EntityManager manager = factory.createEntityManager();

    public User createUser(Registration uRegData) {
        User user = new User();
        user.setName(uRegData.getName());
        user.setEmail(uRegData.getEmail());
        user.setPassword(uRegData.getPassword());

        manager.getTransaction().begin();
        manager.merge(user);
        manager.getTransaction().commit();

        return user;
    }
}
