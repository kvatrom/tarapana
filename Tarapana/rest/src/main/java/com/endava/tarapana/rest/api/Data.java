package com.endava.tarapana.rest.api;

import java.util.List;

import com.endava.tarapana.core.model.PageInfo;

public class Data {
	private String key;

	private List<PageInfo> values;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public List<PageInfo> getValues() {
		return values;
	}

	public void setValues(List<PageInfo> values) {
		this.values = values;
	}

}
