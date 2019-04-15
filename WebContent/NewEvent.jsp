<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>New Event</title>
	<script>
		if(<%=(Boolean)request.getSession().getAttribute("loggedin")%>){
			window.location.replace("http://localhost:8080/CSCI201-Final-PartyPeople/Login.jsp")
		}
	</script>
</head>
<body>

	<h1> Create an New Event</h1>
	<br>
	<form name="event_form" action="EventServlet" method="GET">
		<span> Name </span>
		<input type="text" name="name"> 
		<br>
		<span> Location </span>
		<input type="text" name="location"> 
		<br>
		<span> Start Date </span>
		<input type="date" name="date"> 
		<br>
		<span> Start Time </span>
		<input type="time" name="start_time"> 
		<br>
		<span> End Time </span>
		<input type="time" name="end_time"> 
		<br>
		<span> Details </span>
		<textarea form="event_form" type="text" name="details"> 
		</textarea>
		<br>
		<span> Affiliation </span>
		<input type="text" name="affiliation"> 
		<br>
		<span> Tags </span>
		<textarea form="event_form" type="text" name="tags"> 
		</textarea>
		<br>
		<input type="submit">
	</form>



</body>
</html>