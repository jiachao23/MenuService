package com.menu.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;




public class DaoTool  {
	protected static Connection conn = null;
	protected static PreparedStatement st = null;
	protected static ResultSet rs = null;	
	
	/**
	 * 检查字符串的长度，
	 * <br/>
	 * value!=null  &&  （value.toString().length()>=length）  时返回 true
	 * <br/>
	 * @param value
	 * @param length
	 * @return
	 */
	private  static  boolean ChekcStrMinLength(String value,boolean autoTrim,int length){
		boolean ok = false;
		if(autoTrim){
			value = value.trim();
		}
		if( (value!=null)  &&  (value.length()>=length)){
			ok = true;
		}		
		return ok;
	}
	
	/**
	 * 最小 长度 1（包含1）（不去掉空格）时返回 true
	 * @param value
	 * @return
	 */
	public  static  boolean isNotNull_1(String value){			
		return ChekcStrMinLength(value,false,1);
	}
	/**
	 * 最小 长度 32（包含32）（不去掉空格）时返回 true
	 * @param value
	 * @return
	 */
	public  static  boolean isNotNull_32(String value){			
		return ChekcStrMinLength(value,false,32);
	}
	
	/**
	 * 最小 长度 32（包含32）（自动去掉两端空格）时返回 true
	 * @param value
	 * @return
	 */
	public  static  boolean isNotNull_t_32(String value){			
		return ChekcStrMinLength(value,true,32);
	}
	/**
	 * 最小 长度 1（包含1）（自动去掉两端空格）时返回 true
	 * @param value
	 * @return
	 */
	public  static  boolean isNotNull_t_1(String value){			
		return ChekcStrMinLength(value,true,1);
	}
	
	
	/**
	 * 变换前：fa4368fecf3240df90eb7988aef79fc4,efe820dbca2c46b1b5249d694945f242,a7ba60a45c8147b9803c70de58e52af8
	 * 变换后：'fa4368fecf3240df90eb7988aef79fc4','efe820dbca2c46b1b5249d694945f242','a7ba60a45c8147b9803c70de58e52af8'
	 * @param idList
	 * @return
	 */
	public  static  String changeIdList(String idList){
		if(idList==null)
			return null;
		else
			return "'"+idList.replace(",", "','")+"'";
	}
	
	
	
	/**
	 * 将查询的单条记录放到对象中去。（根据查询的列名 自动调用对象相应属性 的set方法 ） 并返回对象
	 * @param sql
	 * @param object
	 */	
	public  static Object returnSetDetailsToObject (String sql,Object object){
		try {
			
			conn = JdbcUtils.getPoolConnection();			
			st = JdbcUtils.prepareStatement(conn, sql);
			rs = JdbcUtils.executeQuery(st);		
			object=DaoTool.setObjectDataFromRs(rs, object);
		} catch (Exception e) {		
			e.printStackTrace();
		}finally {
			JdbcUtils.disConnect(conn);	
		}
		return object;
	}
	
	/**
	 * 将查询的单条记录放到对象中去。（根据查询的列名 自动调用对象相应属性 的set方法 ）
	 * @param sql
	 * @param object
	 */
	public  static void setDetailsToObject (String sql,Object object){
		try {
			
			conn = JdbcUtils.getPoolConnection();			
			st = JdbcUtils.prepareStatement(conn, sql);
			rs = JdbcUtils.executeQuery(st);		
			DaoTool.setObjectDataFromRs(rs, object);
		} catch (Exception e) {		
			e.printStackTrace();
		}finally {
			JdbcUtils.disConnect(conn);	
		}
	}
	
