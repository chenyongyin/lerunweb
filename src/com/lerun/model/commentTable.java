/**
*@Project: LeRun
 */
package com.lerun.model;
/**
 *@Author: wschenyongyin
 *@Date: 2016-7-15
 *@TestState:
 */
public class commentTable {
	int comment_id;
	int show_id;
	private String user_id;//评论者的id
	private String comment_content;
	private String comment_time;
	private String comment_userid;//被评论者的id
	private String user_header;
	private String user_name;
	public String getComment_userid() {
		return comment_userid;
	}
	public void setComment_userid(String comment_userid) {
		this.comment_userid = comment_userid;
	}
	public String getUser_header() {
		return user_header;
	}
	public void setUser_header(String user_header) {
		this.user_header = user_header;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public int getComment_id() {
		return comment_id;
	}
	public void setComment_id(int commentId) {
		comment_id = commentId;
	}
	public int getShow_id() {
		return show_id;
	}
	public void setShow_id(int showId) {
		show_id = showId;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String userId) {
		user_id = userId;
	}
	public String getComment_content() {
		return comment_content;
	}
	public void setComment_content(String commentContent) {
		comment_content = commentContent;
	}
	public String getComment_time() {
		return comment_time;
	}
	public void setComment_time(String commentTime) {
		comment_time = commentTime;
	}
	public String getComment_argued() {
		return comment_userid;
	}
	public void setComment_argued(String commentArgued) {
		comment_userid = commentArgued;
	}

}
