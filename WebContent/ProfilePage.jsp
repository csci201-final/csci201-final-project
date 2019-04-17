<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="DatabasePackage.*, EventPackage.*, java.util.Vector" %>
<!DOCTYPE html>
<html>
	<script> 
		var pushSocket = new WebSocket("ws://10.26.5.186:8080/cs201_final/push/")
		
		pushSocket.onmessage = function (event) {
			alert(event.data); 
		
		  console.log(event.data);
		};
	</script>
<%
	DatabaseManager.checkDatabase();
	if (session.getAttribute("loggedin") == null){
		session.setAttribute("loggedin",false);
	}
	if (!((Boolean)session.getAttribute("loggedin"))) {
		%> <jsp:forward page="Login.jsp" /> <%
	}
	if (session.getAttribute("userEvents") == null) {
		session.setAttribute("userEvents", DatabaseQuery.getUserEvents((String)session.getAttribute("username")));
	}
	@SuppressWarnings("unchecked")
	Vector<Event> userEvents = (Vector<Event>) session.getAttribute("userEvents");
	Vector<Event> pastEvents = SortEvents.getPastEvents(userEvents);
	Vector<Event> futureEvents = SortEvents.getFutureEvents(userEvents);
	int numEvents = userEvents.size();
	String username = (String) session.getAttribute("username");
	String picPath = DatabaseQuery.getPicPath(username);
	String profileTitle = numEvents > 0 ? "HOST" : "PARTYGOER";
	Double rating = DatabaseQuery.getUserRating(username);
	String ratingString = null;
	if (rating == -1.0) {
		ratingString = "None yet!";
	}
	else {
		ratingString = rating + " out of 5";
	}
%>
<head>
	<link href="https://fonts.googleapis.com/css?family=Poppins"
		rel="stylesheet">
	<link
		href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css"
		rel="stylesheet" id="bootstrap-css">
	<script
		src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
	<meta charset="UTF-8">
	<link rel="stylesheet"
		href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
	<title>Profile Page</title>
	<link rel="stylesheet" type="text/css" href="styles/ProfilePage.css">
</head>
<script>
	$(document).ready(function() {
		$(".myratings").click(function() {
			$(".ratingspage").show();
			$(".settingspage").hide();
			$("#eventdisplay").hide();
			$("#myeventstitle").hide();

		});
		$(".first").click(function() {
			$("#eventdisplay").show();
			$(".ratingspage").hide();
			$(".settingspage").hide();
			$("#myeventstitle").show();

		});
		$(".mysettings").click(function() {
			$(".settingspage").show();
			$(".ratingspage").hide();
			$("#eventdisplay").hide();
			$("#myeventstitle").hide();
		});
		$("#seeAll").click(function() {
			$(".events-all").show();
			$(".events-past").hide();
			$(".events-future").hide();
		});
		$("#seePast").click(function() {
			$(".events-all").hide();
			$(".events-past").show();
			$(".events-future").hide();
		});
		$("#seeFuture").click(function() {
			$(".events-all").hide();
			$(".events-past").hide();
			$(".events-future").show();
		});
	});
	$(document).ready(function() {
	    $(".ratingspage").hide();
	    $(".settingspage").hide();
	    $(".events-past").hide();
	    $(".events-future").hide();
	});
	function newEvent() {
		window.location.replace("http://localhost:8080/CSCI201-Final-PartyPeople/NewEvent.jsp");
	}
	function to_EventDetails(event_name) {
		var this_event = event_name; 
		var xhttp = new XMLHttpRequest();
		xhttp.open("GET", "SearchServlet?this_event=" + this_event, true);
		xhttp.send(); 
	}
