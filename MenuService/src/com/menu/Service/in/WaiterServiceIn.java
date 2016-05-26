package com.menu.Service.in;

import com.menu.model.Waiter;

public interface WaiterServiceIn extends BaseDaoIn<Waiter>{

	/**
	 * 当添加服务员时，自动生成服务员编号
	 * @return
	 */
	public String getWaiter_No() ;
	
	/**
	 * 检查服务员的合法性
	 * @param waiter
	 * @return
	 */
	public boolean checkWaiter(Waiter waiter);
}
