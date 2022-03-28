<%@ page import="com.bo.User" %>
<%@ page import="java.util.List" %>
<%@ page import="java.io.PrintWriter" %><%--
  Created by IntelliJ IDEA.
  User: walid
  Date: 3/14/2022
  Time: 9:10 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Best score</title>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>User home</title>
    <link href="${pageContext.request.contextPath}/style/bootstrap.min.css"
          rel="stylesheet">

</head>
<body>

        <%
            List<User> users = (List<User>) request.getAttribute("users");
            PrintWriter outi = response.getWriter();
            outi.print("<table class=\"table\"><tr><td>Nom</td><td>Best Score</td></tr>");
            for (User user : users) {
                outi.print("<tr><td>" + user.getFirstName() + " " + user.getLastName() + "</td><td>" + user.getBestScore() + "</td></tr>");
            }

            outi.print("</table>");
        %>
</body>
</html>
