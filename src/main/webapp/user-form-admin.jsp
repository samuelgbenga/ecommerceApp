<%--
  Created by IntelliJ IDEA.
  User: apple
  Date: 12/06/2024
  Time: 12:01 am
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Signup</title>
    <link rel="stylesheet" href="css/new-form-style.css">
</head>
<body>
<div class="container">
    <form class="form" action="admin-signup" method="post">
        <h3>Admin Signup</h3>
        <div class="form-group">
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" required>
        </div>
        <div class="form-group">
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>
        </div>
        <div class="form-group">
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required>
        </div>
        <div class="form-group">
            <button type="submit" value="Register">Submit</button>
        </div>
    </form>
</div>

</body>
</html>
