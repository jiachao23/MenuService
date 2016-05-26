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
	 * 餐桌信息管理页面 初始化  重要返回参数查询到的餐桌信息条数。
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
		//查询到所有餐桌总条数
		httpServletRequest.setAttribute("tablesCount",queryResult.getRecordCount());
		//回显 查询条件 主要有个query 属性 是否是查询
		httpServletRequest.setAttribute("tablesForm", tablesForm);
		 
		return SUCCESS;
	}
	
	/**
	 * 获取所有餐桌信息列表。
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
		
		
		//查询到的所有菜的信息
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
	 * 获取所有餐桌信息列表,为初始化桌子类表准备数据。
	 * @return
	 */
	public String getTableList() {
		String sqlString = "select * from tables";
		//查询到的所有菜的信息
		QueryResult<Tables> queryResult = tsi.select(sqlString);
		httpServletRequest.setAttribute("tableList", queryResult.getResultList());
		return SUCCESS;
	}
	
	/**
	 * 自动生成餐桌编号
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
	 * 添加餐桌
	 * @return
	 */
	public String addTable() {
		if(tables != null) {
			
			int result = tsi.save(tables);
			try {
				if(result>0){
					httpServletResponse.getWriter().print("新增餐桌成功！");
				}
				else {
					httpServletResponse.getWriter().print("新增餐桌失败！");
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
			
		return null;
	}
	
	/**
	 * 准备餐桌更新页面
	 * @return
	 */
	public String updateTablePage() {
		Tables updateTable = tsi.find(String.valueOf(tables.getId()));
		httpServletRequest.setAttribute("updateTable", updateTable);
		return SUCCESS;
	}
	
	
	/**
	 * 更新餐桌信息
	 * @return
	 */
	public String updateTable() {
		int result = tsi.update(tables);
		if(result>0){
			try {
				httpServletResponse.getWriter().print("餐桌信息更新成功！");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 *删除餐桌信息
	 */
	 
	public String deleteTable() {
		int result = tsi.delete(tablesForm.getIdList());
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
	
	/**
	 *检查餐桌名称是否存在
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
