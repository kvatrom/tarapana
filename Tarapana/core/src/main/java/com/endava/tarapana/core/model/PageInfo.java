package com.endava.tarapana.core.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class PageInfo {
	@Id
	private String id;

	private String userId;

	private String name;

	private int numberOfLikes;

	// derived field
	private Set<UserInfo> activeLikes = new HashSet<>();

	private int numberOfMaleActiveLikes;

	private int numberOfFemaleActiveLikes;

	private int numberOfNoGenderActiveLikes;

	private int activeMembersUnder20;

	private int activeMembersBetween20and30;

	private int activeMembersBetween30and40;

	private int activeMembersBetween40and50;

	private int activeMembersOver50;

	private int activeMembersNoAge;

	private ArrayList<PostInfo> feed;

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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Set<UserInfo> getActiveLikes() {
		return activeLikes;
	}

	public void setActiveLikes(Set<UserInfo> activeLikes) {
		this.activeLikes = activeLikes;
	}

	public int getNumberOfMaleActiveLikes() {
		return numberOfMaleActiveLikes;
	}

	public void setNumberOfMaleActiveLikes(int numberOfMaleActiveLikes) {
		this.numberOfMaleActiveLikes = numberOfMaleActiveLikes;
	}

	public int getNumberOfFemaleActiveLikes() {
		return numberOfFemaleActiveLikes;
	}

	public void setNumberOfFemaleActiveLikes(int numberOfFemaleActiveLikes) {
		this.numberOfFemaleActiveLikes = numberOfFemaleActiveLikes;
	}

	public int getNumberOfNoGenderActiveLikes() {
		return numberOfNoGenderActiveLikes;
	}

	public void setNumberOfNoGenderActiveLikes(int numberOfNoGenderActiveLikes) {
		this.numberOfNoGenderActiveLikes = numberOfNoGenderActiveLikes;
	}

	public int getActiveMembersUnder20() {
		return activeMembersUnder20;
	}

	public void setActiveMembersUnder20(int activeMembersUnder20) {
		this.activeMembersUnder20 = activeMembersUnder20;
	}

	public int getActiveMembersBetween20and30() {
		return activeMembersBetween20and30;
	}

	public void setActiveMembersBetween20and30(int activeMembersBetween20and30) {
		this.activeMembersBetween20and30 = activeMembersBetween20and30;
	}

	public int getActiveMembersBetween30and40() {
		return activeMembersBetween30and40;
	}

	public void setActiveMembersBetween30and40(int activeMembersBetween30and40) {
		this.activeMembersBetween30and40 = activeMembersBetween30and40;
	}

	public int getActiveMembersBetween40and50() {
		return activeMembersBetween40and50;
	}

	public void setActiveMembersBetween40and50(int activeMembersBetween40and50) {
		this.activeMembersBetween40and50 = activeMembersBetween40and50;
	}

	public int getActiveMembersOver50() {
		return activeMembersOver50;
	}

	public void setActiveMembersOver50(int activeMembersOver60) {
		this.activeMembersOver50 = activeMembersOver60;
	}

	public int getActiveMembersNoAge() {
		return activeMembersNoAge;
	}

	public void setActiveMembersNoAge(int activeMembersNoAge) {
		this.activeMembersNoAge = activeMembersNoAge;
	}
}
