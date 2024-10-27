package by.it_academy.jd2.controller.servlet;

import by.it_academy.jd2.dto.AuthenticationDTO;
import by.it_academy.jd2.dto.UserSessionDTO;
import by.it_academy.jd2.service.api.ILoginService;
import by.it_academy.jd2.service.factory.ServiceFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


import java.io.IOException;
@WebServlet(urlPatterns = "/api/login")
public class ApiLogin extends HttpServlet {

    private final static ILoginService loginService = ServiceFactory.getLoginService();
    private static final String SIGN_IN_JSP = "/ui/signIn.jsp";
    private static final String HELLO_URL = "/api/hello";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(SIGN_IN_JSP).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        AuthenticationDTO authDTO = new AuthenticationDTO(login, password);

        UserSessionDTO userSessionDTO = loginService.authenticate(authDTO);

        HttpSession session = req.getSession();
        session.setAttribute("user", userSessionDTO);

        resp.setStatus(HttpServletResponse.SC_OK);

        resp.sendRedirect(req.getContextPath() + HELLO_URL);
    }
}
