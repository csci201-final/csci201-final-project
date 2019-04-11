<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>    
<%@page import = "javax.servlet.http.HttpSession" %>
<%@ page import="EventPackage.Profile" %>
<%@ page import="EventPackage.Event" %>
<%@ page import="EventPackage.EventDate" %>
<%@ page import="EventPackage.Time" %>
<!DOCTYPE html>
<html>
	<head>
	
		<% 
			HttpSession mySession = request.getSession();
			String username = (String) mySession.getAttribute("username");	
		%>
		
		<title>New Event</title>
		<link rel="stylesheet" href="new-event.css">
		<script>
		
		</script>
		

	</head>
	<body>
		<div id="title">
			<h1> Create an Event</h1>
		</div>
		
		<br>
		
		<div id="form">
			<form name="newEventForm" method="GET">
				<p> Event Name </p> <br>
				<input id="eventName" type="text" name="eventName"> <br>
				<p> Event Location </p> <br>
				<input id="eventLocation" type="text" name="eventLocation"> <br>
				<p> Event Date </p> <br>
				<input id="eventDate" type="text" name="eventDate"> <br>
				<p> Event Time </p> <br>
				<input id="eventTime" type="text" name="eventTime"> <br>
				<p> Event Tags </p> <br>
				<input id="eventTags" type="text" name="eventTags"> <br>
			</form>
		</div>
		
		
		
	</body>
</html>