</script>
<body>

	<div class="container-fluid">

		<div class="row" id="header">
			<form action="HomePage.jsp" class="headerforms" id="homebutton">
				<button type="submit" class="btn btn-default btn-lg homebutton">
					<span class="glyphicon glyphicon-home"></span> Home
				</button>
			</form>
			<form action="Logout.jsp" class="headerforms" id="outbutton">
				<button type="submit" class="btn btn-default btn-lg logoutbutton">
					<span class="glyphicon glyphicon-log-out"></span> Log Out
				</button>
			</form>
			<div class="col-md-12">
				<img src="images/partypeople-logo.png" id="logo" height="60" width="170" />
				<div class="banner"></div>
			</div>
			
		</div>
		
		<div class="row">
			<div class="sideprofile">
				<div class="col-md-4">
					<div class="sider">
						<div class="profilePic">
							<img src=<%= picPath %>>
						</div>
						<div class="userinfo">
							<div class="profilename"><%= username %></div>
							<div class="profiletitle"><%= profileTitle %></div>
							<div class="ratings">
								My Rating: <%= ratingString %>
							</div>
						</div>

						<div class="useroptions">
							<ul class="bar">
								<li class="first" style="list-style-type: none;"><span
									class="glyphicon glyphicon-th-list"></span> My Events</li>
								<li class="myratings" style="list-style-type: none;"
									onclick="ratings()"><span class="glyphicon glyphicon-star"></span>
									My Ratings</li>
								<li class="mysettings" style="list-style-type: none;"><span
									class="glyphicon glyphicon-user" style="outline: none;"></span>
									Account Settings</li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
		<h1 id="myeventstitle">My Events</h1>
		<button class="plus" style="border: none;" onclick="newEvent()">
				<span class="glyphicon glyphicon-plus-sign" style="font-size: 30px;"></span>
		</button>
		<div id="eventdisplay">
			<div class="menbar">
				<ul>
					<li><span id="seeAll">All</span></li>
					<li><span id="seePast">Past</span></li>
					<li><span id="seeFuture">Future</span></li>
				</ul>
			</div>

			<div class="container">
				<div class="events-all">
					<% if (userEvents.size() > 0) { %>
					<table>
						<% for (Event e : userEvents) { %>
						<tr>
							<th onclick="to_EventDetails(<%=e.getName()%>);"><%= e.getEventName() %></th>
							<th>Tags: <%= e.getTags() %></th>
						</tr>
						<tr>
							<th><%= e.getAffiliation() %></th>
						</tr>
						<tr>
							<th><%= e.getLocation() %></th>
						</tr>
						<tr>
							<th><%= e.getDate() %></th>
						</tr>
						<tr>
							<th><%= e.getBegin() %> to <%= e.getEnd() %></th>
						</tr>
						<% } %>
					</table>
					<% } else { %>
					<div class="noEvents">No events to display</div>
					<% } %>
				</div>
				<div class="events-past">
					<% if (pastEvents.size() > 0) { %>
					<table>
						<% for (Event e : pastEvents) { %>
						<tr>
							<th onclick="to_EventDetails(<%=e.getName()%>);"> <%= e.getEventName() %></th>
							<th>Tags: <%= e.getTags() %></th>
						</tr>
						<tr>
							<th><%= e.getAffiliation() %></th>
						</tr>
						<tr>
							<th><%= e.getLocation() %></th>
						</tr>
						<tr>
							<th><%= e.getDate() %></th>
						</tr>
						<tr>
							<th><%= e.getBegin() %> to <%= e.getEnd() %></th>
						</tr>
						<% } %>
					</table>
					<% } else { %>
					<div class="noEvents">No events to display</div>
					<% } %>
				<div class="events-future">
					<% if (futureEvents.size() > 0) { %>
					<table>
						<% for (Event e : futureEvents) { %>
						<tr>
							<thonclick="to_EventDetails(<%=e.getName()%>);"><%= e.getEventName() %></th>
							<th>Tags: <%= e.getTags() %></th>
						</tr>
						<tr>
							<th><%= e.getAffiliation() %></th>
						</tr>
						<tr>
							<th><%= e.getLocation() %></th>
						</tr>
						<tr>
							<th><%= e.getDate() %></th>
						</tr>
						<tr>
							<th><%= e.getBegin() %> to <%= e.getEnd() %></th>
						</tr>
						<% } %>
					</table>
					<% } else { %>
					<div class="noEvents">No events to display</div>
					<% } %>
				</div>
			</div>
		</div>
		<div class="ratingspage">
			<h1 class="titler">My Ratings</h1>

		</div>
		<div class="settingspage">
			<h1 class="settitle">My Account Info</h1>
			<form>
				<h3>Change Password?</h3>
				<table class="changePW">
					<tr>
						<th>Old Password</th>
						<th><input type="password" class="form-control"
							id="Opassword" aria-describedby="password"
							placeholder="Enter Old Password"></th>
					</tr>
					<tr>
						<th>New Password</th>
						<th><input type="password" class="form-control"
							id="Npassword" aria-describedby="password"
							placeholder="Enter New Password"></th>
					</tr>
					<tr>
						<th>Confirm New Password</th>
						<th><input type="password" class="form-control"
							id="CNpassword" aria-describedby="password"
							placeholder="Confirm Password"></th>
					</tr>
				</table>
				<h4>Change Email</h4>
				<table class="changeEmail">
					<tr>
						<th>New Email</th>
						<th><input type="email" class="form-control" id="Nemail"
							aria-describedby="email" placeholder="Enter New Email"></th>
					</tr>
					<tr>
						<th>Confirm New Email</th>
						<th><th><input type="email" class="form-control" id="CNemail"
							aria-describedby="email" placeholder="Confirm New Email"></th></th>

					</tr>
				</table>
			</form>
		</div>
	</div>
</div>
</body>
</html>