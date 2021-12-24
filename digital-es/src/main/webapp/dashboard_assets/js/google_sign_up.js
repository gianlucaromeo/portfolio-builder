/**
 * 
 */

//login con google



var CLIENT_ID='666216352369-98lhc8kqedb2mf28ssdknqfqmc4958fv.apps.googleusercontent.com';

	var googleUser = {};
  	function setGoogleSignUp() {
		console.log("start");
	    gapi.load('auth2', function(){
	      // Retrieve the singleton for the GoogleAuth library and set up the client.
	      	auth2 = gapi.auth2.init({
	        client_id: CLIENT_ID,
	        cookiepolicy: 'single_host_origin',
	        // Request scopes in addition to 'profile' and 'email'
	        //scope: "https://www.googleapis.com/auth/user.birthday.read", 

	      });
	      attachSignin(document.getElementById('googleSignUpBtn'));
	    });
  };

  function attachSignin(element) {
    console.log(element.id);
    auth2.attachClickHandler(element, {},
        function(googleUser) {
		
	
			$("#firstName").val( googleUser.getBasicProfile().getGivenName ());
			$("#lastName").val(googleUser.getBasicProfile().getFamilyName());
			$("#inputEmail").val(googleUser.getBasicProfile().getEmail());
			
			setPasswordFields();
			setUsernameField();
			
			
			console.log(password);
			
			disableInputFields();
			addLabelOnDatePicker();
			
			

        }, function(error) {
          alert(JSON.stringify(error, undefined, 2));
        });

			
         
  }

function disableInputFields(){
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
	$("#passwordInput").val(password);
	$("#repeatPasswordInput").val(password);

}
function setUsernameField() {
	let username = generateUsername();
	$("#username").val(username);
}
function generatePassword() {
	var pass = '';
	var str = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ' +
		'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789@$!%*?';

	for (i = 1; i <= 12; i++) {
		var char = Math.floor(Math.random()
			* str.length + 1);

		pass += str.charAt(char)
	}

	return pass;

}
function generateUsername() {

	let id='';
	let username='';
	
	$.ajax({

		url: "/sign_up_get_next_id_googleRest",
		contentType: "application/json",
		//data: ,
		type: "post",
		dataType: "json",

	}).done(function(data) {
		id= data;
		
		var tempUsername= 'googleUser'+id;
		
		let verified= false;
		while (!verified) {
			$.ajax({

				url: "/sign_up_verify_username_exists_googleRest",
				contentType: "application/json",
				data: JSON.stringify(tempUsername),
				type: "post",
				dataType: "json",

			}).done(function(data) {
				if(data.username==="ok"){
					verified=true;
				}else{
					id++;
					tempUsername= 'googleUser'+id;
				}



			});
		}
		
		username=tempUsername;
		
		

	});
	
	return username;

}

	window.setGoogleSignUp();


