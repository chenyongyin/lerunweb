package com.lerun.model;
/**
 *@Project: LeRun
 *@Author: wschenyongyin
 *@Date: 2016年8月4日
 *@explain:反馈表
 *@TestState:
 */

public class FeedBackTable {
	
	
	private int feedback_id;
	private String user_id;
	private String user_telphone;
	private String feedback_content;
	private String feedback_time;
	private int process_state;
	
	
	
	
	public FeedBackTable() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public FeedBackTable(int feedback_id, String user_id, String user_telphone,
			String feedback_content, String feedback_time, int process_state) {
		super();
		this.feedback_id = feedback_id;
		this.user_id = user_id;
		this.user_telphone = user_telphone;
		this.feedback_content = feedback_content;
		this.feedback_time = feedback_time;
		this.process_state = process_state;
	}


	public int getFeedback_id() {
		return feedback_id;
	}
	public void setFeedback_id(int feedback_id) {
		this.feedback_id = feedback_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_telphone() {
		return user_telphone;
	}
	public void setUser_telphone(String user_telphone) {
		this.user_telphone = user_telphone;
	}
	public String getFeedback_content() {
		return feedback_content;
	}
	public void setFeedback_content(String feedback_content) {
		this.feedback_content = feedback_content;
	}
	public String getFeedback_time() {
		return feedback_time;
	}
	public void setFeedback_time(String feedback_time) {
		this.feedback_time = feedback_time;
	}
	public int getProcess_state() {
		return process_state;
	}
	public void setProcess_state(int process_state) {
		this.process_state = process_state;
	}
	
	

}
