
start();

function start() {
	loadMainInformations();
	loadEvents();
	editAll(true);
	loadCurriculumSkills();
	refactUndefinedImage();
	$("#editUserSettings").hide(); //I don't think they should be modified
}
function loadEvents() {
	loadProfileImageEvent()
	for(i=1;i<=3;i++) {
		handleDragEvent(i);
		setOnChangeImage(i);
	}
	setEditAll();
	setUserSettingsEdit();
	setConctactSettingsEdit();
	setMainSkillEdit();
	setSkillsEdit();
	saveBiography();
	saveMainInfo();
	saveContacts();
	saveMainSkills();
	
	setCreateSkill();
}

function setEditAll() {
	$("#edit_all_btn").click(function(e){
		e.preventDefault();
		if($("#edit_all_btn").text()=="Edit your profile") {
			editBioArea(false);
			$("#edit_all_btn").text("Undo");
			return;
		}
		if($("#edit_all_btn").text()=="Undo") {
			editBioArea(true);
			$("#edit_all_btn").text("Edit your profile");
			loadMainInformations();
			return;
		}
//		editAll(false);
	});
}

function setUserSettingsEdit() {
	$("#editUserSettings").click(function(e){
		e.preventDefault();
		mainInfoEdit(false);
	});
}
function setConctactSettingsEdit() {
	$("#editContactSettings").click(function(e){
		e.preventDefault();
		if($("#editContactSettings").text()=="Edit Contact Settings") {
			contactEdit(false);
			$("#editContactSettings").text("Undo");
			return;
		}
		if($("#editContactSettings").text()=="Undo") {
			contactEdit(true);
			$("#editContactSettings").text("Edit Contact Settings");
			
			$("#contactEmail").removeClass("is-invalid");
			$("#phoneNumber").removeClass("is-invalid");
			$("#secPhoneNumber").removeClass("is-invalid");
			$("#facebook").removeClass("is-invalid");
			$("#twitter").removeClass("is-invalid");
			$("#instagram").removeClass("is-invalid");
			
			loadMainInformations();

			return;
		}
	});
}
function setMainSkillEdit() {
	$("#editMainSkill").click(function(e){
		e.preventDefault();
		if($("#editMainSkill").text()=="Edit Skills") {
			mainSkillEdit(false);
			$("#editMainSkill").text("Undo")
			return;
		}
		if($("#editMainSkill").text()=="Undo") {
			$("#editMainSkill").text("Edit Skills")
			loadMainInformations();
			mainSkillEdit(true);
			return;
		}

	});
}
function setSkillsEdit() {
	$("#editSkills").click(function(e){
		e.preventDefault();
		if($("#editSkills").text()=="Edit Skills") {
			skillsEdit(false);
			$("#editSkills").text("Undo");
			return;
		}
		if($("#editSkills").text()=="Undo") {
			$("#editSkills").text("Edit Skills");
			$("#firstSkill").removeClass("is-invalid");
			$("#firstLevel").removeClass("is-invalid");
			skillsEdit(true);
			return;
		}
	});
}
function saveBiography() {
		$("#biography_btn").click(function(e){
		e.preventDefault();
		biography ={
			bio:$("#biography").val(),
		};
		console.log(biography.bio);
			$.ajax({
			url: "/save_bio",
			contentType: "application/json",
			data: JSON.stringify(biography),
			type: "post",
			dataType: "json",
		}).done(function(data) {
			console.log(data);
		});
		bioEdit(true);
		$("#edit_all_btn").text("Edit your profile")
	});
}

function saveMainInfo() {
		$("#main_info_btn").click(function(e){
		e.preventDefault();
		mainInfo ={
			username:$("#username").val(),
			email:$("#email").val(),
			firstName:$("#firstName").val(),
			lastName:$("#lastName").val(),
			dateOfBirth:$("#datePicker").val(),
		};
			$.ajax({
			url: "/save_main_info",
			contentType: "application/json",
			data: JSON.stringify(mainInfo),
			type: "post",
			dataType: "json",
		}).done(function(data) {
			mainInfoValidation(data);
		});
	});
}

