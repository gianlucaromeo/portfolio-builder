emailjs.init("user_8pSYeKRzRGlH0I3n8Mv49"); 
function sendEmail(receiver,password) {
	
		var values={
		token: $("#username").val()
		};
	
		$.ajax({
		url: "/get_token",
		contentType: "application/json",
		data: JSON.stringify(values),
		type: "post",
		dataType: "json"
		}).done(function(data) {
			var token=data.token;
			var html='<a href="http://localhost:8080/users/'+token+'/password_reset">Click Here to reset your password</a>';
			var templateParams = {
	  			   from_name: "DigitalES",
	  			   to_name: receiver,
	  			   current_password: password,
				   my_html:html
	  			};
	  		emailjs.send("service_p1x7zca","template_m85gci9", templateParams)
	  	    .then(function(response) {
	        console.log('SUCCESS!', response.status, response.text);
			alert("An email with your password has been sent to the email associated with your username");
	  		}, function(error) {
	  	       console.log('FAILED...', error);
	  		});
		});	
		
		
	   };

function sendForgotPassword(){
	var user=$("#username");
	var values={
		username: user.val(),
		email: "",
		password: ""
	};
	$.ajax({
		url: "/get_password_and_email_from_username",
		contentType: "application/json",
		data: JSON.stringify(values),
		type: "post",
		dataType: "json",
		error: function() {alert("The selected username does not exist");},
	}).done(function(data) {
		if(data.email!="")
			sendEmail(data.email,data.password);
	});	
};

function resetPassword(){
	var password=$("#password");
	var repeatPassword=$("#repeatPassword");
	var token=$("#tokenValue");
	var values={
		password: password.val(),
		repeatPassword: repeatPassword.val(),
		token: token.val()
	};
	console.log(values);
/*	$.ajax({
		url: "/reset_password",
		contentType: "application/json",
		data: JSON.stringify(values),
		type: "post",
		dataType: "json"
	}).done(function(data) {
		if(checkPasswordMatch(password,repeatPassword))
			alert("Password successfully changed, You'll now be redirected to the login page'");
	});	*/
}

function checkPasswordMatch(password,repeatPassword) {
	if (password.val() !== repeatPassword.val()) {
		password.addClass("is-invalid");
		repeatPassword.addClass("is-invalid");
		return false;
	}
	return true;
}
