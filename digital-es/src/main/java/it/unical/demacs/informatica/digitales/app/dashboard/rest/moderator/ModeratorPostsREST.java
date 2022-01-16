package it.unical.demacs.informatica.digitales.app.dashboard.rest.moderator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import it.unical.demacs.informatica.digitales.app.beans.Moderator;
import it.unical.demacs.informatica.digitales.app.beans.Post;
import it.unical.demacs.informatica.digitales.app.beans.RemovePostRequest;
import it.unical.demacs.informatica.digitales.app.beans.RemovedPost;
import it.unical.demacs.informatica.digitales.app.beans.User;
import it.unical.demacs.informatica.digitales.app.dao.ModeratorDAOImpl;
import it.unical.demacs.informatica.digitales.app.dao.PostDAOImpl;
import it.unical.demacs.informatica.digitales.app.dao.RemovedPostDAOImpl;
import it.unical.demacs.informatica.digitales.app.dao.UserDAOImpl;
import it.unical.demacs.informatica.digitales.app.dao.UserMainInformationsDAOImpl;
import it.unical.demacs.informatica.digitales.app.dashboard.AppServletsHandler;
import it.unical.demacs.informatica.digitales.app.database.protocol.Protocol;
import it.unical.demacs.informatica.digitales.app.settings.RemovePostReasons;

@RestController
public class ModeratorPostsREST {

	@PostMapping("/get_users_data_action")
	public String getUsersDataAction(HttpServletRequest req, HttpServletResponse resp) {

		Gson gson = new Gson();

		Cookie moderatorCookie = AppServletsHandler.getCookie(req, "logged_moderator");
		if (moderatorCookie != null) {

			Set<User> users = new HashSet<User>();
			users = UserDAOImpl.getInstance().findAllNotBanned();
			String usersToJSON = gson.toJson(users);

			return usersToJSON;

		}

		return Protocol.ERROR;

	}

	@PostMapping("/get_user_profile_image")
	public String getUsersProfileImageAction(HttpServletRequest req, HttpServletResponse resp)
			throws JsonSyntaxException, JsonIOException, IOException {
		Gson gson = new Gson();
		Integer id = gson.fromJson(req.getReader(), Integer.class);

		Cookie moderatorCookie = AppServletsHandler.getCookie(req, "logged_moderator");
		if (moderatorCookie != null) {
			String profileImage64 = "";
			profileImage64 = UserMainInformationsDAOImpl.getInstance().findProfileImageById(id);
			String imageToJSON = gson.toJson(profileImage64);

			return imageToJSON;

		}

		return Protocol.ERROR;

	}


	@PostMapping("/get_users_posts_by_id_not_banned")
	public String getUserPostsAction(HttpServletRequest req, HttpServletResponse resp) throws JsonSyntaxException, JsonIOException, IOException {
		Gson gson = new Gson();
		Integer id = gson.fromJson(req.getReader(), Integer.class);

		Cookie moderatorCookie = AppServletsHandler.getCookie(req, "logged_moderator");
		if (moderatorCookie != null) {
			List<Post> posts = new ArrayList<Post>();
			posts = PostDAOImpl.getInstance().findAllByUserIdNotBanned(id);
			String postsToJSON = gson.toJson(posts);

			return postsToJSON;
		}

		return Protocol.ERROR;

	}

	@PostMapping("/get_ban_reasons")
	public String getBanReasons(HttpServletRequest req, HttpServletResponse resp)
			throws JsonSyntaxException, JsonIOException, IOException {
		Gson gson = new Gson();
		Cookie moderatorCookie = AppServletsHandler.getCookie(req, "logged_moderator");
		if (moderatorCookie != null) {
			List<String> reasons = new ArrayList<String>();
			reasons.add(RemovePostReasons.TEXT_NOT_COMPLY);
			String reasonsToJSON = gson.toJson(reasons);
			return reasonsToJSON;
		}

		return Protocol.ERROR;

	}

	@PostMapping("/remove_post_action")
	public long banPost(HttpServletRequest req, HttpServletResponse resp)
			throws JsonSyntaxException, JsonIOException, IOException {

		Gson gson = new Gson();
		RemovePostRequest banReq = gson.fromJson(req.getReader(), RemovePostRequest.class);
		Post post = new Post();

		Cookie moderatorCookie = AppServletsHandler.getCookie(req, "logged_moderator");
		if (moderatorCookie != null) {
			post = PostDAOImpl.getInstance().findById(banReq.getPostId());
			if (post != null) {
				RemovedPost rem = new RemovedPost();
				Moderator m = ModeratorDAOImpl.getInstance().findByUsername(moderatorCookie.getValue());
				rem.setModeratorId(m.getId());
				rem.setPostId(banReq.getPostId());
				rem.setReason(banReq.getReason());
				RemovedPostDAOImpl.getInstance().create(rem);
				return rem.getPostId();
			}
		}

		return -1;

	}
}
