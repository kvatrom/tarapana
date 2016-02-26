package com.endava.tarapana.util;

import org.springframework.social.facebook.api.Page;
import org.springframework.web.client.RestTemplate;

import com.endava.tarapana.model.PageInfo;

public class RestClient {
	public static void postPage(PageInfo page) {
		final String uri = "";

		RestTemplate restTemplate = new RestTemplate();
		Page result = restTemplate.postForObject(uri, page, Page.class);
	}
}
