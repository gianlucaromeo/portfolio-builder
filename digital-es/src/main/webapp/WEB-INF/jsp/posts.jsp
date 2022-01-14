<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>Posts - PB</title>
<link rel="stylesheet"
	href="../../dashboard_assets/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="../../dashboard_assets/bootstrap/css/posts.css">
<link rel="stylesheet"
	href="../../dashboard_assets/bootstrap/css/notification.css">
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
					<li class="nav-item"><a class="nav-link" href="profile"><i
							class="fas fa-user"></i><span>Profile</span></a></li>
					<li class="nav-item"><a class="nav-link" href="projects"><i
							class="fas fa-project-diagram"></i><span>Projects</span></a></li>
					<li class="nav-item"><a class="nav-link active" href="posts"><i
							class="far fa-comments"></i><span>Posts</span></a></li>
					<li class="nav-item"><a class="nav-link" href="curriculum"><i
							class="fas fa-address-card"></i><span>Curriculum</span></a></li>
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
										class="badge bg-danger badge-counter"
										id="notificationsNumberSpan"></span><i
										class="fas fa-bell fa-fw"></i></a>
									<div
										class="dropdown-menu dropdown-menu-end dropdown-list animated--grow-in"
										id="alertDiv">
										<h6 class="dropdown-header" id="alertCenter">alerts
											center</h6>



										<!-- ALERT NOTIFCHE -->




									</div>
								</div>
							</li>

							<li class="nav-item dropdown no-arrow">
								<div class="nav-item dropdown no-arrow">
									<a class="dropdown-toggle nav-link" aria-expanded="false"
										data-bs-toggle="dropdown" href="#"><span
										class="d-none d-lg-inline me-2 text-gray-600 small">${username}</span><img
										class="border rounded-circle img-profile"
										src="${profilePicture}"></a>
									<div
										class="dropdown-menu shadow dropdown-menu-end animated--grow-in">
										
										<div class="dropdown-divider"></div>
										<a class="dropdown-item" href="do_logout"><i
											class="fas fa-sign-out-alt fa-md fa-fw me-2 text-gray-400"></i>&nbsp;Logout</a>
									</div>
								</div>
							</li>
						</ul>
					</div>
				</nav>
				
				<div class="container-fluid">
					<h3 class="text-dark mb-4">Posts</h3>

					<!-- ADD -->
					<div class="row mb-3 justify-content-center">
						<div class="col-12 col-lg-6">
							<div class="card mb-3">
								<div class="card-body shadow postClass">
									<div class="text-center">
										<img src="undefined" width="160" height="160"
											id="newPostImage">
										<div class="row" style="margin-top: 20px;" id="divNewImage">
											<div class="text-center">
												<input type="file" accept="image/jpg, image/png"
													id="newImageInput" class="editPhotoChooser col-12" hidden />
												<label id="newImageLabel" for="newImageInput"
													class="labelImage"> <i class="material-icons">
														add_photo_alternate </i> &nbsp New image
												</label>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-12">

											<form>
												<label class="form-label" for="newPostTitle"><strong>Title</strong></label>
												<div class="mb-3">
													<input class="form-control" type="text" id="newPostTitle"
														placeholder="Title..." name="new_title">
												</div>
												<label class="form-label" for="newPostDescription"><strong>Description</strong></label>
												<div class="mb-3">
													<textarea class="form-control" id="newPostDSescription"
														rows="4" placeholder="Description..."
														name="new_description"></textarea>
												</div>

												<label class="form-label" for="newPostRefLink"><strong>Title</strong></label>
												<div class="mb-3">
													<input class="form-control" type="text" id="newPostRefLink"
														placeholder="Reference link..." name="new_reference_link">
												</div>


												<div class="mb-3">
													<button class="btn btn-primary btn-sm" id="addNewPostBtn"
														type="submit">Publish Post</button>
												</div>
												<button type="button" class="btn btn-primary btn-sm"
													data-bs-toggle="modal" data-bs-target="#modalIdNewPopup"
													id="popupNewBtn" hidden></button>

												<!-- Modal -->
												<div class="modal fade" id="modalIdNewPopup" tabindex="-1"
													aria-labelledby="modalLabel" aria-hidden="true">
													<div class="modal-dialog">
														<div class="modal-content">
															<div class="modal-header">
																<h5 class="modal-title" id="modalLabel">New Post</h5>
																<button type="button" class="btn-close"
																	data-bs-dismiss="modal" aria-label="Close"></button>
															</div>
															<div class="modal-body">Post successfully published</div>
															<div class="modal-footer">
																<button type="button" class="btn btn-primary"
																	data-bs-dismiss="modal" id="closeNewPopupBtn">Ok</button>
															</div>
														</div>
													</div>
												</div>

											</form>

										</div>
									</div>
								</div>
							</div>
						</div>
					</div>


					<div class="row" id="postsContainer">
						<div class="col">
							<div class="card shadow mb-3">
								<div class="card-header py-3">
									<p class="text-primary m-0 fw-bold">Published Posts</p>
								</div>
								<div class="card-body">
									<form>
										<div class="col-md-12 col-lg-12 col-sm-12 text-center"
											id="postsSection"></div>

									</form>
								</div>
							</div>


						</div>
					</div>

				</div>
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
	<script src="../../dashboard_assets/js/posts.js"></script>
	<script src="../../dashboard_assets/js/notification.js"></script>
	<script src="../../dashboard_assets/js/jquery-ui.min.js"></script>

</body>

</html>