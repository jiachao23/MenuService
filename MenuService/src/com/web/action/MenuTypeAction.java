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
	 * �˵�������Ϣ����ҳ�� ��ʼ��  ��Ҫ���ز�����ѯ���Ĳ˵�������Ϣ������
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
		//��ѯ�����в˵�����������
		httpServletRequest.setAttribute("menuTypeCount",queryResult.getRecordCount());
		//���� ��ѯ���� ��Ҫ�и�query ���� �Ƿ��ǲ�ѯ
		httpServletRequest.setAttribute("menuTypeForm", menuTypeForm);
		 
		return SUCCESS;
	}
	
	/**
	 * ��ȡ���в�������Ϣ�б�
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
		
		
		//��ѯ�������в˵���Ϣ
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
	 * �Զ��������ͱ��
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
	 * �������ӵĲ����������Ƿ����
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
	 * ��Ӳ�������
	 * @return
	 */
	public String addMenuType() {
		
		if(menuType != null && !"".equals(menuType.getMenuType_Name())) {
			
			int result = mtsi.save(menuType);
			try {
				if(result>0){
					httpServletResponse.getWriter().print("�����������ͳɹ���");
				}
				else {
					httpServletResponse.getWriter().print("������������ʧ�ܣ�");
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
			
		return null;
	}
	
	/**
	 * ׼���˵����͸���ҳ��
	 * @return
	 */
	public String updateMenuTypePage() {
		MenuType updateMenuType = mtsi.find(String.valueOf(menuType.getId()));
		httpServletRequest.setAttribute("updateMenuType", updateMenuType);
		return SUCCESS;
	}
	
	/**
	 * ���²˵�����
	 * @return
	 */
	public String updateMenuType() {
		if(!"".equals(menuType.getMenuType_Name()))
		{
			int result = mtsi.update(menuType);
			try {
				if (result > 0) {

					httpServletResponse.getWriter().print("����������Ϣ���³ɹ���");

				} else {
					httpServletResponse.getWriter().print("����������Ϣ����ʧ�ܣ�");
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		return null;
	}
	
	/**
	 *ɾ������������Ϣ 
	 */
	 
	public String deleteMenuType() {
		int result = mtsi.delete(menuTypeForm.getIdList());
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
