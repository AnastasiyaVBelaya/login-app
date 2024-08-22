package by.it_academy.jd2.service;

import by.it_academy.jd2.dto.UserDTO;
import by.it_academy.jd2.model.ERole;
import by.it_academy.jd2.service.api.ILoginService;
import by.it_academy.jd2.storage.LoginStorage;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

public class LoginService implements ILoginService {

    private final static LoginService instance = new LoginService();
    private final LoginStorage loginStorage = LoginStorage.getInstance();

    private LoginService() {
        initializeAdmin();
    }

    private void initializeAdmin() {
        if (!adminExists()) {
            String adminLogin = "admin";
            String adminPassword = "admin";
            String adminFio = "Admin";
            LocalDate adminDateOfBirth = LocalDate.of(1111, 1, 1);
            UserDTO admin = new UserDTO(adminLogin, adminPassword, adminFio,
                    adminDateOfBirth, LocalDate.now(), ERole.ADMIN);
            loginStorage.save(admin);
        }
    }

    @Override
    public void create(UserDTO user) {
        if (userExists(user.getLogin())) {
            throw new IllegalArgumentException("User with this login already exists.");
        }
        loginStorage.save(user);
    }

    @Override
    public void update(UserDTO user) {
        if (!userExists(user.getLogin())) {
            throw new IllegalArgumentException("User with this login does not exist.");
        }
    }

    @Override
    public void delete(String login) {
        if (!userExists(login)) {
            throw new IllegalArgumentException("User with this login does not exist.");
        }
        loginStorage.delete(login);
    }

    @Override
    public UserDTO get(String login) {
        return loginStorage.get(login);
    }

    @Override
    public Collection<UserDTO> getAll() {
        return loginStorage.getAll();
    }

    @Override
    public boolean adminExists() {
        return loginStorage.getAll().stream()
                .anyMatch(user -> "Администратор".equals(user.getRole()));
    }

    public boolean userExists(String login) {
        return loginStorage.get(login) != null;
    }

    public static LoginService getInstance() {
        return instance;
    }
}
