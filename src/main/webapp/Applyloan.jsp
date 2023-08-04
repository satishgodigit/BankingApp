<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>choose Loan</title>
</head>
<body>

<h1>custmer id </h1>
<%
session=request.getSession();
out.println(session.getAttribute("cust_id"));
%><br>
<h1>custmer Name </h1>
<%
session=request.getSession();
out.println(session.getAttribute("customer_name"));
%>

<h1><---HERE  LOANS ---></h1>
<form action ="Loan">

<h1>1.Home Loan</h1>
<h1>2.Education loan</h1>
<h1>3.vehicle loan</h1>
<h1>4.Gold loan</h1>
<h1>5.Personal loan</h1>

<label>Enter choice</label>
<input type="text" name="id">
<input type="submit" >



</form>
</body>
</html>