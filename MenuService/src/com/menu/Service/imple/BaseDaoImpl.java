package com.menu.Service.imple;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import com.menu.Service.in.BaseDaoIn;
import com.menu.model.Orders;
import com.menu.model.QueryResult;
import com.menu.util.DaoTool;
import com.menu.util.JdbcUtils;

/**
 * 
 * @author Administrator
 *
 * @param <T> T����Ҫ������ʵ��������ʵ��������ȷ��
 */

public abstract class BaseDaoImpl<T> implements BaseDaoIn<T> {

	//�õ�ʵ������ ,  delete �������õ�
	protected Class<T> entityClass = getEntityClass();

	//ͨ���������еķ����õ�ʵ������
	//protected Class<T> entityClass = GenericsUtils.getSuperClassGenricType(getClass());
	

	/**
	 * ͨ�����似���õ�ʵ������ DaoSupport<T> ��T������ �� ����bean���е�����  ����ģ��
	 */
	@SuppressWarnings("unchecked")
	public Class<T> getEntityClass(){
		//�õ�����ʱ����ĸ���
		Type parentType = getClass().getGenericSuperclass();  
		
		//�жϵõ��ĸ����ǲ��Ƿ��͵� ����Ƿ���һ��ʵ����ParameterizedType �ӿ�
		if(parentType instanceof ParameterizedType){  
			ParameterizedType parameterizedType =(ParameterizedType)parentType;
			
			//�õ����������  �磺DaoSupport<MyTest>  Ҳ���� MyTest
			return (Class<T>)parameterizedType.getActualTypeArguments()[0];
		}
		return null;
	}
	
	/**
	 * ���ʵ������  �� MyTestʵ��bean ������ Buyer �� 
	 * @return
	 */
	public String getEntityName(){
		String entityName = this.entityClass.getSimpleName();
		//�õ�ʵ�����Entity ע�� ;�ж�Entityע���name�����Ƿ��޸��ˣ�Ҳ���ǵõ����ݿ����ɵı������

		return entityName;

	}
	
	/**
	 * ����order by ��� �磺order by username asc,email desc�� keyΪ���ԣ�valueΪasc/desc��
	 * @param orderby
	 * @return
	 */
	public static String buildOrderby(LinkedHashMap<String,String> orderby){
		StringBuilder sb = new StringBuilder();
		if(orderby!=null&&!orderby.isEmpty()){
			sb.append(" order by ");
			for(Map.Entry<String, String> entry:orderby.entrySet()){
				sb.append("o.").append(entry.getKey()).append(" ").append(entry.getValue()).append(",");
			}
			sb.deleteCharAt(sb.length()-1);//ɾ�����һ����,��
		}
		return sb.toString();
	}

	public int delete(String entityid) {
		// TODO Auto-generated method stub
		/*Connection conn = JdbcUtils.getPoolConnection();
		PreparedStatement st=null;
		int res=-1;
		String sql= "delete * from "+getEntityName()+" where id in(?)";
		try {
			conn.setAutoCommit(false);
			st = JdbcUtils.prepareStatement(conn, sql,entityid);
			res = JdbcUtils.executeUpdate(st);
			conn.commit();
			conn.setAutoCommit(true);
		} catch (Exception e) {
			res = -1;
			try {
				conn.rollback();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			JdbcUtils.disConnect(conn);
		}	
		return res;*/
		
		Connection conn = JdbcUtils.getPoolConnection();
		PreparedStatement st=null;
		String[] idList = entityid.split("##");
		StringBuffer sBuffer = new StringBuffer();
		for(int i=0;i<idList.length;i++){
			sBuffer.append(idList[i]+",");
		}
		String idListString=sBuffer.substring(0, sBuffer.lastIndexOf(","));
		String sql="delete from " + getEntityName() + " where id in("+idListString+")";
		
		System.out.println(sql);
		int result = 0;
		try {
			st = JdbcUtils.prepareStatement(conn, sql);
			result = JdbcUtils.executeUpdate(st);
			st.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.disConnect(conn);
		}
		return result ;
	}

