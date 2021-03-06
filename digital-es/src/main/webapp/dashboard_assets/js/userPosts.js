
const postsMap = new Map();

//é***************************************************** FUNZIONE START *******************************************//
/**
* Chiedo al rest controller tutti i post, lui me li invia correttamente poichè
* abbiamo solo il cookie dell'utente loggato (solo lui può essere in questa dashboard
 */
function start() {

	//setto la grandezza del div che contiene i posts
	var h = $(window).height();
	$("#postsSection").height(h);
	addEventOnNewPostImage();
	addEventOnAddNewPost();

	$.ajax({

		url: "/get_posts_data_action",
		contentType: "application/json",
		type: "post",
		dataType: "json",

	}).done(function(data) {
		
		posts = data;

		if (posts.length === 0) {
			$("#postsContainer").hide();
		} else {
			posts.forEach(post => {
				addPost(post);

				postsMap.set(post.id, post);
			});
		}

	});

	


}

//é*****************************************************END  FUNZIONE START *******************************************//
//é*****************************************************END  FUNZIONE START *******************************************//





//************************************************************************CREAZIONE POST NELL'HTML**************************************************' */
/**
* aggiungo i post alla pagina in modo dinamico
* per ogni post che inserisco vado a settare read only i suoi campi
* setto gli eventi sui bottoni dei post
* 	
 */
function addPost(post) {
	
	
	postsMap.set(post.id, post);
	prependPost(post);
	refactPostFields(post.id, true);
	setEventOnDelete(post.id);
	setEventOnEdit(post.id);
	setEventOnSave(post.id);
	setEventChangePhoto(post.id);
	setOnDiscardChanges(post.id);
	refactButton(post.id, true);
	showPostDiv();
	

}
function showPostDiv() {
	$("#postsContainer").show();
}
/**
* Inserisco il post in run time al caricamento della pagina, prendo tutti i post e li inserisco in un div
* in cui ho inserito l'auto scroll una volta superata la grandezza predefinita (vedere funzione start parte finale e vedere posts.css
* in dashobard_assets/bootstrap/css'
* 	
 */
function appendPost(post) {
	$("#postsSection").append(createPost(post));
}
function prependPost(post) {
	$("#postsSection").prepend(createPost(post));
}
function createPost(post) {
	let id = post.id;
	let title = post.title;
	let description = post.description;
	let imagePath = post.picture;
	let publicationDate = post.pubblicationDate;
	let lastEditDate = post.lastEditDate;
	let refLink = post.refLink;


	
	return `<div  id="post${id}">
				<div class="postClass card border-0">
								<br/>
								<div class="postButtonDiv">
								<div class="text-left mb-3" style="text-align: right;">
									<button class="btn btn-primary btn-sm" id="editId${id}">Edit</button>
									`+ getDeleteButton(id) + `
								</div>
								</div>
								<div class="row">
									<div col-12>
										<img src="${imagePath}" height="150" class="img-responsive"
											id="postImageId${id}" />
									</div>
								</div>
								<div class="row" style="margin-top: 20px;">
									<div class="text-center"> 
									<input type="file" accept="image/jpg, image/png" id="editPhotoId${id}" class="editPhotoChooser" hidden/>
									<label id="editPhotoLabelId${id}" for="editPhotoId${id}" class="labelImage">
									<i class="material-icons"> add_photo_alternate </i> &nbsp
									New image
									</label>
									</div>
								</div>
							
								<div class="card-body">
									<div style="text-align: left">
										<label for="postTitleId${id}"><strong>Title</strong></label> <input
											class="form-control" type="text" id="postTitleId${id}"
											placeholder="Title..." name="post_title" value="${title}">
									</div>
									<div style="text-align: left">
										<label for="postDescriptionId${id}"><strong>Description</strong></label>
										<textarea class="form-control" id="postDescriptionId${id}"
											placeholder="Description..." name="post_description" rows="3">${description}</textarea>
									</div>
									<div style="text-align: left">
										<label for="postLinkRefId${id}"><strong>Reference
												link<strong></label> <input class="form-control" type="text"
											id="postLinkRefId${id}" placeholder="Reference link..."
											name="post_link_ref" value="${refLink}">
									</div>
									<div style="text-align: left; margin-top: 20px;">
										<h6 id="publicationDateLabelId${id}">Publication date:
											${publicationDate}</h6>
										<h6 id="lastEditDateLabelId${id}">Last Edit date:
											${lastEditDate}</h6>
									</div>
								</div>
								
								<button class="btn btn-danger btn-sm" type="submit" id="discardChangesId${id}">Discard
									Changes</button>
								<button class="btn btn-primary btn-sm" type="submit" id="saveBtn${id}">Save
									Settings</button>
								`+ getPopUpOnSave(id) + `
								</div>
								
								<hr /> 
								
							</div> `;
}



