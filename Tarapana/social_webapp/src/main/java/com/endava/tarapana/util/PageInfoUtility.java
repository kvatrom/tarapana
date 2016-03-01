package com.endava.tarapana.util;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.endava.tarapana.model.PageInfo;

public class PageInfoUtility {

	@Value("${rest.api.page}")
	private String uri;

	@Autowired
	private RestTemplate restTemplate;

	public void storePageInfo(ArrayList<PageInfo> pages) {
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		restTemplate.getMessageConverters().add(new StringHttpMessageConverter());

		for (PageInfo pageInfo : pages) {
			PageInfo result = restTemplate.postForObject(uri, pageInfo, PageInfo.class);
		}

	}
}
