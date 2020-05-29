<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>Login</title>
    </head>
    <body>
        <c:if test="${error != null}">
            Authentication failed: ${error}
        </c:if>
        <form method="POST">
            <label for="username">Username</label>
            <input type="text" name="username" value="${username}" /><br/>
            <label for="password">Password</label>
            <input type="password" name="password" /><br/>
            <button type="submit">Register</button>
        </form>
    </body>
</html>