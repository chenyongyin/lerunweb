package com.lerun.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.GsonBuilder;
import com.lerun.bean.ResponseObject;
import com.lerun.model.OrderInfo;
import com.lerun.service.volunteerService;

public class VolunteerServlet extends HttpServlet {

	public VolunteerServlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");// 设置响应返回体参数 设置字符格式为utf-8
		PrintWriter out = response.getWriter();
		ResponseObject result = null;
		int index=0;
		OrderInfo beaninfo=null;
		String flag = request.getParameter("flag");
		System.out.println(flag);
		if (flag.equals("vlogin") ) {
			volunteerService volunteerService = new volunteerService();
			int voluntary_id = Integer.parseInt(request.getParameter("vlid"));
			String voluntary_pwd = request.getParameter("vlpwd");
			try {
				index = volunteerService.voluntaryLogin(voluntary_id,
						voluntary_pwd);
				if (index == 1) {
					result = new ResponseObject(1, "欢迎登录志愿者端！");
				} else {
					result = new ResponseObject(0, "登录失败！");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (flag.equals("vscanf") ) {
			volunteerService volunteerService = new volunteerService();
			int lerun_id = Integer.parseInt(request.getParameter("lerun_id"));
			String user_id = request.getParameter("user_id");
			String user_telphone=request.getParameter("user_telphone");
			System.out.println("user_id"+user_id);
			System.out.println("lerun_id"+lerun_id);
			
			
			try {
				beaninfo = volunteerService.returnVoluntaryInfo(lerun_id, user_id,user_telphone);
				if (beaninfo != null) {
					result = new ResponseObject(1, beaninfo);
					System.out.println("result:"+new GsonBuilder().create().toJson(result));
				} else {
					result = new ResponseObject(0, "信息有误！");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (flag.equals("vconfirm")) {
			volunteerService volunteerService = new volunteerService();
			int lerun_id = Integer.parseInt(request.getParameter("lerun_id"));
			String user_id = request.getParameter("user_id");
			try {
				index = volunteerService.SignIn(user_id, lerun_id);
				if (index == 1) {
					result = new ResponseObject(1, "签到成功！");
				} else {
					result = new ResponseObject(0, "操作失败！");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			result = new ResponseObject(0, "请求失败，请检查请求操作！");
		}
		out.println(new GsonBuilder().create().toJson(result));
		System.out.println(new GsonBuilder().create().toJson(result));
		out.flush();
		out.close();
	}

}
