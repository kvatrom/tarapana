package com.endava.tarapana.rest.api;

import java.util.List;

import com.endava.tarapana.core.model.TrackingEntity;

public class Data {
	private String key;

	private List<TrackingEntity> values;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public List<TrackingEntity> getValues() {
		return values;
	}

	public void setValues(List<TrackingEntity> values) {
		this.values = values;
	}

}
