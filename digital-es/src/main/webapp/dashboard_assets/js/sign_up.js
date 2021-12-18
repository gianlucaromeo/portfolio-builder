//$('.datepicker').datepicker();

signUpform = $("#signUpForm");

console.log(signUpForm);

signUpform.submit(function(e) {

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
					Please provide a valid city.
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
		url: "/sign_up_action",
		contentType: "application/json",
		data: JSON.stringify(userData),
		type: "post",
		dataType: "json",
	}).done(function(data) {
		
		fields.forEach(field => field.removeClass("is-invalid"));
		
		if(data.firstNameResp==="error")
			firstName.addClass("is-invalid");
		
		if(data.lastNameResp==="error")
			lastName.addClass("is-invalid");
		
		if(data.dateOfBirthResp==="error")
			dateOfBirth.addClass("is-invalid");
		
		if(data.emailResp==="error")
			email.addClass("is-invalid");
		
		if(data.passwordResp==="error"){
			password.addClass("is-invalid");
			repeatPassword.addClass("is-invalid");
		}
			
		
		if(data.usernameResp==="error")
			username.addClass("is-invalid");
		
		console.log(data);
	})

}









