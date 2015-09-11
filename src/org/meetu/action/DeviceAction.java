package org.meetu.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.meetu.dto.BaseDto;
import org.meetu.model.DeviceInfo;
import org.meetu.model.User;
import org.meetu.service.IDeviceInfoService;
import org.meetu.service.IUserService;
import org.meetu.util.BeanConverter;
import org.springframework.beans.factory.annotation.Autowired;

import static org.meetu.constant.Constant.*;

/**
 * 设备信息Action
 * */
public class DeviceAction {

	Log logger = LogFactory.getLog(DeviceAction.class);

	@Autowired
	private IDeviceInfoService service;
	
	@Autowired
	private IUserService uService;

	HttpServletRequest req;
	HttpServletResponse resp;

	PrintWriter out = null;
	/**
	 * 反馈信息对象STRUTS2注入
	 * */
	private DeviceInfo device;

	/**
	 * 返给客户端xml
	 * */
	private String xml = "";

	/**
	 * 上传设备信息(saveOrUpload)<br>
	 * 1.将设备信息入库<br>
	 * 2.将user表中imei字段改为当前设备IMEI
	 * */
	public String uploadDeviceInfo() {
		req = ServletActionContext.getRequest();
		resp = ServletActionContext.getResponse();
		
		BaseDto dto = new BaseDto();
		try {
			out = resp.getWriter();
			//
			service.saveOrUpdate(device);
			User u = new User();
			u.setId(device.getUserId());
			u = uService.queryById(u);//从db查出的对象,再修改imei属性,这样dynamic-update才会起效,但是为什么不起效....还是所有字段都会set一遍
			u.setImei(device.getImei());
			//更新user表中的IMEI字段
			uService.update(u);
		} catch (Exception e) {
			logger.error(e);
			logger.error("更新信息失败为",e);
			dto.setErrCode(STATUS_FAIL);
			dto.setErrMsg("更新信息失败");
		} finally {
			xml = BeanConverter.bean2xml(dto);
			out.write(xml);
			out.close();
		}
		return null;
	}

	/**
	 * getters and setters
	 * */
	public DeviceInfo getDevice() {
		return device;
	}

	public void setDevice(DeviceInfo device) {
		this.device = device;
	}

}
