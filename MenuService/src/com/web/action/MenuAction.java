package com.web.action;

import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;


import com.menu.Service.imple.MenuServiceImpl;
import com.menu.Service.imple.MenuTypeServiceImpl;
import com.menu.Service.in.MenuServiceIn;
import com.menu.Service.in.MenuTypeServiceIn;
import com.menu.model.Menu;
import com.menu.model.MenuType;
import com.menu.model.QueryResult;
import com.menu.web.formBean.MenuForm;
import com.opensymphony.xwork2.ActionSupport;

public class MenuAction extends ActionSupport implements ServletResponseAware,ServletRequestAware{

	private HttpServletResponse httpServletResponse;
	private HttpServletRequest httpServletRequest;
	
	private MenuServiceIn msi = new MenuServiceImpl();
	private MenuTypeServiceIn mtsi = new MenuTypeServiceImpl();
	
	private MenuForm menuForm;
	private String menuType_Name;
	
	private Menu menu;
	
	private File uploadImages;
	private String uploadImageFileName;
	private String uploauploadImageContentType;
	
	/**
	 * �˵���Ϣ����ҳ�� ��ʼ��  ��Ҫ���ز�����ѯ���Ĳ���Ϣ������
	 * @return
	 */
	public String managePage(){	
		
		String sql="";
		
		if(!menuForm.isQuery()) {
			sql = "select * from menu";
		}
		else {
			sql = "select * from menu where menu_Name like '%" + menuForm.getMenu_Name() + "%'";
		}
		
		QueryResult<Menu> queryResult =msi.select(sql);
		//��ѯ�����в˵�������
		httpServletRequest.setAttribute("menuCount",queryResult.getRecordCount());
		//���� ��ѯ���� ��Ҫ�и�query ���� �Ƿ��ǲ�ѯ
		httpServletRequest.setAttribute("menuForm", menuForm);
		 
		return SUCCESS;
	}
	
