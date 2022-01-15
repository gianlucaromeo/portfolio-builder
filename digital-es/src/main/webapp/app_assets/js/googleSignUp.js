
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
			checkGoogleUserExists($("#inputEmail").val());
			setPasswordFields();
			setUsernameField();

			disableInputFields();
			addLabelOnDatePicker();
			
		}, function(error) {
			
		});

}
function checkGoogleUserExists(email) {
	console.log("check");

	$.ajax({

		url: "/check_google_user_exists",
		contentType: "application/json",
		data: JSON.stringify(email),
		type: "post",
		dataType: "json",

	}).done(function(data) {
		console.log("user:" + data);

		if (data !== null) {
			login(data);
		}else{
			console.log("not exists");
		}
	});

}
function login(email) {
	$.ajax({
		url: "/google_login",
		contentType: "application/json",
		data: JSON.stringify(email),
		type: "post",
		dataType: "json"
	}).done(function(data) {
		console.log(data);
		if (data === "ok") {
			window.location.href = "/dashboard/profile";
		}
	});
}

var signUpWithGoogleChoose = false;
function disableInputFields() {
	$("#inputEmail").attr('readonly', true);
	$("#firstName").attr('readonly', true);
	$("#lastName").attr('readonly', true);
	$("#username").attr('readonly', true);
	$("#passwordInput").attr('readonly', true);
	$("#repeatPasswordInput").attr('readonly', true);
	signUpWithGoogleChoose = true;
}

function addLabelOnDatePicker() {
	$("#dateContainer").append(`<div id="dateInvalid" class="text-danger">
			Please enter your date of birth before continuing...
		</div>`);
}

var generatedPassword = generatePassword();

function setPasswordFields() {
	$("#passwordInput").val(generatedPassword);
	$("#repeatPasswordInput").val(generatedPassword);
}

function generatePassword() {
	
	var pass = '';
	
	var upperCase='ABCDEFGHIJKLMNOPQRSTUVWXYZ';
	var lowerCase = 'abcdefghijklmnopqrstuvwxyz';
	var numbers = '0123456789';
	var specialChar='@$!%*?';
		

	for (i = 1; i <= 4; i++) {
		var idx1 = Math.floor(Math.random()
			* upperCase.length + 1);
		var idx2 = Math.floor(Math.random()
			* lowerCase.length + 1);
		var idx3 = Math.floor(Math.random()
			* specialChar.length + 1);
		var idx4 = Math.floor(Math.random()
			* numbers.length + 1);
		pass += upperCase.charAt(idx1);
		pass += lowerCase.charAt(idx2);
		pass += specialChar.charAt(idx3);
		pass += numbers.charAt(idx4);

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