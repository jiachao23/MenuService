package com.menu.Service.in;

import com.menu.model.Waiter;

public interface WaiterServiceIn extends BaseDaoIn<Waiter>{

	/**
	 * ����ӷ���Աʱ���Զ����ɷ���Ա���
	 * @return
	 */
	public String getWaiter_No() ;
	
	/**
	 * ������Ա�ĺϷ���
	 * @param waiter
	 * @return
	 */
	public boolean checkWaiter(Waiter waiter);
}
