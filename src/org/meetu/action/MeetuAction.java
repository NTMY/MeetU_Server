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
	
	@Autowired
	private LocationCurrServiceImpl currService;
	
	@Autowired
	private LocationHisServiceImpl hisService;

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

		LocationCurr oldCurr = new LocationCurr();
		oldCurr.setUserId(curr.getUserId());

		List<LocationCurr> isExistList = currService.queryAll(oldCurr);
		try {
			out = response.getWriter();
			if (isExistList == null) {
				dto.setErrCode(STATUS_FAIL);
				dto.setErrMsg("DB查询异常");
				return null;
			}
			// 如果在curr当前表中有1条此用户记录,则将老数据迁入his历史表,(这是正常的流程)
			if (isExistList.size() == 1) {
				oldCurr = isExistList.get(0);
				currService.delete(oldCurr);// 在curr当前表中删除
				LocationHis his = new LocationHis(oldCurr);
				hisService.insert(his);// 在his历史表中添加
				// 如果在curr当前表中有多条此用户数据,则不将老数据迁入历史表,直接在当前表删除(这种情况说明有问题)
			} else if (isExistList.size() > 1) {
				currService.deleteByUserId(curr.getUserId());
			}
			// 在curr当前表中插入新数据
			currService.insert(curr);
		} catch (Exception e) {
			dto.setErrCode(STATUS_FAIL);
			dto.setErrMsg("上传用户LOCATION信息,SERVER端处理异常");
			logger.error(e);
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
		ListBean<LocationCurr> beans = new ListBean<LocationCurr>();// 返回的对象(转xml)

		LocationCurr oldCurr = new LocationCurr();
		oldCurr.setUserId(curr.getUserId());
		
		try {
			out = response.getWriter();
			List<LocationCurr> isExistList = currService.queryAll(oldCurr);
			if (isExistList == null) {
				beans.setErrCode(STATUS_FAIL);
				beans.setErrMsg("DB查询异常");
				return null;
			}

			// 如果在curr当前表中有1条此用户记录,则将老数据迁入his历史表,
			if (isExistList.size() == 1) {
				oldCurr = isExistList.get(0);
				currService.delete(oldCurr);// 在curr当前表中删除
				LocationHis his = new LocationHis(oldCurr);
				hisService.insert(his);// 在his历史表中添加
				// 如果curr当前表中有多条此人记录,则全部删除
			} else if (isExistList.size() > 1) {
				currService.deleteByUserId(curr.getUserId());
			}

			// 在curr当前表中插入新数据
			currService.insert(curr);
			// 查找附近的好友(利用当前表)
			double[] range = RangeCalculator.getSquare(curr.getLatitude(),
					curr.getLongitude(), 3000);
			curr.setRange(range);// 设置边界值(查询条件)
			List list = currService.queryNear(curr);// 查询附近的人

			beans.setList(list);
			
			throw new Exception();
		} catch (Exception e) {
			beans = new ListBean<>();
			beans.setErrCode(STATUS_FAIL);
			beans.setErrMsg("查询附近的人出现异常");
			logger.error(e);
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
	public LocationCurrServiceImpl getCurrService() {
		return currService;
	}

	public void setCurrService(LocationCurrServiceImpl currService) {
		this.currService = currService;
	}

	public LocationHisServiceImpl getHisService() {
		return hisService;
	}

	public void setHisService(LocationHisServiceImpl hisService) {
		this.hisService = hisService;
	}

	public LocationCurr getCurr() {
		return curr;
	}

	public void setCurr(LocationCurr curr) {
		this.curr = curr;
	}
}