function mainInfoValidation(data) {
	console.log("START VALIDATION; VALIDATING THIS DATA:");
	console.log(data);
	
	$("#username").removeClass("is-invalid");
	$("#email").removeClass("is-invalid");
	$("#firstName").removeClass("is-invalid");
	$("#lastName").removeClass("is-invalid");
	$("#datePicker").removeClass("is-invalid");
	var isValid = true;
	if (data.username === "error") {
		$("#username").addClass("is-invalid");
		isValid = false;
	} if (data.email === "error") {
		$("#email").addClass("is-invalid");
		isValid = false;
	} if (data.firstName === "error") {
		$("#firstName").addClass("is-invalid");
		isValid = false;
	}  if (data.lastName === "error") {
		$("#lastName").addClass("is-invalid");
		isValid = false;
	}  if (data.dateOfBirth === "error") {
		$("#datePicker").addClass("is-invalid");
		isValid = false;
	}
	if (isValid) {
				mainInfoEdit(true);
	}
}

function saveContacts() {
		$("#contact_btn").click(function(e){
		e.preventDefault();
		contacts1 ={
			contactEmail:$("#contactEmail").val(),
			mainPhoneNumber:$("#phoneNumber").val(),
			secondaryPhoneNumber:$("#secPhoneNumber").val(),
		};
			$.ajax({
			url: "/save_contacts1",
			contentType: "application/json",
			data: JSON.stringify(contacts1),
			type: "post",
			dataType: "json",
		}).done(function(data) {
			saveContacts2(contactValidation1(data));
		});
	});
}

function saveContacts2(validated1) {
		contacts2 ={
			facebookLinkRef:$("#facebook").val(),
			twitterLinkRef:$("#twitter").val(),
			instagramLinkRef:$("#instagram").val(),
		};
			$.ajax({
			url: "/save_contacts2",
			contentType: "application/json",
			data: JSON.stringify(contacts2),
			type: "post",
			dataType: "json",
		}).done(function(data) {
			validated2=contactValidation2(data);
			if(validated1&&validated2) {
			contactEdit(true);
			$("#editContactSettings").text("Edit Contact Settings");
			console.log("VALID DATA");
			}
		});
}

function contactValidation1(data) {
	console.log("START VALIDATION; VALIDATING THIS DATA:");
	console.log(data);
	
	$("#contactEmail").removeClass("is-invalid");
	$("#phoneNumber").removeClass("is-invalid");
	$("#secPhoneNumber").removeClass("is-invalid");
	var isValid = true;
	if (data.contactEmail === "error") {
		$("#contactEmail").addClass("is-invalid");
		isValid = false;
	} if (data.mainPhoneNumber === "error") {
		$("#phoneNumber").addClass("is-invalid");
		isValid = false;
	} if (data.secondaryPhoneNumber === "error") {
		$("#secPhoneNumber").addClass("is-invalid");
		isValid = false;
	} 
	if (isValid) {
				return true;
	}
	return false;
}

function contactValidation2(data) {
	console.log("START VALIDATION; VALIDATING THIS DATA:");
	console.log(data);
	
	$("#facebook").removeClass("is-invalid");
	$("#twitter").removeClass("is-invalid");
	$("#instagram").removeClass("is-invalid");
	var isValid = true;
	if (data.facebookLinkRef === "error") {
		$("#facebook").addClass("is-invalid");
		isValid = false;
	} if (data.twitterLinkRef === "error") {
		$("#twitter").addClass("is-invalid");
		isValid = false;
	} if (data.instagramLinkRef === "error") {
		$("#instagram").addClass("is-invalid");
		isValid = false;
	} 
	if (isValid) {
				return true;
	}
	return false;
}

function saveMainSkills() {
		$("#main_skill_btn").click(function(e){
		e.preventDefault();
		mainSkills ={
			specialSkillName1:$("#mainSkill1").val(),
			specialSkillDescr1:$("#mainSkillDescr1").val(),
			specialSkillName2:$("#mainSkill2").val(),
			specialSkillDescr2:$("#mainSkillDescr2").val(),
			specialSkillName3:$("#mainSkill3").val(),
			specialSkillDescr3:$("#mainSkillDescr3").val(),
		};
		console.log(mainSkills);
			$.ajax({
			url: "/save_main_skills",
			contentType: "application/json",
			data: JSON.stringify(mainSkills),
			type: "post",
			dataType: "json",
		}).done(function(data) {
			console.log(data);
			mainSkillEdit(true);
			$("#editMainSkill").text("Edit Skills")
		});
	});
}

function addSkill(skill) {
		appendSkill(skill);
		setSkillEditable(skill.id,true);
		setSkillEvents(skill.id);
		resetSkillButtons(skill.id,false);		
}

