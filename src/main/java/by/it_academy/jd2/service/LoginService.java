package by.it_academy.jd2.service;

import by.it_academy.jd2.dto.AuthenticationDTO;
import by.it_academy.jd2.dto.UserSessionDTO;
import by.it_academy.jd2.exceptions.InvalidCredentialsException;
import by.it_academy.jd2.exceptions.UserNotFoundException;
import by.it_academy.jd2.service.api.ILoginService;
import by.it_academy.jd2.storage.api.IUserStorage;
import by.it_academy.jd2.storage.entity.UserEntity;

public class LoginService implements ILoginService {

    private final IUserStorage storage;

    public LoginService(IUserStorage storage) {
        this.storage = storage;
    }

    @Override
    public boolean isPasswordValid(String rawPassword, String storedPassword) {
        return rawPassword.equals(storedPassword);
    }

    @Override
    public UserSessionDTO authenticate(AuthenticationDTO authenticationDTO) {
        UserEntity storedUser = storage.get(authenticationDTO.getLogin());
        if (storedUser == null) {
            throw new UserNotFoundException("Пользователь с таким логином не найден.");
        }

        if (!isPasswordValid(authenticationDTO.getPassword(), storedUser.getPassword())) {
            throw new InvalidCredentialsException("Неверный логин или пароль.");
        }

        return UserSessionDTO.builder()
                .login(storedUser.getLogin())
                .fio(storedUser.getFio())
                .dateOfBirth(storedUser.getDateOfBirth())
                .role(storedUser.getRole())
                .build();
    }
}
