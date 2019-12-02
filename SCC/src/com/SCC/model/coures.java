package com.SCC.model;

public class coures {
	private int id;
	private String name;
	private String category;
	private String capacity;
	private int teacher_id;
	private String description;

	public coures() {
		super();
	}

	public coures(int id, String name, String category, String capacity, int teacher_id) {
		this.id = id;
		this.name = name;
		this.category = category;
		this.capacity = capacity;
		this.teacher_id = teacher_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCapacity() {
		return capacity;
	}

	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}

	public int getTeacher_id() {
		return teacher_id;
	}

	public void setTeacher_id(int teacher_id) {
		this.teacher_id = teacher_id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
