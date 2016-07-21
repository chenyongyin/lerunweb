package com.lerun.model;

public class LikeTable {
	private int like_id;
	private String show_id;
	private String user_id;//点赞用户的id
	private String like_time;
	private String like_userid;//被点用户的id
	
	
	public LikeTable(){}
	public int getLike_id() {
		return like_id;
	}
	public void setLike_id(int likeId) {
		like_id = likeId;
	}
	public String getShow_id() {
		return show_id;
	}
	public void setShow_id(String showId) {
		show_id = showId;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String userId) {
		user_id = userId;
	}
	public String getLike_time() {
		return like_time;
	}
	public void setLike_time(String likeTime) {
		like_time = likeTime;
	}
	public String getLike_userid() {
		return like_userid;
	}
	public void setLike_userid(String likeUserid) {
		like_userid = likeUserid;
	}

}
