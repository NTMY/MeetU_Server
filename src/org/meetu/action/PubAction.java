package org.meetu.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 公共报文
 * */
public class PubAction extends ActionSupport {

	private static final long serialVersionUID = 6782900565506818589L;
	private static Log log = LogFactory.getLog(PubAction.class);
	HttpServletRequest request = null;
	HttpServletResponse response = null;
	PrintWriter out = null;

	/**
	 * 公共数据接口
	 * */
	public String pubData() {
		request = ServletActionContext.getRequest();
		response = ServletActionContext.getResponse();

		String os = request.getParameter("os");//操作系统名称/android/ios
		String osVer = request.getParameter("osVer");//操作系统版本
		String appVer = request.getParameter("appVer");// 客户端app版本号
		String signature = request.getParameter("signature");// 客户端签名加密串

		try {
			out = response.getWriter();

		} catch (IOException e) {
			log.error(e.getCause());
		}

		return null;
	}

}
