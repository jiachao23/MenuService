<%@page import="com.menu.model.*,com.menu.web.formBean.*,utils.*"%>
<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<script language="javascript" src="../js/LodopFuncs.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>结账页面</title>
</head>
<% 
 /* 判断是否第一次加载页面 */
 Boolean firstVisit = (Boolean)application.getAttribute("firstVisit");
 if(firstVisit==null) {  System.out.println("这是第一次访问index.jsp页面...");  
 application.setAttribute("firstVisit", false);} 
 else {  System.out.println("非第一次访问...");
 }
/*  获取桌号 */
 String tableid = (String)request.getParameter("order.table_No");
 String sum_price =(String)request.getAttribute("sum_price");
 /* 获取桌单参数对象 */
 List<OrderItemForm> list = (List<OrderItemForm>)request.getAttribute("orderItemFormList");
 PrinterParas paras = new PrinterParas();
%>
<body>
<center>

<a href="javascript:prn1_print()">打印</a>
<a href="javascript:prn1_preview()">关闭页面</a>
   <div id="count">
     <form id="form1" name="form1" method="post">
     <h4>桌单详情-结账联</h4>
        <table width="600px"> 
       <tr> <td colspan=4><hr width="600px"/></td></tr>       
       <tr><td colspan=2>台号：<%=tableid%></td><td colspan=2>人数：</td> </tr>
       <tr><td colspan=2>单号：<%=list.isEmpty()?"":list.get(0).getOrder_No() %></td><td colspan=2>服务员：</td> </tr>
       <tr><td colspan=4>流水号：</td></tr> 
       <tr><td colspan=4>打印时间：<%=new Date().toLocaleString()%></td></tr>
       <tr> <td colspan=4><hr width="600px"/></td></tr>
           <td width=10% aligh=center>编号</td>
           <td width=40% aligh=left>菜名</td>
           <td width=25% aligh=left>价格</td>
           <td width=25% aligh=left>份数</td>        
         </tr>
    <%/* List<OrderItemForm> list = (List<OrderItemForm>)request.getAttribute("orderItemFormList");  */  
    int i=0; 
    String[][] menus = new String[list.size()][];
    
     /* 初始化xp-80c打印机参数结构体 */	
    for(;i<list.size();i++) {
    OrderItemForm item = (OrderItemForm)list.get(i);
    /* 填充菜品详情 */
    menus[i] = new String[3];
    menus[i][0] = item.getMenu_Name();
    menus[i][1] = item.getMenu_Num();
    menus[i][2] = String.valueOf(item.getMenu_price());
    %>
    <tr>
    	<td width=10% aligh=center><%=i %></td>
    	<td width=40% aligh=left><%=item.getMenu_Name() %></td>
    	<td width=25% aligh=left><%=item.getMenu_price() %></td>
    	<td width=25% aligh=left><%=item.getMenu_Num() %></td>
    </tr>
    <% 
    paras.setCashier("");
    paras.setName("**餐厅");
	paras.setCashier("002");
	paras.setPrinter("003");
	paras.setGuestNumbers("6");
	paras.setOrderNumber(list.isEmpty()?"":list.get(0).getOrder_No());
	paras.setTableName(tableid);
	paras.setPaperName("结账单-顾客联");
	paras.setWaiter("张三");
	paras.setSerialnumber("20140201");
	paras.setDiscountRate("1.0");
	paras.setTotalmoney("1000");
	paras.setMenuList(menus);
	} 
	if(firstVisit!=null)
	PrintTool.DoPrint(new XPrinter80(),paras);
	%>
    <tr> <td colspan=4><hr width="600px"/></td></tr>
    <tr><td colspan=2>折扣率：</td><td colspan=2></td></tr>
    <tr><td colspan=2>消费金额：</td><td colspan=2></td></tr>
    <tr> <td colspan=4><hr width="600px"/></td></tr>
    <tr><td colspan=2>收银员：</td><td colspan=2>打印员：</td></tr>
    </table>
     </form>
   </div>
</center>
</body>
<script language="javascript" type="text/javascript">   
    /* var LODOP; //声明为全局变量 
	function prn1_preview() {	
		CreateOneFormPage();	
		LODOP.PREVIEW();	
	};
	function prn1_print() {		
		CreateOneFormPage();
		LODOP.PRINT();	
	};
		
	function CreateOneFormPage(){
		LODOP=getLodop();  
		LODOP.PRINT_INIT("桌单详情打印");
		var strBodyStyle="<style>"+document.getElementById("count").innerHTML+"</style>";
		var strFormHtml=strBodyStyle+"<body>"+document.getElementById("form1").innerHTML+"</body>";
		LODOP.PRINT_INIT("桌单详情打印");	
		//LODOP.ADD_PRINT_TEXT(50,50,260,39,"打印与显示样式一致：");
		LODOP.ADD_PRINT_HTM(88,50,300,600,strFormHtml);
		//LODOP.PREVIEW();

	};	    */                  
	
	function prn1_print() {	
	    window.location.reload();	    		
	}; 
	function prn1_preview() {	
		window.close();
	};
</script> 
</html>

