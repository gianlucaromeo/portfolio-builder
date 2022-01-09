/**
 * 
 */

notify = `<a class="dropdown-item d-flex align-items-center" href="#">
											<div class="me-3">
												<div class="bg-warning icon-circle">
													<i class="fas fa-exclamation-triangle text-white"></i>
												</div>
											</div>
											<div>
												<span class="small text-gray-500">December 2, 2019</span>
												<p>Spending Alert: We've noticed unusually high spending
													for your account.</p>
											</div>
										</a>`;

function getNotifications() {

	$.ajax({

		url: "/get_notifications",
		contentType: "application/json",
		type: "post",

	}).done(function(data) {
		console.log(data);
		notifications = data;
		
		console.log(data);
		if (notifications.length === 0) {
			console.log("no data");
		} else {
			notifications.forEach(notification => {
				addNotification(notification);
			});
			
		}
	});

}