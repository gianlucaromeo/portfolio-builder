<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>Profile - PB</title>
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
					<li class="nav-item"><a class="nav-link" href="index.html"><i
							class="fas fa-tachometer-alt"></i><span>Dashboard</span></a></li>
					<li class="nav-item"><a class="nav-link active"
						href="profile.html"><i class="fas fa-user"></i><span>Profile</span></a></li>
					<li class="nav-item"><a class="nav-link" href="projects"><i
							class="fas fa-project-diagram"></i><span>Projects</span></a></li>
					<li class="nav-item"><a class="nav-link" href="posts"><i
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
										src="assets/img/avatars/avatar1.jpeg"></a>
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
				<div class="container-fluid">
					<h3 class="text-dark mb-4">Profile</h3>
					<div class="row mb-3">
						<div class="col-lg-4">
							<div class="card mb-3">
								<div class="card-body shadow">
									<div class="text-center">
										<img class="rounded-circle mb-3 mt-4"
											src="../../dashboard_assets/img/avatars/default_avatar_image.png"
											width="160" height="160" id="profile_picture">
										<div class="mb-3">
											<input type="file" accept="image/jpg, image/png" id="image_chooser_profile" class="editPhotoChooser col-12"/> 
													<label id="image_label_profile" for="image_chooser_profile" class="labelImage">
														Change Image
													</label>
										</div>
									</div>
									<div class="row">
										<div class="col-md-12">
											<label class="form-label" for="biography"><strong>Biography</strong></label>
											<form>
												<div class="mb-3">
													<textarea class="form-control" id="biography" rows="4"
														name="biography"></textarea>
												</div>

												<div class="mb-3">
													<button class="btn btn-primary btn-sm" type="submit" id="biography_btn">Save
														Settings</button>
												</div>
											</form>
										</div>
									</div>
								</div>
							</div>
							<div class="card shadow mb-4">
								<div class="card-header py-3">
									<h6 class="text-primary fw-bold m-0">Your 3 main Skills</h6>
								</div>
								<div class="card-body">

									<!--  A SKILL -->
									<div class="row">
										<div class="col-8 mb-3">
											<div class="row">
												<h4 class="small fw-bold">Skill</h4>
											</div>
											<input class="form-control" type="text" id="firstName"
												placeholder="First Name" name="first_name" value="Java">
										</div>
										<div class="col-4 mb-3">
											<div class="row">
												<h4 class="small fw-bold">Level (0-100)</h4>
											</div>
											<input class="form-control" type="text" id="firstName"
												placeholder="First Name" name="first_name" value="20">
										</div>

									</div>
									<div class="progress progress-sm mb-3">
										<div class="progress-bar bg-danger" aria-valuenow="20"
											aria-valuemin="0" aria-valuemax="100" style="width: 20%;">
											<span class="visually-hidden">20%</span>
										</div>
									</div>
									<hr />
									<!--  END OF A SKILL -->


									<h4 class="small fw-bold">
										Account setup<span class="float-end">Complete!</span>
									</h4>
									<div class="progress progress-sm mb-3">
										<div class="progress-bar bg-success" aria-valuenow="100"
											aria-valuemin="0" aria-valuemax="100" style="width: 100%;">
											<span class="visually-hidden">100%</span>
										</div>
									</div>

									<div class="mb-3">
										<button class="btn btn-primary btn-sm" type="submit" id="skill_btn">Add skill</button>
									</div>

								</div>
							</div>
						</div>
						<div class="col-lg-8">
							<div class="row mb-3 d-none">
								<div class="col">
									<div class="card textwhite bg-primary text-white shadow">
										<div class="card-body">
											<div class="row mb-2">
												<div class="col">
													<p class="m-0">Peformance</p>
													<p class="m-0">
														<strong>65.2%</strong>
													</p>
												</div>
												<div class="col-auto">
													<i class="fas fa-rocket fa-2x"></i>
												</div>
											</div>
											<p class="text-white-50 small m-0">
												<i class="fas fa-arrow-up"></i>&nbsp;5% since last month
											</p>
										</div>
									</div>
								</div>
								<div class="col">
									<div class="card textwhite bg-success text-white shadow">
										<div class="card-body">
											<div class="row mb-2">
												<div class="col">
													<p class="m-0">Peformance</p>
													<p class="m-0">
														<strong>65.2%</strong>
													</p>
												</div>
												<div class="col-auto">
													<i class="fas fa-rocket fa-2x"></i>
												</div>
											</div>
											<p class="text-white-50 small m-0">
												<i class="fas fa-arrow-up"></i>&nbsp;5% since last month
											</p>
										</div>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col">
									<div class="card shadow mb-3">
										<div class="card-header py-3">
											<p class="text-primary m-0 fw-bold">User Settings</p>
										</div>
										<div class="card-body">
											<form>
												<div class="row">
													<div class="col">
														<div class="mb-3">
															<label class="form-label" for="username"><strong>Username</strong></label><input
																class="form-control" type="text" id="username"
																placeholder="username" name="username"
																value="${username}">
														</div>
													</div>
													<div class="col">
														<div class="mb-3">
															<label class="form-label" for="email"><strong>Email
																	Address</strong></label><input class="form-control" type="email"
																id="email" placeholder="your_email@example.com"
																name="email" value="${email}">
														</div>
													</div>
												</div>
												<div class="row">
													<div class="col">
														<div class="mb-3">
															<label class="form-label" for="first_name"><strong>First
																	Name</strong></label><input class="form-control" type="text"
																id="firstName" placeholder="First Name"
																name="first_name" value="${firstName}">
														</div>
													</div>
													<div class="col">
														<div class="mb-3">
															<label class="form-label" for="last_name"><strong>Last
																	Name</strong></label><input class="form-control" type="text"
																id="lastName" placeholder="Last Name" name="last_name"
																value="${lastName}">
														</div>
													</div>
												</div>


												<div class="row">
													<div class="col">
														<div class="mb-3">
															<div id="datePickerContainer"
																class="md-form md-outline input-with-post-icon datepicker">
																<label class="datePickerLabel" for="datePicker"><strong>Date
																		of birth</strong></label> </i> <input placeholder="Select date" type="date"
																	id="datePicker" name="date_of_birth"
																	class="form-control form-control-user"
																	value="${dateOfBirth}">
															</div>
														</div>
													</div>
												</div>
												<div class="mb-3">
													<button class="btn btn-primary btn-sm" type="submit" id="main_info_btn">Save
														Settings</button>
												</div>
											</form>
										</div>
									</div>
									<div class="card shadow">
										<div class="card-header py-3">
											<p class="text-primary m-0 fw-bold">Contact Settings</p>
										</div>
										<div class="card-body">
											<form>
												<div class="mb-3">
													<label class="form-label" for="contact_email"><strong>Contact
															email</strong></label><input class="form-control" type="text"
														id="contactEmail" placeholder="" name="contact_email">
												</div>
												<div class="row">
													<div class="col">
														<div class="mb-3">
															<label class="form-label" for="city"><strong>Phone
																	number</strong></label><input class="form-control" type="text"
																id="phoneNumber" placeholder="" name="phone_number">
														</div>
													</div>
													<div class="col">
														<div class="mb-3">
															<label class="form-label" for="sec_phone_number"><strong>Phone number (2)</strong></label><input class="form-control" type="text"
																id="secPhoneNumber" placeholder=""
																name="sec_phone_number">
														</div>
													</div>
													<div class="row">
														<div class="col-4">
															<div class="mb-3 text-center">
																<label class="form-label" for="sec_phone_number"><strong><i class="fab fa-twitter"></i></strong></label><input class="form-control" type="text"
																	id="secPhoneNumber" placeholder=""
																	name="sec_phone_number">
															</div>
														</div>
														<div class="col-4">
															<div class="mb-3 text-center">
																<label class="form-label" for="sec_phone_number"><strong><i class="fab fa-facebook"></i></strong></label><input class="form-control" type="text"
																	id="secPhoneNumber" placeholder=""
																	name="sec_phone_number">
															</div>
														</div>
														<div class="col-4">
															<div class="mb-3 text-center">
																<label class="form-label" for="sec_phone_number"><strong><i class="fab fa-instagram"></i></strong></label><input class="form-control" type="text"
																	id="secPhoneNumber" placeholder=""
																	name="sec_phone_number">
															</div>
														</div>
													</div>
												</div>
												<div class="mb-3">
													<button class="btn btn-primary btn-sm" type="submit" id="contact_btn">Save&nbsp;Settings</button>
												</div>
											</form>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="card shadow mb-5">
						<div class="card-header py-3">
							<p class="text-primary m-0 fw-bold">Presentation Images</p>
						</div>
						<div class="card-body">
							<div class="row">
								<div class="col-lg-4 col-md-12">
									<div id="drop-area-1" class="drop-area">
										<form class="image_form">
											<div class="row">
												<div class="col-sm-6">
											    	<p>Drop your Image here</p>
											    	<input type="file" id="fileElem" multiple accept="image/*" onchange="handleFiles(this.files)">
											    	<input type="file" accept="image/jpg, image/png" id="image_chooser_1" class="editPhotoChooser col-12"/> 
													<label id="image_label_1" for="image_chooser_1" class="labelImage">
														Or click here to select your photo
													</label>
												</div>
												<div class="col-sm-6">
													<img 
													class="img-fluid"
													src="undefined"
													width="160" height="160" id="presentation_image_1">
												</div>
											</div>
									  	</form>
									</div>
								</div>
								
								<div class="col-lg-4 col-md-12">
									<div id="drop-area-2" class="drop-area">
										<form class="image_form">
											<div class="row">
												<div class="col-sm-6">
											    	<p>Drop your Image here</p>
											    	<input type="file" id="fileElem" multiple accept="image/*" onchange="handleFiles(this.files)">
											    	<input type="file" accept="image/jpg, image/png" id="image_chooser_2" class="editPhotoChooser col-12"/> 
													<label id="image_label_2" for="image_chooser_2" class="labelImage">
														Or click here to select your photo
													</label>
												</div>
												<div class="col-sm-6">
													<img 
													class="img-fluid"
													src="undefined"
													width="160" height="160" id="presentation_image_2">
												</div>
											</div>
									  	</form>
									</div>
								</div>
								
								<div class="col-lg-4 col-md-12">
									<div id="drop-area-3" class="drop-area">
										<form class="image_form">
											<div class="row">
												<div class="col-sm-6">
											    	<p>Drop your Image here</p>
											    	<input type="file" id="fileElem" multiple accept="image/*" onchange="handleFiles(this.files)">
											    	<input type="file" accept="image/jpg, image/png" id="image_chooser_3" class="editPhotoChooser col-12"/> 
													<label id="image_label_3" for="image_chooser_3" class="labelImage">
														Or click here to select your photo
													</label>
												</div>
												<div class="col-sm-6">
													<img 
													class="img-fluid"
													src="undefined"
													width="160" height="160" id="presentation_image_3">
												</div>
											</div>
									  	</form>
									</div>
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
	<script src="../../dashboard_assets/js/profile.js"></script>
</body>

</html>