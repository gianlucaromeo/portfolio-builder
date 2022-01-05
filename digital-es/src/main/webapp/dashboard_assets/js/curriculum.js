let user_id = -1;
const all_experiences = new Map();

window.onload = (event) => {
	initDoneBtn();
	hideNewExperienceDiv();
	initBtns();
	retrieveCurriculumData();
};

function fetchUserId() {

	$.ajax({

		url: "/get_user_id_action",
		type: "post",
		dataType: "json",

	}).done(function(data) {

		user_id = JSON.stringify(data);
		console.log("User id retrieved. Value: " + user_id);

	});

}

function retrieveCurriculumData() {
	$.ajax({

		url: "/get_curriculum_data_action",
		type: "post",
		dataType: "json",

	}).done(function(data) {

		if (data.length == 0) {
			//console.log("no data");
		} else {
			experiences = data;
			experiences.forEach(exp => {
				all_experiences.set(exp.id, exp);
			});
			init();
			//initDoneButton(userId);
		}


	});
}

function initBtns() {
	initAddNewExperienceBtn();
	initDiscardNewExperienceChangesBtn();
}

function initAddNewExperienceBtn() {
	$("#addExperienceBtn").on("click", function(e) {
		e.preventDefault();
		cleanAddNewExperienceFields();
		showNewExperienceDiv();
	});
}

function initDiscardNewExperienceChangesBtn() {
	$("#discardChangesBtn").on("click", function(e) {
		e.preventDefault();
		hideNewExperienceDiv();
	});
}

// 1 QUA
function initDoneBtn() {

	if (user_id == -1) {
		fetchUserId();
	}

	$("#doneBtn").on("click", function(e) {

		e.preventDefault();

		new_title = $("#experienceTitle").val();
		new_dateStart = $("#datePickerFrom").val();
		new_dateEnd = $("#datePickerTo").val();
		new_place = $("#experiencePlace").val();
		new_type = $("#experienceType").val();
		new_description = $("#experienceDescription").val();

		new_data = {
			id: "-1",
			userId: user_id,
			title: new_title,
			startDate: new_dateStart,
			endDate: new_dateEnd,
			place: new_place,
			type: new_type,
			description: new_description
		};

		//console.log(new_data);
		addExperience(new_data);

	});

}


function init() {
	$("#curriculumExperiencesContainer").empty();
	all_experiences.forEach((exp, exp_id) => {
		initExperience(exp, exp_id, false);
	});
}

function initExperience(exp, exp_id, is_updating) {
	buildExperience(exp, is_updating);
	if (exp.endDate == "PRESENT") {
		checkToPresent(exp.id);
	}
	hideSaveBtn(exp_id);
	hideDiscardChangesBtn(exp_id);
	disableFields(exp_id);
	setEditBtnActions(exp_id);
	setSaveBtnAction(exp);
	setDeleteBtnAction(exp_id);
	setDiscardChangesBtn(exp_id);
}


/* ================== BUTTONS ACTIONS ================= */

function setDiscardChangesBtn(exp_id) {
	$("#discardChangesBtn_" + exp_id).on("click", function(e) {
		e.preventDefault();
		updateExperience(all_experiences.get(exp_id));
	});
}

function setDeleteBtnAction(exp_id) {
	$("#deleteBtn_" + exp_id).on("click", function(e) {
		e.preventDefault();
		deleteExperience(exp_id);
	});
}

function setEditBtnActions(exp_id) {
	$("#editBtn_" + exp_id).on("click", function(e) {
		e.preventDefault();
		enableFields(exp_id);
		hideDeleteBtn(exp_id);
		hideEditBtn(exp_id);
		showSaveBtn(exp_id);
		showDiscardChangesBtn(exp_id);
	});
}

function setSaveBtnAction(exp) {
	$("#saveBtn_" + exp.id).on("click", function(e) {
		e.preventDefault();
		// TODO: Ask if User wants to edit the experience with a friendly pop-up!
		new_data = fetchNewData(exp);
		//console.log(new_data);

		updateExperience(new_data);

	})
}


function fetchNewData(exp) {

	new_title = $("#title_" + exp.id).val();
	new_dateStart = $("#dpFrom_" + exp.id).val();
	to_present = $("#toPresent_" + exp.id).prop("checked");
	new_dateEnd = to_present ? "PRESENT" : $("#dpTo_" + exp.id).val();
	new_place = $("#place_" + exp.id).val();
	new_type = $("#typeSelect_" + exp.id).val(); // WORK = 1, EDUCATION = 2
	new_description = $("#description_" + exp.id).val();

	new_data = {
		id: exp.id,
		userId: exp.userId,
		title: new_title,
		startDate: new_dateStart,
		endDate: new_dateEnd,
		place: new_place,
		type: new_type,
		description: new_description
	};

	return new_data;

}

