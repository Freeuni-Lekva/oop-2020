<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP with Java snippet</title>
    </head>
    <body>
        <%!
        String concat(String a, String b) {
          return a + b;
        }
        %>

        <%= concat("foo", "bar") %>
    </body>
</html>