package com.lerun.model;

public class OrderInfo {
	
	private int order_num;
	private int lerun_id;
	private String user_id;
	private String sign_state;
	private String charge_state;
	private String sign_barcode;
	public String getLerun_title() {
		return lerun_title;
	}
	public void setLerun_title(String lerun_title) {
		this.lerun_title = lerun_title;
	}
	public String getLerun_charge() {
		return lerun_charge;
	}
	public void setLerun_charge(String lerun_charge) {
		this.lerun_charge = lerun_charge;
	}
	private String signup_time;
	private String pay_time;
	private String sign_time;
	
	private String lerun_title;
	private String lerun_charge;
	
	public int getOrder_num() {
		return order_num;
	}
	public void setOrder_num(int orderNum) {
		order_num = orderNum;
	}
	public int getLerun_id() {
		return lerun_id;
	}
	public void setLerun_id(int lerunId) {
		lerun_id = lerunId;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String userId) {
		user_id = userId;
	}
	public String getSign_state() {
		return sign_state;
	}
	public void setSign_state(String signState) {
		sign_state = signState;
	}
	public String getCharge_state() {
		return charge_state;
	}
	public void setCharge_state(String chargeState) {
		charge_state = chargeState;
	}
	public String getSign_barcode() {
		return sign_barcode;
	}
	public void setSign_barcode(String signBarcode) {
		sign_barcode = signBarcode;
	}
	public String getSignup_time() {
		return signup_time;
	}
	public void setSignup_time(String signupTime) {
		signup_time = signupTime;
	}
	public String getPay_time() {
		return pay_time;
	}
	public void setPay_time(String payTime) {
		pay_time = payTime;
	}
	public String getSign_time() {
		return sign_time;
	}
	public void setSign_time(String signTime) {
		sign_time = signTime;
	}
	
	
	

}
