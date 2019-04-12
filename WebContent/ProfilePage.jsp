<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
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
</script>
<body>

	<div class="container-fluid">

		<div class="row">
			<div class="col-md-12">
				<h2>Party Planning People</h2>
				<div class="banner"></div>

			</div>

		</div>
		<div class="row">
			<div class="sideprofile">
				<div class="col-md-4">
					<div class="sider">
						<div class="profilePic">
							<img src="jeffrey_miller.jpg">
						</div>
						<div class="userinfo">
							<div class="profilename">Jeffrey Miller</div>
							<div class="profiletitle">Host</div>
							<div class="ratings">
								My Rating:
								<%
								for (int i = 0; i < 4; i++) {
							%><span class="glyphicon glyphicon-star"></span>
								<%
									}
								%>
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
		<button class="plus" style="border: none;" onclick="goEvent()">
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
				<table class="events-all">
					<tr>
						<th>Summer Splash</th>
						<th>Event Rating</th>
						<th></th>
					</tr>
					<tr>
						<th>USC Scope</th>
						<th></th>
					</tr>
					<tr>
						<th>935 W. 30th St</th>
					</tr>
					<tr>
						<th>April 9, 2019</th>
					</tr>
					<tr>
						<th>8:00 pm to 2:00 am</th>
					</tr>
				</table>
				<table class="events-past">
					<tr>
						<th>Past Splash</th>
						<th>Event Rating</th>
						<th></th>
					</tr>
					<tr>
						<th>USC Scope</th>
						<th></th>
					</tr>
					<tr>
						<th>935 W. 30th St</th>
					</tr>
					<tr>
						<th>April 9, 2019</th>
					</tr>
					<tr>
						<th>8:00 pm to 2:00 am</th>
					</tr>
				</table>
				<table class="events-future">
					<tr>
						<th>Future Splash</th>
						<th>Event Rating</th>
						<th></th>
					</tr>
					<tr>
						<th>USC Scope</th>
						<th></th>
					</tr>
					<tr>
						<th>935 W. 30th St</th>
					</tr>
					<tr>
						<th>April 9, 2019</th>
					</tr>
					<tr>
						<th>8:00 pm to 2:00 am</th>
					</tr>
				</table>
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
	<p>
		<button type="button" class="btn btn-default btn-lg homebutton">
			<span class="glyphicon glyphicon-home"></span> Home
		</button>
	</p>
	<p>
		<button type="button" class="btn btn-default btn-lg logoutbutton">
			<span class="glyphicon glyphicon-log-out"></span> Log Out
		</button>
	</p>
</body>
</html>