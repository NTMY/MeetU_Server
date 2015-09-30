package org.meetu.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.meetu.constant.Constant;
import org.meetu.dto.BaseDto;
import org.meetu.model.User;
import org.meetu.service.IUserService;
import org.meetu.util.BeanConverter;
import org.meetu.util.CheckUtil;
import org.meetu.util.ImgGen;
import org.meetu.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 文件上传Action
 * */
public class FileUploadAction extends ActionSupport {

	private Log logger = LogFactory.getLog(FileUploadAction.class);
	// inject by struts.xml
//	private String savePath;

	@Autowired
	private IUserService userService;
	
	private java.io.File file;
	private String fileContentType;
	
	
	private String fileFileName;
	
	// write to client[xml]
	private String xml;

	HttpServletRequest request = null;
	HttpServletResponse response = null;
	
	// upload files which is allowed
	private String allowTypes;
	
	// the max upload file size
	private int maxSize;
	
	PrintWriter out = null;
	
	
	
	/**
	 * upload file method
	 * */
	public String upload() throws Exception {
		InputStream is = null;
		OutputStream os = null;
		request = ServletActionContext.getRequest();
		response = ServletActionContext.getResponse();
		
		//返回
		BaseDto dto = new BaseDto();
		//上传userId
		String userId = request.getParameter("userId");
		//上传分辨率 是否高清
		String resolution = request.getParameter("resolution");//分辨率.高清传HD

		logger.info("--------------------------文件上传开始-----------------------------");
		logger.info("userId =  " + userId);
		logger.info("resolution =  " + resolution);
		logger.info("fileName = " + this.getFileFileName());
		//可以上传
		if (filterUploadFile() == true) {
			try {
				out = response.getWriter();
				is = new FileInputStream(file);
				System.out.println("fileContentType= " + fileContentType);
				String filePath = ImgGen.genFileDiskPath(resolution);
				String extName = getFileFileName().split("\\.")[getFileFileName().split("\\.").length-1];
				String fileName = ImgGen.genFileName(userId, resolution , extName);
				File toFile = new File(filePath, fileName);
				os = new FileOutputStream(toFile);
				byte[] buffer = new byte[1024];
				int length = 0;
				while ((length = is.read(buffer)) > 0) {
					os.write(buffer, 0, length);
				}
				User u = new User();
				u.setId(Integer.valueOf(userId));
				u = userService.queryById(u);
				if(resolution.equalsIgnoreCase("HD")) {
					u.setImgUrlHD("fileDownloadAction!download?userId="+userId+"&resolution=HD&fileName="+ImgGen.genFileName(userId, resolution, extName));
				} else {
					u.setImgUrl("fileDownloadAction!download?userId="+userId+"&resolution=&fileName="+ImgGen.genFileName(userId, resolution, extName));
				}
				userService.update(u);
			} catch (Exception e) {
				logger.error("文件上传过程异常",e);
				dto = new BaseDto(Constant.STATUS_FAIL , "文件上传过程异常"+e.getClass());
				throw (e);
			} finally {
				xml = BeanConverter.bean2xml(dto);
				out.write(xml);
				out.close();
				os.close();
				is.close();
				logger.warn("文件上传返回的XML是");
				logger.warn(xml);
				logger.info("--------------------------文件上传结束-----------------------------");
			}
		} 
		//如果不允许上传 
		else {
			dto = new BaseDto(Constant.STATUS_FAIL, "格式错误或文件太大");
			xml = BeanConverter.bean2xml(dto);
			out.write(xml);
			out.close();
			logger.warn("文件上传返回的XML是");
			logger.warn(xml);
			logger.info("--------------------------文件上传结束-----------------------------");
		}
		return null;

	}

	/**
	 * return true : allow to upload<br>
	 * return false : forbid to upload
	 * */
	public Boolean filterUploadFile() {
//		if (allowTypes.contains(fileContentType) && file.length() < maxSize) {
//			return true;
//		} else {
//			return false;
//		}
		return true;
	}


	
	/**
	 * getters and setters
	 * */

	public java.io.File getFile() {
		return file;
	}

	public void setFile(java.io.File file) {
		this.file = file;
	}

	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}

	/*
	public String getSavePath() {
		return savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}
	*/
	
	public String getAllowTypes() {
		return allowTypes;
	}

	public void setAllowTypes(String allowTypes) {
		this.allowTypes = allowTypes;
	}

	public int getMaxSize() {
		return maxSize;
	}

	public void setMaxSize(int maxSize) {
		this.maxSize = maxSize;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

}
