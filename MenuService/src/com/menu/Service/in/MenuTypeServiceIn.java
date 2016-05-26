package com.menu.Service.in;

import com.menu.model.MenuType;

public interface MenuTypeServiceIn extends BaseDaoIn<MenuType>{

	/**
	 * ͨ�����׵����ͱ�ţ���������MenuType����(��Ӳ�ʱ�õ�)
	 */
	public MenuType findMenuType(String menuType_No);
	
	/**
	 * ����Ӳ��׵�����ʱ���Զ����ɲ������ͱ��
	 * @return
	 */
	public String getMenuType_No();
	
	/**
	 * ��Ӳ���������ʱ�����������������Ƿ��Ѿ�����
	 * @return
	 */
	public boolean checkMenuType_NameExist(String menuType_Name);
}
