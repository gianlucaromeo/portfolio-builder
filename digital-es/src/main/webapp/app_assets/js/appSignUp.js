/*
 * At the beginning, we have multiple divs in our jsp
 * that we want to hide when the user first goes to the sign up page.
 * We hide them here and then "toggle" those we need to show.
 **/
hideErrorsDiv();

signUpBtn = $("#signUpBtn"); // Check signup_fields..
signUpForm = $("#signUpForm"); // ..then submit the form

var firstName = $("#firstName");
var lastName = $("#lastName");
var dateOfBirth = $("#datePicker");
var email = $("#inputEmail");
var username = $("#username");
var password = $("#passwordInput");
var repeatPassword = $("#repeatPasswordInput");

let signup_fields = [
	firstName,
	lastName,
	dateOfBirth,
	email,
	username,
	password,
	repeatPassword
];


signUpBtn.on("click", function(e) {

	e.preventDefault();

	signup_fields.forEach(field => field.removeClass("is-invalid"));

	// Check all the signup_fields are not null and/or empty.
	var signup_fieldsAreValid = true;
	signup_fields.forEach(field => {
		if (field.val() == null || field.val() === "") {
			field.addClass("is-invalid");
			signup_fieldsAreValid = false;
		}
	});

	if (checkPasswordMatch()) {
		if (signup_fieldsAreValid) {
			// Send data to Server and check if the User can be registered
				sendData(signup_fields);	
		}
	}

});


var userDataValidated = true;

function sendData(signup_fields) {

	let userData = {
		firstName: signup_fields[0].val(),
		lastName: signup_fields[1].val(),
		dateOfBirth: signup_fields[2].val(),
		email: signup_fields[3].val(),
		username: signup_fields[4].val(),
		password: signup_fields[5].val()
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

		signup_fields.forEach(field => field.removeClass("is-invalid"));

		removeErrorsDivs();
		hideErrorsDiv();

		userDataValidated = true;
		checkPasswordValidation(data);
		checkFirstNameValidation(data);
		checkLastNameValidation(data);
		checkUsernameValidation(data);
		checkDateOfBirthValidation(data);
		checkEmailValidation(data);
		
		
		if (signUpWithGoogleChoose) {
			
			signUpWithGoogle(userData);
		}
		else if (userDataValidated) {

			signUp(userData);
			
			//signUpForm.submit();

		}

	});

}

function signUpWithGoogle(userData) {

	$.ajax({

		url: "/sign_up_with_google",
		contentType: "application/json",
		data: JSON.stringify(userData),
		type: "post",
		dataType: "json",

	}).done(function(data) {
		if(data==="ok"){
			window.location.href = "/dashboard/profile";
		}
		
	});

}



function signUp(userData) {

	$.ajax({

		url: "/dashboard/sign_up",
		contentType: "application/json",
		data: JSON.stringify(userData),
		type: "post",
		dataType: "json"

	}).done(function(emailConfirmation) {
		
		
		token = emailConfirmation.token;
		sendEmail(userData.email, token);
		setTimeout(() => {
			window.location.href = "/dashboard/email_confirmation_page";
		}, 2000)

		
	});

}

emailjs.init("user_OVRlRtT0ckdLLMRvS39z1");
function sendEmail(receiver, token) {
	
	
	html = `<a href="http://localhost:8080/confirm_email/${token}">Confirm your email!</a>`;
	
	var templateParams = {
		from_name: "Portfolio Builder",
		to_name: receiver,
		message: "Hello, if you want to confirm your registration, please click the link below",
		my_html: html,
		//reply_to
	};

	emailjs.send("service_ia6pdau", "template_0bwkalm", templateParams)
		.then(function(response) {
		}, function(error) {
		});
};

/* ===================== HIDE ERROR DIVS ======================== */

function hideErrorsDiv() {
	hidePasswordDontMatchDiv();
	hidePasswordNotValidDiv();
	hideDateOfBirthNotValidDiv();
	hideEmailNotValidDiv();
}

function hidePasswordDontMatchDiv() {
	$("#passwordsDontMatch").hide();
}

function hidePasswordNotValidDiv() {
	$("#passwordInvalid").hide();
}

function hideDateOfBirthNotValidDiv() {
	$("#dateInvalid").hide();
}

function hideEmailNotValidDiv() {
	$("#emailInvalid").hide();
}

/* ===================== SHOW ERROR DIVS ======================== */

function showPasswordDontMatchDiv() {
	$("#passwordsDontMatch").show();
}

function showPasswordNotValidDiv() {
	$("#passwordInvalid").show();
}

function showDateOfBirthNotValidDiv() {
	$("#dateInvalid").show();
}

function showEmailNotValidDiv() {
	$("#emailInvalid").show();
}

/* ================= ADD ERRORS DIV ================= */

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


/* =========== REMOVE ERROR DIVS =============== */

function removeErrorsDivs() {
	removeUsernameInvalidDiv();
	removeNameInvalidDiv();
	removeNameInvalidDiv(); // twice for both First and Last name
}
function removeDateInvalidDiv() {
	$("#dateInvalid").remove();
}

function removeUsernameInvalidDiv() {
	$("#usernameInvalid").remove();

}

function removeNameInvalidDiv() {
	$("#nameInvalid").remove();
}


/* ========================== CHECK DATA VALIDATION ============== */

function checkPasswordMatch() {
	if (password.val() !== repeatPassword.val()) {
		password.addClass("is-invalid");
		repeatPassword.addClass("is-invalid");
		showPasswordDontMatchDiv();
		return false;
	} else {
		hidePasswordDontMatchDiv();
	}
	return true;
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
		showDateOfBirthNotValidDiv();
		userDataValidated = false;
	} else if (data.dateOfBirthResp === "ok") {
		dateOfBirth.addClass("is-valid");
		hideDateOfBirthNotValidDiv();
	}
}

function checkEmailValidation(data) {
	if (data.emailResp === "email duplicated error") {
		email.addClass("is-invalid");
		showEmailNotValidDiv();
		userDataValidated = false;
	} else if (data.emailResp === "ok") {
		hideEmailNotValidDiv();
		email.addClass("is-valid");
	}
}

function checkPasswordValidation(data) {
	if (data.passwordResp === "error") {
		password.addClass("is-invalid");
		repeatPassword.addClass("is-invalid");
		userDataValidated = false;
		showPasswordNotValidDiv();
	} else if (data.passwordResp === "ok") {
		password.addClass("is-valid");
		repeatPassword.addClass("is-valid");
		hidePasswordNotValidDiv();
	}
}