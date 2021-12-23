/**
 * 
 */


function checkLoginState() {               //viene chiamata quando ho finito con il form di login facebook
    FB.getLoginStatus(function(response) {   
      statusChangeCallback(response);
    });
  }

window.fbAsyncInit = function() { //setto l'api'
	FB.init
		({
			appId: '331331315259722',
			status: true,
			cookie: true,
			xfbml: true
		});
	
};


//appendo la funzione del login alla div che contiene il bottone
(function() { 
	var e = document.createElement('script');
	e.src = document.location.protocol + '//connect.facebook.net/en_US/all.js';
	e.async = true;
	document.getElementById('fb-root').appendChild(e);
}());


//qui controllo lo stato del login 
function statusChangeCallback(response) { 
    console.log('statusChangeCallback');
    console.log(response);                   
    if (response.status === 'connected') {   
      //console.log("connected");
    }
  }


var signUpBtn= document.getElementById('facebookSignUpBtn');

signUpBtn.addEventListener("click", function(e) {
	
	 FB.login(function(response) {

        if (response.authResponse) {
            access_token = response.authResponse.accessToken; //prendo il token dell'utente loggato'
            user_id = response.authResponse.userID; //qui ho l'id dell'udente facebook

            FB.api('/me', function(response) { //in questa response ho tutti i dati dell'utente '
                user_email = response.email;  //esempio per prendere la mail
				console.log(user_email);
             
            });

        } else {
			//qui non si è loggato l'utente'

        }
    }, {
        scope: 'public_profile,email' //quelo che voglio ricevere
    });

	
});



