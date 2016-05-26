package com.menu.model;

import java.util.List;

/**
 * 查询结果bean
 * @author kkwp@163.com
 *
 */
public class QueryResult<T> {
	/* 查询返回的记录集 */
	private List<T> resultList;
	/* 总记录数 */
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
