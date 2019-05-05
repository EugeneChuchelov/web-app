document.getElementById("nameField").setAttribute('placeholder', 'Ivanov for example');
document.getElementById("nameField").onmouseover = function(){
    document.getElementById("nameField").setAttribute('placeholder', '');
}
document.getElementById("nameField").onmouseout = function(){
    document.getElementById("nameField").setAttribute('placeholder', 'Ivanov for example');
}

document.getElementById("idField").onchange = function(){
    if(isNaN(parseInt(document.getElementById("idField").value))){
        alert('Only digits allowed!')
    }
}
function removeEmployee(id){
    if(confirm('Really remove?')){
        var employee = document.getElementById('employee' + id);
        document.getElementById('table').firstElementChild.removeChild(employee);
    }
}

function sendname(){
    var name = document.getElementById('nameField').value;
    window.open("name.html?name=" + name);
}


var employeesTable;
var nameField;
var idField;
var req;

function derp(){
    alert("derp!")
}

function init(){
    employeesTable = document.getElementById("table");
    nameField = document.getElementById("nameField");
    idField = document.getElementById("idField");
}

function findAll(){
    var url = "employees?findAll=findAll";
    req = new XMLHttpRequest();
    req.open("GET", url, true);
    req.onreadystatechange = callback;
    req.send(null);
}

function findByName(){
    if(nameField.value.length >= 2){
        var url = "employees?name=" + nameField.value;
        req = new XMLHttpRequest();
        req.open("GET", url, true);
        req.onreadystatechange = callback;
        req.send(null);
    }
}

function findById(){
    var url = "employees?id=" + idField.value;
    req = new XMLHttpRequest();
    req.open("GET", url, true);
    req.onreadystatechange = callback;
    req.send(null);
}

function callback() {
    clearTable();
    if (req.readyState == 4) {
        if (req.status == 200) {
            parseMessages(req.responseText);
        }
    }
}

function clearTable() {
    if (employeesTable.getElementsByTagName("tr").length > 0) {
        for (var loop = employeesTable.childNodes.length - 1;
             loop >= 0 ;
             loop--) {
            employeesTable.removeChild(
                employeesTable.childNodes[loop]);
        }
    }
}

function parseMessages(responseText) {
    if (responseText == null) {
        return false;
    } else {
        var employees = JSON.parse(responseText);
        if (employees.length > 0) {
            for (var loop = 0; loop < employees.length; loop++) {
                appendEmployee(employees[loop]);
            }
        }
        return true;
    }
}

function appendEmployee(employee) {
    var row;
    var idcell, namecell, jobcell, datecell, depnamecell;
    row = document.createElement("tr");
    row.className = "employee";
    idcell = document.createElement("td");
    namecell = document.createElement("td");
    jobcell = document.createElement("td");
    datecell = document.createElement("td");
    depnamecell = document.createElement("td");

    idcell.textContent = employee.id;
    row.appendChild(idcell);

    namecell.textContent = employee.name;
    row.appendChild(namecell);

    jobcell.textContent = employee.jobtitle;
    row.appendChild(jobcell);

    var dateText = employee.hireDate.year + "-" +
        employee.hireDate.month + "-" +
        employee.hireDate.day;
    datecell.textContent = dateText;
    row.appendChild(datecell);

    depnamecell.textContent = employee.department.name;
    row.appendChild(depnamecell);

    employeesTable.appendChild(row);
}

