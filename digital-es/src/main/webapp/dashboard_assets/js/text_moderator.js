$("#testBtn").on("click", function(e) {

	e.preventDefault();

	text = $("#username").val();

	const settings = {
		"async": true,
		"crossDomain": true,
		"url": "https://linguist.p.rapidapi.com/v1/remove-bad-words",
		"method": "POST",
		"headers": {
			"content-type": "application/x-www-form-urlencoded",
			"x-rapidapi-host": "linguist.p.rapidapi.com",
			"x-rapidapi-key": "f87a10d962mshbc92490a1f3d8b0p1fd5b5jsnf6d0dbd9d567"
		},
		"data": {
			"text": text
		}
	};

	$.ajax(settings).done(function(response) {
		console.log(response.results.string);
	});

});