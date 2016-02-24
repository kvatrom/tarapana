package com.endava.tarapana.rest.api;

import java.util.List;

public class Data {
	private String key;

	private List<LabelValue> values;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public List<LabelValue> getValues() {
		return values;
	}

	public void setValues(List<LabelValue> values) {
		this.values = values;
	}

}
