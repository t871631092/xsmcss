package com.SCC.model;

public class coures {
	private int id;
	private String name;
	private String category;
	private int period;
	private int capacity;
	private String teacher_id;
	private String description;

	public coures() {
		super();
	}

	public coures(int id, String name, String category, int capacity, String teacher_id) {
		this.id = id;
		this.name = name;
		this.category = category;
		this.capacity = capacity;
		this.teacher_id = teacher_id;
	}

	public int getPeriod() {
		return period;
	}

	public void setPeriod(int period) {
		this.period = period;
	}

	public void setCategory(String category) {
		this.category = category;
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


	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public String getTeacher_id() {
		return teacher_id;
	}

	public void setTeacher_id(String teacher_id) {
		this.teacher_id = teacher_id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	public boolean unVali() {
		if (this.name.isEmpty()||this.teacher_id.isEmpty()||this.capacity==0) {
			return true;
		}else {
			return false;
		}
	}
}
