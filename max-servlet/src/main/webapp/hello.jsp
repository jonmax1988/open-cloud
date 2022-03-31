<%--
  Created by IntelliJ IDEA.
  User: hushu
  Date: 2022/3/30
  Time: 22:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>My Dear</title>
</head>
<body style="font-size: 18px">
<%--使用out.print("Hello JSP");方式输出--%>
<%
    for (int i = 0; i < 10; i++) {
        System.out.println("hello jsp <br>");
    }
%>
<hr>
<!--使用<%="Hello JSP"%>方式输出-->
<%
    for (int i = 0; i < 10; i++) {
%>
<%="No." + (i + 1) + "Hello JSP" %><br>
<%
    }
%>

</body>
</html>
