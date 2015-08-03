package org.meetu.bean2xml.jaxb;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 1、需要转换的model对象一定要添加@XmlRootElement注解，其里面的其他对象则不需要
 * 2、需要转换的model对象一定要有不带参数的构造方法，包括该对象里面引用的对象。
 * */
@XmlRootElement
public class Student {
	private int id;
	private String name;
	private int age;
	private Classroom classroom;

	/**
	 * constructor
	 * */
	public Student() {
		super();
	}

	/**
	 * constructor
	 * */
	public Student(int id, String name, int age, Classroom classroom) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.classroom = classroom;
	}

	/**
	 * getters and setters
	 * */
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Classroom getClassroom() {
		return classroom;
	}

	public void setClassroom(Classroom classroom) {
		this.classroom = classroom;
	}

}