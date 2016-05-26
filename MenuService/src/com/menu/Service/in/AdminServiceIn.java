package com.menu.Service.in;

import com.menu.model.AdminUser;
import com.menu.web.formBean.AdminUserForm;


/**
 * ����Ա��Ϣ����
 * @author kkwp@163.com
 *
 */
public interface AdminServiceIn extends BaseDaoIn<AdminUser> {

	/**
	 * �û���½ ��Ϣ���
	 * @param ueername
	 * @param password
	 * @return
	 */
	boolean checkUser(String username, String password);

	/**
	 * ���Ҫ��ӵ��û��Ƿ��Ѿ�����
	 * @param username
	 * @return
	 */
	boolean checkExist(String username);
	
	/**
	 * �����û���Ϣ �ŵ� session ����
	 * @param username
	 * @return
	 */
	public AdminUser findUser(String username);
	
	/**
	 * �޸�����
	 * @param adminUserFrom
	 * @return
	 */
	public String updatePwd(AdminUserForm adminUserFrom);
	
}
