<%@page import="com.menu.model.*,com.menu.web.formBean.*,utils.*"%>
<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<script language="javascript" src="../js/LodopFuncs.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>����ҳ��</title>
</head>
<% 
 /* �ж��Ƿ��һ�μ���ҳ�� */
 Boolean firstVisit = (Boolean)application.getAttribute("firstVisit");
 if(firstVisit==null) {  System.out.println("���ǵ�һ�η���index.jspҳ��...");  
 application.setAttribute("firstVisit", false);} 
 else {  System.out.println("�ǵ�һ�η���...");
 }
/*  ��ȡ���� */
 String tableid = (String)request.getParameter("order.table_No");
 String sum_price =(String)request.getAttribute("sum_price");
 /* ��ȡ������������ */
 List<OrderItemForm> list = (List<OrderItemForm>)request.getAttribute("orderItemFormList");
 PrinterParas paras = new PrinterParas();
%>
<body>
<center>

<a href="javascript:prn1_print()">��ӡ</a>
<a href="javascript:prn1_preview()">�ر�ҳ��</a>
   <div id="count">
     <form id="form1" name="form1" method="post">
     <h4>��������-������</h4>
        <table width="600px"> 
       <tr> <td colspan=4><hr width="600px"/></td></tr>       
       <tr><td colspan=2>̨�ţ�<%=tableid%></td><td colspan=2>������</td> </tr>
       <tr><td colspan=2>���ţ�<%=list.isEmpty()?"":list.get(0).getOrder_No() %></td><td colspan=2>����Ա��</td> </tr>
       <tr><td colspan=4>��ˮ�ţ�</td></tr> 
       <tr><td colspan=4>��ӡʱ�䣺<%=new Date().toLocaleString()%></td></tr>
       <tr> <td colspan=4><hr width="600px"/></td></tr>
           <td width=10% aligh=center>���</td>
           <td width=40% aligh=left>����</td>
           <td width=25% aligh=left>�۸�</td>
           <td width=25% aligh=left>����</td>        
         </tr>
    <%/* List<OrderItemForm> list = (List<OrderItemForm>)request.getAttribute("orderItemFormList");  */  
    int i=0; 
    String[][] menus = new String[list.size()][];
    
     /* ��ʼ��xp-80c��ӡ�������ṹ�� */	
    for(;i<list.size();i++) {
    OrderItemForm item = (OrderItemForm)list.get(i);
    /* ����Ʒ���� */
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
    paras.setName("**����");
	paras.setCashier("002");
	paras.setPrinter("003");
	paras.setGuestNumbers("6");
	paras.setOrderNumber(list.isEmpty()?"":list.get(0).getOrder_No());
	paras.setTableName(tableid);
	paras.setPaperName("���˵�-�˿���");
	paras.setWaiter("����");
	paras.setSerialnumber("20140201");
	paras.setDiscountRate("1.0");
	paras.setTotalmoney("1000");
	paras.setMenuList(menus);
	} 
	if(firstVisit!=null)
	PrintTool.DoPrint(new XPrinter80(),paras);
	%>
    <tr> <td colspan=4><hr width="600px"/></td></tr>
    <tr><td colspan=2>�ۿ��ʣ�</td><td colspan=2></td></tr>
    <tr><td colspan=2>���ѽ�</td><td colspan=2></td></tr>
    <tr> <td colspan=4><hr width="600px"/></td></tr>
    <tr><td colspan=2>����Ա��</td><td colspan=2>��ӡԱ��</td></tr>
    </table>
     </form>
   </div>
</center>
</body>
<script language="javascript" type="text/javascript">   
    /* var LODOP; //����Ϊȫ�ֱ��� 
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
		LODOP.PRINT_INIT("���������ӡ");
		var strBodyStyle="<style>"+document.getElementById("count").innerHTML+"</style>";
		var strFormHtml=strBodyStyle+"<body>"+document.getElementById("form1").innerHTML+"</body>";
		LODOP.PRINT_INIT("���������ӡ");	
		//LODOP.ADD_PRINT_TEXT(50,50,260,39,"��ӡ����ʾ��ʽһ�£�");
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

