<%--
  Created by IntelliJ IDEA.
  User: gar24
  Date: 11.10.2023
  Time: 17:49
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="post" action="/save">
    <input type="text" name="id" readonly="readonly" value="<c:out value="${employee.id}"/>">
    <input type="text" name="name" value="<c:out value="${employee.name}"/>">
    <input type="text" name="position" value="<c:out value="${employee.position}"/>">
    <input type="text" name="salary" value="<c:out value="${employee.salary}"/>">
    <input type="submit" value="ok" name="update">
</form>
</body>
</html>
