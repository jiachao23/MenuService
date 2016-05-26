package com.menu.Service.imple;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.menu.Service.in.MenuTypeServiceIn;
import com.menu.model.MenuType;
import com.menu.util.DaoTool;
import com.menu.util.JdbcUtils;

public class MenuTypeServiceImpl extends BaseDaoImpl<MenuType> implements MenuTypeServiceIn{

	/**
	 * ͨ�����׵����ͱ�ţ���������MenuType����(��Ӳ���ʱ�õ�)
	 */
	public MenuType findMenuType(String menuType_No) {
		String sql="select * from menuType where menuType_No='" + menuType_No + "'";
		return (MenuType)DaoTool.returnSetDetailsToObject(sql, new MenuType());
	}

	/**
	 * ����Ӳ��׵�����ʱ���Զ����ɲ������ͱ��
	 * @return
	 */
	public String getMenuType_No() {
		Connection conn = JdbcUtils.getPoolConnection();
		String maxMenuType_No="";
		try {
			String sql = "select max(menuType_No) maxMenuType_No from menuType";
			PreparedStatement st = JdbcUtils.prepareStatement(conn, sql);
			ResultSet rs = JdbcUtils.executeQuery(st);
			while (rs.next()) {
				maxMenuType_No = rs.getString("maxMenuType_No");
				if(maxMenuType_No != null && !maxMenuType_No.equals("")) {
					if(Integer.parseInt(maxMenuType_No) + 1 > 9 && Integer.parseInt(maxMenuType_No) + 1 < 99)//������������Ŀ����9С��99ʱ���������ͱ�ż�1
					{
						maxMenuType_No = (Integer.parseInt(maxMenuType_No)+ 1) + "";
					}
					else {//������������С��9ʱ��ǰ��һλ��0
						maxMenuType_No = "0" + (Integer.parseInt(maxMenuType_No)+ 1);
					}
				}
				else {//�����ͼ�¼Ϊ��ʱ
					maxMenuType_No = "01";
				}
			}
			st.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.disConnect(conn);
		}
		return maxMenuType_No;
	}

	/**
	 *  ��Ӳ���������ʱ���������������Ƿ��Ѿ�����
	 * @return
	 */
	public boolean checkMenuType_NameExist(String menuType_Name) {
		Connection conn = JdbcUtils.getPoolConnection();
		int count = 0;
		try {
			String sql = "select *  from menuType where menuType_Name='" + menuType_Name + "'";
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

	
}
