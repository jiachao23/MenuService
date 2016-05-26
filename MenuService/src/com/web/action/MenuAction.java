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
	 * 菜单信息管理页面 初始化  重要返回参数查询到的菜信息条数。
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
		//查询到所有菜的总条数
		httpServletRequest.setAttribute("menuCount",queryResult.getRecordCount());
		//回显 查询条件 主要有个query 属性 是否是查询
		httpServletRequest.setAttribute("menuForm", menuForm);
		 
		return SUCCESS;
	}
	
	/**
	 * 获取所有菜的信息列表。
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
		
		//查询到的所有菜的信息
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
	 * 获取所有菜类型的信息列表。
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
	 * 检查新添加的菜名是否存在
	 * @return
	 */
	public String menu_NameExist() {
		String result = "";
		try {
			if (msi.checkExist(menu.getMenu_Name())) {
				result = "此菜谱名称已经存在！";
			} else {
				result = "此菜谱名称可以添加！";
			}
			httpServletResponse.getWriter().print(result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 当添加菜时根据菜的种类生成菜的编号
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
	 * 添加菜
	 * @return
	 */
	public String addMenu() {
	
		if(menu != null) {
			
			int result = msi.save(menu);
			try {
				if(result>0){
					httpServletResponse.getWriter().print("新增菜成功！");
				}
				else {
					httpServletResponse.getWriter().print("新增菜失败！");
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
			
		return null;
	}
	
	/**
	 * 菜单信息更新 初始化页面
	 * @return
	 */
	public String updateMenuPage() {
		
		Menu updateMenu = msi.find(String.valueOf(menu.getId()));
		httpServletRequest.setAttribute("updateMenu", updateMenu);
		httpServletRequest.setAttribute("menuType_Name", menuType_Name);
		return SUCCESS;
	}
	
	/**
	 * 更新菜单信息
	 * @return
	 */
	public String updateMenu() {
		
		int result = msi.update(menu);
		if(result>0){
			try {
				httpServletResponse.getWriter().print("菜单信息更新成功！");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 *删除菜单信息 
	 */
	 
	public String deleteMenu() {
		int result = msi.delete(menuForm.getIdList());
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
	 * 上传图片
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
