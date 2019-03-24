<%@ page import="calc.Calc" %>
<%@ page import="javax.naming.InitialContext" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page errorPage="errorPage.jsp"%>
<html>
<head>
    <title>THE CALCULATOR</title>
</head>
<body>
<%
    Calc calc = (Calc) session.getAttribute("calc");
    if(calc == null){
        InitialContext ic = new InitialContext();
        calc = (Calc) ic.lookup(Calc.class.getName());
        session.setAttribute("calc", calc);
    }

    if (request.getParameter("num0") != null){
        calc.setFirstOp(Double.parseDouble(request.getParameter("num0")));
    }

    if (request.getParameter("num1") != null) {
        calc.setSecondOp(Double.parseDouble(request.getParameter("num1")));
    }

    if (request.getParameter("add") != null) {
        calc.add();
    }
    if (request.getParameter("sub") != null) {
        calc.subtract();
    }
    if (request.getParameter("mult") != null) {
        calc.multiplicate();
    }
    if (request.getParameter("div") != null) {
        calc.divide();
    }
    if (request.getParameter("save") != null) {
        calc.saveToMemory();
    }
    if (request.getParameter("load0") != null) {
        calc.loadToFirst();
    }
    if (request.getParameter("load1") != null) {
        calc.loadToSecond();
    }
    if (request.getParameter("clear") != null) {
        calc.clearMemory();
    }
%>
<form action="index.jsp">
    <input type="text" name="num0" id="inp0" value="<%=calc.getFirstOp()%>">
    <input type="text" name="num1" id="inp1" value="<%=calc.getSecondOp()%>">
    Memory: <%=calc.getMemory()%>
    <br>
    <input type="submit" value="+" name="add">
    <input type="submit" value="-" name="sub">
    <br>
    <input type="submit" value="*" name="mult">
    <input type="submit" value="/" name="div">
    <br>
    <input type="submit" value="M" name="save">
    <input type="submit" value="MR0" name="load0">
    <input type="submit" value="MR1" name="load1">
    <input type="submit" value="MC" name="clear">
</form>
<%=calc.getResult()%>
</body>
</html>
