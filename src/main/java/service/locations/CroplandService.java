package service.locations;

import entity.locations.Cropland;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUtil;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mihail on 15.10.17.
 */
public class CroplandService {

    EntityManagerFactory factory = Persistence.createEntityManagerFactory("Tirety");
    EntityManager manager = factory.createEntityManager();

    public void addCropland(Cropland cropland) {
        manager.getTransaction().begin();
        manager.merge(cropland);
        manager.getTransaction().commit();
    }

    public List getAllCroplands() {

        manager.getTransaction().begin();
        List croplandsList = manager.createQuery("SELECT a from Cropland a").getResultList();
        manager.getTransaction().commit();

        return croplandsList;
    }

    public Cropland getCroplandById(long id) {

        manager.getTransaction().begin();
         Cropland cropland = manager.find(Cropland.class, id);
        manager.getTransaction().commit();

        return cropland;

    }

    public void refrashCropland(Cropland cropland) {

        manager.getTransaction().begin();
        manager.merge(cropland);
        manager.getTransaction().commit();

    }
 }
