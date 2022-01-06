/**
 * 
 */
var banReasons = [];


function start() {
	getBanReasons();

	console.log(banReasons)
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

function addUserOnTable(user) {
	$("#usersTableBody").append(createUserRow(user));
	setUserTableUserImage(user.id);
	setEventOnConfirmBan(user.id);
	setBanReasonsOnPopUp(user.id)

}

function createUserRow(userData) {
	//console.log(userData);
	;
	return `<tr id="rowUserId${userData.id}" class="shadow-sm p-3 mb-5">
				<td><img class="rounded-circle me-2" width="30" height="30"
					src="undefined" id="profileImageUserId${userData.id}">${userData.username} </td>
				<td>${userData.email}</td>
				<td>${userData.dateOfBirth}</td>
				<td>`+ getBanButton(userData.id) + `</td>
			</tr>`;
}

function setUserTableUserImage(id) {
	$.ajax({

		url: "/get_user_profile_image",
		contentType: "application/json",
		type: "post",
		data: JSON.stringify(id),
		dataType: "json",

	}).done(function(profileImage) {
		//console.log(data);
		//console.log($("#profileImageUserId"+id));

		if (profileImage !== "error") {
			$("#profileImageUserId" + id).attr("src", profileImage);
		}


	});


}



function setEventOnConfirmBan(id) {


	$("#confirmBan" + id).click(function(e) {
		e.preventDefault();

		let selectedReason = $('#reasonsSelectId' + id + ' option:selected').val();
		console.log(selectedReason);

		let end = $("#datePickerExpiration").val();

		var banRequest = {
			userId: id,
			reason: selectedReason,
			dateStart: getCurrentDate(),
			dateEnd: end

		}
		console.log(id);
		$.ajax({

			url: "/ban_user",
			contentType: "application/json",
			data: JSON.stringify(banRequest),
			type: "post",
			dataType: "json",

		}).done(function(id) {
			$("#rowUserId" + id).remove();
		});




	});

}

function getCurrentDate() {
	var date = new Date();
	var dateString = date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate();

	return dateString;
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
									<div id="selectDivDate">
										<label class="datePickerLabel" for="datePicker"><strong>Expiration date</strong></label>
										<input placeholder="Select date" type="date" id="datePickerExpiration" name="expirationDateFrom" class="form-control form-control-user">
									</div>
									<div class="modal-body">Please give a reson why you want to delete this post...</div>
									<div id="selectDiv">
									<select name="reasons" id="reasonsSelectId${id}">
									</select>
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
function getBanReasons() {


	$.ajax({

		url: "/get_users_ban_reasons",
		contentType: "application/json",
		type: "post",


	}).done(function(data) {
		reasons = JSON.parse(data);
		console.log(reasons);
		if (reasons.length !== 0) {
			console.log("ciao")
			setBanReasons(reasons);
		}

	});
}

function setBanReasons(reasons) {
	banReasons = reasons;
	//	console.log(banReasons);
}

function appendReason(reason, id) {
	$("#reasonsSelectId" + id).append(`<option value="${reason}">${reason}</option>`);
	//console.log($("#reasonsSelectId" + id).val();
}
function setBanReasonsOnPopUp(id) {
	banReasons.forEach(function(reason) {
		//console.log(reason);
		appendReason(reason, id);

		//console.log(reason);
	});
}


start();