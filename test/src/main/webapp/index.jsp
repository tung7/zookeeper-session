<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<h1>
    <%
        HttpSession session1 = request.getSession();
        out.println(session1.getId());
    %>
</h1>
<h2>Hello World! 81</h2>

</body>
</html>