	/**
	 * ��ȡ���в˵���Ϣ�б�
	 * @return
	 */
	public String getMenuList() {
		
		String sql = "";
		
		if(!menuForm.isQuery()) {
			sql = "select top " + menuForm.getPageSize() + " * " +
					"from menu where id not in" +
					"(select top " + menuForm.getPageIndex() * menuForm.getPageSize() + " id from menu)";
		}
		else {
			sql = "select top " + menuForm.getPageSize() + " * " +
					"from menu where id not in" +
					"(select top " + menuForm.getPageIndex() * menuForm.getPageSize() + " id from menu " +
					"where id in (select id from menu where menu_Name like '%" + menuForm.getMenu_Name() + "%'))"
					+ "and id in (select id from menu where menu_Name like '%" + menuForm.getMenu_Name() + "%')";
			
		}
		
		System.out.println("********"+sql);
		
		//��ѯ�������в˵���Ϣ
		QueryResult<Menu> queryResult = msi.select(sql);
		ArrayList<MenuForm> alMenuFrom = new ArrayList<MenuForm>();
		
		for (Menu menu : queryResult.getResultList()) {
			
			MenuType menuType = mtsi.findMenuType(menu.getMenuType_No());
			
			MenuForm menuForm = new MenuForm();
			menuForm.setId(menu.getId());
			menuForm.setMenu_No(menu.getMenu_No());
			menuForm.setMenu_Name(menu.getMenu_Name());
			menuForm.setMenu_Price(menu.getMenu_Price());
			menuForm.setMenuType_Name(menuType.getMenuType_Name());
			
			alMenuFrom.add(menuForm);
		}
		
		JSONArray json = JSONArray.fromObject(alMenuFrom);
		try {
			httpServletResponse.getWriter().print(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * ��ȡ���в����͵���Ϣ�б�
	 * @return
	 */
	public String getMenuTypeList() {
		String sql = "select * from menuType";
		QueryResult<MenuType> queryResult = mtsi.select(sql);
		List<MenuType> menuTypeList = queryResult.getResultList();
		httpServletRequest.setAttribute("menuTypeList", menuTypeList);
		return SUCCESS;
	}
	
	/**
	 * �������ӵĲ����Ƿ����
	 * @return
	 */
	public String menu_NameExist() {
		String result = "";
		try {
			if (msi.checkExist(menu.getMenu_Name())) {
				result = "�˲��������Ѿ����ڣ�";
			} else {
				result = "�˲������ƿ�����ӣ�";
			}
			httpServletResponse.getWriter().print(result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * ����Ӳ�ʱ���ݲ˵��������ɲ˵ı��
	 * @return
	 */
	public String getMenu_No() {
		String result = "";
		try {
			result = msi.getMenu_NoByMenuType_No(menu.getMenuType_No());
			httpServletResponse.getWriter().print(result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * ��Ӳ�
	 * @return
	 */
	public String addMenu() {
	
		if(menu != null) {
			
			int result = msi.save(menu);
			try {
				if(result>0){
					httpServletResponse.getWriter().print("�����˳ɹ���");
				}
				else {
					httpServletResponse.getWriter().print("������ʧ�ܣ�");
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
			
		return null;
	}
	
	/**
	 * �˵���Ϣ���� ��ʼ��ҳ��
	 * @return
	 */
	public String updateMenuPage() {
		
		Menu updateMenu = msi.find(String.valueOf(menu.getId()));
		httpServletRequest.setAttribute("updateMenu", updateMenu);
		httpServletRequest.setAttribute("menuType_Name", menuType_Name);
		return SUCCESS;
	}
	
	/**
	 * ���²˵���Ϣ
	 * @return
	 */
	public String updateMenu() {
		
		int result = msi.update(menu);
		if(result>0){
			try {
				httpServletResponse.getWriter().print("�˵���Ϣ���³ɹ���");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 *ɾ���˵���Ϣ 
	 */
	 
	public String deleteMenu() {
		int result = msi.delete(menuForm.getIdList());
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
	 * �ϴ�ͼƬ
	 * @return
	 */
	public String uploadImage(){
		String realPath = ServletActionContext.getServletContext().getRealPath("/images/menuPicture");
		System.out.println(realPath);
		if(uploadImages != null){
			File saveFile = new File(new File(realPath),uploadImageFileName);
			if(!saveFile.getParentFile().exists()){
				saveFile.getParentFile().mkdirs();
			}
			try {
				FileUtils.copyFile(uploadImages, saveFile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return SUCCESS;
	}
	public void setServletRequest(HttpServletRequest httpServletRequest) {
		
		this.httpServletRequest = httpServletRequest;
	}

	public void setServletResponse(HttpServletResponse httpServletResponse) {

		this.httpServletResponse = httpServletResponse;
		httpServletResponse.setCharacterEncoding("UTF-8");
		httpServletResponse.setContentType("text/plain");
	}

	public MenuForm getMenuForm() {
		return menuForm;
	}

	public void setMenuForm(MenuForm menuForm) {
		this.menuForm = menuForm;
	}

	public String getMenuType_Name() {
		return menuType_Name;
	}

	public void setMenuType_Name(String menuType_Name) {
		this.menuType_Name = menuType_Name;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public File getUploadImages() {
		return uploadImages;
	}

	public void setUploadImages(File uploadImages) {
		this.uploadImages = uploadImages;
	}

	public String getUploadImageFileName() {
		return uploadImageFileName;
	}

	public void setUploadImageFileName(String uploadImageFileName) {
		this.uploadImageFileName = uploadImageFileName;
	}

	public String getUploauploadImageContentType() {
		return uploauploadImageContentType;
	}

	public void setUploauploadImageContentType(String uploauploadImageContentType) {
		this.uploauploadImageContentType = uploauploadImageContentType;
	}

}
