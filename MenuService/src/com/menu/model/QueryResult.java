package com.menu.model;

import java.util.List;

/**
 * ��ѯ���bean
 * @author kkwp@163.com
 *
 */
public class QueryResult<T> {
	/* ��ѯ���صļ�¼�� */
	private List<T> resultList;
	/* �ܼ�¼�� */
	private long recordCount;
	public List<T> getResultList() {
		return resultList;
	}
	public void setResultList(List<T> resultList) {
		this.resultList = resultList;
	}
	public long getRecordCount() {
		return recordCount;
	}
	public void setRecordCount(long recordCount) {
		this.recordCount = recordCount;
	}
	

	
	
	
}
