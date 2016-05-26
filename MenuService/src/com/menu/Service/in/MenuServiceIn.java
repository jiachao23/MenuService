package com.menu.Service.in;

import com.menu.model.Menu;

public interface MenuServiceIn extends BaseDaoIn<Menu>{

	/**
	 * 添加菜名时，检测菜名是否已经存在
	 * @return
	 */
	public boolean checkExist(String menu_Name);
	
	
	/**
	 * 当添加菜时根据菜的种类生成菜的编号
	 * @return
	 */
	public String getMenu_NoByMenuType_No(String menuType_No);
	
	
	/** 
	 * xu add at 2014-8-9
	 * 当添加菜序号获取菜名
	 * @return
	 */
	public String getMenuNameById(String MenuId);
}
