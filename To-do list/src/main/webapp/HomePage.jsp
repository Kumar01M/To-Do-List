<%@ page language="java" contentType="text/html; charset=UTF-8"
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="HomePage.css">
<script src="HomePage.js" defer></script>
<title>To-Do List</title>
</head>
<body style="text-align:center;" >
	<h1>📒To-Do List</h1>
	<form method="POST">
		<input type="text" placeholder="0-50 characters" required name="inputtodo" id="inputtodo">
		<input type="submit" value="Add" >
	</form>
	<br>
	<div id="response-parent">
		<div id="response" style="text-align:center;"></div>
	</div>
	</body>
</html>