package by.it_academy.jd2.storage.hibernate;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.function.Function;

public class HibernateManager implements AutoCloseable {
    private EntityManagerFactory factory =
            Persistence.createEntityManagerFactory("unit");

    public <T> T inTransaction(Function<EntityManager, T> work) {
        EntityManager entityManager = factory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            T data = work.apply(entityManager);
            transaction.commit();

            return data;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void close() throws Exception {
        if (factory != null && factory.isOpen()) {
            factory.close();
        }
    }
}
