<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>Moderators (Projects) - PB</title>
<link rel="stylesheet"
	href="../../dashboard_assets/bootstrap/css/bootstrap.min.css">
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
	href="../../dashboard_assets/bootstrap/css/profile.css">
<link rel="stylesheet"
	href="../../dashboard_assets/bootstrap/css/moderatorProjects.css">
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
					<li class="nav-item"><a class="nav-link active"
						href="all_projects"><i class="fas fa-project-diagram"></i><span>Projects</span></a></li>
					<li class="nav-item"><a class="nav-link" href="all_posts"><i
							class="far fa-comments"></i><span>Posts</span></a></li>
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

							<li class="nav-item dropdown no-arrow mx-1">
								<div class="nav-item dropdown no-arrow">
									<a class="dropdown-toggle nav-link" aria-expanded="false"
										data-bs-toggle="dropdown" href="#"><span
										class="badge bg-danger badge-counter">3+</span><i
										class="fas fa-bell fa-fw"></i></a>
									<div
										class="dropdown-menu dropdown-menu-end dropdown-list animated--grow-in">
										<h6 class="dropdown-header">alerts center</h6>
										<a class="dropdown-item d-flex align-items-center" href="#">
											<div class="me-3">
												<div class="bg-primary icon-circle">
													<i class="fas fa-file-alt text-white"></i>
												</div>
											</div>
											<div>
												<span class="small text-gray-500">December 12, 2019</span>
												<p>A new monthly report is ready to download!</p>
											</div>
										</a><a class="dropdown-item d-flex align-items-center" href="#">
											<div class="me-3">
												<div class="bg-success icon-circle">
													<i class="fas fa-donate text-white"></i>
												</div>
											</div>
											<div>
												<span class="small text-gray-500">December 7, 2019</span>
												<p>$290.29 has been deposited into your account!</p>
											</div>
										</a><a class="dropdown-item d-flex align-items-center" href="#">
											<div class="me-3">
												<div class="bg-warning icon-circle">
													<i class="fas fa-exclamation-triangle text-white"></i>
												</div>
											</div>
											<div>
												<span class="small text-gray-500">December 2, 2019</span>
												<p>Spending Alert: We've noticed unusually high spending
													for your account.</p>
											</div>
										</a><a class="dropdown-item text-center small text-gray-500"
											href="#">Show All Alerts</a>
									</div>
								</div>
							</li>

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
											class="fas fa-user fa-sm fa-fw me-2 text-gray-400"></i>&nbsp;Profile</a><a
											class="dropdown-item" href="#"><i
											class="fas fa-cogs fa-sm fa-fw me-2 text-gray-400"></i>&nbsp;Settings</a><a
											class="dropdown-item" href="#"><i
											class="fas fa-list fa-sm fa-fw me-2 text-gray-400"></i>&nbsp;Activity
											log</a>
										<div class="dropdown-divider"></div>
										<a class="dropdown-item" href="#"><i
											class="fas fa-sign-out-alt fa-sm fa-fw me-2 text-gray-400"></i>&nbsp;Logout</a>
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
								<h2 class="text-dark mb-4">Projects</h2>
							</div>
						</div>
						<!-- END HEADER PAGE TITLE -->

						<!-- MAIN CARDS CONTAINER -->
						<div class="row">

							<!-- USERS CARD -->
							<div class="col-12 col-lg-4">
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
														<th>Username</th>
													</tr>
												</thead>
												<tbody id="allUsersTableBody">

												</tbody>
												<tfoot>
													<tr>
														<td><strong>Username</strong></td>
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
							<div class="col-12 col-lg-8">
								<div class="card shadow mb-3">

									<!-- CURRICULUMS CARD HEADER -->
									<div class="card-header py-3">
										<p class="text-primary m-0 fw-bold">User's Projects</p>
									</div>
									<!-- END CURRICULUMS CARD HEADER -->

									<!-- USERS CURRICULUMS CARD BODY -->
									<div class="card-body shadow">

										<div class="table-responsive table mt-2" id="dataTable"
											role="grid" aria-describedby="dataTable_info">
											<table class="table my-0" id="dataTable">
												<thead>
													<tr>
														<th>Picture</th>
														<th>Title</th>
														<th>Description</th>
														<th>Status</th>
													</tr>
												</thead>
												<tbody id="projectsTableBody"></tbody>
												<tfoot>
													<tr>
														<th>Picture</th>
														<th>Title</th>
														<th>Description</th>
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
			
			<footer class="bg-white sticky-footer">
				<div class="container my-auto">
					<div class="text-center my-auto copyright">
						<span>Copyright © Brand 2021</span>
					</div>
				</div>
			</footer>
		</div>
		<a class="border rounded d-inline scroll-to-top" href="#page-top"><i
			class="fas fa-angle-up"></i></a>
	</div>
	<script src="../../dashboard_assets/bootstrap/js/bootstrap.min.js"></script>
	<script src="../../dashboard_assets/js/bs-init.js"></script>
	<script src="../../dashboard_assets/js/theme.js"></script>
	<script src="https://code.jquery.com/jquery-3.6.0.js"
		integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk="
		crossorigin="anonymous"></script>
	<script src="../../dashboard_assets/js/moderatorProjects.js"></script>
</body>

</html>