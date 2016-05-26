package com.menu.Service.imple;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.menu.Service.in.WaiterServiceIn;
import com.menu.model.Waiter;
import com.menu.util.JdbcUtils;

public class WaiterServiceImpl extends BaseDaoImpl<Waiter> implements WaiterServiceIn{

	/**
	 * 当添加服务员时，自动生成服务员编号
	 * @return
	 */
	public String getWaiter_No() {
		Connection conn = JdbcUtils.getPoolConnection();
		String maxWaiter_No="";
		try {
			String sql = "select max(waiter_No) maxWaiter_No from waiter";
			PreparedStatement st = JdbcUtils.prepareStatement(conn, sql);
			ResultSet rs = JdbcUtils.executeQuery(st);
			while (rs.next()) {
				maxWaiter_No = rs.getString("maxWaiter_No");
				if(maxWaiter_No != null && !maxWaiter_No.equals("")) {
					maxWaiter_No = (Integer.parseInt(maxWaiter_No)+ 1) + "";
				}
				else {
					maxWaiter_No = "1001";
				}
			}
			st.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.disConnect(conn);
		}
		return maxWaiter_No;
	}

	/**
	 * 检查服务员的合法性
	 * @param waiter
	 * @return
	 */
	public boolean checkWaiter(Waiter waiter) {
		Connection conn = JdbcUtils.getPoolConnection();
		long count = 0;
		try {
			String sql = "select *  from waiter where waiter_Name='"+waiter.getWaiter_Name()+"' and waiter_Password='"+waiter.getWaiter_Password()+"'";
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
