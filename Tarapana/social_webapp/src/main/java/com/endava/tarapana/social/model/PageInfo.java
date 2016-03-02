package com.endava.tarapana.social.model;

import java.util.ArrayList;

public class PageInfo {
	private String id;

	private String userId;

	private String name;

	private int numberOfLikes;

	private ArrayList<PostInfo> feed;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumberOfLikes() {
		return numberOfLikes;
	}

	public void setNumberOfLikes(int numberOfLikes) {
		this.numberOfLikes = numberOfLikes;
	}

	public ArrayList<PostInfo> getFeed() {
		return feed;
	}

	public void setFeed(ArrayList<PostInfo> feed) {
		this.feed = feed;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
