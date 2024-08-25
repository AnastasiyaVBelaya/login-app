package by.it_academy.jd2.controller.web.servlet;

import by.it_academy.jd2.dto.UserDTO;
import by.it_academy.jd2.model.ERole;
import by.it_academy.jd2.service.LoginService;
import by.it_academy.jd2.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.time.LocalDate;

@WebServlet(urlPatterns = "/api/login")
public class ApiLogin extends HttpServlet {
    private final static LoginService loginService = LoginService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String regForm = "<!DOCTYPE html>" +
                "<html lang=\"ru\">" +
                "<head>" +
                "    <meta charset=\"UTF-8\">" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">" +
                "    <title>Вход</title>" +
                "</head>" +
                "<body>" +
                "    <h1>Вход</h1>" +
                "    <form action=\"/login-app-1.0-SNAPSHOT/api/login\" method=\"post\">" +
                "        <label for=\"login\">Логин:</label>" +
                "        <input type=\"text\" id=\"login\" name=\"login\" required><br><br>" +
                "        <label for=\"password\">Пароль:</label>" +
                "        <input type=\"password\" id=\"password\" name=\"password\" required><br><br>" +
                "        <input type=\"submit\" value=\"Войти\">" +
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
        try {

            UserDTO user = loginService.login(login, password);
            HttpSession session = req.getSession();
            session.setAttribute("user", user);

            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().write("You logged in");

        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("Error: " + e.getMessage());
        }
    }
}


