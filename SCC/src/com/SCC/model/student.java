package com.SCC.model;

import org.apache.struts2.json.annotations.JSON;

public class student {
	private String id;
	private String password;
	private String name;
	private int credits;
	private String major;
	private String description;

	public student() {
		super();
	}

	public student(String id, String password) {
		this.id = id;
		this.password = password;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	@JSON(serialize=false)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCredits() {
		return credits;
	}

	public void setCredits(int credits) {
		this.credits = credits;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	public boolean unVali() {
		if (this.id.isEmpty()||this.password.isEmpty()||this.name.isEmpty()) {
			return true;
		}else {
			return false;
		}
	}
}
