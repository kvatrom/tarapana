package com.endava.tarapana.social.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.endava.tarapana.social.model.PageInfo;

@Service
public class PageInfoRestService {

	@Autowired
	private RestTemplate restTemplate;

	@Value("${rest.api.page}")
	private String uri;

	public void storePageInfo(ArrayList<PageInfo> pages) {

		for (PageInfo pageInfo : pages) {
			restTemplate.postForObject(uri, pageInfo, PageInfo.class);
		}

	}
}
