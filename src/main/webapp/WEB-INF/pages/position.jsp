<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title> Position </title>
    <meta charset="UTF-8">
</head>

<body>
<h1><p>Position</p></h1>
<table border="1">
    <tr><th>Name</th><th></th><th></th></tr>
    <c:forEach items="${requestScope.position}" var="position">
        <tr>
            <td>
                <p>${position.name}</p>
            </td>
            <td>
                <form action = "/positionv/edit" method="get">
                    <input type = "submit" value = "Edit"/>
                    <input type="hidden" name="id" value="${position.id}"/>
                </form>
            </td>
            <td>
                <form action = "/position/delete" method="get">
                    <input type = "hidden" name="action" value = "delete"/>
                    <input type = "hidden" name="id" value="${position.id}"/>
                    <input type = "submit" value = "Delete"/>
                </form>
            </td>
        </tr>
    </c:forEach>

</table>
<form action = "/position/save" method="get">
    <input type = "hidden" name="action" value = "save"/>
    <input type = "submit" value = "New position"/>
</form>

</body>
</html>
