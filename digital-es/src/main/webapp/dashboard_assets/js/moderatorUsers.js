/**
 * 
 */
var banReasons=[];


function start(){
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
	setEventOnConfirmBan(user.id);
	
}

function createUserRow(userData){
	//console.log(userData);
		return `<tr id="rowUserId${userData.id}">
				<td><img class="rounded-circle me-2" width="30" height="30"
					src="undefined" id="profileImageUserId${userData.id}">${userData.firstName} ${userData.lastName} </td>
				<td>${userData.email}</td>
				<td>${userData.dateOfBirth}</td>
				<td>${userData.signUpDate}</td>
				<td>`+ getBanButton(userData.id) +`</td>
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



function setEventOnConfirmBan(id){
	
	$("#confirmBan" + id).click(function(e) {
		e.preventDefault();
		console.log(id);
		$.ajax({

			url: "/ban_user",
			contentType: "application/json",
			data: JSON.stringify(id),
			type: "post",
			dataType: "json",

		}).done(function(id) {
			$("#userId" + id).remove();
		});


		

	});
	
}




function getBanButton(id) {
	var banButton = `<!-- Button trigger modal -->
						<button type="button" class="btn btn-danger btn-sm"
							data-bs-toggle="modal" data-bs-target="#modalId${id}"
							id="banId${id}" style="margin-bottom: 10px">Ban</button>
						<!-- Modal -->
						<div class="modal fade" id="modalId${id}" tabindex="-1"
							aria-labelledby="modalLabel" aria-hidden="true">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<h5 class="modal-title" id="modalLabel">Ban User</h5>
										<button type="button" class="btn-close" data-bs-dismiss="modal"
											aria-label="Close"></button>
									</div>
									<div class="modal-body">Ban expiration date, if you don't give a date, ban will be forever...</div>
									<div id="selectDiv">
									<label class="datePickerLabel" for="datePicker"><strong>Expiration date</strong></label>
									<input placeholder="Select date" type="date" id="datePickerExpiration" name="expirationDateFrom" class="form-control form-control-user">
									<div class="modal-footer">
										<button type="button" class="btn btn-secondary"
											data-bs-dismiss="modal"">Close</button>
										<button type="button" class="btn btn-danger" id="confirmBan${id}"
											data-bs-dismiss="modal">Ban</button>
									</div>
								</div>
							</div>
						</div>`;

	//console.log(banReasons);
	
	
	return banButton;
}



start();