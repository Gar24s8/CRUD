<%--
  Created by IntelliJ IDEA.
  User: gar24
  Date: 15.10.2023
  Time: 23:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Employee</title>
</head>
<body>
<h3>Edit employee</h3>
<form method="post">
    <input type="hidden" value="${employee.id}" name="id"/>
    <label>Name</label><br>
    <input name="name" value="${employee.name}" required placeholder="Input name"/> <br><br>
    <label>Position</label><br>
    <input name="position" value="${employee.position}" required placeholder="Input position"/> <br><br>
    <label>Salary</label><br>
    <input name="salary" value="${employee.salary}" type="number" required placeholder="Input salary"/><br><br>
    <input type="submit" value="Send"/>
</form>
</body>
</html>
