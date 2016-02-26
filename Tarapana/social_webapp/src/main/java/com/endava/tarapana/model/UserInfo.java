package com.endava.tarapana.model;

public class UserInfo {
	private String id;

	private String firstName;

	private String lastName;

	private String birthday;

	private String gender;

	private String politicalAffiliation;

	private String relationshipStatus;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	private String religion;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPoliticalAffiliation() {
		return politicalAffiliation;
	}

	public void setPoliticalAffiliation(String politicalAffiliation) {
		this.politicalAffiliation = politicalAffiliation;
	}

	public String getRelationshipStatus() {
		return relationshipStatus;
	}

	public void setRelationshipStatus(String relationshipStatus) {
		this.relationshipStatus = relationshipStatus;
	}

	public String getReligion() {
		return religion;
	}

	public void setReligion(String religion) {
		this.religion = religion;
	}
}
