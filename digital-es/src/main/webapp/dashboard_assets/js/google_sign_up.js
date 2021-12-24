
/*
 * ID Client della nostra App da Google Cloud Platform.
 */
var CLIENT_ID = '666216352369-98lhc8kqedb2mf28ssdknqfqmc4958fv.apps.googleusercontent.com';

var googleUser = {};

/*
 * Carica l'API tramite il nostro Client ID.
 */
function setGoogleSignUpAPI() {

	gapi.load('auth2', function() {
		
		// Retrieve the singleton for the GoogleAuth library and set up the client.
		auth2 = gapi.auth2.init({
			client_id: CLIENT_ID,
			cookiepolicy: 'single_host_origin',
			// Request scopes in addition to 'profile' and 'email'
			//scope: "https://www.googleapis.com/auth/user.birthday.read", 
		});

		attachGoogleLogin(document.getElementById('googleLoginBtn'));

	});

};

/*
 * Collega il pop-up di Google per l'accesso
 * al pulsante "Login With Google".
 */
function attachGoogleLogin(element) {
	
	auth2.attachClickHandler(element, {},
		function(googleUser) {

			$("#firstName").val(googleUser.getBasicProfile().getGivenName());
			$("#lastName").val(googleUser.getBasicProfile().getFamilyName());
			$("#inputEmail").val(googleUser.getBasicProfile().getEmail());

			setPasswordFields();
			setUsernameField();

			disableInputFields();
			addLabelOnDatePicker();

		}, function(error) {
			alert(JSON.stringify(error, undefined, 2));
		});

}

function disableInputFields() {
	$("#inputEmail").prop('disabled', true);
	$("#firstName").prop('disabled', true);
	$("#lastName").prop('disabled', true);
	$("#username").prop('disabled', true);
	$("#passwordInput").prop('disabled', true);
	$("#repeatPasswordInput").prop('disabled', true);
}

function addLabelOnDatePicker() {
	$("#dateContainer").append(`<div id="dateInvalid" class="text-danger">
			Please enter your date of birth before continuing...
		</div>`);
}

function setPasswordFields() {
	let password = generatePassword();
	console.log(password);
	$("#passwordInput").val(password);
	$("#repeatPasswordInput").val(password);
	console.log("PSW: " + $("#passwordInput").val());
}

function generatePassword() {
	
	var pass = '';
	
	var upperCase='ABCDEFGHIJKLMNOPQRSTUVWXYZ';
	var lowerCase = 'abcdefghijklmnopqrstuvwxyz';
	var specialChar='@$!%*?';
		

	for (i = 1; i <= 5; i++) {
		var idx1 = Math.floor(Math.random()
			* upperCase.length + 1);
		var idx2 = Math.floor(Math.random()
			* lowerCase.length + 1);
		pass += str.charAt(char)
		var idx3 = Math.floor(Math.random()
			* specialChar.length + 1);
		pass += upperCase.charAt(idx1);
		pass += lowerCase.charAt(idx2);
		pass += specialChar.charAt(idx3);
	}

	return pass;

}

var generatedUsername = generateUsername();

function setUsernameField() {
	console.log("Generated username: " + generatedUsername);
	$("#username").val(generatedUsername);
}

function generateUsername() {

	$.ajax({
		url: "/sign_up_get_new_google_username",
		contentType: "application/json",
		type: "post",
		dataType: "json",
	}).done(function(data) {
		generatedUsername = data;		
	});

}

window.setGoogleSignUpAPI();