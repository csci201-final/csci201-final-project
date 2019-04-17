<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="DatabasePackage.*,java.util.Vector,EventPackage.Event, ServletPackage.SearchServlet" %>
<!DOCTYPE html>
<html>

<%
	DatabaseManager.checkDatabase();
	if (session.getAttribute("loggedin") == null || session.getAttribute("username") == null){
		session.setAttribute("loggedin",false);
	}
%>


<head>
 	 <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
     <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
	
	<link href="https://fonts.googleapis.com/css?family=Poppins" rel="stylesheet">
	
	<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
	
	<meta charset="UTF-8">
	
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
	
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
	
	<meta charset="UTF-8">
	
	<title>Home</title>
	<link rel="stylesheet" type="text/css" href="styles/HomePage.css">
	<script>
	$(document).ready(function() {
		$(document).ready(function() {
		    $("#attending").hide();
		    $("#interested").hide();
		    $("#notattending").hide();
		    $("#all").show();
		});
		$("#interestB").click(function() {
			$("#all").hide();
			$("#attending").hide();
		    $("#notattending").hide();
			$("#interested").show();
		});
		$("#attendingB").click(function() {
			$("#all").hide();
		    $("#notattending").hide();
			$("#interested").hide();
			$("#attending").show();
		});
		$("#notattendingB").click(function() {
			$("#all").hide();
		    $("#attending").hide();
			$("#interested").hide();
			$("#notattending").show();
		});
		$("#reset").click(function() {
			$("#attending").hide();
		    $("#interested").hide();
		    $("#notattending").hide();
		    $("#all").show();
		});
	});
	</script>
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
					<% if ((boolean)session.getAttribute("loggedin")) {%>
					<div class="profilePic">
						<img src=<%= DatabaseQuery.getPicPath((String)session.getAttribute("username")) %>>
					</div>
					<div class="userinfo">
						<div class="profilename"><%= (String)session.getAttribute("username")  %></div>
					</div>
					<% } else { %>
					<div class="filler"></div>
					<% } %>
					<div class="col-md-5">
						<h1>Upcoming Events</h1>
					</div>
					<form action="SearchServlet" method="POST">
						<input type="text" class="form-control" id="search"
							aria-describedby="search"
							placeholder="Search Event by Name or Tags">
						<input type="submit" class="btn btn-default btn-lg searchglass">
						<span class="glyphicon glyphicon-search"></span>
					</form>
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
						<div class="events-all" id="all">
							<table id="event-table">
							<% Vector<Event> curEvents = DatabaseQuery.getCurrentEvents();
							if (curEvents.size() > 0) {%>
								<tr>
									<th>
										<div class="solo-table">
										<%for(Event e : curEvents){ %>
											<span class="breaker"></span>
											<table>
												<tr>
													<th><%= e.getName() %></th>
													<th>Host Rating: 3.20 out of 5</th>
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
														<form action="GoEvent" method="post">
															<button type="submit"
															class="btn btn-default btn-lg expand">
																<span class="glyphicon glyphicon-expand"></span>
															</button>
															<input type="hidden" name="eventID" value="<%= e.getEventID() %>">
														</form>
													</th>
												</tr>
												<tr>
													<th><%= e.getDate() %></th>
													<th>Not Attending: <%= e.getNumNotAttending() %></th>
												</tr>
												<tr>
													<th><%= e.getBegin() %> to <%= e.getEnd() %></th>
													<th>Tags:  <%= e.getTags() %></th>

												</tr>
											</table>
										<%} %>
										</div>
									</th>
								</tr>
								<% } else { %>
							<div class="noEvents">
								No events to display
							</div>
							<% } %>
							</table>
						</div>
						<div class="events-all" id="interested">
							<table id="event-table">
							<% Vector<Event> interestedEvents = DatabaseQuery.getInterested_User((String)session.getAttribute("username"), curEvents);
							if (interestedEvents.size() > 0) {%>
								<tr>
									<th>
										<div class="solo-table">
										<%for(Event e : interestedEvents){ %>
											<span class="breaker"></span>
											<table>
												<tr>
													<th><%= e.getName() %></th>
													<th>Host Rating: 3.20 out of 5</th>
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
														<form action="GoEvent" method="post">
															<button type="submit"
															class="btn btn-default btn-lg expand">
																<span class="glyphicon glyphicon-expand"></span>
															</button>
															<input type="hidden" name="eventID" value="<%= e.getEventID() %>">
														</form>
													</th>
												</tr>
												<tr>
													<th><%= e.getDate() %></th>
													<th>Not Attending: <%= e.getNumNotAttending() %></th>
												</tr>
												<tr>
													<th><%= e.getBegin() %> to <%= e.getEnd() %></th>
													<th>Tags:  <%= e.getTags() %></th>

												</tr>
											</table>
										<%} %>
										</div>
									</th>
								</tr>
								<% } else { %>
							<div class="noEvents">
								No events to display
							</div>
							<% } %>
							</table>
						</div>
						<div class="events-all" id="attending">
							<table id="event-table">
							<% Vector<Event> attendingEvents = DatabaseQuery.getAttending_User((String)session.getAttribute("username"), curEvents);
							if (attendingEvents.size() > 0) {%>
								<tr>
									<th>
										<div class="solo-table">
										<%for(Event e : attendingEvents){ %>
											<span class="breaker"></span>
											<table>
												<tr>
													<th><%= e.getName() %></th>
													<th>Host Rating: 3.20 out of 5</th>
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
														<form action="GoEvent" method="post">
															<button type="submit"
															class="btn btn-default btn-lg expand">
																<span class="glyphicon glyphicon-expand"></span>
															</button>
															<input type="hidden" name="eventID" value="<%= e.getEventID() %>">
														</form>
													</th>
												</tr>
												<tr>
													<th><%= e.getDate() %></th>
													<th>Not Attending: <%= e.getNumNotAttending() %></th>
												</tr>
												<tr>
													<th><%= e.getBegin() %> to <%= e.getEnd() %></th>
													<th>Tags:  <%= e.getTags() %></th>

												</tr>
											</table>
										<%} %>
										</div>
									</th>
								</tr>
								<% } else { %>
							<div class="noEvents">
								No events to display
							</div>
							<% } %>
							</table>
						</div>
						<div class="events-all" id="notattending">
							<table id="event-table">
							<% Vector<Event> notAttendingEvents = DatabaseQuery.getNotAttending_User((String)session.getAttribute("username"), curEvents);
							if (notAttendingEvents.size() > 0) {%>
								<tr>
									<th>
										<div class="solo-table">
										<%for(Event e : notAttendingEvents){ %>
											<span class="breaker"></span>
											<table>
												<tr>
													<th><%= e.getName() %></th>
													<th>Host Rating: 3.20 out of 5</th>
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
														<form action="GoEvent" method="post">
															<button type="submit"
															class="btn btn-default btn-lg expand">
																<span class="glyphicon glyphicon-expand"></span>
															</button>
															<input type="hidden" name="eventID" value="<%= e.getEventID() %>">
														</form>
													</th>
												</tr>
												<tr>
													<th><%= e.getDate() %></th>
													<th>Not Attending: <%= e.getNumNotAttending() %></th>
												</tr>
												<tr>
													<th><%= e.getBegin() %> to <%= e.getEnd() %></th>
													<th>Tags:  <%= e.getTags() %></th>

												</tr>
											</table>
										<%} %>
										</div>
									</th>
								</tr>
								<% } else { %>
							<div class="noEvents">
								No events to display
							</div>
							<% } %>
							</table>
						</div>
					<div class="sortbycat" id="reset">Filters</div>
					<div class="cat">
						<button type="button" id="interestB" class="btn btn-default btn-lg interestedbutton">
						<span class="glyphicon glyphicon-star-empty"></span><span class="cattag"> Interested</span>
						</button><br> 
						<button type="button" id="attendingB" class="btn btn-default btn-lg attendingbutton">
							<span class="glyphicon glyphicon-check"></span><span class="cattag"> Attending</span>
						</button> <br> 
						<button type="button" id="notattendingB" class="btn btn-default btn-lg notattendingbutton">
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
	</div>
	<script>
	</script>
</body>
</html>