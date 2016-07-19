package com.lerun.model;
/**
 * 
 * @author wschenyongyin
 *
 */
public class ShowTable {
	int show_id;
	String user_id;
	String show_content;
	String show_image;
	String show_time;
	
	int like_state;

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
