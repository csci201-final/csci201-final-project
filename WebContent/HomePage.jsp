<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="DatabasePackage.*,java.util.Vector,EventPackage.Event, javax.servlet.http.HttpSession" %>
<!DOCTYPE html>
<html>

<%
	DatabaseManager.checkDatabase();
	if (session.getAttribute("loggedin") == null){
		session.setAttribute("loggedin",false);
	}
	if (session.getAttribute("username") == null){
		session.setAttribute("loggedin", false);
	}
	if (session.getAttribute("currentEvents") == null) {
		session.setAttribute("currentEvents", DatabaseQuery.getCurrentEvents());
	}
	@SuppressWarnings("unchecked")
	Vector<Event> curEvents = (Vector<Event>) session.getAttribute("currentEvents");
	int numCurEvents = curEvents.size();
%>


<head>
	<script> 
		function search(search_string){
			var xhttp = new XMLHttpRequest();
			xhttp.open("POST", "Validate", true);
			xhttp.setRequestHeader("Content-Type", "http://localhost:8080/CSCI201-Final-PartyPeople/SearchServlet/search=" + search_string);
			
			xhttp.onreadystatechange = function() {
				console.log("Working");
				reloadData();
			}
			xhttp.send();
		}
		function getInterested(){
			<% curEvents = DatabaseQuery.getInterested_User((String)session.getAttribute("username"));%>
			reloadData();
		}
		function getAttending(){
			<% curEvents = DatabaseQuery.getAttending_User((String)session.getAttribute("username"));%>
			reloadData();
		}
		function getNotAttending(){
			<% curEvents = DatabaseQuery.getNotAttending_User((String)session.getAttribute("username"));%>
			reloadData();
		}
		function reloadData(){
			document.getElementById("event-all").innerHTML = 
				"<% if (numCurEvents > 0) { %>
					<table>
				<tr>
					<th>
						<div class="solo-table">
						<%for(Event e : curEvents){ %>
							<span class="breaker"></span>
							<table>
								<tr>
									<th><%= e.getName() %></th>
									<th>Host Rating<%for (int i=0;i<5;i++){ %>
										<span class="glyphicon glyphicon-star"></span>
										<%} %>
									</th>
									<th></th>
								</tr>
								<tr>
									<th><%= e.getAffiliation() %></th>
									<th>Attending: <%= e.getNumAttending() %></th>
								</tr>
								<tr>
									<th><%= e.getLocation() %></th>
									<th>Interested: <%= e.getNumInterested() %></th>
									<th>
										<button type="button"
											class="btn btn-default btn-lg expand">
											<span class="glyphicon glyphicon-expand"></span>
										</button>
									</th>
								</tr>
								<tr>
									<th><%= e.getDate() %></th>
									<th>Not Attending: <%= e.getNumNotInterested() %></th>
								</tr>
								<tr>
									<th><%= e.getBegin() %> to <%= e.getEnd() %></th>
									<th>Tags:  <%= e.getTags() %></th>

								</tr>
									<%} %>
							</table>
						</div>
					</th>
				</tr>
			</table>
			<% } else { %>
			<div class="noEvents">
				No events to display
			</div>
			<% } %>"
		}
	</script>
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
	<meta charset="UTF-8">
	<title>Home</title>
	<link rel="stylesheet" type="text/css" href="styles/HomePage.css">
