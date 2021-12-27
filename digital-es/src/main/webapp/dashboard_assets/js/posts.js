
var deleteButton= `<!-- Button trigger modal -->
<button type="button" class="btn btn-danger btn-sm" data-bs-toggle="modal" data-bs-target="#exampleModal" id="deleteId${id}">
  Delete
</button>
<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        Are you sure to delete this post?
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
        <button type="button" class="btn btn-danger" id="confirmDelete${id}">Delete</button>
      </div>
    </div>
  </div>
</div>`;

function start() {
	$.ajax({

		url: "/get_posts_data_action",
		contentType: "application/json",
		type: "post",
		dataType: "json",

	}).done(function(data) {
		console.log(data);

	});
	//addPost(post);

}
function addPost(post) {
	let id = post.id;
	let title = post.title;
	let description = post.description;
	let imagePath = post.imagePath;
	let publicationDate = post.publicationDate;
	let lastEditDate = post.lastEditDate;
	let refLink = post.refLink;

	$("#postsSection").append(`<div class="card border-0" id="post${id}">
		<div class="text-left mb-3" style="text-align: right;" >
		<button class="btn btn-primary btn-sm" type="submit" id="modifyId${id}">Modify</button>`+ deleteButton+ `
		</div>
		<div class= "col-12">
		<img src="${imagePath}" height="150" class="img-responsive" />
		</div >
		<div class="card-body">
		<h2>${title}</h2>
		<p class="text-muted card-text">${description}</p>
		<a href="">${refLink}</a>
		<h6>Publication date: ${publicationDate}</h6>
		<h6>Last Edit date: ${lastEditDate}</h6>
		</div>
		</div >
		</div >
		<hr/>`);

	$("#deleteId" + id).click(function(e) {
		e.preventDefault();
		var answer = window.confirm("Are you sure to delete this post?");
		if (answer) {
			console.log(id);
			$.ajax({

				url: "/delete_post",
				contentType: "application/json",
				data: JSON.stringify(id),
				type: "post",
				dataType: "json",

			}).done(function() {

			});
			$("#post" + id).remove();
		}

	});

	$("#modifyId" + id).click(function(event) {
		event.preventDefault();
		openDialog();
		$("#dialog").dialog();
	});

}

start();