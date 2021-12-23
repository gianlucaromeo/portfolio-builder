//$('.datepicker').datepicker();

signUpBtn = $("#signUpBtn");
signUpForm = $("#signUpForm");

emailjs.init("user_8pSYeKRzRGlH0I3n8Mv49"); //please encrypted user id for malicious attacks
function sendEmail(receiver) {
	var templateParams = {
		from_name: "DigitalES",
		to_name: receiver,
		message: "Hello, if you want to confirm your registration, please click the link below",
		my_html: '<a href="https://www.google.com">Test</a>'
		//reply_to
	};

	emailjs.send("service_p1x7zca", "template_1cxbziq", templateParams)
		.then(function(response) {
			console.log('SUCCESS!', response.status, response.text);
		}, function(error) {
			console.log('FAILED...', error);
		});
};
//sendEmail("mymail@gmail.com");  //testing if the email is sent when page loaded


signUpBtn.submit(function(e) {
	console.log("signUpBtn on submit()");

	e.preventDefault();

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

	$("#passwordsDontMatch").remove();
	if (password.val() !== repeatPassword.val()) {
		password.addClass("is-invalid");
		repeatPassword.addClass("is-invalid");
		$("#passwordsContainer").append(
			`<div id="passwordsDontMatch" class="text-danger">
					Passwords does't match
			</div>`);
	}

	console.log(fields);

	fields.forEach(field => field.removeClass("is-invalid"));

	var fieldsAreValid = true;
	fields.forEach(field => {
		if (field.val() == null || field.val() === "") {
			field.addClass("is-invalid");
			fieldsAreValid = false;
		}
	});

	if (!fieldsAreValid) {
		console.log("form is not correct");
	} else {
		sendData(fields);
	}

});





function sendData(fields) {

	var firstName = $("#firstName");
	var lastName = $("#lastName");
	var dateOfBirth = $("#datePicker");
	var email = $("#inputEmail");
	var username = $("#username");
	var password = $("#passwordInput");
	var repeatPassword = $("#repeatPasswordInput");

	console.log(fields[0].val());
	console.log(fields[1].val());
	console.log(fields[2].val());
	console.log(fields[3].val());
	console.log(fields[4].val());
	console.log(fields[5].val());

	let userData = {
		firstName: fields[0].val(),
		lastName: fields[1].val(),
		dateOfBirth: fields[2].val(),
		email: fields[3].val(),
		username: fields[4].val(),
		password: fields[5].val()
	}

	$.ajax({
		url: "/sign_up_data_validation",
		contentType: "application/json",
		data: JSON.stringify(userData),
		type: "post",
		dataType: "json",
	}).done(function(data) {

		fields.forEach(field => field.removeClass("is-invalid"));
		var correctData = true;


		$("#passwordInvalid").remove();
		$("#usernameInvalid").remove();
		$("#nameInvalid").remove();
		$("#emailInvalid").remove();
		$("#dateInvalid").remove();


		//************************** */CHECK FIRST NAME AND LAST NAME ****************************************************//
		if (data.firstNameResp === "error") {
			firstName.addClass("is-invalid");
			$("#nameContainer").append(
				`<div id="nameInvalid" class="text-danger">
					First name must start with a capital letter
			</div>`);
			correctData = false;
		} else if (data.firstNameResp === "ok")
			firstName.addClass("is-valid");

		if (data.lastNameResp === "error") {
			lastName.addClass("is-invalid");
			$("#nameContainer").append(
				`<div id="nameInvalid" class="text-danger">
					Last name must start with a capital letter
			</div>`);
			correctData = false;
		} else if (data.lastNameResp === "ok")
			lastName.addClass("is-valid");

		//************************** */CHECK DATE OF BIRTH ****************************************************//
		if (data.dateOfBirthResp === "error") {
			dateOfBirth.addClass("is-invalid");

			$("#dateContainer").append(
				`<div id="dateInvalid" class="text-danger">
					You must be at least 18 years old
			</div>`);
			correctData = false;
		} else if (data.dateOfBirthResp === "ok")
			dateOfBirth.addClass("is-valid");

		//************************** */CHECK EMAIL ****************************************************//
		if (data.emailResp === "email duplicated error") {
			email.addClass("is-invalid");
			$("#emailContainer").append(
				`<div id="emailInvalid" class="text-danger">
						Email already used
				</div>`);
			correctData = false;
		} else if (data.emailResp === "ok")
			email.addClass("is-valid");

		//************************** */CHECK PASSWORD ****************************************************//
		if (data.passwordResp === "error") {
			password.addClass("is-invalid");
			repeatPassword.addClass("is-invalid");
			correctData = false;
			$("#passwordsContainer").append(
				`<div id="passwordInvalid" class="text-danger">
						Password must contain:
						<br>- at least eight characters and at most twenty caracters
						<br>- at least one number [0-9]
						<br>- both lower and uppercase letters 
						<br>- at least a special character [@$!%*?&]
				</div>`);
		} else if (data.passwordResp === "ok") {
			password.addClass("is-valid");
			repeatPassword.addClass("is-valid");
		}


		//************************** */CHECK USERNAME ****************************************************//
		switch (data.usernameResp) {
			case "error":
				username.addClass("is-invalid");
				correctData = false;
				$("#usernameContainer").append(
					`<div id="usernameInvalid" class="text-danger">
						Username must contain:
						<br>- at least eight characters
						<br>- only letters and numbers
				</div>`);
				break;

			case "username duplicated error":
				username.addClass("is-invalid");
				correctData = false;
				$("#usernameContainer").append(
					`<div id="usernameInvalid" class="text-danger">
						Username already exists
				</div>`);
				break;

			case "username length error":
				username.addClass("is-invalid");
				correctData = false;
				$("#usernameContainer").append(
					`<div id="usernameInvalid" class="text-danger">
						Username must be at least 8 characters
				</div>`);
				break;

			case "ok":
				username.addClass("is-valid");
				break;

			default:
				break;

		}



		//if data is correct we can do the registration
		if (correctData)
			signUpForm.submit();

	})




}









