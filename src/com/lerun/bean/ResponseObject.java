package com.lerun.bean;

public class ResponseObject {

	int stage;// 状态码
	Object datas;// 数据

	public ResponseObject(int stage, Object datas) {
		super();
		this.stage = stage;
		this.datas = datas;
	}

	public ResponseObject() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getStage() {
		return stage;
	}

	public void setStage(int stage) {
		this.stage = stage;
	}

	public Object getDatas() {
		return datas;
	}

	public void setDatas(Object datas) {
		this.datas = datas;
	}

}
