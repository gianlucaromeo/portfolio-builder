emailjs.init("user_8pSYeKRzRGlH0I3n8Mv49"); 
function sendEmail(receiver,password) {
		var templateParams = {
	  			   from_name: "DigitalES",
	  			   to_name: receiver,
	  			   current_password: password
	  			};
	  			
	  		emailjs.send("service_p1x7zca","template_m85gci9", templateParams)
	  	    .then(function(response) {
	        console.log('SUCCESS!', response.status, response.text);
			alert("An email with your password has been sent to the email associated with your username");
	  		}, function(error) {
	  	       console.log('FAILED...', error);
	  		});
	   };

function sendForgotPassword(){
	var username=$("#username");
	var values={
		user: username,
		email: "",
		password: ""
	};
	$.ajax({
		url: "/get_password_and_email_from_username",
		contentType: "application/json",
		data: JSON.stringify(values),
		type: "post",
		dataType: "json",
	}).done(function(data) {
		sendEmail(data.email,data.password);
	});	
};