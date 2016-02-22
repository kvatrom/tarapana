package com.endava.tarapana.rest.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/test")
public class Main {

	public static void main(String[] args) {
		System.out.println("hello world");
	}
}
