package com.lerun.model;

/**
 * 
 * @author wschenyongyin
 * 
 */
public class ShowTable {
	private int show_id;
	private String user_id;
	private String show_content;
	private String show_image;
	private String show_time;
	private int comment_num;
	private int like_num;
	private String user_header;
	private String user_name;

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

	private int like_state;

	public int getComment_num() {
		return comment_num;
	}

	public void setComment_num(int comment_num) {
		this.comment_num = comment_num;
	}

	public int getLike_num() {
		return like_num;
	}

	public void setLike_num(int like_num) {
		this.like_num = like_num;
	}

	public int getLike_state() {
		return like_state;
	}

	public void setLike_state(int likeState) {
		like_state = likeState;
	}

	public ShowTable() {
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

	public String getShow_content() {
		return show_content;
	}

	public void setShow_content(String showContent) {
		show_content = showContent;
	}

	public String getShow_image() {
		return show_image;
	}

	public void setShow_image(String showImage) {
		show_image = showImage;
	}

	public String getShow_time() {
		return show_time;
	}

	public void setShow_time(String showTime) {
		show_time = showTime;
	}

	public int getLerun_id() {
		return lerun_id;
	}

	public void setLerun_id(int lerunId) {
		lerun_id = lerunId;
	}

	int lerun_id;

}
