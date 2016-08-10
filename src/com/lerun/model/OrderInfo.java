package com.lerun.model;

public class OrderInfo {
	
	private int order_num;
	private int lerun_id;
	private String user_telphone;
	private String lerun_title;
	private String user_id;
	private int signin_type ;
	private String personal_name;
	private String company_name;
	private String certificate_image;
	private String signup_time;
	private int charge_state;
	private String pay_time;
	private int payment;
	private int check_state;
	private String identity_type;
	private String identity_card;
	private int insurance_state;
	private int insurance_id;
	private String dress_size;
	private String sign_time;
	private int sign_state;
	private String sign_barcode;
	private String user_sex;
	
	//备用字段
	private String beiyong1;
	private String beiyong2;
	private String beiyong3;

	public OrderInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public OrderInfo(int order_num, int lerun_id, String user_telphone,
			String lerun_title, String user_id, int signin_type,
			String personal_name, String company_name, String certificate_image,
			String signup_time, int charge_state, String pay_time, int payment,
			int check_state, String identity_type, String identity_card,
			int insurance_state, int insurance_id, String dress_size,
			String sign_time, int sign_state, String sign_barcode) {
		super();
		this.order_num = order_num;
		this.lerun_id = lerun_id;
		this.user_telphone = user_telphone;
		this.lerun_title = lerun_title;
		this.user_id = user_id;
		this.signin_type = signin_type;
		this.personal_name = personal_name;
		this.company_name = company_name;
		this.certificate_image = certificate_image;
		this.signup_time = signup_time;
		this.charge_state = charge_state;
		this.pay_time = pay_time;
		this.payment = payment;
		this.check_state = check_state;
		this.identity_type = identity_type;
		this.identity_card = identity_card;
		this.insurance_state = insurance_state;
		this.insurance_id = insurance_id;
		this.dress_size = dress_size;
		this.sign_time = sign_time;
		this.sign_state = sign_state;
		this.sign_barcode = sign_barcode;
	}
	public int getOrder_num() {
		return order_num;
	}
	public void setOrder_num(int order_num) {
		this.order_num = order_num;
	}
	public int getLerun_id() {
		return lerun_id;
	}
	public void setLerun_id(int lerun_id) {
		this.lerun_id = lerun_id;
	}
	public String getUser_telphone() {
		return user_telphone;
	}
	public void setUser_telphone(String user_telphone) {
		this.user_telphone = user_telphone;
	}
	public String getLerun_title() {
		return lerun_title;
	}
	public void setLerun_title(String lerun_title) {
		this.lerun_title = lerun_title;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public int getSignin_type() {
		return signin_type;
	}
	public void setSignin_type(int signin_type) {
		this.signin_type = signin_type;
	}
	public String getPersonal_name() {
		return personal_name;
	}
	public void setPersonal_name(String personal_name) {
		this.personal_name = personal_name;
	}
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	public String getCertificate_image() {
		return certificate_image;
	}
	public void setCertificate_image(String certificate_image) {
		this.certificate_image = certificate_image;
	}
	public String getSignup_time() {
		return signup_time;
	}
	public void setSignup_time(String signup_time) {
		this.signup_time = signup_time;
	}
	public int getCharge_state() {
		return charge_state;
	}
	public void setCharge_state(int charge_state) {
		this.charge_state = charge_state;
	}
	public String getPay_time() {
		return pay_time;
	}
	public void setPay_time(String pay_time) {
		this.pay_time = pay_time;
	}
	public int getPayment() {
		return payment;
	}
	public void setPayment(int payment) {
		this.payment = payment;
	}
	public int getCheck_state() {
		return check_state;
	}
	public void setCheck_state(int check_state) {
		check_state = check_state;
	}
	public String getIdentity_type() {
		return identity_type;
	}
	public void setIdentity_type(String identity_type) {
		this.identity_type = identity_type;
	}
	public String getIdentity_card() {
		return identity_card;
	}
	public void setIdentity_card(String identity_card) {
		this.identity_card = identity_card;
	}
	public int getInsurance_state() {
		return insurance_state;
	}
	public void setInsurance_state(int insurance_state) {
		this.insurance_state = insurance_state;
	}
	public int getInsurance_id() {
		return insurance_id;
	}
	public void setInsurance_id(int insurance_id) {
		this.insurance_id = insurance_id;
	}
	public String getDress_size() {
		return dress_size;
	}
	public void setDress_size(String dress_size) {
		this.dress_size = dress_size;
	}
	public String getSign_time() {
		return sign_time;
	}
	public void setSign_time(String sign_time) {
		this.sign_time = sign_time;
	}
	public int getSign_state() {
		return sign_state;
	}
	public void setSign_state(int sign_state) {
		this.sign_state = sign_state;
	}
	public String getSign_barcode() {
		return sign_barcode;
	}
	public void setSign_barcode(String sign_barcode) {
		this.sign_barcode = sign_barcode;
	}
	public String getUser_sex() {
		return user_sex;
	}
	public void setUser_sex(String user_sex) {
		this.user_sex = user_sex;
	}
	
	

}