//************************************************************************END CREAZIONE POST NELL'HTML**************************************************' */
//************************************************************************************************+****************************************************' */




//************************************************************************Gestione campi dei post e bottoni di edit ed eliminazione POST NELL'HTML**************************************************' */
/**
* se value è true allora gli input field devono essere nascosti, altrimenti devo mostrarli (se ho cliccato su edit è false)
* 
* se è true vuol dire che ho cliccato su salva dopo una modifica oppure non ho cliccato ancora su edite
* 
* 	
 */
function refactPostFields(id, value) {
	$("#postTitleId" + id).attr('readonly', value);
	$("#postDescriptionId" + id).attr('readonly', value);
	$("#postLinkRefId" + id).attr('readonly', value);
	if ($("#postImageId" + id).attr('src') === 'undefined') {
		$("#postImageId" + id).hide();

	}
	if ($("#newPostImage").attr('src') === 'undefined') {
		$("#newPostImage").hide();
	}

}

function refactButton(id, value) {
	if (!value) {
		$("#deleteId" + id).hide();
		$("#editId" + id).hide();
		$("#saveBtn" + id).show();
		$("#editPhotoId" + id).show();
		$("#editPhotoLabelId" + id).show();
		$("#discardChangesId" + id).show();

	} else {
		$("#deleteId" + id).show();
		$("#editId" + id).show();
		$("#saveBtn" + id).hide();
		$("#discardChangesId" + id).hide();
		$("#editPhotoId" + id).hide();
		$("#editPhotoLabelId" + id).hide();
	}


}

function resetFieldsNewPost() {
	$("#newPostImage").attr("src", "undefined");
	$("#newPostTitle").val("");
	$("#newPostDSescription").val("");
	$("#newPostRefLink").val("");
}



//************************************************************ EVENTS ON BUTTONS ****************************************************** */
function addEventOnNewPostImage() {
	const imageInput = document.querySelector("#newImageInput");
	var newImage = "";
	imageInput.addEventListener("change", function() {
		const reader = new FileReader();
		reader.addEventListener("load", () => {
			newImage = reader.result;

			document.querySelector("#newPostImage").src = newImage;

		});
		reader.readAsDataURL(this.files[0]);

		$("#newPostImage").show();
	});
}


//funzione per la creazione di un nuovo post, notifica alla servlet che risponde in base a come sono creati i campi
function addEventOnAddNewPost() {
	$("#newPostImage").hide()

	$("#addNewPostBtn").click(function(e) {
		e.preventDefault();


		var date = getCurrentDate();
		const newPost = {
			id: -1,
			title: $("#newPostTitle").val(),
			description: $("#newPostDSescription").val(),
			picture: $("#newPostImage").attr("src"),
			pubblicationDate: date,
			lastEditDate: "0-0-0",
			refLink: $("#newPostRefLink").val(),
			userId: -1
		};



		$.ajax({

			url: "/create_post",
			contentType: "application/json",
			data: JSON.stringify(newPost),
			type: "post",
			dataType: "json",

		}).done(function(data) {

			doCreateOnDOM(data);

		});

	});
}


