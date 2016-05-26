package com.menu.Service.in;


import com.menu.model.QueryResult;



/**
 * 实体操作通用接口
 * @author kkwp@163.com
 *
 * @param <T>  实体类型
 */
public interface BaseDaoIn<T> {
	
	
	/**
	 * 保存实体
	 * @param entity 实体对象
	 */
	public int save(T entity);  //T 为泛型  
	
	/**
	 * 更新实体
	 * @param entity 实体对象
	 */
	public int update(T entity);
	/**
	 * 删除实体
	 * @param entityid 实体ID
	 */
	public int delete (String entityid);//JPA规定实体的id属性必须实现序列化接口
	/**
	 * 查找实体
	 * @param entityid 实体ID
	 * @return
	 */
	public T find(String entityid);
	
	/**
	 * 根据条件得到记录数
	 * @return 总记录数
	 */
	//public long getCount(String sqlWhere);  //得到表的总记录条数
	
	/**
	 * 根据条件查询
	 * @return 对象list
	 */
	public QueryResult<T> select(String sql);
}
