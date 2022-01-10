<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>Curriculum - PB</title>
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
<link rel="stylesheet"
	href="../../dashboard_assets/bootstrap/css/notification.css">
<link rel="stylesheet"
	href="../../dashboard_assets/bootstrap/css/curriculum.css">
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
					<li class="nav-item"><a class="nav-link" href="posts"><i
							class="far fa-comments"></i><span>Posts</span></a></li>
					<li class="nav-item"><a class="nav-link active" href="curriculum"><i
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
										class="badge bg-danger badge-counter" id="notificationsNumberSpan"></span><i
										class="fas fa-bell fa-fw"></i></a>
									<div
										class="dropdown-menu dropdown-menu-end dropdown-list animated--grow-in"
										id="alertDiv">
										<h6 class="dropdown-header">alerts center</h6>



										<!-- ALERT NOTIFCHE -->




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

				<!-- MAIN CONTAINER -->
				<div class="container-fluid">

					<!-- BODY HEADER -->
					<div class="col">
						<h1 class="text-dark mb-4">Curriculum</h1>
						<div class="text-center">
							<button class="btn btn-primary btn-sm mb-3" type="submit"
								id="addExperienceBtn">Add Experience</button>
						</div>
					</div>
					<!-- END BODY HEADER -->


					<div class="row mb-3 justify-content-center" id="newExperienceContainer">
							<!-- A CURRICULUM EXPERIENCE -->
						<div class="col-12 col-lg-6" id="experienceId">
							<div class="card shadow mb-4">
								<div class="card-header py-3">
									<h6 class="text-primary fw-bold m-0">Add a new experience!</h6>
								</div>
								<div class="card-body">
									<div class="row">

										<!-- TITLE -->
										<div class="col-12 mb-3">
											<div class="row">
												<h4 class="small fw-bold">Experience Title</h4>
											</div>
											<input class="form-control" type="text" id="experienceTitle"
												placeholder="Title of the experience..." name="experience_title" 
												value="">
										</div>
										<!-- END TITLE -->

										<!-- DATE PICKER FROM -->
										<div id="datePickerFromContainer"
											class="col-12 col-sm-6 mb-3 md-form md-outline input-with-post-icon datepicker">
											<label class="datePickerLabel" for="datePicker"><strong>From</strong></label>
											<input placeholder="Select date" type="date"
												id="datePickerFrom" name="experienceDateFrom"
												class="form-control form-control-user">
										</div>
										<!-- END DATE PICKER FROM -->

										<!-- DATE PICKER TO -->
										<div id="datePickerToContainer"
											class="col-12 col-sm-6 mb-3 md-form md-outline input-with-post-icon datepicker">
											<label class="datePickerLabel" for="datePicker"><strong>To</strong></label>
											<input placeholder="Select date" type="date"
												id="datePickerTo" name="experienceDateTo"
												class="form-control form-control-user"> <input
												class="form-check-input" type="checkbox" id="toPresent"><label
												class="form-check-label" for="toPresent"><strong>Present</strong></label>
										</div>
										<!-- END DATE PICKER TO -->

										<!-- PLACE -->
										<div class="col-12 col-sm-6 mb-3">
											<div class="row">
												<h4 class="small fw-bold">Place</h4>
											</div>
											<input class="form-control" type="text" id="experiencePlace"
												placeholder="Place..."
												value="">
										</div>
										<!-- END PLACE -->

										<!-- TYPE -->
										<div class="col-12 col-sm-6 mb-3">
											<div class="row">
												<h4 class="small fw-bold">Type</h4>
											</div>
											<select class="form-select" aria-label="Work"
												id="experienceType">
												<option value="WORK">WORK</option>
												<option value="EDUCATION">EDUCATION</option>
											</select>
										</div>
										<!-- END TYPE -->

										<!-- DESCRIPTION -->
										<div class="col-12 mb-3">
											<div class="row">
												<h4 class="small fw-bold">Description</h4>
											</div>
											<textarea class="form-control" id="experienceDescription"
												rows="4"></textarea>
										</div>
										<!-- END DESCRIPTION -->

										<!-- BUTTONS -->
										<div class="row">
											<div class="col-12">
												<button class="btn btn-danger btn-sm" type="submit"
													id="discardChangesBtn">Discard changes</button>
												<button class="btn btn-primary text-light btn-sm" type="submit"
													id="doneBtn">Add</button>
											</div>
										</div>
										<!-- END BUTTONS -->
									</div>
								</div>
							</div>
						</div>
						<!-- END OF A CURRICULUM EXPERIENCE -->
					</div>
					<hr/>

					<!-- EXPERIENCES CONTAINER -->
					<div class="row mb-3" id="curriculumExperiencesContainer">

						<!-- Experiences go here... -->

					</div>
					<!-- END OF EXPERIENCES CONTAINER -->

				</div>
				<!-- END OF MAIN CONTAINER -->

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
	<script src="../../dashboard_assets/js/curriculum.js"></script>
	<script src="../../dashboard_assets/js/notification.js"></script>

</body>

</html>