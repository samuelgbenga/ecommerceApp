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
    <form class="form" action="register" method="post">
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
        <div class="a" onclick="redirectToServlet('newAdmin')"> you can sign up as an Admin here.</div>
    </form>
</div>

</body>
<script>
    function redirectToServlet(action) {
        window.location.href = 'users?action=' + action;
    }
</script>
</html>