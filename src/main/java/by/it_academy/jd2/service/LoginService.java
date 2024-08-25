package by.it_academy.jd2.service;

import by.it_academy.jd2.dto.UserDTO;
import by.it_academy.jd2.service.api.ILoginService;
import by.it_academy.jd2.storage.UserStorageMemory;

public class LoginService implements ILoginService {

    private final static LoginService instance = new LoginService();
    private final UserStorageMemory userStorage = UserStorageMemory.getInstance();

    private LoginService() {
    }
    public boolean isPasswordValid(UserDTO user, String password) {
        return user.getPassword().equals(password);
    }

    @Override
    public UserDTO login(String login, String password) {
        UserDTO user = userStorage.get(login);
        if (user == null) {
            throw new IllegalArgumentException("User with this login does not exist.");
        }
        if (!isPasswordValid(user, password)) {
            throw new IllegalArgumentException("Invalid login or password.");
        }

        return user;
    }

    public static LoginService getInstance() {
        return instance;
    }
}

