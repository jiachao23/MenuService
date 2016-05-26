package com.client.action;

import java.awt.print.PrinterException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import utils.PrintTool;
import utils.PrinterParas;
import utils.XPrinter58;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.menu.Service.imple.MenuServiceImpl;
import com.menu.Service.imple.OrderItemServiceImpl;
import com.menu.Service.imple.OrderServiceImpl;
import com.menu.Service.in.MenuServiceIn;
import com.menu.Service.in.OrderItemServiceIn;
import com.menu.Service.in.OrderServiceIn;
import com.menu.model.Orders;
import com.menu.model.OrderItem;
import com.menu.web.formBean.OrderItemForm;
import com.opensymphony.xwork2.ActionSupport;


@SuppressWarnings("serial")
public class OrderItemAction extends  ActionSupport implements ServletResponseAware,ServletRequestAware{
	private HttpServletResponse httpServletResponse;
	private HttpServletRequest httpServletRequest;
	private PrintWriter printWriter;
	private Orders order;
	private String gsonData;//���������ն˷��͵�����
	private OrderItemServiceIn oisi = new OrderItemServiceImpl();//��������Action������
	private OrderServiceIn odsi = new OrderServiceImpl();
	private MenuServiceIn msi = new MenuServiceImpl();
	//��ȡOrder��Ӧ������OrderItem
	public String getOrderItemForm()
	{
		List<OrderItemForm> orderItemFormList = oisi.getOrderItemForms(order);
		httpServletRequest.setAttribute("orderItemFormList", orderItemFormList);
		return SUCCESS;
	}
	
	//��ȡOrder��Ӧ������OrderItem
	public String getOrderItemFormForCounter()
	{
		List<OrderItemForm> orderItemFormList = oisi.getOrderItemForms(order);
		httpServletRequest.setAttribute("orderItemFormList", orderItemFormList);
		return SUCCESS;
	}
	
	//ɾ��orderItem
	public boolean deleteOrderItem(String idList)
	{
		int result = oisi.delete(idList);		
		return result > 0;
	}
	
	//ɾ��orderItem
	public String deleteOrderItem()
	{
		//int result = oisi.delete(idList);
		String idlist = httpServletRequest.getParameter("idlist");
		System.out.println("idlist="+idlist);
		int result = oisi.delete(idlist);
		if(result>0)
		{
//			List<OrderItemForm> orderItemFormList = oisi.getOrderItemForms(order);
//			httpServletRequest.setAttribute("orderItemFormList", orderItemFormList);
			return SUCCESS;
		}
		else
			return ERROR;
	}
	
	//�ɼ�OrderItem
	public String collectOrderItem() throws PrinterException{
		System.out.print("gsonData="+gsonData);
		Type ListType = new TypeToken<ArrayList<OrderItem>>(){}.getType();
		Gson gson = new Gson();
		ArrayList<OrderItem> alOrderItem = gson.fromJson(gsonData, ListType);
		//��ȡ�������ݲ���д��PrinterParas�ṹ��
		PrinterParas paras = new PrinterParas();
		Orders od = odsi.getOrdersById(alOrderItem.get(0).getOrder_No());
		String[][] menus = new String[alOrderItem.size()][];
		
		// List<OrderItemForm> orderItemFormList = oisi.getOrderItemForms(od);
		
		OrderItem orderItem;
		/*for (OrderItem orderItem : alOrderItem) {*/
		for (int i=0;i<alOrderItem.size();i++) {
			orderItem = alOrderItem.get(i);
			oisi.save(orderItem);//���浽���ݿ�
			
			//��ȡ�������ݲ���д��PrinterParas�ṹ��
			menus[i] = new String[3];
			menus[i][0] = msi.getMenuNameById(orderItem.getMenu_No());
		    menus[i][1] = String.valueOf(orderItem.getMenu_Num());
		    menus[i][2] = orderItem.getRemark()==null?"��":orderItem.getRemark();
		}
		
		// ��ӡ����������
		paras.setName("**����");
		paras.setCashier("002");
		paras.setPrinter("003");
		paras.setGuestNumbers(String.valueOf(od.getPersonNum()));
		paras.setOrderNumber(od.getOrder_No());
		paras.setTableName(od.getTable_No());
		paras.setPaperName("���˵�-������");
		paras.setWaiter(od.getWaiter_No());
		paras.setSerialnumber("20140201");
		paras.setOrderTime(od.getOrder_Time());	
		paras.setMenuList(menus);
		//���ô�ӡ�����ִ�ӡ����
		try
		{
			PrintTool.DoPrint(new XPrinter58(),paras);
		}
		catch(PrinterException ex)
		{
			System.out.print("printer 58 is error to print.");
		}
		
		try {
			httpServletResponse.setCharacterEncoding("UTF-8");
			httpServletResponse.setHeader("Cache-Control", "no-cache");
			printWriter = httpServletResponse.getWriter();
			printWriter.print("success");
			System.out.print("success");
		} catch (Exception e) {
			printWriter.print("failure");
			System.out.print("failure");
		} finally{
			printWriter.flush();
			printWriter.close();
		}
		return null;
	}
	
	
	
	public String getGsonData() {
		return gsonData;
	}
	
	public void setGsonData(String gsonData) {
		this.gsonData = gsonData;
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
	
	
}
