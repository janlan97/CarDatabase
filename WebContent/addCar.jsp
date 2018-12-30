<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="css/main.css">
<title>Dodaj samochod</title>
</head>
<body>
	<h1>Dodaj samochod do bazy danych</h1>
	<div id="options">
		<form action="AddCarServlet" method="POST">
		<div id="data">id klienta: <input type="text" name="clientId" required /> <br></div>
		<div id="data">producent: <input type="text" name="producent" required /> <br></div>
		<div id="data">model: <input type="text" name="model" required /><br></div>
		<div id="data">rok produkcji: <input type="text" name="productionYear" required /> <br></div>
		<div id="data"><input type="submit" value="dodaj auto" /></div>
		</form>
	</div>
	<footer><form action="http://localhost:8080/1111CarStorage/"> <input type="submit" value="Powrot do strony glownej" /></footer>
</body>
</html>