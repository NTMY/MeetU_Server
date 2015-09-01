package org.meetu.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.meetu.dto.BaseDto;
import org.meetu.dto.PubDataDto;
import org.meetu.util.BeanConverter;

import com.opensymphony.xwork2.ActionSupport;

import static org.meetu.constant.Constant.*;

/**
 * 公共报文
 * */
public class PubAction extends ActionSupport {

	private static final long serialVersionUID = 6782900565506818589L;
	
	private static Log logger = LogFactory.getLog(PubAction.class);
	
	HttpServletRequest request = null;
	HttpServletResponse response = null;
	
	PrintWriter out = null;
	/**
	 * 写回给客户端的xml串 
	 * */
	String xml = "";
	
	
	/**
	 * 公共数据接口
	 * */
	public String pubData() {
		request = ServletActionContext.getRequest();
		response = ServletActionContext.getResponse();
		PubDataDto dto = new PubDataDto();
		
		String os = request.getParameter("os");//操作系统名称/android/ios
		String osVer = request.getParameter("osVer");//操作系统版本
		String appVerStr = request.getParameter("appVer");// 客户端app版本号
		String signature = request.getParameter("signature");// 客户端签名加密串

		try {
			out = response.getWriter();
			float appVer = Float.valueOf(appVerStr);
			
			if(appVer != 1.0f) {
				throw new RuntimeException("版本信息异常,请去官网下载安装最新版本");
			}
			
			dto.setUploadFreq("300");
			dto.setMeetuFreq("600");
			
		} catch (IOException e) {
			logger.error(e);
			e.printStackTrace();
			dto = new PubDataDto(STATUS_FAIL , "IO EXCEPTION");
		} catch(NumberFormatException e) {
			logger.error(e);
			e.printStackTrace();
			dto = new PubDataDto(STATUS_FAIL , "参数异常");
		} catch(RuntimeException e) {
			dto = new PubDataDto(STATUS_FAIL , e.getMessage());
		} 
		finally{
			xml = BeanConverter.bean2xml(dto);
			logger.warn("pubData接口返回数据");
			logger.warn(xml);
			out.close();
		}

		return null;
	}

}
