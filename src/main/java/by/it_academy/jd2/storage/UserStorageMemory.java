package by.it_academy.jd2.storage;

import by.it_academy.jd2.dto.UserDTO;
import by.it_academy.jd2.storage.api.IUserStorageMemory;
import by.it_academy.jd2.model.ERole;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.time.LocalDate;

public class UserStorageMemory implements IUserStorageMemory {

    private final static UserStorageMemory instance = new UserStorageMemory();

    private Map<String, UserDTO> users = new HashMap<>();

    private UserStorageMemory() {
        String adminLogin = "admin";
        String adminPassword = "admin";
        String adminFio = "Admin";
        LocalDate adminDateOfBirth = LocalDate.of(1970, 1, 1);
        UserDTO admin = new UserDTO(adminLogin, adminPassword, adminFio,
                adminDateOfBirth, LocalDate.now(), ERole.ADMIN);
        users.put(admin.getLogin(), admin);
    }

    @Override
    public void save(UserDTO user) {
        users.put(user.getLogin(), user);
    }

/*
    @Override
    public void update(UserDTO user) {
        users.put(user.getLogin(), user);
    }
*/

/*
    @Override
    public void delete(UserDTO user) {
        users.remove(user.getLogin());
    }
*/

    @Override
    public UserDTO get(String login) {
        return users.get(login);
    }

    @Override
    public Collection<UserDTO> getAll() {
        return users.values();
    }

    public static UserStorageMemory getInstance() {
        return instance;
    }
}
