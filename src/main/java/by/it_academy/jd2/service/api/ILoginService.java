package by.it_academy.jd2.service.api;

import by.it_academy.jd2.dto.UserDTO;

public interface ILoginService {

    boolean isPasswordValid(UserDTO user, String password);

    UserDTO login(String login, String password);
}
