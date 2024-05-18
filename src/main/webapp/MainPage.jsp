<%@ include file="Header.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Main Page</title>
    <style>
      body {
        font-family: Arial, sans-serif;
        background-color: #f4f4f4;
        margin: 0;
        padding: 0;
      }

      .header {
        background-color: #333;
        color: #fff;
        padding: 10px;
        text-align: right;
      }

      h2 {
        text-align: center;
        margin-top: 50px;
        color: #333;
      }

      ul {
        list-style-type: none;
        padding: 0;
        margin: 0;
        text-align: center;
      }

      li {
        display: inline;
        margin-right: 20px;
      }


      a {
        text-decoration: none;
        color: #007bff;
      }

      a:hover {
        text-decoration: underline;
      }
    </style>
</head>
<body>
<h2>Main Page</h2>
<ul>
    <li><a href="createQueue">Create queue</a></li>
    <li><a href="viewQueues">View Queues</a></li>
    <li><a href="updateQueue">Update Queues</a></li>
    <li><a href="addMeInQueue">Add me in queue</a></li>
</ul>
</body>
</html>




