package com.SCC.model;

public class elective {
	private int id;
	private int student_id;
	private int teacher_id;
	private String shoose;
	private String achievement;
	public elective() {
		super();
	}
	public elective(int id,int student_id,int teacher_id) {
		this.id = id;
		this.student_id = student_id;
		this.teacher_id = teacher_id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getStudent_id() {
		return student_id;
	}
	public void setStudent_id(int student_id) {
		this.student_id = student_id;
	}
	public int getTeacher_id() {
		return teacher_id;
	}
	public void setTeacher_id(int teacher_id) {
		this.teacher_id = teacher_id;
	}
	public String getShoose() {
		return shoose;
	}
	public void setShoose(String shoose) {
		this.shoose = shoose;
	}
	public String getAchievement() {
		return achievement;
	}
	public void setAchievement(String achievement) {
		this.achievement = achievement;
	}
	
}
