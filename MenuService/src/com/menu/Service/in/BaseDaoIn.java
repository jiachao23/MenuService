package com.menu.Service.in;


import com.menu.model.QueryResult;



/**
 * ʵ�����ͨ�ýӿ�
 * @author kkwp@163.com
 *
 * @param <T>  ʵ������
 */
public interface BaseDaoIn<T> {
	
	
	/**
	 * ����ʵ��
	 * @param entity ʵ�����
	 */
	public int save(T entity);  //T Ϊ����  
	
	/**
	 * ����ʵ��
	 * @param entity ʵ�����
	 */
	public int update(T entity);
	/**
	 * ɾ��ʵ��
	 * @param entityid ʵ��ID
	 */
	public int delete (String entityid);//JPA�涨ʵ���id���Ա���ʵ�����л��ӿ�
	/**
	 * ����ʵ��
	 * @param entityid ʵ��ID
	 * @return
	 */
	public T find(String entityid);
	
	/**
	 * ���������õ���¼��
	 * @return �ܼ�¼��
	 */
	//public long getCount(String sqlWhere);  //�õ�����ܼ�¼����
	
	/**
	 * ����������ѯ
	 * @return ����list
	 */
	public QueryResult<T> select(String sql);
}
