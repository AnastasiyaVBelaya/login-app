package by.it_academy.jd2.controller.servlet;

import by.it_academy.jd2.dto.UserDTO;
import by.it_academy.jd2.dto.UserSessionDTO;
import by.it_academy.jd2.service.api.IUserService;
import by.it_academy.jd2.service.factory.ServiceFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@WebServlet(urlPatterns = "/api/user")
public class ApiUser extends HttpServlet {

    private final static IUserService userService = ServiceFactory.getUserService();
    private static final String SIGN_UP_JSP = "/ui/signUp.jsp";
    private static final String HELLO_URL = "/api/hello";

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(SIGN_UP_JSP).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String fio = req.getParameter("fio");
        String dateOfBirth = req.getParameter("dateOfBirth");

        UserDTO userDTO  = UserDTO.builder()
                .login(login)
                .password(password)
                .fio(fio)
                .dateOfBirth(LocalDate.parse(dateOfBirth, formatter))
                .build();

        UserSessionDTO userSessionDTO = userService.create(userDTO);

        HttpSession session = req.getSession();
        session.setAttribute("user", userSessionDTO);

        resp.setStatus(HttpServletResponse.SC_CREATED);

        resp.sendRedirect(req.getContextPath() + HELLO_URL);
    }
}
