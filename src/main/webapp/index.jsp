<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Obliczanie rat</h1><br>
	<div>W celu obliczenia wysokości rat kredytu proszę uzupełnić następujący formularz</div><br>
	<form action="Harmonogram" method="post">
		<label>Wnioskowana kwota kredytu: <input type="text" name="kwota"></label><br><br>
		<label>Ilość rat: <input type="number" name="raty" min="6" max="240"></label><br><br>
		<label>Oprocentowanie [%]: <input type="number" name="procent" min="0" max="100" step="0.1"></label><br><br>
		<label>Opłata stała: <input type="text" name="oplata"></label><br><br>
		<label>Rodzaj rat: </label>
		<label><input type="radio" name="rata" value="m" checked> Malejąca</label></label>
		<label><input type="radio" name="rata" value="s"> Stałe</label>
		<br><br>
		<input type="submit" value="Oblicz"><input type="reset" value="Wyczyść">
	</form>
</body>
</html>