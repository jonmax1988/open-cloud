<%--
  Created by IntelliJ IDEA.
  User: hushu
  Date: 2022/3/30
  Time: 23:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.*,java.text.SimpleDateFormat" pageEncoding="UTF-8"
         contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>date jsp</title>
</head>
<body>
<div style="font-size: 12px;border:1px solid blue;width: 350px;padding: 20px">
    <%
        Date date=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    %>
    当前系统时间：<%=sdf.format(date)%>
</div>
</body>
</html>
