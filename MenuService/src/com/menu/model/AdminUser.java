package com.menu.model;

public class AdminUser {

	private int id;
	private String username;//�û���
	private String password;//�û�����
	private int adminRole;//����Ա��ɫ��0����������Ա��1������ͨ����Ա
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getAdminRole() {
		return adminRole;
	}
	public void setAdminRole(int adminRole) {
		this.adminRole = adminRole;
	}
	
}
