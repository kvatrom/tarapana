package com.endava.tarapana.core.model;

import org.springframework.data.annotation.Id;

public class Test {

	@Id
	private String id;

	private String name;

	private String age;

	public Test() {
	}

	public Test(String name, String age) {
		this.name = name;
		this.age = age;
	}

	@Override
	public String toString() {
		return String.format("Test[id=%s, name='%s', age='%s']", id, name, age);
	}

	public String getId() {
		// TODO Auto-generated method stub
		return id;
	}

}
