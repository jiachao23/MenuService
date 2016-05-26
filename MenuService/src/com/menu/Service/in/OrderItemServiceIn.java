package com.menu.Service.in;

import java.util.List;

import com.menu.model.Orders;
import com.menu.model.OrderItem;
import com.menu.web.formBean.OrderItemForm;

public interface OrderItemServiceIn extends BaseDaoIn<OrderItem>{
	
	//根据order_No得到对应的所有OrderItem
	public List<OrderItemForm> getOrderItemForms(Orders order);
	
	
}
