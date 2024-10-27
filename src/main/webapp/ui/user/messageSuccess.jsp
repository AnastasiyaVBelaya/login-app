<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Сообщение отправлено</title>
</head>
 <style>
        button {
            margin: 10px 0;
        }
    </style>
<body>
    <h2>Ваше сообщение отправлено<h2>
    <div>
            <form action="<%= request.getContextPath() %>/ui/user/message.jsp" method="get">
                <button type="submit">Отправить новое сообщение</button>
            </form>

            <form action="<%= request.getContextPath() %>/api/message" method="get">
                <button type="submit">Посмотреть свои сообщения</button>
            </form>
            <form action="<%= request.getContextPath() %>/api/logout" method="post">
                <button type="submit">Выйти</button>
            </form>
        </div>
</body>
</html>
