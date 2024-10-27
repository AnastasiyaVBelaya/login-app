package by.it_academy.jd2.service.factory;

import by.it_academy.jd2.service.*;
import by.it_academy.jd2.service.api.*;
import by.it_academy.jd2.storage.api.IUserStorage;
import by.it_academy.jd2.storage.api.IMailStorage;
import by.it_academy.jd2.storage.factory.StorageFactory;
import lombok.Getter;

public class ServiceFactory {

    @Getter
    private static final IUserService userService;
    @Getter
    private static final ILoginService loginService;
    @Getter
    private static final IMailService mailService;
    @Getter
    private static final StatisticsService statisticsService;
    @Getter
    private static final UserValidator userValidator;

    static {
        userValidator = createUserValidator();
        userService = createUserService(userValidator);
        loginService = createLoginService();
        mailService = createMailService();
        statisticsService = createStatisticsService();
    }

    private static IUserService createUserService(UserValidator userValidator) {
        IUserStorage userStorage = getUserStorage();
        return new UserService(userStorage, userValidator);
    }

    private static ILoginService createLoginService() {
        IUserStorage userStorage = getUserStorage();
        return new LoginService(userStorage);
    }

    private static IMailService createMailService() {
        IMailStorage mailStorage = getMailStorage();
        return new MailService(mailStorage);
    }

    private static StatisticsService createStatisticsService() {
        IUserStorage userStorage = getUserStorage();
        IMailStorage mailStorage = getMailStorage();
        return new StatisticsService(userStorage, mailStorage);
    }

    private static IUserStorage getUserStorage() {
        return StorageFactory.getUserStorage();
    }

    private static IMailStorage getMailStorage() {
        return StorageFactory.getMailStorage();
    }

    private static UserValidator createUserValidator() {
        IUserStorage userStorage = getUserStorage();
        return new UserValidator(userStorage);
    }

    private ServiceFactory() {
    }
}
