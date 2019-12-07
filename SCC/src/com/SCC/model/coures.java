package com.SCC.model;

import xsm520.interceptor.isloginInterceptor;

public class coures {
	private int coures_id;
	private String name;
	private String category;
	private int period;
	private int capacity;
	private String teacher_id;
	private String teacher_name;
	private String description;
	private int credit;
	private int score;
	private String score_teacher;
	private int status;
	
	public boolean unVali() {
		if (this.name.isEmpty()||this.teacher_id.isEmpty()||this.capacity==0) {
			return true;
		}else {
			return false;
		}
	}

	public int getCoures_id() {
		return coures_id;
	}

	public void setCoures_id(int coures_id) {
		this.coures_id = coures_id;
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

	public int getPeriod() {
		return period;
	}

	public void setPeriod(int period) {
		this.period = period;
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

	public String getTeacher_name() {
		return teacher_name;
	}

	public void setTeacher_name(String teacher_name) {
		this.teacher_name = teacher_name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getScore_teacher() {
		return score_teacher;
	}

	public void setScore_teacher(String score_teacher) {
		this.score_teacher = score_teacher;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	
}
