<%@page errorPage="errorPage.jsp"%>
<html>
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="styles.css">
    <script async src="scripts.js"></script>
</head>
<body onload="init();">
<!-- <form action="main.jsp"> -->
    <div>
        <input type="text" placeholder="ID" class="text" name="id" value="" id="idField" onkeyup="findById()">
        <input class="button" type="submit" value="Find" name="findById">
    </div>
    <div class="create" align="center">
        <a href="add.html">Create</a>
    </div>
    <div>
        <input type="text" class="text" name="name" value="" id="nameField" onkeyup="findByName()">
        <input class="button" type="submit" value="Find" name="findByName">
    </div>
    <div>
        <!-- <input class="button" type="submit" value="All" name="findAll" onclick="findAll()"> -->
        <button onclick="findAll()">All</button>
    </div>
<!-- </form> -->
<div class="results">
    <table id="table"></table>
</div>
</body>
</html>