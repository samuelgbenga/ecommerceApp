<%--
  Created by IntelliJ IDEA.
  User: apple
  Date: 11/06/2024
  Time: 11:54 am
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="main.ecommerce.app.model.User" %>
<%@ page import="java.util.List" %>
<%List<User> users = (List<User>) request.getAttribute("users");
%>
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
        for (User u : users) {

    %>
        <tr>
            <td><%=u.getId()%></td>
            <td><%=u.getUsername()%></td>
            <td><%=u.getEmail()%></td>
        </tr>
    <%}%>
</table>


<div></div>


</body>
</html>
