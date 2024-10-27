package by.it_academy.jd2.storage.hibernate;


import by.it_academy.jd2.storage.api.IMailStorage;
import by.it_academy.jd2.storage.entity.MessageEntity;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;
import java.util.UUID;

public class MailStorage implements IMailStorage {

    private final HibernateManager hibernateManager;

    public MailStorage(HibernateManager hibernateManager) {
        this.hibernateManager = hibernateManager;
    }

    @Override
    public void create(MessageEntity entity) {
        hibernateManager.inTransaction((entityManager) -> {
            entityManager.persist(entity);
            return entity;
        });
    }

    @Override
    public MessageEntity get(UUID id) {
        return hibernateManager.inTransaction((entityManager) -> {
            return entityManager.find(MessageEntity.class, id);
        });
    }

    @Override
    public List<MessageEntity> get() {
        return hibernateManager.inTransaction((entityManager) -> {
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<MessageEntity> query = builder.createQuery(MessageEntity.class);

            query.from(MessageEntity.class);

            return entityManager.createQuery(query).getResultList();
        });
    }

    @Override
    public List<MessageEntity> getByLogin(String login) {
        return hibernateManager.inTransaction((entityManager) -> {
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<MessageEntity> query = builder.createQuery(MessageEntity.class);
            Root<MessageEntity> mail = query.from(MessageEntity.class);

            query.where(builder.equal(mail.get("to"), login));

            return entityManager.createQuery(query).getResultList();
        });
    }

    @Override
    public int getTotalMessageCount() {
        return hibernateManager.inTransaction(entityManager -> {
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Long> query = builder.createQuery(Long.class);
            Root<MessageEntity> root = query.from(MessageEntity.class);
            query.select(builder.count(root));

            return entityManager.createQuery(query).getSingleResult().intValue();
        });
    }
}
