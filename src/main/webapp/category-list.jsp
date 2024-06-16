<%--
  Created by IntelliJ IDEA.
  User: apple
  Date: 11/06/2024
  Time: 2:25 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="main.ecommerce.app.model.Category" %>
<%@ page import="java.util.List" %>
<%List<Category> categories = (List<Category>) request.getAttribute("categories");%>
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
        for (Category u : categories) {

    %>
    <tr>
        <td><%=u.getId()%></td>
        <td><%=u.getDescription()%></td>
        <td><%=u.getName()%></td>
    </tr>
    <%}%>
</table>


<div></div>


</body>
</html>
