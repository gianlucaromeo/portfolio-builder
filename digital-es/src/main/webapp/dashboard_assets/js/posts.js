

const post={
	id : 1,
	title : "titolo",
	description: "descrizione",
	imagePath: "image",
	publicationDate: "12/12/2021",
	lastEditDate: "",
	refLink: "FACEBOOK.COM	"
	
}

function start() {
/*	$.ajax({

		url: "/get_posts_data_action",
		contentType: "application/json",
		type: "post",
		dataType: "json",

	}).done(function(data) {
		console.log(data);

	});
	*/
	addPost(post);

}

function addPost(post) {
	appendPost(post);
	refactPostFields(post.id, true);
	setEventOnDelete(post.id);
	setEventOnEdit(post.id);
	setEventOnSave(post.id);

}

function refactPostFields(id, value){
	$("#postTitleId"+id).attr('readonly', value);
	$("#postDescriptionId"+id).attr('readonly', value);
	$("#postLinkRefId"+id).attr('readonly', value);
	if(value){
		$("#saveBtn"+id).hide();
		$("#editPhotoId"+id).hide();
	}
	
	
}

function refactButton(id, value){
	if(value){
		$("#deleteId"+id).hide();
		$("#editId" + id).hide();
		$("#saveBtn"+id).show();
		$("#editPhotoId"+id).show();
	}else{
		$("#deleteId"+id).show();
		$("#editId" + id).show();
		$("#saveBtn"+id).hide();
		$("#editPhotoId"+id).hide();
	}
	
	
}

function setEventOnDelete(id){

	$("#confirmDelete" + id).click(function(e) {
		e.preventDefault();
		console.log(id);
			/*$.ajax({

				url: "/delete_post",
				contentType: "application/json",
				data: JSON.stringify(id),
				type: "post",
				dataType: "json",

			}).done(function() {

			});
			*/
			$("#post" + id).remove();
			$("#modalId"+id).modal('toggle');

	});
}

function setEventOnEdit(id){
		$("#editId" + id).click(function(event) {
		event.preventDefault();
		refactPostFields(id, false);
		refactButton(id, true);
	});
}
function setEventOnSave(id){
		$("#saveBtn" + id).click(function(event) {
		event.preventDefault();
		refactPostFields(id, true);
		refactButton(id, false);
	});
}

function getDeleteButton(id){
	var deleteButton= `<!-- Button trigger modal -->
	<button type="button" class="btn btn-danger btn-sm" data-bs-toggle="modal" data-bs-target="#modalId${id}" id="deleteId${id}">
	  Delete
	</button>
	<!-- Modal -->
	<div class="modal fade" id="modalId${id}" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="exampleModalLabel">Delete</h5>
	        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	      </div>
	      <div class="modal-body">
	        Are you sure to delete this post?
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal" ">Close</button>
	        <button type="button" class="btn btn-danger" id="confirmDelete${id}" data-bs-dismiss="modal">Delete</button>
	      </div>
	    </div>
	  </div>
	</div>`;

return deleteButton;
}

function appendPost(post){
	
	let id = post.id;
	let title = post.title;
	let description = post.description;
	let imagePath = post.imagePath;
	let publicationDate = post.publicationDate;
	let lastEditDate = post.lastEditDate;
	let refLink = post.refLink;

	
	$("#postsSection").append(`<div class="card border-0" id="post${id}">
		<div class="text-left mb-3" style="text-align: right;" >
		<button class="btn btn-primary btn-sm"  id="editId${id}">Edit</button>`+ getDeleteButton(id)+ `
		</div>
		<div class= "row">
		
			<img src="${imagePath}" height="150" class="img-responsive" />
		</div>
		<div class "row" style="margin-top: 20px;">
		<button class="btn btn-primary btn-sm"  id="editPhotoId${id}">Change photo</button>
		</div>
		
		<div class="card-body">
		<div style="text-align: left">
		<label for="postTitleId${id}"><strong>Title</strong></label>
		<input class="form-control" type="text" id="postTitleId${id}"
												placeholder="Title..." name="post_title"
												value="${title}">
		</div>
		<div style="text-align: left">
		<label for="postDescriptionId${id}"><strong>Description</strong></label>										
		<textarea class="form-control" id="postDescriptionId${id}"
												placeholder="Description..." name="post_description"
												 rows="3" >${description}</textarea>
		</div>
		<div style="text-align: left">
		<label for="postLinkRefId${id}"><strong>Reference link<strong></label>
		<input class="form-control" type="text" id="postLinkRefId${id}"
												placeholder="Reference link..." name="post_link_ref"
												value="${refLink}">
		</div>
		<div style="text-align: left; margin-top: 20px;">
		<h6>Publication date: ${publicationDate}</h6>
		<h6>Last Edit date: ${lastEditDate}</h6>
		</div>
		</div>
		</div >
		</div >
		<button class="btn btn-primary btn-sm" type="submit" id="saveBtn${id}">Save Settings</button>
		<hr/>`);
		
	
}

start();