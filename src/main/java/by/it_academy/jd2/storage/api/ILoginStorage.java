package by.it_academy.jd2.storage.api;

import by.it_academy.jd2.dto.UserDTO;

import java.util.Collection;

public interface ILoginStorage {
    void save(UserDTO user);

    void update(UserDTO user);

    void delete(String login);

    UserDTO get(String login);

    Collection<UserDTO> getAll();
}
