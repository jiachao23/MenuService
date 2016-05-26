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
	 * ����Ա��¼����Ա��
	 * @return
	 */
	public String waiterLogin(){
		CheckWaiterThread collectWaiterThread = new CheckWaiterThread();
		collectWaiterThread.start();
		return null;
	}
	
	/**
	 * ����gsonData���ݷ�װ�ɶ���
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
		 * ���ַ������ݽ�����List<Menu>����
		 * @param gsonDataҪ����������
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
	 * �˵�������Ϣ����ҳ�� ��ʼ��  ��Ҫ���ز�����ѯ���Ĳ˵�������Ϣ������
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
		//��ѯ�����в˵�����������
		httpServletRequest.setAttribute("waiterCount",queryResult.getRecordCount());
		//���� ��ѯ���� ��Ҫ�и�query ���� �Ƿ��ǲ�ѯ
		httpServletRequest.setAttribute("waiterForm", waiterForm);
		 
		return SUCCESS;
	}
	
	/**
	 * ��ȡ���з���Ա��Ϣ�б�
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
		
		
		//��ѯ�������в˵���Ϣ
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
	 * ��ӷ���Աʱ�Զ����ɷ���Ա�ı��
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
	 * ��ӷ���Ա
	 * @return
	 */
	public String addWaiter() {
		if(waiter != null) {
			waiter.setWaiter_Password("123456");
			int result = wsi.save(waiter);
			System.out.println(result + "*****");
			try {
				if(result>0){
					httpServletResponse.getWriter().print("��������Ա�ɹ���");
				}
				else {
					httpServletResponse.getWriter().print("��������Աʧ�ܣ�");
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return null;
	}
	
	/**
	 * ׼������Ա����ҳ��
	 * @return
	 */
	public String updateWaiterPage() {
		Waiter updateWaiter = wsi.find(String.valueOf(waiter.getId()));
		httpServletRequest.setAttribute("updateWaiter", updateWaiter);
		return SUCCESS;
	}
	
	/**
	 * ���·���Ա
	 * @return
	 */
	public String updateWaiter() {
		System.out.println("----------------");
		waiter.setWaiter_Password("123456");
		int result = wsi.update(waiter);
		if(result>0){
			try {
				httpServletResponse.getWriter().print("����Ա��Ϣ���³ɹ���");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 *ɾ���˵�������Ϣ 
	 */
	 
	public String deleteWaiter() {
		System.out.println("--------");
		int result = wsi.delete(waiterForm.getIdList());
		try {
			if(result > 0){
				httpServletResponse.getWriter().print("ɾ���ɹ���");
			}else{
				httpServletResponse.getWriter().print("ɾ��ʧ�ܣ�");
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
