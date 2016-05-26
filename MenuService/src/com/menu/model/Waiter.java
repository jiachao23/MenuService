package com.menu.model;

public class Waiter {

	private int id;
	private String waiter_No;//服务员编号
	private String waiter_Name;//服务员姓名
	private int waiter_Sex;//服务员性别,0男性，1女性
	private int waiter_Age;//服务员年龄
	private String waiter_Telphone;//服务员手机
	private String waiter_Password;//服务员登录密码
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getWaiter_No() {
		return waiter_No;
	}
	public void setWaiter_No(String waiter_No) {
		this.waiter_No = waiter_No;
	}
	public String getWaiter_Name() {
		return waiter_Name;
	}
	public void setWaiter_Name(String waiter_Name) {
		this.waiter_Name = waiter_Name;
	}
	
	public int getWaiter_Sex() {
		return waiter_Sex;
	}
	public void setWaiter_Sex(int waiter_Sex) {
		this.waiter_Sex = waiter_Sex;
	}
	public int getWaiter_Age() {
		return waiter_Age;
	}
	public void setWaiter_Age(int waiter_Age) {
		this.waiter_Age = waiter_Age;
	}
	public String getWaiter_Telphone() {
		return waiter_Telphone;
	}
	public void setWaiter_Telphone(String waiter_Telphone) {
		this.waiter_Telphone = waiter_Telphone;
	}
	public String getWaiter_Password() {
		return waiter_Password;
	}
	public void setWaiter_Password(String waiter_Password) {
		this.waiter_Password = waiter_Password;
	}
	
}
