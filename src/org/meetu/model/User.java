package org.meetu.model;

import com.thoughtworks.xstream.annotations.XStreamOmitField;

/**
 * 
drop table if exists u_user;
create table u_user(
	id int(16) auto_increment primary key comment '用户主键',
	mobile varchar(16) comment '手机号',
	pwd varchar(64) comment '密码',
	imei varchar(64) comment '手机imei',
	name varchar(64) comment '真实姓名',
	nickname varchar(64) comment '昵称',
	birthdate varchar(64) comment '生日',
	gender varchar(4) comment '性别 男/女',
	qq varchar(32) comment 'qq',
	email varchar(64) comment 'email',
	company varchar(128) comment '公司名称',
	company_addr varchar(256) comment '公司地址',
	home_addr varchar(256) comment '家庭地址',
	regtime varchar(64) comment '注册时间',
	mood varchar(512) comment '心情签名',
	status varchar(3) not null comment '状态 0:正常 1:冻结'
) comment = '用户表';

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

	/**关联*/
	/** 当前地址 */
	private LocationCurr locCurr;
	
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

	public LocationCurr getLocCurr() {
		return locCurr;
	}

	public void setLocCurr(LocationCurr locCurr) {
		this.locCurr = locCurr;
	}
	
	public String getMood() {
		return mood;
	}

	public void setMood(String mood) {
		this.mood = mood;
	}
	
}
