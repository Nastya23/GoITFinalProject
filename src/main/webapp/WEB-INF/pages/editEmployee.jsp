<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<body>

<form action = "/empoy" method="post">
    <input type = "text" name="name" value = "${requestScope.name}"/>
    <input type = "text" name="surname" value = "${requestScope.surname}"/>
    <input type = "text" name="middleName" value = "${requestScope.middleName}"/>
    <input type = "text" name="department" value = "${requestScope.department}"/>
    <input type = "text" name="position" value = "${requestScope.position}"/>
    <select size="3" name="manufId">
        <c:forEach items="${requestScope.event}" var="event">
            <c:if test="${event.idEvent == requestScope.eventIdSelect}">
                <option value="${event.idEvent}" selected>${event.nameEvent}</option>
            </c:if>
            <c:if test="${event.idEvent != requestScope.eventIdSelect}">
                <option value="${event.idEvent}">${event.nameEvent}</option>
            </c:if>
            <option value="${event.idEvent}">${event.nameEvent}</option>
        </c:forEach>
    </select>
    <input type = "hidden" name="action" value = "update"/>
    <input type = "hidden" name="id" value = "${requestScope.id}"/>
    <input type = "submit" value = "Save"/>
</form>
</body>
</html>
