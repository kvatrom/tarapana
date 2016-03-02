package com.endava.tarapana.social.controller;

import java.util.ArrayList;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.UncategorizedApiException;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Account;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.Page;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.Post;
import org.springframework.social.facebook.api.Reference;
import org.springframework.social.facebook.api.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.endava.tarapana.social.model.LikesResponse;
import com.endava.tarapana.social.model.NumberOfLikesResponse;
import com.endava.tarapana.social.model.PageInfo;
import com.endava.tarapana.social.model.PostInfo;
import com.endava.tarapana.social.model.UserInfo;
import com.endava.tarapana.social.model.UserResponse;
import com.endava.tarapana.social.service.PageInfoRestService;

@Controller
@RequestMapping("/")
public class FetchDataController {

	private static final Logger logger = LoggerFactory.getLogger(FetchDataController.class);

	private Facebook facebook;

	private ConnectionRepository connectionRepository;

	@Autowired
	private PageInfoRestService pageInfoRestService;

	@Inject
	public FetchDataController(Facebook facebook, ConnectionRepository connectionRepository) {
		this.facebook = facebook;
		this.connectionRepository = connectionRepository;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String helloFacebook(Model model) {
		if (connectionRepository.findPrimaryConnection(Facebook.class) == null) {
			return "redirect:/connect/facebook";
		}

		logger.info("data fetching started.");
		ArrayList<PageInfo> pages = getPages(facebook.pageOperations().getAccounts());
		logger.info("data fetching done.");

		pageInfoRestService.storePageInfo(pages);

		return "/connect/facebookConnected";
	}

	private ArrayList<PageInfo> getPages(PagedList<Account> pages) {
		if (pages == null) {
			throw new IllegalArgumentException();
		}
		if (pages.size() == 0) {
			logger.info("User has no pages with admin privileges");
		}

		ArrayList<PageInfo> result = new ArrayList<PageInfo>();

		for (Account pageAccount : pages) {
			Page page = facebook.pageOperations().getPage(pageAccount.getId());
			Facebook facebookPage = facebook.pageOperations().facebookOperations(pageAccount.getId());

			PageInfo pageInfo = getPageInfo(page);
			pageInfo.setFeed(getFeed(facebookPage));

			result.add(pageInfo);
		}
		return result;
	}

	private ArrayList<PostInfo> getFeed(Facebook facebookPage) {
		if (facebookPage == null) {
			logger.warn("facebook page variable is null");
			throw new IllegalArgumentException();
		}

		ArrayList<PostInfo> result = new ArrayList<PostInfo>();
		PagedList<Post> pagePosts = facebookPage.feedOperations().getFeed();

		if (pagePosts.size() == 0) {
			logger.info("Page has no posts yet.");
		}

		for (Post post : pagePosts) {
			PostInfo postInfo = new PostInfo();

			System.out.println("Post id: " + post.getId());
			System.out.println("Post created time: " + post.getCreatedTime());
			System.out.println("Post content: " + post.getMessage());

			postInfo.setContent(post.getMessage());
			postInfo.setCreatedTime(post.getCreatedTime());
			postInfo.setId(post.getId());
			postInfo.setLikes(getObjectiLikes(postInfo.getId()));

			result.add(postInfo);
		}
		return result;
	}

	private ArrayList<UserInfo> getObjectiLikes(String objectId) {
		if (objectId == null || objectId == "") {
			logger.warn("objectId is null or empty string.");
			throw new IllegalArgumentException();
		}

		PagedList<Reference> objectLikes = facebook.likeOperations().getLikes(objectId);
		ArrayList<UserInfo> result = new ArrayList<UserInfo>();

		for (Reference reference : objectLikes) {
			User user;
			try {
				user = facebook.userOperations().getUserProfile(reference.getId());
			}
			catch (UncategorizedApiException e) {
				// like is not from user, its probably from some page
				break;
			}

			UserInfo userinfo = new UserInfo();

			System.out.println("User id: " + user.getId());
			System.out.println("User name: " + user.getName());
			System.out.println("User lastName: " + user.getLastName());
			System.out.println("User gender: " + user.getGender());
			System.out.println("User birthday: " + user.getBirthday());
			System.out.println("User relationship: " + user.getRelationshipStatus());
			System.out.println("User religion: " + user.getReligion());

			userinfo.setId(user.getId());
			userinfo.setFirstName(user.getFirstName());
			userinfo.setLastName(user.getLastName());
			userinfo.setGender(user.getGender());
			userinfo.setBirthday(user.getBirthday());
			userinfo.setRelationshipStatus(user.getRelationshipStatus());
			userinfo.setReligion(user.getReligion());

			result.add(userinfo);
		}

		return result;
	}

	private PageInfo getPageInfo(Page page) {
		if (page == null) {
			logger.warn("page variable is null.");
			throw new IllegalArgumentException();
		}

		PageInfo pageInfo = new PageInfo();

		System.out.println("Page name: " + page.getName());
		System.out.println("Page id: " + page.getId());

		String[] options = { "likes" };
		String url = createUrl(page.getId(), options);
		NumberOfLikesResponse nol = facebook.restOperations().getForObject(url, NumberOfLikesResponse.class);

		LikesResponse response = facebook.restOperations()
				.getForObject(facebook.getBaseGraphApiUrl() + "/" + page.getId() + "/likes", LikesResponse.class);

		System.out.println("PAGE LIKES");
		System.out.println("Number of likes:" + response.getData().length);
		for (UserResponse user : response.getData()) {
			System.out.println(user.getId());
		}
		System.out.println("PAGE LIKES END");

		pageInfo.setId(page.getId());
		pageInfo.setUserId(facebook.userOperations().getUserProfile().getId());
		pageInfo.setName(page.getName());
		pageInfo.setNumberOfLikes(nol.getLikes());

		return pageInfo;
	}

	private String createUrl(String id, String[] options) {
		if (id == null || id == "" || options.length < 1) {
			throw new IllegalArgumentException();
		}

		StringBuilder sb = new StringBuilder();
		sb.append(facebook.getBaseGraphApiUrl());
		sb.append("/" + id + "?fields=");
		for (String option : options) {
			sb.append(option + ",");
		}

		sb.deleteCharAt(sb.length() - 1);

		return sb.toString();
	}
}