<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>PB - Reset Password</title>


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
			<a class="btn btn-light text-primary m-2" role="button" href="/home"><i
				class="fas fa-home p-3"></i>Visit our Homepage</a>
		</div>
	</div>

	<div class="container">
		<div class="card shadow-lg o-hidden border-0 my-5">
			<div class="card-header py-3 text-center">
				<h6 class="text-primary fw-bold m-0">Portfolio Builder - Reset
					Your Password</h6>
			</div>
			<div class="card-body p-0">
				<div class="row">
					<div class="col-lg-5 d-none d-lg-flex">
						<div class="flex-grow-1 bg-register-image"
							style="background-image: url(&quot;../../dashboard_assets/img/app_images/reset-password-img.jpg&quot;);"></div>
						<div class="vr"></div>
					</div>
					<div class="col-lg-7">
						<div class="p-5">
							<div class="text-center">
								<h4 class="text-dark mb-4">Reset Password</h4>
							</div>
							<form class="user" id="forgotPasswordForm"
								action="/dashboard/login" method="post">

								<div class="row mb-3" id="passwordContainer">
									<div class="mb-3">
										<input class="form-control form-control-user" type="password"
											id="password" aria-describedby="passwordHelp"
											placeholder="Insert New Password" name="password"
											minlength="8" maxlength="20">
									</div>
								</div>
								<div class="row mb-3" id="passwordContainer">
									<div class="mb-3">
										<input class="form-control form-control-user" type="password"
											id="repeatPassword" aria-describedby="passwordHelp"
											placeholder="Repeat Password" name="repeatPassword"
											minlength="8" maxlength="20">
									</div>
								</div>


								<div id="passwordInvalid" class="text-secondary mb-4">
									Remember: Your Password must contain: <br>- At least 8
									characters and at most 20 characters. <br>- At least one
									digit [0-9]. <br>- Both lower and uppercase letters . <br>-
									At least a special character [@$!%*?&].
								</div>


								<button class="btn btn-primary d-block btn-user w-100"
									type="button" onclick="resetPassword()" id="resetBtn">Confirm</button>
							</form>
							<div class="text-center">
								<a class="small" href="/dashboard/login">Return to Login</a>
							</div>
							<div class="text-center">
								<a class="small" href="/dashboard/sign_up">Create an
									Account!</a>
							</div>

							<input type="text" id="tokenValue" value="${token}" hidden>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript"
		src="https://cdn.jsdelivr.net/npm/emailjs-com@3/dist/email.min.js"></script>
	<script src="../../dashboard_assets/bootstrap/js/bootstrap.min.js"></script>
	<script src="../../dashboard_assets/js/bs-init.js"></script>
	<script src="../../dashboard_assets/js/theme.js"></script>
	<script src="https://code.jquery.com/jquery-3.6.0.js"
		integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk="
		crossorigin="anonymous"></script>
	<script src="../../app_assets/js/appForgotPassword.js"></script>
</body>

</html>