/**
 * 
 */

//login con google
	
	var googleUser = {};
  	function setGoogleSignUp() {
		console.log("start");
	    gapi.load('auth2', function(){
	      // Retrieve the singleton for the GoogleAuth library and set up the client.
	      auth2 = gapi.auth2.init({
	        client_id: '666216352369-98lhc8kqedb2mf28ssdknqfqmc4958fv.apps.googleusercontent.com',
	        cookiepolicy: 'single_host_origin',
	        // Request scopes in addition to 'profile' and 'email'
	        //scope: 'additional_scope'
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
			
			
         

        }, function(error) {
          alert(JSON.stringify(error, undefined, 2));
        });
  }


	window.setGoogleSignUp();


