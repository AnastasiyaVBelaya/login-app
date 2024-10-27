<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="by.it_academy.jd2.controller.listener.StatisticsListener" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Статистика</title>
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
    <h2>Собранная статистика</h2>
    <table>
        <thead>
            <tr>
                <th>Параметр</th>
                <th>Значение</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td>Активные пользователи</td>
                <td><%= StatisticsListener.getActiveUserCount() %></td>
            </tr>
            <tr>
                <td>Всего пользователей</td>
                <td><%= StatisticsListener.getTotalUsersCount() %></td>
            </tr>
            <tr>
                <td>Всего сообщений</td>
                <td><%= StatisticsListener.getTotalMessagesCount() %></td>
            </tr>
        </tbody>
    </table>

    <div>
        <form action="<%= request.getContextPath() %>/ui/hello.jsp" method="get">
            <button type="submit">Вернуться назад</button>
        </form>
    </div>
</body>
</html>
