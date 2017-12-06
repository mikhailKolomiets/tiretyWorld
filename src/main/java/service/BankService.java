package service;

import entity.Bank;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by mihail on 22.10.17.
 */
public class BankService {
    EntityManagerFactory factory = Persistence.createEntityManagerFactory("Tirety");
    EntityManager manager = factory.createEntityManager();

    public Bank findBankingByUserId(long userId) {
        Bank bank;
        manager.getTransaction().begin();
        Session session = (Session) manager.getDelegate();
        Criteria criteria = session.createCriteria(Bank.class);
        criteria.add(Restrictions.eq("userId", userId));

        bank = (Bank) criteria.uniqueResult();
        manager.getTransaction().commit();
        return bank;
    }
}
