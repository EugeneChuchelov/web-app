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
        session.setAttribute("num0", firstNum);
    } else {
        Double fn = (Double) session.getAttribute("num0");
        if (fn != null) {
            firstNum = fn;
        }
    }

    if (request.getParameter("num1") != null) {
        secondNum = Double.parseDouble(request.getParameter("num1"));
        session.setAttribute("num1", firstNum);
    } else {
        Double fn = (Double) session.getAttribute("num1");
        if (fn != null) {
            firstNum = fn;
        }
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
</form>
<%=res%>
</body>
</html>
