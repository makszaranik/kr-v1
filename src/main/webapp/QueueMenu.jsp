<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<%@ include file="Header.jsp" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>All Queues</title>
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
      form {
        text-align: center;
        margin-top: 20px;
      }
      select {
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
        margin-right: 10px;
      }
      input[type="submit"]:hover {
        background-color: #0056b3;
      }
    </style>
</head>
<body>
<h1>${tableName}</h1>
<c:if test="${empty queues}">
    <p>No queues available.</p>
</c:if>
<form action="viewSelectedQueue" method="get">
    <select name="selectedQueue">
        <c:forEach var="queue" items="${queues}">
            <option value="${queue.name}">${queue.name}</option>
        </c:forEach>
    </select>
    <input type="submit" name="action" value="View">
</form>
</body>
</html>
