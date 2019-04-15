<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>New Event</title>
	<%
		session = request.getSession();
		if((Boolean)session.getAttribute("loggedin") == false){
	%>
		
	<%	
		}
	%>
</head>
<body>

<h1> Create an New Event</h1>




</body>
</html>