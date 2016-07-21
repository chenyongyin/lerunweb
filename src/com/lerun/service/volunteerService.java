package com.lerun.service;

import java.sql.SQLException;

import com.lerun.bean.GeneralBean;
import com.lerun.dao.OrderInfoDao;
import com.lerun.dao.VoluntaryTableDao;

public class volunteerService {

	private OrderInfoDao orderVolunteer =new OrderInfoDao();
	private VoluntaryTableDao voluntaryTableDao=new VoluntaryTableDao();
	private int flag = 0;
	private GeneralBean bean=null;
	
	//志愿者登录    voluntaryLogin
	
	public int voluntaryLogin(int voluntary_id, String voluntary_pwd)
			throws SQLException {
		System.out.println(flag);
		flag=voluntaryTableDao.voluntaryLogin(voluntary_id, voluntary_pwd);
		System.out.println(flag);
		return flag;
	}
	
	//志愿者扫描判断  
	public GeneralBean returnVoluntaryInfo(int lerun_id, String user_id)
			throws SQLException {		
		bean=orderVolunteer.returnVoluntaryInfo(lerun_id, user_id);		
		return bean;
	}
	//签到实现    1签到成功 0签到失败   SignIn
	public int SignIn(String user_id, int lerun_id) throws SQLException {
		flag=orderVolunteer.SignIn(user_id, lerun_id);
		return flag;
	}
}
