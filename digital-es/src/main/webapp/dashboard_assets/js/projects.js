projectBtn = $("#projectBtn");

usernameSpan=$("#username_span");
 console.log(usernameSpan.textContent);

var username = $("#username");
console.log(username);


userData = {
	username: "abcd1234",
	id: 0
}

$.ajax({
		url: "/load_user_id",
		contentType: "application/json",
		data: JSON.stringify(userData),
		type: "post",
		dataType: "json",
	}).done(function(data) {
	loadProjects(data.id);
});

function loadProjects(id) {
	
	let projectId={
		id: id	
	}
	
	$.ajax({
		url: "/load_projects_id",
		contentType: "application/json",
		data: JSON.stringify(projectId),
		type: "post",
		dataType: "json",

	}).done(function(data) {
		console.log(data);
});
}



projectBtn.on("click", function(e) {
	first=$("#firstProject");
	first.append(`<div class="col-lg-4">
							<div class="card mb-3">
								<div class="card-body shadow">
								
									<div class="text-center">
										
										<div class="mb-3">
													<button class="btn btn-danger btn-sm" type="submit">DELETE</button>
												</div>
										
										<img class="img-thumbnail mb-3 mt-4"
											src="../../dashboard_assets/img/avatars/default_avatar_image.png"
											width="160" height="160">
										<div class="mb-3">
											<button class="btn btn-primary btn-sm" type="button">Change
												Photo</button>
										</div>
									</div>
									
									<div class="row">
										<div class="col-md-12">
											<label class="form-label" for="project_title"><strong>Project Title</strong></label>
											<form>
												<div class="mb-3">
													<input class="form-control" type="text"
																id="project_title" placeholder="The title of your project"
																name="project_title">
												</div>

												<div class="mb-3">
													<button class="btn btn-primary btn-sm" type="submit">Save
														Settings</button>
												</div>
											</form>
										</div>
									</div>
									
									<div class="row">
										<div class="col-md-12">
											<label class="form-label" for="project_description"><strong>Project description</strong></label>
											<form>
												<div class="mb-3">
													<textarea class="form-control" id="project_description" rows="1"
														name="project_description"></textarea>
												</div>

												<div class="mb-3">
													<button class="btn btn-primary btn-sm" type="submit">Save
														Settings</button>
												</div>
											</form>
										</div>
									</div>
									
									<div class="row">
										<div class="col-md-12">
											<label class="form-label" for="project_link"><strong>Link</strong></label>
											<form>
												<div class="mb-3">
													<input class="form-control" type="text"
																id="projectLink" placeholder="Link to your project"
																name="project_link">
												</div>

												<div class="mb-3">
													<button class="btn btn-primary btn-sm" type="submit">Save
														Settings</button>
												</div>
											</form>
										</div>
									</div>
									
								</div>
								
							</div>
						</div>`);
});