package com.endava.tarapana.core.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.endava.tarapana.core.util.Model.EntityType;

@Document
public class TrackingEntity {

	@Id
	private String id;

	private String remoteId;

	private EntityType type;

	private String name;

	private int likes;

	public String getId() {
		return id;
	}

	public String getRemoteId() {
		return remoteId;
	}

	public void setRemoteId(String remoteId) {
		this.remoteId = remoteId;
	}

	public EntityType getType() {
		return type;
	}

	public void setType(EntityType type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}
}
