<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Projects</title>
    <link rel="stylesheet" href="../../portfolio_assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Lato:300,400,700">
    <link rel="stylesheet" href="../../portfolio_assets/fonts/ionicons.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/pikaday/1.6.1/css/pikaday.min.css">
</head>

<body>
    <nav class="navbar navbar-dark navbar-expand-lg fixed-top bg-light portfolio-navbar gradient">
        <div class="container"><a class="navbar-brand logo" href="#">${firstName} ${lastName}</a><button data-bs-toggle="collapse"
                class="navbar-toggler" data-bs-target="#navbarNav"><span class="visually-hidden">Toggle
                    navigation</span><span class="navbar-toggler-icon"></span></button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav ms-auto">
                    <li class="nav-item"><a class="nav-link" href="homepage">Home</a></li>
                    <li class="nav-item"><a class="nav-link active" href="projects">Projects</a></li>
                    <li class="nav-item"><a class="nav-link" href="posts">Posts</a></li>
                    <li class="nav-item"><a class="nav-link" href="curriculum">CV</a></li>
                </ul>
            </div>
        </div>
    </nav>
    <main class="page projects-page">
        <section class="portfolio-block projects-cards">
        	<c:if test="${emptyProject==true}">
          	  <div class="heading">
	          	<h2>I don't have any project to show you yet!</h2>
	          </div>
        	</c:if>
        	<c:if test="${emptyProject==false}">
	            <div class="container">
	                <div class="heading">
	                    <h2>Here are all my projects</h2>
	                </div>
	                <div class="row" style="display: flex;">
	                    <c:forEach items="${projects}" var="project">
		  					<div class="col-md-6 col-lg-4">
		                        <div class="card border-0 text-center">
		                        	<a href="${project.linkRef}">
			                        	<img class="card-img-top scale-on-hover" src="${project.picture }" alt="Card Image" 
			                        		 style="width: 300px; 
	  												height: 300px;
	    											object-fit: cover;">
    								</a>
		                            <div class="card-body">
		                                <h6><a href="#">${project.title}</a></h6>
		                                <p class="text-muted card-text">${project.description}</p>
		                            </div>
		                        </div>
		                    </div>
	  					</c:forEach>
	                </div>
	            </div>
            </c:if>
        </section>
    </main>
    <footer class="page-footer">
        <div class="container">
            <div class="links"><a href="homepage">About me</a><a href="#">Projects</a></div>
            <div class="social-icons">
            	<c:if test="${facebook!= '' && facebook != null}">
            		<a href="${facebook}"><i class="icon ion-social-facebook"></i></a>
            	</c:if>
            	<c:if test="${instagram!= '' && instagram != null}">
            		<a href="${instagram}"><i class="icon ion-social-instagram-outline"></i></a>
            	</c:if>
            	<c:if test="${twitter!= '' && twitter != null}">
            		<a href="${twitter}"><i class="icon ion-social-twitter"></i></a>
            	</c:if>
            </div>
        </div>
    </footer>
    <script src="../../portfolio_assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/pikaday/1.6.1/pikaday.min.js"></script>
    <script src="../../portfolio_assets/js/theme.js"></script>
</body>

</html>