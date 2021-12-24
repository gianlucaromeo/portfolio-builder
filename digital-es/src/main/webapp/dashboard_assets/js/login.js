//$('.datepicker').datepicker();

loginBtn = $("#loginBtn");
signUpForm = $("#loginForm");

var username = $("#username");
var password = $("#passwordInput");

var fields = [
	username,
	password,
];

loginBtn.on("click", function(e) {

	e.preventDefault();

	fields.forEach(field => field.removeClass("is-invalid"));

	var fieldsAreValid = true;
	fields.forEach(field => {
		if (field.val() == null || field.val() === "") {
			field.addClass("is-invalid");
			fieldsAreValid = false;
		}
	});

	if (fieldsAreValid) {
		sendData(fields);
	}

});


var userLoginDataValidated = true;

function sendData(fields) {

	let userData = {
		username: fields[0].val(),
		password: fields[1].val()
	}

	/*
	 * Invia una richiesta al Server per controllare
	 * se l'utente è già registrato'
	 *
	 * Il Server manderà una risposta, indicando
	 * se i campi inseriti sono corretti e, eventualmente,
	 * specificando quali errori si sono verificati.
	 * Ad esempio: {..username: "username not valid error"}.
	 *
	 **/
	$.ajax({

		url: "/login_data_validation",
		contentType: "application/json",
		data: JSON.stringify(userData),
		type: "post",
		dataType: "json",

	}).done(function(data) {

		console.log(data.username);
		console.log(data.password);
		fields.forEach(field => field.removeClass("is-invalid"));

		removeErrorLabels();

		userLoginDataValidated = true;
		checkPasswordValidation(data);
		checkUsernameValidation(data);
		
		if (userLoginDataValidated) {
			loginForm.submit();
		}

	});

}

/**
 * Rimuove tutte le Label
 * che erano state aggiunte per mostrare
 * eventuali errori all'Utente.
 * */
function removeErrorLabels() {
	$("#passwordInvalid").remove();
	$("#usernameInvalid").remove();
}

function checkUsernameValidation(data) {
	
	username.addClass("is-invalid");
	
	switch (data.usernameResp) {
		
		case "error", "username not valid error":
			userLoginDataValidated = false;
			break;
			
		case "ok":
			username.removeClass("is-invalid");
			username.addClass("is-valid");
			break;
			
		default:
			break;
			
	}
	
}

function checkPasswordValidation(data) {
	if (data.passwordResp === "login error" || data.passwordResp == "login password error") {
		password.addClass("is-invalid");
		userLoginDataValidated = false;
	} else if (data.passwordResp === "ok") {
		password.addClass("is-valid");
	}
}








