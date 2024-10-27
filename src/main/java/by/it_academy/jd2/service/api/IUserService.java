package by.it_academy.jd2.service.api;

import by.it_academy.jd2.dto.UserDTO;
import by.it_academy.jd2.dto.UserSessionDTO;

import java.util.List;

public interface IUserService {
    UserSessionDTO create(UserDTO user);

    UserDTO get(String login);

    List<UserDTO> get();

}
