<%@page import="com.menu.model.*,com.menu.web.formBean.*"%>
<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>菜谱</title>
<link rel="stylesheet" type="text/css" href="../css/index.css" />
</head>

<body>
<div id="all">
 <!--左边-->
 <div id="menu">
 <div id="count">
  <form id="form1" name="form1" method="post">
    <table width="740" height="530">
    <tr height="40">
           <td>编号</td>
           <td>菜名</td>
           <td>价格</td>
           <td>份数</td>
           <td>删除选择?</td>
         </tr>
    <%List<OrderItemForm> list = (List<OrderItemForm>)request.getAttribute("orderItemFormList");   
     int i=0;
    for(;i<list.size();i++) {
    OrderItemForm item = (OrderItemForm)list.get(i);
    %>
    <tr>
    	<td><%=i %></td>
    	<td><%=item.getMenu_Name() %></td>
    	<td><%=item.getMenu_price()%></td>
    	<td><%=item.getMenu_Num() %></td>
    	<td><input type='checkbox' name='deleteOption' >是</td>
    </tr>
    <%}%>
   
     <tr> <td colspan="6"  id="count" >折扣率：<input  type="text" name='zhekoulv' ></td></tr>
     <tr>
        <td colspan="6" id="count" >总计:<label id="count" >&yen;270</label></td>
     </tr>  
     <tr>
        <td colspan="6">
          <a href="counter.jsp">结账</a>
          <a href="javascript:history.go(-1);">返回</a>
        </td>
     </tr>
    </table>
  </form>
 </div>
 </div>
 <!--右边-->
 <div id="dish_class">
  <div id="dish_top">
    <ul>
      <li class="dish_num">1</li>
      <li><a href="counter.jsp"><img src="../css/images/jiezhang.png"/></a></li>
      <li><a href="javascript:history.go(-1);"><img src="../css/images/fanhui.png"/>返回</a></li>
    </ul>
  </div>
  
  
 </div>
</div>
</body>
</html>
