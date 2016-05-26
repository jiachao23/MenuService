package com.client.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.google.gson.Gson;
import com.menu.Service.imple.MenuServiceImpl;
import com.menu.Service.in.MenuServiceIn;
import com.menu.model.Menu;
import com.menu.model.QueryResult;
import com.opensymphony.xwork2.ActionSupport;

public class MenuAction extends ActionSupport implements ServletResponseAware,ServletRequestAware{

	private HttpServletResponse httpServletResponse;
	private HttpServletRequest httpServletRequest;
	private PrintWriter printWriter;
	
	private MenuServiceIn msi = new MenuServiceImpl();
	
	/**
	 * 获取所有菜的信息列表。
	 * @return
	 */
	public String getMenuList() {
		try {
			String gsonData = initMenuList();
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
	
	private String initMenuList(){
		
		String sql = "select * from menu";
		
		//查询到的所有菜的信息
		QueryResult<Menu> queryResult = msi.select(sql);
		ArrayList<Menu> menuList = new ArrayList<Menu>();
		menuList = (ArrayList<Menu>) queryResult.getResultList();
		
		Gson gson = new Gson();
		String gsonData = gson.toJson(menuList);
		return gsonData;
	}

	public void setServletRequest(HttpServletRequest httpServletRequest) {
		
		this.httpServletRequest = httpServletRequest;
	}

	public void setServletResponse(HttpServletResponse httpServletResponse) {

		this.httpServletResponse = httpServletResponse;
	}
}
