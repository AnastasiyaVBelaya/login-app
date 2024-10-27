<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Вход</title>
    <style>
        .error {
            color: #CC0000;
            margin-top: 5px;
        }
    </style>
    <script>
        function validateForm(event) {
            const errorMessages = document.querySelectorAll('.error');
            errorMessages.forEach(msg => msg.textContent = '');

            let valid = true;

            const login = document.getElementById('login');
            const password = document.getElementById('password');

            if (!login.value) {
                document.getElementById('loginError').textContent = 'Необходимо указать логин';
                valid = false;
            }

            if (!password.value) {
                document.getElementById('passwordError').textContent = 'Необходимо указать пароль';
                valid = false;
            }

            if (!valid) {
                event.preventDefault();
            }
        }
    </script>
</head>
<body>
    <h2>Введите данные для входа</h2>
    <form action="${pageContext.request.contextPath}/api/login" method="post" onsubmit="validateForm(event)">
        <label for="login">Логин:</label>
        <input type="text" id="login" name="login">
        <div class="error" id="loginError"></div>
        <br>

        <label for="password">Пароль:</label>
        <input type="password" id="password" name="password">
        <div class="error" id="passwordError"></div>
        <br>

        <input type="submit" value="Войти">
    </form>
</body>
</html>
