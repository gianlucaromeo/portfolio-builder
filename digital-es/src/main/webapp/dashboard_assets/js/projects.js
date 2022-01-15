
start();

function start() {
	setCreateProject();
	setChangeImage();
	loadProjects();
	handleDragEvent();	
	$("#project_image").hide();
}

function loadProjects() {
	
	$.ajax({
		url: "/load_projects",
		contentType: "application/json",
		type: "post",
		dataType: "json",

	}).done(function(projects) {

		if (projects.length===0) {
			console.log("user has no projects");
		} else {
			projects.forEach(project => addProject(project));

		}
});
}

function handleDragEvent() {
	let dropArea = document.getElementById('drop-area');
	
	['dragenter', 'dragover', 'dragleave', 'drop'].forEach(eventName => {
		dropArea.addEventListener(eventName, preventDefaults, false)});

	function preventDefaults (e) {
  		e.preventDefault()
  		e.stopPropagation()
		}
	
	['dragenter', 'dragover'].forEach(eventName => {
    dropArea.addEventListener(eventName, highlight, false)});

	['dragleave', 'drop'].forEach(eventName => {
    dropArea.addEventListener(eventName, unhighlight, false)});

	function highlight(e) {
  		dropArea.classList.add('highlight');
	}

	function unhighlight(e) {
  		dropArea.classList.remove('highlight');
	}


	dropArea.addEventListener('drop', handleDrop, false)

	function handleDrop(e) {
	let dt = e.dataTransfer;
  	let files = dt.files;
	console.log(files);
	handleFiles(files);
	}
	
	//files is a FileList
	function handleFiles(files) {
  		files = [...files];
 		files.forEach(uploadFile);
 		files.forEach(previewFile);
	}
	
	function uploadFile(file) {
 	//	let url = ''
  		let formData = new FormData()
		
		formData.append('file', file)

	// 	fetch(url, {
    //	method: 'POST',
    //	body: formData
  	//	})
  	//	.then(() => { /* Done. Inform the user */ })
  	//	.catch(() => { /* Error. Inform the user */ })
	}
	
	function previewFile(file) {		
	    let reader = new FileReader()
    	reader.readAsDataURL(file)
    	reader.onloadend = function() {
      		document.querySelector("#project_image").src=reader.result;
			if($("#project_image").attr("src")==="undefined"){
				$("#project_image").hide();
			}else{
				$("#project_image").show();
			}
			
		}
	}
}

function addProject(project) {
	console.log("AGGIUNGENDO PROJECT");
	console.log(project.id);
	appendProject(project);
	setEditable(project.id,true);
	setEvents(project.id);
	resetButtons(project.id,false);
}

function setEvents(projectId) {
	setOnDelete(projectId);
	setOnEdit(projectId);
	setOnConfirmEdit(projectId);
	setOnChangeImage(projectId);
	setOnDiscard(projectId);
}

