<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Список офисов</title>
</head>
<body>
<h2>Список офисов</h2>
<table border="2">
    <tr>
        <th>Название</th>
        <th>Адрес</th>
    </tr>

    <c:forEach var="office" items="${offices}">
        <tr>
            <td><a href="/employee/listEmployees?officeID=${office.id}">${office.officeName}</a></td>
            <td>${office.address}</td>
        </tr>
    </c:forEach>
</table>
<h2>Список всех сотрудников</h2>
<p><a href='<c:url value="/employee/create" />'>Новый сотрудник</a></p>

<form action="/employee/search" method="GET">
    <input type="text" name="name" placeholder="Введите имя сотрудника">
    <button type="submit">Search</button>
</form>

<table border="2">
    <tr>
        <th>Имя</th>
        <th>Должность</th>
        <th>Зарплата</th>
        <th>Действия</th>
    </tr>

    <c:forEach var="employees" items="${employees}">
        <tr>
            <td>${employees.name}</td>
            <td>${employees.position}</td>
            <td>${employees.salary}</td>
            <td>
                <form method="get" action='<c:url value="/employee/edit?id=${employees.id}" />'
                      style="display:inline;">
                    <input type="hidden" name="id" value="${employees.id}">
                    <input type="submit" value="Edit"> |
                </form>
                <form method="post" action='<c:url value="/employee/delete" />' style="display:inline;">
                    <input type="hidden" name="id" value="${employees.id}">
                    <input type="submit" value="Delete">
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>

