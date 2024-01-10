<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Employee</title>
</head>
<body>
<h1>Edit Employee</h1>
<form method="post">
    <input type="hidden" name="id" value="${employee.id}">
    <label>Имя:</label>
    <input type="text" name="name" value="${employee.name}" required><br><br>
    <label>Должность:</label>
    <input type="text" name="position" value="${employee.position}" required><br><br>
    <label>Зарплата:</label>
    <input type="number" name="salary" value="${employee.salary}" required><br><br>
    <label>Офис:</label>
    <select name="officeId" required>
        <option value="">Выберите офис</option>
        <c:forEach items="${offices}" var="office">
            <option value="${office.id}" ${office.id == employee.office.id ? 'selected' : ''}>${office.officeName}</option>
        </c:forEach>
    </select><br><br>
    <input type="submit" value="Save">
</form>
</body>
</html>







<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<html>--%>
<%--<head>--%>
<%--    <title>Edit Employee</title>--%>
<%--</head>--%>
<%--<body>--%>
<%--<h3>Edit employee</h3>--%>
<%--<form method="post">--%>
<%--    <input type="hidden" value="${employee.id}" name="id" />--%>
<%--    <label>Name</label><br>--%>
<%--    <input name="name" value="${employee.name}" required placeholder="Input name"/> <br><br>--%>
<%--    <label>Position</label><br>--%>
<%--    <input name="position" value="${employee.position}" required placeholder="Input position"/> <br><br>--%>
<%--    <label>Salary</label><br>--%>
<%--    <input name="salary" value="${employee.salary}" type="number" required placeholder="Input salary"/><br><br>--%>
<%--    <input type="submit" value="Send"/>--%>
<%--</form>--%>
<%--</body>--%>
<%--</html>--%>
