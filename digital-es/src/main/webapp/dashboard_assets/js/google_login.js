
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

			var email= googleUser.getBasicProfile().getEmail();
			doLogin(email);

		}, function(error) {
		
		});

}


function doLogin(email){
	$.ajax({
		url: "/google_login",
		contentType: "application/json",
		data: JSON.stringify(email),
		type: "post",
		dataType: "json",
	}).done(function(data) {
		console.log("ciao");
	});
}

setGoogleSignUpAPI();


