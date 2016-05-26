package com.web.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.menu.Service.imple.AdminServiceImpl;
import com.menu.Service.in.AdminServiceIn;
import com.menu.model.AdminUser;
import com.opensymphony.xwork2.ActionSupport;

public class AdminAction extends ActionSupport implements ServletResponseAware,ServletRequestAware{

	private HttpServletResponse httpServletResponse;
	private HttpServletRequest httpServletRequest;
	private HttpSession httpSession;
	
	private AdminUser adminUser;
	
	private AdminServiceIn asi = new AdminServiceImpl();
	
	/**
	 * 系统管理员登陆验证
	 * @return
	 * @throws Exception
	 */
	public String checklogin() {
		
		System.out.println(adminUser.getUsername()+"__"+adminUser.getPassword());
		
		if(asi.checkUser(adminUser.getUsername(), adminUser.getPassword())) {
			//登陆验证成功 则将用户的信息存入session
			httpSession.setAttribute("adminuser", asi.findUser(adminUser.getUsername()));
			return SUCCESS;
		}
		else {
			try {
				httpServletResponse.setCharacterEncoding("UTF-8");
				httpServletResponse.getWriter().print("账号或密码错误！");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
			
		return null;
	}
	
	public String exitSystem(){
		httpSession.removeAttribute("adminuser");
		return SUCCESS;
	}
	public void setServletRequest(HttpServletRequest httpServletRequest) {
		
		this.httpServletRequest = httpServletRequest;
		this.httpSession = httpServletRequest.getSession();
	}

	public void setServletResponse(HttpServletResponse httpServletResponse) {
		// TODO Auto-generated method stub
		this.httpServletResponse = httpServletResponse;
	}

	public AdminUser getAdminUser() {
		return adminUser;
	}

	public void setAdminUser(AdminUser adminUser) {
		this.adminUser = adminUser;
	}

}
