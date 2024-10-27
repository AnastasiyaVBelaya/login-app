package by.it_academy.jd2.controller.servlet;

import by.it_academy.jd2.dto.MailForGettingDTO;
import by.it_academy.jd2.dto.UserSessionDTO;
import by.it_academy.jd2.service.api.IMailService;
import by.it_academy.jd2.dto.MailForSendingDTO;
import by.it_academy.jd2.service.factory.ServiceFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/api/message")
public class ApiMessage extends HttpServlet {

    private final static String TO_PARAM_NAME = "to";
    private final static String TEXT_PARAM_NAME = "text";
    private static final String CHATS_JSP = "/ui/user/chats.jsp";
    private static final String MESSAGE_SUCCESS_JSP = "/ui/user/messageSuccess.jsp";

    private final IMailService mailService = ServiceFactory.getMailService();


    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession();
        UserSessionDTO currentUser = (UserSessionDTO) session.getAttribute("user");

        List<MailForGettingDTO> messages = mailService.getByLogin(currentUser.getLogin());

        req.setAttribute("messages", messages);

        req.getRequestDispatcher(CHATS_JSP).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req,
                          HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession();
        UserSessionDTO currentUser = (UserSessionDTO) session.getAttribute("user");

        if (currentUser == null) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN, "Пользователь не авторизован");
            return;
        }

        mailService.create(MailForSendingDTO.builder()
                .from(currentUser.getLogin())
                .to(req.getParameter(TO_PARAM_NAME))
                .text(req.getParameter(TEXT_PARAM_NAME))
                .build());

        resp.sendRedirect(req.getContextPath() + MESSAGE_SUCCESS_JSP);
    }
}
