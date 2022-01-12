var removeProjectReasons = [];

var allUsers = {};
var allProfilePictures = {};

var selectedUserId = -1;
var selectedUserProjects = {};

function fetchReasons() {

	console.log("Fetching reasons..");

	$.ajax({

		url: "/get_remove_projects_reasons",
		contentType: "application/json",
		type: "post",
		data: JSON.stringify(selectedUserId),
		dataType: "json",

	}).done(function(reasons) {

		removeProjectReasons = reasons;
		console.log("Reasons fetched.")
		console.log(reasons);

	});

}

function fetchAllUsers() {

	console.log("Fetching users..")

	$.ajax({

		url: "/get_users_data_action",
		contentType: "application/json",
		type: "post",
		data: JSON.stringify(selectedUserId),
		dataType: "json",

	}).done(function(users) {

		allUsers = users;
		allUsers.forEach(user => buildUserRow(user));
		allUsers.forEach(user => fetchUserProfilePicture(user.id));

	});

}

function fetchUserProjects() {

	if (selectedUserId < 0) {
		console.log("Invalid user id.");
		return;
	}

	$.ajax({

		url: "/get_projects_by_user_id",
		contentType: "application/json",
		type: "post",
		data: JSON.stringify(selectedUserId),
		dataType: "json",

	}).done(function(selectedUserProjects) {

		clearProjectsTable();

		if (selectedUserProjects.length == 0) {
			console.log("Users has no projects.");
			buildNoProjectsRow();
		} else {
			console.log("User has " + selectedUserProjects.length + " projects. Building..")
			selectedUserProjects.forEach(project => buildProjectRow(project));
			console.log("User's projects built.")
		}

	});

}

function fetchUserProfilePicture(userId) {

	$.ajax({

		url: "/get_user_profile_image",
		contentType: "application/json",
		type: "post",
		data: JSON.stringify(userId),
		dataType: "json",

	}).done(function(userProfilePicture) {
		$("#profilePicture_" + userId).attr("src", userProfilePicture);
	});

}

function clearProjectsTable() {
	$("#projectsTableBody tr").remove();
}

function clearUsersTable() {
	$("#usersTableBody").remove();
}


/* ==================================== ROWS =================================== */

function buildRemoveBtn(projectId) {
	return `<!-- Button trigger modal -->
			<button type="button" class="btn btn-danger btn-sm"
				data-bs-toggle="modal" data-bs-target="#modal_${projectId}"
				id="removeProjectBtn_${projectId}" style="margin-bottom: 10px">Delete</button>
			<!-- Modal -->
			<div class="modal fade" id="modal_${projectId}" tabindex="-1"
				aria-labelledby="modalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="modalLabel">Delete</h5>
							<button type="button" class="btn-close" data-bs-dismiss="modal"
								aria-label="Close"></button>
						</div>
						<div class="modal-body">Select the reason why You want to remove this Project.</div>
						<div id="selectDiv">
						<select name="reasons" id="reasonsSelect_${projectId}">
						</select>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary"
								data-bs-dismiss="modal"">Close</button>
							<button type="button" class="btn btn-danger" id="confirmRemove_${projectId}"
								data-bs-dismiss="modal">Delete</button>
						</div>
					</div>
				</div>
			</div>`;
}

function buildNoProjectsRow() {
	row = `<tr class="shadow-sm p-3 mb-5" id="noProjects">
				<td class="align-middle">No Projects</td>
				<td class="align-middle">No Projects</td>
				<td class="align-middle">No Projects</td>
				<td class="align-middle">No Projects</td>
			</tr>`;

	$("#projectsTableBody").append(row);
}

function buildProjectRow(project) {

	row = `<tr class="shadow-sm p-3 mb-5" id="project_${project.id}">
				<td class="align-middle"><img class=" me-2" width="100" height="50"
					src="${project.picture}" id="projectPicture_${project.id}"></td>
				<td class="align-middle"><h5>${project.title}<h5></td>
				<td class="align-middle">${project.description}</td>
				<td class="align-middle">${buildRemoveBtn(project.id)}</td>
			</tr>`;

	$("#projectsTableBody").append(row);
	addClickOnConfirmRemoveProjectBtn(project.id);
	removeProjectReasons.forEach(reason => addReasonToPopup(project.id, reason));

}

function addReasonToPopup(projectId, reason) {
	$("#reasonsSelect_" + projectId).append(`<option value="${reason}">${reason}</option>`);
}

function addClickOnConfirmRemoveProjectBtn(projectId) {
	$("#confirmRemove_" + projectId).click(function() {

		selectedReason = $("#reasonsSelect_" + projectId + " option:selected").val();
		var removeProjectRequest = {
			id: projectId,
			reason: selectedReason
		};

		// Send data to Server. Action: Remove Project
		$.ajax({

			url: "/remove_user_project",
			contentType: "application/json",
			type: "post",
			data: JSON.stringify(removeProjectRequest),
			dataType: "json",

		}).done(function(status) {
			if (status == "ok") {
				console.log("Project removed.")
			} else {
				console.log("Remove project status error: " + status);
			}
			fetchUserProjects();
		});

		//console.log("Remove project. Id: " + removeProjectRequest.id + ". Reason: " + removeProjectRequest.reason);
	});
}

function addClickOnRow(userId) {
	$("#tr_user_" + userId).click(function() {
		selectedUserId = userId;
		console.log("Clicked on userid: " + userId);
		fetchUserProjects();
	});
}

function buildUserRow(user) {

	clearUsersTable();

	row = `<tr class="shadow-sm p-3 mb-5 moderator-projects-user" id="tr_user_${user.id}">
				<td><img class="rounded-circle me-2" width="30" height="30"
					src="" id="profilePicture_${user.id}">${user.username}</td>
			</tr>`;

	$("#allUsersTableBody").append(row);
	addClickOnRow(user.id);
	//console.log("Row appended to tbody. Username: " + user.username);

}

/* =================================== END ROWS ======================================= */


window.onload = (event) => {
	console.log("Load page Projects for Moderators.")
	fetchReasons();
	fetchAllUsers();
};

/*
setTimeout(() => {
	fetchUserProjects();
}, 4000)
*/

/*
const qrcode = new QRCode(document.getElementById('qrcode'), {
		text: 'http://jindo.dev.naver.com/collie',
		width: 128,
		height: 128,
		colorDark: '#000',
		colorLight: '#fff',
		correctLevel: QRCode.CorrectLevel.H
	});
*/
