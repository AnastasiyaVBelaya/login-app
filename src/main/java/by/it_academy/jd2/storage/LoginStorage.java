package by.it_academy.jd2.storage;

import by.it_academy.jd2.dto.UserDTO;
import by.it_academy.jd2.storage.api.ILoginStorage;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoginStorage implements ILoginStorage {

    private final static LoginStorage instance = new LoginStorage();

    private Map<String, UserDTO> users = new HashMap<>();

    private LoginStorage() {
    }

    @Override
    public void save(UserDTO user) {
        users.put(user.getLogin(), user);
    }

    @Override
    public void update(UserDTO user) {
        users.put(user.getLogin(), user);
    }

    @Override
    public void delete(String login) {
        users.remove(login);
    }

    @Override
    public UserDTO get(String login) {
        return users.get(login);
    }

    @Override
    public Collection<UserDTO> getAll() {
        return users.values();
    }

    public static LoginStorage getInstance() {
        return instance;
    }
}