function updateExperience(new_experience_data) {

	$.ajax({

		url: "/update_experience_action",
		contentType: "application/json",
		data: JSON.stringify(new_experience_data),
		type: "post",
		dataType: "json",

	}).done(function(data) {

		all_experiences.set(new_experience_data.id, new_experience_data);
		updateExperienceDiv(new_experience_data);

	});

}

function addExperience(new_experience) {

	$.ajax({

		url: "/create_experience_action",
		contentType: "application/json",
		data: JSON.stringify(new_experience),
		type: "post",
		dataType: "json",

	}).done(function(data) {

		all_experiences.set(data.id, data);
		initExperience(data, data.id, false);
		cleanAddNewExperienceFields();
		hideNewExperienceDiv();

	});

}

function deleteExperience(exp_id) {

	$.ajax({

		url: "/delete_experience_action",
		contentType: "application/json",
		data: JSON.stringify(exp_id),
		type: "post",
		dataType: "json",

	}).done(function(data) {

		$("#experience_" + exp_id).remove();
		//init();

	});

}

/* ================== END BUTTONS ACTIONS ================= */


/* ======================== SHOW/HIDE CONTENT ======================== */

function cleanAddNewExperienceFields() {
	$("#experienceTitle").val("");
	$("#datePickerFrom").val("");
	$("#datePickerTo").val("");
	$("#experiencePlace").val("");
	$("#experienceType").val("");
	$("#experienceDescription").val("");
}

function setFieldsDisabled(id, enable) {
	$("#title_" + id).attr('readonly', enable);
	$("#dpFrom_" + id).attr('readonly', enable);
	$("#dpTo_" + id).attr('readonly', enable);
	$("#toPresent_" + id).prop('disabled', enable);
	$("#place_" + id).attr('readonly', enable);
	$("#typeSelect_" + id).prop('disabled', enable);
	$("#description_" + id).attr('readonly', enable);
}

function disableFields(id) {
	setFieldsDisabled(id, true);
}

function enableFields(id) {
	setFieldsDisabled(id, false);
}

function hideDiscardChangesBtn(id) {
	$("#discardChangesBtn_" + id).hide();
}

function showDiscardChangesBtn(id) {
	$("#discardChangesBtn_" + id).show();
}

function hideDeleteBtn(id) {
	$("#deleteBtn_" + id).hide();
}

function showDeleteBtn(id) {
	$("#deleteBtn_" + id).show();
}

function hideSaveBtn(id) {
	$("#saveBtn_" + id).hide();
}

function showSaveBtn(id) {
	$("#saveBtn_" + id).show();
}

function hideEditBtn(id) {
	$("#editBtn_" + id).hide();
}

function showEditBtn(id) {
	$("#editBtn_" + id).show();
}

function hideNewExperienceDiv() {
	$("#newExperienceContainer").hide();
}

function showNewExperienceDiv() {
	$("#newExperienceContainer").show();
}

function checkToPresent(id) {
	$('#toPresent_' + id).prop("checked", true);
}

function uncheckToPresent(id) {
	$('#toPresent_' + id).prop("checked", false);
}

/* ======================== END SHOW/HIDE CONTENT ======================== */


/* ========================= BUILD GUI ============================ */

function updateExperienceDiv(exp) {
	$("#experience_" + exp.id).empty();
	initExperience(exp, exp.id, true);
}

function buildExperience(exp, is_updating) {

	experiences_container = $("#curriculumExperiencesContainer");

	exp_card_container = buildCardContainer(exp);

	exp_card = buildCard(exp);

	exp_card_header = buildCardHeader(exp.type);
	exp_card_body = buildCardBody(exp);

	title_div = buildTitleDiv(exp);
	dp_from_div = buildDatePickerFromDiv(exp);
	dp_to_div = buildDatePickerToDiv(exp);
	place_div = buildPlaceDiv(exp);
	type_div = buildTypeDiv(exp);
	description_div = buildDescriptionDiv(exp);
	buttons_div = buildButtonsDiv(exp);

	if (!is_updating) {
		experiences_container.append(exp_card_container);
	}

	$("#experience_" + exp.id).append(exp_card);

	$("#expCard_" + exp.id).append(exp_card_header);
	$("#expCard_" + exp.id).append(exp_card_body);

	$("#cardBody_" + exp.id).append(title_div);
	$("#cardBody_" + exp.id).append(dp_from_div);
	$("#cardBody_" + exp.id).append(dp_to_div);
	$("#cardBody_" + exp.id).append(place_div);
	$("#cardBody_" + exp.id).append(type_div);
	$("#cardBody_" + exp.id).append(description_div);
	$("#cardBody_" + exp.id).append(buttons_div);

}

function buildCardBody(exp) {
	div_id = "cardBody_" + exp.id;
	return `<div class="card-body">
				<div class="row" id="${div_id}">
				</div>
			</div>`;
}

function buildCardHeader(type) {
	div_class = "card-header py-3 ";
	div_class += type === "WORK" ? "work-experience" : "education-experience";
	console.log(type);
	return `<div class="${div_class}">
				<h6 class="text-light fw-bold m-0">Experience</h6>
			</div>`;
}

