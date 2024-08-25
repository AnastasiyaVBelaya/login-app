package by.it_academy.jd2.service;

import by.it_academy.jd2.dto.UserDTO;
import by.it_academy.jd2.service.api.IUserService;
import by.it_academy.jd2.storage.UserStorageMemory;

import java.util.Collection;

public class UserService implements IUserService {

    private final static UserService instance = new UserService();
    private final UserStorageMemory userStorage = UserStorageMemory.getInstance();

    private UserService() {
    }

    @Override
    public void create(UserDTO user) {//TODO добавить проверки на длину логина и сложность пароля
        if (userExists(user.getLogin())) {
            throw new IllegalArgumentException("User with this login already exists.");
        }
        userStorage.save(user);
    }

/*
    @Override
    public void update(UserDTO user) {
        if (!userExists(user.getLogin())) {
            throw new IllegalArgumentException("User with this login does not exist.");
        }
        loginStorage.update(user);
    }
*/

/*
    @Override
    public void delete(UserDTO user) {
        if (!userExists(user.getLogin())) {
            throw new IllegalArgumentException("User with this login does not exist.");
        }
        loginStorage.delete(user);
    }
*/

    @Override
    public UserDTO get(String login) {
        return userStorage.get(login);
    }

    @Override
    public Collection<UserDTO> getAll() {
        return userStorage.getAll();
    }

    public boolean userExists(String login) {
        return userStorage.get(login) != null;
    }

    public static UserService getInstance() {
        return instance;
    }
}
