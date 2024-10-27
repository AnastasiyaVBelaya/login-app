package by.it_academy.jd2.storage.hibernate;

import by.it_academy.jd2.model.ERole;
import by.it_academy.jd2.storage.api.IUserStorage;
import by.it_academy.jd2.storage.entity.UserEntity;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;


import java.util.List;

public class UserStorage implements IUserStorage {

    private final HibernateManager hibernateManager;

    public UserStorage(HibernateManager hibernateManager) {
        this.hibernateManager = hibernateManager;
    }

    @Override
    public void create(UserEntity entity) {
        hibernateManager.inTransaction(entityManager -> {
            entityManager.persist(entity);
            return entity;
        });
    }

    @Override
    public UserEntity get(String login) {
        return hibernateManager.inTransaction(entityManager -> {
            return entityManager.find(UserEntity.class, login);
        });
    }

    @Override
    public List<UserEntity> get() {
        return hibernateManager.inTransaction(entityManager -> {
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<UserEntity> query = builder.createQuery(UserEntity.class);

            query.from(UserEntity.class);

            return entityManager.createQuery(query).getResultList();
        });
    }

    @Override
    public List<UserEntity> getByRole(ERole role) {
        return hibernateManager.inTransaction(entityManager -> {
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<UserEntity> query = builder.createQuery(UserEntity.class);
            Root<UserEntity> userEntityRoot = query.from(UserEntity.class);
            query.where(builder.equal(userEntityRoot.get("role"), role));

            return entityManager.createQuery(query).getResultList();
        });
    }

    @Override
    public int getTotalUserCount() {
        return hibernateManager.inTransaction(entityManager -> {
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Long> query = builder.createQuery(Long.class);
            Root<UserEntity> root = query.from(UserEntity.class);
            query.select(builder.count(root));

            return entityManager.createQuery(query).getSingleResult().intValue();
        });
    }
}
