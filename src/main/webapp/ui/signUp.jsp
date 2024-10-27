<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Регистрация пользователя</title>
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
            const fio = document.getElementById('fio');
            const dateOfBirth = document.getElementById('dateOfBirth');

            if (!login.value) {
                document.getElementById('loginError').textContent = 'Необходимо указать логин';
                valid = false;
            }

            if (!password.value) {
                document.getElementById('passwordError').textContent = 'Необходимо указать пароль';
                valid = false;
            }

            if (!fio.value) {
                document.getElementById('fioError').textContent = 'Необходимо указать ФИО';
                valid = false;
            }

            if (!dateOfBirth.value) {
                document.getElementById('dateOfBirthError').textContent = 'Необходимо указать дату рождения';
                valid = false;
            }

            if (!valid) {
                event.preventDefault();
            }
        }
    </script>
</head>
<body>
    <h2>Введите данные для регистрации</h2>
    <form action="${pageContext.request.contextPath}/api/user" method="post" onsubmit="validateForm(event)">
        <label for="login">Логин:</label>
        <input type="text" id="login" name="login">
        <div class="error" id="loginError"></div>
        <br>

        <label for="password">Пароль:</label>
        <input type="password" id="password" name="password">
        <div class="error" id="passwordError"></div>
        <br>

        <label for="fio">ФИО:</label>
        <input type="text" id="fio" name="fio">
        <div class="error" id="fioError"></div>
        <br>

        <label for="dateOfBirth">Дата рождения:</label>
        <input type="date" id="dateOfBirth" name="dateOfBirth">
        <div class="error" id="dateOfBirthError"></div>
        <br>

        <input type="submit" value="Зарегистрироваться">
    </form>
</body>
</html>
