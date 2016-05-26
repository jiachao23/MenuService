<%@page import="java.util.*,com.menu.model.*"%>
<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head >
<meta http-equiv="refresh" content="60" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>点菜系统主页</title>
<link rel="stylesheet" type="text/css" href="../css/menu.css" />
<link rel="stylesheet" type="text/css" href="../css/index_1.css" />
</head>
<body>
<center>
<table >
<tr id='tr_top'>
  <td></td></tr>
<tr id='tr_middle'>
  <td> 
  <div id='center_bottom'>
   <%int i=0; Tables table;%>
     <% List<Tables> list = (List<Tables>)request.getAttribute("tableList");   
    	for(;i<list.size();i++)
    	{
	    	table = (Tables)list.get(i);
	    	%>   	    	  	
	    	<% if(i%10==0) {%>  
	    	<br/>  	
	    	<% } %>    	    	
	    	<%if (table.getTable_Status()== 0){ %>	
	    		<img style="margin-bottom:-6px" height="50" width="50" src="../images/desk_idle.png" border=0 align="top"/>
	    		<%=table.getTable_No()%>
	    	<%}else if(table.getTable_Status()== 1){ %>	
	    		<a href="../orderItem/getOrderItemForm?order.table_No=<%=table.getTable_No()%>"><img height="50" width="50" src="../images/desk_busy.png" border=0  />
	    		<%=table.getTable_No()%></a>
	    	<%} %>    	   	
	   <%}%>
	   </div>
	   </td></tr>
<tr id='tr_bottom'>
  <td></td></tr>
<tr></tr>
</table>

<a href="../index.jsp" target="_blank"><span style="font-size:14px;">后台管理</span></a>
</center>
</body>
</html>

