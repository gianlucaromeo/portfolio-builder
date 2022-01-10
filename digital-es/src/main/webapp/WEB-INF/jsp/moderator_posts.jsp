<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>(Moderator) Posts - PB</title>
<link rel="stylesheet"
	href="../../dashboard_assets/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="../../dashboard_assets/bootstrap/css/moderatorPosts.css">
<link rel="stylesheet"
	href="../../dashboard_assets/bootstrap/css/posts.css">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i">
<link rel="stylesheet"
	href="../../dashboard_assets/fonts/fontawesome-all.min.css">
<link rel="stylesheet"
	href="../../dashboard_assets/fonts/font-awesome.min.css">
<link rel="stylesheet"
	href="../../dashboard_assets/fonts/fontawesome5-overrides.min.css">
<link rel="stylesheet"
	href="../../dashboard_assets/bootstrap/css/jquery-ui.min.css">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<link href='https://fonts.googleapis.com/css?family=Montserrat'
	rel='stylesheet'>
</head>

<body id="page-top">

	<div id="wrapper">
		<nav
			class="navbar navbar-dark align-items-start sidebar sidebar-dark accordion bg-gradient-primary p-0">
			<div class="container-fluid d-flex flex-column p-0">
				<a
					class="navbar-brand d-flex justify-content-center align-items-center sidebar-brand m-0"
					href="#"> <!-- <div class="sidebar-brand-icon rotate-n-15"><i class="fas fa-laugh-wink"></i></div> -->
					<div class="sidebar-brand-icon ">
						<i class="fas fa-cogs"></i>
					</div>
					<div class="sidebar-brand-text mx-3">
						<span>Home</span>
					</div>
				</a>
				<hr class="sidebar-divider my-0">
				<ul class="navbar-nav text-light" id="accordionSidebar">
					<li class="nav-item"><a class="nav-link" href="all_users"><i
							class="fas fa-user"></i><span>Users</span></a></li>
					<li class="nav-item"><a class="nav-link" href="all_projects"><i
							class="fas fa-project-diagram"></i><span>Projects</span></a></li>
					<li class="nav-item"><a class="nav-link active"
						href="all_posts"><i class="far fa-comments"></i><span>Posts</span></a></li>
				</ul>
				<div class="text-center d-none d-md-inline">
					<button class="btn rounded-circle border-0" id="sidebarToggle"
						type="button"></button>
				</div>
			</div>
		</nav>
		<div class="d-flex flex-column" id="content-wrapper">
			<div id="content">
				<nav
					class="navbar navbar-light navbar-expand bg-white shadow mb-4 topbar static-top">
					<div class="container-fluid">
						<button class="btn btn-link d-md-none rounded-circle me-3"
							id="sidebarToggleTop" type="button">
							<i class="fas fa-bars"></i>
						</button>

						<ul class="navbar-nav flex-nowrap ms-auto">

							

							<div class="d-none d-sm-block topbar-divider"></div>
							
							<li class="nav-item dropdown no-arrow">
								<div class="nav-item dropdown no-arrow">
									<a class="dropdown-toggle nav-link" aria-expanded="false"
										data-bs-toggle="dropdown" href="#"><span
										class="d-none d-lg-inline me-2 text-gray-600 small">${username}</span><img
										class="border rounded-circle img-profile"
										src="${profilePicture}"></a>
									<div
										class="dropdown-menu shadow dropdown-menu-end animated--grow-in">
										<a class="dropdown-item" href="#"><i
											class="fas fa-info-circle fa-md fa-fw me-2 text-gray-400"></i>&nbsp;Info</a>
										<div class="dropdown-divider"></div>
										<a class="dropdown-item" href="do_logout"><i
											class="fas fa-sign-out-alt fa-md fa-fw me-2 text-gray-400"></i>&nbsp;Logout</a>
									</div>
								</div>
							</li>
						</ul>
					</div>
				</nav>


				<!--  MAIN CONTAINER -->
				<div class="container-fluid">

					<!-- DISPLAY ELEMENTS IN ROW  -->
					<div class="row">

						<!-- HEADER PAGE TITLE -->
						<div class="row">
							<div class="col-md-2">
								<h2 class="text-dark mb-4">Posts</h2>
							</div>
						</div>
						<!-- END HEADER PAGE TITLE -->

						<!-- MAIN CARDS CONTAINER -->
						<div class="row">

							<!-- USERS CARD -->
							<div class="col-12 col-lg-5">
								<div class="card shadow mb-3">

									<!-- USERS CARD HEADER -->
									<div class="card-header py-3">
										<p class="text-primary m-0 fw-bold">Users</p>
									</div>
									<!-- END USERS CARD HEADER -->

									<!-- USERS CARD BODY -->
									<div class="card-body shadow">

										<div class="table-responsive table mt-2" id="dataTable"
											role="grid" aria-describedby="dataTable_info">
											<table class="table my-0" id="dataTable">
												<thead>
													<tr>
														<td><strong>Username</strong></td>
														<td><strong>Email</strong></td>
														<td><strong>Date of birth</strong></td>
													</tr>
												</thead>
												<tbody id="usersTableBody">

												</tbody>
												<tfoot>
													<tr>
														<td><strong>Username</strong></td>
														<td><strong>Email</strong></td>
														<td><strong>Date of birth</strong></td>
													</tr>
												</tfoot>
											</table>
										</div>

									</div>
									<!-- END USERS CARD BODY -->

								</div>
							</div>
							<!-- END USERS CARD -->


							<!-- CURRICULUMS CARD -->
							<div class="col-12 col-lg-7 " id="postsContainer">
								<div class="card shadow mb-3">

									<!-- CURRICULUMS CARD HEADER -->
									<div class="card-header py-3">
										<p class="text-primary m-0 fw-bold" id="userPostText">User's
											Posts ${currentUserSelected}</p>
									</div>
									<!-- END CURRICULUMS CARD HEADER -->

									<!-- USERS CURRICULUMS CARD BODY -->
									<div class="card-body shadow">

										<div class="table-responsive table mt-2"
											id="dataTableDivPosts" role="grid"
											aria-describedby="dataTable_info">
											<table class="table my-0" id="dataTablePost">
												<thead>
													<tr>
														<th>Picture</th>
														<th>Title</th>
														<th id="postDescriptionCol">Description</th>
														<th>Reference Link</th>
														<th id="postDateCol">Publication Date</th>
														<th id="postStatusCol">Status</th>
													</tr>
												</thead>
												<tbody id="postsTableBody"></tbody>
												<tfoot>
													<tr>
														<th>Picture</th>
														<th>Title</th>
														<th>Description</th>
														<th>Reference Link</th>
														<th>Publication Date</th>
														<th>Status</th>
													</tr>
												</tfoot>
											</table>
										</div>

									</div>
									<!-- END CURRICULUMS CARD BODY -->

								</div>
							</div>
							<!-- END CURRICULUMS CARD -->



						</div>
						<!-- END MAIN CARDS CONTAINER -->

					</div>
					<!-- END DISPLAY ELEMENTS IN ROW  -->

				</div>
				<!-- END MAIN CONTAINER -->

			</div>
			<script src="../../dashboard_assets/bootstrap/js/bootstrap.min.js"></script>
			<script src="../../dashboard_assets/js/bs-init.js"></script>
			<script src="../../dashboard_assets/js/theme.js"></script>
			<script src="https://code.jquery.com/jquery-3.6.0.js"
				integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk="
				crossorigin="anonymous"></script>
			<script src="../../dashboard_assets/js/moderatorPosts.js"></script>
			<script src="../../dashboard_assets/js/jquery-ui.min.js"></script>
</body>

</html>


