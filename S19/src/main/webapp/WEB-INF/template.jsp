<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Template JSP</title>
    </head>
    <body>
        Rendering ArrayList
        <ul>
            <c:forEach var="i" items="${foo}">
                <li>${i}</li>
            </c:forEach>
        </ul>
    </body>
</html>