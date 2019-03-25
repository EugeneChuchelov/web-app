<%@ page import="java.util.Collection" %>
<%@ page import="entity.Employee" %>
<%@ page import="java.util.LinkedList" %>
<%@ page import="javax.naming.InitialContext" %>
<%@ page import="bean.EmployeeDAO" %>
<%@page errorPage="errorPage.jsp"%>
<html>
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="styles.css">
    <script async src="scripts.js"></script>
</head>
<body>

<%
    int id = 0;
    String name = "";
    Collection<Employee> employees = new LinkedList<Employee>();
    InitialContext ic = new InitialContext();
    EmployeeDAO empl = (EmployeeDAO) ic.lookup(EmployeeDAO.class.getName());
    if(request.getParameter("findAll") != null){
        employees = empl.findAll();
    }
    if(request.getParameter("findById") != null){
        id = Integer.parseInt(request.getParameter("id"));
        employees = empl.findByID(id);
    }
    if(request.getParameter("findByName") != null){
        name = request.getParameter("name");
        employees = empl.findByName(name.toUpperCase());
    }
%>
<form action="main.jsp">
    <div>
        <input type="text" placeholder="ID" class="text" id="numberInput" name="id" value="<%=id%>">
        <input class="button" type="submit" value="Find" name="findById">
    </div>
    <div class="create" align="center">
        <a href="add.html">Create</a>
    </div>
    <div>
        <input type="text" class="text" id="nameInput" name="name" value="<%=name%>">
        <input class="button" type="submit" value="Find" name="findByName">
    </div>
    <div>
        <input class="button" type="submit" value="All" name="findAll">
    </div>
</form>
<div class="results">
    <table id="table">
        <%
            for(Employee employee : employees){
        %>
        <tr class="employee" id="employee<%=employee.getId()%>">
            <td><input type="checkbox"></td>
            <td><a href="id<%=employee.getId()%>"><%=employee.getId()%></a></td>
            <td><%=employee.getName()%></td>
            <td><%=employee.getJobtitle()%></td>
            <td><%=employee.getHireDate()%></td>
            <td><%=employee.getDepartment().getName()%></td>
            <td><a href="javascript:removeEmployee(<%=employee.getId()%>)">Remove</a></td>
        </tr>
        <%}%>
    </table>
</div>
</body>
</html>