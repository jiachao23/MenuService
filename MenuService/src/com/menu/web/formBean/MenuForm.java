package com.menu.web.formBean;

public class MenuForm extends BaseForm{

	private int id;
	private String menu_No;//菜的编号
	private String menu_Name;//菜名
	private float menu_Price;//菜的价格
	private String menu_Pic;//菜的图片
	private String menuType_Name;//菜的种类
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMenu_No() {
		return menu_No;
	}
	public void setMenu_No(String menu_No) {
		this.menu_No = menu_No;
	}
	public String getMenu_Name() {
		return menu_Name;
	}
	public void setMenu_Name(String menu_Name) {
		this.menu_Name = menu_Name;
	}
	public float getMenu_Price() {
		return menu_Price;
	}
	public void setMenu_Price(float menu_Price) {
		this.menu_Price = menu_Price;
	}
	public String getMenu_Pic() {
		return menu_Pic;
	}
	public void setMenu_Pic(String menu_Pic) {
		this.menu_Pic = menu_Pic;
	}
	public String getMenuType_Name() {
		return menuType_Name;
	}
	public void setMenuType_Name(String menuType_Name) {
		this.menuType_Name = menuType_Name;
	}
	
}
