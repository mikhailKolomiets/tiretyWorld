package service;

import entity.Map;
import entity.User;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mihail on 13.10.17.
 */
public class MapService {

    EntityManagerFactory factory = Persistence.createEntityManagerFactory("Tirety");
    EntityManager manager = factory.createEntityManager();

    public void addLocation(String name, int position) {
        Map map = new Map();
        map.setName(name);
        map.setPosition(position);

        manager.getTransaction().begin();
        manager.merge(map);
        manager.getTransaction().commit();
    }

    public List<Map> getLocations(int position, int radius) {
        List<Map> locations;
        int positionMinX = position % 10000 - radius;
        int positionMinY = position - radius * 10000;
        int positionMaxX = position % 10000 + radius;
        int posiyionMaxY = position + radius * 10000;

        manager.getTransaction().begin();
        locations = manager.createQuery("SELECT a FROM Map a WHERE MOD (a.position, 10000) < " + positionMaxX +
                " AND MOD (a.position, 10000) > " + positionMinX + "AND a.position < " + posiyionMaxY +
                " AND a.position > " + positionMinY, Map.class).getResultList();
        manager.getTransaction().commit();
        return locations;
    }

    public Map getLocationByPosition(int position) {
        Map map = new Map();

        manager.getTransaction().begin();
        Session session = (Session) manager.getDelegate();
        Criteria criteria = session.createCriteria(Map.class);
        criteria.add(Restrictions.eq("position", position));

        try {
            map = (Map) criteria.uniqueResult();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        manager.getTransaction().commit();

        return map;
    }
}
