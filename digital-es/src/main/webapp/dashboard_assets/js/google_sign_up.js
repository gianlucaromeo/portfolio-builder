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
			//$("#datePicker").val();
			$("#inputEmail").val(googleUser.getBasicProfile().getEmail());
			
			
			var userIdToken=googleUser.getAuthResponse().id_token;
			var userId=googleUser.getBasicProfile().getId();
			console.log(userId);
			console.log(userIdToken);
			/*
			fetch("https://people.googleapis.com/v1/people/"+userId+"?alt=json&personFields=names&key=AIzaSyCUCx5WXDu9ulW3i6tcmosJM7UsNlNddZo&access_token="+userIdToken)
			  .then(response => console.log(response.json) )
			  .then(data => console.log(data));
			*/
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


	window.setGoogleSignUp();


