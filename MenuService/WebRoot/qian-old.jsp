<%@page import="java.util.*,com.menu.model.*"%>
<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>�ޱ����ĵ�</title>
<link rel="stylesheet" type="text/css" href="../css/index_1.css" />
<style type="text/css">
*{ margin:0px; padding:0px}

</style>
</head>

<body>
<!--�ⲿ�Ĵ��-->
<div class="index_all" style="text-align:center;">
  <!--����ı�����-->
  <div><img src="../css/images/flower.gif" /></div>
  <!--�м��-->
  <div id="index_center">
    <!--�м��Ŀհײ�-->
    <div id="space"><img src="../css/images/index_menu.gif" border="0" usemap="#Map" style="height: 68px; width: 312px; " /></div>
    
    <!--�м��Ĳ˵���-->
    <div>
      <!--�˵�������-->
      <div  id="index_centerleft"></div>
      
      <!--�˵�����м�-->
      <div class="bg_middle"  >
        
          <map name="Map" id="Map">
            <area shape="rect" coords="164,99,354,199" href="front/call_dish.html" />
        </map>
    </div>
      
      <!--�м����ұ�-->
      <div id="index_centerright"></div>
    </div>
   	
    <!--�����ӵĲ�-->
    <div  id="center_bottom" >
     <ul style=" display:inline-table">
     <%int i=0; Tables table;%>
     <% List<Tables> list = (List<Tables>)request.getAttribute("tableList");     
    	for(;i<list.size();i++)
    	{
	    	table = (Tables)list.get(i);
	    	%>   	    	  	
	    	<% if(i%5==0) {%>
	    	<br>
	    	<% } %>    	
	    	<li>
	    	<%if (table.getTable_Status()== 0){ %>	
	    		<a href="../orderItemForm/getOrderItemForm?order.table_No=<%=table.getTable_No()%>">
	    		<%=table.getTable_No()%></a>
	    	<%} %>
	    	</li>    	
	   <%}%>
  	   
  	    <!--  
        <li><a href="front/call_dish.jsp">1</a></li>
        <li><a href="front/call_dish.jsp">2</a></li>
        -->
      </ul>
    </div>

  </div>
  <!--����ı�����-->
  <div><img src="../css/images/flower.gif" /></div>
  </div>
</body>
</html>

