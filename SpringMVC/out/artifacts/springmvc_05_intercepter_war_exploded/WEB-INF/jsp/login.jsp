<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
</head>
<body>
<h1>登录页面</h1>
<form action="${pageContext.request.contextPath}/user/Login" method="post">
    账号：<input type="text" name="username" required><br/>
    密码：<input type="password" name="password" required><br/>
    <input type="submit" value="提交">
</form>
</body>
</html>
