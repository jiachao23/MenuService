package com.client.action;


import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.google.gson.Gson;
import com.menu.Service.imple.OrderServiceImpl;
import com.menu.Service.in.OrderServiceIn;
import com.menu.model.Orders;
import com.opensymphony.xwork2.ActionSupport;


@SuppressWarnings("serial")
public class OrderAction extends ActionSupport implements ServletResponseAware,ServletRequestAware{
	
	private HttpServletResponse httpServletResponse;
	private HttpServletRequest httpServletRequest;
	private PrintWriter printWriter;
	
	private OrderServiceIn osi = new OrderServiceImpl();//用来处理Action的请求
	
	private Orders order;
	private String gsonData;//用来接收终端发送的数据
	private String orderItems;//用来接受前台发送的数据
	
	//采集order信息
	public String collectOrder(){
		System.out.println("*******");
		Gson gson = new Gson();
		Orders order = gson.fromJson(gsonData, Orders.class);
		boolean result = osi.getOrderExist(order);
		if(!result) {
			System.out.println("****"+result);
			osi.save(order);
			osi.setTableStatus(order);
			
			try {
				httpServletResponse.setCharacterEncoding("UTF-8");
				httpServletResponse.setHeader("Cache-Control", "no-cache");
				printWriter = httpServletResponse.getWriter();
				printWriter.print("success");
			} catch (Exception e) {
				printWriter.print("failure");
			} finally {
				printWriter.flush();
				printWriter.close();
			}
		}
		return null;
	}
	
	/**
	 * 更新订单
	 * @return
	 */
	public String updateOrders(){
		osi.update(order);
		osi.setTableStatus(order);
		
		//OrderItemServiceIn oisi = new OrderItemServiceImpl();
		//oisi.updateOrderItemByOrder_No(order, orderItems);
		
		return null;
	}
	
	public void setServletRequest(HttpServletRequest httpServletRequest) {
		this.httpServletRequest = httpServletRequest;
	}

	public void setServletResponse(HttpServletResponse httpServletResponse) {
		this.httpServletResponse = httpServletResponse;
		httpServletResponse.setCharacterEncoding("UTF-8");
		httpServletResponse.setContentType("text/plain");
	}

	public Orders getOrder() {
		return order;
	}

	public void setOrder(Orders order) {
		this.order = order;
	}

	public String getGsonData() {
		return gsonData;
	}

	public void setGsonData(String gsonData) {
		this.gsonData = gsonData;
	}

	public String getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(String orderItems) {
		this.orderItems = orderItems;
	}
}
