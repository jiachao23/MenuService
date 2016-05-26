package com.client.action;

import java.io.IOException;
import java.io.PrintWriter;
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
import com.opensymphony.xwork2.ActionSupport;

public class TablesAction extends ActionSupport implements ServletResponseAware,ServletRequestAware{

	private HttpServletResponse httpServletResponse;
	private HttpServletRequest httpServletRequest;
	private PrintWriter printWriter;
	
	private TablesServiceIn tsi = new TablesServiceImpl();
	
	/**
	 * 获取所有餐桌信息列表
	 * @return
	 */
	public String getTableList() {
		
		try {
		  	String gsonData = initTableList();
			httpServletResponse.setCharacterEncoding("UTF-8");
			httpServletResponse.setHeader("Cache-Control", "no-cache");
			printWriter = httpServletResponse.getWriter();
			printWriter.print(gsonData);
		} catch (IOException e) {
			printWriter.print("failure");
		} finally {
			printWriter.flush();
			printWriter.close();
		}
		return null;
	}
	
	private String initTableList(){
		
		String sqlString = "select * from tables";
		
		QueryResult<Tables> queryResult = tsi.select(sqlString);
		ArrayList<Tables> tableList = new ArrayList<Tables>();
		tableList = (ArrayList<Tables>) queryResult.getResultList();
		
		Gson gson = new Gson();
		String gsonData = gson.toJson(tableList);
		return gsonData;
	}
	
	public void setServletRequest(HttpServletRequest httpServletRequest) {
		
		this.httpServletRequest = httpServletRequest;
	}

	public void setServletResponse(HttpServletResponse httpServletResponse) {

		this.httpServletResponse = httpServletResponse;
	}

}
