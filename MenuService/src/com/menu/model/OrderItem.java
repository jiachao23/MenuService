package com.menu.model;

public class OrderItem {

	private int id;
	private String order_No;//对应的订单编号
	private String menu_No;//菜谱编号
	private int menu_Num;//份数
	private String remark;//备注
	private String discount;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOrder_No() {
		return order_No;
	}
	public void setOrder_No(String order_No) {
		this.order_No = order_No;
	}
	public String getMenu_No() {
		return menu_No;
	}
	public void setMenu_No(String menu_No) {
		this.menu_No = menu_No;
	}
	public int getMenu_Num() {
		return menu_Num;
	}
	public void setMenu_Num(int menu_Num) {
		this.menu_Num = menu_Num;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getDiscount() {
		return discount;
	}
	public void setDiscount(String discount) {
		this.discount = discount;
	}
}
