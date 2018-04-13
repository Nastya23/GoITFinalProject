<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<body>
        <form action = "/employ" method="post">
            <input type = "text" name="name" value = ""/>
            <input type = "text" name="surname" value = ""/>
            <input type = "text" name="department" value = ""/>
            <input type = "text" name="position" value = ""/>
            <select size="3" name="event">
            <select size="3" name="salary">
                <c:forEach items="${requestScope.event}" var="event">
                    <option value="${event.idEvent}">${event.nameEvent}</option>
                </c:forEach>
            </select>
            <input type = "hidden" name="action" value = "save"/>
            <input type = "submit" value = "Save"/>
        </form>
</body>
</html>
