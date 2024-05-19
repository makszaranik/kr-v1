<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>queue Successfully Blocked</title>
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
        background-color: #ff9999;
        border-radius: 8px;
        box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        padding: 20px;
        text-align: center;
      }

      h1 {
        color: #333;
        margin-bottom: 20px;
      }

      p {
        color: #666;
        margin-bottom: 20px;
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
    <h1>Error</h1>
    <p><%= request.getAttribute("errorMessage") %></p>
    <div class="link">
        <a href="MainPage.jsp">Back to Main</a>
    </div>
</div>
</body>
</html>
