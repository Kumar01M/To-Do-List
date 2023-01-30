function load() {
	console.log("hello loading");
	let xhttp = new XMLHttpRequest();
	xhttp.onload = function() {
		if (xhttp.readyState === 4 && xhttp.status === 200) {
			console.log(xhttp.responseText);
			let jsonObject = JSON.parse(xhttp.responseText);
			let div = document.getElementById("response");
			for (let key in jsonObject) {
				console.log(jsonObject[key].id + " " + jsonObject[key].check);
				let p = document.createElement('label');
				p.innerText = jsonObject[key].input;
				p.id = jsonObject[key].id+"data";
				
				let cb = document.createElement('input');
				cb.type = 'checkbox';
				cb.id = jsonObject[key].id;
				if (jsonObject[key].check === 1) {
					cb.checked = 1;
					p.style.textDecoration = "line-through";
				}
				cb.onclick = listChecked;
				
				let butn = document.createElement('button');
				butn.innerHTML = 'x';
				butn.id = jsonObject[key].id;
				butn.onclick = removeFromList;
				butn.setAttribute('class',"child");
				
				let br = document.createElement('br');
				let par = document.createElement('div');
				par.id = jsonObject[key].id + "element";
				par.setAttribute("class", 'parent');
				par.append(cb, p, butn, document.createElement('p').innerHTML=" ", br);
				div.prepend(par)
				
			}
		}
	};
	xhttp.open("GET", "/To-do_list/getList");
	xhttp.send();
};
//on window loading this method is called
Window.onload = load();

document.forms[0].onsubmit = function() {
//function addEle() {
	console.log("cf");
	let inEle = document.getElementById("inputtodo").value;
	if (inEle.trim() == "") {
		alert("input data");
		return false;
	}
	let xhr = new XMLHttpRequest();
	xhr.open("POST", "/To-do_list/add", true);
	xhr.setRequestHeader("content-type", "application/x-www-form-urlencoded");
	xhr.onreadystatechange = function() {
		if (xhr.readyState === 4 && xhr.status === 200) {
			console.log(xhr.responseText);
			let div = document.getElementById("response");
			let jsonObject = JSON.parse(xhr.responseText);
			let p = document.createElement('label');
			p.innerText = jsonObject.input;
			p.id = jsonObject.id+"data";
			
			let cb = document.createElement('input');
			cb.type = 'checkbox';
			cb.id = jsonObject.id; 	
			//cb.setAttribute('class',jsonObject.id);
			if (jsonObject.check === 1) {
				cb.checked = 1;
				p.style.textDecoration = "line-through";
			}
			cb.onclick = listChecked;
			
			let butn = document.createElement('button');
			butn.innerHTML = 'x';
			butn.id = jsonObject.id;
			butn.onclick = removeFromList;
			butn.setAttribute('class',"child");
			
			let br = document.createElement('br');
			let par = document.createElement('div');
			par.id = jsonObject.id + "element";
			par.setAttribute("class", 'parent');
			par.append(cb, p, butn, br);
			div.prepend(par);
			document.getElementById("inputtodo").value = "";
		}
	};
	xhr.send("inputtodo="+inEle);
	return false;
};

function removeFromList() {
	console.log("hello there" + this.id);
	let id = this.id;
	let xhr = new XMLHttpRequest();
	let req = "id="+this.id;
	console.log(req);
	xhr.open("POST", "/To-do_list/removeList", true);
	xhr.setRequestHeader("content-type", "application/x-www-form-urlencoded");
	xhr.onreadystatechange = function() {
		if (xhr.readyState === 4 && xhr.status === 200) {
			console.log(xhr.responseText);
			let element = document.getElementById(id+"element");
			element.remove();
		}
	};
	xhr.send(req);
	return false;
};

function listChecked() {
	let req = "id="+this.id;
	let strike = this.id+'data';
	let id = this.id;
	
	let xhr = new XMLHttpRequest();
	xhr.open("POST", "/To-do_list/elementChecked", true);
	xhr.setRequestHeader("content-type", "application/x-www-form-urlencoded");
	xhr.onreadystatechange = function() {
		if (xhr.readyState === 4 && xhr.status === 200) {
			let ch = parseInt(xhr.responseText);
			let ele = document.getElementById(strike);
			if (ch == 1) {
				ele.style.textDecoration = "line-through";
			} else {
				ele.style.textDecoration = "none";
			}
			document.getElementById(id).checked = ch;
		}
	}
	xhr.send(req);
	return false;
};