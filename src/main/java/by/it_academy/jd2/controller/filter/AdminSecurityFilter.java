package by.it_academy.jd2.controller.filter;

import by.it_academy.jd2.dto.UserSessionDTO;
import by.it_academy.jd2.model.ERole;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(urlPatterns = {"/ui/admin/*", "/api/admin/*"})
public class AdminSecurityFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String contextPath = req.getContextPath();
        HttpSession session = req.getSession(false);

        if (session != null) {
            UserSessionDTO userSession = (UserSessionDTO) session.getAttribute("user");

            if (userSession != null && ERole.ADMIN.equals(userSession.getRole())) {
                chain.doFilter(request, response);
                return;
            }
        }

        resp.sendRedirect(contextPath + "/");
    }
}

