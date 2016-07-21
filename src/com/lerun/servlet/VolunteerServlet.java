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
		String flag = request.getParameter("flag");
		System.out.println(flag);
		if (flag.equals("vlogin") ) {
			volunteerService volunteerService = new volunteerService();
			int voluntary_id = Integer.parseInt(request.getParameter("vlid"));
			String voluntary_pwd = request.getParameter("vlpwd");
			try {
				index = volunteerService.voluntaryLogin(voluntary_id,
						voluntary_pwd);
				System.out.println(index);
				if (index == 1) {
					result = new ResponseObject(1, "欢迎登录志愿者端！");
				} else {
					result = new ResponseObject(0, "登录失败！");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (flag.equals("vscanf") ) {

		} else if (flag.equals("vconfirm")) {

		} else {
			result = new ResponseObject(0, "请求失败，请检查请求操作！");
		}
		out.println(new GsonBuilder().create().toJson(result));
		out.flush();
		out.close();
	}

}
