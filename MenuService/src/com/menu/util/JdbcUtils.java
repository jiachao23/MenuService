package com.menu.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/*
 * sqlserver数据库连接池配置：
 * 在Tomcat的context.xml中添加
 * <Resource name="jdbc/sqlserver"
 		auth="Container"
 		type="javax.sql.DataSource"
 		driverClassName="com.microsoft.sqlserver.jdbc.SQLServerDriver"
 		url="jdbc:sqlserver://localhost:1433;DatabaseName=vehicleTrackingAndNavigation"
 		username="sa"
 		password="123456"
 		maxActive="50"
 		maxIdle="30"
 		maxWait="500"
 		removeAbandoned="true" removeAbandonedTimeout="60"
 		logAbandoned="true"/>
 	将sqljdbc.jar复制到tomcat的lib中
 */

public class JdbcUtils {
	
	private static DataSource ds = null;
	private static Context ctx = null;
	
	
	static{
		try {
			
			System.out.println("*************tomcat 获得数据源方式**********************");
			ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/sqlserver");
			System.out.println("***********************************");
			
			/*
			//*************dbcp 获得数据源方式**********************
			Properties prop = new Properties();
			InputStream is = JdbcUtils.class.getClassLoader().getResourceAsStream("dbcpconfig.properties");
			prop.load(is);
			ds = BasicDataSourceFactory.createDataSource(prop);
			//*************dbcp 获得数据源方式**********************
			*/
			
		} catch (Exception e) {
			//e.printStackTrace();
		}
	}
	
	/**
	 * 通过JDBC桥连接获取connection
	 * @return
	 */
	public static Connection getJDBCConnection() {
		 Connection conn = null;
		 String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";//加载JDBC驱动 
		 String dbURL ="jdbc:sqlserver://localhost:1433; DatabaseName=menuDB"; //连接服务器和数据库test 
		 String userName = "sa"; //默认用户名 
		 String userPwd ="123456"; //密码
		  
		 try {
			Class.forName(driverName); 
			conn = DriverManager.getConnection(dbURL, userName, userPwd);
			System.out.println("JDBCConnection Successful!"); //如果连接成功控制台输出Connection Successful! 
		 } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		 }
		  
		 return conn;
		
	}
	/**
	 * 通过连接池获取connection
	 * @return
	 */
	public static Connection getPoolConnection(){
		Connection conn = null;
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			//e.printStackTrace();
		}
		return conn;
	}
	
 


	public static void disConnect(Connection conn) {
		if (conn != null)
			try {
				conn.close();
			} catch (SQLException e) {
				//SystemOut.printStackTrace(e.getMessage());
				//e.printStackTrace();
			}
	}

	public static PreparedStatement prepareStatement(Connection conn,
			String sql, Object[] params) throws SQLException {
		PreparedStatement st = conn.prepareStatement(sql);
		if((params != null) && (params.length != 0)){
			for(int i = 0 ; i < params.length ; i++){
				st.setObject(i+1, params[i]);
			}
		}
		return st;
	}

	public static PreparedStatement prepareStatement(Connection conn,
			String sql, ArrayList<String> params) throws SQLException {
		PreparedStatement st = conn.prepareStatement(sql);
		System.out.println("------------------" + params.size()+"------");
		if((params != null) && (params.size()!= 0)){
			for(int i = 0 ; i < params.size() ; i++){
				st.setObject(i+1, params.get(i));
			}
		}
		return st;
	}
	
	public static PreparedStatement prepareStatement(Connection conn,String sql,Object param) throws SQLException{
		return prepareStatement(conn,sql,new Object[]{param});
	}
	
	public static PreparedStatement prepareStatement(Connection conn,String sql) throws SQLException{
		PreparedStatement st = conn.prepareStatement(sql);
		return st;
	}
	
	
	
	public static ResultSet executeQuery(PreparedStatement st) throws SQLException{
		return st.executeQuery();
	}
	
	public static int executeUpdate(PreparedStatement st) throws SQLException{
		return st.executeUpdate();
	}
	
}
