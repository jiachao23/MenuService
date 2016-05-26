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
	 * 通过菜谱的类型编号，返回整个MenuType对象(添加菜谱时用到)
	 */
	public MenuType findMenuType(String menuType_No) {
		String sql="select * from menuType where menuType_No='" + menuType_No + "'";
		return (MenuType)DaoTool.returnSetDetailsToObject(sql, new MenuType());
	}

	/**
	 * 当添加菜谱的类型时，自动生成菜谱类型编号
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
					if(Integer.parseInt(maxMenuType_No) + 1 > 9 && Integer.parseInt(maxMenuType_No) + 1 < 99)//当菜谱类型数目大于9小于99时，菜谱类型编号加1
					{
						maxMenuType_No = (Integer.parseInt(maxMenuType_No)+ 1) + "";
					}
					else {//当菜类型数据小于9时，前面一位补0
						maxMenuType_No = "0" + (Integer.parseInt(maxMenuType_No)+ 1);
					}
				}
				else {//菜类型记录为空时
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
	 *  添加菜谱类型名时，检测菜谱类型名是否已经存在
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
