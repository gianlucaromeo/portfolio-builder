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
 **/

window.onload = function() {
	
	console.log("curriculum.jsp loaded")
	
	$.ajax({

		url: "/get_curriculum_data_action",
		//contentType: "application/json",
		// data: JSON.stringify(""),
		type: "post",
		dataType: "json",

	}).done(function(data) {

		console.log(data);

	});
	
}

hideSaveBtn();
hideDiscardChangesBtn();
disableFields();

$("#editBtn").on("click", function(e) { 
	
	e.preventDefault();
	
	enableFields();

	hideDeleteBtn();
	hideEditBtn();

	showSaveBtn();
	showDiscardChangesBtn();
	
});




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