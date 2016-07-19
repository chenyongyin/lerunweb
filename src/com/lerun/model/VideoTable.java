package com.lerun.model;

/**
 *@Author: wschenyongyin
 *@Date: 2016-7-15
 *@explain:
 *@TestState:
 */
public class VideoTable {

	int video_id;
	String video_image;
	String video_url;
	String video_title;
	String update_type;
	String update_values;
	

	public String getUpdate_type() {
		return update_type;
	}

	public void setUpdate_type(String updateType) {
		update_type = updateType;
	}

	public String getUpdate_values() {
		return update_values;
	}

	public void setUpdate_values(String updateValues) {
		update_values = updateValues;
	}

	public VideoTable() {
	}

	public int getVideo_id() {
		return video_id;
	}

	public void setVideo_id(int videoId) {
		video_id = videoId;
	}

	public String getVideo_image() {
		return video_image;
	}

	public void setVideo_image(String videoImage) {
		video_image = videoImage;
	}

	public String getVideo_url() {
		return video_url;
	}

	public void setVideo_url(String videoUrl) {
		video_url = videoUrl;
	}

	public String getVideo_title() {
		return video_title;
	}

	public void setVideo_title(String videoTitle) {
		video_title = videoTitle;
	}

}
