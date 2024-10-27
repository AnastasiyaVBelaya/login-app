package by.it_academy.jd2.service;

import by.it_academy.jd2.dto.UserDTO;
import by.it_academy.jd2.exceptions.InvalidUserException;
import by.it_academy.jd2.storage.api.IUserStorage;

public class UserValidator {
    private final IUserStorage storage;

    public UserValidator(IUserStorage storage) {
        this.storage = storage;
    }

    public boolean userExists(String login) {
        return storage.get(login) != null;
    }

    public void validateLogin(UserDTO user) {
        if (user == null || user.getLogin() == null || user.getLogin().length() < 5) {
            throw new InvalidUserException("Логин должен быть не менее 5 символов в длину.");
        }
    }

    public void validatePassword(UserDTO user) {
        if (user == null || user.getPassword() == null || user.getPassword().length() < 8 || !user.getPassword().matches(".*\\d.*")) {
            throw new InvalidUserException("Пароль должен быть не менее 8 символов в длину и содержать минимум одну цифру.");
        }
    }
}
