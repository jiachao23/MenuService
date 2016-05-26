package com.menu.Service.in;

import com.menu.model.MenuType;

public interface MenuTypeServiceIn extends BaseDaoIn<MenuType>{

	/**
	 * 通过菜谱的类型编号，返回整个MenuType对象(添加菜时用到)
	 */
	public MenuType findMenuType(String menuType_No);
	
	/**
	 * 当添加菜谱的类型时，自动生成菜谱类型编号
	 * @return
	 */
	public String getMenuType_No();
	
	/**
	 * 添加菜谱类型名时，检测菜谱类型名称是否已经存在
	 * @return
	 */
	public boolean checkMenuType_NameExist(String menuType_Name);
}
