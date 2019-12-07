package com.SCC.model;

import org.apache.struts2.json.annotations.JSON;

import xsm520.interceptor.isloginInterceptor;

public class student {
	private String student_id;
	private String student_password;
	private String student_name;
	private int electives_credits;
	private String student_major;
	private String student_description;
	private int score;
	private Long avgScore;

	public boolean unVali() {
		if (this.student_id.isEmpty()||this.student_password.isEmpty()||this.student_name.isEmpty()) {
			return true;
		}else {
			return false;
		}
	}

	public String getStudent_id() {
		return student_id;
	}

	public void setStudent_id(String student_id) {
		this.student_id = student_id;
	}

	public String getStudent_password() {
		return student_password;
	}

	public void setStudent_password(String student_password) {
		this.student_password = student_password;
	}

	public String getStudent_name() {
		return student_name;
	}

	public void setStudent_name(String student_name) {
		this.student_name = student_name;
	}

	public int getElectives_credits() {
		return electives_credits;
	}

	public void setElectives_credits(int electives_credits) {
		this.electives_credits = electives_credits;
	}

	public String getStudent_major() {
		return student_major;
	}

	public void setStudent_major(String student_major) {
		this.student_major = student_major;
	}

	public String getStudent_description() {
		return student_description;
	}

	public void setStudent_description(String student_description) {
		this.student_description = student_description;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public Long getAvgScore() {
		return avgScore;
	}

	public void setAvgScore(Long avgScore) {
		this.avgScore = avgScore;
	}
}
