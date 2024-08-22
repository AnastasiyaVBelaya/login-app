package by.it_academy.jd2.service.api;

import by.it_academy.jd2.dto.UserDTO;

import java.util.Collection;

public interface ILoginService {
    public void create(UserDTO user);
    void update(UserDTO user);
    void delete(String login);
    UserDTO get(String login);
    Collection<UserDTO> getAll();
    boolean adminExists();
    boolean userExists(String login);
}
