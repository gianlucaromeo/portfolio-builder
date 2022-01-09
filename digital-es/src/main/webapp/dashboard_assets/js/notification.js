/**
 * 
 */

function getNotifications() {

	$.ajax({

		url: "/get_notifications",
		contentType: "application/json",
		type: "post",
		dataType: "json",

	}).done(function(data) {
		
		notifications = data;
		console.log(notifications);
		if (notifications.length === 0) {
			console.log("no data");
		} else {
			$("#notificationsNumberSpan").text(notifications.length);
			notifications.forEach((notification) => {
				addNotification(notification);
			});

		}
	});

}


function addNotification(notification){
	$("#alertDiv").append(`<a class="dropdown-item d-flex align-items-center" href="#" id="notificationId${notification.id}">
											<div class="me-3">
												<div class="bg-warning icon-circle">
													<i class="fas fa-exclamation-triangle text-white"></i>
												</div>
											</div>
											<div>
												<p> Your ${notification.type} "${notification.contentTitle}" has been removed.
													</p>
												<span class="small text-gray-500">Reason: ${notification.reason}</span>
												<button type="button" class="btn btn-primary btn-sm float-end"
													id="seenId${notification.id}">OK</button>
											</div>
											
											
										</a>`);
										
	
		$("#seenId"+notification.id).click(function(event) {
			event.preventDefault();
			if(notification.type==="project"){
				deleteProject(notification);
			}else if(notification.type==="post"){
				deletePost(notification.id);
			}
			
			
			
		});
}

function deletePost(notification){
	$.ajax({

			url: "/delete_post",
			contentType: "application/json",
			data: JSON.stringify(notification.contentId),
			type: "post",
			dataType: "json",

		}).done(function() {
			$("#notificationId" + notification.id).remove();
		});
}

function deleteProject(notification){
	$.ajax({
			url: "/delete_project",
			contentType: "application/json",
			data: JSON.stringify(notification.contentId),
			type: "post",
			dataType: "json",
		}).done(function() {
			$("#notificationId" + notification.id).remove();
		});
}
setTimeout(() => {
	getNotifications();
}, 300)


