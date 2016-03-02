package com.endava.tarapana.core.model;

import java.util.ArrayList;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class PageInfo {
	@Id
	private String id;

	private String name;

	private int numberOfLikes;

	private ArrayList<PostInfo> feed;

	private ArrayList<UserInfo> likes;

	// no setter for id
	public String getId() {
		return id;
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

	public ArrayList<UserInfo> getLikes() {
		return likes;
	}

	public void setLikes(ArrayList<UserInfo> likes) {
		this.likes = likes;
	}
}
