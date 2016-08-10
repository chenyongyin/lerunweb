package com.lerun.model;
/**
 *@Project: LeRun
 *@Author: wschenyongyin
 *@Date: 2016年7月26日
 *@explain:保险配置表
 *@TestState:
 */

public class InsuranceTable {
	
	private String insurance_name;
	private String pay_money;
	private String insurance_money;
	private String insurancecompany_name;
	private int insurance_id;
	public InsuranceTable() {
		super();
		// TODO Auto-generated constructor stub
	}
	public InsuranceTable(String insurance_name, String pay_money,
			String insurance_money, String insurancecompany_name,
			int insurance_id) {
		super();
		this.insurance_name = insurance_name;
		this.pay_money = pay_money;
		this.insurance_money = insurance_money;
		this.insurancecompany_name = insurancecompany_name;
		this.insurance_id = insurance_id;
	}
	public String getInsurance_name() {
		return insurance_name;
	}
	public void setInsurance_name(String insurance_name) {
		this.insurance_name = insurance_name;
	}
	public String getPay_money() {
		return pay_money;
	}
	public void setPay_money(String pay_money) {
		this.pay_money = pay_money;
	}
	public String getInsurance_money() {
		return insurance_money;
	}
	public void setInsurance_money(String insurance_money) {
		this.insurance_money = insurance_money;
	}
	public String getInsurancecompany_name() {
		return insurancecompany_name;
	}
	public void setInsurancecompany_name(String insurancecompany_name) {
		this.insurancecompany_name = insurancecompany_name;
	}
	public int getInsurance_id() {
		return insurance_id;
	}
	public void setInsurance_id(int insurance_id) {
		this.insurance_id = insurance_id;
	}
	
	

}
