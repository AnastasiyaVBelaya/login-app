<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="by.it_academy.jd2.dto.MailForGettingDTO" %>
<%@ page import="by.it_academy.jd2.service.api.IMailService" %>
<%@ page import="by.it_academy.jd2.service.factory.ServiceFactory" %>
<%@ page import="by.it_academy.jd2.dto.UserSessionDTO" %>

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Чаты</title>
    <style>
        table {
            border-collapse: collapse;
            margin-top: 20px;
        }
        th, td {
            border: 2px solid #ddd;
            padding: 8px;
            text-align: left;
        }
    </style>
</head>
 <style>
        button {
            margin: 10px 0;
        }
    </style>
<body>
    <h2>Ваши сообщения</h2>

    <table>
        <thead>
            <tr>
                <th>Дата/время</th>
                <th>От кого</th>
                <th>Текст</th>
            </tr>
        </thead>
        <tbody>
            <%
                UserSessionDTO currentUser = (UserSessionDTO) session.getAttribute("user");
                String currentUserLogin = currentUser.getLogin();
                IMailService mailService = ServiceFactory.getMailService();

                List<MailForGettingDTO> messages = mailService.getByLogin(currentUserLogin);

                if (messages.isEmpty()) {
            %>
                <tr>
                    <td colspan="3">Нет сообщений</td>
                </tr>
            <%
                } else {
                    for (MailForGettingDTO message : messages) {
            %>
                <tr>
                    <td><%= message.getCreateAt() %></td>
                    <td><%= message.getFrom() %></td>
                    <td><%= message.getText() %></td>
                </tr>
            <%
                    }
                }
            %>
        </tbody>
    </table>

    <div>
            <form action="<%= request.getContextPath() %>/ui/hello.jsp" method="post">
                <button type="submit">Вернуться назад</button>
            </form>
        </div>
</body>
</html>
