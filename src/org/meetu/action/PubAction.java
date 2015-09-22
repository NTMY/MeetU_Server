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
import org.meetu.util.CheckUtil;
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
	 * 公共数据接口<br>
	 * 数据缓存暂时使用内存单例map<br>
	 * 下一步看看memcache
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
			//缓存中的appVer对象(来自于DB)
			AppVer appVerDB = (AppVer)Cache.get(os);
			
			String signDB = appVerDB.getSignature();
			if(!signDB.equals(signature)) {
				logger.error("===== SIGNATURE软件签名不正确,请在官网重新下载安装app =====");
				dto = new PubDataDto(STATUS_FAIL , "SIGNATURE软件签名不正确,请在官网重新下载安装app");
				return null;
			}
			//校验版本号 0:不更新 1:建议更新 2:强制更新
			String updateLv = CheckUtil.checkAppVer(appVerDB.getAppVer(), appVerStr);
			
			dto.setUpdateLv(updateLv);//建议更新级别
			if(!updateLv.equals("0")) {
				dto.setDownloadUrl(appVerDB.getDownloadUrl());//新版本下载地址
				dto.setDesc(appVerDB.getDescription());//新版本描述
			}
			dto.setUploadFreq("300");//upload接口调用周期(秒)
			dto.setMeetuFreq("600");//meetu接口调用周期(秒)
			
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
			out.write(xml);
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
