package com.menu.Service.in;

import com.menu.model.Tables;

public interface TablesServiceIn extends BaseDaoIn<Tables>{

	
	/**
	 * 当添加桌子时，自动生成桌子编号
	 * @return
	 */
	public String getTables_No();
	
	/**
	 * 当添加桌子时，检查餐桌名称是否存在
	 * @return
	 */
	public boolean checkTable_NameExist(String table_Name);
}
