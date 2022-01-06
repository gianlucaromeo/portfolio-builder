/**
 * 
 */
var banReasons=[];


function start(){
	getBanReasons();
	
	
	$("#postsContainer").hide();
	$.ajax({

		url: "/get_users_data_action",
		contentType: "application/json",
		type: "post",
		dataType: "json",

	}).done(function(data) {
		//console.log(data);
		users = data;

		if (users.length !== 0) {
			//console.log("no data");
		
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
					src="undefined" id="profileImageUserId${userData.id}">${userData.username}</td>
				<td>${userData.email}</td>
				<td>${userData.dateOfBirth}</td>
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
		$("#postsTableBody").empty();
		$.ajax({

			url: "/get_users_posts_by_id_not_banned",
			contentType: "application/json",
			type: "post",
			data: JSON.stringify(id),
			dataType: "json",

		}).done(function(data) {
			//console.log(data);
			posts = data;
			//console.log(posts);
			if (posts.length === 0) {
				addUserPost(null);
			} else {
				posts.forEach(post => {
					addUserPost(post);
					//console.log(post);
				});
			}

		});
		
		
		$("#postsContainer").show();

	});
}

function addUserPost(post) {
	prependPost(post);
	if (post !== null) {
		console.log(post.picture);
		if (post.picture === "undefined" || post.picture === undefined) {
			$("#imageId" + post.id).hide();

		}

		setBanReasonsOnDelete(post.id);
		setEventOnConfirmBan(post.id);
		//setEventOnDelete(post.id);
	}else{
		$("#noImage").hide();
	}
	
}

function setEventOnConfirmBan(id){
	let selectedReason= $('#reasonsSelectId'+id+' option:selected').val();
	var banRequest={
		postId: id,
		reason: selectedReason
	}
	$("#confirmDelete" + id).click(function(e) {
		e.preventDefault();
		console.log(id);
		$.ajax({

			url: "/remove_post_action",
			contentType: "application/json",
			data: JSON.stringify(banRequest),
			type: "post",
			dataType: "json",

		}).done(function(id) {
			if(id!==-1){
				$("#post" + id).remove();
			}
			
		});


		//$("#modalId"+id).modal('toggle');

	});
	
}
function setBanReasonsOnDelete(id){
	banReasons.forEach(function(reason) {
		//console.log(reason);
		appendReason(reason, id);
		
		//console.log(reason);
	});
}

function prependPost(post) {
	$("#postsTableBody").prepend(createPost(post));
}

function createPost(post){
	if(post!==null){
		let refLink = (post.refLink!="")? "None" : post.refLink;
		
		return `<tr class="shadow-sm p-3 mb-5" id="post${post.id}">
				<td class="align-middle"><img class=" me-2" width="100" height="50"
					src="${post.picture}" id="imageId${post.id}"></td>
				<td class="align-middle"><h5>${post.title}<h5></td>
				<td class="align-middle">${post.description}</td>
				<td class="align-middle"><a href="${refLink}">${refLink}</a></td>
				<td class="align-middle">${post.pubblicationDate}</td>
				<td class="align-middle">${getDeleteButton(post.id)}</td>
			</tr>`;
	
	}
	else{
		return `<tr class="shadow-sm p-3 mb-5" id="noPost">
				<td class="align-middle"><img class=" me-2" width="100" height="50"
					src="" id="noImage"></td>
				<td class="align-middle">NONE</td>
				<td class="align-middle">NONE</td>
				<td class="align-middle">NONE</td>
				<td class="align-middle">NONE</td>
			</tr>`;
	}
	
}

function getDeleteButton(id) {
	var deleteButton = `<!-- Button trigger modal -->
						<button type="button" class="btn btn-danger btn-sm"
							data-bs-toggle="modal" data-bs-target="#modalId${id}"
							id="deleteId${id}" style="margin-bottom: 10px">Delete</button>
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
									<div class="modal-body">Please give a reson why you want to delete this post...</div>
									<div id="selectDiv">
									<select name="reasons" id="reasonsSelectId${id}">
									</select>
									<div class="modal-footer">
										<button type="button" class="btn btn-secondary"
											data-bs-dismiss="modal"">Close</button>
										<button type="button" class="btn btn-danger" id="confirmDelete${id}"
											data-bs-dismiss="modal">Delete</button>
									</div>
								</div>
							</div>
						</div>`;

	//console.log(banReasons);
	
	
	return deleteButton;
}
function appendReason(reason, id){
	$("#reasonsSelectId" + id).append(`<option value="${reason}">${reason}</option>`);
	//console.log($("#reasonsSelectId" + id).val();
}
function getBanReasons(){
	$.ajax({

		url: "/get_ban_reasons",
		contentType: "application/json",
		type: "post",
		

	}).done(function(data) {
		reasons=JSON.parse(data);
		//console.log(reasons);
		if (reasons.length !== 0) {
			console.log("ciao")
			setBanReasons(reasons);	
		} 

	});
}

function setBanReasons(reasons){
	banReasons=reasons;
//	console.log(banReasons);
}


start();