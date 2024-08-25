package by.it_academy.jd2.storage.api;

import by.it_academy.jd2.dto.UserDTO;

import java.util.Collection;

public interface IUserStorageMemory {
    void save(UserDTO user);

    //void update(UserDTO user);

    //void delete(UserDTO user);

    UserDTO get(String login);

    Collection<UserDTO> getAll();
}
