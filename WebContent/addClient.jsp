<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="css/main.css">
<title>Dodaj klienta do bazy danych</title>
</head>
<body>
	<h1>Tutaj dodasz klienta do bazy danych</h1>
	<div id="options">
	<form action="AddNewClientServlet" method="POST">
		<div id="data"><label>imie:</label><input type="text" name="name" required /> <br></div>
		<div id="data"><label>nazwisko:</label><input type="text" name="lastName" required /> <br></div>
		<div id="data">data urodzenia:	<input type="date" name="born" min="1900-01-01" max="2018-12-31" required /> <br></div>
		<div id="data">miasto:		    <input type="text" name="city"required /> <br><p></div>
		<div id="data">ulica:		    <input type="text" name="street" required /> <br></div>
		<div id="data">nr domu:		<input type="text" name="bnum" required /> <br></div>
		<div id="data">nr mieszkania:  <input type="text" name="hnum" /> <br></div>
		<div id="data"><input type="submit" value="dodaj klienta" /></div>
	</form>
	</div>
	<footer><form action="http://localhost:8080/1111CarStorage/"> <input type="submit" value="Powrot do strony glownej" /></footer>
</body>
</html>