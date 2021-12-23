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