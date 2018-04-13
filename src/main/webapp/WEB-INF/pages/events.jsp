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
    <title> Events </title>
    <meta charset="UTF-8">
</head>

<body>
<h1><p>Events</p></h1>
<table border="1">
    <tr><th>Name</th><th></th><th></th></tr>
    <c:forEach items="${requestScope.events}" var="events">
        <tr>
            <td>
                <p>${events.type}</p>
            </td>
            <td>
                <form action = "/events/edit" method="get">
                    <input type = "submit" value = "Edit"/>
                    <input type="hidden" name="id" value="${events.id}"/>
                </form>
            </td>
            <td>
                <form action = "/events/delete" method="get">
                    <input type = "hidden" name="action" value = "delete"/>
                    <input type = "hidden" name="id" value="${events.id}"/>
                    <input type = "submit" value = "Delete"/>
                </form>
            </td>
        </tr>
    </c:forEach>

</table>
<form action = "/events/save" method="get">
    <input type = "hidden" name="action" value = "save"/>
    <input type = "submit" value = "New events"/>
</form>

</body>
</html>
