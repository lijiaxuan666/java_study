<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Lee
  Date: 2021/8/23
  Time: 18:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
 <%  List<String> list =new ArrayList<>();
        list.add("aaaa");
        list.add("bbbb");
        list.add("cccc");

        pageContext.setAttribute("p",list);
 %>
    ${p}

<form action="http://localhost:8080/springmvc_01_start/upload" method="post" enctype="multipart/form-data">
    头像：<input type="file" name="username" /><br/>
    <input type="submit" value="上传">
</form>

</body>
</html>
