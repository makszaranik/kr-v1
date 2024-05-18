<%@ page import="kpi.fict.coursework.op.zaranik.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Header</title>
    <style>
      .header {
        background-color: #333;
        color: #fff;
        padding: 10px;
        text-align: right;
      }

      .header span {
        margin-right: 10px;
      }

      .header a {
        text-decoration: none;
        color: #fff;
        padding: 5px 10px;
        border-radius: 5px;
        transition: background-color 0.3s ease;
        margin-right: 10px;
      }

      .header a:hover {
        background-color: #555;
      }
    </style>
</head>
<body>

<%
    HttpSession httpSession = (HttpSession) request.getSession(false);
    User user = null;
    if (httpSession != null) {
        user = (User) httpSession.getAttribute("user");
    }
%>
<div class="header">
    <a href="MainPage.jsp">Main Page</a>
    <% if (user != null) { %>
    <span>Welcome, <%= session.getAttribute("username") %>!</span>
    <% } %>
    <a href="logout">Logout</a>
</div>

</body>
</html>