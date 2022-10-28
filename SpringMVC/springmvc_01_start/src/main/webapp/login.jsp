<%--
  Created by IntelliJ IDEA.
  User: Lee
  Date: 2021/9/2
  Time: 19:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="http://localhost:8080/springmvc_01_start/loginServlet" method="get">
        <input type="text" name="username" value=${cookie.username.value}></br>
        <input type="password" name="password">
        <input type="submit" value="登录">
    </form>
</body>
</html>