function appendSkill(skill) {
	let id = skill.id;
	let title = skill.title;
	let level = skill.level;
	
	start=$("#skillStart");
	start.append(`<div class="row" id="skillContainer${id}">
										<div class="col-8 mb-3">
											<div class="row">
												<h4 class="small fw-bold">Skill</h4>
											</div>
											<input class="form-control" type="text" id="skill${id}"
												placeholder="e.g.: Excel" name="skill${id}" value="${title}">
										</div>
										<div class="col-4 mb-3">
											<div class="row">
												<h4 class="small fw-bold">(0-100)</h4>
											</div>
											<input class="form-control" type="number" id="level${id}"
												placeholder="e.g.: 85" name="level${id}" value="${level}"
												min="0" step="5" max="100">
										</div>
									</div>

									<div class="progress progress-sm mb-3" id="progress${id}">
										<div class="progress-bar bg-success" aria-valuenow="${level}"
											aria-valuemin="0" aria-valuemax="100" style="width: ${level}%;">
											<span class="visually-hidden">${level}%</span>
										</div>
									</div>

									<div class="col mb-3" id="skillButtons${id}">
										<button class="btn btn-primary btn-sm" type="submit"
													id="editSkill${id}">Edit</button>
										<button class="btn btn-primary btn-sm" type="submit"
											id="confirmEditSkill${id}">Confirm</button>
										<button class="btn btn-danger btn-sm" type="submit"
											id="removeSkill${id}">Remove</button>
									</div>
									<hr>`);
}

function setSkillEditable(id,value) {
	$("#skill"+id).attr('readonly',value);
	$("#level"+id).attr('readonly',value);
}

function setSkillEvents(id) {
	$("#editSkill"+id).click(function(e){
		e.preventDefault();
		setSkillEditable(id,false);
		resetSkillButtons(id,true);
	});
	
	$("#confirmEditSkill"+id).click(function(e){
		e.preventDefault();
		selectedSkill={
			id:id,
			userId:-1,
			title:$("#skill"+id).val(),
			level:$("#level"+id).val()
		};
		$.ajax({
			url: "/edit_skill",
			contentType: "application/json",
			data: JSON.stringify(selectedSkill),
			type: "post",
			dataType: "json",
		}).done(function(data) {
			if(data==="error"){
				
			}else{
				editSkillValidation(data);
			}
		});
	});
	
	$("#removeSkill"+id).click(function(e){
		e.preventDefault();
		console.log(id);
		$.ajax({
			url: "/remove_skill",
			contentType: "application/json",
			data: JSON.stringify(id),
			type: "post",
			dataType: "json",
		}).done(function() {

		});

		$("#skillContainer"+id).remove();
		$("#skillButtons"+id).remove();
		$("#progress"+id).remove();
	});
}

function setCreateSkill() {
	skillBtn = $("#addSkillBtn");
	
	skillBtn.click(function(e){
		e.preventDefault();
		
		newSkill={
		id:-1,
		userId:-1,
		title:$("#firstSkill").val(),
		level:$("#firstLevel").val()
		};
		
		if(newSkill.level=="") {
			newSkill.level=-1;
		}
		$.ajax({
		url: "/add_skill",
		contentType: "application/json",
		data: JSON.stringify(newSkill),
		type: "post",
		dataType: "json",
		}).done(function(data) {
			if(data==="error"){
				
			}else{
				skillValidation(data);
			}
		});
	});
}

function skillValidation(data) {
	console.log("START VALIDATION; VALIDATING THIS DATA:");
	console.log(data);
	
	$("#firstSkill").removeClass("is-invalid");
	$("#firstLevel").removeClass("is-invalid");
	var isValidSkill = true;
	if (data.title === "error") {
		$("#firstSkill").addClass("is-invalid");
		isValidSkill = false;
	} if (data.level === -1) {
		$("#firstLevel").addClass("is-invalid");
		isValidSkill = false;
	}
	if (isValidSkill) {
				addSkill(data);
				resetSkillTemplate();
	}
}

function editSkillValidation(data) {
	console.log("START VALIDATION; VALIDATING THIS DATA:");
	console.log(data);
	
	$("#skill"+data.id).removeClass("is-invalid");
	$("#level"+data.id).removeClass("is-invalid");
	var isValidSkill = true;
	if (data.title === "error") {
		$("#skill"+data.id).addClass("is-invalid");
		isValidSkill = false;
	} if (data.level === -1) {
		$("#level"+data.id).addClass("is-invalid");
		isValidSkill = false;
	}
	if (isValidSkill) {
		refactSkill(data.id,data);
		setSkillEditable(data.id,true);
		resetSkillButtons(data.id,false);
	}
}

function refactSkill(id,data) {
	$("#skill"+id).val(data.title);
	$("#level"+id).val(data.level);
}

