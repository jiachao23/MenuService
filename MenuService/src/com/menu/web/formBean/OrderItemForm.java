package com.menu.web.formBean;

public class OrderItemForm {

	private int id;				//序号
	private String order_No;	//订单号
	private String menu_No;		//菜品编号
	private String menu_Num;	//菜品数量
	private String remark;		//口味
	private String menu_Name;	//菜品名称
	private float menu_price;	//菜品价格
	private String discount;
	//	
	private String orderItem_Id;//订单项编号
	//
	public String getOrderItem_Id() {
		return orderItem_Id;
	}
	public void setOrderItem_Id(String orderItem_Id) {
		this.orderItem_Id = orderItem_Id;
	}
	
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
	public String getMenu_Num() {
		return menu_Num;
	}
	public void setMenu_Num(String menu_Num) {
		this.menu_Num = menu_Num;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getMenu_Name() {
		return menu_Name;
	}
	public void setMenu_Name(String menu_Name) {
		this.menu_Name = menu_Name;
	}
	public float getMenu_price() {
		return menu_price;
	}
	public void setMenu_price(float menu_price) {
		this.menu_price = menu_price;
	}
	public String getDiscount() {
		return discount;
	}
	public void setDiscount(String discount) {
		this.discount = discount;
	}
}
