<%@ include file="Header.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>View Queues</title>
    <style>
      body {
        font-family: Arial, sans-serif;
        background-color: #f4f4f4;
        margin: 0;
        padding: 0;
      }

      h1 {
        text-align: center;
        margin-top: 50px;
        color: #333;
      }

      ul {
        list-style-type: none;
        padding: 0;
        margin: 20px auto;
        width: fit-content;
        text-align: center;
      }

      li {
        margin-bottom: 10px;
      }

      a {
        display: inline-block;
        padding: 10px 20px;
        color: #fff;
        text-decoration: none;
        border-radius: 4px;
        transition: background-color 0.3s ease;
      }

      a:hover {
        background-color: #555;
      }

      .blue-background {
        background-color: #007bff;
      }

      .blue-background:hover {
        background-color: #0056b3;
      }
    </style>

</head>
<body>
<h1>View Queues</h1>

<ul>
    <li><a href="myQueues" class="blue-background">My Queues</a></li>
    <li><a href="allQueues" class="blue-background">All Queues</a></li>
</ul>

</body>
</html>
