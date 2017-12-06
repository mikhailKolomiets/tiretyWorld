package service;

import entity.Counter;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.time.LocalDate;

/**
 * Created by mihail on 23.10.17.
 */
public class CounterServise {
    EntityManagerFactory factory = Persistence.createEntityManagerFactory("Tirety");
    EntityManager manager = factory.createEntityManager();

    public boolean setCountByIp(String url) {

        LocalDate date = LocalDate.now();
        Counter counter;

        manager.getTransaction().begin();
        Session session = (Session) manager.getDelegate();
        Criteria criteria = session.createCriteria(Counter.class);
        criteria.add(Restrictions.eq("url", url));
        criteria.add(Restrictions.eq("date", date.toString()));
        counter = (Counter) criteria.uniqueResult();


        if (counter == null) {
            counter = new Counter();
            counter.setUrl(url);
            counter.setDate(date);
            manager.merge(counter);
            manager.getTransaction().commit();
            return false;
        } else
        manager.getTransaction().commit();
            return true;
    }

    public int getAllCount() {

        manager.getTransaction().begin();

        int allCount = (int)(long)(manager.createQuery("SELECT count (*) FROM Counter").getSingleResult());
        manager.getTransaction().commit();

        return allCount;
    }

    public int getTodayCount() {

        manager.getTransaction().begin();

        int todayCount = (int)(long)(manager.createQuery("SELECT COUNT (*) FROM Counter where date = '" +
                LocalDate.now().toString() + "'").getSingleResult());
        manager.getTransaction().commit();

        return todayCount;
    }
}
