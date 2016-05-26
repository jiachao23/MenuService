package com.web.action;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.menu.Service.imple.WaiterServiceImpl;
import com.menu.Service.in.WaiterServiceIn;
import com.menu.model.Orders;
import com.menu.model.QueryResult;
import com.menu.model.Waiter;
import com.menu.web.formBean.WaiterForm;
import com.opensymphony.xwork2.ActionSupport;

public class WaiterAction extends ActionSupport implements ServletResponseAware,ServletRequestAware{

	private HttpServletResponse httpServletResponse;
	private HttpServletRequest httpServletRequest;
	
	private WaiterServiceIn wsi = new WaiterServiceImpl();
	
	private WaiterForm waiterForm;
	private Waiter waiter;
	private String gsonData;
	
	/**
	 * 服务员登录服务员器
	 * @return
	 */
	public String waiterLogin(){
		CheckWaiterThread collectWaiterThread = new CheckWaiterThread();
		collectWaiterThread.start();
		return null;
	}
	
	/**
	 * 解析gsonData数据封装成对象
	 * @author Administrator
	 *
	 */
	public class CheckWaiterThread extends Thread {

		@Override
		public void run() {
			
			Gson gson = new Gson();
			Waiter waiter = gson.fromJson(gsonData, Waiter.class);
		    boolean checkResult = wsi.checkWaiter(waiter);
		    try {
				httpServletResponse.getWriter().print(checkResult);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		/**
		 * 将字符串数据解析成List<Menu>对象
		 * @param gsonData要解析的数据
		 */
		public ArrayList<Orders> parseJson(String gsonData)
		{
			Type ListType = new TypeToken<ArrayList<Orders>>(){}.getType();
			Gson gson = new Gson();
			ArrayList<Orders> alOrder = gson.fromJson(gsonData, ListType);
			return alOrder;
		}
		
	}
	/**
	 * 菜单类型信息管理页面 初始化  重要返回参数查询到的菜单类型信息条数。
	 * @return
	 */
	public String managePage(){	
		
		String sql="";
		
		if(!waiterForm.isQuery()) {
			sql = "select * from waiter";
		}
		else {
			sql = "select * from waiter where waiter_Name like '%" + waiterForm.getWaiter_Name() + "%'";
		}
		
		QueryResult<Waiter> queryResult =wsi.select(sql);
		//查询到所有菜的类型总条数
		httpServletRequest.setAttribute("waiterCount",queryResult.getRecordCount());
		//回显 查询条件 主要有个query 属性 是否是查询
		httpServletRequest.setAttribute("waiterForm", waiterForm);
		 
		return SUCCESS;
	}
	
	/**
	 * 获取所有服务员信息列表。
	 * @return
	 */
	public String getWaiterList(){
		
		String sql = "";
		if(!waiterForm.isQuery()) {
			sql = "select top " + waiterForm.getPageSize() + " * " +
					"from waiter where id not in" +
					"(select top " + waiterForm.getPageIndex() * waiterForm.getPageSize() + " id from waiter)";
		}
		else {
			sql = "select top " + waiterForm.getPageSize() + " * " +
					"from waiter where id not in" +
					"(select top " + waiterForm.getPageIndex() * waiterForm.getPageSize() + " id from waiter " +
					"where id in (select id from waiter where waiter_Name like '%" + waiterForm.getWaiter_Name() + "%'))"
					+ "and id in (select id from waiter where waiter_Name like '%" + waiterForm.getWaiter_Name() + "%')";
		}
		
		
		//查询到的所有菜的信息
		QueryResult<Waiter> queryResult = wsi.select(sql);
		
		JSONArray json = JSONArray.fromObject(queryResult.getResultList());
		//System.out.println(json);
		try {
			httpServletResponse.getWriter().print(json.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 添加服务员时自动生成服务员的编号
	 * @return
	 */
	public String getWaiter_No() {
		String result = "";
		try {
			result = wsi.getWaiter_No();
			httpServletResponse.getWriter().print(result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 添加服务员
	 * @return
	 */
	public String addWaiter() {
		if(waiter != null) {
			waiter.setWaiter_Password("123456");
			int result = wsi.save(waiter);
			System.out.println(result + "*****");
			try {
				if(result>0){
					httpServletResponse.getWriter().print("新增服务员成功！");
				}
				else {
					httpServletResponse.getWriter().print("新增服务员失败！");
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return null;
	}
	
	/**
	 * 准备服务员更新页面
	 * @return
	 */
	public String updateWaiterPage() {
		Waiter updateWaiter = wsi.find(String.valueOf(waiter.getId()));
		httpServletRequest.setAttribute("updateWaiter", updateWaiter);
		return SUCCESS;
	}
	
	/**
	 * 更新服务员
	 * @return
	 */
	public String updateWaiter() {
		System.out.println("----------------");
		waiter.setWaiter_Password("123456");
		int result = wsi.update(waiter);
		if(result>0){
			try {
				httpServletResponse.getWriter().print("服务员信息更新成功！");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 *删除菜单类型信息 
	 */
	 
	public String deleteWaiter() {
		System.out.println("--------");
		int result = wsi.delete(waiterForm.getIdList());
		try {
			if(result > 0){
				httpServletResponse.getWriter().print("删除成功。");
			}else{
				httpServletResponse.getWriter().print("删除失败！");
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public void setServletRequest(HttpServletRequest httpServletRequest) {
		// TODO Auto-generated method stub
		this.httpServletRequest = httpServletRequest;
	}

	public void setServletResponse(HttpServletResponse httpServletResponse) {
		// TODO Auto-generated method stub
		this.httpServletResponse = httpServletResponse;
		httpServletResponse.setCharacterEncoding("UTF-8");
		httpServletResponse.setContentType("text/plain");
	}

	public WaiterForm getWaiterForm() {
		return waiterForm;
	}

	public void setWaiterForm(WaiterForm waiterForm) {
		this.waiterForm = waiterForm;
	}

	public Waiter getWaiter() {
		return waiter;
	}

	public void setWaiter(Waiter waiter) {
		this.waiter = waiter;
	}

	public String getGsonData() {
		return gsonData;
	}

	public void setGsonData(String gsonData) {
		this.gsonData = gsonData;
	}
	
}
