package com.menu.web.formBean;

public class MenuTypeForm extends BaseForm{
	
	private int id;
	private String menuType_No;//��Ʒ������
	private String menuType_Name;//��Ʒ��������
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMenuType_No() {
		return menuType_No;
	}
	public void setMenuType_No(String menuType_No) {
		this.menuType_No = menuType_No;
	}
	public String getMenuType_Name() {
		return menuType_Name;
	}
	public void setMenuType_Name(String menuType_Name) {
		this.menuType_Name = menuType_Name;
	}
	
}
