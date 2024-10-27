package by.it_academy.jd2.controller.listener;

import by.it_academy.jd2.service.StatisticsService;
import by.it_academy.jd2.service.factory.ServiceFactory;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import jakarta.servlet.http.HttpSessionAttributeListener;

import java.util.concurrent.atomic.AtomicInteger;

@WebListener
public class StatisticsListener implements HttpSessionListener, HttpSessionAttributeListener {
    private static final AtomicInteger activeUserCount = new AtomicInteger(0);
    private static final AtomicInteger totalUsersCount = new AtomicInteger(0);
    private static final AtomicInteger totalMessagesCount = new AtomicInteger(0);


    public StatisticsListener() {
        StatisticsService statisticsService = ServiceFactory.getStatisticsService();
        totalUsersCount.set(statisticsService.getTotalUsersCount());
        totalMessagesCount.set(statisticsService.getTotalMessagesCount());
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        activeUserCount.incrementAndGet();
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        activeUserCount.decrementAndGet();
    }

    public static int getActiveUserCount() {
        return activeUserCount.get();
    }

    public static int getTotalUsersCount() {
        return totalUsersCount.get();
    }

    public static int getTotalMessagesCount() {
        return totalMessagesCount.get();
    }

    public static void incrementUserCount() {
        totalUsersCount.incrementAndGet();
    }

    public static void incrementMessageCount() {
        totalMessagesCount.incrementAndGet();
    }
}
