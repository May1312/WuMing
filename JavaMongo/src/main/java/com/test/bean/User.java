package com.test.bean;

import org.mongodb.morphia.annotations.Entity;

import java.io.Serializable;

//@Document(collection="User")
@Entity(noClassnameStored = true)//mongodb field className not save
public class User implements Serializable {

	private static final long serialVersionUID = 8028989597226068116L;

	private String userId;

	private String name;

	private String password;

	private int age;

	private String sex;

	private String registTime;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getRegistTime() {
		return registTime;
	}

	public void setRegistTime(String registTime) {
		this.registTime = registTime;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User{" +
				"userId='" + userId + '\'' +
				", name='" + name + '\'' +
				", password='" + password + '\'' +
				", age=" + age +
				", sex='" + sex + '\'' +
				", registTime='" + registTime + '\'' +
				'}';
	}
}
