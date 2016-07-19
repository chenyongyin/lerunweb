package com.lerun.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lerun.dao.ShowTableDao;
import com.lerun.model.ShowTable;
import com.lerun.utils.DividePageUtil;
import com.lerun.utils.JsonTools;

/**
 *@Author: wschenyongyin
 *@Date: 2016-7-16
 *@explain:
 *@TestState:
 */
public class ShowService {

	ShowTableDao showDao = new ShowTableDao();

	int flag = 0;
	int tag;
	String result;

	// 用户发布show
	public int ReleaseShow(ShowTable show) throws SQLException {
		tag = showDao.ReleaseShow(show);
		if (tag == 1) {
			flag = 1;
		} else {
			flag = 0;
		}
		return flag;
	}

	// 删除show

	public int deleteShow(int show_id) throws SQLException {
		tag = showDao.deleteShow(show_id);
		if (tag == 1) {
			flag = 1;
		}
		return flag;
	}

	// 查看所有的show
	public String QueryAllShow(int pageSize, int currentPage)
			throws SQLException {
		List<ShowTable> data = new ArrayList<ShowTable>();
		// 用于存放一页的内容
		List<ShowTable> subData = null;
		data = showDao.QueryAllShow();

		if (data != null) {
			int pageNumber = data.size() / pageSize;
			System.out.println("数据的大小" + data.size());
			System.out.println("pageNumber" + pageNumber);
			// 对数据进行分页处理
			DividePageUtil dividePageUtil = new DividePageUtil(pageSize, data
					.size(), currentPage);
			// 获取指定页的起始索引
			int fromIndex = dividePageUtil.getFromIndex();
			// 获取指定页的结束索引
			int toIndex = dividePageUtil.getToIndex();
			// 判断是否还有内容
			if (currentPage * pageSize <= pageSize * pageNumber) {
				subData = data.subList(fromIndex, toIndex);
				result = JsonTools.createJsonString("result", "aaaaaa");
				System.out.println("1大小" + subData.size());

			} else if (pageSize * pageNumber < currentPage * pageSize
					&& currentPage * pageSize < pageSize + data.size()) {

				subData = data.subList(fromIndex, data.size());
				System.out.println("起始id" + data.size());
			} else {
				System.out.println("没有内容了");
				return null;
			}

		} else {
			result = "failure";
		}

		return result;
	}

	// 查看自己发布的show
	public String QueryPersonalShow(String user_id) throws SQLException {
		int tag = showDao.QueryViewExist(user_id);
		if (tag == 1) {
			System.out.println("视图已经存在，直接查询");
			List<ShowTable> tag2 = showDao.QueryShowView(user_id);
			if (tag2 != null) {
				result = JsonTools.createJsonString("result", tag2);
				System.out.println(result);
			} else {
				result = "failuer";
			}
		} else if (tag == 0) {
			int tag3 = showDao.createView(user_id);
			
			if (tag3 == 0) {
				
				List<ShowTable> tag4 = showDao.QueryShowView(user_id);
				System.out.println("tag3"+tag4);
				if (tag4 != null&&!tag4.isEmpty()) {
					result = JsonTools.createJsonString("result", tag4);
					System.out.println("创建试图成功，查询结果为" +result);
				} else {
					result = "empty";
				}
			}
		}else{
			result = "failuer";
		}

		return result;
	}

	public static void main(String[] args) throws SQLException {
		ShowService showService = new ShowService();
		// showService.QueryAllShow(2, 2);
		String Result=showService.QueryPersonalShow("12345");
		System.out.println(Result);

	}

}
