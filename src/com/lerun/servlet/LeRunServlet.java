package com.lerun.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lerun.model.LeRun;
import com.lerun.model.UserInfo;
import com.lerun.service.LeRunService;
import com.lerun.service.ShowService;
import com.lerun.service.UserService;
import com.lerun.utils.JsonTools;
import com.lerun.utils.ParsingJson;

public class LeRunServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public LeRunServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		PrintWriter out = response.getWriter();
		String jsonSting = null;
		int result;
		String flag = request.getParameter("flag");
		int index = Integer.parseInt(request.getParameter("index"));

		System.out.println("flag标记：" + flag);

		if (flag.equals("user")) {
			// index表示的操作 0：表示注册 1：表示登陆 2：表示注销 3：表示修改信息 4：表示查看用户信息

			UserService service = new UserService();
			switch (index) {
			case 0:
				String user_id = request.getParameter("user_id");
				String user_pwd = request.getParameter("user_pwd");
				try {
					result = service.userRegister(user_id, user_pwd);
					out.print(result);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				break;
			case 1:
				String user_id2 = request.getParameter("user_id");
				String user_pwd2 = request.getParameter("user_pwd");
				try {
					result = service.userLog(user_pwd2, user_id2);
					out.print(result);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				break;
			case 2:
				String user_id3 = request.getParameter("user_id");
				try {
					result = service.userLogout(user_id3);
					out.print(result);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				break;
			case 3:
				UserInfo info = new UserInfo();
				info.setUser_id(request.getParameter("user_id"));
				info.setUpdate_type(request.getParameter("update_type"));
				info.setUpdate_values(request.getParameter("update_values"));
				try {
					result = service.upDateInfo(info);
					out.print(result);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				break;
			case 4:
				String user_id4 = request.getParameter("user_id");
				try {
					jsonSting = service.QueryInfo(user_id4);
					out.print(jsonSting);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				break;

			default:
				break;
			}

		} else if (flag.equals("lerun")) {
			// index: 0获取所有未结束的活动 1：获取活动的详细信息 2：发布活动 3：修改活动信息 4 报名 5 付款成功
			// 6查看参加的活动
			LeRunService service = new LeRunService();

			switch (index) {
			// 获取所有未结束的活动
			case 0:
				try {
					jsonSting = service.QueryAll();
					out.print(jsonSting);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				break;
			// 获取活动的详细信息
			case 1:
				try {
					int lerun_id = Integer.parseInt(request
							.getParameter("lerun_id"));
					jsonSting = service.QueryDetail(lerun_id);
					out.print(jsonSting);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				break;
			// 发布活动
			case 2:
				jsonSting = request.getParameter("release");
				LeRun lerun = ParsingJson.getPayCostInfo("release", jsonSting);
				try {
					result = service.ReleaseLerun(lerun);
					out.print(result);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				break;
			// 3：修改活动信息
			case 3:
				int lerun_id = Integer.parseInt(request
						.getParameter("lerun_id"));
				String update_type = request.getParameter("update_type");
				String update_values = request.getParameter("update_values");
				try {
					result = service.update(lerun_id, update_type,
							update_values);
					out.print(result);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				break;
			// 报名乐跑
			case 4:
				String user_id = request.getParameter("user_id");
				int lerun_id4 = Integer.parseInt(request
						.getParameter("lerun_id"));
				try {
					result = service.SignUp(lerun_id4, user_id);
					out.print(result);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				break;
			// 付款成功
			case 5:
				String user_id5 = request.getParameter("user_id");
				int lerun_id5 = Integer.parseInt(request
						.getParameter("lerun_id"));
				String QRcode_Path = request.getParameter("QRcode_Path");
				try {
					result = service.paySuccess(QRcode_Path, lerun_id5,
							user_id5);
					out.print(result);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			// 用户查看参加的活动

			case 6:
				String user_id6 = request.getParameter("user_id");
				try {
					jsonSting = service.QueryPersonalLerun(user_id6);
					out.print(jsonSting);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;

			default:
				break;
			}
		}
		// show
		else if (flag.equals("show")) {
			// index: 0用户发布show 1：用户删除show 2：获取所有秀内容 3获取自己发布的show
			ShowService showSerview = new ShowService();
			switch (index) {
			case 0:

				break;
			// 删除show
			case 1:
				int show_id = Integer.parseInt(request.getParameter("show_id"));
				try {
					result = showSerview.deleteShow(show_id);
					out.print(result);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				break;
			// 获取所有show内容
			case 2:
				int pageSize = Integer.parseInt(request
						.getParameter("pageSize"));
				int currentPage = Integer.parseInt(request
						.getParameter("currentPage"));

				try {
					jsonSting = showSerview.QueryAllShow(pageSize, currentPage);
					out.print(jsonSting);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				break;
			// 查看自己发布的show
			case 3:
				String user_id = request.getParameter("user_id");
				try {
					jsonSting = showSerview.QueryPersonalShow(user_id);
					out.print(jsonSting);
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				break;

			default:
				break;
			}

		}
	}

	public void init() throws ServletException {

	}

}
