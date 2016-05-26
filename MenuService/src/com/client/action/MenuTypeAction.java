package com.client.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.google.gson.Gson;
import com.menu.Service.imple.MenuTypeServiceImpl;
import com.menu.Service.in.MenuTypeServiceIn;
import com.menu.model.MenuType;
import com.menu.model.QueryResult;
import com.opensymphony.xwork2.ActionSupport;

public class MenuTypeAction extends ActionSupport implements ServletResponseAware,ServletRequestAware{

	private HttpServletResponse httpServletResponse;
	private HttpServletRequest httpServletRequest;
	private PrintWriter printWriter;
	
	private MenuTypeServiceIn mtsi = new MenuTypeServiceImpl();
	
	/**
	 * ��ȡ���в˵�������Ϣ�б�
	 * @return
	 */
	public String getMenuTypeList() {
		
		try {
			String gsonData = initMenuTypeList();
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
	
	private String initMenuTypeList(){
		
		String sql = "select * from menuType";
		
		//��ѯ�������в˵�������Ϣ
		QueryResult<MenuType> queryResult = mtsi.select(sql);
		ArrayList<MenuType> menuTypeList = new ArrayList<MenuType>();
		menuTypeList = (ArrayList<MenuType>) queryResult.getResultList();
		
		Gson gson = new Gson();
		String gsonData = gson.toJson(menuTypeList);
		return gsonData;
	}
	
	public void setServletRequest(HttpServletRequest httpServletRequest) {
		
		this.httpServletRequest = httpServletRequest;
	}

	public void setServletResponse(HttpServletResponse httpServletResponse) {

		this.httpServletResponse = httpServletResponse;
	}

	

}
