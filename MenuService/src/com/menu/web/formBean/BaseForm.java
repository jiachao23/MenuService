package com.menu.web.formBean;

public class BaseForm {
	/* ����ҳ�� */
	private int pageIndex;
	/* ÿҳ�������� */
	private int pageSize;
	/* �����Ƿ���в��� */
	private boolean query;
	
	private String idList;
	
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public boolean isQuery() {
		return query;
	}
	public void setQuery(boolean query) {
		this.query = query;
	}
	public String getIdList() {
		return idList;
	}
	public void setIdList(String idList) {
		this.idList = idList;
	}

}
