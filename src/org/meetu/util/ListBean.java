package org.meetu.util;

import java.util.List;

import org.meetu.model.BaseModel;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * bean list转xml中间类
 * @param <T>
 * */
//@XStreamAlias("beans")
public class ListBean<T> extends BaseModel {

	private String name;
	private List<T> list;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}


}