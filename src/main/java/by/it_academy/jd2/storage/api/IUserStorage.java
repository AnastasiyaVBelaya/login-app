package by.it_academy.jd2.storage.api;

import by.it_academy.jd2.model.ERole;
import by.it_academy.jd2.storage.entity.UserEntity;

import java.util.List;

public interface IUserStorage {
    void create(UserEntity entity);

    UserEntity get(String login);

    List<UserEntity> get();

    List<UserEntity> getByRole(ERole role);

    int getTotalUserCount();

}
