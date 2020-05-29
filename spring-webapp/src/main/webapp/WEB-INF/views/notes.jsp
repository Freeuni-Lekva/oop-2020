<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page session = "true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>Home</title>
    </head>
    <body>
        <a href="/logout">Logout</a></br>
        Hi ${sessionScope.user.username}
        <ul>
            <c:forEach var="note" items="${notes}">
                <li>
                    <form action="notes/${note.id}" method="POST">
                        <button type="submit">Delete</button>
                    </form>
                    ${note.text}</li>
            </c:forEach>
        </ul>
        <form action="/notes" method="POST">
            <textarea name="text"></textarea><br/>
            <button type="submit">Add note</button>
        </form>
    </body>
</html>