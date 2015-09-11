package org.meetu.action;

import static org.meetu.constant.Constant.STATUS_FAIL;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.meetu.dto.BaseDto;
import org.meetu.model.LocationCurr;
import org.meetu.model.LocationHis;
import org.meetu.service.ILocationCurrService;
import org.meetu.service.ILocationHisService;
import org.meetu.service.impl.LocationCurrServiceImpl;
import org.meetu.service.impl.LocationHisServiceImpl;
import org.meetu.util.BeanConverter;
import org.meetu.util.ListBean;
import org.meetu.util.RangeCalculator;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 相遇Action
 * */
public class MeetuAction {

	private static Log logger = LogFactory.getLog(MeetuAction.class);
	
	/**
	 * 当使用aop时,必须定义注入接口?定义为实现类时无法注入
	 * */
	@Autowired
	private ILocationCurrService currService;
	
	@Autowired
	private ILocationHisService hisService;

	HttpServletRequest request = null;

	HttpServletResponse response = null;

	PrintWriter out = null;
	
	/**
	 * 写回的xml
	 * */
	String retXml = "";

	/**
	 * STRUTS2 传递参数对象
	 * */
	private LocationCurr curr;

	/**
	 * 上报自己当前位置信息,但不查询附近的人
	 * 
	 * @param 客户端上传curr对象
	 * */
	public String upload() {
		request = ServletActionContext.getRequest();
		response = ServletActionContext.getResponse();
		BaseDto dto = new BaseDto();// 返回的对象(转xml)

		try {
			out = response.getWriter();
			dto = currService.upload(curr);
		} catch(Exception e) {
			logger.error(e);
			logger.error("用户上报UPLOAD异常",e);
		} finally {
			retXml = BeanConverter.bean2xml(dto);
			logger.warn("用户上报UPLOAD接口返回XML");
			logger.warn(retXml);
			out.write(retXml);
			out.close();
		}
		return null;
	}

	/**
	 * meetu core<br>
	 * 1.删除本用户旧的curr表中信息,将新上传的信息入库curr表<br>
	 * 2.查找N小时内,N米内的好友
	 * 
	 * @param 客户端上传curr对象
	 * */
	public String meetu() {
		request = ServletActionContext.getRequest();
		response = ServletActionContext.getResponse();
		ListBean beans = new ListBean();
		try {
			out = response.getWriter();
			beans = currService.meetu(curr);
		} catch (Exception e) {
			logger.error(e);
			logger.error("相遇MEETU",e);
			beans.setErrCode(STATUS_FAIL);
			beans.setErrMsg(e.getCause().toString());
		} finally {
			retXml = BeanConverter.bean2xml(beans); // detail数据
			logger.warn("用户相遇MEETU接口返回XML");
			logger.warn(retXml);
			out.write(retXml);
			out.close();
		}
		return null;
	}

	/**
	 * getters and setters
	 * */

	public LocationCurr getCurr() {
		return curr;
	}

	public void setCurr(LocationCurr curr) {
		this.curr = curr;
	}
}
