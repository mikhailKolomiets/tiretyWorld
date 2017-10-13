package service;

import entity.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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

}
