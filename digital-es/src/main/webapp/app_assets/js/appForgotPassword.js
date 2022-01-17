emailjs.init("user_OVRlRtT0ckdLLMRvS39z1");

function sendEmail(receiver) {
	$("#forgotPasswordBtn").remove();
	$("#username").removeClass("is-invalid");
	$("#username").attr('readonly', true);
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

		var html = '<a href="http://localhost:8080/users/' + token + '/password_reset">Click Here to reset your password</a>';
		var templateParams = {
			from_name: "Portfolio Builder",
			to_name: receiver,
			my_html: html
		};
		emailjs.send("service_ia6pdau", "template_29txnm1", templateParams)
			.then(function(response) {
				window.location.href = "/dashboard/reset_password_email_sent";
			}, function(error) {

			});
	});


};

function setEventOnModal() {

	$("#closeModalBtn").click(function() {
		window.location.href = "/dashboard/login";
	});
}
setEventOnModal();
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
		error: function() { $("#username").addClass("is-invalid"); },
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

	$.ajax({
		url: "/reset_password",
		contentType: "application/json",
		data: JSON.stringify(values),
		type: "post",
		dataType: "json"
	}).done(function(data) {

		if (checkPasswordMatch(password, repeatPassword) && checkPasswordValid(password, repeatPassword, data.password)) {
			password.removeClass("is-invalid");
			repeatPassword.removeClass("is-invalid");
			password.attr("readonly", true);
			repeatPassword.attr("readonly", true);
			$("#modalButton").trigger("click");
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
