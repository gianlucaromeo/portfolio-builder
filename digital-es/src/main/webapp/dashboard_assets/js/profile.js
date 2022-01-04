
start();

function start() {
	$("#main_info_btn").hide();
	$("#contact_btn").hide();
	$("#biography_btn").hide();
	//$("#profile_photo_btn").hide();
	$("#skill_btn").hide();
	loadMainInformations();
	loadEvents();
	for(i=1;i<=3;i++) {
		handleDragEvent(i);
		setOnChangeImage(i);
	}
}
function loadEvents() {
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
	var newImages={
			profilePicture:document.querySelector("#profile_picture").src,
			presentationPicture1:document.querySelector("#presentation_image_1").src,
			presentationPicture2:document.querySelector("#presentation_image_2").src,
			presentationPicture3:document.querySelector("#presentation_image_3").src,
		};
		$.ajax({
			url: "/save_presentation_image",
			contentType: "application/json",
			data: JSON.stringify(newImages),
			type: "post",
			dataType: "json",
		});
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
			document.querySelector("#profile_picture").src=data.profilePicture;
			$("#biography").val(data.bio);
			
			
			document.querySelector("#presentation_image_1").src=data.presentationPicture1;
			document.querySelector("#presentation_image_2").src=data.presentationPicture2;
			document.querySelector("#presentation_image_3").src=data.presentationPicture3;
		});
	
}




