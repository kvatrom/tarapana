package com.endava.tarapana.core.model;

import java.util.ArrayList;
import java.util.Date;

public class PostInfo {

	private String id;

	private String content;

	private Date createdTime;

	private int numberOfLikes;

	private ArrayList<UserInfo> likes;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public ArrayList<UserInfo> getLikes() {
		return likes;
	}

	public void setLikes(ArrayList<UserInfo> likes) {
		setNumberOfLikes(likes.size());
		this.likes = likes;
	}

	public int getNumberOfLikes() {
		return numberOfLikes;
	}

	public void setNumberOfLikes(int numberOfLikes) {
		this.numberOfLikes = numberOfLikes;
	}
}
