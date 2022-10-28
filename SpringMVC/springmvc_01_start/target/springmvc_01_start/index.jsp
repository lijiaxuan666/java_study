<%--
  Created by IntelliJ IDEA.
  User: Lee
  Date: 2021/8/6
  Time: 19:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>主页</title>
    <script>
        function fun(){
            window.location.href="http://localhost:8080/springmvc_01_start/a.html"
        }
    </script>
</head>
<body>
    <button onclick="window.location.href='http://localhost:8080/springmvc_01_start/a.html'" type="button">登录1</button><br/>
    <input type="button" value="登录2" onclick="fun()"/><br/>
    <a href="http://localhost:8080/springmvc_01_start/a.html"><button>登录3</button></a><br/>
    <form action="http://localhost:8080/springmvc_01_start/a.html" method="get">
        <input type="submit" value="登录4">
    </form>

</body>
</html>
