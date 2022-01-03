/**
 * 
 */
function start(){
	
	$("#postsContainer").hide();
	$.ajax({

		url: "/get_users_data_action",
		contentType: "application/json",
		type: "post",
		dataType: "json",

	}).done(function(data) {
		//console.log(data);
		users = data;

		if (users.length === 0) {
			console.log("no data");
		} else {
			users.forEach(user => {
				addUserOnTable(user);
				//console.log(user);
			});
		}

	});
}

function addUserOnTable(user){
	$("#usersTableBody").append(createUserRow(user));
	setUserTableUserImage(user.id);
	setEventOnUserRow(user.id);
}

function createUserRow(userData){
	//console.log(userData);
		return `<tr id="rowUserId${userData.id}">
				<td><img class="rounded-circle me-2" width="30" height="30"
					src="undefined" id="profileImageUserId${userData.id}">${userData.firstName} ${userData.lastName} </td>
				<td>${userData.email}</td>
				<td>${userData.dateOfBirth}</td>
				<td>${userData.signUpDate}</td>
			</tr>`;
}

function setUserTableUserImage(id){
	$.ajax({

		url: "/get_user_profile_image",
		contentType: "application/json",
		type: "post",
		data: JSON.stringify(id),
		dataType: "json",

	}).done(function(profileImage) {
		//console.log(data);
		//console.log($("#profileImageUserId"+id));
			
		if(profileImage!=="error"){
			$("#profileImageUserId"+id).attr("src", profileImage);
		}
			

	});
	
	
}

function setEventOnUserRow(id){
	$("#rowUserId"+id).click(function() {
		$("#postsSection").empty();
		$.ajax({

			url: "/get_users_posts_by_id",
			contentType: "application/json",
			type: "post",
			data: JSON.stringify(id),
			dataType: "json",

		}).done(function(data) {
			//console.log(data);
			posts = data;
			console.log(posts);
			if (posts.length === 0) {
				
			} else {
				posts.forEach(post => {
					addUserPost(post);
					console.log(post);
				});
			}

		});
		
		
		$("#postsContainer").show();

	});
}

function addUserPost(post) {
	prependPost(post);
	if(post.picture==="undefined"){
		
	}
	//setEventOnDelete(post.id);
}

function prependPost(post) {
	$("#postsSection").prepend(createPost(post));
}

function createPost(post){
	let id = post.id;
	let title = post.title;
	let description = post.description;
	let imagePath = post.picture;
	let publicationDate = post.pubblicationDate;
	let lastEditDate = post.lastEditDate;
	let refLink = post.refLink;
	

	//console.log(post.pubblicationDate);
	return `<div  id="post${id}" class="row">
			<div class="postButtonDiv">
								<div class="text-left mb-3" style="text-align: right;">
									`+ getDeleteButton(id) + `
			</div>
			<div class="col-md-12 col-lg-12">
				<div class="card border-0">
					<img class="card-img-top scale-on-hover"
						src="${imagePath}"
						alt="Card Image" height="350">
					<div style="text-align: center; ">
						<h5><strong>
							Title: ${title}
							</strong>
						</h5>
						<p class="text-muted card-text">Description: ${description} </p>
						
						Reference Link: <a href="${refLink}">${refLink}</a>
					</div>
				
					<div style="text-align: left; ">
						<h6 id="publicationDateLabelId${id}">Publication date:
							${publicationDate}</h6>
						<h6 id="lastEditDateLabelId${id}">Last Edit date:
							${lastEditDate}</h6>
					</div>
				</div>
			</div>

				
								
			</div> <hr/>`;
}

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

start();