/**
*  prendo il bottone di eliminazione che ha ogni post 
*  gli associo un evento sul click evitando che effettui la submit con preventDefault()
*  quando viene cliccato su questo bottone compare una finestra di dialog gestita con l'uso di bootstrap dialog '
* se l'utente clicca sul pulsante di conferma dell'eliminazione allora:
* viene effettuata una chiamata rest con ajax in cui passo l'id univoco del post
* la funzione rest si occupa di eliminare quel post dal database '
 */
function setEventOnDelete(id) {

	$("#confirmDelete" + id).click(function(e) {
		e.preventDefault();
		
		$.ajax({

			url: "/delete_post",
			contentType: "application/json",
			data: JSON.stringify(id),
			type: "post",
			dataType: "json",

		}).done(function(id) {
			$("#post" + id).remove();
			postsMap.delete(id);
			
			if (postsMap.size===0) {
				$("#postsContainer").hide();
			}
		});


		//$("#modalId"+id).modal('toggle');

	});
}

/**
*  prendo il bottone di modifica che ha ogni post 
*  gli associo un evento sul click evitando che effettui la submit con preventDefault()
*  quando viene cliccato su questo bottone scompaiono i bottoni di edit stesso e di eliminazione del post
*  gli input field diventano editabili e quindi non più solo leggibili
*  compare il bottone di salvataggio delle modifiche effettuate
* 	
 */
function setEventOnEdit(id) {
	$("#editId" + id).click(function(event) {
		event.preventDefault();
		refactPostFields(id, false);
		refactButton(id, false);
	});
}

/**
*  prendo il bottone di salvataggio delle modifiche che ha ogni post 
*  gli associo un evento sul click evitando che effettui la submit con preventDefault()
*  quando viene cliccato su questo bottone creo il post con i valori dei campi modificati 
*  pubblicationDate e userId non possono essere modificati perciò vengono inviati in un formato stabilito
*  ciò verrà gestito poi nel rest controller
*  una volta creato il post aggiornato viene spedito alla rest controller sotto forma di json
*  la servlet si occuperà di memorizzare i dati sul database
*  compare il bottone di salvataggio delle modifiche effettuate
*  i bottoni modifica ed elimina ricompaiono e quello di salvataggio scompare
* 	
 */
function setOnDiscardChanges(postId) {
	$("#discardChangesId" + postId).click(function(event) {
		event.preventDefault();

		oldPost = postsMap.get(postId);

		
		$("#postTitleId" + postId).val(oldPost.title);
		$("#postDescriptionId" + postId).val(oldPost.description);
		$("#postLinkRefId" + postId).val(oldPost.refLink);
		refactPostFields(postId, true);
		refactButton(postId, true);
		updatedPostValid(postId);
	});
}
function setEventOnSave(postId) {
	$("#saveBtn" + postId).click(function(event) {
		event.preventDefault();

		var date = getCurrentDate();


		const updatedPost = {
			id: postId,
			title: $("#postTitleId" + postId).val(),
			description: $("#postDescriptionId" + postId).val(),
			picture: $("#postImageId" + postId).attr("src"),
			pubblicationDate: "0-0-0",
			lastEditDate: date,
			refLink: $("#postLinkRefId" + postId).val(),
			userId: -1
		};


		$.ajax({

			url: "/update_post",
			contentType: "application/json",
			data: JSON.stringify(updatedPost),
			type: "post",
			dataType: "json",

		}).done(function(data) {

			doUpdateOnDOM(data);

		});



		//modifico in runtime la data di ultima modifica






	});
}



function setEventChangePhoto(id) {
	const imageInput = document.querySelector("#editPhotoId" + id);
	var newImage = "";
	imageInput.addEventListener("change", function() {
		const reader = new FileReader();
		reader.addEventListener("load", () => {
			newImage = reader.result;

			document.querySelector("#postImageId" + id).src = newImage;

		});
		reader.readAsDataURL(this.files[0]);
		$("#postImageId" + id).show();
	});


}



