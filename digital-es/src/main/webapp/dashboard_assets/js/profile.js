
start();

function start() {
	loadMainInformations();
	loadEvents();
	editAll(true);
	refactUndefinedImage();
}
function loadEvents() {
	loadProfileImageEvent()
	for(i=1;i<=3;i++) {
		handleDragEvent(i);
		setOnChangeImage(i);
	}
	setEditAll();
	saveBiography();
	saveMainInfo();
	saveContacts();
}

function setEditAll() {
	$("#edit_all_btn").click(function(e){
			editAll(false);
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
		contacts2 ={
			facebookLinkRef:$("#facebook").val(),
			twitterLinkRef:$("#twitter").val(),
			instagramLinkRef:$("#instagram").val(),
		};
			$.ajax({
			url: "/save_contacts1",
			contentType: "application/json",
			data: JSON.stringify(contacts1),
			type: "post",
			dataType: "json",
		}).done(function(data) {
			console.log(data);
		});
			$.ajax({
			url: "/save_contacts2",
			contentType: "application/json",
			data: JSON.stringify(contacts2),
			type: "post",
			dataType: "json",
		}).done(function(data) {
			console.log(data);
		});
		contactEdit(true);
	});
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
			
			
			document.querySelector("#presentation_image_1").src=data.presentationPicture1;
			document.querySelector("#presentation_image_2").src=data.presentationPicture2;
			document.querySelector("#presentation_image_3").src=data.presentationPicture3;
			refactUndefinedImage();
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
	if(value) {
		$("#main_info_btn").hide();
		$("#contact_btn").hide();
		$("#biography_btn").hide();
		$("#skill_btn").hide();	
	}
	else {
		$("#main_info_btn").show();
		$("#contact_btn").show();
		$("#biography_btn").show();
		$("#skill_btn").show();	
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


