<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="EventPackage.Event" import="java.util.Vector"%>
<%
	//Event this_event = vector.getEvent(); 
	Vector<Event> all_events = (Vector<Event>)session.getAttribute("currentEvents"); 
	//Get the event that is click from the home_page 
	String this_event = (String)session.getAttribute("this_event"); 
	Event e = null; 
	for(int i = 0; i < all_events.size(); i++) {
		if(all_events.get(i).getName().contentEquals(this_event)) {
			 e = all_events.get(i); 
		}
	} 
%>
<!DOCTYPE html>
<html>
	<head>
		<title>Event Details</title>
		<script type="text/javascript">
		</script>
	</head>
	<body>
		<h1><%= e.getName() %></h1>
		<h2>Host: <%= e.getAffiliation() %></h2>
		<h2>Address: <%= e.getLocation() %></h2>
		<h2>Time: <%= e.getBegin() %> to <%=e.getEnd()%></h2>
		<h2>	tags: <%=e.getTags()%> </h2>
		<h2>	going:  </h2>
	</body>
</html>