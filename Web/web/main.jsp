<%@ page import="sndr.Sndr" %>
<%@ page import="javax.naming.InitialContext" %>

<%@page errorPage="errorPage.jsp"%>
<html>
<head>
    <meta charset="utf-8">
</head>
<body>
<%
    InitialContext ic = new InitialContext();
    Sndr sndr = (Sndr) ic.lookup(Sndr.class.getName());
    if(request.getParameter("Send") != null){
        sndr.send(request.getParameter("text"));
    }
%>
<form action="main.jsp">
    <input type="text" placeholder="Message" name="text">
    <input type="submit" name = "Send" value="Send">
</form>
</html>
