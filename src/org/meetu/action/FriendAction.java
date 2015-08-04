package org.meetu.action;

import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.meetu.dto.BaseDto;
import org.meetu.model.FriendReq;
import org.meetu.service.IFriendReqService;
import org.meetu.util.BeanConverter;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;

import static org.meetu.constant.Constant.*;
/**
 * 好友操作Action<br>
 * */
public class FriendAction extends ActionSupport {
	
	private static final long serialVersionUID = 824855826950474674L;
	
	private static Log logger = LogFactory.getLog(FriendAction.class);
	
	HttpServletRequest request = null;
	
	HttpServletResponse response = null;
	
	PrintWriter out = null;
	
	/** 好友申请service */
//	@Resource(name="reqService")
	@Autowired
	private IFriendReqService reqService;
	
	/**
	 * STRUTS2接收上传参数对象
	 * */
	private FriendReq req;
	
	/**
	 *  
	 * */
	String xml = "";
	
	
	/**
	 * 发送好友申请 
	 * */
	public String sendFriendReq() {
		request = ServletActionContext.getRequest();
		response = ServletActionContext.getResponse();
		BaseDto dto = new BaseDto();//返回的对象
		try {
			out = response.getWriter();
			req.setReqTime(new Date());//请求时间为当前时间
			req.setReqStatus(REQ_STATUS_ORIGIN);//设置请求状态为初始状态
			reqService.insert(req);
		} catch (Exception e) {
			logger.error(e);
			dto = new BaseDto(STATUS_FAIL,"好友请求发送失败");
		} finally{
			BeanConverter.bean2xml(dto);
			out.close();
		}
		return null;
	}
	
	
	/**
	 * 处理好友请求
	 * */
	public String dealFriendReq() {
		//上传什么字段
		//userId,

		//流程
		//1.更新REQ表  如果通过验证,则插入REL表
		
		return null;
	}
	
	
	/**
	 * 改变自己好友的状态<br>
	 * 拉黑/删除(没有物理删除,呵呵 你们断不开的,请永远藕断丝连吧)
	 * */
	public String updateFriendRelStatus() {
		
		return null;
	}


	/***************************************************************************
	 * 
	 * getters and setters
	 * 
	 ***************************************************************************/
	/**
	 * @return the req
	 */
	public FriendReq getReq() {
		return req;
	}


	/**
	 * @param req the req to set
	 */
	public void setReq(FriendReq req) {
		this.req = req;
	}


	
}
