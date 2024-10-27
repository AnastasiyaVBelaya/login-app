package by.it_academy.jd2.controller.filter;

import by.it_academy.jd2.exceptions.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class TryCatchFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain)
            throws IOException, ServletException {

        try {
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (UserNotFoundException e) {
            handleException((HttpServletRequest) servletRequest, (HttpServletResponse) servletResponse,
                    HttpServletResponse.SC_NOT_FOUND, "Пользователь не найден: " + e.getMessage());
        } catch (UserAlreadyExistsException e) {
            handleException((HttpServletRequest) servletRequest, (HttpServletResponse) servletResponse,
                    HttpServletResponse.SC_CONFLICT, "Пользователь уже существует: " + e.getMessage());
        } catch (InvalidUserException e) {
            handleException((HttpServletRequest) servletRequest, (HttpServletResponse) servletResponse,
                    HttpServletResponse.SC_BAD_REQUEST, "Некорректные данные пользователя: " + e.getMessage());
        } catch (InvalidCredentialsException e) {
            handleException((HttpServletRequest) servletRequest, (HttpServletResponse) servletResponse,
                    HttpServletResponse.SC_UNAUTHORIZED, "Некорректные учетные данные: " + e.getMessage());
        } catch (MessageSendException e) {
            handleException((HttpServletRequest) servletRequest, (HttpServletResponse) servletResponse,
                    HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Ошибка отправки сообщения: " + e.getMessage());
        } catch (DataAccessException e) {
            handleException((HttpServletRequest) servletRequest, (HttpServletResponse) servletResponse,
                    HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Ошибка доступа к данным: " + e.getMessage());
        } catch (IOException e) {
            handleException((HttpServletRequest) servletRequest, (HttpServletResponse) servletResponse,
                    HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Ошибка ввода-вывода: " + e.getMessage());
        } catch (ServletException e) {
            handleException((HttpServletRequest) servletRequest, (HttpServletResponse) servletResponse,
                    HttpServletResponse.SC_BAD_REQUEST, "Ошибка сервлета: " + e.getMessage());
        } catch (Exception e) {
            handleException((HttpServletRequest) servletRequest, (HttpServletResponse) servletResponse,
                    HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Внутренняя ошибка сервера: " + e.getMessage());
        }
    }

    private void handleException(HttpServletRequest request, HttpServletResponse response, int statusCode, String message)
            throws IOException, ServletException {
        request.setAttribute("errorMessage", message);

        response.setStatus(statusCode);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/ui/errorPage.jsp");
        dispatcher.forward(request, response);
    }
}
