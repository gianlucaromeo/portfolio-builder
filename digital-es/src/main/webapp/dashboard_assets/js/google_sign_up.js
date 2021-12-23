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
			
			/*
			var userIdToken=googleUser.getAuthResponse().id_token;
			var userId=googleUser.getBasicProfile().getId();
			
			fetch("https://people.googleapis.com/v1/people/"+userId+"?alt=json&personFields=names&key=AIzaSyCUCx5WXDu9ulW3i6tcmosJM7UsNlNddZo&access_token="+userIdToken)
			  .then(response => console.log(response) )
			  .then(data => console.log(data));
			*/	
			
			

        }, function(error) {
          alert(JSON.stringify(error, undefined, 2));
        });

			
         
  }



	window.setGoogleSignUp();


