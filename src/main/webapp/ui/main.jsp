<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Главная страница</title>
</head>
 <style>
        button {
            margin: 10px 0;
        }
    </style>
<body>
    <h2>Добро пожаловать на главную страницу</h2>
    <h2>Выберите действие:</h2>
    <div>
        <form action="<%= request.getContextPath() %>/api/user" method="get">
            <button type="submit">Зарегистрироваться</button>
        </form>

        <form action="<%= request.getContextPath() %>/api/login" method="get">
            <button type="submit">Войти</button>
        </form>
    </div>
</body>
</html>