	/**
	 * 将查询的单条记录放到对象中去。（根据查询的列名 自动调用对象相应属性 的set方法 ）
	 * @param sql
	 * @param thisId
	 * @param object
	 */
	public  static void setDetailsToObject (String sql,String thisId,Object object){
		try {
			conn = JdbcUtils.getPoolConnection();			
			st = JdbcUtils.prepareStatement(conn, sql,thisId);
			rs = JdbcUtils.executeQuery(st);		
			DaoTool.setObjectDataFromRs(rs, object);
		} catch (Exception e) {		
			e.printStackTrace();
		}finally {
			JdbcUtils.disConnect(conn);	
		}
	}
	/**
	 * 执行一次数据库更新操作，返回影响行数
	 * @param sql
	 * @return
	 */
	
	public  static int executeUpdate (String sql){
		int res = 0;		
		try {
			conn = JdbcUtils.getPoolConnection();
			conn.setAutoCommit(false);	
			st = JdbcUtils.prepareStatement(conn,sql);
			res = JdbcUtils.executeUpdate(st);				
			conn.commit();
			conn.setAutoCommit(true);
		} catch (Exception e) {
			res = 0 ;			
			try {
				conn.rollback();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally {
			JdbcUtils.disConnect(conn);	
		}
		return res;
	}
	/**
	 * 执行一次数据库更新操作，返回影响行数
	 * @param sql
	 * @param params sql语句中的参数列表
	 * @return
	 */
	
	public  static int executeUpdate (String sql,Object[] params){
		int res = 0;		
		try {
			conn = JdbcUtils.getPoolConnection();
			conn.setAutoCommit(false);	
			st = JdbcUtils.prepareStatement(conn,sql,params);
			res = JdbcUtils.executeUpdate(st);				
			conn.commit();
			conn.setAutoCommit(true);
		} catch (Exception e) {
			res = 0 ;			
			try {
				conn.rollback();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally {
			JdbcUtils.disConnect(conn);	
		}
		return res;
	}
	
	/**
	 * 执行 sql
	 * @param sql
	 * @param params
	 * @return
	 */
	public  static int executeUpdate (String sql,Object params){
		int res = 0;		
		try {
			conn = JdbcUtils.getPoolConnection();
			conn.setAutoCommit(false);	
			st = JdbcUtils.prepareStatement(conn,sql,params);
			res = JdbcUtils.executeUpdate(st);				
			conn.commit();
			conn.setAutoCommit(true);
		} catch (Exception e) {
			res = 0 ;			
			try {
				conn.rollback();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally {
			JdbcUtils.disConnect(conn);	
		}
		return res;
	}
	
	
	
	private static void runMethod(Object classObject,Method setMethod,Object value,int type) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException{

		if(value==null)
			return;
		
		
		switch (type) {
			case 1: setMethod.invoke(classObject,new String[]{value.toString()}); 	break;
			case 2: setMethod.invoke(classObject,new Integer(value.toString()));  	break;
			case 3: setMethod.invoke(classObject,new Long(value.toString())); 		break;
			case 4: setMethod.invoke(classObject,new Float(value.toString()));  	break;
			case 5: setMethod.invoke(classObject,new Double(value.toString()));  	break;
			case 6: setMethod.invoke(classObject,new Boolean(value.toString()));  	break;
			case 7: setMethod.invoke(classObject,new Byte(value.toString()));		break;
			case 8: setMethod.invoke(classObject,new Character(value.toString().charAt(0))); break;
			case 9: setMethod.invoke(classObject,new Short(value.toString()));		break;
		}
		
	}
	
	
//	public static String changeObjectListToJSObjectList(List list,Object object,String[] attributess){
//		String reString = "var myObjectList = new Array();\n var tempObject = null; \n";
//		
//		Class c = object.getClass();
//		Field[] fieldarray = c.getDeclaredFields(); 
//		Method methodList[] = new Method[attributess.length];
//		String[] ObjectAttributess = new String[attributess.length];	
//		
//		try {
//			for (int i = 0; i < attributess.length; i++) {
//				String tempAttributes = attributess[i];
//				boolean hasThisAttributes = false;
//				Field tempf1 = null;
//				for (int j = 0; j < fieldarray.length; j++) {
//					tempf1 = fieldarray[j];
//					if((tempf1.getName()).toLowerCase().equals(tempAttributes.toLowerCase())){
//						hasThisAttributes = true;
//						ObjectAttributess[i] = tempf1.getName();
//						break;
//					}
//				}
//				if(hasThisAttributes==false){
//					System.out.println("系统出现错误！对象没有改属性！");
//					System.exit(0);
//				}
//				if(ObjectAttributess[i] != null){						
//					 String stringLetter=ObjectAttributess[i].substring(0, 1).toUpperCase();
//		    	     String getName="get"+stringLetter+ObjectAttributess[i].substring(1); 
//		    	     Method setMethod = null;
//		    	     setMethod = object.getClass().getMethod(getName);
//		    	     methodList[i] = setMethod;
//				}
//			}//for
//			
//			for (int i = 0; i < list.size(); i++) {
//				reString += "tempObject = new Object(); \n ";				
//				for (int j = 0; j < ObjectAttributess.length; j++) {
//					//String thisValueString = methodList[j].invoke(list.get(i)).toString();
//					reString += "tempObject."+ObjectAttributess[j]+" = '"+methodList[j].invoke(list.get(i))+"';\n";					
//				}				
//				reString += "myObjectList["+i+"] = tempObject;\n\n\n";
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		reString += " if(typeof(jsAjaxDataCallBack)!='undefined'){jsAjaxDataCallBack();};";		
//		return reString;		
//	}
	
	/**
	 * 将  ResultSet rs 中的数据，存放到 List 数组中。
	 * rs 中的每一行数据存放到 一个  Object object中，然后在将  Object object 存放到List中。
	 * 向 Object object 存放原理：扫面 列名，然后根据列名 查找 对象是否有该属性，如果调用 SET方法 赋值。
	 * @param rs
	 * @param object
	 * @return
	 */
	
	
	public  static List setObjectListDataFromRs(ResultSet rs,Object object){		
		List datas = new ArrayList();
		
		Class c = object.getClass();
		
		if(SystemSeting.DaoTool_OUT){
			StackTraceElement ste = getCaller("DaoTool.java");	
			System.out.println("******************************************************");
			System.out.println("*  本次DaoTool类被调用信息：");
			System.out.println("*  调用类名："+ste.getClassName());
			System.out.println("*  调用方法："+ste.getMethodName());
			System.out.println("*  类中行数："+ste.getLineNumber()+"行");
			System.out.println("******************************************************");
			
			String string = ste.getClassName();
			String voFromDaoImplClassName = string.substring(string.lastIndexOf(".")+1, string.indexOf("DaoImpl"));
			String tempClass = c.toString();
			String voFromObjectClassName = tempClass.substring(tempClass.lastIndexOf(".")+1);
			
			if(!voFromDaoImplClassName.toLowerCase().equals(voFromObjectClassName.toLowerCase()))
			{			
				//System.out.println("voFromDaoImplClassName="+voFromDaoImplClassName);
				//System.out.println("voFromObjectClassName="+voFromObjectClassName);			
				System.out.println("\n\n错误：");			
				System.out.println(ste.getClassName()+"中第"+ste.getLineNumber()+"行 中调用DaoTool.setObjectListDataFromRs方法的第二个参数类型错误！");
				System.out.println("第二个参数类型现在是："+voFromObjectClassName);
				System.out.println("第二个参数类型应该是："+voFromDaoImplClassName);			
				System.out.println("系统出现严重错误！");
				System.out.println("系统关闭！");	
				//MyTool.printSystemStackTraceElement();
				//System.exit(0);
			}
		}
		
		
		
		
		Field[] fieldarray = c.getDeclaredFields(); 
		try {
			
			int titlenumber = rs.getMetaData().getColumnCount();
			Method methodList[] = new Method[titlenumber];
			int[] MethodType = new int[titlenumber]; 
			
			
			for (int j = 0; j < titlenumber; j++) {
				String title = rs.getMetaData().getColumnLabel(j+1);
				String methodName = null;
				Field tempf = null;
				for(int i = 0 ; i<fieldarray.length;i++)
				{
					Field tempf1 = fieldarray[i];						
				    String name = (tempf1.getName()).toLowerCase();
				    if(name.equals(title.toLowerCase())){
				    	methodName = tempf1.getName();
				    	tempf = tempf1;
				    	break;
				    }
				}
				
				if(methodName != null){						
					 String stringLetter=methodName.substring(0, 1).toUpperCase();
		    	     String setName="set"+stringLetter+methodName.substring(1); 
		    	     Method setMethod = null;
		    	     setMethod = object.getClass().getMethod(setName, new Class[]{tempf.getType()});
		    	     methodList[j] = setMethod;
		    	     String TYPE_ = tempf.getType().toString();		    	     
				      if(TYPE_.equals("class java.lang.String"))      
				    	  MethodType[j] = 1;
				      else if(TYPE_.equals("int"))      
				    	  MethodType[j] = 2; 
				      else if(TYPE_.equals("long"))      
				    	  MethodType[j] = 3;
				      else if(TYPE_.equals("float"))      
				    	  MethodType[j] = 4;
				      else if(TYPE_.equals("double"))      
				    	  MethodType[j] = 5;
				      else if(TYPE_.equals("boolean"))      
				    	  MethodType[j] = 6;
				      else if(TYPE_.equals("byte"))      
				    	  MethodType[j] = 7;
				      else if(TYPE_.equals("char"))      
				    	  MethodType[j] = 8;
				      else if(TYPE_.equals("short"))      
				    	  MethodType[j] = 9;
				      else 
				    	  MethodType[j] = 0;
					
				      
				}else{
					MethodType[j] = 0;
					if(SystemSeting.DaoTool_OUT){
						System.err.println("message:返回的数据集中列名字为'"+title.toLowerCase()+"'数据 无法被使用 ！");
						System.err.println("原       因:"+object.getClass()+"类中没有名字为'"+title.toLowerCase()+"'的属性及相应的set方法！");
					}
					
					
				}
			}
			
			
			while(rs.next()){
				
				Object classObject =  object.getClass().newInstance();
				
				if(SystemSeting.DaoTool_OUT){
					System.out.println("-----------------------------------------------");
					System.out.println("开始初始化:  "+object.getClass()+"   类 的对象！(第"+rs.getRow()+"个)");
					System.out.println();
				}
				
				for (int j = 0; j < titlenumber; j++) {
					String title = rs.getMetaData().getColumnLabel(j+1);
					if(MethodType[j]!=0){						
						Object  value = rs.getObject(title);
						//判断是否是日期类型，如果是 则需要转换
						if((value!=null)&&(value.getClass().toString().equals("class java.sql.Date"))){
							value = rs.getTimestamp(title).toString().substring(0,19);
						}
						//if(value.)
						//System.out.println("value="+value);
						
						if(value==null){
							if(SystemSeting.DaoTool_OUT){
								System.err.println("message:返回的数据集中   第"+rs.getRow()+"行    列名字为'"+title.toLowerCase()+"' 值为null！");
							}
						}
						
						
						if(SystemSeting.DaoTool_OUT){
							System.out.println(title.toLowerCase()+":"+value);						
						}
						
						runMethod(classObject,methodList[j], value,MethodType[j]);
						
					}
				}
				 //设置该对象 已经初始化
				  //Method setInitMethod = null;
			      //setInitMethod = classObject.getClass().getMethod("setInit", boolean.class );
			      //setInitMethod.invoke(classObject, true);
				datas.add(classObject);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("DaoTool.setObjectDataFromRs出现异常！"+e.getMessage());
			e.printStackTrace();
		}
		return datas;
	}
	/**
	 * 将查询出来的每一行 放到对象object中（利用反射机制，更具列名 找相应的属性名）
	 * 然后在 放到 Map 中，
	 * mapKeyColumnName 表示 map 中key值得来源列名字
	 * @param rs
	 * @param object
	 * @param mapKeyColumnName   表示 map 中key值得来源列名字
	 * @return
	 */
	
	public  static Map setObjectMapDataFromRs(ResultSet rs,Object object,String mapKeyColumnName){		
		Map datas = new HashMap();
		
		Class c = object.getClass();
		
		if(SystemSeting.DaoTool_OUT){
			StackTraceElement ste = getCaller("DaoTool.java");	
			System.out.println("******************************************************");
			System.out.println("*  本次DaoTool类被调用信息：");
			System.out.println("*  调用类名："+ste.getClassName());
			System.out.println("*  调用方法："+ste.getMethodName());
			System.out.println("*  类中行数："+ste.getLineNumber()+"行");
			System.out.println("******************************************************");
			
			String string = ste.getClassName();
			String voFromDaoImplClassName = string.substring(string.lastIndexOf(".")+1, string.indexOf("DaoImpl"));
			String tempClass = c.toString();
			String voFromObjectClassName = tempClass.substring(tempClass.lastIndexOf(".")+1);
			
			if(!voFromDaoImplClassName.toLowerCase().equals(voFromObjectClassName.toLowerCase()))
			{			
				//System.out.println("voFromDaoImplClassName="+voFromDaoImplClassName);
				//System.out.println("voFromObjectClassName="+voFromObjectClassName);			
				System.out.println("\n\n错误：");			
				System.out.println(ste.getClassName()+"中第"+ste.getLineNumber()+"行 中调用DaoTool.setObjectListDataFromRs方法的第二个参数类型错误！");
				System.out.println("第二个参数类型现在是："+voFromObjectClassName);
				System.out.println("第二个参数类型应该是："+voFromDaoImplClassName);			
				System.out.println("系统出现严重错误！");
				System.out.println("系统关闭！");	
				//MyTool.printSystemStackTraceElement();
				//System.exit(0);
			}
		}
		
		
		
		
		Field[] fieldarray = c.getDeclaredFields(); 
		try {
			
			int titlenumber = rs.getMetaData().getColumnCount();
			Method methodList[] = new Method[titlenumber];
			int[] MethodType = new int[titlenumber]; 
			
			boolean hasThisColumnName = false;//判断是否 有 该名字的列 数据
			
			for (int j = 0; j < titlenumber; j++) {
				String title = rs.getMetaData().getColumnLabel(j+1);
				
				if(title.toLowerCase().equals(mapKeyColumnName.toLowerCase())){
					hasThisColumnName = true;
				}
				
				String methodName = null;
				Field tempf = null;
				for(int i = 0 ; i<fieldarray.length;i++)
				{
					Field tempf1 = fieldarray[i];						
				    String name = (tempf1.getName()).toLowerCase();
				    if(name.equals(title.toLowerCase())){
				    	methodName = tempf1.getName();
				    	tempf = tempf1;
				    	break;
				    }
				}
				
				if(methodName != null){						
					 String stringLetter=methodName.substring(0, 1).toUpperCase();
		    	     String setName="set"+stringLetter+methodName.substring(1); 
		    	     Method setMethod = null;
		    	     setMethod = object.getClass().getMethod(setName, new Class[]{tempf.getType()});
		    	     methodList[j] = setMethod;
		    	     String TYPE_ = tempf.getType().toString();		    	     
				      if(TYPE_.equals("class java.lang.String"))      
				    	  MethodType[j] = 1;
				      else if(TYPE_.equals("int"))      
				    	  MethodType[j] = 2; 
				      else if(TYPE_.equals("long"))      
				    	  MethodType[j] = 3;
				      else if(TYPE_.equals("float"))      
				    	  MethodType[j] = 4;
				      else if(TYPE_.equals("double"))      
				    	  MethodType[j] = 5;
				      else if(TYPE_.equals("boolean"))      
				    	  MethodType[j] = 6;
				      else if(TYPE_.equals("byte"))      
				    	  MethodType[j] = 7;
				      else if(TYPE_.equals("char"))      
				    	  MethodType[j] = 8;
				      else if(TYPE_.equals("short"))      
				    	  MethodType[j] = 9;
				      else 
				    	  MethodType[j] = 0;
					
				      
				}else{
					MethodType[j] = 0;
					if(SystemSeting.DaoTool_OUT){
						System.err.println("message:返回的数据集中列名字为'"+title.toLowerCase()+"'数据 无法被使用 ！");
						System.err.println("原       因:"+object.getClass()+"类中没有名字为'"+title.toLowerCase()+"'的属性及相应的set方法！");
					}
					
					
				}
			}//for
			
			
			if(hasThisColumnName==false){
				StackTraceElement ste = getCaller("DaoTool.java");	
				System.out.println("\n\n错误：");			
				System.out.println(ste.getClassName()+"中第"+ste.getLineNumber()+"行 中调用DaoTool.setObjectMapDataFromRs方法的第三个参数类型错误！");
				System.out.println("第三个参数不能为空    或者   查询结果中 没有 名字 为" +mapKeyColumnName+"的列！");			
				System.out.println("系统出现严重错误！");
				System.out.println("系统关闭！");		
			}
			
			
			while(rs.next()){
				
				Object classObject =  object.getClass().newInstance();
				
				if(SystemSeting.DaoTool_OUT){
					System.out.println("-----------------------------------------------");
					System.out.println("开始初始化:  "+object.getClass()+"   类 的对象！(第"+rs.getRow()+"个)");
					System.out.println();
				}
				
				for (int j = 0; j < titlenumber; j++) {
					String title = rs.getMetaData().getColumnLabel(j+1);
					if(MethodType[j]!=0){						
						Object  value = rs.getObject(title);
						//判断是否是日期类型，如果是 则需要转换
						if((value!=null)&&(value.getClass().toString().equals("class java.sql.Date"))){
							value = rs.getTimestamp(title).toString().substring(0,19);
						}
						//if(value.)
						//System.out.println("value="+value);
						
						if(value==null){
							if(SystemSeting.DaoTool_OUT){
								System.err.println("message:返回的数据集中   第"+rs.getRow()+"行    列名字为'"+title.toLowerCase()+"' 值为null！");
							}
						}
						
						
						if(SystemSeting.DaoTool_OUT){
							System.out.println(title.toLowerCase()+":"+value);						
						}
						
						runMethod(classObject,methodList[j], value,MethodType[j]);
						
					}
				}
				 //设置该对象 已经初始化
				  //Method setInitMethod = null;
			      //setInitMethod = classObject.getClass().getMethod("setInit", boolean.class );
			      //setInitMethod.invoke(classObject, true);
				//datas.add(classObject);
			      datas.put(rs.getObject(mapKeyColumnName), classObject);
			    
			}//while
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("DaoTool.setObjectDataFromRs出现异常！"+e.getMessage());
			e.printStackTrace();
		}
		return datas;
	}
	
	
	
	public  static Object setObjectDataFromRs(ResultSet rs,Object object){		
		Class c = object.getClass();
		
		if(SystemSeting.DaoTool_OUT){
			StackTraceElement ste = getCaller("DaoTool.java");		
			System.out.println("******************************************************");
			System.out.println("*  本次DaoTool类被调用信息：");
			System.out.println("*  调用类名："+ste.getClassName());
			System.out.println("*  调用方法："+ste.getMethodName());
			System.out.println("*  类中行数："+ste.getLineNumber()+"行");
			System.out.println("******************************************************");
			
			
			String string = ste.getClassName();
			String voFromDaoImplClassName = string.substring(string.lastIndexOf(".")+1, string.indexOf("DaoImpl"));
			String tempClass = c.toString();
			String voFromObjectClassName = tempClass.substring(tempClass.lastIndexOf(".")+1);
			
			if(!voFromDaoImplClassName.toLowerCase().equals(voFromObjectClassName.toLowerCase()))
			{			
				//System.out.println("voFromDaoImplClassName="+voFromDaoImplClassName);
				//System.out.println("voFromObjectClassName="+voFromObjectClassName);			
				System.out.println("\n\n错误：");			
				System.out.println(ste.getClassName()+"中第"+ste.getLineNumber()+"行 中调用DaoTool.setObjectDataFromRs方法的第二个参数类型错误！");
				System.out.println("第二个参数类型现在是："+voFromObjectClassName);
				System.out.println("第二个参数类型应该是："+voFromDaoImplClassName);			
				System.out.println("系统出现严重错误！");
				System.out.println("系统关闭！");	
				//MyTool.printSystemStackTraceElement();
				//System.exit(0);
			}
		}
		
		
		
		Field[] fieldarray = c.getDeclaredFields();
		try {
			if(rs.next()){
				
				if(SystemSeting.DaoTool_OUT){
					System.out.println();
					System.out.println("开始初始化:  "+object.getClass()+"   类 的对象！");
					System.out.println();
				}
				
				int titlenumber = rs.getMetaData().getColumnCount();
				for (int j = 0; j < titlenumber; j++) {
					/**
					 * getColumnLabel 是获取标签名字（如果有别名，则是别名）
					 */
					String title = rs.getMetaData().getColumnLabel(j+1);
					String methodName = null;
					Field tempf = null;
					for(int i = 0 ; i<fieldarray.length;i++)
					{
						Field tempf1 = fieldarray[i];						
					    String name = (tempf1.getName()).toLowerCase();
					    if(name.equals(title.toLowerCase())){
					    	methodName = tempf1.getName();
					    	tempf = tempf1;
					    	break;
					    }
					}
					Object  value = rs.getObject(title);
					
					
					
					if(value==null){
						if(SystemSeting.DaoTool_OUT){
							System.err.println("message:返回的数据集中列名字为'"+title.toLowerCase()+"'值为null！");
						}
					}
					
					
					//判断是否是日期类型，如果是 则需要转换
					if((value!=null)&&(value.getClass().toString().equals("class java.sql.Date"))){
						value = rs.getTimestamp(title).toString().substring(0,19);
					}
					
					
					if(SystemSeting.DaoTool_OUT){
						System.out.println(title.toLowerCase()+":"+value);						
					}
					
					//System.out.println("value="+value);
					if(methodName != null&&value!=null){
						
						 String stringLetter=methodName.substring(0, 1).toUpperCase();
			    	     String setName="set"+stringLetter+methodName.substring(1); 
			    	     Method setMethod = null;
			    	     setMethod = object.getClass().getMethod(setName, new Class[]{tempf.getType()});
			    	     
			    	     String TYPE_ = tempf.getType().toString();
			    	     
					      if(TYPE_.equals("class java.lang.String"))      
					       setMethod.invoke(object,new String[]{value.toString()});
					      else if(TYPE_.equals("int"))      
					       setMethod.invoke(object,new Integer(value.toString()));       
					      else if(TYPE_.equals("long"))      
					       setMethod.invoke(object,new Long(value.toString()));
					      else if(TYPE_.equals("float"))      
					       setMethod.invoke(object,new Float(value.toString())); 
					      else if(TYPE_.equals("double"))      
					       setMethod.invoke(object,new Double(value.toString())); 
					      else if(TYPE_.equals("boolean"))      
					       setMethod.invoke(object,new Boolean(value.toString())); 
					      else if(TYPE_.equals("byte"))      
					       setMethod.invoke(object,new Byte(value.toString()));
					      else if(TYPE_.equals("char"))      
					       setMethod.invoke(object,new Character(value.toString().charAt(0)));
					      else if(TYPE_.equals("short"))      
					       setMethod.invoke(object,new Short(value.toString()));
					      
					      //设置该对象 已经初始化
					      //Method setInitMethod = null;
					      //setInitMethod = object.getClass().getMethod("setInit", boolean.class );
					      //setInitMethod.invoke(object, true);
					      
					}else{
						//System.out.println(title+"列数据没有用！");
						if(SystemSeting.DaoTool_OUT&&methodName == null){
							System.err.println("message:返回的数据集中列名字为'"+title.toLowerCase()+"'数据 无法被使用 ！");
							System.err.println("原       因:"+object.getClass()+"类中没有名字为'"+title.toLowerCase()+"'的属性及相应的set方法！");
						}
					}
				}
				
			}else{
				System.out.println("DaoTool.setObjectDataFromRs中rs没有数据！");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("DaoTool.setObjectDataFromRs出现异常！"+e.getMessage());
			e.printStackTrace();
		}

		return object;
	}
	
	
	
	public static List getListFromResultSet(ResultSet rs) throws SQLException{
		List datas = new ArrayList() ;
		int i = rs.getMetaData().getColumnCount();
		String[] titleStrings = new String[i];
		for (int j = 0; j < titleStrings.length; j++) {
			titleStrings[j] = rs.getMetaData().getColumnLabel(j+1);
			//System.out.println(titleStrings[j]);
		}
		while(rs.next())
		{
			Map datasrow = new HashMap() ;	
			for (int j = 0; j < titleStrings.length; j++) {
				datasrow.put(titleStrings[j], rs.getString(titleStrings[j]));	
			}			
			datas.add(datasrow);
		}		
		return datas;
	}
	
	public static Map getMapFromResultSet(ResultSet rs) throws SQLException{
		Map datasrow = new HashMap() ;
		int i = rs.getMetaData().getColumnCount();
		String[] titleStrings = new String[i];
		for (int j = 0; j < titleStrings.length; j++) {
			titleStrings[j] = rs.getMetaData().getColumnLabel(j+1);
		}
		if(rs.next())
		{
			for (int j = 0; j < titleStrings.length; j++) {
				datasrow.put(titleStrings[j], rs.getString(titleStrings[j]));	
			}			
		}		
		return datasrow;
	}
	
	
	/**
	 * 获取调用 className 的StackTraceElement
	 */
	private static StackTraceElement getCaller(String className){
		StackTraceElement ste = null;
		int i=0;   
		StackTraceElement stack[] = (new Throwable()).getStackTrace();			
//		for (i=0; i < stack.length; i++) {   
//			StackTraceElement ste=stack[i]; 
//			System.out.println(ste.getClassName()+"."+ste.getMethodName()+"(...)");   
//			System.out.println(i+"--"+ste.getMethodName());   
//			System.out.println(i+"--"+ste.getFileName());   
//			System.out.println(i+"--"+ste.getLineNumber()); 			  
//		}			
		//找到最早一个调用SystemOut.java的类
		for (i=0; i < stack.length; i++) {
			if(stack[i].getFileName().equals(className)&&(!stack[i+1].getFileName().equals(className)))
			{
				break;
			}			  
		}
		i++;
		ste=stack[i];
//		System.out.println("***************************************"); 
//		System.out.println(ste.getClassName()+"."+ste.getMethodName()+"(...)");   
//		System.out.println(i+"--"+ste.getMethodName());   
//		System.out.println(i+"--"+ste.getFileName());   
//		System.out.println(i+"--"+ste.getLineNumber()); 
		return ste;		
	}
	
}
