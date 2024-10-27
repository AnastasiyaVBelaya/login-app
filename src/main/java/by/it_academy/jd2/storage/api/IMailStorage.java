package by.it_academy.jd2.storage.api;

import by.it_academy.jd2.storage.entity.MessageEntity;

import java.util.List;
import java.util.UUID;

public interface IMailStorage {
    void create(MessageEntity entity);

    MessageEntity get(UUID id);

    List<MessageEntity> get();

    List<MessageEntity> getByLogin(String login);

    int getTotalMessageCount();

}
