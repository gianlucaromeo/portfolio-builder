

var removeProjectReasons = [];

var allUsers = {}; 
var allProfilePictures = {};

var selectedUserId = 8;
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

		get_remove_projects_reasons = reasons;
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

		if (selectedUserProjects.length == 0) {
			console.log("Users has no projects.");
		} else {
			console.log("User has " + selectedUserProjects.length + " projects. Building..")
			clearProjectsTable();
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

function buildProjectRow(project) {

	row = `<tr class="shadow-sm p-3 mb-5" id="project_${project.id}">
				<td class="align-middle"><img class=" me-2" width="100" height="50"
					src="${project.picture}" id="projectPicture_${project.id}"></td>
				<td class="align-middle"><h5>${project.title}<h5></td>
				<td class="align-middle">${project.description}</td>
				<td class="align-middle"><button class="btn btn-danger btn-sm" type="submit"
								id="btnRemoveProject_${project.id}">Remove</button></td>
			</tr>`;

	$("#projectsTableBody").append(row);
}

function buildUserRow(user) {

	clearUsersTable();
	
	row = `<tr class="shadow-sm p-3 mb-5 moderator-projects-user" id="user_${user.id}">
				<td><img class="rounded-circle me-2" width="30" height="30"
					src="undefined" id="profilePicture_${user.id}">${user.username}</td>
			</tr>`;

	$("#allUsersTableBody").append(row);
	//console.log("Row appended to tbody. Username: " + user.username);

}

/* =================================== END ROWS ======================================= */


window.onload = (event) => {
	console.log("Load page Projects for Moderators.")
	fetchReasons();
	fetchAllUsers();
};

setTimeout(() => {
  fetchUserProjects();
}, 4000)