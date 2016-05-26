package com.menu.Service.imple;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.menu.Service.in.TablesServiceIn;
import com.menu.model.Tables;
import com.menu.util.JdbcUtils;

public class TablesServiceImpl extends BaseDaoImpl<Tables> implements TablesServiceIn{

	/**
	 * 当添加桌子时，自动生成桌子编号
	 * @return
	 */
	public String getTables_No() {
		Connection conn = JdbcUtils.getPoolConnection();
		String maxTable_No="";
		try {
			String sql = "select max(table_No) maxTable_No from tables";
			PreparedStatement st = JdbcUtils.prepareStatement(conn, sql);
			ResultSet rs = JdbcUtils.executeQuery(st);
			while (rs.next()) {
				maxTable_No = rs.getString("maxTable_No");
				if(maxTable_No != null && !maxTable_No.equals("")) {
					if(Integer.parseInt(maxTable_No)+ 1 > 9 && Integer.parseInt(maxTable_No)+ 1 < 100)
					{
						maxTable_No = "0" + (Integer.parseInt(maxTable_No)+ 1) ;
					}
					else if (Integer.parseInt(maxTable_No)+ 1 > 99){
						maxTable_No = (Integer.parseInt(maxTable_No)+ 1) + "";
					}
					else {
						maxTable_No = "00" + (Integer.parseInt(maxTable_No)+ 1) ;
					}
				}
				else {
					maxTable_No = "001";
				}
			}
			st.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.disConnect(conn);
		}
		return maxTable_No;
	}

	public boolean checkTable_NameExist(String table_Name) {
		Connection conn = JdbcUtils.getPoolConnection();
		int count = 0;
		try {
			String sql = "select *  from tables where table_Name='" + table_Name + "'";
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