function resetSkillTemplate() {
	$("#firstSkill").val("");
	$("#firstLevel").val("");
}

function loadProfileImageEvent() {
	const image_chooser=document.querySelector("#image_chooser_profile");
	var newImage="";
	image_chooser.addEventListener("change", function(){
		const reader= new FileReader();
		reader.addEventListener("load", ()=> {
			newImage=reader.result;
			document.querySelector("#profile_picture").src=newImage;	
			saveImages();
		});
		reader.readAsDataURL(this.files[0]);
	});
}

function handleDragEvent(id) {
	let dropArea = document.getElementById('drop-area-'+id);
	
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
      		document.querySelector("#presentation_image_"+id).src=reader.result;
			saveImages();
		}
	}
}


function setOnChangeImage(id) {
	const image_chooser=document.querySelector("#image_chooser_"+id);
	var newImage="";
	image_chooser.addEventListener("change", function(){
		const reader= new FileReader();
		reader.addEventListener("load", ()=> {
			newImage=reader.result;
			document.querySelector("#presentation_image_"+id).src=newImage;	
			saveImages();
		});
		reader.readAsDataURL(this.files[0]);
		
	});
}

function saveImages() {
	console.log(newImages);
	var newImages={
			profilePicture:$("#profile_picture").attr("src"),
			presentationPicture1:$("#presentation_image_1").attr("src"),
			presentationPicture2:$("#presentation_image_2").attr("src"),
			presentationPicture3:$("#presentation_image_3").attr("src"),
		};
		
		console.log(newImages);
		$.ajax({
			url: "/save_presentation_image",
			contentType: "application/json",
			data: JSON.stringify(newImages),
			type: "post",
			dataType: "json",
		});
	refactUndefinedImage();
}

function loadMainInformations() {
	
	$("#firstSkill").val("");
	$("#firstLevel").val("");
	
	var mainInformations ={
		 userId:-1,
		 profilePicture:"",
	     logoPicture:"",
	     logoName:"",
	     bio:"",
	     presentationPicture1:"",
	     presentationPicture2:"",
	     presentationPicture3:"",
	     specialSkillName1:"",
	     specialSkillName2:"",
	     specialSkillName3:"",
	     specialSkillDescr1:"",
	     specialSkillDescr2:"",
	     specialSkillDescr3:"",
	     facebookLinkRef:"",
	     instagramLinkRef:"",
         twitterLinkRef:"",
	}
	$.ajax({
			url: "/get_main_info",
			contentType: "application/json",
			data: JSON.stringify(mainInformations),
			type: "post",
			dataType: "json",
		}).done(function(data) {
			console.log(data);
			$("#biography").val(data.bio);
			
			
			$("#mainSkill1").val(data.specialSkillName1);
			$("#mainSkillDescr1").val(data.specialSkillDescr1);
			$("#mainSkill2").val(data.specialSkillName2);
			$("#mainSkillDescr2").val(data.specialSkillDescr2);
			$("#mainSkill3").val(data.specialSkillName3);
			$("#mainSkillDescr3").val(data.specialSkillDescr3);
			
			$("#facebook").val(data.facebookLinkRef);
			$("#instagram").val(data.instagramLinkRef);
			$("#twitter").val(data.twitterLinkRef);
			
			document.querySelector("#presentation_image_1").src=data.presentationPicture1;
			document.querySelector("#presentation_image_2").src=data.presentationPicture2;
			document.querySelector("#presentation_image_3").src=data.presentationPicture3;
			refactUndefinedImage();
		});	
		
		contacts ={
			contactEmail:"",
			mainPhoneNumber:"",
			secondaryPhoneNumber:""
		};
			$.ajax({
			url: "/get_main_info_user",
			contentType: "application/json",
			data: JSON.stringify(contacts),
			type: "post",
			dataType: "json",
		}).done(function(data) {
			console.log(data);
			
			
			$("#contactEmail").val(data.contactEmail);
			$("#phoneNumber").val(data.mainPhoneNumber);
			$("#secPhoneNumber").val(data.secondaryPhoneNumber);

		});	

}

function loadCurriculumSkills() {
	$.ajax({
		url: "/load_skills",
		contentType: "application/json",
		type: "post",
		dataType: "json",
	}).done(function(skills) {
		console.log(skills);

		if (skills.length===0) {
			console.log("user has no skills");
		} else {
			skills.forEach(skill => addSkill(skill));
		}
	});
}

