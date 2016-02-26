package com.endava.tarapana.model;

public class PageInfo {
	private String id;

	private String name;

	private int numberOfLikes;

	private int numberOfTalkingAbout;

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

	public int getNumberOfTalkingAbout() {
		return numberOfTalkingAbout;
	}

	public void setNumberOfTalkingAbout(int numberOfTalkingAbout) {
		this.numberOfTalkingAbout = numberOfTalkingAbout;
	}
}
