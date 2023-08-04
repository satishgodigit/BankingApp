<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>
hii
<%
session=request.getSession();
out.println((String)session.getAttribute("customer_name"));

%>
here are details...</h1>

<%
session=request.getSession();
out.println("Loan Type :"+session.getAttribute("l_type"));
%>
<br>
<%
session=request.getSession();
out.println("Loan Tenure :"+session.getAttribute("tenure"));
%>
<br>
<%
session=request.getSession();
out.println("Loan Interest :"+session.getAttribute("interest"));
%>
<br>
<%
session=request.getSession();
out.println("Loan Descr... :"+session.getAttribute("description"));
%>


</body>
</html>