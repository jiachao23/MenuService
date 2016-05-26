package com.client.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;














import com.google.gson.Gson;
import com.menu.Service.imple.TablesServiceImpl;
import com.menu.Service.in.TablesServiceIn;
import com.menu.model.Menu;
import com.menu.model.QueryResult;
import com.menu.model.Tables;
import com.menu.util.JdbcUtils;
import com.opensymphony.xwork2.ActionSupport;

public class TestTablesAction extends ActionSupport implements ServletResponseAware,ServletRequestAware{

	private static final long serialVersionUID = 7316932748460730152L;
	private HttpServletResponse httpServletResponse;
	private HttpServletRequest httpServletRequest;
	private String jsonData;
	private TablesServiceIn tsi = new TablesServiceImpl();
	
	public String getJsonData() {
		return jsonData;
	}
	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}
    public String getTestJson() throws ClassNotFoundException
    {
    	try {
    		String gsonData = DataTableToJson();
			httpServletResponse.setCharacterEncoding("UTF-8");
			httpServletResponse.setHeader("Cache-Control", "no-cache");
			PrintWriter printWriter = httpServletResponse.getWriter();
			printWriter.print(gsonData);
			printWriter.flush();
			printWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
    }
    
    public String doSearch() throws ClassNotFoundException
    {
    	try {
    		//获取request对象中的查询参数
    		String type = httpServletRequest.getParameter("type");
    		String formdate = httpServletRequest.getParameter("formdate");
    		String todate = httpServletRequest.getParameter("todate");
    		//初始化结果字符串
    		String sqlQuery ="";
    		String gsonData ="";
    		//根据查询类型构造查询字符串，并查询到结果转成json格式
    		switch(type)
    		{
    			case "DP1":
    				sqlQuery = "SELECT  orders.table_No as 桌号, orders.order_Time as 订单时间,orders.order_Paytotal as 消费金额, orders.personNum as 消费人数  FROM orders INNER JOIN tables ON orders.table_No = tables.table_No  where orders.order_Time between '"+formdate+"' and '"+todate+"' order by 桌号 asc,订单时间 asc ";
    				gsonData = DataTableToJson(sqlQuery);
    				break;
    			case "DP2":
    				sqlQuery = "SELECT   CONVERT(VARCHAR(10),orders.order_Time,111) as 日期,orders.table_No as 桌号,SUM(orders.order_Paytotal) AS 消费总额	FROM   orders where orders.order_Time between '"+formdate+"' and '"+todate+"' GROUP BY  CONVERT(VARCHAR(10),orders.order_Time,111),orders.table_No order by 日期 asc,桌号 asc";
    				gsonData = DataTableToJson(sqlQuery);
    				break;
    			case "MP1":
    				sqlQuery = "SELECT   DATEPART(mm,orders.order_Time) as 月份,CONVERT(VARCHAR(10),orders.order_Time,111) as 日期,orders.table_No as 桌号,SUM(orders.order_Paytotal) AS 日消费总额 FROM   orders  where orders.order_Time between '"+formdate+"' and '"+todate+"' GROUP BY  CONVERT(VARCHAR(10),orders.order_Time,111),orders.table_No, DATEPART(mm,orders.order_Time)  order by 日期 asc, 桌号 asc";
    				gsonData = DataTableToJson(sqlQuery);
    				break;
    			case "MP2":
    				sqlQuery = "select t.月份, t.桌号,SUM(t.消费总额) as 月消费总额  from (SELECT  top 100 percent  DATEPART(mm,orders.order_Time) as 月份,CONVERT(VARCHAR(10),orders.order_Time,111) as 日期,orders.table_No as 桌号  ,SUM(orders.order_Paytotal) AS 消费总额  FROM   orders  where orders.order_Time between '"+formdate+"' and '"+todate+"' GROUP BY  CONVERT(VARCHAR(10),orders.order_Time,111),orders.table_No, DATEPART(mm,orders.order_Time) order by 日期 asc,桌号 asc )t GROUP BY 桌号, t.月份";
    				gsonData = DataTableToJson(sqlQuery);
    				break;
    		}
			httpServletResponse.setCharacterEncoding("UTF-8");
			httpServletResponse.setHeader("Cache-Control", "no-cache");
			PrintWriter printWriter = httpServletResponse.getWriter();
			printWriter.print(gsonData);
			printWriter.flush();
			printWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
    }
    
	private String DataTableToJson() throws ClassNotFoundException
	{
		Connection conn = null;		
		Statement stmt = null;		
		ResultSet rs = null;    //想oracle中的游标
		try {			
			/*Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");			
			conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=MenuDB","sa","123456");			*/
			conn = JdbcUtils.getPoolConnection();
			stmt = conn.createStatement();
		    rs=stmt.executeQuery("select * from tables");
		    ////////////////////////////////////////////
		    StringBuilder json = new StringBuilder();
	        json.append("{\"");
	        json.append("rows");
	        json.append("\":[");
	        ResultSetMetaData rsmd=rs.getMetaData();//rs为查询结果集
	        int count=rsmd.getColumnCount();	        
	        while(rs.next())
	        {
	            json.append("{");
	            for(int i=1;i<=count;i++)
	            {
	                json.append("\"");
	                json.append(rsmd.getColumnName(i));
	                json.append("\":\"");
	                json.append(rs.getString(i));
	                json.append("\",");
	            }
	            json.deleteCharAt(json.length() - 1);
	            json.append("},");
	        }
	        json.deleteCharAt(json.length() - 1);
	        json.append("]");
	        json.append("}");
	        jsonData = json.toString();
	        return json.toString();
		    /////////////////////////////////////////////////
		
	} catch(SQLException e) {			
		e.printStackTrace();		
	} 
	finally {			
		try {	
			if(rs != null) {	
				rs.close();				
			}				
			if(stmt != null) {					
				stmt.close();				
			}				
			if(conn != null) {					
				conn.close();				
			}			} 
		catch (SQLException e) {				
			e.printStackTrace();			
			}		
		}
		return null;
	}
	
	
	private String DataTableToJson(String _sqlQuery) throws ClassNotFoundException
	{
		Connection conn = null;		
		Statement stmt = null;		
		ResultSet rs = null;    //想oracle中的游标
		try {			
			/*Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");			
			conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=MenuDB","sa","123456");			*/
			conn = JdbcUtils.getPoolConnection();
			stmt = conn.createStatement();
		    rs=stmt.executeQuery(_sqlQuery);
		    ////////////////////////////////////////////
		    StringBuilder json = new StringBuilder();
	        json.append("{\"");
	        json.append("rows");
	        json.append("\":[");
	        ResultSetMetaData rsmd=rs.getMetaData();//rs为查询结果集
	        int count=rsmd.getColumnCount();	        
	        while(rs.next())
	        {
	            json.append("{");
	            for(int i=1;i<=count;i++)
	            {
	                json.append("\"");
	                json.append(rsmd.getColumnName(i));
	                json.append("\":\"");
	                json.append(rs.getString(i));
	                json.append("\",");
	            }
	            json.deleteCharAt(json.length() - 1);
	            json.append("},");
	        }
	        json.deleteCharAt(json.length() - 1);
	        json.append("]");
	        json.append("}");
	        jsonData = json.toString();
	        return json.toString();
		    /////////////////////////////////////////////////
		
	} catch(SQLException e) {			
		e.printStackTrace();		
	} 
	finally {			
		try {	
			if(rs != null) {	
				rs.close();				
			}				
			if(stmt != null) {					
				stmt.close();				
			}				
			if(conn != null) {					
				conn.close();				
			}			} 
		catch (SQLException e) {				
			e.printStackTrace();			
			}		
		}
		return null;
	}
	
	
	
	public void setServletRequest(HttpServletRequest httpServletRequest) {
		
		this.httpServletRequest = httpServletRequest;
	}

	public void setServletResponse(HttpServletResponse httpServletResponse) {

		this.httpServletResponse = httpServletResponse;
	}

}
