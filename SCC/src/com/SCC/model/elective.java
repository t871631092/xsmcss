package com.SCC.model;

public class elective {
	private String uuid;
	private String student_id;
	private String teacher_id;
	private String shoose;
	private int achievement;

	public elective() {
		super();
	}

	public elective(String uuid, String student_id, String teacher_id) {
		this.uuid = uuid;
		this.student_id = student_id;
		this.teacher_id = teacher_id;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getStudent_id() {
		return student_id;
	}

	public void setStudent_id(String student_id) {
		this.student_id = student_id;
	}

	public String getTeacher_id() {
		return teacher_id;
	}

	public void setTeacher_id(String teacher_id) {
		this.teacher_id = teacher_id;
	}

	public String getShoose() {
		return shoose;
	}

	public void setShoose(String shoose) {
		this.shoose = shoose;
	}

	public int getAchievement() {
		return achievement;
	}

	public void setAchievement(int achievement) {
		this.achievement = achievement;
	}

}
