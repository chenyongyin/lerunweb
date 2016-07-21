package com.lerun.bean;

public class ResponseObject {

	int state;// 状态码
	Object datas;// 数据

	public ResponseObject(int state, Object datas) {
		super();
		this.state = state;
		this.datas = datas;
	}

	public ResponseObject() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getStage() {
		return state;
	}

	public void setStage(int stage) {
		this.state = stage;
		
	}

	public Object getDatas() {
		return datas;
	}

	public void setDatas(Object datas) {
		this.datas = datas;
	}
}
