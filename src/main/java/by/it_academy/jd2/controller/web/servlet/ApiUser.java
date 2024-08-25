package by.it_academy.jd2.controller.web.servlet;

import by.it_academy.jd2.dto.UserDTO;
import by.it_academy.jd2.model.ERole;
import by.it_academy.jd2.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

@WebServlet(urlPatterns = "/api/user")
public class ApiUser extends HttpServlet {

    private final static UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String regForm = "<!DOCTYPE html>" +
                "<html lang=\"ru\">" +
                "<head>" +
                "    <meta charset=\"UTF-8\">" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">" +
                "    <title>Регистрация пользователя</title>" +
                "</head>" +
                "<body>" +
                "    <h1>Регистрация пользователя</h1>" +
                "    <form action=\"/login-app-1.0-SNAPSHOT/api/user\" method=\"post\">" +
                "        <label for=\"login\">Логин:</label>" +
                "        <input type=\"text\" id=\"login\" name=\"login\" required><br><br>" +
                "        <label for=\"password\">Пароль:</label>" +
                "        <input type=\"password\" id=\"password\" name=\"password\" required><br><br>" +
                "        <label for=\"fio\">ФИО:</label>" +
                "        <input type=\"text\" id=\"fio\" name=\"fio\" required><br><br>" +
                "        <label for=\"dateOfBirth\">Дата рождения:</label>" +
                "        <input type=\"date\" id=\"dateOfBirth\" name=\"dateOfBirth\" required><br><br>" +
                "        <input type=\"submit\" value=\"Зарегистрироваться\">" +
                "    </form>" +
                "</body>" +
                "</html>";

        try (PrintWriter out = resp.getWriter()) {
            out.write(regForm);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String fio = req.getParameter("fio");
        LocalDate dateOfBirth = LocalDate.parse(req.getParameter("dateOfBirth"));

        try {
            UserDTO user = new UserDTO(login, password, fio, dateOfBirth, LocalDate.now(), ERole.USER);
            userService.create(user);

            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().write("User registered successfully.");
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("Error: " + e.getMessage());
        }
    }
}
