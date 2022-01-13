<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>Register - Portfolio Builder</title>


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
	<div class="container">
		<div class="card shadow-lg o-hidden border-0 my-5">
			<div class="card-body p-0">
				<div class="row">
					<div class="col-lg-5 d-none d-lg-flex">
						<div class="flex-grow-1 bg-register-image"
							style="background-image: url(&quot;../../dashboard_assets/img/app_images/sign-up-img.png&quot;);"></div>
					</div>
					<div class="col-lg-7">
						<div class="p-5">
							<div class="text-center">
								<h4 class="text-dark mb-4">Create an Account!</h4>
							</div>
							<form class="user" id="signUpForm" action="sign_up" method="post">
								<div class="row mb-3" id="nameContainer">
									<div class="row mb-3">
										<div class="col-sm-6 mb-3 mb-sm-0">
											<input class="form-control form-control-user" type="text"
												id="firstName" placeholder="First Name" name="first_name"
												value="Gianluca">
										</div>

										<div class="col-sm-6">
											<input class="form-control form-control-user" type="text"
												id="lastName" placeholder="Last Name" name="last_name"
												value="Romeo">
										</div>
									</div>
								</div>
								<div class="row mb-3" id="dateContainer">
									<div class="mb-3">
										<div id="datePickerContainer"
											class="md-form md-outline input-with-post-icon datepicker">
											<label class="datePickerLabel" for="datePicker">Date
												of birth</label> <i class="fas fa-calendar input-prefix" tabindex=0></i>
											<input placeholder="Select date" type="date" id="datePicker"
												name="date_of_birth" class="form-control form-control-user"
												value="2000-02-02">
											<div id="dateInvalid" class="text-danger">You must be
												at least 18 years old.</div>
										</div>
									</div>
								</div>
								<div class="row mb-3" id="emailContainer">
									<div class="mb-3">
										<input class="form-control form-control-user" type="email"
											id="inputEmail" aria-describedby="emailHelp"
											placeholder="Email Address" name="email"
											value="gianlucaromeo@outlook.com">
									</div>
									<div id="emailInvalid" class="text-danger">Email already
										used. Please try again.</div>
								</div>
								<div class="row mb-3" id="usernameContainer">
									<div class="mb-3">
										<input class="form-control form-control-user" type="text"
											id="username" aria-describedby="usernameHelp"
											placeholder="Username" name="username" minlength="8"
											maxlength="20" value="gianluca1234">
									</div>
								</div>
								<div class="row mb-3" id="passwordsContainer">
									<div class="row mb-3">
										<div class="col-sm-6 mb-3 mb-sm-0">
											<input class="form-control form-control-user" type="password"
												id="passwordInput" placeholder="Password" name="password"
												minlength="8" maxlength="20" value="Password1234?">
										</div>
										<div class="col-sm-6">
											<input class="form-control form-control-user" type="password"
												id="repeatPasswordInput" placeholder="Repeat Password"
												name="password_repeat" minlength="8" maxlength="20"
												value="Password1234?">
										</div>
									</div>
									<div id="passwordsDontMatch" class="text-danger">Please
										check passwords match and try again.</div>
									<div id="passwordInvalid" class="text-danger">
										Password must contain: <br>- At least 8 characters and at
										most 20 characters. <br>- At least one digit [0-9]. <br>-
										Both lower and uppercase letters . <br>- At least a
										special character [@$!%*?&].
									</div>
								</div>
								<button class="btn btn-primary d-block btn-user w-100"
									type="submit" id="signUpBtn">Register Account</button>
								<hr>
								<a
									class="btn btn-primary d-block btn-google btn-user w-100 mb-2"
									role="button" id="googleLoginBtn"><i class="fab fa-google"></i>&nbsp;
									Login with Google</a>
								
								<hr>
							</form>
							<div class="text-center">
								<a class="small" href="login">Already have an account?
									Login!</a>
							</div>
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
	<script src="https://apis.google.com/js/platform.js" async defer></script>
	<script src="https://apis.google.com/js/client.js"></script>
	<script src="https://apis.google.com/js/api:client.js"></script>
	<script src="../../dashboard_assets/js/sign_up.js"></script>
	<script src="../../dashboard_assets/js/google_sign_up.js"></script>


</body>

</html>