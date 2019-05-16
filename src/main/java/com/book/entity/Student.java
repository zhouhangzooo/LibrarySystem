package com.book.entity;

/**
 * 数据模型(与数据库中表映射)
 * 
 * @author zhouhang
 * @date 2019/05/14
 */
public class Student {

	private String id;
	private String name;
	private String password;
	private int age;
	private String profession;
	private String grade;
	private String sex;
	private int integrity;
	private int cumulative;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public int getIntegrity() {
		return integrity;
	}

	public void setIntegrity(int integrity) {
		this.integrity = integrity;
	}

	public int getCumulative() {
		return cumulative;
	}

	public void setCumulative(int cumulative) {
		this.cumulative = cumulative;
	}

}
