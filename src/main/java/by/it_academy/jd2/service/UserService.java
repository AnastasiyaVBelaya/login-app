package by.it_academy.jd2.service;

import by.it_academy.jd2.controller.listener.StatisticsListener;
import by.it_academy.jd2.dto.UserDTO;
import by.it_academy.jd2.dto.UserSessionDTO;
import by.it_academy.jd2.exceptions.UserAlreadyExistsException;
import by.it_academy.jd2.exceptions.UserNotFoundException;
import by.it_academy.jd2.model.ERole;
import by.it_academy.jd2.service.api.IUserService;
import by.it_academy.jd2.storage.api.IUserStorage;
import by.it_academy.jd2.storage.entity.UserEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class UserService implements IUserService {

    private final IUserStorage storage;
    private final UserValidator validator;

    public UserService(IUserStorage storage, UserValidator validator) {
        this.storage = storage;
        this.validator = validator;
    }

    @Override
    public UserSessionDTO create(UserDTO userDTO) {
        validator.validateLogin(userDTO);
        validator.validatePassword(userDTO);

        if (validator.userExists(userDTO.getLogin())) {
            throw new UserAlreadyExistsException("Пользователь с таким логином уже существует.");
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setCreateAt(LocalDateTime.now());
        userEntity.setLogin(userDTO.getLogin());
        userEntity.setPassword(userDTO.getPassword());
        userEntity.setFio(userDTO.getFio());
        userEntity.setDateOfBirth(userDTO.getDateOfBirth());
        userEntity.setRole(ERole.USER);

        storage.create(userEntity);
        StatisticsListener.incrementUserCount();
        return UserSessionDTO.builder()
                .login(userEntity.getLogin())
                .fio(userEntity.getFio())
                .dateOfBirth(userEntity.getDateOfBirth())
                .role(userEntity.getRole())
                .build();
    }

    @Override
    public UserDTO get(String login) {
        UserEntity userEntity = storage.get(login);
        if (userEntity == null) {
            throw new UserNotFoundException("Пользователь с таким логином не найден.");
        }
        return mapToDTO(userEntity);
    }

    @Override
    public List<UserDTO> get() {
        return storage.get().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private UserDTO mapToDTO(UserEntity userEntity) {
        UserDTO userDTO = new UserDTO();
        userDTO.setLogin(userEntity.getLogin());
        userDTO.setFio(userEntity.getFio());
        userDTO.setDateOfBirth(userEntity.getDateOfBirth());
        return userDTO;
    }
}
