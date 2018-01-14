package service.locations;

import entity.locations.IronMind;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by mihail on 16.12.17.
 */
public class IronMindService {

    EntityManagerFactory factory = Persistence.createEntityManagerFactory("Tirety");
    EntityManager manager = factory.createEntityManager();

    public void updateIronMindByCoordinate(IronMind ironMind) {

        IronMind ironMindFromBase = findIronMindByCoordinate(ironMind.getPosition());

        manager.getTransaction().begin();

        if (ironMindFromBase != null) {
            ironMind.setId(ironMindFromBase.getId());
        }

        manager.merge(ironMind);
        manager.getTransaction().commit();
    }

    public IronMind findIronMindByCoordinate(int coordinate) {
        IronMind ironMind = null;
        manager.getTransaction().begin();

        Session session = (Session) manager.getDelegate();
        Criteria criteria = session.createCriteria(IronMind.class);
        criteria.add(Restrictions.eq("position", coordinate));

        try {
            ironMind = (IronMind) criteria.uniqueResult();
        } catch (HibernateException e) {
            e.printStackTrace();
        }

        manager.getTransaction().commit();
        return ironMind;
    }

}
