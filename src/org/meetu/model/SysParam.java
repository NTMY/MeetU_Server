package org.meetu.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 系统参数表
 * */
@Entity
@Table(name = "sys_param", catalog = "meetu")
public class SysParam extends BaseModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 101494575474083819L;

	@Id
	@Column(name="_key")
	private String key;

	@Column(name="_value")
	private String value;
	
	@Column(name="description")
	private String desc;

	
	public SysParam() {
		
	}
	
	
	public SysParam(String key, String value, String desc) {
		super();
		this.key = key;
		this.value = value;
		this.desc = desc;
	}

	
	

	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * @param key the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * @return the desc
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * @param desc the desc to set
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	
	
}
