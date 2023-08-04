<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Check Balance</title>
</head>
<body>
<form action="Checkbalance">
<h3><%
	session=request.getSession();
	out.println("Balance="+session.getAttribute("balance"));
%></h3>
<a href="loginsuccess.html">Redirect</a></form>
</body>
</html>