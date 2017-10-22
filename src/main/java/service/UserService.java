package service;

import entity.Registration;
import entity.User;
import entity.items.Userbeg;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

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

        Userbeg userbeg = new Userbeg();
        try {
            TreeMap oneCrop = new TreeMap<String, Integer>();

            oneCrop.put("Пшеница", 1);
            userbeg.setItems(oneCrop);
        } catch (Exception e) {
            System.out.println(e.toString() + " UserService 1");
        }


        manager.getTransaction().begin();
        user = manager.merge(user);
        userbeg.setUserId(user.getId());
        manager.merge(userbeg);
        manager.getTransaction().commit();

        return user;
    }

    public User findUserByEmail(String email) {
        User user;
        manager.getTransaction().begin();
        Session session = (Session) manager.getDelegate();
        Criteria criteria = session.createCriteria(User.class);
        criteria.add(Restrictions.eq("email", email));

        try {
            user = (User) criteria.uniqueResult();

        } catch (HibernateException e) {
            e.printStackTrace();
            return null;
        }
        manager.getTransaction().commit();
        if (user == null)
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
        return user;
    }

    public User updateUser(User user) {

        manager.getTransaction().begin();
        user = manager.merge(user);
        manager.getTransaction().commit();
        return user;
    }


    /**
     * Userbeg
     */

    public void createUserbeg(long userId) {
        Userbeg userbeg = new Userbeg();

        userbeg.setUserId(userId);

        manager.getTransaction().begin();
        manager.merge(userbeg);
        manager.getTransaction().commit();

    }

    public Map getUserbeg(long userId) {

        Map userbeg = new TreeMap<String, Integer>();
        try {
            manager.getTransaction().begin();
            Session session = (Session) manager.getDelegate();
            Criteria criteria = session.createCriteria(Userbeg.class);
            criteria.add(Restrictions.eq("userId", userId));


            Userbeg beg = (Userbeg) criteria.uniqueResult();
            userbeg = beg.getItems();
        } catch (HibernateException e) {
            System.out.println(e.toString() + " userService 1");
        }
        manager.getTransaction().commit();
        return userbeg;

    }

    public void updateUserbeg(Userbeg userbeg) {
        manager.getTransaction().begin();
        manager.merge(userbeg);
        manager.getTransaction().commit();
    }

    public Userbeg getUserbegByUserId(long userId) {

        manager.getTransaction().begin();
        Session session = (Session) manager.getDelegate();
        Criteria criteria = session.createCriteria(Userbeg.class);
        criteria.add(Restrictions.eq("userId", userId));


        Userbeg userbeg = (Userbeg) criteria.uniqueResult();
        manager.getTransaction().commit();

        return userbeg;
    }
}
