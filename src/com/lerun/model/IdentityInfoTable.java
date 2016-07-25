package com.lerun.model;
/**
 *@Project: LeRun
 *@Author: wschenyongyin
 *@Date: 2016年7月25日
 *@explain:身份信息表
 *@TestState:
 */

public class IdentityInfoTable {

	private String user_id;
	private String identity_image;
	private String school_name;
	private String student_id;
	private String beiyong1;
	private String beiyong2;
	private String beiyong3;
	
	
	
	
	public IdentityInfoTable() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public IdentityInfoTable(String user_id, String identity_image,
			String school_name, String student_id, String beiyong1,
			String beiyong2, String beiyong3) {
		super();
		this.user_id = user_id;
		this.identity_image = identity_image;
		this.school_name = school_name;
		this.student_id = student_id;
		this.beiyong1 = beiyong1;
		this.beiyong2 = beiyong2;
		this.beiyong3 = beiyong3;
	}


	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getIdentity_image() {
		return identity_image;
	}
	public void setIdentity_image(String identity_image) {
		this.identity_image = identity_image;
	}
	public String getSchool_name() {
		return school_name;
	}
	public void setSchool_name(String school_name) {
		this.school_name = school_name;
	}
	public String getStudent_id() {
		return student_id;
	}
	public void setStudent_id(String student_id) {
		this.student_id = student_id;
	}
	public String getBeiyong1() {
		return beiyong1;
	}
	public void setBeiyong1(String beiyong1) {
		this.beiyong1 = beiyong1;
	}
	public String getBeiyong2() {
		return beiyong2;
	}
	public void setBeiyong2(String beiyong2) {
		this.beiyong2 = beiyong2;
	}
	public String getBeiyong3() {
		return beiyong3;
	}
	public void setBeiyong3(String beiyong3) {
		this.beiyong3 = beiyong3;
	}
	
	
}
