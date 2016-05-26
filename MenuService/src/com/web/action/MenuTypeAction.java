package com.web.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.menu.Service.imple.MenuServiceImpl;
import com.menu.Service.imple.MenuTypeServiceImpl;
import com.menu.Service.in.MenuTypeServiceIn;
import com.menu.model.MenuType;
import com.menu.model.QueryResult;
import com.menu.web.formBean.MenuTypeForm;
import com.opensymphony.xwork2.ActionSupport;

public class MenuTypeAction extends ActionSupport implements ServletResponseAware,ServletRequestAware{

	private HttpServletResponse httpServletResponse;
	private HttpServletRequest httpServletRequest;
	
	private MenuTypeServiceIn mtsi = new MenuTypeServiceImpl();
	
	private MenuType menuType;
	private MenuTypeForm menuTypeForm;
	
	
	/**
	 * 菜单类型信息管理页面 初始化  重要返回参数查询到的菜单类型信息条数。
	 * @return
	 */
	public String managePage(){	
		
		String sql="";
		
		if(!menuTypeForm.isQuery()) {
			sql = "select * from menuType";
		}
		else {
			sql = "select * from menuType where menuType_Name like '%" + menuTypeForm.getMenuType_Name() + "%'";
		}
		
		QueryResult<MenuType> queryResult =mtsi.select(sql);
		//查询到所有菜的类型总条数
		httpServletRequest.setAttribute("menuTypeCount",queryResult.getRecordCount());
		//回显 查询条件 主要有个query 属性 是否是查询
		httpServletRequest.setAttribute("menuTypeForm", menuTypeForm);
		 
		return SUCCESS;
	}
	
	/**
	 * 获取所有菜类型信息列表。
	 * @return
	 */
	public String getMenuTypeList() {
		String sql = "";
		if(!menuTypeForm.isQuery()) {
			sql = "select top " + menuTypeForm.getPageSize() + " * " +
					"from menuType where id not in" +
					"(select top " + menuTypeForm.getPageIndex() * menuTypeForm.getPageSize() + " id from menuType)";
		}
		else {
			sql = "select top " + menuTypeForm.getPageSize() + " * " +
					"from menuType where id not in" +
					"(select top " + menuTypeForm.getPageIndex() * menuTypeForm.getPageSize() + " id from menuType " +
					"where id in (select id from menuType where menuType_Name like '%" + menuTypeForm.getMenuType_Name() + "%'))"
					+ "and id in (select id from menuType where menuType_Name like '%" + menuTypeForm.getMenuType_Name() + "%')";
		}
		
		
		//查询到的所有菜的信息
		QueryResult<MenuType> queryResult = mtsi.select(sql);
		
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
	 * 自动生成类型编号
	 * @return
	 */
	public String getMenuType_No() {
		String result = "";
		try {
			result = mtsi.getMenuType_No();
			httpServletResponse.getWriter().print(result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 检查新添加的菜类型名称是否存在
	 * @return
	 */
	public String menuType_NameExist() {
		try {
			httpServletResponse.getWriter().print(mtsi.checkMenuType_NameExist(menuType.getMenuType_Name()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 添加菜谱类型
	 * @return
	 */
	public String addMenuType() {
		
		if(menuType != null && !"".equals(menuType.getMenuType_Name())) {
			
			int result = mtsi.save(menuType);
			try {
				if(result>0){
					httpServletResponse.getWriter().print("新增菜谱类型成功！");
				}
				else {
					httpServletResponse.getWriter().print("新增菜谱类型失败！");
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
			
		return null;
	}
	
	/**
	 * 准备菜单类型更新页面
	 * @return
	 */
	public String updateMenuTypePage() {
		MenuType updateMenuType = mtsi.find(String.valueOf(menuType.getId()));
		httpServletRequest.setAttribute("updateMenuType", updateMenuType);
		return SUCCESS;
	}
	
	/**
	 * 更新菜单类型
	 * @return
	 */
	public String updateMenuType() {
		if(!"".equals(menuType.getMenuType_Name()))
		{
			int result = mtsi.update(menuType);
			try {
				if (result > 0) {

					httpServletResponse.getWriter().print("菜谱类型信息更新成功！");

				} else {
					httpServletResponse.getWriter().print("菜谱类型信息更新失败！");
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		return null;
	}
	
	/**
	 *删除菜谱类型信息 
	 */
	 
	public String deleteMenuType() {
		int result = mtsi.delete(menuTypeForm.getIdList());
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
		
		this.httpServletRequest = httpServletRequest;
	}

	public void setServletResponse(HttpServletResponse httpServletResponse) {

		this.httpServletResponse = httpServletResponse;
		httpServletResponse.setCharacterEncoding("UTF-8");
		httpServletResponse.setContentType("text/plain");
	}

	public MenuType getMenuType() {
		return menuType;
	}

	public void setMenuType(MenuType menuType) {
		this.menuType = menuType;
	}

	public MenuTypeForm getMenuTypeForm() {
		return menuTypeForm;
	}

	public void setMenuTypeForm(MenuTypeForm menuTypeForm) {
		this.menuTypeForm = menuTypeForm;
	}

}
