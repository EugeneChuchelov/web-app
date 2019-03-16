document.getElementById("nameInput").setAttribute('placeholder', 'Ivanov for example');
document.getElementById("nameInput").onmouseover = function(){
	document.getElementById("nameInput").setAttribute('placeholder', '');
}
document.getElementById("nameInput").onmouseout = function(){
	document.getElementById("nameInput").setAttribute('placeholder', 'Ivanov for example');
}

document.getElementById("numberInput").onchange = function(){
	if(isNaN(parseInt(document.getElementById("numberInput").value))){
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
	var name = document.getElementById('nameInput').value;
	window.open("name.html?name=" + name);
}