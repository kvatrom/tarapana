package com.endava.tarapana.social.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.endava.tarapana.social.model.PageInfo;

@Service
public class PageInfoRestService {

	public void storePageInfo(ArrayList<PageInfo> pages) {
		final String uri = "http://localhost:9000/analyze/service/pageInfo";

		RestTemplate restTemplate = new RestTemplate();

		for (PageInfo pageInfo : pages) {
			restTemplate.postForObject(uri, pageInfo, PageInfo.class);
		}

	}
}
