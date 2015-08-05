package org.meetu.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;


/**
 * 设备信息
 * */
@Entity
@Table(name = "U_DEVICE", catalog = "MEETU")
public class DeviceInfo {

	/**操作系统名称*/
	@Column(name = "osName", nullable = false)
	private String osName;
	
	/**操作系统版本*/
	@Column(name = "osVer", nullable = false)
	private String osVer;
	
	/**设备厂商*/
	@Column(name = "deviceCompany")
	private String deviceCompany;
	
	/**设备IMEI*/
	@Id
	private String imei;
	
	/**
	 * 设备添加时间<br>
	 * DDL设置为INSERT时插入当前时间 update不更新
	 * */
	@Column(name = "addTime", nullable = false)
//	@Temporal(TemporalType.TIMESTAMP)
//	@Transient//不持久化    
	private Date addTime;
	
	/**
	 * 最后登录时间
	 * */
	@Column(name = "lastTime", nullable = false)
	private Date lastTime;
	
	/**
	 * 构造方法Constructor
	 * */
	public DeviceInfo() {}

	
	
	/********************************************************************
	 * 
	 * getters and setters
	 * 
	 ********************************************************************/
	/**
	 * @return the osName
	 */
	public String getOsName() {
		return osName;
	}

	/**
	 * @param osName the osName to set
	 */
	public void setOsName(String osName) {
		this.osName = osName;
	}

	/**
	 * @return the osVer
	 */
	public String getOsVer() {
		return osVer;
	}

	/**
	 * @param osVer the osVer to set
	 */
	public void setOsVer(String osVer) {
		this.osVer = osVer;
	}

	/**
	 * @return the deviceCompany
	 */
	public String getDeviceCompany() {
		return deviceCompany;
	}

	/**
	 * @param deviceCompany the deviceCompany to set
	 */
	public void setDeviceCompany(String deviceCompany) {
		this.deviceCompany = deviceCompany;
	}

	/**
	 * @return the imei
	 */
	public String getImei() {
		return imei;
	}

	/**
	 * @param imei the imei to set
	 */
	public void setImei(String imei) {
		this.imei = imei;
	}



	/**
	 * @return the addTime
	 */
	public Date getAddTime() {
		return addTime;
	}



	/**
	 * @param addTime the addTime to set
	 */
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}



	/**
	 * @return the lastTime
	 */
	public Date getLastTime() {
		return lastTime;
	}



	/**
	 * @param lastTime the lastTime to set
	 */
	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}
	
	
	
	
	
}
