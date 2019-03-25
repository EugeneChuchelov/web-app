<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<html>
<head>
    <title>ERROR</title>
</head>
<body>
<H1>ВСЁ СЛОМАЛОСЬ!!!</H1>
<%
    for (StackTraceElement s : exception.getStackTrace()){
%>
<%=s.toString()%>
<br>
<%
    }
%>
<a href="index.jsp">Вернуться обратно</a>
</body>
</html>