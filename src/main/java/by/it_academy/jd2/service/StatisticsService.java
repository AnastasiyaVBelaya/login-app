package by.it_academy.jd2.service;

import by.it_academy.jd2.storage.api.IMailStorage;
import by.it_academy.jd2.storage.api.IUserStorage;
import by.it_academy.jd2.exceptions.DataAccessException;

public class StatisticsService {

    private final IUserStorage userStorage;
    private final IMailStorage mailStorage;

    public StatisticsService(IUserStorage userStorage, IMailStorage mailStorage) {
        this.userStorage = userStorage;
        this.mailStorage = mailStorage;
    }

    public int getTotalUsersCount() {
        try {
            return userStorage.getTotalUserCount();
        } catch (Exception e) {
            throw new DataAccessException("Ошибка доступа к данным пользователей: " + e.getMessage());
        }
    }

    public int getTotalMessagesCount() {
        try {
            return mailStorage.getTotalMessageCount();
        } catch (Exception e) {
            throw new DataAccessException("Ошибка доступа к данным сообщений: " + e.getMessage());
        }
    }
}
