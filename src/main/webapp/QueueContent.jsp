<%@ include file="Header.jsp" %>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>View Selected Queue</title>
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

      p {
        text-align: center;
        color: #666;
        margin-top: 10px;
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
        background-color: #fff;
        padding: 10px;
        border-radius: 4px;
        box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
      }
    </style>
</head>
<body>

<c:if test="${selectedQueue ne null}">
    <h1>Queue Name: ${selectedQueue.name}</h1>
    <p>Created by: ${selectedQueue.creator.username}</p>
    <p>Your position in the queue: ${userPosition}</p>
    <ul>
        <c:forEach var="item" items="${selectedQueue.items}">
            <li>${item}</li>
        </c:forEach>
    </ul>
</c:if>

</body>
</html>
