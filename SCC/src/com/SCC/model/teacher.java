package com.SCC.model;

import org.apache.struts2.json.annotations.JSON;

public class teacher {
	private String id;
	private String password;
	private String name;
	private String grade;
	private String category;
	private String department;
	private String description;

	public teacher() {
		super();
	}

	public teacher(String id, String password) {
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

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
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
