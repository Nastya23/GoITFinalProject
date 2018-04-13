<%--
  Created by IntelliJ IDEA.
  User: Анастасия
  Date: 09.03.2018
  Time: 22:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title> Users </title>
    <meta charset="UTF-8">
</head>

<body>
<h1><p>Status</p></h1>
<table border="1">
    <tr><th>Name</th><th></th><th></th></tr>
    <c:forEach items="${requestScope.status}" var="status">
        <tr>
            <td>
                <p>${status.type}</p>
            </td>
            <td>
                <form action = "/status/edit" method="get">
                    <input type = "submit" value = "Edit"/>
                    <input type="hidden" name="id" value="${status.id}"/>
                </form>
            </td>
            <td>
                <form action = "/status/delete" method="get">
                    <input type = "hidden" name="action" value = "delete"/>
                    <input type = "hidden" name="id" value="${user.id}"/>
                    <input type = "submit" value = "Delete"/>
                </form>
            </td>
        </tr>
    </c:forEach>

</table>
<form action = "/status/save" method="get">
    <input type = "hidden" name="action" value = "save"/>
    <input type = "submit" value = "New status"/>
</form>

</body>
</html>
