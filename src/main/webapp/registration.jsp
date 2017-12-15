<%--
  Created by IntelliJ IDEA.
  User: mihail
  Date: 16.04.17
  Time: 22:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Регистрация</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link href="./css/index.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Underdog" rel="stylesheet" type="text/css">
</head>
<body>
<div class="infoMessage">
    ${message}
    ${goodMessage}
</div>
<form class="loginForm" action="/registration" method="post">
    Имя нового жителя: <input type="text" value="${user.name}" name="name"><br>
    Пароль: <input type="password" value="${user.password}" name="password"><br>
    e-mail: <input type="text" value="${user.email}" name="email"><br>
    <input type="submit" style="color: chocolate" value="Создать">

    <footer class="coText">
        <div><a href="/">tirety 2017</a></div>
    </footer>
</form>
</body>
</html>
