package by.it_academy.jd2.storage.factory;

import by.it_academy.jd2.storage.api.IUserStorage;
import by.it_academy.jd2.storage.api.IMailStorage;
import by.it_academy.jd2.storage.hibernate.HibernateManager;
import by.it_academy.jd2.storage.hibernate.UserStorage;
import by.it_academy.jd2.storage.hibernate.MailStorage;

public class StorageFactory {

    private static final HibernateManager hibernateManager = new HibernateManager();
    private static final IUserStorage userStorageHibernate = new UserStorage(hibernateManager);
    private static final IMailStorage mailStorageHibernate = new MailStorage(hibernateManager);

    public static IUserStorage getUserStorage() {
        return userStorageHibernate;
    }

    public static IMailStorage getMailStorage() {
        return mailStorageHibernate;
    }

    public static void close() throws Exception {
        hibernateManager.close();
    }
}
