<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: gar24
  Date: 15.10.2023
  Time: 23:25
  To change this template use File | Settings | File Templates.
--%>
<%
    String message = pageContext.getException().getMessage();
    String exception = pageContext.getException().getClass().toString();
%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<html>
<head>
    <title>Error!</title>
</head>
<body>
<h2>Exception occurred while processing the request</h2>
<p>Type: <%= exception%></p>
<p>Message: <%= message %></p>

<form action='<c:url value="/office/ListOffices" />' style="display:inline;">
    <input type="submit" value="Back">
</form>
</body>
</html>
