<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>PB - Login</title>
<link rel="stylesheet"
	href="../../dashboard_assets/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i">
<link rel="stylesheet"
	href="../../dashboard_assets/fonts/fontawesome-all.min.css">
<link rel="stylesheet"
	href="../../dashboard_assets/fonts/font-awesome.min.css">
<link rel="stylesheet"
	href="../../dashboard_assets/fonts/fontawesome5-overrides.min.css">
</head>

<body class="bg-gradient-primary">

	<div class="row justify-content-center mt-3">
		<div class="col-12 text-center">
			<a class="btn btn-light text-primary m-2"
				role="button" href="/home"><i class="fas fa-home p-3"></i>Visit our Homepage</a>
		</div>
	</div>

	<div class="container">
		<div class="row justify-content-center">
			<div class="col-md-9 col-lg-12 col-xl-10">
				<div class="card shadow-lg o-hidden border-0 my-5">
					<div class="card-header py-3 text-center">
						<h6 class="text-primary fw-bold m-0">Portfolio Builder -
							Login</h6>
					</div>
					<div class="card-body p-0">
						<div class="row">
							<div class="col-lg-6 d-none d-lg-flex">
								<div class="flex-grow-1 bg-login-image"
									style="background-image: url(&quot;../../dashboard_assets/img/app_images/login-img.jpg&quot;);"></div>
								<div class="vr"></div>
							</div>
							<div class="col-lg-6">
								<div class="p-5">
									<div class="text-center">
										<h4 class="text-dark mb-4">Welcome Back!</h4>
									</div>
									<form class="user" id="loginForm" action="login" method="post">
										<div class="mb-3">
											<input class="form-control form-control-user" type="text"
												id="username" aria-describedby="usernameHelp"
												placeholder="Enter your username..." name="username">
										</div>
										<div class="mb-3">
											<input class="form-control form-control-user" type="password"
												id="passwordInput" placeholder="Password" name="password">
										</div>
										<button class="btn btn-primary d-block btn-user w-100"
											type="submit" id="loginBtn">Login</button>
										<hr>
										<a
											class="btn btn-primary d-block btn-google btn-user w-100 mb-2"
											role="button" id="googleLoginBtn"><i
											class="fab fa-google"></i>&nbsp; Login with Google</a>
										<hr>
									</form>
									<div class="text-center">
										<a class="small" href="forgot_password">Forgot Password?</a>
									</div>
									<div class="text-center">
										<a class="small" href="/dashboard/sign_up">Create an
											Account!</a>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="../../dashboard_assets/bootstrap/js/bootstrap.min.js"></script>
	<script src="../../dashboard_assets/js/bs-init.js"></script>
	<script src="../../dashboard_assets/js/theme.js"></script>
	<script src="https://code.jquery.com/jquery-3.6.0.js"
		integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk="
		crossorigin="anonymous"></script>
	<script src="../../dashboard_assets/js/login.js"></script>
	<script src="https://apis.google.com/js/platform.js" async defer></script>
	<script src="https://apis.google.com/js/client.js"></script>
	<script src="https://apis.google.com/js/api:client.js"></script>
	<script src="../../dashboard_assets/js/google_login.js"></script>
</body>

</html>