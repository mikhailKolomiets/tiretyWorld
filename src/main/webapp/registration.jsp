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
</head>
<body>
<form action="/registration">
  Имя нового жителя: <input type="text" name="name"><br>
  Пароль: <input type="password" name="password"><br>
  e-mail: <input type="text" name="email"><br>
  <input type="submit" value="Создать">


</form>
</body>
</html>
