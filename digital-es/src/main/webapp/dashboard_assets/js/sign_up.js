//$('.datepicker').datepicker();

signUpBtn = $("#signUpBtn");
signUpForm = $("#signUpForm");

var firstName = $("#firstName");
var lastName = $("#lastName");
var dateOfBirth = $("#datePicker");
var email = $("#inputEmail");
var username = $("#username");
var password = $("#passwordInput");
var repeatPassword = $("#repeatPasswordInput");

var fields = [
	firstName,
	lastName,
	dateOfBirth,
	email,
	username,
	password,
	repeatPassword
];

function checkPasswordMatch() {
	$("#passwordsDontMatch").remove();
	if (password.val() !== repeatPassword.val()) {
		password.addClass("is-invalid");
		repeatPassword.addClass("is-invalid");
		addPasswordDontMatchDiv();
		return false;
	}
	return true;
}

signUpBtn.on("click", function(e) {

	e.preventDefault();

	fields.forEach(field => field.removeClass("is-invalid"));

	var fieldsAreValid = true;
	fields.forEach(field => {
		if (field.val() == null || field.val() === "") {
			field.addClass("is-invalid");
			fieldsAreValid = false;
		}
	});

	if (checkPasswordMatch()) {
		if (fieldsAreValid) {
			sendData(fields);
		}	
	}

});


var userDataValidated = true;

function sendData(fields) {

	let userData = {
		firstName: fields[0].val(),
		lastName: fields[1].val(),
		dateOfBirth: fields[2].val(),
		email: fields[3].val(),
		username: fields[4].val(),
		password: fields[5].val()
	}


	/*
	 * Invia una richiesta al Server per controllare
	 * che i dati inseriti dall'Utente rispettino
	 * determinati formati.
	 *
	 * Il Server manderà una risposta, indicando
	 * se i campi inseriti sono corretti e, eventualmente,
	 * specificando quali errori si sono verificati.
	 * Ad esempio: {..username: "username already exists"}.
	 *
	 **/
	$.ajax({

		url: "/sign_up_data_validation",
		contentType: "application/json",
		data: JSON.stringify(userData),
		type: "post",
		dataType: "json",

	}).done(function(data) {

		fields.forEach(field => field.removeClass("is-invalid"));

		removeErrorLabels();

		userDataValidated = true;
		checkPasswordValidation(data);
		checkFirstNameValidation(data);
		checkLastNameValidation(data);
		checkUsernameValidation(data);
		checkDateOfBirthValidation(data);
		checkEmailValidation(data);
		
		if (userDataValidated) {
			signUpForm.submit();
		}

	})

}

/**
 * Rimuove tutte le Label
 * che erano state aggiunte per mostrare
 * eventuali errori all'Utente.
 * */
function removeErrorLabels() {
	$("#passwordInvalid").remove();
	$("#usernameInvalid").remove();
	$("#nameInvalid").remove();
	$("#nameInvalid").remove();
	$("#emailInvalid").remove();
	$("#dateInvalid").remove();
}


function checkFirstNameValidation(data) {
	
	if (data.firstNameResp === "error") {
		firstName.addClass("is-invalid");
		addNameNotValidDiv("First Name");
		userDataValidated = false;
	} else if (data.firstNameResp === "ok") {
		firstName.addClass("is-valid");
	}
}


function checkLastNameValidation(data) {
	
	if (data.lastNameResp === "error") {
		lastName.addClass("is-invalid");
		addNameNotValidDiv("Last Name");
		userDataValidated = false;
	} else if (data.lastNameResp === "ok") {
		lastName.addClass("is-valid");
	}
	
}

function checkUsernameValidation(data) {
	
	username.addClass("is-invalid");
	
	switch (data.usernameResp) {
		
		case "error":
			userDataValidated = false;
			addUsernameNotValidDiv("Please check your username and try again.")
			break;
			
		case "username duplicated error":
			userDataValidated = false;
			addUsernameNotValidDiv("Username already exists.")
			break;
			
		case "username length error":
			userDataValidated = false;
			addUsernameNotValidDiv(
				"Username must contains at least 8 characters." 
			  + "\nUsername cannot contain more than 20 characters.")
			break;
			
		case "ok":
			username.removeClass("is-invalid");
			username.addClass("is-valid");
			break;
			
		default:
			break;
			
	}
	
}

function checkDateOfBirthValidation(data) {

	if (data.dateOfBirthResp === "error") {
		dateOfBirth.addClass("is-invalid");
		addDateOfBirthNotValidDiv();
		userDataValidated = false;
	} else if (data.dateOfBirthResp === "ok") {
		dateOfBirth.addClass("is-valid");
	}
}

function checkEmailValidation(data) {
	if (data.emailResp === "email duplicated error") {
		email.addClass("is-invalid");
		addEmailNotValidDiv();
		userDataValidated = false;
	} else if (data.emailResp === "ok") {
		email.addClass("is-valid");
	}
}

function checkPasswordValidation(data) {
	if (data.passwordResp === "error") {
			password.addClass("is-invalid");
			repeatPassword.addClass("is-invalid");
			userDataValidated = false;
			addPasswordNotValidDiv();
		} else if (data.passwordResp === "ok") {
			password.addClass("is-valid");
			repeatPassword.addClass("is-valid");
		}
}


function addPasswordDontMatchDiv() {
	$("#passwordsContainer").append(
		`<div id="passwordsDontMatch" class="text-danger">
			Please check passwords match and try again.
		</div>`
	);
}

function addNameNotValidDiv(fieldName) {
	$("#nameContainer").append(
		`<div id="nameInvalid" class="text-danger">
			Please check the ${fieldName} field and try again.
	     </div>`
	 );
}

function addUsernameNotValidDiv(errorText) {
	$("#usernameContainer").append(
		`<div id="usernameInvalid" class="text-danger">
			${errorText}
		</div>`
	);
}

function addDateOfBirthNotValidDiv() {
	$("#dateContainer").append(
		`<div id="dateInvalid" class="text-danger">
			You must be at least 18 years old.
		</div>`
	);
}

function addEmailNotValidDiv() {
	$("#emailContainer").append(
		`<div id="emailInvalid" class="text-danger">
			Email already used. Please try again.
		</div>`
	);
}

function addPasswordNotValidDiv() {
	$("#passwordsContainer").append(
		`<div id="passwordInvalid" class="text-danger">
			Password must contain:
			<br>- At least 8 characters and at most 20 characters.
			<br>- At least one digit [0-9].
			<br>- Both lower and uppercase letters .
			<br>- At least a special character [@$!%*?&].
		</div>`
	);
}