function buildCard(exp) {
	div_id = "expCard_" + exp.id;
	return `<div class="card shadow mb-4" id="${div_id}">
			</div>`;
}

function buildCardContainer(exp) {
	div_id = "experience_" + exp.id;
	return `<!-- A CURRICULUM EXPERIENCE -->
			<div class="col-12 col-lg-6" id="${div_id}">
			`;
}

function buildButtonsDiv(exp) {
	edit_btn = "editBtn_" + exp.id;
	delete_btn = "deleteBtn_" + exp.id;
	discard_btn = "discardChangesBtn_" + exp.id;
	save_btn = "saveBtn_" + exp.id;
	return `<!-- BUTTONS -->
			<div class="row">
				<div class="col-12">
					<button class="btn btn-primary btn-sm" type="submit"
						id="${edit_btn}">Edit</button>
					<button class="btn btn-danger btn-sm" type="submit"
						id="${delete_btn}">Delete</button>
					<button class="btn btn-danger btn-sm" type="submit"
						id="${discard_btn}">Discard changes</button>
					<button class="btn btn-primary btn-sm" type="submit"
						id="${save_btn}">Save Settings</button>
				</div>
			</div>
			<!-- END BUTTONS -->`;
}

function buildDescriptionDiv(exp) {
	div_id = "description_" + exp.id;
	description = exp.description == undefined ? "" : exp.description;
	return `<!-- DESCRIPTION -->
			<div class="col-12 mb-3">
				<div class="row">
					<h4 class="small fw-bold">Description</h4>
				</div>
				<textarea class="form-control" id="${div_id}"
					rows="4" name="experience_description">${description}</textarea>
			</div>
			<!-- END DESCRIPTION -->`;
}

function buildTypeDiv(exp) {
	div_id = "type_" + exp.id;
	select_id = "typeSelect_" + exp.id;
	secondary_exp = exp.type === "WORK" ? "EDUCATION" : "WORK";
	return `<!-- TYPE -->
			<div class="col-12 col-sm-6 mb-3" id="${div_id}">
				<div class="row">
					<h4 class="small fw-bold">Type</h4>
				</div>
				<select class="form-select" aria-label="Work"
					id="${select_id}">
					<option value="${exp.type}">${exp.type}</option>
					<option value="${secondary_exp}">${secondary_exp}</option>
				</select>
			</div>
			<!-- END TYPE -->`;
}

function buildPlaceDiv(exp) {
	div_id = "place_" + exp.id;
	return `<!-- PLACE -->
			<div class="col-12 col-sm-6 mb-3">
				<div class="row">
					<h4 class="small fw-bold">Place</h4>
				</div>
				<input class="form-control" type="text" id="${div_id}"
					placeholder="experience_place" name="experience_place"
					value="${exp.place}">
			</div>
			<!-- END PLACE -->`;
}

function buildDatePickerFromDiv(exp) {
	div_id = "dpFromContainer_" + exp.id;
	dp_id = "dpFrom_" + exp.id;
	return `<!-- DATE PICKER FROM -->
			<div id="${div_id}"
				class="col-12 col-sm-6 mb-3 md-form md-outline input-with-post-icon datepicker">
				<label class="datePickerLabel" for="${div_id}"><strong>From</strong></label>
				<input placeholder="Select date" type="date"
					id="${dp_id}" name="experienceDateFrom"
					class="form-control form-control-user"
					value="${exp.startDate}" pattern="yyyy-MM-dd">
			</div>
			<!-- END DATE PICKER FROM -->`;
}

function buildDatePickerToDiv(exp) {
	div_id = "dpToContainer_" + exp.id;
	dp_id = "dpTo_" + exp.id;
	to_present_div = "toPresent_" + exp.id;
	return `<!-- DATE PICKER TO -->
			<div id="${div_id}"
				class="col-12 col-sm-6 mb-3 md-form md-outline input-with-post-icon datepicker">
				<label class="datePickerLabel" for="${div_id}"><strong>To</strong></label>
				<input placeholder="Select date" type="date"
					id="${dp_id}" name="experienceDateTo"
					class="form-control form-control-user" value="${exp.endDate}"> <input
					class="form-check-input" type="checkbox" id="${to_present_div}"><label
					class="form-check-label" for="${to_present_div}"><strong>Present</strong></label>
			</div>
			<!-- END DATE PICKER TO -->`;
}

function buildTitleDiv(exp) {
	div_id = "title_" + exp.id;
	return `<!-- TITLE -->
			<div class="col-12 mb-3">
				<div class="row">
					<h4 class="small fw-bold">Experience Title</h4>
				</div>
				<input class="form-control" type="text" id="${div_id}"
					placeholder="Experience" name="experience_title"
					value="${exp.title}">
			</div>
			<!-- END TITLE -->`;
}

/* ========================= END BUILD GUI ============================ */