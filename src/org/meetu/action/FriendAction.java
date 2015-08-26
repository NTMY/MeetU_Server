package org.meetu.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.meetu.dto.BaseDto;
import org.meetu.dto.FriendReqDealDto;
import org.meetu.dto.PushBaiduParam;
import org.meetu.model.FriendReq;
import org.meetu.service.IFriendRelService;
import org.meetu.service.IFriendReqService;
import org.meetu.service.IPushService;
import org.meetu.util.BeanConverter;
import org.meetu.util.ListBean;
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
	
	@Autowired
	private IFriendRelService relService;
	
	/** 推送服务 */
	@Autowired
	private IPushService pushService;
	
	/**
	 * STRUTS2接收上传参数对象
	 * */
	private FriendReq req;
	
	/**
	 *  
	 * */
	String xml = "";
	
	
	/**
	 * 发送好友申请<br>
	 * 1.好友申请 数据入库<br>
	 * 2.给对方发送推送<br>
	 * */
	public String sendFriendReq() {
		request = ServletActionContext.getRequest();
		response = ServletActionContext.getResponse();
		BaseDto dto = new BaseDto();//返回的对象
		try {
			out = response.getWriter();
			req.setReqTime(new Date());//请求时间为当前时间
			req.setReqStatus(REQ_STATUS_ORIGIN);//设置请求状态为初始状态
			int reqId = reqService.insert(req);//向好友申请表insert数据
			//给对方发送好友请求推送
			PushBaiduParam param = new PushBaiduParam();
			param.setType(PUSHTYPE_PUSH);//0透传 1推送
			param.setTitle("您有一个好友申请未处理");
			param.setDesc("用户ID: "+req.getReqUserId()+" 通过 " +req.getReqWay() + " 想添加您为好友!  留言:"+ req.getReqMessage() +" |||| REQID="+ reqId);
			List<Integer> userIds = new ArrayList<>();
			userIds.add(req.getReqFriendId());
			pushService.pushToTarget(userIds , param);
		} catch (Exception e) {
			logger.error(e);
			dto = new BaseDto(STATUS_FAIL,"好友请求发送失败");
		} finally{
			xml = BeanConverter.bean2xml(dto);
			out.write(xml);
			out.close();
		}
		return null;
	}
	
	
	/**
	 * 处理好友请求<br>
	 * 1.更新REQ表
	 * 2.如果通过验证,则插入REL表
	 * */
	public String dealFriendReq() {
		//需要上传字段.上传req对象接收即可
		//1.userId本人id 2.friendId 3.reqStatus状态
		request = ServletActionContext.getRequest();
		response = ServletActionContext.getResponse();
		FriendReqDealDto dealDto = new FriendReqDealDto();
		try{
			out = response.getWriter();
			if(req.getReqStatus() == null) {
				dealDto.setErrCode(STATUS_FAIL);
				dealDto.setErrMsg("处理好友申请异常,status参数异常");
			} else {
				//service层处理好友申请,带事务管理
				dealDto = reqService.dealFriendReq(req);
			}
		} catch(Exception e) {
			logger.error(e.getStackTrace());
			e.printStackTrace();
			dealDto.setErrCode(STATUS_FAIL);
			dealDto.setErrMsg("处理好友申请DB异常");
		} finally{
			xml = BeanConverter.bean2xml(dealDto);
			out.write(xml);
			logger.warn("处理好友请求返回的XML是");
			logger.warn(xml);
			out.close();
		}
		
		return null;
	}
	
	
	/**
	 * 改变自己好友的状态<br>
	 * 拉黑/删除(没有物理删除)
	 * */
	public String updateFriendRelStatus() {
		
		return null;
	}

	
	/**
	 * 客户端主动获取好友请求<br>
	 * 客户端定时取数据,自定义通知,防止推送慢或失败<br>
	 * 保证好友请求可以通知到位
	 * */
	public String getFriendReqActive() {
		request = ServletActionContext.getRequest();
		response = ServletActionContext.getResponse();
		ListBean<FriendReq> beans = new ListBean<>();
		List<FriendReq> list = new ArrayList<>();
		try {
			out = response.getWriter();
			list = reqService.queryList(req);
			beans.setList(list);
		} catch (IOException e) {
			logger.error(e);
			e.printStackTrace();
			beans.setErrCode(STATUS_FAIL);
			beans.setErrMsg("查询好友请求异常");
		} finally{
			xml = BeanConverter.bean2xml(beans);
			out.write(xml);
			out.close();
		}
				
		
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
