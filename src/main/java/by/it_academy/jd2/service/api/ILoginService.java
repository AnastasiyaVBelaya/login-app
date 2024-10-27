package by.it_academy.jd2.service.api;

import by.it_academy.jd2.dto.AuthenticationDTO;
import by.it_academy.jd2.dto.UserSessionDTO;

public interface ILoginService {

    boolean isPasswordValid(String rawPassword, String storedPassword);

    UserSessionDTO authenticate(AuthenticationDTO authenticationDTO);
}