/*
 * "experience" refers to the current experience 
 * this file is working on.
 *
 * To dynamically add an experience, I'll use the following ids inside the jsp file:
 *  - experienceId: id (stored in database) of the experience.
 *  - experienceTitle: title of the experience.
 *  - datePickerFrom: date start of the experience.
 *  - datePickerTo: date end of the experience.
 *  - toPresent: check box to check if the experience ended.
 *  - experiencePlace: the place the user had the experience at.
 *  - experienceType: the type of the experience. Must be "Work" or "Education".
 *  - experienceDescription: the description of the experience.
 *  - editBtn: ...
 *  - deleteBtn: ...
 *  - saveBtn: ...
 *  - discardChangesBtn: ...
 *  - addExperienceBtn: ...
 **/

window.onload = function() {

	hideSaveBtn();
	hideDiscardChangesBtn();
	disableFields();

	$.ajax({

		url: "/get_curriculum_data_action",
		type: "post",
		dataType: "json",

	}).done(function(data) {

		experiences = data;

		if (experiences.length == 0) {
			console.log("no data");
		} else {
			experiences.forEach(exp => buildExperience(exp));
		}

	});

}


function buildExperience(exp) {

	experiences_container = $("#curriculumExperiencesContainer");
	
	exp_card_container = buildCardContainer(exp);
	
	exp_card = buildCard(exp);
	
	exp_card_header = buildCardHeader();
	exp_card_body = buildCardBody(exp);

	title_div = buildTitleDiv(exp);
	dp_from_div = buildDatePickerFromDiv(exp);
	dp_to_div = buildDatePickerToDiv(exp);
	place_div = buildPlaceDiv(exp);
	type_div = buildTypeDiv(exp);
	description_div = buildDescriptionDiv(exp);
	buttons_div = buildButtonsDiv(exp);
	
	experiences_container.append(exp_card_container);
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

function buildCardHeader() {
	return `<div class="card-header py-3">
				<h6 class="text-primary fw-bold m-0">Experience</h6>
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
					<option value="1">${exp.type}</option>
					<option value="2">${secondary_exp}</option>
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
	div_id = "dpFormContainer_" + exp.id;
	dp_id = "dpFrom_" + exp.id;
	return `<!-- DATE PICKER FROM -->
			<div id="${div_id}"
				class="col-12 col-sm-6 mb-3 md-form md-outline input-with-post-icon datepicker">
				<label class="datePickerLabel" for="${div_id}"><strong>From</strong></label>
				<input placeholder="Select date" type="date"
					id="${dp_id}" name="experienceDateFrom"
					class="form-control form-control-user"
					value="${exp.startDate}">
			</div>
			<!-- END DATE PICKER FROM -->`;
}

function buildDatePickerToDiv(exp) {
	div_id = "dpToContainer_" + exp.id;
	dp_id = "dpTo_" + exp.id;
	end_date = exp.endDate === undefined ? '' : exp.endDate;
	return `<!-- DATE PICKER TO -->
			<div id="${div_id}"
				class="col-12 col-sm-6 mb-3 md-form md-outline input-with-post-icon datepicker">
				<label class="datePickerLabel" for="${div_id}"><strong>To</strong></label>
				<input placeholder="Select date" type="date"
					id="${dp_id}" name="experienceDateTo"
					class="form-control form-control-user" value="${end_date}"> <input
					class="form-check-input" type="checkbox" id="toPresent"><label
					class="form-check-label" for="toPresent"><strong>Present</strong></label>
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


/* ================== GUI ACTIONS ================= */

$("#editBtn").on("click", function(e) {

	e.preventDefault();

	enableFields();

	hideDeleteBtn();
	hideEditBtn();

	showSaveBtn();
	showDiscardChangesBtn();

});

/* ================== END GUI ACTIONS ================= */


/* ======================== SHOW/HIDE CONTENT ======================== */

function setFieldsDisabled(enable) {
	$("#experienceTitle").attr('readonly', enable);
	$("#datePickerFrom").attr('readonly', enable);
	$("#datePickerTo").attr('readonly', enable);
	$("#toPresent").prop('disabled', enable);
	$("#experiencePlace").attr('readonly', enable);
	$("#experienceType").prop('disabled', enable);
	$("#experienceDescription").attr('readonly', enable);
}

function disableFields() {
	setFieldsDisabled(true);
}

function enableFields() {
	setFieldsDisabled(false);
}

function hideDiscardChangesBtn() {
	$("#discardChangesBtn").hide();
}

function showDiscardChangesBtn() {
	$("#discardChangesBtn").show();
}

function hideDeleteBtn() {
	$("#deleteBtn").hide();
}

function showDeleteBtn() {
	$("#deleteBtn").show();
}

function hideSaveBtn() {
	$("#saveBtn").hide();
}

function showSaveBtn() {
	$("#saveBtn").show();
}

function hideEditBtn() {
	$("#editBtn").hide();
}

function showEditBtn() {
	$("#editBtn").show();
}

/* ======================== END SHOW/HIDE CONTENT ======================== */