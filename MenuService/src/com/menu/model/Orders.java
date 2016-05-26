package com.menu.model;

public class Orders {

	private int id;
	private String order_No;//订单编号
	private String order_Time;//订单创建时间
	private int order_Status;//订单状态
	private float order_Total;//订单金额
	private float order_Paytotal;//订单实际付的金额
	private String waiter_No;//服务员编号
	private String table_No;//餐桌编号
	private int personNum;//订单用餐人数
	
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
	public String getOrder_Time() {
		return order_Time;
	}
	public void setOrder_Time(String order_Time) {
		this.order_Time = order_Time;
	}
	public int getOrder_Status() {
		return order_Status;
	}
	public void setOrder_Status(int order_Status) {
		this.order_Status = order_Status;
	}
	public float getOrder_Total() {
		return order_Total;
	}
	public void setOrder_Total(float order_Total) {
		this.order_Total = order_Total;
	}
	public float getOrder_Paytotal() {
		return order_Paytotal;
	}
	public void setOrder_Paytotal(float order_Paytotal) {
		this.order_Paytotal = order_Paytotal;
	}
	public String getWaiter_No() {
		return waiter_No;
	}
	public void setWaiter_No(String waiter_No) {
		this.waiter_No = waiter_No;
	}
	public String getTable_No() {
		return table_No;
	}
	public void setTable_No(String table_No) {
		this.table_No = table_No;
	}
	public int getPersonNum() {
		return personNum;
	}
	public void setPersonNum(int personNum) {
		this.personNum = personNum;
	}
	
}
