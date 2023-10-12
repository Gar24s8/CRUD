<%--
  Created by IntelliJ IDEA.
  User: gar24
  Date: 11.10.2023
  Time: 17:47
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table>
    <tr>
        <td>name</td>
        <td>position</td>
        <td>salary</td>

    </tr>
    <c:forEach items="${list}" var="list">
        <tr>
            <th>${list.name}</th>
            <th>${list.position}</th>
            <th>${list.salary}</th>
            <th><a href="/save?action=update&id=<c:out value="${list.id}"/>">update</a> </th>
            <th><a href="/save?action=delete&id=${list.id}">delete</a> </th>
        </tr>
    </c:forEach>
</table>
</body>
</html>
