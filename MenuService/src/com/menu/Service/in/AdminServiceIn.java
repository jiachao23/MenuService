package com.menu.Service.in;

import com.menu.model.AdminUser;
import com.menu.web.formBean.AdminUserForm;


/**
 * 管理员信息管理
 * @author kkwp@163.com
 *
 */
public interface AdminServiceIn extends BaseDaoIn<AdminUser> {

	/**
	 * 用户登陆 信息检查
	 * @param ueername
	 * @param password
	 * @return
	 */
	boolean checkUser(String username, String password);

	/**
	 * 检查要添加的用户是否已经存在
	 * @param username
	 * @return
	 */
	boolean checkExist(String username);
	
	/**
	 * 查找用户信息 放到 session 中用
	 * @param username
	 * @return
	 */
	public AdminUser findUser(String username);
	
	/**
	 * 修改密码
	 * @param adminUserFrom
	 * @return
	 */
	public String updatePwd(AdminUserForm adminUserFrom);
	
}
