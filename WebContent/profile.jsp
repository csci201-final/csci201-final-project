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
			Profile user = SQLQueryProfile(username);		
		%>
		
		<title><% // username %>'s Profile</title>
		<script></script>
	</head>
	<body>

	</body>
</html>