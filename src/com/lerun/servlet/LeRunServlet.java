package com.lerun.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lerun.model.LeRun;
import com.lerun.model.OrderInfo;
import com.lerun.model.ShowTable;
import com.lerun.model.UserInfo;
import com.lerun.service.LeRunService;
import com.lerun.service.LunBoService;
import com.lerun.service.ShowService;
import com.lerun.service.SystemService;
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

		PrintWriter out = response.getWriter();
		String jsonSting = null;
		int result;
		String flag = request.getParameter("flag");
		int index = Integer.parseInt(request.getParameter("index"));

		System.out.println("flag标记：" + flag);
		System.out.println("index标记:" + index);

		if (flag.equals("user")) {
			// index表示的操作 0：表示注册 1：表示登陆 2：表示注销 3：表示修改信息 4：表示查看用户信息

			UserService service = new UserService();
			switch (index) {
			case 0:
				String user_id = request.getParameter("user_id");
				String user_pwd = request.getParameter("user_pwd");
				System.out.println("注册的id:" + user_id);
				System.out.println("注册的pwd:" + user_pwd);
				try {
					result = service.userRegister(user_id, user_pwd);

					System.out.println("注册结果:" + result);
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
				System.out.println("用户id:" + request.getParameter("user_id"));
				System.out.println("update_type:"
						+ request.getParameter("update_type"));
				System.out.println("update_values:"
						+ request.getParameter("update_values"));
				try {
					result = service.upDateInfo(info);
					System.out.println("result" + result);
					out.print(result);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				break;
			case 4:
				String user_id4 = request.getParameter("user_id");
				System.out.println("user_id:" + user_id4);
				try {
					jsonSting = service.QueryInfo(user_id4);
					System.out.println("获取用户信息：" + jsonSting);
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
					jsonSting = service.QueryAll("江西");
					System.out.println("获取所有活动" + jsonSting);
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
					System.out.println("lerun_id:" + lerun_id);
					jsonSting = service.QueryDetail(lerun_id);
					System.out.println("活动详情信息" + jsonSting);
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
				OrderInfo info = new OrderInfo();
				info.setUser_id(request.getParameter("user_id"));
				info.setLerun_id(Integer.parseInt(request
						.getParameter("lerun_id")));
				info.setUser_telphone(request.getParameter("user_telphone"));
				info.setLerun_title(request.getParameter("lerun_title"));
				System.out.println("signin_type"
						+ request.getParameter("signin_type"));
				info.setSignin_type(Integer.parseInt(request
						.getParameter("signin_type")));

				info.setPersonal_name(request.getParameter("personal_name"));
				info.setCompany_name(request.getParameter("company_name"));
				info.setCertificate_image(request
						.getParameter("certificate_image"));
				info.setIdentity_type(request.getParameter("identity_type"));
				info.setIdentity_card(request.getParameter("identity_card"));
				// info.setInsurance_id(Integer.parseInt(request.getParameter("insurance_id")));
				info.setDress_size(request.getParameter("dress_size"));
				info.setUser_sex(request.getParameter("user_sex"));
				info.setPayment(Integer.parseInt(request
						.getParameter("payment")));
				System.out.println("用户电话:"
						+ request.getParameter("user_telphone"));
				System.out.println("价格payent："
						+ request.getParameter("payment"));
				System.out.println("用户id:" + request.getParameter("user_id"));
				try {
					result = service.SignUp(info);
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
				int payment = Integer.parseInt(request.getParameter("payment"));
				try {
					result = service.paySuccess(QRcode_Path, payment,
							lerun_id5, user_id5);
					out.print(result);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			// 用户查看参加的活动

			case 6:
				String user_id6 = request.getParameter("user_id");
				System.out.println("用户id" + user_id6);
				try {
					jsonSting = service.QueryPersonalLerun(user_id6);
					out.print(jsonSting);
					System.out.println(jsonSting);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			// 查询结束的活动
			case 7:
				try {
					jsonSting = service.QueryEnd();
					out.print(jsonSting);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 8:
				String user_id8 = request.getParameter("user_id");
				int lerun_id8 = Integer.parseInt(request
						.getParameter("lerun_id"));
				try {
					System.out.println("用户名:" + user_id8 + "lerun_id8:"
							+ lerun_id8);
					jsonSting = service.getQrCode(user_id8, lerun_id8);
					System.out.println("查询二维码返回的结果:" + jsonSting);
					out.print(jsonSting);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				break;
			case 9:
				String user_id9 = request.getParameter("user_id");

				try {
					jsonSting = service.QueryPersonalNoPayLerun(user_id9);
					System.out.println("查询未付款的结果:" + jsonSting);
					out.print(jsonSting);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				break;
			case 10:
				String user_id10 = request.getParameter("user_id");
				String user_telphone = request.getParameter("user_telphone");
				try {
					System.out.println("用户名:" + user_id10 + "联系号码:"
							+ user_telphone);
					jsonSting = service.getPersonQrCode(user_id10,
							user_telphone);
					System.out.println("取个人二维码:" + jsonSting);
					out.print(jsonSting);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			default:
				break;
			}
		}
		// show
		else if (flag.equals("show")) {
			// index: 0用户发布show 1：用户删除show 2：获取所有秀内容 3获取自己发布的show 4.获取show的评论
			// 5获取show的点赞信息 6用户取消点赞 7评论 8点赞
			ShowService showSerview = new ShowService();
			switch (index) {
			case 0:
				ShowTable show = new ShowTable();
				show.setUser_id(request.getParameter("user_id"));
				show.setShow_content(request.getParameter("show_content"));
				show.setShow_image(request.getParameter("show_image"));
				System.out.println("用户发布show的图片"
						+ request.getParameter("show_image"));

				try {
					result = showSerview.ReleaseShow(show);
					System.out.println("发布show:" + result);
					out.print(result);
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

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
				String user_id2 = request.getParameter("user_id");
				System.out.println("pageSize:" + pageSize);
				System.out.println("currentPage:" + currentPage);
				System.out.println("user_id2:" + user_id2);
				try {
					jsonSting = showSerview.QueryAllShow(pageSize, currentPage,
							user_id2);
					out.print(jsonSting);
					System.out.println("获取全部show：" + jsonSting);
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
					System.out.println("查看自己的show：" + jsonSting);

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				break;
			// 获取show的评论
			case 4:
				int show_id2 = Integer
						.parseInt(request.getParameter("show_id"));
				System.out.println("获取show的评论时show_id" + show_id2);
				try {
					jsonSting = showSerview.QueryShowComment(show_id2);
					System.out.println("获取show的评论" + jsonSting);
					out.print(jsonSting);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			// 获取show的点赞信息
			case 5:
				int show_id3 = Integer
						.parseInt(request.getParameter("show_id"));
				try {
					jsonSting = showSerview.QueryShowLike(show_id3);
					out.print(jsonSting);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				break;
			// 取消点赞
			case 6:
				int show_id4 = Integer
						.parseInt(request.getParameter("show_id"));
				String user_id4 = request.getParameter("user_id");
				System.out.println("show_id" + show_id4);
				System.out.println("user_id" + user_id4);
				try {
					jsonSting = showSerview.canLike(user_id4, show_id4);
					System.out.println("取消点赞返回信息:" + jsonSting);
					out.print(jsonSting);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			// 评论
			case 7:
				int show_id7 = Integer
						.parseInt(request.getParameter("show_id"));
				String user_id7 = request.getParameter("user_id");
				String comment_content = request
						.getParameter("comment_content");
				String comment_userid = request.getParameter("comment_userid");
				try {
					jsonSting = showSerview.ReleaseComment(show_id7, user_id7,
							comment_content, comment_userid);
					out.print(jsonSting);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				break;
			// 点赞
			case 8:
				String user_id8 = request.getParameter("user_id");
				String like_userid8 = request.getParameter("like_userid");
				int show_id8 = Integer
						.parseInt(request.getParameter("show_id"));
				System.out.println("点赞用户的id:" + user_id8);
				System.out.println("点赞show_id:" + show_id8);
				System.out.println("被点赞用户like_userid8:" + like_userid8);

				try {
					jsonSting = showSerview.ReleaseLike(user_id8, show_id8,
							like_userid8);
					System.out.println("点赞返回的数据:" + jsonSting);
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
		// 获取lunbo信息 包括轮播视频 轮播图片
		else if (flag.endsWith("lunbo")) {
			// index: 0 获取图片1获取视频
			LunBoService service = new LunBoService();
			switch (index) {
			case 0:
				try {
					jsonSting = service.getImageData();
					System.out.println("lunbo信息：" + jsonSting);
					out.print(jsonSting);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				break;
			case 1:
				try {
					jsonSting = service.getVideoData();
					out.print(jsonSting);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				break;

			default:
				break;
			}
		} else if (flag.equals("aboutus")) {
			// index 0:检查更新 1:反馈
			SystemService service=new SystemService();
			switch (index) {
			case 0:
				String version_num=request.getParameter("version_number");
				try {
					
					jsonSting=service.checkVersion(version_num);
					System.out.println("检查版本更新："+jsonSting);
					out.print(jsonSting);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				break;
				
			case 1:
				String version_number=request.getParameter("version_number");
				String update_content=request.getParameter("update_content");
				String update_url=request.getParameter("update_url");
				
				try {
					jsonSting=service.ReleaseVersion(version_number, update_content, update_url);
					out.print(jsonSting);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				break;
			case 2:
				
				String feedback_content=request.getParameter("feedback_content");
				String user_id=request.getParameter("user_id");
				String user_telphone=request.getParameter("user_telphone");
				jsonSting=service.FeedBack(feedback_content, user_id, user_telphone);
				out.print(jsonSting);

				break;

			default:
				break;
			}

		}
	}

	public void init() throws ServletException {

	}

}