</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-6">
				<img src="images/partypeople-logo.png" id="logo" height="60" width="170" />
				<div class="banner"></div>
			</div>
			<div class="col-md-6" id="header">
				<%
				if ((boolean)session.getAttribute("loggedin")) {
				%>
				<form action="ProfilePage.jsp" class="headerforms">
					<button type="submit" class="btn btn-default btn-lg userbutton">
						<span class="glyphicon glyphicon-user"></span> My Account
					</button>
				</form>
				<form action="Logout.jsp" class="headerforms">
					<button type="submit" class="btn btn-default btn-lg logoutbutton">
						<span class="glyphicon glyphicon-log-out"></span> Log Out
					</button>
				</form>
				<% } else { %>
				<form action="Login.jsp" class="headerforms">
					<button type="submit" class="btn btn-default btn-lg loginbutton">
						<span class="glyphicon glyphicon-log-in"></span> Sign In
					</button>
				</form>
				<form action="Register.jsp" class="headerforms">
					<button type="submit" class="btn btn-default btn-lg registerbutton">
						<span class="glyphicon glyphicon-user"></span> Register
					</button>
				</form>
				<% } %>
			</div>
			<div class="row">
				<div class="col-md-7">
					<% if ((boolean)session.getAttribute("loggedin")) { %>
					<div class="profilePic">
						<img src="images/profile-pics/jeffrey_miller.jpg">
					</div>
					<div class="userinfo">
						<div class="profilename">Jeffrey Miller</div>
					</div>
					<% } else { %>
					<div class="filler"></div>
					<% } %>
					<div class="col-md-5">
						<h1>Upcoming Events</h1>
					</div>
					<input type="text" class="form-control" id="search"
							aria-describedby="search"
							placeholder="Search Event by Name or Tags">
					<button type="button" class="btn btn-default btn-lg searchglass" onclick="search()">
						<span class="glyphicon glyphicon-search"></span>
					</button>
				</div>
				<div class="row1">
					<div class="col-md-3">
						<p>
							<button type="button" class="btn btn-default btn-lg listview">
								<span class="glyphicon glyphicon-th-list"></span>
							</button>
						</p>
					</div>

					<div class="col-md-3">
						<p>
							<button type="button" class="btn btn-default btn-lg calendarview">
								<span class="glyphicon glyphicon-calendar"></span>
							</button>
						</p>
					</div>
					<div class="col-md-3">
						<p>
							<button type="button" class="btn btn-default btn-lg mapview">
								<span class="glyphicon glyphicon-map-marker"></span>
							</button>
						</p>
					</div>
				</div>
				<div class=row2>
					<div class="container">
						<div id="events-all" class="events-all">
							<% if (numCurEvents > 0) { %>
							<table>
								<tr>
									<th>
										<div class="solo-table">
										<%for(Event e : curEvents){ %>
											<span class="breaker"></span>
											<table>
												<tr>
													<th><%= e.getName() %></th>
													<th>Host Rating<%for (int i=0;i<5;i++){ %>
														<span class="glyphicon glyphicon-star"></span>
														<%} %>
													</th>
													<th></th>
												</tr>
												<tr>
													<th><%= e.getAffiliation() %></th>
													<th>Attending: <%= e.getNumAttending() %></th>
												</tr>
												<tr>
													<th><%= e.getLocation() %></th>
													<th>Interested: <%= e.getNumInterested() %></th>
													<th>
														<button type="button"
															class="btn btn-default btn-lg expand">
															<span class="glyphicon glyphicon-expand"></span>
														</button>
													</th>
												</tr>
												<tr>
													<th><%= e.getDate() %></th>
													<th>Not Attending: <%= e.getNumNotInterested() %></th>
												</tr>
												<tr>
													<th><%= e.getBegin() %> to <%= e.getEnd() %></th>
													<th>Tags:  <%= e.getTags() %></th>

												</tr>
													<%} %>
											</table>
										</div>
									</th>
								</tr>
							</table>
							<% } else { %>
							<div class="noEvents">
								No events to display
							</div>
							<% } %>
						</div>
					</div>

					<div class="sortbycat">Filters</div>
					<div class="cat">
						<button type="button" class="btn btn-default btn-lg interestedbutton" onclick="getInterested()">
						<span class="glyphicon glyphicon-star-empty"></span><span class="cattag"> Interested</span>
						</button><br> 
						<button type="button" class="btn btn-default btn-lg attendingbutton" onclick="getAttending()">
							<span class="glyphicon glyphicon-check"></span><span class="cattag"> Attending</span>
						</button> <br> 
						<button type="button" class="btn btn-default btn-lg notattendingbutton" onclick="getNotAttending()">
							<span class="glyphicon glyphicon-remove"></span><span class="cattag"> Not Attending</span>
						</button>
					</div>
					<div class="backgroundcat"></div>
					<%
					if ((boolean)session.getAttribute("loggedin")) {	
					%>
					<div class="notificationbg">
						<div class="notifications">
							<div class="notifytitle">Notifications</div>
						</div>
					</div>
					<% } %>
				</div>
			</div>
		</div>
	</div>
</body>
</html>