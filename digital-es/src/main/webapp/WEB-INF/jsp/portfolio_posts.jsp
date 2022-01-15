<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>Posts - ${firstName} ${lastName}</title>
<link rel="stylesheet"
	href="../../portfolio_assets/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="../../portfolio_assets/bootstrap/css/portfolio_posts.css">
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
		<div class="container ">
			<div class="pe-3" id="profileImageLogo">
				<img class="border rounded-circle img-profile"
					src="${profilePicture }" height="32" width="32">
			</div>
			<a class="navbar-brand logo" href="/users/${username}/homepage">${firstName} ${lastName}</a>
			<button data-bs-toggle="collapse" class="navbar-toggler"
				data-bs-target="#navbarNav">
				<span class="visually-hidden">Toggle navigation</span><span
					class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarNav">
				<ul class="navbar-nav ms-auto">
					<li class="nav-item"><a class="nav-link" href="homepage">Home</a></li>
					<li class="nav-item"><a class="nav-link" href="projects">Projects</a></li>
					<li class="nav-item"><a class="nav-link active" href="posts">Posts</a></li>
					<li class="nav-item"><a class="nav-link" href="curriculum">CV</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<main class="page lanidng-page">
		<c:if test="${almostOnePost==false}">
			
				<img class="img-fluid" alt="" src="../../portfolio_assets/img/no_posts.png">

			<!-- POST END -->

		</c:if>
		<c:if test="${almostOnePost==true}">
		<div class="container">
			<div class="row">
				<div class="col-sm-12">
					<div>
						<div class="content social-timeline">
							<div class="row">


								<div class="col-xl-9 col-lg-8 col-md-8 col-xs-12 ">

									<div class="card social-tabs"></div>

									<div class="tab-content">

										<div class="tab-pane active" id="timeline">


											<!-- POST BEGIN -->

											<c:forEach items="${posts}" var="post">
												<div class="row">
													<div class="col-md-12 timeline-dot">
														<div class="social-timelines p-relative">
															<div class="row timeline-right p-t-35">
																<div class="col-2 col-sm-2 col-xl-1">
																	<div class="social-timelines-left">
																		<img class="img-radius timeline-icon"
																			src="${profilePicture}" alt="">
																	</div>
																</div>
																<div class="col-10 col-sm-10 col-xl-11 p-l-5 p-b-35">
																	<div class="card">
																		<div class="card-block post-timelines">
																			<span
																				class="dropdown-toggle addon-btn text-muted f-right service-btn"
																				data-toggle="dropdown" aria-haspopup="true"
																				aria-expanded="true" role="tooltip"></span>

																			<div class="f-w-600">${firstName}${lastName}</div>
																			<c:if
																				test="${post.lastEditDate == '' || post.lastEditDate == null}">
																				<div class="social-time text-muted">Posted on:
																					${post.pubblicationDate}</div>
																			</c:if>

																			<c:if
																				test="${post.lastEditDate != '' && post.lastEditDate != null}">
																				<div class="social-time text-muted">Updated
																					on: ${post.lastEditDate}</div>
																			</c:if>


																		</div>

																		<c:if
																			test="${post.picture != 'undefined' && post.picture != null}">
																			<img src="${post.picture}" height="350"
																				class=" width-100" alt="">
																		</c:if>

																		<div class="card-block">
																			<div class="timeline-details">
																				<div>
																					<h4>${post.title }</h4>
																				</div>
																				<p class="text-muted">${post.description}</p>
																				<c:if
																			test="${post.refLink != '...' && post.refLink != null}">
																			<p class="text-muted">Reference Link: <a href="${post.refLink}">${post.refLink}</a> </p>
																		</c:if>
																				
																			</div>
																		</div>


																	</div>
																</div>
															</div>
														</div>

													</div>
												</div>
												<!-- POST END -->
											</c:forEach>



										</div>

									</div>
								</div>
							</div>

						</div>
					</div>
				</div>
			</div>
		</div>
		<div></div>
		</c:if>
	</main>

	<footer class="page-footer">
		<div class="container">
			<div class="social-icons">
				<c:if test="${facebookLink != null && facebookLink != ''}">
					<a href="https://${facebookLink}"><i class="icon ion-social-facebook"></i></a>
				</c:if>
				<c:if test="${instagramLink != null && instagramLink != ''}">
					<a href="https://${instagramLink}"><i
						class="icon ion-social-instagram-outline"></i></a>
				</c:if>
				<c:if test="${twitterLink != null && twitterLink != ''}">
					<a href="https://${twitterLink}"><i class="icon ion-social-twitter"></i></a>
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