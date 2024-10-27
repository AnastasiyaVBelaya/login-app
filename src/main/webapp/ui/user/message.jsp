<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Отправка сообщения</title>
</head>
 <style>
        .error {
            color: #CC0000;
            margin-top: 5px;
        }
        button {
            margin: 10px 0;
        }
    </style>
    <script>
        function validateForm(event) {
            const errorMessages = document.querySelectorAll('.error');
            errorMessages.forEach(msg => msg.textContent = '');

            let valid = true;

            const to = document.getElementById('to');
            const text = document.getElementById('text');

            if (!to.value) {
                document.getElementById('toError').textContent = 'Необходимо указать получателя';
                valid = false;
            }

            if (!text.value) {
                document.getElementById('textError').textContent = 'Сообщение не должно быть пустым';
                valid = false;
            }

            if (!valid) {
                event.preventDefault();
            }
        }
    </script>
<body>
    <h2>Отправка сообщения</h2>
    <form action="${pageContext.request.contextPath}/api/message" method="post" onsubmit="validateForm(event)">
        <label for="to">Кому:</label>
        <input type="text" id="to" name="to">
        <div class="error" id="toError"></div>
        <br>

        <label for="text">Текст:</label>
        <textarea id="text" name="text"></textarea>
         <div class="error" id="textError"></div>
        <br>
        <input type="submit" value="Отправить">
    </form>
</body>
</html>
