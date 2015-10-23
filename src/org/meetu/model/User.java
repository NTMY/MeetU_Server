package org.meetu.model;

import java.util.List;

import javax.persistence.JoinColumn;

import org.meetu.constant.Constant;

import com.thoughtworks.xstream.annotations.XStreamOmitField;

/**
 * 
 * 
 * */

//@XStreamAlias("user")
public class User extends BaseModel {
	/** pk */
	private Integer id;
	
	/** 手机号 */
	private String mobile;

	/**密码*/
	@XStreamOmitField
	private String pwd;
	
	/** 手机串号 */
	@JoinColumn(name = "imei", referencedColumnName = "imei")
	private String imei;
	
	/** 用户姓名 */
	private String name;
	
	/** 昵称 */
	private String nickname;
	
	/** 生日 */
	private String birthdate;
	
	/** 性别 男/女 */
	private String gender;
	
	/** qq号 */
	private String qq;
	
	/** 微信号 */
	private String wechat;
	
	/** email */
	private String email;
	
	/** 公司名称 */
	private String company;
	
	/** 公司地址 */
	private String company_addr;
	
	/** 家庭住址 */
	private String home_addr;
	
	/** 注册时间 */
	private String regtime;
	
	/**心情签名*/
	private String mood;
	
	/** 状态 */
	private String status;

	/**
	 * 非高清头像
	 * */
	private String imgUrl;
	
	/**
	 * 高清头像
	 * */
	private String imgUrlHD;
	
	/**
	 * 关联<br>
	 * 虽然数据上的关系是一对多,但是只读取那个当前使用的1v1的关系就够了
	 * */
	private LocationCurr curr;
	
	private List<LocationHis> hisList;
	
	/** 百度推送信息 */
//	private PushInfoBaidu pushInfo;
	
	/**
	 * constructor
	 * */
	public User() {
		
	}
	

	
	/**
	 * 融合User对象,将上传的用户信息merge到db中的用户信息后,执行update
	 * @param user 上传来的user对象
	 * @return 融合之后的userDB对象
	 * */
	public void merge(User user) {
		//手机号
		if (user.getMobile() != null && !user.getMobile().equals("")) {
			this.mobile = user.getMobile();
		}
		//姓名
		if (user.getName() != null && !user.getName().equals("")) {
			this.name = user.getName();
		}
		//昵称nickname
		if (user.getNickname() != null && !user.getNickname().equals("")) {
			this.nickname = user.getNickname();
		}
		//IMEI
		if (user.getImei() != null && !user.getImei().equals("")) {
			this.imei = user.getImei();
		}
		//密码
		if (user.getPwd() != null && !user.getPwd().equals("")) {
			this.pwd = user.getPwd();
		}
		//生日
		if (user.getBirthdate() != null && !user.getBirthdate().equals("")) {
			this.birthdate = user.getBirthdate();
		}
		//性别
		if (user.getGender() != null && !user.getGender().equals("")) {
			this.gender = user.getGender();
		}
		//QQ
		if (user.getQq() != null && !user.getQq().equals("")) {
			this.qq = user.getQq();
		}
		//Email
		if (user.getEmail() != null && !user.getEmail().equals("")) {
			this.email = user.getEmail();
		}
		//mood
		if (user.getMood() != null && !user.getMood().equals("")) {
			this.mood = user.getMood();
		}
		//wechat
		if (user.getWechat() != null && !user.getWechat().equals("")) {
			this.wechat = user.getWechat();
		}
	}
	
	@Override
	public String toString(){
		return "id="+this.getId();
	}
	
	@Override
	public boolean equals(Object obj){
		if(obj instanceof User) {
			if(this.id == ((User) obj).getId()) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return this.imei.hashCode();
	}
	
	/**************************************************
	 * 
	 * getters and setters
	 * 
	 ***************************************************/
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getCompany_addr() {
		return company_addr;
	}

	public void setCompany_addr(String company_addr) {
		this.company_addr = company_addr;
	}

	public String getHome_addr() {
		return home_addr;
	}

	public void setHome_addr(String home_addr) {
		this.home_addr = home_addr;
	}

	public String getRegtime() {
		return regtime;
	}

	public void setRegtime(String regtime) {
		this.regtime = regtime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	public String getMood() {
		return mood;
	}

	public void setMood(String mood) {
		this.mood = mood;
	}

	/**
	 * @return the wechat
	 */
	public String getWechat() {
		return wechat;
	}

	/**
	 * @param wechat the wechat to set
	 */
	public void setWechat(String wechat) {
		this.wechat = wechat;
	}



	public String getImgUrl() {
		return imgUrl;
	}


	/**
	 * 设置路径
	 * */
	public void setImgUrl(String imgUrl) {
		if(imgUrl == null) {
			this.imgUrl = imgUrl;
		} else {
			if(imgUrl.startsWith(Constant.URL)) {
				imgUrl.replace(Constant.URL, "");
			} else {
				this.imgUrl = imgUrl;
			}
		}
	}



	public String getImgUrlHD() {
		return imgUrlHD;
	}



	public void setImgUrlHD(String imgUrlHD) {
		if(imgUrlHD == null) {
			this.imgUrlHD = imgUrlHD;
		} else {
			if(imgUrlHD.startsWith(Constant.URL)) {
				imgUrlHD.replace(Constant.URL, "");
			} else {
				this.imgUrlHD = imgUrlHD;
			}
		}
	}

	
	/**
	 * 返回完整的imgUrl连接
	 * */
	public String getImgUrlReal() {
		if(imgUrl == null) {
			return null;
		} else {
			if(imgUrl.startsWith(Constant.URL)) {
				return imgUrl;
			} else {
				return Constant.URL + imgUrl;
			}
		}
	}

	
	/**
	 * 返回完整的imgUrlHD连接
	 * */
	public String getImgUrlHDReal() {
		if(imgUrlHD == null) {
			return null;
		} else {
			if(imgUrlHD.startsWith(Constant.URL)) {
				return imgUrlHD;
			} else {
				return Constant.URL + imgUrlHD;
			}
		}
	}



	/**
	 * @return the curr
	 */
	public LocationCurr getCurr() {
		return curr;
	}



	/**
	 * @param curr the curr to set
	 */
	public void setCurr(LocationCurr curr) {
		this.curr = curr;
	}



	/**
	 * @return the hisList
	 */
	public List<LocationHis> getHisList() {
		return hisList;
	}



	/**
	 * @param hisList the hisList to set
	 */
	public void setHisList(List<LocationHis> hisList) {
		this.hisList = hisList;
	}




//	public PushInfoBaidu getPushInfo() {
//		return pushInfo;
//	}
//
//	public void setPushInfo(PushInfoBaidu pushInfo) {
//		this.pushInfo = pushInfo;
//	}
	
}
