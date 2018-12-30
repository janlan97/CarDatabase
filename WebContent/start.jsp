<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="css/main.css">
<title>System zarządzania parkingiem</title>
</head>
<body>
	<h1>Zarządzaj bazą danych</h1>
	<div id="options">
		Dodaj klienta do bazy danych:		<form action="http://localhost:8080/1111CarStorage/AddNewClientServlet"> <input type="submit" value="Dodaj nowego klienta" /> </form> <br>
		Dodaj samochód do bazy danych:		<form action="http://localhost:8080/1111CarStorage/AddCarServlet"> <input type="submit" value="Dodaj samochod" /> </form> <br>
		Usun samochód z bazy danych:		<form action="http://localhost:8080/1111CarStorage/DeleteCarServlet"> <input type="submit" value="Usun samochod" /> </form> <br>
		Znajdź samochód w bazie danych:		<form action="http://localhost:8080/1111CarStorage/FindCarServlet"> <input type="submit" value="Znajdz samochod" /> </form> <br>
	</div>
	<footer>Jan Łaniecki 2018</footer>
</body>
</html>