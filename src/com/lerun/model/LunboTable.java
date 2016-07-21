package com.lerun.model;

/**
 *@Author: wschenyongyin
 *@Date: 2016-7-15
 *@explain:
 *@TestState:
 */
public class LunboTable {

	private int lunbo_id;
	private String lunbo_image;
	private String lunbo_url;
	private String lunbo_title;
	private String update_type;
	private String update_values;
	
	
	public LunboTable(){}

	public int getLunbo_id() {
		return lunbo_id;
	}

	public void setLunbo_id(int lunboId) {
		lunbo_id = lunboId;
	}

	public String getLunbo_image() {
		return lunbo_image;
	}

	public void setLunbo_image(String lunboImage) {
		lunbo_image = lunboImage;
	}

	public String getLunbo_url() {
		return lunbo_url;
	}

	public void setLunbo_url(String lunboUrl) {
		lunbo_url = lunboUrl;
	}

	public String getLunbo_title() {
		return lunbo_title;
	}

	public void setLunbo_title(String lunboTitle) {
		lunbo_title = lunboTitle;
	}

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

}
