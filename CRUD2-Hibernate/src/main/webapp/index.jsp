<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
  Created by IntelliJ IDEA.
  User: gar24
  Date: 15.10.2023
  Time: 22:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Employees</title>
</head>
<body>
<h2>Employee List</h2>
<p><a href='<c:url value="/employee/create" />'>Create new</a></p>
<%--<table>--%>
<%--    <tr>--%>
<%--        <th>Name</th>--%>
<%--        <th>Position</th>--%>
<%--        <th>Salary</th>--%>
<%--        <th>Office</th>--%>
<%--        <th>Address</th>--%>
<%--        <th></th>--%>
<%--    </tr>--%>

    <c:forEach var="office" items="${offices}">
        <div>Office: ${office.id}</div>
        <c:forEach var="employee" items="${employees}">
            <div style="padding-left:15px">Employee id: ${employee.office}</div>
        </c:forEach>
    </c:forEach>

<%--    <c:forEach var="employee" items="${employees}">--%>

<%--        <tr>--%>
<%--            <td>${employee.name}</td>--%>
<%--            <td>${employee.position}</td>--%>
<%--            <td>${employee.salary}</td>--%>
<%--            <td>${office.officeName}</td>--%>
<%--            <td>${office.address}</td>--%>
<%--            <td>--%>
<%--                <a href='<c:url value="/employee/edit?id=${employee.id}" />'>Edit</a> |--%>
<%--                <form method="post" action='<c:url value="/employee/delete" />' style="display:inline;">--%>
<%--                    <input type="hidden" name="id" value="${employee.id}">--%>
<%--                    <input type="submit" value="Delete">--%>
<%--                </form>--%>
<%--            </td>--%>
<%--        </tr>--%>
<%--    </c:forEach>--%>
</table>
</body>
</html>
