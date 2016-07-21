package com.lerun.servlet;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.lerun.bean.ResponseObject;
import com.lerun.bean.imageBean;
import com.lerun.model.ShowTable;
import com.lerun.utils.ContentCommon;
import com.lerun.utils.GsonTools;

/**
 * @Author: wschenyongyin
 * @Date: 2016-7-15
 * @explain:
 * @TestState:
 */
public class UploadServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public UploadServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		String returnImagePath = null;

		PrintWriter out = response.getWriter();
		String path = null;

		DiskFileItemFactory factory = new DiskFileItemFactory();

		// 返回的图片地址
		String returnPath = null;
		// 设置文件上传路径
		String upload = this.getServletContext().getRealPath("/");

		System.out.println("upload" + upload);
		// 获取系统默认的临时文件保存路径，该路径为Tomcat根目录下的temp文件夹
		String temp = System.getProperty("java.io.tmpdir");
		// 设置缓冲区大小为 5M
		factory.setSizeThreshold(1024 * 1024 * 5);
		// 设置临时文件夹为temp
		factory.setRepository(new File(temp));
		// 用工厂实例化上传组件,ServletFileUpload 用来解析文件上传请求
		ServletFileUpload servletFileUpload = new ServletFileUpload(factory);

		// 解析结果放在List中
		try {
			List<FileItem> list = servletFileUpload.parseRequest(request);
			System.out.println("list大小" + list.size());
			List<imageBean> imagelist = new ArrayList<imageBean>();
			for (FileItem item : list) {
				String name = item.getFieldName();
				InputStream is = item.getInputStream();

				imageBean bean = new imageBean();
				if (name.contains("content")) {
					System.out.println(inputStream2String(is));
				} else if (name.contains("img")) {
					try {
						path = upload + "userlog/" + item.getName();
						returnPath = ContentCommon.ImagePath + "image/"
								+ item.getName();
						inputStream2File(is, path);
						bean.setImagePath(returnPath);
						imagelist.add(bean);
						// break;
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

				ResponseObject responseObject = new ResponseObject(1, imagelist);
//				ResponseObject responseObject = new ResponseObject(1,showbean.getShow_image() );
				returnImagePath= GsonTools
						.createJsonString(responseObject);
				System.out.println(returnImagePath);

			}
			out.write(returnImagePath); // 这里我把服务端成功后，返回给客户端的是上传成功后路径
			
		} catch (FileUploadException e) {
			e.printStackTrace();
			System.out.println("failure");
			out.write("failure");
		}

		out.flush();
		out.close();
	}

	// 流转化成字符串
	public static String inputStream2String(InputStream is) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int i = -1;
		while ((i = is.read()) != -1) {
			baos.write(i);
		}
		return baos.toString();
	}

	// 流转化成文件
	public static void inputStream2File(InputStream is, String savePath)
			throws Exception {
		System.out.println("文件保存路径为:" + savePath);
		File file = new File(savePath);
		InputStream inputSteam = is;
		BufferedInputStream fis = new BufferedInputStream(inputSteam);
		FileOutputStream fos = new FileOutputStream(file);
		int f;
		while ((f = fis.read()) != -1) {
			fos.write(f);
		}
		fos.flush();
		fos.close();
		fis.close();
		inputSteam.close();

	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
