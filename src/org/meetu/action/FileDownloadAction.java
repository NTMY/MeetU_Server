package org.meetu.action;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.meetu.util.ImgGen;

import com.opensymphony.xwork2.ActionSupport;

public class FileDownloadAction extends ActionSupport {
	private static final long serialVersionUID = 9217325298735916549L;

	private static Log logger = LogFactory.getLog(FileDownloadAction.class);

	private HttpServletRequest req;

	private HttpServletResponse resp;

	private PrintWriter out = null;

	/**
	 * from request
	 * */
	private String fileName;
	
	/**普通头像文件路径*/
	private String filePath;
	
	/**高清大图文件路径*/
	private String filePathHD;
	
	
	public String download()
	{
		return SUCCESS;
	}
	
	/**
	 * @throws FileNotFoundException 
	 * 
	 * */
	public InputStream getImg() throws FileNotFoundException,IOException {
		req = ServletActionContext.getRequest();
		resp = ServletActionContext.getResponse();

		String userId = req.getParameter("userId");// userId
		String resolution = req.getParameter("resolution");// 清晰度 高清传HD

		//filePath = ImgGen.genFileDiskPath(resolution);
//		fileName = ImgGen.genFileName(userId, resolution, "jpg");

		InputStream is = null;
		try {
			if(resolution != null && resolution.equalsIgnoreCase("HD")) {
				is = new FileInputStream(filePathHD + fileName);
			} else {
				is = new FileInputStream(filePath + fileName);
			}
			return is;
		} catch (FileNotFoundException e) {
			logger.error("文件下载出现异常FileNotFoundException", e);
			throw(e);
		} catch (IOException e) {
			logger.error("文件下载出现异常IOException", e);
			throw(e);
		} finally {
			logger.warn("文件下载fianlly");
		}
	}



	/**
	 * getters and setters
	 * */
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	/**
	 * @return the filePathHD
	 */
	public String getFilePathHD() {
		return filePathHD;
	}

	/**
	 * @param filePathHD the filePathHD to set
	 */
	public void setFilePathHD(String filePathHD) {
		this.filePathHD = filePathHD;
	}
	
}