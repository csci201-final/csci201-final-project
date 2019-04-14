<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="DatabasePackage.DatabaseManager" %>
<!doctype html>
<html lang="en">
<head>
<link href="https://fonts.googleapis.com/css?family=Poppins"
	rel="stylesheet">

<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="google-signin-client_id"
	content="YOUR_CLIENT_ID.apps.googleusercontent.com">

<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
	
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">

<title>Register</title>
<link rel="stylesheet" type="text/css" href="styles/Register.css">

</head>
<body>
	<% DatabaseManager.checkDatabase(); %>
	<div class="container-fluid">
		
		<div class="row">
			<div class="col-md-12">
				<img src="images/partypeople-logo.png" id="logo" height="60" width="170" />
			<div class="banner"></div>
		</div>
		<form action="HomePage.jsp" class="headerforms">
			<button type="submit" class="btn btn-default btn-lg homebutton">
				<span class="glyphicon glyphicon-home"></span> Home
			</button>
		</form>
		</div>
		<div class="row justify-content-center">
			<div class="col-md-6">
				<div class="card bg-secondary mb-3">
					<div class="card-body">
						<h3>Sign Up</h3>
						<form method="post" action="RegisterUser" enctype="multipart/form-data">
							<div class="row">
								<div class="col-md-6">
									<div class="form-group">
										<label for="fname">Your First Name</label> <input type="text"
											class="form-control" name="fname" id="fname" aria-describedby="fname"
											placeholder="Enter First Name">
									</div>
								</div>

								<div class="col-md-6">
									<div class="form-group">
										<label for="lname">Your Last Name</label> <input type="text"
											class="form-control" name="lname" id="lname" aria-describedby="lname"
											placeholder="Enter Last Name">
									</div>
								</div>
							</div>
							<div class="form-group">
								<label for="email">Your Email</label> <input type="email"
									class="form-control" id="email" name="email" aria-describedby="email"
									placeholder="Enter Email">
							</div>
							<div class="row">
								<div class="col-md-6">
									<div class="form-group">
										<label for="password">Password</label> <input type="password"
											class="form-control" id="password" name="password" aria-describedby="password"
											placeholder="Enter Password">
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<label for="Cpassword">Confirm Password</label> <input
											type="password" class="form-control" id="Cpassword"
											aria-describedby="Cpassword" name="Cpassword" placeholder="Confirm Password">
									</div>
								</div>
							</div>
							<div class="form-group">
								<label for="bio">Your Short Bio</label> <textarea rows="3"
									class="form-control" id="bio" name="bio" aria-describedby="bio"
									placeholder="Enter Bio"></textarea>
							</div>
							<div class="form-group">
								<label for="profilePic">Upload Profile Picture</label> <input type="file"
									class="form-control" id="profilePic" name="profilePic" aria-describedby="profilePic" accept="image/*" >
							</div>
							<div class="row">
								<div class="col-md-6">
									<div id="my-signin2"></div>
									<script>
										function onSuccess(googleUser) {
											console.log('Logged in as: '
													+ googleUser
															.getBasicProfile()
															.getName());
										}
										function onFailure(error) {
											console.log(error);
										}
										function renderButton() {
											gapi.signin2.render('my-signin2', {
												'scope' : 'profile email',
												'width' : 240,
												'height' : 50,
												'longtitle' : true,
												'theme' : 'dark',
												'onsuccess' : onSuccess,
												'onfailure' : onFailure
											});
										}
									</script>

									<script
										src="https://apis.google.com/js/platform.js?onload=renderButton"
										async defer></script>

								</div>
								<div class=col-md-6>
									<div id="fb-root"></div>
									<script async defer crossorigin="anonymous"
										src="https://connect.facebook.net/en_US/sdk.js#xfbml=1&version=v3.2"></script>
									<div class="fb-login-button" data-size="large"
										data-button-type="login_with" data-auto-logout-link="true"
										data-use-continue-as="false"></div>
								</div>
							</div>
							<div class="form-group ">
								<button type="submit"
									class="btn btn-primary btn-lg btn-block login-button">Register</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>