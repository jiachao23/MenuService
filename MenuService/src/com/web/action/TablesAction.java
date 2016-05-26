package com.web.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;


import com.menu.Service.imple.TablesServiceImpl;
import com.menu.Service.in.TablesServiceIn;
import com.menu.model.QueryResult;
import com.menu.model.Tables;
import com.menu.web.formBean.TablesForm;
import com.opensymphony.xwork2.ActionSupport;

public class TablesAction extends ActionSupport implements ServletResponseAware,ServletRequestAware{

	private HttpServletResponse httpServletResponse;
	private HttpServletRequest httpServletRequest;
	
	private TablesServiceIn tsi = new TablesServiceImpl();
	
	private Tables tables;
	private TablesForm tablesForm;
	
	
	/**
	 * ������Ϣ����ҳ�� ��ʼ��  ��Ҫ���ز�����ѯ���Ĳ�����Ϣ������
	 * @return
	 */
	public String managePage(){	
		
		String sql="";
		
		if(!tablesForm.isQuery()) {
			sql = "select * from tables";
		}
		else {
			sql = "select * from tables where table_No like '%" + tablesForm.getTable_No() + "%'";
		}
		
		QueryResult<Tables> queryResult =tsi.select(sql);
		//��ѯ�����в���������
		httpServletRequest.setAttribute("tablesCount",queryResult.getRecordCount());
		//���� ��ѯ���� ��Ҫ�и�query ���� �Ƿ��ǲ�ѯ
		httpServletRequest.setAttribute("tablesForm", tablesForm);
		 
		return SUCCESS;
	}
	
	/**
	 * ��ȡ���в�����Ϣ�б�
	 * @return
	 */
	public String getTablesList() {
		String sql = "";
		if(!tablesForm.isQuery()) {
			sql = "select top " + tablesForm.getPageSize() + " * " +
					"from tables where id not in" +
					"(select top " + tablesForm.getPageIndex() * tablesForm.getPageSize() + " id from tables)";
		}
		else {
			sql = "select top " + tablesForm.getPageSize() + " * " +
					"from tables where id not in" +
					"(select top " + tablesForm.getPageIndex() * tablesForm.getPageSize() + " id from tables " +
					"where id in (select id from tables where table_No like '%" + tablesForm.getTable_No() + "%'))"
					+ "and id in (select id from tables where table_No like '%" + tablesForm.getTable_No() + "%')";
		}
		
		
		//��ѯ�������в˵���Ϣ
		QueryResult<Tables> queryResult = tsi.select(sql);
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
	 * ��ȡ���в�����Ϣ�б�,Ϊ��ʼ���������׼�����ݡ�
	 * @return
	 */
	public String getTableList() {
		String sqlString = "select * from tables";
		//��ѯ�������в˵���Ϣ
		QueryResult<Tables> queryResult = tsi.select(sqlString);
		httpServletRequest.setAttribute("tableList", queryResult.getResultList());
		return SUCCESS;
	}
	
	/**
	 * �Զ����ɲ������
	 * @return
	 */
	public String getTable_No() {
		String result = "";
		try {
			result = tsi.getTables_No();
			httpServletResponse.getWriter().print(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * ��Ӳ���
	 * @return
	 */
	public String addTable() {
		if(tables != null) {
			
			int result = tsi.save(tables);
			try {
				if(result>0){
					httpServletResponse.getWriter().print("���������ɹ���");
				}
				else {
					httpServletResponse.getWriter().print("��������ʧ�ܣ�");
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
			
		return null;
	}
	
	/**
	 * ׼����������ҳ��
	 * @return
	 */
	public String updateTablePage() {
		Tables updateTable = tsi.find(String.valueOf(tables.getId()));
		httpServletRequest.setAttribute("updateTable", updateTable);
		return SUCCESS;
	}
	
	
	/**
	 * ���²�����Ϣ
	 * @return
	 */
	public String updateTable() {
		int result = tsi.update(tables);
		if(result>0){
			try {
				httpServletResponse.getWriter().print("������Ϣ���³ɹ���");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 *ɾ��������Ϣ
	 */
	 
	public String deleteTable() {
		int result = tsi.delete(tablesForm.getIdList());
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
	
	/**
	 *�����������Ƿ����
	 */
	 
	public String checkTable_NameExist() {
		try {
			httpServletResponse.getWriter().print(tsi.checkTable_NameExist(tables.getTable_Name()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

	public Tables getTables() {
		return tables;
	}

	public void setTables(Tables tables) {
		this.tables = tables;
	}

	public TablesForm getTablesForm() {
		return tablesForm;
	}

	public void setTablesForm(TablesForm tablesForm) {
		this.tablesForm = tablesForm;
	}

	
}
