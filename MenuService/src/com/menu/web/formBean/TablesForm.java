package com.menu.web.formBean;

public class TablesForm extends BaseForm{
	
	private int id;
	private String table_No;//���ӱ��
	private String table_Name;//��������
	private int table_Volum;//������������
	private int table_Status;//����״̬��0������У�1����Ԥ����2����ռ��
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTable_No() {
		return table_No;
	}
	public void setTable_No(String table_No) {
		this.table_No = table_No;
	}
	public String getTable_Name() {
		return table_Name;
	}
	public void setTable_Name(String table_Name) {
		this.table_Name = table_Name;
	}
	public int getTable_Volum() {
		return table_Volum;
	}
	public void setTable_Volum(int table_Volum) {
		this.table_Volum = table_Volum;
	}
	public int getTable_Status() {
		return table_Status;
	}
	public void setTable_Status(int table_Status) {
		this.table_Status = table_Status;
	}
	
}
