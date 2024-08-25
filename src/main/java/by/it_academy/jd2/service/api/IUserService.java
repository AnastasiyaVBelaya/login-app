package by.it_academy.jd2.service.api;

import by.it_academy.jd2.dto.UserDTO;

import java.util.Collection;

public interface IUserService {
    void create(UserDTO user);

    //void update(UserDTO user);

    //void delete(UserDTO user);

    UserDTO get(String login);

    Collection<UserDTO> getAll();

    boolean userExists(String login);
}
