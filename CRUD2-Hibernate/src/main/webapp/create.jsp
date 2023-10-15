<%--
  Created by IntelliJ IDEA.
  User: gar24
  Date: 15.10.2023
  Time: 22:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Employee</title>
</head>
<body>
<h3>New employee</h3>
<form method="post">
    <label>Name</label><br>
    <input name="name"/><br><br>
    <label>Position</label><br>
    <input name="position"/><br><br>
    <label>Salary</label><br>
    <input name="salary" type="number" min="10000"/><br><br>
    <input type="submit" value="Save"/>
</form>
</body>
</html>
