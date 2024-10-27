<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Ошибка</title>
</head>
<body>
    <h1>Произошла ошибка</h1>
    <p><%= request.getAttribute("errorMessage") %></p> <!-- Отображение сообщения об ошибке -->
    <form action="<%= request.getContextPath() %>/" method="get" style="margin-top: 10px;">
        <button type="submit">Вернуться на главную</button>
    </form>
</body>
</html>
