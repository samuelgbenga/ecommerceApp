<%--
  Created by IntelliJ IDEA.
  User: apple
  Date: 11/06/2024
  Time: 6:22 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="css/login-style.css">
</head>
<body>
<div class="container">
    <form action="login" method="post">
        <div class="form-group">
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" required>
        </div>
        <div class="form-group">
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>
        </div>
        <div class="form-group">
            <button type="submit">Login</button>
        </div>
        <div class="a" onclick="redirectToServlet('loginAdmin')"> are you an admin? Login here.</div>
    </form>

</div>

</body>
<script>
    function redirectToServlet(action) {
        window.location.href = 'users?action=' + action;
    }
</script>
</html>

