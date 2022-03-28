<%@ page import="java.io.PrintWriter" %>
<%@ page import="com.bo.Message" %><%--
  Created by IntelliJ IDEA.
  User: walid
  Date: 3/9/2022
  Time: 5:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  Message message = (Message) request.getAttribute("message");
  if(message != null)
  {
    PrintWriter outi = response.getWriter();
    outi.println("<div class=\"alert alert-"+message.getTypeString()+"\" role=\"alert\">\n" +
            message.getText() +
            "</div>");
  }
%>

<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport"
        content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">
    <title>GameApp</title>
  <link href="${pageContext.request.contextPath}/style/bootstrap.min.css"
        rel="stylesheet">
</head>
<body>
<form action="${pageContext.request.contextPath}/LoginServlet" method="post">
  <div class="form-group">
    <label for="exampleInputEmail1">Login</label>
    <input name="login" type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter Login">
  </div>
  <div class="form-group">
    <label for="exampleInputPassword1">Password</label>
    <input name="password" type="password" class="form-control" id="exampleInputPassword1" placeholder="Password">
  </div>

  <button type="submit" class="btn btn-primary">Login</button> <br><br>
  <div class="form-group">
    <a href="${pageContext.request.contextPath}/RegistrationServlet" class="badge badge-secondary">create account</a>
  </div>


</form>

</body>
</html>
