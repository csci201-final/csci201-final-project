<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="Login.css">

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
</head>
<body>
	<div id=header></div>
	<p>
			<button type="button" class="btn btn-default btn-md">
				<span class="glyphicon glyphicon-home"></span> Home
			</button>
		</p>
	
	<div class="container">
		<div class="card rounded">
			<form>
				<h2>Sign in</h2>
				<label for="username" class="sr-only">Username</label> <input
					type="email" id="username" class="form-control"
					placeholder="Username"> <label for="password"
					class="sr-only">Password</label> <input type="password"
					id="password" class="form-control" placeholder="Password">
				<div class="checkbox">
					<label><input type="checkbox" name="remember">
						Remember me</label>
				</div>
				<div id="reg" onclick="goRegister()">Create Account</div>
				<button type="submit" class="btn btn-default">Submit</button>
			</form>
		</div>
		
	</div>
</body>
<script>
	function goRegister(){
		window.location.replace("Register.jsp");
	}
</script>
</html>