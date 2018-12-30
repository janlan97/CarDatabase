<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="css/main.css">
<title>Usun samochod</title>
</head>
<body>
	<h1>Usun samochod z bazy danych</h1>
	<div id="options">
		<form action="DeleteCarServlet" method="POST">
			<div id="data">id samochodu: <input type="text" name="carId" required /> <br></div>
			<div id="data"><input type="submit" value="usun auto" /></div>
		</form>
	</div>
	<footer><form action="http://localhost:8080/1111CarStorage/"> <input type="submit" value="Powrot do strony glownej" /></footer>
</body>
</html>