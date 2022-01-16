emailjs.init("user_8pSYeKRzRGlH0I3n8Mv49");

function sendEmail(receiver) {

	var values = {
		token: $("#username").val()
	};

	$.ajax({
		url: "/get_token",
		contentType: "application/json",
		data: JSON.stringify(values),
		type: "post",
		dataType: "json"
	}).done(function(data) {
		var token = data.token;
		console.log(data.token);
		var html = '<a href="http://localhost:8080/users/' + token + '/password_reset">Click Here to reset your password</a>';
		var templateParams = {
			from_name: "DigitalES",
			to_name: receiver,
			my_html: html
		};
		emailjs.send("service_p1x7zca", "template_m85gci9", templateParams)
			.then(function(response) {
				console.log('SUCCESS!', response.status, response.text);
				alert("An email with your password has been sent to the email associated with your username");
			}, function(error) {
				console.log('FAILED...', error);
			});
	});


};

function sendForgotPassword() {
	var user = $("#username");
	var values = {
		username: user.val(),
		email: "",
	};
	$.ajax({
		url: "/get_email_from_username",
		contentType: "application/json",
		data: JSON.stringify(values),
		type: "post",
		dataType: "json",
		error: function() { alert("The selected username does not exist"); },
	}).done(function(data) {
		if (data.email != "")
			sendEmail(data.email);
	});
};

function resetPassword() {
	var password = $("#password");
	var repeatPassword = $("#repeatPassword");
	var token = $("#tokenValue");
	var values = {
		password: password.val(),
		repeatPassword: repeatPassword.val(),
		token: token.val()
	};
	console.log(values);
	$.ajax({
		url: "/reset_password",
		contentType: "application/json",
		data: JSON.stringify(values),
		type: "post",
		dataType: "json"
	}).done(function(data) {
		console.log(data);
		if (checkPasswordMatch(password, repeatPassword) && checkPasswordValid(password, repeatPassword, data.password)) {
			alert("Your Password has been updated successfully");
			password.removeClass("is-invalid");
			repeatPassword.removeClass("is-invalid");
			password.attr("readonly", true);
			repeatPassword.attr("readonly", true);
		}
	});
}

function checkPasswordMatch(password, repeatPassword) {
	if (password.val() !== repeatPassword.val()) {
		password.addClass("is-invalid");
		repeatPassword.addClass("is-invalid");
		return false;
	}
	return true;
}

function checkPasswordValid(password, repeatPassword, value) {
	if (value == "error") {
		password.addClass("is-invalid");
		repeatPassword.addClass("is-invalid");
		return false;
	}
	return true;
}
