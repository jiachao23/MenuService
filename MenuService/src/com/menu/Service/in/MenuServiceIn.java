package com.menu.Service.in;

import com.menu.model.Menu;

public interface MenuServiceIn extends BaseDaoIn<Menu>{

	/**
	 * ��Ӳ���ʱ���������Ƿ��Ѿ�����
	 * @return
	 */
	public boolean checkExist(String menu_Name);
	
	
	/**
	 * ����Ӳ�ʱ���ݲ˵��������ɲ˵ı��
	 * @return
	 */
	public String getMenu_NoByMenuType_No(String menuType_No);
	
	
	/** 
	 * xu add at 2014-8-9
	 * ����Ӳ���Ż�ȡ����
	 * @return
	 */
	public String getMenuNameById(String MenuId);
}
