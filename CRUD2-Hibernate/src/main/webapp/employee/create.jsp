<%@ page import="models.Office" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: gar24
  Date: 15.10.2023
  Time: 22:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Create Employee</title>
</head>
<body>
<h1>Новый сотрудник</h1>
<form method="post">
    <label>Имя:</label><br>
    <input name="name" required placeholder="Input name"/><br><br>
    <label>Должность:</label><br>
    <input name="position" required placeholder="Input position"/><br><br>
    <label>Зарплата:</label><br>
    <input name="salary" type="number" required placeholder="Input salary"/><br><br>
    <label>Офис:</label><br>
    <select name="officeName" required>
        <option value="">Выберите офис</option>
        <c:forEach items="${offices}" var="office">
            <option value="${office.officeName}">${office.officeName}</option>
        </c:forEach>
    </select><br><br>


    <%--    <label>Офис:</label><br>--%>
    <%--    <input name="officeName" /><br><br>--%>
    <%--    <label>Адрес:</label><br>--%>
    <%--    <input name="address" /><br><br>--%>

    <input type="submit" value="Save"/>
</form>
</body>
</html>
