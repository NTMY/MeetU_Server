package org.meetu.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.meetu.cache.Cache;
import org.meetu.dto.PubDataDto;
import org.meetu.model.AppVer;
import org.meetu.model.PubData;
import org.meetu.service.IAppVerService;
import org.meetu.util.BeanConverter;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;

import static org.meetu.constant.Constant.*;

/**
 * 公共报文
 * */
public class PubAction extends ActionSupport {

	private static final long serialVersionUID = 6782900565506818589L;
	
	private static Log logger = LogFactory.getLog(PubAction.class);
	
	@Autowired
	private IAppVerService appVerService;
	
	HttpServletRequest request = null;
	HttpServletResponse response = null;
	
	PrintWriter out = null;
	/**
	 * 写回给客户端的xml串 
	 * */
	String xml = "";
	
	/**
	 * STRUTS2注入参数对象
	 * */
	private PubData data;
	
	
	
	/**
	 * 公共数据接口
	 * */
	public String pubData() {
		request = ServletActionContext.getRequest();
		response = ServletActionContext.getResponse();
		PubDataDto dto = new PubDataDto();
		
		String os = data.getOs();//操作系统名称/android/ios
		String appVerStr = data.getAppVer();// 客户端app版本号
		String signature = data.getSignature();// 客户端签名加密串

		try {
			out = response.getWriter();
			//缓存中的appVer信息(来自于DB)
			AppVer appVerDB = (AppVer)Cache.get(os);
			
			//TODO...比较版本信息,先睡觉去了
			float appVer = Float.valueOf(appVerStr);
			
			if(appVer != 1.0f) {
				throw new RuntimeException("版本信息异常,请去官网下载安装最新版本");
			}
			
			dto.setUploadFreq("300");
			dto.setMeetuFreq("600");
			
		} catch (IOException e) {
			logger.error(e);
			logger.error("PUBDATA IO EX", e);
			e.printStackTrace();
			dto = new PubDataDto(STATUS_FAIL , "IO EXCEPTION");
		} catch(NumberFormatException e) {
			logger.error(e);
			logger.error("PUBDATA NUMBERFMT EX", e);
			e.printStackTrace();
			dto = new PubDataDto(STATUS_FAIL , "参数异常");
		} catch(RuntimeException e) {
			logger.error("PUBDATA RUNTIME EX", e);
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



	public PubData getData() {
		return data;
	}



	public void setData(PubData data) {
		this.data = data;
	}

}
