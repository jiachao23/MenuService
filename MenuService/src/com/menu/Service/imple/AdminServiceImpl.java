package com.menu.Service.imple;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.menu.Service.in.AdminServiceIn;
import com.menu.model.AdminUser;
import com.menu.util.DaoTool;
import com.menu.util.JdbcUtils;
import com.menu.web.formBean.AdminUserForm;

public class AdminServiceImpl extends BaseDaoImpl<AdminUser> implements AdminServiceIn{

	/**
	 * �û���½ ��Ϣ���
	 * @param ueername
	 * @param password
	 * @return
	 */
	public boolean checkUser(String username, String password) {
		Connection conn = JdbcUtils.getPoolConnection();
		//MD5Digest md5Digest = new MD5Digest();
		long count = 0;
		try {
			String sql = "select *  from adminuser where username='"+username+"' and password='"+password+"'";
			PreparedStatement st = JdbcUtils.prepareStatement(conn, sql);
			ResultSet rs = JdbcUtils.executeQuery(st);
			while (rs.next()) {				
				count++;
			}
			st.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.disConnect(conn);
		}
		return count > 0;
	}

	/**
	 * ���Ҫ��ӵ��û��Ƿ��Ѿ�����
	 * @param username
	 * @return
	 */
	public boolean checkExist(String username) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * �����û���Ϣ �ŵ� session ����
	 * @param username
	 * @return
	 */
	public AdminUser findUser(String username) {
		String sql="select * from adminuser where username='"+username+"'";
		return (AdminUser)DaoTool.returnSetDetailsToObject(sql, new AdminUser());
	}

	/**
	 * �޸�����
	 * @param adminUserFrom
	 * @return
	 */
	public String updatePwd(AdminUserForm adminUserFrom) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
