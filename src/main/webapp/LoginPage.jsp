<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <style>
      body {
        font-family: Arial, sans-serif;
        background-color: #f4f4f4;
        margin: 0;
        padding: 0;
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
      }

      .container {
        background-color: #fff;
        border-radius: 8px;
        box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        padding: 20px;
        text-align: center;
      }

      h2 {
        color: #333;
        margin-bottom: 20px;
      }

      form {
        margin-bottom: 20px;
      }

      input[type="text"],
      input[type="password"] {
        padding: 8px;
        margin-bottom: 10px;
        border: 1px solid #ccc;
        border-radius: 4px;
        width: 100%;
        box-sizing: border-box;
      }

      input[type="submit"] {
        background-color: #007bff;
        color: #fff;
        padding: 10px 20px;
        border: none;
        border-radius: 4px;
        cursor: pointer;
      }

      input[type="submit"]:hover {
        background-color: #0056b3;
      }

      p {
        color: #666;
        margin-bottom: 10px;
      }

      a {
        color: #007bff;
        text-decoration: none;
      }

      a:hover {
        text-decoration: underline;
      }
    </style>
</head>
<body>
<div class="container">
    <h2>Login</h2>
    <form action="login" method="post">
        Username: <input type="text" name="username" value="Max"><br>
        Password: <input type="password" name="password" value="max123"><br>
        <input type="submit" value="Login">
    </form>
    <p>Don't have an account? <a href="RegisterPage.jsp">Register</a></p>
</div>
</body>
</html>
