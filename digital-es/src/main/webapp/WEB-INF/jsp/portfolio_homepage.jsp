<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>Home - ${firstName} ${lastName}</title>
<link rel="stylesheet"
	href="../../portfolio_assets/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Lato:300,400,700">
<link rel="stylesheet"
	href="../../portfolio_assets/fonts/ionicons.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/pikaday/1.6.1/css/pikaday.min.css">
</head>

<body>
	<nav
		class="navbar navbar-dark navbar-expand-lg fixed-top bg-white portfolio-navbar gradient">
		<div class="container">
			<a class="navbar-brand logo" href="#">${firstName} ${lastName}</a>
			<button data-bs-toggle="collapse" class="navbar-toggler"
				data-bs-target="#navbarNav">
				<span class="visually-hidden">Toggle navigation</span><span
					class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarNav">
				<ul class="navbar-nav ms-auto">
					<li class="nav-item"><a class="nav-link active"
						href="homepage">Home</a></li>
					<li class="nav-item"><a class="nav-link" href="projects">Projects</a></li>
					<li class="nav-item"><a class="nav-link" href="posts">Posts</a></li>
					<li class="nav-item"><a class="nav-link" href="curriculum">CV</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<main class="page lanidng-page">
		<section class="portfolio-block block-intro">
			<div class="container">
				<div class="avatar"
					style="background-image: url(${profilePicture});"></div>
				<div class="about-me">
					<c:if test="${biography == null}">
						<p>
							Hello! I am <strong>${firstName} ${lastName}</strong>.
						</p>
					</c:if>
					<c:if test="${biography != null}">
						<p>${biography}.</p>
					</c:if>
					<a class="btn btn-outline-primary" role="button" href="curriculum">See
						my CV</a>
				</div>
			</div>
		</section>
		<section class="portfolio-block photography">
			<div class="container">
				<div class="row g-0 justify-content-center">
					<c:forEach items="${presentationPictures}" var="pic">
						<c:if test="${pic != 'undefined' && pic != null}">
							<div class="col-md-6 col-lg-4 item zoom-on-hover">
								<a href="#"><img class="img-fluid image" src="${pic}"></a>
							</div>
						</c:if>
					</c:forEach>
					<c:if test="${atLeastOnePresentationPicture == false}">
					<div class="col-md-6 col-lg-4 item zoom-on-hover">
								<a href="#"><img class="img-fluid image" src="../../portfolio_assets/img/default/default_presentation_image.jfif"></a>
							</div>
					</c:if>
				</div>
			</div>
		</section>
		<section class="portfolio-block call-to-action border-bottom">
			<div class="container">
				<div
					class="d-flex justify-content-center align-items-center content">
					<a class="btn btn-outline-primary m-2" role="button"
						href="projects">Show Projects</a> <a
						class="btn btn-outline-primary m-2" role="button" href="posts">Show
						Posts</a> <a class="btn btn-outline-primary m-2" role="button"
						href="curriculum">Show Curriculum</a>
				</div>
			</div>
		</section>
		<section class="portfolio-block skills">
			<div class="container">
				<!-- Start: portfolio heading -->
				<div class="heading">
					<h2>Special Skills</h2>
				</div>
				<!-- End: portfolio heading -->
				<div class="row justify-content-center">
					<c:if test="${skillName1 != null && skillName1 != ''}">
						<div class="col-md-4">
							<div class="card special-skill-item border-0">
								<div class="card-header bg-transparent border-0">
									<i class="icon ion-ios-star-outline"></i>
								</div>
								<div class="card-body">
									<h3 class="card-title">${skillName1}</h3>
									<p class="card-text">${skillDescr1}</p>
								</div>
							</div>
						</div>
					</c:if>
					<c:if test="${skillName2 != null && skillName2 != ''}">
						<div class="col-md-4">
							<div class="card special-skill-item border-0">
								<div class="card-header bg-transparent border-0">
									<i class="icon ion-ios-lightbulb-outline"></i>
								</div>
								<div class="card-body">
									<h3 class="card-title">${skillName2}</h3>
									<p class="card-text">${skillDescr2}</p>
								</div>
							</div>
						</div>
					</c:if>
					<c:if test="${skillName3 != null && skillName3 != ''}">
						<div class="col-md-4">
							<div class="card special-skill-item border-0">
								<div class="card-header bg-transparent border-0">
									<i class="icon ion-ios-gear-outline"></i>
								</div>
								<div class="card-body">
									<h3 class="card-title">${skillName3}</h3>
									<p class="card-text">${skillDescr3}</p>
								</div>
							</div>
						</div>
					</c:if>
				</div>
			</div>
		</section>
	</main>
	<footer class="page-footer">
		<div class="container">
			<div class="social-icons">
				<c:if test="${facebookLink != null}">
					<a href="${facebookLink}"><i class="icon ion-social-facebook"></i></a>
				</c:if>
				<c:if test="${instagramLink != null}">
					<a href="${instagramLink}"><i class="icon ion-social-instagram-outline"></i></a>
				</c:if>
				<c:if test="${twitterLink != null}">
					<a href="${twitterLink}"><i class="icon ion-social-twitter"></i></a>
				</c:if>
			</div>
		</div>
	</footer>
	<script src="../../portfolio_assets/bootstrap/js/bootstrap.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/pikaday/1.6.1/pikaday.min.js"></script>
	<script src="../../portfolio_assets/js/theme.js"></script>
	<script src="https://code.jquery.com/jquery-3.6.0.js"
		integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk="
		crossorigin="anonymous"></script>
</body>

</html>