<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="by.it_academy.jd2.dto.UserSessionDTO" %>
<%@ page import="by.it_academy.jd2.model.ERole" %>

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Страница приветствия</title>
</head>
 <style>
        button {
            margin: 10px 0;
        }
    </style>
<body>
    <h2>Здравствуйте, <%= ((UserSessionDTO) session.getAttribute("user")) != null ? ((UserSessionDTO) session.getAttribute("user")).getLogin() : "Гость" %></h2>
    <h2>Выберите действие:</h2>
    <div>
        <form action="<%= request.getContextPath() %>/api/message" method="get">
            <button type="submit">Посмотреть свои сообщения</button>
        </form>

        <form action="<%= request.getContextPath() %>/ui/user/message.jsp" method="get">
            <button type="submit">Отправить сообщения</button>
        </form>
        <%
                   UserSessionDTO userSession = (UserSessionDTO) session.getAttribute("user");
                   if (userSession != null && ERole.ADMIN.equals(userSession.getRole())) {
               %>
                   <form action="<%= request.getContextPath() %>/api/admin/statistics" method="get">
                       <button type="submit">Посмотреть статистику</button>
                   </form>
               <%
                   }
               %>

        <form action="<%= request.getContextPath() %>/api/logout" method="post">
            <button type="submit">Выйти</button>
        </form>
    </div>
</body>
</html>
