package com.endava.tarapana.social_webapp;

import java.util.ArrayList;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Account;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.endava.tarapana.util.TrackingEntityUtility;

@Controller
@RequestMapping("/")
public class HelloController {

	private static final Logger logger = LoggerFactory.getLogger(HelloController.class);

	private Facebook facebook;

	private ConnectionRepository connectionRepository;

	@Inject
	public HelloController(Facebook facebook, ConnectionRepository connectionRepository) {
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
			PagedList<Account> pagesToTrack = (PagedList<Account>) new ArrayList<Account>();
			System.out.println("Ima strana");
			for (Account page : pagesToTrack) {
				if (facebook.pageOperations().isPageAdmin(page.getId())) {
					pagesToTrack.add(page);
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

		return "hello";
	}

}