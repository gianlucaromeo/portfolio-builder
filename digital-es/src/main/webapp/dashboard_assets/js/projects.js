
setCreateProject();
setChangeImage();
loadProjects();
handleDragEvent();

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
 		let url = 'YOUR URL HERE'
  		let formData = new FormData()
		
		formData.append('file', file)

	 	fetch(url, {
    	method: 'POST',
    	body: formData
  		})
  		.then(() => { /* Done. Inform the user */ })
  		.catch(() => { /* Error. Inform the user */ })
	}
	
	function previewFile(file) {
		
	    let reader = new FileReader()
    	reader.readAsDataURL(file)
    	reader.onloadend = function() {
      		document.querySelector("#project_image").src=reader.result;
		}
	}
}

function addProject(project) {
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
}

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
}

function setOnConfirmEdit(id) {
	var canvas = document.createElement("canvas");
	context = canvas.getContext('2d');
	base_image = new Image();
	base_image.src = $("#project_image"+id).src;
	base_image.onload = function() {
		context.drawImage(base_image, 100, 100);
	}

	console.log(base_image);
		
	var image64 =canvas.toDataURL();
	console.log(image64);
	
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
				$("#project" + data.id).remove()
				addProject(data);
			}
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
	first.append(`<div class="col-lg-4" id="project${id}">
							<div class="card mb-3">
								<div class="card-body shadow">
									<div class="text-center">
										<img 
											src="undefined"
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
													<textarea class="form-control" id="project_description${id}" rows="1"
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
											<div class="col mb-3">
												<button id="delete${id}" class="btn btn-danger btn-sm" type="submit">DELETE</button>
											</div>
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
		
		var canvas = document.createElement("canvas");
		context = canvas.getContext('2d');

		base_image = new Image();
		base_image.src = $("#project_image").attr("src");
		base_image.onload = function() {
			context.drawImage(base_image, 100, 100);
		}
		console.log(base_image);		
		
		var image64 =canvas.toDataURL();
		
		newProject={
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
				appendProject(project);
				setEditable(project.id,true);
				setEvents(project.id);
				resetTemplate();
			}
		});
	});
}

function setChangeImage() {
	const image_chooser=document.querySelector("#image_chooser");
	var newImage="";
	image_chooser.addEventListener("change", function(){
		const reader= new FileReader();
		reader.addEventListener("load", ()=> {
			newImage=reader.result;
			document.querySelector("#project_image").src=newImage;	
			
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
}

function resetButtons(id,value) {
	$("#edit"+id).hide();
	$("#delete"+id).hide();
	$("#confirm"+id).hide();
	if(value===true) {
		$("#delete"+id).show();
		$("#confirm"+id).show();
	}
	else {
		$("#edit"+id).show();
		$("#delete"+id).show();
	}
}
