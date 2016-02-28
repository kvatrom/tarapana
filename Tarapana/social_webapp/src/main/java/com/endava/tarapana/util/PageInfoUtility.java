package com.endava.tarapana.util;

import java.util.ArrayList;

import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.endava.tarapana.model.PageInfo;

public class PageInfoUtility {

	public static void storePageInfo(ArrayList<PageInfo> pages) {
		final String uri = "http://localhost:9000/analyze/service/pageInfo";

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		restTemplate.getMessageConverters().add(new StringHttpMessageConverter());

		for (PageInfo pageInfo : pages) {
			PageInfo result = restTemplate.postForObject(uri, pageInfo, PageInfo.class);
		}

	}
}
