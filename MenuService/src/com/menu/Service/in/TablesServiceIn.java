package com.menu.Service.in;

import com.menu.model.Tables;

public interface TablesServiceIn extends BaseDaoIn<Tables>{

	
	/**
	 * ���������ʱ���Զ��������ӱ��
	 * @return
	 */
	public String getTables_No();
	
	/**
	 * ���������ʱ�������������Ƿ����
	 * @return
	 */
	public boolean checkTable_NameExist(String table_Name);
}
