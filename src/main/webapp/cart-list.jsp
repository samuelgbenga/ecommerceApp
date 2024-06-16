<%--
  Created by IntelliJ IDEA.
  User: apple
  Date: 11/06/2024
  Time: 3:00 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="main.ecommerce.app.model.Cart" %>
<%@ page import="java.util.List" %>
<%List<Cart> carts = (List<Cart>) request.getAttribute("carts");%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User List</title>
</head>
<body>
<h1>List of Users</h1>
<table border="1">
    <tr >
        <th>ID</th>
        <th>Username</th>
        <th>Email</th>
    </tr>
    <%
        for (Cart u : carts) {

    %>
    <tr>
        <td><%=u.getId()%></td>
        <td><%=u.getStatus()%></td>
        <td><%=u.getUser().getId()%></td>
    </tr>
    <%}%>
</table>


<div></div>


</body>
</html>
