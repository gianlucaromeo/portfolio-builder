
<html>
<head>
<title>404 - Not Found</title>
<link rel="stylesheet"
	href="../../dashboard_assets/bootstrap/css/error_page.css">
</head>
<body>
	<div class="moon"></div>
	<div class="moon__crater moon__crater1"></div>
	<div class="moon__crater moon__crater2"></div>
	<div class="moon__crater moon__crater3"></div>

	<div class="star star1"></div>
	<div class="star star2"></div>
	<div class="star star3"></div>
	<div class="star star4"></div>
	<div class="star star5"></div>

	<div class="error">
		<div class="error__title">404</div>
		<div class="error__subtitle">Not Found...</div>
		<div class="error__description">we couldn't find that page :(</div>
		<a href="/dashboard/sign_up">  
		<button class="error__button error__button--active">GO TO SIGN UP</button>
		</a>
		<a href="/dashboard/login">  
		<button class="error__button">GO TO LOGIN</button>
		</a>
	</div>

	<div class="astronaut">
		<div class="astronaut__backpack"></div>
		<div class="astronaut__body"></div>
		<div class="astronaut__body__chest"></div>
		<div class="astronaut__arm-left1"></div>
		<div class="astronaut__arm-left2"></div>
		<div class="astronaut__arm-right1"></div>
		<div class="astronaut__arm-right2"></div>
		<div class="astronaut__arm-thumb-left"></div>
		<div class="astronaut__arm-thumb-right"></div>
		<div class="astronaut__leg-left"></div>
		<div class="astronaut__leg-right"></div>
		<div class="astronaut__foot-left"></div>
		<div class="astronaut__foot-right"></div>
		<div class="astronaut__wrist-left"></div>
		<div class="astronaut__wrist-right"></div>

		<div class="astronaut__cord">
			<canvas id="cord" height="500px" width="500px"></canvas>
		</div>

		<div class="astronaut__head">
			<canvas id="visor" width="60px" height="60px"></canvas>
			<div class="astronaut__head-visor-flare1"></div>
			<div class="astronaut__head-visor-flare2"></div>
		</div>
	</div>
	
	<script src="../../dashboard_assets/js/404.js"></script>
</body>

</html>