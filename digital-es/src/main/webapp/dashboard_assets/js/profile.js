
start();
for(i=1;i<=3;i++) {
	handleDragEvent(i);
	setOnChangeImage(i);
}

function start() {
	$("#main_info_btn").hide();
	$("#contact_btn").hide();
	$("#biography_btn").hide();
	$("#profile_photo_btn").hide();
	$("#skill_btn").hide();
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
			saveImage(reader.result,id);
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
			saveImage(newImage,id);
		});
		reader.readAsDataURL(this.files[0]);
	});
}

function saveImage(newImage,id) {
	console.log("SAVING IMAGEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
	
	
	var selectedImage={
			presentationPicture1:"...",
			presentationPicture2:"...",
			presentationPicture3:"..."
		};
	if(id==1)
		selectedImage.presentationPicture1=newImage;
	if(id==2)
		selectedImage.presentationPicture2=newImage;
	if(id==3)
		selectedImage.presentationPicture3=newImage;
		$.ajax({
			url: "/save_presentation_image",
			contentType: "application/json",
			data: JSON.stringify(selectedImage),
			type: "post",
			dataType: "json",
		});
}




