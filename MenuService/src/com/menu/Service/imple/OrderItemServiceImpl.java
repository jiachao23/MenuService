package com.menu.Service.imple;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.menu.Service.in.OrderItemServiceIn;
import com.menu.model.Menu;
import com.menu.model.Orders;
import com.menu.model.OrderItem;
import com.menu.util.DaoTool;
import com.menu.util.JdbcUtils;
import com.menu.web.formBean.OrderItemForm;

public class OrderItemServiceImpl extends BaseDaoImpl<OrderItem> implements OrderItemServiceIn{
	
	/*public void updateOrderItemByOrder_No(Orders orders, String gsonData) {
		
		//删除orderId对应的旧orderItem
		List<OrderItemForm> orderItemForms = getOrderItemForms(orders);
		for (OrderItemForm item : orderItemForms) {
			oisi.delete(String.valueOf(item.getId()));
		}
		
		//添加orderId对应的新orderItem
		updateOrderItem(gsonData);
		
	}

	//更新orderItem
	public void updateOrderItem(String gsonData){
		System.out.print("gsonData="+gsonData);
		Type ListType = new TypeToken<ArrayList<OrderItem>>(){}.getType();
		Gson gson = new Gson();
		ArrayList<OrderItem> alOrderItem = gson.fromJson(gsonData, ListType);
		for (OrderItem orderItem : alOrderItem) {
			oisi.save(orderItem);//保存到数据库
		}
	}*/
	
	//根据order_No得到对应的所有OrderItem
	public List<OrderItemForm> getOrderItemForms(Orders order){
		
		List<OrderItemForm> orderItemFormList = new ArrayList<OrderItemForm>();
		Connection conn = JdbcUtils.getPoolConnection();
		
		try {
			String sqlString = "select order_No from [menuDB].[dbo].[orders] where table_No='"+order.getTable_No()+"' and order_Status='"+order.getOrder_Status()+"'";
			PreparedStatement st = JdbcUtils.prepareStatement(conn, sqlString);
			ResultSet rs = JdbcUtils.executeQuery(st);
			if (rs.next()) {
				sqlString = "select * from orderItem where order_No='"+ rs.getString("order_No")+"'";
				st = JdbcUtils.prepareStatement(conn, sqlString);
				rs = JdbcUtils.executeQuery(st);
				while (rs.next()) {
					OrderItemForm orderItemForm = new OrderItemForm();
					orderItemForm.setMenu_No(rs.getString("menu_No"));
					orderItemForm.setMenu_Num(rs.getString("menu_Num"));
					orderItemForm.setRemark(rs.getString("remark"));
					orderItemForm.setOrder_No(rs.getString("order_No"));
					orderItemForm.setDiscount(rs.getString("discount"));
					orderItemForm.setOrderItem_Id(rs.getString("id"));
					sqlString = "select * from menu where menu_no='"+ rs.getString("menu_No")+ "'";
					Menu menu = (Menu)DaoTool.returnSetDetailsToObject(sqlString, new Menu());
					orderItemForm.setMenu_Name(menu.getMenu_Name());
					orderItemForm.setMenu_price(menu.getMenu_Price());
					orderItemFormList.add(orderItemForm);
				}			
			}
			st.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			JdbcUtils.disConnect(conn);
		}
		return orderItemFormList;
	}

}
