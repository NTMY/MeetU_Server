package org.meetu.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.meetu.dto.BaseDto;
import org.meetu.model.DeviceInfo;
import org.meetu.service.IDeviceInfoService;
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
	 * 上传设备信息(saveOrUpload)
	 * */
	public String uploadDeviceInfo() {
		BaseDto dto = new BaseDto();
		try {
			out = resp.getWriter();
			service.saveOrUpdate(device);
		} catch (Exception e) {
			logger.error(e);
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