//***************************************************************************************DOM MANIPULATION************************************************* */
function doCreateOnDOM(data) {
	

	newPostValid();
	var isValidPost = true;
	if (data.title === "post_field_empty" || data.title === "post_title_not_correct") {
		$("#newPostTitle").addClass("is-invalid");
		isValidPost = false;
	} if (data.description === "post_field_empty") {
		$("#newPostDSescription").addClass("is-invalid");
		isValidPost = false;
	} if (data.refLink === "post_field_empty") {
		$("#newPostRefLink").addClass("is-invalid");
		isValidPost = false;
	}

	if (isValidPost) {
		$("#popupNewBtn").trigger('click');

		$("#closeNewPopupBtn").click(function(e) {
			e.preventDefault();

		});
		addPost(data);
		resetFieldsNewPost();
		refactButton(data.id, true);
	}


}
function newPostValid() {
	$("#newPostTitle").removeClass("is-invalid");
	$("#newPostDSescription").removeClass("is-invalid");
	$("#newPostRefLink").removeClass("is-invalid");
}

function updatedPostValid(id) {
	$("#postTitleId" + id).removeClass("is-invalid");
	$("#postDescriptionId" + id).removeClass("is-invalid");
	$("#postLinkRefId" + id).removeClass("is-invalid");
}

function doUpdateOnDOM(data) {
	updatedPostValid(data.id);
	var isValidPost = true;
	if (data.title === "post_field_empty" || data.title === "post_title_not_correct") {
		$("#postTitleId" + data.id).addClass("is-invalid");
		isValidPost = false;
	} if (data.description === "post_field_empty") {
		$("#postDescriptionId" + data.id).addClass("is-invalid");
		isValidPost = false;
	} if (data.refLink === "post_field_empty") {
		$("#postLinkRefId" + data.id).addClass("is-invalid");
		isValidPost = false;
	}

	if (isValidPost) {
		$("#popupSaveBtn" + data.id).trigger('click');

		$("#closeSavePopupBtn" + data.id).click(function() {
			$("#post" + data.id).remove()
			addPost(data);
			refactPostFields(data.id, true);
			refactButton(data.id, true);
		});



	}
}





//************************************************************************END Gestione campi dei post e bottoni di edit ed eliminazione POST NELL'HTML**************************************************' */
//****************************************************************************************************************************************' */




//*******************************************************************************************************FUnzione per prendere la data************************_ */

/**
*  Prendo la data in cui la funzione viene richiamata e ritorno una stringra del patter yyyy-mm-dd
* il mese viene sommato ad 1 perchè vanno da 0 a 11
* 	
 */
function getCurrentDate() {
	var date = new Date();
	var dateString = date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate();

	return dateString;
}

//*******************************************************************************************************END FUnzione per prendere la data************************_ */
//****************************************************************************************************************************************************************_ */



//***************************** BUTTON CREATION ************************************************************************************************* */
/**
*  Creo il bottone dell'eliminazione dei post passandogli il loro id, inorltre gli associo la funzionalità del model box di bootstrap ovvero
* una finestra di dialogo in cui l'utente deve confermare l'eliminazione'
* 	
 */
function getDeleteButton(id) {
	var deleteButton = `<!-- Button trigger modal -->
						<button type="button" class="btn btn-danger btn-sm"
							data-bs-toggle="modal" data-bs-target="#modalId${id}"
							id="deleteId${id}">Delete</button>
						<!-- Modal -->
						<div class="modal fade" id="modalId${id}" tabindex="-1"
							aria-labelledby="modalLabel" aria-hidden="true">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<h5 class="modal-title" id="modalLabel">Delete</h5>
										<button type="button" class="btn-close" data-bs-dismiss="modal"
											aria-label="Close"></button>
									</div>
									<div class="modal-body">Are you sure to delete this post?</div>
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
									<div class="modal-body">Post successfully updated</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-primary"
											data-bs-dismiss="modal" id="closeSavePopupBtn${id}">Ok</button>
									</div>
								</div>
							</div>
						</div>`;




	return saveButton;
}




//richiamo il metodo start in modo che venga eseguito tutto il codice che mi serve per settare i post
start();





