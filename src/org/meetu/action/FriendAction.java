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
import org.meetu.model.FriendRel;
import org.meetu.model.FriendReq;
import org.meetu.model.User;
import org.meetu.service.IFriendRelService;
import org.meetu.service.IFriendReqService;
import org.meetu.service.IPushService;
import org.meetu.service.IUserService;
import org.meetu.util.BeanConverter;
import org.meetu.util.CheckUtil;
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
	// @Resource(name="reqService")
	@Autowired
	private IFriendReqService reqService;

	@Autowired
	private IFriendRelService relService;

	@Autowired
	private IUserService userService;

	/** 推送服务 */
	@Autowired
	private IPushService pushService;

	/**
	 * STRUTS2接收上传参数对象
	 * */
	private FriendReq req;

	/**
	 * STRUTS2接收上传参数对象
	 * */
	private User user;

	/**
	 * STRUTS2接收上传参数对象
	 * */
	private FriendRel rel;

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
		BaseDto dto = new BaseDto();// 返回的对象
		try {
			out = response.getWriter();
			req.setReqStatus(REQ_STATUS_ORIGIN);// 设置请求状态为初始状态
			// 根据userId和friendId查询req表,是否存在未处理的请求
			FriendReq reqCondition = new FriendReq();// 构造只包含要查询,查询条件的对象
			reqCondition.setReqUserId(req.getReqUserId());
			reqCondition.setReqFriendId(req.getReqFriendId());
			List list = reqService.queryList(reqCondition);
			if (list == null) {
				dto = new BaseDto(STATUS_FAIL, "好友请求发送失败,查询失败");
				return null;
			} else if (list.size() == 0) {
				req.setReqTime(new Date());// 请求时间为当前时间
				int reqId = reqService.insert(req);// 向好友申请表insert数据
				// 给对方发送好友请求推送
				PushBaiduParam param = new PushBaiduParam();
				param.setType(PUSHTYPE_PUSH);// 0透传 1推送
				param.setTitle("您有一个好友申请未处理");
				param.setDesc("用户ID: " + req.getReqUserId() + " 通过 "
						+ req.getReqWay() + " 想添加您为好友!  留言:"
						+ req.getReqMessage() + " |||| REQID=" + reqId);
				List<Integer> userIds = new ArrayList<>();
				userIds.add(req.getReqFriendId());
				pushService.pushToTarget(userIds, param);
			} else {
				dto = new BaseDto(STATUS_FAIL, "您已经向对方发送过好友请求,请等待对方处理");
				return null;
			}
		} catch (Exception e) {
			logger.error(e);
			logger.error("发送好友请求失败", e);
			dto = new BaseDto(STATUS_FAIL, "好友请求发送失败");
		} finally {
			xml = BeanConverter.bean2xml(dto);
			out.write(xml);
			logger.warn("发送好友申请返回的XML是");
			logger.warn(xml);
			out.close();
		}
		return null;
	}

	/**
	 * 处理好友请求<br>
	 * 1.更新REQ表 2.如果通过验证,则插入REL表
	 * */
	public String dealFriendReq() {
		// 需要上传字段.上传req对象接收即可
		// 1.userId本人id 2.friendId 3.reqStatus状态
		request = ServletActionContext.getRequest();
		response = ServletActionContext.getResponse();
		FriendReqDealDto dealDto = new FriendReqDealDto();
		try {
			out = response.getWriter();
			if (req.getReqStatus() == null) {
				dealDto.setErrCode(STATUS_FAIL);
				dealDto.setErrMsg("处理好友申请异常,status参数异常");
			} else {
				// service层处理好友申请,带事务管理
				dealDto = reqService.dealFriendReq(req);
			}
		} catch (Exception e) {
			logger.error(e.getStackTrace());
			logger.error("处理好友申请异常", e);
			dealDto.setErrCode(STATUS_FAIL);
			dealDto.setErrMsg("处理好友申请DB异常");
		} finally {
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
			req.setReqStatus(REQ_STATUS_ORIGIN);// 查询初始状态的好友申请
			list = reqService.queryList(req);
			beans.setList(list);
		} catch (IOException e) {
			logger.error(e);
			logger.error("查询好友请求异常", e);
			beans.setErrCode(STATUS_FAIL);
			beans.setErrMsg("查询好友请求异常");
		} finally {
			xml = BeanConverter.bean2xml(beans);
			out.write(xml);
			logger.warn("主动获取好友申请信息返回XML是");
			logger.warn(xml);
			out.close();
		}

		return null;
	}

	/**
	 * 查找自己所有好友<br>
	 * 可用作好友列表,好友筛选...
	 * */
	public String getMyFriendList() {
		request = ServletActionContext.getRequest();
		response = ServletActionContext.getResponse();
		ListBean<User> beans = new ListBean<>();
		// 接收参数
		String userIdStr = request.getParameter("userId");
		String statusRel = request.getParameter("statusRel");
		Integer userId = null;
		try {
			userId = Integer.valueOf(userIdStr);
			List list = null;
			out = response.getWriter();
			// list = userService.queryMyFriendList(user.getId());//获取所有用户
			list = relService.queryMyFriendList(userId, statusRel);
			beans.setList(list);
			if(list == null) {
				beans.setErrCode(STATUS_FAIL);
				beans.setErrMsg("好友查询异常");
			}
		} catch (Exception e) {
			logger.error("获取好友列表失败", e);
		} finally {
			xml = BeanConverter.bean2xml(beans);
			// xml = CheckUtil.replaceESC(xml);//不能在server端进行转换,否则客户端xsteam无法解析
			out.write(xml);
			logger.warn("获取好友列表返回的数据是");
			logger.warn(xml);
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
	 * @param req
	 *            the req to set
	 */
	public void setReq(FriendReq req) {
		this.req = req;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the rel
	 */
	public FriendRel getRel() {
		return rel;
	}

	/**
	 * @param rel
	 *            the rel to set
	 */
	public void setRel(FriendRel rel) {
		this.rel = rel;
	}

}
