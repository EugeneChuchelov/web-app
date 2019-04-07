<%@ page import="sender.Sender" %>
<%@ page import="javax.naming.InitialContext" %>

<%@page errorPage="errorPage.jsp"%>
<html>
<head>
    <meta charset="utf-8">
</head>
<body>

<%
    InitialContext ic = new InitialContext();
    Sender sender = (Sender) ic.lookup(Sender.class.getName());
    if(request.getParameter("Send") != null){
        sender.send(request.getParameter("text"));
    }
%>
<form action="main.jsp">
    <input type="text" placeholder="Message" name="text">
    <input type="submit" name = "Send" value="Send">

</form>

</html>