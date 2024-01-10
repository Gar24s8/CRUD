<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Список сотрудников оффиса</title>
</head>
<body>
<h2>Список сотрудников выбранного офиса</h2>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Имя</th>
        <th>Должность</th>
        <th>Зарплата</th>
        <th>Действия</th>
    </tr>

    <c:forEach var="employees" items="${employees}">
        <tr>
            <td>${employees.id}</td>
            <td>${employees.name}</td>
            <td>${employees.position}</td>
            <td>${employees.salary}</td>
            <td>
                <form method="post" action='<c:url value="/employee/edit?id=${employees.id}" />'
                      style="display:inline;">
                    <input type="hidden" name="id" value="${employees.id}">
                    <input type="submit" value="Edit"> |
                </form>
                <form method="post" action='<c:url value="/employee/delete" />' style="display:inline;">
                    <input type="hidden" name="id" value="${employees.id}">
                    <input type="submit" value="Delete"> |
                </form>
                <form method="post" action='<c:url value="/employee/deleteFromOffice" />' style="display:inline;">
                    <input type="hidden" name="id" value="${employees.id}">
                    <input type="hidden" name="officeId" value="${employees.office.id}">
                    <input type="submit" value="Delete from office">
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
