package service.locations;

import entity.locations.Cropland;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
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

    public Cropland getCroplandByIdAndCoordinate(Cropland cropland) {

        manager.getTransaction().begin();

        Session session = (Session) manager.getDelegate();
        Criteria criteria = session.createCriteria(Cropland.class);
        criteria.add(Restrictions.eq("position", cropland.getPosition()));
        criteria.add(Restrictions.eq("userId", cropland.getUserId()));

        Cropland croplandFromBase = new Cropland();

        try {
            croplandFromBase = (Cropland) criteria.uniqueResult();
        } catch (HibernateException e) {
            e.printStackTrace();
        }

        manager.getTransaction().commit();

        if (croplandFromBase == null)
            return cropland;
        else
            return croplandFromBase;
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
