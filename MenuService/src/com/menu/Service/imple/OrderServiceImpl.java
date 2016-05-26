package com.menu.Service.imple;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.menu.Service.in.OrderServiceIn;
import com.menu.Service.in.TablesServiceIn;
import com.menu.model.Orders;
import com.menu.model.Tables;
import com.menu.util.DaoTool;
import com.menu.util.JdbcUtils;

public class OrderServiceImpl extends BaseDaoImpl<Orders> implements OrderServiceIn{

	//根据订单中的桌子号更新桌子的状态
	public void setTableStatus(Orders orders) {
		int status;
		String sqlString = "select * from tables where table_No='" + orders.getTable_No() + "'";
		Tables table = (Tables)DaoTool.returnSetDetailsToObject(sqlString, new Tables());
		status = orders.getOrder_Status() == 0 ? 1:0;
		table.setTable_Status(status);
			
		TablesServiceIn tsi = new TablesServiceImpl();
		tsi.update(table);
			
	}

	//根据订单号查询该订单是否存在
	public boolean getOrderExist(Orders orders) {
		Connection conn = JdbcUtils.getPoolConnection();
		int count = 0;
		try {
			String sql = "select * from orders where order_No='" + orders.getOrder_No() + "'";
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

	@Override
	// xu add at 2014-8-8 
	// 根据id号获取Orders实体
	public Orders getOrdersById(String id) {
		// TODO Auto-generated method stub
		Connection conn = JdbcUtils.getPoolConnection();
		Orders od = new Orders();
		try {
			String sql = "select * from orders where order_No='" + id + "'";
			PreparedStatement st = JdbcUtils.prepareStatement(conn, sql);
			ResultSet rs = JdbcUtils.executeQuery(st);
			while (rs.next()) {
				od.setOrder_No(rs.getString("order_No"));
				od.setPersonNum(rs.getInt("personNum"));
				od.setTable_No(rs.getString("table_No"));
				od.setWaiter_No(rs.getString("waiter_No"));
				od.setOrder_Time(rs.getString("order_Time"));
			}
			st.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			JdbcUtils.disConnect(conn);
		}
		return od;
	}	
}
