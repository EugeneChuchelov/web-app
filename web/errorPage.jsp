<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<html>
<head>
    <title>ERROR</title>
</head>
<body>
<H1>ALL IS BROKEN!!!</H1>
<%
    for (StackTraceElement s : exception.getStackTrace()){
%>
<%=s.toString()%>
<br>
<%
    }
%>
<a href="main.jsp">Return</a>
</body>
</html>