projectBtn = $("#projectBtn");

projectBtn.click(appendTemplate());

function loadProjects() {
	
	$.ajax({
		url: "/load_projects",
		contentType: "application/json",
		type: "post",
		dataType: "json",

	}).done(function(projects) {
		console.log(projects);

		if (projects.length===0) {
			console.log("user has no projects");
		} else {
			projects.forEach(project => addProject(project));
			projects.forEach(project => console.log(project));

		}
});
}

loadProjects();

function addProject(project) {
	appendProject(project);
	setEvents(project.id);
};

function setEvents(projectId) {
	setOnDelete(projectId);
	
};

function setOnDelete(id) {
	$("#delete"+id).click(function(e) {
		e.preventDefault();
		console.log(id);
		$.ajax({
			url: "/delete_project",
			contentType: "application/json",
			data: JSON.stringify(id),
			type: "post",
			dataType: "json",
		}).done(function() {

		});

		$("#project"+id).remove();

	});
};

function appendProject(project) {
	
	let id = project.id;
	let title = project.title;
	let description = project.description;
	let imagePath = project.picture;
	let linkRef = project.linkRef;
	
	first=$("#firstProject");
	first.append(`<div class="col-lg-4" id="project${id}">
							<div class="card mb-3">
								<div class="card-body shadow">
								
									<div class="text-center">
										
										<div class="mb-3">
													<button class="btn btn-danger btn-sm" type="submit" id="delete${id}">DELETE</button>
												</div>
										
										<img class="img-thumbnail mb-3 mt-4"
											src="${imagePath}"
											width="160" height="160">
										<div class="mb-3">
											<button class="btn btn-primary btn-sm" type="button" id="changePhoto${id}">Change
												Photo</button>
										</div>
									</div>
									
									<div class="row">
										<div class="col-md-12">
											<label class="form-label" for="project_title"><strong>Project Title</strong></label>
											<form>
												<div class="mb-3">
													<input class="form-control" type="text"
																id="project_title${id}" placeholder="The title of your project"
																name="project_title" value="${title}">
												</div>

												<div class="mb-3">
													<button class="btn btn-primary btn-sm" type="submit" id="changeTitle${id}">Save
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
													<textarea class="form-control" id="project_description${id}" rows="1"
														name="project_description">${description}</textarea>
												</div>

												<div class="mb-3">
													<button class="btn btn-primary btn-sm" type="submit" id="changeDescription${id}">Save
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
																id="projectLink${id}" placeholder="Link to your project"
																name="project_link" value="${linkRef}">
												</div>

												<div class="mb-3">
													<button class="btn btn-primary btn-sm" type="submit" id="changeLink${id}">Save
														Settings</button>
												</div>
											</form>
										</div>
									</div>
									
								</div>
								
							</div>
						</div>`);
};

function appendTemplate() {
	first=$("#firstProject");
	first.append(`<div class="col-lg-4" id="project">
							<div class="card mb-3">
								<div class="card-body shadow">
								
									<div class="text-center">
										
										<div class="mb-3">
													<button class="btn btn-danger btn-sm" type="submit" id="delete">DELETE</button>
												</div>
										
										<img class="img-thumbnail mb-3 mt-4"
											src=""
											width="160" height="160">
										<div class="mb-3">
											<button class="btn btn-primary btn-sm" type="button" id="changePhoto">Change
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
																name="project_title" value="">
												</div>

												<div class="mb-3">
													<button class="btn btn-primary btn-sm" type="submit" id="changeTitle">Save
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
													<button class="btn btn-primary btn-sm" type="submit" id="changeDescription">Save
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
																name="project_link" value="">
												</div>

												<div class="mb-3">
													<button class="btn btn-primary btn-sm" type="submit" id="changeLink">Save
														Settings</button>
												</div>
											</form>
										</div>
									</div>
									
								</div>
								
							</div>
						</div>`);
}
