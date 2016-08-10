package com.lerun.model;
/**
 *@Project: LeRun
 *@Author: wschenyongyin
 *@Date: 2016年7月26日
 *@explain:活动收费表
 *@TestState:
 */

public class ChargeTable {
	
	private int charge_id;
	private int charge_free;
	private int charge_common;
	private int charge_vip;
	private String free_equipment;
	private String common_equipment;
	private String vip_eqeuipment;
	
	public ChargeTable() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ChargeTable(int charge_id, int charge_free, int charge_common,
			int charge_vip, String free_equipment, String common_equipment,
			String vip_eqeuipment) {
		super();
		this.charge_id = charge_id;
		this.charge_free = charge_free;
		this.charge_common = charge_common;
		this.charge_vip = charge_vip;
		this.free_equipment = free_equipment;
		this.common_equipment = common_equipment;
		this.vip_eqeuipment = vip_eqeuipment;
	}

	public int getCharge_id() {
		return charge_id;
	}
	public void setCharge_id(int charge_id) {
		this.charge_id = charge_id;
	}
	public int getCharge_free() {
		return charge_free;
	}
	public void setCharge_free(int charge_free) {
		this.charge_free = charge_free;
	}
	public int getCharge_common() {
		return charge_common;
	}
	public void setCharge_common(int charge_common) {
		this.charge_common = charge_common;
	}
	public int getCharge_vip() {
		return charge_vip;
	}
	public void setCharge_vip(int charge_vip) {
		this.charge_vip = charge_vip;
	}
	public String getFree_equipment() {
		return free_equipment;
	}
	public void setFree_equipment(String free_equipment) {
		this.free_equipment = free_equipment;
	}
	public String getCommon_equipment() {
		return common_equipment;
	}
	public void setCommon_equipment(String common_equipment) {
		this.common_equipment = common_equipment;
	}
	public String getVip_eqeuipment() {
		return vip_eqeuipment;
	}
	public void setVip_eqeuipment(String vip_eqeuipment) {
		this.vip_eqeuipment = vip_eqeuipment;
	}
	

}
