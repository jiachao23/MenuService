package com.menu.Service.in;

import java.util.List;

import com.menu.model.Orders;
import com.menu.model.OrderItem;
import com.menu.web.formBean.OrderItemForm;

public interface OrderItemServiceIn extends BaseDaoIn<OrderItem>{
	
	//����order_No�õ���Ӧ������OrderItem
	public List<OrderItemForm> getOrderItemForms(Orders order);
	
	
}