function setOnDelete(id) {
	$("#confirmDelete"+id).click(function(e) {
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
}

function setOnConfirmEdit(id) {
	
	$("#confirm"+id).click(function(e) {
		selectedProject={
			id:id,
			userId:-1,
			title:$("#project_title"+id).val(),
			description:$("#project_description"+id).val(),
			picture:$("#project_image"+id).attr("src"),
			linkRef:$("#project_link"+id).val()
		};
		e.preventDefault();
		$.ajax({
			url: "/edit_project",
			contentType: "application/json",
			data: JSON.stringify(selectedProject),
			type: "post",
			dataType: "json",
		}).done(function(data) {
			if(data==="error"){
				
			}else{
				editValidation(data);
			}
		});
		
	});
}

function editValidation(data) {
	console.log("START EDIT VALIDATION; VALIDATING THIS DATA:");
	console.log(data);
	
	$("#project_title"+data.id).removeClass("is-invalid");
	$("#project_description"+data.id).removeClass("is-invalid");
	$("#project_link"+data.id).removeClass("is-invalid");
	var isValidProject = true;
	if (data.title === "project_field_empty" || data.title === "project_title_not_valid") {
		$("#project_title"+data.id).addClass("is-invalid");
		isValidProject = false;
	} if (data.description === "project_field_empty") {
		$("#project_description"+data.id).addClass("is-invalid");
		isValidProject = false;
	} if (data.linkRef === "project_field_empty") {
		$("#project_link"+data.id).addClass("is-invalid");
		isValidProject = false;
	}
	if (isValidProject) {
				restore(data.id,data);
				setEditable(data.id,true);
				resetButtons(data.id,false);
				
				$("#popupSaveBtn" + data.id).trigger('click');

				$("#closeSavePopupBtn" + data.id).click(function() {
					restore(data.id,data);
					setEditable(data.id,true);
					resetButtons(data.id,false);
				});
	}
}

function setOnDiscard(id) {
	$("#discard"+id).click(function(e) {
		e.preventDefault();
		console.log(id);
		$.ajax({
			url: "/restore_project",
			contentType: "application/json",
			data: JSON.stringify(id),
			type: "post",
			dataType: "json",
		}).done(function(data) {
			restore(id,data);
			setEditable(id,true);
			resetButtons(id,false);
			$("#project_title"+id).removeClass("is-invalid");
			$("#project_description"+id).removeClass("is-invalid");
			$("#project_link"+id).removeClass("is-invalid");
		});
	});
}

function setOnEdit(id) {
	$("#edit"+id).click(function(e){
			setEditable(id,false);
			resetButtons(id,true);
	});

}

function setEditable(id,bool) {
	$("#project_title"+id).attr('readonly',bool);
	$("#project_description"+id).attr('readonly',bool);	
	$("#project_link"+id).attr('readonly',bool);	
}

function appendProject(project) {
	
	let id = project.id;
	let title = project.title;
	let description = project.description;
	let imagePath = project.picture;
	let linkRef = project.linkRef;
	
	first=$("#firstProject");
	first.append(`<div class="col-lg-6" id="project${id}">
							<div class="card mb-3">
								<div class="card-body shadow" id="project_container">
									<div class="text-center">
										<img 
											src="${imagePath}"
											width="160" height="160" id="project_image${id}">
										<div class="row" style="margin-top: 20px;"id="divNewImage${id}">
											<div class="text-center"> 
											<input type="file" accept="image/jpg, image/png" id="image_chooser${id}" class="editPhotoChooser col-12"/> 
											<label id="image_label${id}" for="image_chooser${id}" class="labelImage">
												Change Photo
											</label>
											</div>
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
											</form>
										</div>
									</div>
									
									<div class="row">
										<div class="col-md-12">
											<label class="form-label" for="project_description"><strong>Project description</strong></label>
											<form>
												<div class="mb-3">
													<textarea class="form-control" id="project_description${id}" rows="4" maxlength="250"  style="resize: none;"
														name="project_description">${description}</textarea>
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
																id="project_link${id}" placeholder="Link to your project"
																name="project_link" value="${linkRef}">
												</div>
											</form>
										</div>
										<div class="row text-center">
											<div class="col mb-3">
												<button id="edit${id}" class="btn btn-primary btn-sm" type="submit">EDIT</button>
											</div>
											<div class="col mb-3">
												<button id="confirm${id}" class="btn btn-primary btn-sm" type="submit">CONFIRM</button>
											</div>
											`+ getPopUpOnSave(id) + `
											<div class="col mb-3">
												<button id="discard${id}" class="btn btn-danger btn-sm" type="submit">UNDO</button>
											</div>
											<div class="col mb-3">
												<button id="delete${id}" class="btn btn-danger btn-sm" type="submit"
												data-bs-toggle="modal" data-bs-target="#modalId${id}">DELETE</button>
											</div>
											`+ getPopUpOnDelete(id) + `
										</div>
									</div>
								</div>
							</div>
						</div>`);
}

function setCreateProject() {
	projectBtn = $("#projectBtn");
	
	projectBtn.click(function(e){
		e.preventDefault();
		
		var newProject={
		id:-1,
		userId:-1,
		title:$("#project_title").val(),
		description:$("#project_description").val(),
		picture:$("#project_image").attr("src"),
		linkRef:$("#project_link").val()
		};
		
		$.ajax({
		url: "/add_project",
		contentType: "application/json",
		data: JSON.stringify(newProject),
		type: "post",
		dataType: "json",
		}).done(function(project) {
			if(project==="error"){
				
			}else{
				createValidation(project);
			}
		});
	});
}

function createValidation(data) {
	console.log("START VALIDATION; VALIDATING THIS DATA:"+ data.id);
	
	$("#project_title").removeClass("is-invalid");
	$("#project_description").removeClass("is-invalid");
	$("#project_link").removeClass("is-invalid");
	$("#drop-area").removeClass("is-invalid");
	var isValidProject = true;
	if (data.title === "project_field_empty" || data.title === "project_title_not_valid") {
		$("#project_title").addClass("is-invalid");
		isValidProject = false;
	} if (data.description === "project_field_empty") {
		$("#project_description").addClass("is-invalid");
		isValidProject = false;
	} if (data.linkRef === "project_field_empty") {
		$("#project_link").addClass("is-invalid");
		isValidProject = false;
	} if (data.picture === "undefined") {
		$("#drop-area").addClass("is-invalid");
		isValidProject = false;
	}
	if (isValidProject) {
			addProject(data);
			resetTemplate();
			$("#popupNewBtn").trigger('click');
				
			$("#closeNewPopupBtn").click(function() {
			});
	}
}

function setChangeImage() {
	const image_chooser=document.querySelector("#image_chooser");
	var newImage="";
	image_chooser.addEventListener("change", function(){
		const reader= new FileReader();
		reader.addEventListener("load", ()=> {
			newImage=reader.result;
			document.querySelector("#project_image").src=newImage;	
			if($("#project_image").attr("src")==="undefined"){
				$("#project_image").hide();
			}else{
				$("#project_image").show();
			}
		});
		reader.readAsDataURL(this.files[0]);
	});
}

function setOnChangeImage(id) {
	const image_chooser=document.querySelector("#image_chooser"+id);
	var newImage="";
	image_chooser.addEventListener("change", function(){
		const reader= new FileReader();
		reader.addEventListener("load", ()=> {
			newImage=reader.result;
			document.querySelector("#project_image"+id).src=newImage;	
		});
		reader.readAsDataURL(this.files[0]);
	});
}

function resetTemplate() {
	$("#project_title").val("");
	$("#project_description").val("");
	$("#project_image").attr("src","undefined");
	$("#project_link").val("");
	$("#project_image").hide();
}

function resetButtons(id,value) {
	$("#edit"+id).hide();
	$("#delete"+id).hide();
	$("#confirm"+id).hide();
	$("#discard"+id).hide();
	$("#image_label"+id).hide();
	if(value===true) {
		$("#delete"+id).show();
		$("#confirm"+id).show();
		$("#discard"+id).show();
		$("#image_label"+id).show();
	}
	else {
		$("#edit"+id).show();
		$("#delete"+id).show();
	}
}

function restore(id,project) {
			$("#project_title"+id).val(project.title);
			$("#project_description"+id).val(project.description);
			$("#project_image"+id).attr("src",project.picture);
			$("#project_link"+id).val(project.linkRef);
}

function getPopUpOnSave(id) {
	var saveButton = `<button type="button" class="btn btn-primary btn-sm"
							data-bs-toggle="modal" data-bs-target="#modalIdSavePopup${id}"
							id="popupSaveBtn${id}" hidden></button>
						<!-- Modal -->
						<div class="modal fade" id="modalIdSavePopup${id}" tabindex="-1"
							aria-labelledby="modalLabel" aria-hidden="true">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<h5 class="modal-title" id="modalLabel">Update</h5>
										<button type="button" class="btn-close" data-bs-dismiss="modal"
											aria-label="Close"></button>
									</div>
									<div class="modal-body">Project successfully updated</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-primary"
											data-bs-dismiss="modal" id="closeSavePopupBtn${id}">Ok</button>
									</div>
								</div>
							</div>
						</div>`;
	return saveButton;
}

function getPopUpOnDelete(id) {
	var deleteButton = `<!-- Modal -->
						<div class="modal fade" id="modalId${id}" tabindex="-1"
							aria-labelledby="modalLabel" aria-hidden="true">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<h5 class="modal-title" id="modalLabel">Delete</h5>
										<button type="button" class="btn-close" data-bs-dismiss="modal"
											aria-label="Close"></button>
									</div>
									<div class="modal-body">Are you sure you want to delete this project?</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-secondary"
											data-bs-dismiss="modal"">Close</button>
										<button type="button" class="btn btn-danger" id="confirmDelete${id}"
											data-bs-dismiss="modal">Delete</button>
									</div>
								</div>
							</div>
						</div>`;
	return deleteButton;
}
