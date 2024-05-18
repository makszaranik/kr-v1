<%@ include file="Header.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create queue</title>
    <style>
      body {
        font-family: Arial, sans-serif;
        background-color: #f4f4f4;
        margin: 0;
        padding: 0;
      }

      h2 {
        text-align: center;
        margin-top: 50px;
        color: #333;
      }

      form {
        text-align: center;
      }

      input[type="text"] {
        width: 300px;
        padding: 8px;
        margin-bottom: 10px;
        border: 1px solid #ccc;
        border-radius: 4px;
        box-sizing: border-box;
      }

      input[type="submit"] {
        background-color: #007bff;
        color: #fff;
        padding: 10px 20px;
        border: none;
        border-radius: 4px;
        cursor: pointer;
        transition: background-color 0.3s ease;
      }

      input[type="submit"]:hover {
        background-color: #0056b3;
      }
    </style>
</head>
<body>
<h2>Create queue</h2>
<form action="createQueue" method="post">
    <label for="queueName">queue Name:</label><br>
    <input type="text" id="queueName" name="queueName"><br>
    <input type="submit" value="Create queue">
</form>
</body>
</html>
