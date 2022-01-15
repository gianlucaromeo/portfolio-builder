/**
 * 
 */

var notificationsNumber = 0;

function getNotifications() {

	$.ajax({

		url: "/get_notifications",
		contentType: "application/json",
		type: "post",
		dataType: "json",

	}).done(function(data) {

		notifications = data;
		console.log(notifications);
		if (notifications.length <= 0 ) {
			addNoNotification();
		} else {
			
			notificationsNumber = notifications.length;
			$("#notificationsNumberSpan").text(notificationsNumber);
			notifications.forEach((notification) => {
				addNotification(notification);
			});

		}
	});

}


function addNotification(notification) {
	$("#alertDiv").append(`<a class="dropdown-item d-flex align-items-center" id="notificationId${notification.id}">
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


	$("#seenId" + notification.id).click(function(event) {
		event.preventDefault();
		if (notification.type === "project") {
			deleteProject(notification);
		} else if (notification.type === "post") {
			deletePost(notification);
		}



	});
}

function addNoNotification() {
	$("#alertDiv").append(`
							<div class="text-center" style="margin-top:20px">
								<p>There are no notifications :)</p>
			
							</div>`);
}

function deletePost(notification) {
	$.ajax({

		url: "/delete_post",
		contentType: "application/json",
		data: JSON.stringify(notification.contentId),
		type: "post",
		dataType: "json",

	}).done(function() {
		refactNotifications(notification);

	});
}

function deleteProject(notification) {
	$.ajax({
		url: "/delete_project_notification",
		contentType: "application/json",
		data: JSON.stringify(notification.contentId),
		type: "post",
		dataType: "json",
	}).done(function() {
		refactNotifications(notification);
	});
}
function refactNotifications(notification) {
	console.log(notification.id);
	$("#notificationId" + notification.id).remove();
	notificationsNumber--;

	let displayedNotificationsNumber = (notificationsNumber === 0) ? "" : notificationsNumber;
	$("#notificationsNumberSpan").text(displayedNotificationsNumber);

}

setTimeout(() => {
	getNotifications();
}, 1000)


