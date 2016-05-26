package com.client.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.google.gson.Gson;
import com.menu.Service.imple.WaiterServiceImpl;
import com.menu.Service.in.WaiterServiceIn;
import com.menu.model.Waiter;
import com.opensymphony.xwork2.ActionSupport;

public class WaiterAction extends ActionSupport implements ServletResponseAware,ServletRequestAware{

	private HttpServletResponse httpServletResponse;
	private HttpServletRequest httpServletRequest;
	private PrintWriter printWriter;
	
	private WaiterServiceIn wsi = new WaiterServiceImpl();
	
	private String gsonData;
	
	/**
	 * 服务员登录服务员器
	 * @return
	 */
	public String waiterLogin(){
		Gson gson = new Gson();
		Waiter waiter = gson.fromJson(gsonData, Waiter.class);
	    boolean checkResult = wsi.checkWaiter(waiter);
	    System.out.println(checkResult+"*******");
	    try {
			httpServletResponse.setCharacterEncoding("UTF-8");
			httpServletResponse.setHeader("Cache-Control", "no-cache");
			printWriter = httpServletResponse.getWriter();
			printWriter.print(checkResult);
		} catch (Exception e) {
			printWriter.print(false);
		} finally {
			printWriter.flush();
			printWriter.close();
		}
		return null;
	}
	
	public void setServletRequest(HttpServletRequest httpServletRequest) {
		this.httpServletRequest = httpServletRequest;
	}

	public void setServletResponse(HttpServletResponse httpServletResponse) {
		this.httpServletResponse = httpServletResponse;
	}

	public String getGsonData() {
		return gsonData;
	}

	public void setGsonData(String gsonData) {
		this.gsonData = gsonData;
	}
	
}