package com.menu.Service.in;

import com.menu.model.Orders;

public interface OrderServiceIn extends BaseDaoIn<Orders>{
	
	//根据订单中的桌子号更新桌子的状态
	public void setTableStatus(Orders orders);
	
	//根据订单号查询该订单是否存在
	public boolean getOrderExist(Orders orders);
	
	public Orders getOrdersById(String id);
}
