<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>Home - Portfolio Builder</title>
<link rel="stylesheet"
	href="../../app_assets/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="../../portfolio_assets/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Lato:300,400,700,300italic,400italic,700italic">
<link rel="stylesheet"
	href="../../app_assets/fonts/font-awesome.min.css">
<link rel="stylesheet"
	href="../../app_assets/fonts/simple-line-icons.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.5.2/animate.min.css">
</head>

<body>
	<nav
		class="navbar navbar-dark navbar-expand-lg fixed-top bg-white portfolio-navbar gradient">
		<div class="container">
			<a class="navbar-brand logo" href="">Portfolio Builder</a>
			<button data-bs-toggle="collapse" class="navbar-toggler"
				data-bs-target="#navbarNav">
				<span class="visually-hidden">Toggle navigation</span><span
					class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarNav">
				<ul class="navbar-nav ms-auto">
					<li class="nav-item"><a class="nav-link"
						href="/dashboard/login">Login</a></li>
					<li class="nav-item"><a class="nav-link"
						href="/dashboard/sign_up">Sign Up</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<header class="text-center text-white masthead"
		style="background: url('../../dashboard_assets/img/app_images/homepage_intro.jpg') no-repeat center center; background-size: cover;">
		<div class="overlay"></div>
		<div class="container">
			<div class="row">
				<div class="col-xl-9 mx-auto position-relative">
					<h1 class="mb-1">Build your Portfolio.</h1>
					<p class="mb-3">It's easy. And free.</p>
				</div>
				<div class="col-md-10 col-lg-8 col-xl-7 mx-auto position-relative">
					<form>
						<div class="row justify-content-center">
							<div class="col-12 col-md-3">
								<a class="btn btn-primary m-2 gradient" role="button"
									href="/dashboard/sign_up">Start now.</a>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</header>
	<section class="text-center bg-light features-icons">
		<div class="container">
			<div class="row">
				<div class="col-lg-4">
					<div class="mx-auto features-icons-item mb-5 mb-lg-0 mb-lg-3">
						<div class="d-flex features-icons-icon">
							<i class="icon-screen-desktop m-auto text-primary"
								data-bss-hover-animate="pulse"
								onclick="javascript:location.href='#responsive_section'"></i>
						</div>
						<h3>Fully Responsive</h3>
						<p class="lead mb-0">Your Website will look great on any
							device!</p>
					</div>
				</div>
				<div class="col-lg-4">
					<div class="mx-auto features-icons-item mb-5 mb-lg-0 mb-lg-3">
						<div class="d-flex features-icons-icon">
							<i class="icon-layers m-auto text-primary"
								data-bss-hover-animate="pulse"
								onclick="javascript:location.href='#show_who_you_are_section'"></i>
						</div>
						<h3>Show who you are</h3>
						<p class="lead mb-0">Add Projects, Posts, Skills. Let people
							know you.</p>
					</div>
				</div>
				<div class="col-lg-4">
					<div class="mx-auto features-icons-item mb-5 mb-lg-0 mb-lg-3">
						<div class="d-flex features-icons-icon">
							<i class="icon-check m-auto text-primary"
								data-bss-hover-animate="pulse"
								onclick="javascript:location.href='#easy_to_use_section'"></i>
						</div>
						<h3>Easy to Use</h3>
						<p class="lead mb-0">An amazing User Interface will help you
							building your Website with just a click.</p>
					</div>
				</div>
			</div>
		</div>
	</section>
	<section class="showcase">
		<div class="container-fluid p-0">
			<div class="row g-0" id="responsive_section">
				<div class="col-lg-6 text-white order-lg-2 showcase-img"
					style="background-image: url(../../app_assets/img/bg-showcase-1.jpg);">
					<span></span>
				</div>
				<div class="col-lg-6 my-auto order-lg-1 showcase-text">
					<h2>Fully Responsive Design</h2>
					<p class="lead mb-0">
						People can see your website from any device. <br>Try it on
						Mobile Phones, Tablets, Desktop.
					</p>
				</div>
			</div>
			<div class="row g-0" id="show_who_you_are_section">
				<div class="col-lg-6 text-white showcase-img"
					style="background-image: url(../../app_assets/img/show-who-you-are.jpg);">
					<span></span>
				</div>
				<div class="col-lg-6 my-auto order-lg-1 showcase-text">
					<h2>Show who you are</h2>
					<p class="lead mb-0">
						Portoflio Builder is the easiest way for building your website
						quickly.<br> Add your experiences, projects and posts.<br>Connect
						with other people.
					</p>
				</div>
			</div>
			<div class="row g-0" id="easy_to_use_section">
				<div class="col-lg-6 text-white order-lg-2 showcase-img"
					style="background-image: url(../../app_assets/img/bg-showcase-3.jpg);">
					<span></span>
				</div>
				<div class="col-lg-6 my-auto order-lg-1 showcase-text">
					<h2>Easy to Use and Customize</h2>
					<p class="lead mb-0">
						Type. Drag images. And click.<br>That's it.<br>Easy?
					</p>
				</div>
			</div>
		</div>
	</section>
	<section class="text-center bg-light testimonials gradient">
		<div class="container">
			<h2 class="mb-5">What people are saying...</h2>
			<div class="row">
				<div class="col-lg-4">
					<div class="mx-auto testimonial-item mb-5 mb-lg-0">
						<img class="rounded-circle img-fluid mb-3"
							src="../../app_assets/img/testimonials1.jpg">
						<h5>Gianluca</h5>
						<p class="font-weight-light mb-0">"Thank you Portfolio Builder
							Team for making these free resources available to us!"</p>
					</div>
				</div>
				<div class="col-lg-4">
					<div class="mx-auto testimonial-item mb-5 mb-lg-0">
						<img class="rounded-circle img-fluid mb-3"
							src="../../app_assets/img/testimonials2.jpeg">
						<h5>Cristian</h5>
						<p class="font-weight-light mb-0">"Portfolio Builder is
							amazing."</p>
					</div>
				</div>
				<div class="col-lg-4">
					<div class="mx-auto testimonial-item mb-5 mb-lg-0">
						<img class="rounded-circle img-fluid mb-3"
							src="../../app_assets/img/testimonials3.jpg">
						<h5>Luigi</h5>
						<p class="font-weight-light mb-0">"Making my Portfolio was
							never this easy!"</p>
					</div>
				</div>
			</div>
		</div>
	</section>
	<script src="../../app_assets/bootstrap/js/bootstrap.min.js"></script>
	<script src="../../app_assets/js/bs-init.js"></script>
</body>

</html>