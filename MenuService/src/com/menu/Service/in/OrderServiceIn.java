package com.menu.Service.in;

import com.menu.model.Orders;

public interface OrderServiceIn extends BaseDaoIn<Orders>{
	
	//���ݶ����е����ӺŸ������ӵ�״̬
	public void setTableStatus(Orders orders);
	
	//���ݶ����Ų�ѯ�ö����Ƿ����
	public boolean getOrderExist(Orders orders);
	
	public Orders getOrdersById(String id);
}
