package service;

import entity.Registration;
import entity.User;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

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

    public User findUserByEmail(String email) {
        User user = new User();
        manager.getTransaction().begin();
        Session session = (Session) manager.getDelegate();
        Criteria criteria = session.createCriteria(User.class);
        criteria.add(Restrictions.eq("email", email));

        try {
            user = (User) criteria.uniqueResult();

        } catch (HibernateException e) {
            e.printStackTrace();
        }
        manager.getTransaction().commit();
        if(user == null)
            return null;
        return user;
    }

    public User findUserByName(String name) {
        User user = new User();
        manager.getTransaction().begin();
        Session session = (Session) manager.getDelegate();
        Criteria criteria = session.createCriteria(User.class);
        criteria.add(Restrictions.eq("name", name));

        try {
            user = (User) criteria.uniqueResult();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        manager.getTransaction().commit();
        if(user == null)
            return null;
        return user;
    }
}
