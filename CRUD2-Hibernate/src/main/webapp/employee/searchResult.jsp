<%--
  Created by IntelliJ IDEA.
  User: gar24
  Date: 18.01.2024
  Time: 15:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>Результаты поиска</h2>
<table border="1">
    <tr>
        <th>Имя</th>
        <th>Должность</th>
        <th>Зарплата</th>
        <th>Действия</th>
    </tr>

    <c:forEach var="searchResult" items="${searchResult}">
    <tr>
        <td>${searchResult.name}</td>
        <td>${searchResult.position}</td>
        <td>${searchResult.salary}</td>
        <td>
            <form method="get" action='<c:url value="/employee/edit?id=${searchResult.id}" />'
                  style="display:inline;">
                <input type="hidden" name="id" value="${searchResult.id}">
                <input type="submit" value="Edit"> |
            </form>
            <form method="post" action='<c:url value="/employee/delete" />' style="display:inline;">
                <input type="hidden" name="id" value="${searchResult.id}">
                <input type="submit" value="Delete"> |
            </form>
        </td>
    </tr>
    </c:forEach>
</body>
</html>
