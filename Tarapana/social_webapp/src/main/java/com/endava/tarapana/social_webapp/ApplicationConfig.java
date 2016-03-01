package com.endava.tarapana.social_webapp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.endava.tarapana.util.PageInfoUtility;

@Configuration
public class ApplicationConfig {

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	public PageInfoUtility pageInfoUtility() {
		return new PageInfoUtility();
	}

}
