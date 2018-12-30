<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="css/main.css">
<title>Znajdz samochod</title>
</head>
<body>
	<h1>Znajdz samochod/samochody w bazie danych</h1>
	<div id="options">
		<form action="FindCarServlet" method="POST">
			<div id="data">id samochodu: 	<input type="text" name="VEHICLE_ID" /> <br></div>
			<div id="data">id klienta: 		<input type="text" name="CLIENT_ID" /> <br></div>
			<div id="data">model: 			<input type="text" name="model" /> <br></div>
			<div id="data">producent: 		<input type="text" name="manufacturer" /> <br></div>
			<div id="data">rok producji: 	<input type="text" name="year" /> <br></div>
			<div id="data"><input type="submit" value="znajdz samochod" /></div>
		</form>
	</div>
</body>
</html>