	public T find(String entityid) {
		// TODO Auto-generated method stub
		String sql= "select * from "+getEntityName()+" where id =?";
		Object object = null;
		try {
			object = entityClass.newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DaoTool.setDetailsToObject(sql, entityid,object);
		
		return (T) object;
	}



	public int save(T entity) {
		// TODO Auto-generated method stub
		/* ����ʱ���ֶ����� ��:(name,age) */
		StringBuilder fieldName = new StringBuilder("(");
		/* ռλ�� �磺(?,?,?) */
		StringBuilder placeholder = new StringBuilder("(");
		
		ArrayList<String> parameterList = new ArrayList<String>();
		
		Field fields[]= entity.getClass().getDeclaredFields();
		for(Field field:fields){
			try {
				field.setAccessible(true);
				if(field.get(entity)!=null){
					if(!"id".equals(field.getName())) {
						fieldName.append(field.getName()).append(",");
						placeholder.append("?,");
						parameterList.add(field.get(entity).toString());
					}
				}
				//System.out.println("BaseDaoImpl-sace"+field.getName()+"--"+field.get(entity));
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		fieldName.deleteCharAt(fieldName.length()-1);
		placeholder.deleteCharAt(placeholder.length()-1);
		fieldName.append(")");
		placeholder.append(")");
		
		//System.out.println("BaseDaoImpl-save����ֶΣ�"+fieldName.toString());
		//System.out.println("BaseDaoImpl-saveռλ����"+placeholder.toString());
		
		
		int res = -1;
		Connection conn = JdbcUtils.getPoolConnection();
		String sql = "insert into "+getEntityName()+fieldName.toString()
			+ "values "+placeholder.toString();
		System.out.println("***" + sql + "***");
		PreparedStatement st;
		try {
			conn.setAutoCommit(false);
			st = JdbcUtils.prepareStatement(conn, sql,parameterList);
			res = JdbcUtils.executeUpdate(st);
			conn.commit();
			conn.setAutoCommit(true);
		} catch (Exception e) {
			res = -1;
			try {
				conn.rollback();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			JdbcUtils.disConnect(conn);
		}
		return res;
	}


	public QueryResult<T> select(String sql) {
		QueryResult<T> queryResult=new QueryResult<T>();
		Connection conn = JdbcUtils.getPoolConnection();
		PreparedStatement st=null;
		ResultSet rs=null;
		//String sqlCount="select count(*) as recordCount from "+getEntityName()+sqlWhere;
		String tempsql=sql.substring(sql.indexOf("from"), sql.length());
		String sqlCount="select count(*) as recordCount "+tempsql;
		System.out.println("BaseDaoImpl-select�����ѯsql��䣺"+sqlCount);
		try {
			st = JdbcUtils.prepareStatement(conn, sql);
			rs = JdbcUtils.executeQuery(st);
			queryResult.setResultList(DaoTool.setObjectListDataFromRs(rs, entityClass.newInstance()));
			rs.close();
			st.close();
			st = JdbcUtils.prepareStatement(conn, sqlCount);
			rs = JdbcUtils.executeQuery(st);
			while(rs.next()){
				queryResult.setRecordCount(rs.getInt("recordCount"));
				System.out.println("BaseDaoImpl--getCount:"+rs.getInt("recordCount")+"����¼");		
			}
			rs.close();
			st.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.disConnect(conn);
		}
		return queryResult;
	}

	public int update(T entity) {
		// TODO Auto-generated method stub
		StringBuilder fieldsStringBuilder = new StringBuilder("");
		/* where id=? */
		StringBuilder whereSql = new StringBuilder(" where ");
		String id="";
		
		ArrayList<String> parameterList = new ArrayList<String>();
		
		Field fields[]= entity.getClass().getDeclaredFields();
		for(Field field:fields){
			try {
				field.setAccessible(true);
				if(field.get(entity)!=null&&!field.getName().equals("id")){
					fieldsStringBuilder.append(field.getName()+"=?,");
					parameterList.add(field.get(entity).toString());
				}else if(field.getName().equals("id")) {
					whereSql.append(field.getName()+"=?");
					id=field.get(entity).toString();
				}
				System.out.println("BaseDaoImpl-update"+field.getName()+"--"+field.get(entity));
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		fieldsStringBuilder.deleteCharAt(fieldsStringBuilder.length()-1);
		String sql=fieldsStringBuilder.toString()+whereSql.toString();
		parameterList.add(id);
		
		
		
		
		int res = -1;
		Connection conn = JdbcUtils.getPoolConnection();
		sql = "update "+getEntityName()+" set "+sql;
		System.out.println("BaseDaoImpl-update-sql��"+sql);
		PreparedStatement st=null;
		try {
			conn.setAutoCommit(false);
			st = JdbcUtils.prepareStatement(conn, sql,parameterList);
			res = JdbcUtils.executeUpdate(st);
			conn.commit();
			conn.setAutoCommit(true);
		} catch (Exception e) {
			res = -1;
			try {
				conn.rollback();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			JdbcUtils.disConnect(conn);
		}
		return res;
	}

	public Orders getOrdersById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getMenuNameById(String MenuId) {
		// TODO Auto-generated method stub
		return null;
	}	
	
}
