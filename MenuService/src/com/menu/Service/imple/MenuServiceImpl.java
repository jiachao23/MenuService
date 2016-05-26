package com.menu.Service.imple;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.menu.Service.in.MenuServiceIn;
import com.menu.model.Menu;
import com.menu.util.JdbcUtils;

public class MenuServiceImpl extends BaseDaoImpl<Menu> implements MenuServiceIn {

	/**
	 * 添加菜名时，检测菜名是否已经存在
	 * @return
	 */
	public boolean checkExist(String menu_Name) {
		Connection conn = JdbcUtils.getPoolConnection();
		int count = 0;
		try {
			String sql = "select *  from menu where menu_Name='" + menu_Name + "'";
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
	 * 根据菜的类型获取菜的编号
	 */
	public String getMenu_NoByMenuType_No(String menuType_No) {
		Connection conn = JdbcUtils.getPoolConnection();
		String maxMenu_No="";
		try {
			String sql = "select max(menu_No) maxMenu_No from menu where menuType_No='" + menuType_No + "'";
			PreparedStatement st = JdbcUtils.prepareStatement(conn, sql);
			ResultSet rs = JdbcUtils.executeQuery(st);
			while (rs.next()) {
				maxMenu_No = rs.getString("maxMenu_No");
				if(maxMenu_No != null && maxMenu_No.endsWith("")) {
					if(Integer.parseInt(maxMenu_No)+ 1 > 9999){
						maxMenu_No = (Integer.parseInt(maxMenu_No)+ 1) + "";
					}
					else {
						maxMenu_No = "0" + (Integer.parseInt(maxMenu_No)+ 1);
					}
				}
				else {
					maxMenu_No = menuType_No + "001";
				}
			}
			st.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.disConnect(conn);
		}
		return maxMenu_No;
	}

	@Override
	public String getMenuNameById(String MenuId) {
		// TODO Auto-generated method stub
		Connection conn = JdbcUtils.getPoolConnection();
		try {
			String sql = "select menu_Name from menu where menu_No='" + MenuId + "'";
			PreparedStatement st = JdbcUtils.prepareStatement(conn, sql);
			ResultSet rs = JdbcUtils.executeQuery(st);
			if (rs.next()) {
				return rs.getString("menu_Name");
			}
			st.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.disConnect(conn);
		}
		return null;
	}

}
