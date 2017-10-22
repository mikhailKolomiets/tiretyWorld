package service.items;

import entity.items.Culture;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by mihail on 20.10.17.
 */
public class CultureService {

    EntityManagerFactory factory = Persistence.createEntityManagerFactory("Tirety");
    EntityManager manager = factory.createEntityManager();

    public Culture getCultureById(long id) {
        manager.getTransaction().begin();
        Culture culture = manager.find(Culture.class, id);
        manager.getTransaction().commit();

        return culture;
    }

}
