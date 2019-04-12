<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="https://fonts.googleapis.com/css?family=Poppins"
	rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Merriweather" rel="stylesheet">


<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">

<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<title>Login</title>
<link rel="stylesheet" type="text/css" href="styles/Login.css">
</head>
<body>
	<!-- <div id=header></div>
	<div id="titler">Party Planning People</div> -->
	

		<div class="row">
			<div class="col-md-12">
				<h2>Party Planning People</h2>
				<div class="banner"></div>

			</div>

		</div>
	
	
	<div class="container">
		<div class="card rounded">
			<form>
				<h3>Sign in</h3>
				<label for="username" class="sr-only">Username</label> <input
					type="text" id="username" class="form-control"
					placeholder="Username"> <label for="password"
					class="sr-only">Password</label> <input type="password"
					id="password" class="form-control" placeholder="Password">
				<div class="checkbox">
					<label><input type="checkbox" name="remember">
						Remember me</label>
				</div>
				<div id="reg" onclick="goRegister()">Don't Have an Account? Create Account</div>
				<button type="submit" class="btn btn-default">Submit</button>
			</form>
		</div>
		
	</div>
	<p>
		<button type="button" class="btn btn-default btn-lg homebutton">
			<span class="glyphicon glyphicon-home"></span> Home
		</button>
	</p>
</body>
<script>
	function goRegister(){
		window.location.replace("Register.jsp");
	}
</script>
</html>