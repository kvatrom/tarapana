package com.endava.tarapana.social_webapp;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.endava.tarapana.model.UserInfo;
import com.endava.tarapana.util.TrackingEntityUtility;

@Controller
@RequestMapping("/")
public class FetchDataController {

	private static final Logger logger = LoggerFactory.getLogger(FetchDataController.class);

	private Facebook facebook;

	private ConnectionRepository connectionRepository;

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

		User user = facebook.userOperations().getUserProfile();
		model.addAttribute("facebookProfile", user);
		PagedList<Account> pageList = facebook.pageOperations().getAccounts();

		if (pageList != null && !pageList.isEmpty()) {
			PagedList<Account> pagesToTrack = pageList;
			logger.debug("Ima strana");
			System.out.println("########################PAGES#####################");
			for (Account pageAccount : pagesToTrack) {
				if (!facebook.pageOperations().isPageAdmin(pageAccount.getId())) {
					pagesToTrack.remove(pageAccount);
				}
				else {
					Page page = facebook.pageOperations().getPage(pageAccount.getId());
					String pageName = page.getName();
					System.out.println("PageName: " + pageName);
					Integer pageLikeNumber = page.getLikes();
					System.out.println("PageLikeNumber: " + pageLikeNumber);
					String pageMembers = page.getMembers();
					System.out.println("PageMembers: " + pageMembers);
					Integer pageNewLikesNumber = page.getNewLikeCount();
					System.out.println("pageNewLikesNumber: " + pageNewLikesNumber);
					Integer pageTalkingAboutNumber = page.getTalkingAboutCount();
					System.out.println("PageTalkingAboutNumber: " + pageTalkingAboutNumber);

					Facebook facebookPage = facebook.pageOperations().facebookOperations(pageAccount.getId());
					PagedList<Post> pagePosts = facebookPage.feedOperations().getFeed();
					System.out.println("#############POSTS################");
					for (Post pagePost : pagePosts) {
						String pagePostName = pagePost.getName();
						System.out.println("PostName: " + pagePostName);
						String pagePostMessage = pagePost.getMessage();
						System.out.println("PagePostMessage: " + pagePostMessage);
						PagedList<Reference> pagePostLikes = facebookPage.likeOperations().getLikes(pagePost.getId());
						System.out.println("##############LIKES###################");
						Integer postLikesCount = 0;
						for (Reference reference : pagePostLikes) {
							postLikesCount++;
							String postLikeId = reference.getId();
							System.out.println("PostLikeId: " + postLikeId);
							try {
								User userWhoLikedPost = facebook.userOperations().getUserProfile(postLikeId);
								UserInfo userInfo = new UserInfo();
								userInfo.setFirstName(userWhoLikedPost.getFirstName());
								System.out.println(userInfo.getFirstName());
								userInfo.setBirthday(userWhoLikedPost.getBirthday());
								System.out.println(userInfo.getBirthday());
								userInfo.setGender(userWhoLikedPost.getGender());
								System.out.println(userInfo.getGender());
								// String userWhoLikedPostEducation = userWhoLikedPost.getEducation();
								// String userWhoLikedPostHomeTown = userWhoLikedPost.getHometown().getName();
								// System.out.println(userWhoLikedPostHomeTown);
								userInfo.setLastName(userWhoLikedPost.getLastName());
								System.out.println(userInfo.getLastName());
								userInfo.setPoliticalAffiliation(userWhoLikedPost.getPolitical());
								System.out.println(userInfo.getPoliticalAffiliation());
								userInfo.setRelationshipStatus(userWhoLikedPost.getRelationshipStatus());
								System.out.println(userInfo.getRelationshipStatus());
								userInfo.setReligion(userWhoLikedPost.getReligion());
								System.out.println(userInfo.getReligion());

							}
							catch (Exception e) {
								// post is not liked by user
							}

						}
						System.out.println("postLikesTotalCount: " + postLikesCount);
					}
					System.out.println(pageAccount.getName());
					System.out.println(pageAccount.getAccessToken());
					System.out.println("###################END PAGE#####################");
				}
			}
			if (pagesToTrack != null && !pagesToTrack.isEmpty()) {
				TrackingEntityUtility.storeTrackingEntities(pagesToTrack);
			}
			else {
				logger.warn("User " + user.getFirstName() + " " + user.getLastName()
						+ " doesn't have any administrated pages.");
			}
		}
		else {
			logger.warn("User " + user.getFirstName() + " " + user.getLastName() + " doesn't have any related pages.");
		}

		return "/connect/hello";
	}

}