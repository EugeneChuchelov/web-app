<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page errorPage="errorPage.jsp"%>
<html>
<head>
    <title>THE CALCULATOR</title>
</head>
<body>
<%
    double firstNum = 0;
    double secondNum = 0;
    String res = "";
    if (request.getParameter("num0") != null) {
        firstNum = Double.parseDouble(request.getParameter("num0"));
    }
    if (request.getParameter("num1") != null) {
        secondNum = Double.parseDouble(request.getParameter("num1"));
    }

    if (request.getParameter("plus") != null) {
        res = firstNum + " + " + secondNum + " = " + (firstNum + secondNum);
    }
    if (request.getParameter("minus") != null) {
        res = firstNum + " - " + secondNum + " = " + (firstNum - secondNum);
    }
    if (request.getParameter("mult") != null) {
        res = firstNum + " * " + secondNum + " = " + (firstNum * secondNum);
    }
    if (request.getParameter("div") != null) {
        res = firstNum + " / " + secondNum + " = " + (firstNum / secondNum);
    }
    if (request.getParameter("problem") != null) {
        throw new Exception();
    }
%>
<form action="index.jsp">
    <input type="text" name="num0" id="inp0" value="<%=firstNum%>">
    <input type="text" name="num1" id="inp1" value="<%=secondNum%>">
    <br>
    <input type="submit" value="+" name="plus">
    <input type="submit" value="-" name="minus">
    <br>
    <input type="submit" value="*" name="mult">
    <input type="submit" value="/" name="div">
    <br>
    <input type="submit" value="throw" name="problem">
</form>
<%=res%>
</body>
</html>
