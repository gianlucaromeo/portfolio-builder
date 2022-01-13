<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>Curriculum - ${firstName} ${lastName}</title> <
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
					<li class="nav-item"><a class="nav-link" href="homepage">Home</a></li>
					<li class="nav-item"><a class="nav-link" href="projects">Projects</a></li>
					<li class="nav-item"><a class="nav-link" href="posts">Posts</a></li>
					<li class="nav-item"><a class="nav-link active"
						href="curriculum">CV</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<main class="page cv-page">
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

		<section class="portfolio-block cv">
			<div class="container">

				<!-- WORK EXPERIENCES -->
				<div class="work-experience group">
					<div class="heading">
						<h2 class="text-center">Work Experience</h2>
					</div>

					<c:forEach items="${experiences}" var="exp">
						<c:if test="${exp.type == 'WORK'}">

							<div class="item">
								<div class="row">
									<div class="col-md-6">
										<h3>${exp.title}</h3>
										<h4 class="organization">${exp.place}</h4>
									</div>
									<div class="col-md-6">
										<span class="period">${exp.startDate} - ${exp.endDate}</span>
									</div>
								</div>
								<p class="text-muted">${exp.description}</p>
							</div>

						</c:if>
					</c:forEach>

				</div>
				<!-- END WORK EXPERIENCES -->

				<!-- EDUCATION EXPERIENCES -->
				<div class="education group">
					<div class="heading">
						<h2 class="text-center">Education</h2>
					</div>

					<c:forEach items="${experiences}" var="exp">
						<c:if test="${exp.type == 'EDUCATION'}">

							<div class="item">
								<div class="row">
									<div class="col-md-6">
										<h3>${exp.title}</h3>
										<h4 class="organization">${exp.place}</h4>
									</div>
									<div class="col-md-6">
										<span class="period">${exp.startDate} - ${exp.endDate}</span>
									</div>
								</div>
								<p class="text-muted">${exp.description}</p>
							</div>

						</c:if>
					</c:forEach>

				</div>
				<!-- END EDUCATION EXPERIENCES -->

				<div class="group">
					<div class="row">
						<div class="col-md-6">
							<div class="skills portfolio-info-card">
								<h2>Skills</h2>
								<c:forEach items="${skills}" var="skill">
									<h3>${skill.title}</h3>
									<div class="progress">
										<div class="progress-bar" aria-valuenow="${skill.level}"
											aria-valuemin="0" aria-valuemax="100"
											style="width: ${skill.level}%;">
											<span class="visually-hidden">${skill.level}%</span>
										</div>
									</div>
								</c:forEach>
							</div>
						</div>
						<div class="col-md-6">
							<div class="contact-info portfolio-info-card">
								<h2>Contact Info</h2>
								<div class="row">
									<div class="col-1">
										<i class="icon ion-android-calendar icon"></i>
									</div>
									<div class="col-9">
										<span>${dateOfBirth}</span>
									</div>
								</div>
								<div class="row">
									<div class="col-1">
										<i class="icon ion-person icon"></i>
									</div>
									<div class="col-9">
										<span>${firstName} ${lastName}</span>
									</div>
								</div>
								<c:if test="${phoneNumber != null}">
									<div class="row">
										<div class="col-1">
											<i class="icon ion-ios-telephone icon"></i>
										</div>
										<div class="col-9">
											<span>${phoneNumber}</span>
										</div>
									</div>
								</c:if>
								<c:if test="${secondaryPhoneNumber != null}">
									<div class="row">
										<div class="col-1">
											<i class="icon ion-ios-telephone icon"></i>
										</div>
										<div class="col-9">
											<span>(Secondary) ${secondaryPhoneNumber}</span>
										</div>
									</div>
								</c:if>				
								<div class="row">
									<div class="col-1">
										<i class="icon ion-at icon"></i>
									</div>
									<div class="col-9">
										<span>${email}</span>
									</div>
								</div>
								<c:if test="${contactEmail != null}">
								<div class="row">
									<div class="col-1">
										<i class="icon ion-at icon"></i>
									</div>
									<div class="col-9">
										<span>(Contact) ${contactEmail}</span>
									</div>
								</div>
								</c:if>
							</div>
						</div>
					</div>
				</div>
				<!-- HOBBIES
				<div class="hobbies group">
					<div class="heading">
						<h2 class="text-center">Hobbies</h2>
					</div>
					<p class="text-center text-muted">Lorem ipsum dolor sit amet,
						consectetur adipiscing elit. Cras risus ligula, iaculis ut metus
						sit amet, luctus pharetra mauris. Aliquam purus felis, pretium vel
						pretium vitae, dapibus sodales ante. Suspendisse potenti. Duis
						nunc eros.</p>
				</div>
				 -->
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
					<a href="${instagramLink}"><i
						class="icon ion-social-instagram-outline"></i></a>
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
</body>

</html>