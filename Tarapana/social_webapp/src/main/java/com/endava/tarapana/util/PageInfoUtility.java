package com.endava.tarapana.util;

import java.util.ArrayList;

import org.springframework.web.client.RestTemplate;

import com.endava.tarapana.model.PageInfo;

public class PageInfoUtility {

	public static void storePageInfo(ArrayList<PageInfo> pages) {
		final String uri = "";

		RestTemplate restTemplate = new RestTemplate();

		for (PageInfo pageInfo : pages) {
			// TODO send to rest
			// PageInfo result = restTemplate.postForObject(uri, pageInfo, PageInfo.class);
		}

	}

}