function editAll(value) {
	$("#biography").attr('readonly',value);	
	$("#username").attr('readonly',value);	
	$("#email").attr('readonly',value);	
	$("#firstName").attr('readonly',value);	
	$("#lastName").attr('readonly',value);	
	$("#datePicker").attr('readonly',value);	
	$("#contactEmail").attr('readonly',value);	
	$("#phoneNumber").attr('readonly',value);	
	$("#secPhoneNumber").attr('readonly',value);
	$("#twitter").attr('readonly',value);	
	$("#facebook").attr('readonly',value);	
	$("#instagram").attr('readonly',value);	
	$("#mainSkill1").attr('readonly',value);
	$("#mainSkillDescr1").attr('readonly',value);	
	$("#mainSkill2").attr('readonly',value);
	$("#mainSkillDescr2").attr('readonly',value);	
	$("#mainSkill3").attr('readonly',value);
	$("#mainSkillDescr3").attr('readonly',value);	
	$("#firstSkill").attr('readonly',value);
	$("#firstLevel").attr('readonly',value);	
	if(value) {
		$("#main_info_btn").hide();
		$("#contact_btn").hide();
		$("#biography_btn").hide();
		$("#main_skill_btn").hide();	
		$("#addSkillBtn").hide();
		$("#image_chooser_profile").hide();
		$("#image_label_profile").hide();
	}
	else {
		$("#main_info_btn").show();
		$("#contact_btn").show();
		$("#biography_btn").show();
		$("#main_skill_btn").show();	
		$("#addSkillBtn").show();
		$("#image_label_profile").show();
	}
}

function editBioArea(value) {
	$("#biography").attr('readonly',value);	
	if(value) {
		$("#biography_btn").hide();
		$("#image_chooser_profile").hide();
		$("#image_label_profile").hide();
	}
	else {
		$("#biography_btn").show();
		$("#image_label_profile").show();
	}
}

function refactUndefinedImage(){
	if($("#presentation_image_1").attr("src")==="undefined"){
		$("#presentation_image_1").hide();
	}else{
		$("#presentation_image_1").show();
	}
	if($("#presentation_image_2").attr("src")==="undefined"){
		$("#presentation_image_2").hide();
	}else{
		$("#presentation_image_2").show();
	}
	if($("#presentation_image_3").attr("src")==="undefined"){
		$("#presentation_image_3").hide();
	}else{
		$("#presentation_image_3").show();
	}
	
			
}

function contactEdit(value) {
	$("#contactEmail").attr('readonly',value);	
	$("#phoneNumber").attr('readonly',value);	
	$("#secPhoneNumber").attr('readonly',value);
	$("#twitter").attr('readonly',value);	
	$("#facebook").attr('readonly',value);	
	$("#instagram").attr('readonly',value);	
	if(value) {
		$("#contact_btn").hide();
	}
	else {
		$("#contact_btn").show();
	}
}

function mainInfoEdit(value) {
	$("#username").attr('readonly',value);	
	$("#email").attr('readonly',value);	
	$("#firstName").attr('readonly',value);	
	$("#lastName").attr('readonly',value);	
	$("#datePicker").attr('readonly',value);	
	if(value) {
		$("#main_info_btn").hide();
	}
	else {
		$("#main_info_btn").show();
	}
}

function bioEdit(value) {
	$("#biography").attr('readonly',value);	
	if(value) {
		$("#biography_btn").hide();
	}
	else {
		$("#biography_btn").show();
	}
}

function mainSkillEdit(value) {
	$("#mainSkill1").attr('readonly',value);
	$("#mainSkillDescr1").attr('readonly',value);	
	$("#mainSkill2").attr('readonly',value);
	$("#mainSkillDescr2").attr('readonly',value);	
	$("#mainSkill3").attr('readonly',value);
	$("#mainSkillDescr3").attr('readonly',value);	
	if(value) {
		$("#main_skill_btn").hide();
	}
	else {
		$("#main_skill_btn").show();
	}
}

function skillsEdit(value) {
	$("#firstSkill").attr('readonly',value);
	$("#firstLevel").attr('readonly',value);	
	if(value) {
		$("#addSkillBtn").hide();
	}
	else {
		$("#addSkillBtn").show();
	}
}

function resetSkillButtons(id,value) {
	$("#editSkill"+id).hide();
	$("#removeSkill"+id).hide();
	$("#confirmEditSkill"+id).hide();
	if(value===true) {
		$("#confirmEditSkill"+id).show();
		$("#removeSkill"+id).show();
	}
	else {
		$("#editSkill"+id).show();
		$("#removeSkill"+id).show();
	}
}




