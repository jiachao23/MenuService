<%@ page import="com.menu.model.*,com.menu.web.formBean.*"%>
<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>����</title>
<link rel="stylesheet" type="text/css" href="../css/index.css" />
<link rel="stylesheet" type="text/css" href="../css/easyui_themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="../css/easyui_themes/icon.css" />
		<script src="../js/jquery-1.3.2.min.js" language="javascript" type="text/javascript"></script>
		<script src="../js/jquery.pagination.js" language="javascript" type="text/javascript"></script>
		<script src="../js/jquery.loadmask.min.js" language="javascript" type="text/javascript"></script>
		<!-- <script src="../js/artDialog4.1.6/jquery.artDialog.js" language="javascript" type="text/javascript"></script> -->
		<script src="../js/tools.js" language="javascript" type="text/javascript"></script>
		<script src="../js/jquery.easyui.min.js" language="javascript" type="text/javascript"></script>
</head>
<% String tableid = (String)request.getParameter("order.table_No");%>
<body>
 <script language="javascript">
    function delcfm() {
        if (confirm("ȷ��Ҫ���ˣ�")) {
            window.open("../orderItem/getOrderItemFormForCounter?order.table_No=<%=tableid%>",'_blank');
        } 
        <%-- $.messager.confirm('�˵���ʾ', '�Ƿ�ȷ��Ҫ���˴�����?', function(r){
		 if (r){		 
		   		window.location.href="../orderItem/getOrderItemFormForCounter?order.table_No=<%=tableid%>"; 
    			window.open("../orderItem/getOrderItemFormForCounter?order.table_No=<%=tableid%>",'_blank');
    		}
    	}); --%>
    	
    }
</script>
<div id="all">
 <!--���-->
 <div id="menu">
 <div id="count">
  <form id="form1" name="form1" method="post">

  <button type="button" class="easyui-linkbutton" onclick=";">&nbsp;ˢ��&nbsp;</button>
  <button type="button" class="easyui-linkbutton" onclick="deletemenu();">&nbsp;ɾ��&nbsp;</button>
  <button type="button" class="easyui-linkbutton" onclick="alterNum();">&nbsp;������&nbsp;</button>
  <button type="button" class="easyui-linkbutton" onclick="alterRate();">&nbsp;���ۿ�&nbsp;</button>
    <table width="690px"  >
    <tr height="40" >
           <td>���</td>
           <td>����</td>
           <td>��ζ</td>
           <td>�۸�</td>
           <td>����</td>         
           <td>�ۿ���</td>
           <td>ѡ��?</td>
           
         </tr>
    <%
    
    List<OrderItemForm> list = (List<OrderItemForm>)request.getAttribute("orderItemFormList");       
    int i=0;
    float sum_price =0;
    int count = list.size();
    for(;i<list.size();i++) {
    OrderItemForm item = (OrderItemForm)list.get(i);
    sum_price += item.getMenu_price()*Float.parseFloat(item.getMenu_Num());
    %>

    <tr>
    	<!-- ��� --><td id="<%=i+1%>#1" ><%=i+1 %></td>
    	<!-- ���� --><td id="<%=i+1%>#2"><%=item.getMenu_Name() %></td>
    	<!-- ��ζ --><td id="<%=i+1%>#3"><%=item.getRemark()==null?"��":item.getRemark() %></td>
    	<!-- �۸� --><td id="<%=i+1%>#4"><%=item.getMenu_price() %></td>
    	<!-- ���� --><td id="<%=i+1%>#5"><%=item.getMenu_Num() %></td>
    	<!-- �ۿ��� --><td id="<%=i+1%>#6">1.0</td>
    	<td id="<%=i+1%>#7"><input type='checkbox' name='menu_<%=i+1%>' value='<%=item.getOrderItem_Id()%>'> ��</td>
    	<td id="<%=i+1%>#8" style="display:none"><input type='text' id='listcount' value='<%=count%>'></td>
    </tr>
    <%
   		 request.setAttribute("sum_price", sum_price);
    	}
    
    %>
    </table>
    </div>
    <table >
     <tr> <td colspan="6"  id="count1" >���μ��ۿۣ�<input type='checkbox' name='jiushui' value="��ˮ">��ˮ&nbsp;&nbsp;<input type='checkbox' name='����' value="����">����</td></tr>
     <tr> <td colspan="6"  id="count1" >�ܵ��ۿ��ʣ�<input type='text' name='jiushui' value=""></td></tr>
     <tr><td colspan="6"  id="count1">�տʽ��<input type="radio" value="byCash" checked="checked" name="paytype"/>�ֽ�<input type="radio" value="byCard" name="paytype"/>ˢ��</td></tr>
     <tr  align="center" ><td colspan="6" id="count1">�ܼ�:<label id="countTotal" >&yen;<%=sum_price %></label></td>
     </tr>  
    </table>
  </form>
 
 </div>
 <!--�ұ�-->
 <div id="dish_class">
  <div id="dish_top">
  <p></p>
  <table>
  <tr><td>&nbsp;&nbsp;</td></tr>
  <tr><td>���ţ�&nbsp;&nbsp;<%=tableid %></td></tr>
  <tr><td>����Ա��&nbsp;&nbsp;</td></tr>
  <tr><td>&nbsp;&nbsp;</td></tr>
  <tr><td style="text-align:center;"><a onClick="delcfm()" <%-- href="../orderItem/getOrderItemFormForCounter?order.table_No=<%=tableid%>" target="_blank" --%>><img src="../images/jiezhang.png"/></a></td></tr>
  <tr><td><a href="../table/getTableList"><img src="../images/fanhui.png"/></a></td></tr>
  </table>
  </div>
 </div>
</div>
</body>
<script type="text/javascript" >
function deletemenu(){
		var idlist = getCheckBoxsValue("menu_") ;
		if(idlist.length!=0)
		{
			 $.messager.confirm('��Ʒά��', '�Ƿ�ȷ��Ҫɾ����Щ��Ʒ?', function(r){
			 if (r){
			 		alert(idlist);
					$.ajax({  
						type: "POST",  
						url: "../orderItem/deleteOrderItem",  
						data: {"idlist":idlist},  
		 				dataType: "text",  
						//contentType: "application/x-www-form-urlencoded",  
						success: function(data){						
							alert('success');
							window.location.reload(); 	
				    	}//�ص���������                                  
		     		});//ajax�������
				}
		  });
		}		
		else
		{
			 $.messager.alert('��ʾ','����ѡ��!','warning');
		}
	}//ɾ������
	
  //�޸�����
  function alterNum(){
  //ʹ��tools.js��ȡ��Ϊ��menu_���ĸ�ѡ���valueֵ
  var idlist = getCheckBoxsValue("menu_") ;
		if(idlist.length!=0)
		{
			// �������2�����ϵ���Ŀ��ѡ�У�����ʾ
			if(idlist.indexOf("##",0)>=0)
			{			
				$.messager.alert('��ʾ','����ѡ�е�����Ʒ���в���!','warning');
				return;
			}
			else
			{
				//ʹ��tools.js��ȡ��Ϊ��menu_���ĸ�ѡ���nameֵ
				 var idlist1 = getCheckBoxsNames("menu_") ;
				 //ȥ��ǰ׺��ֻ������Ч����ֵ
				idlist1 = idlist1.substr(5);
				var td_id = idlist1+"#5";	//������Ԫ���Ӧ��5�У��ʰ���ƴ��id��				
				// ��ʾ����������ֵ
				var content =document.getElementById(td_id).innerHTML
				$.messager.prompt('��������', "ԭ������"+content, function(r){
                if (r){               
                    if(isNaN(r))
                    {
                    	$.messager.alert('��ʾ','��������Ч����!','warning');
                    	return;
                    }
                    //���ö�Ӧ��Ԫ������
                    document.getElementById(td_id).innerHTML = r;
                	}
            	});
			}
		}
		else
		{
			//alert("����ѡ��");
			$.messager.alert('��ʾ','����ѡ��!','warning');
		}
  }
  
  function alterRate()
  {
  	
   	 var idlist = getCheckBoxsValue("menu_") ;
  		if(idlist.length!=0)
		{
			if(idlist.indexOf("##",0)>=0)
			{
				$.messager.alert('��ʾ','����ѡ�е�����Ʒ���в���!','warning');
				return;
			}
			else
			{
				//ʹ��tools.js��ȡ��Ϊ��menu_���ĸ�ѡ���nameֵ
				 var idlist1 = getCheckBoxsNames("menu_") ;
				 //ȥ��ǰ׺��ֻ������Ч����ֵ
				idlist1 = idlist1.substr(5);	
				var td_id = idlist1+"#6";	//������Ԫ���Ӧ��5�У��ʰ���ƴ��id��			
				// ��ʾ����������ֵ
				var content = document.getElementById(td_id).innerHTML;
				$.messager.prompt('�����ۿ���', "ԭ�ۿ��ʣ�"+content, function(r){
                if (r){   
                	         
                    if(isNaN(r))
                    {
                    	$.messager.alert('��ʾ','������0~1֮����Ч����!','warning');
                    	return;
                    }
                    /* float fr = parseFloat(r);
                    if(fr>1||fr<0)
                    {
                    	$.messager.alert('��ʾ','������0~1֮����Ч����!','warning');
                    	return;
                    }      */                                
                    //���ö�Ӧ��Ԫ������
                    document.getElementById(td_id).innerHTML = r;
                    //caculateAllPrice();
                    window.location.reload(); 
                	}
            	});
			}
		}
		else
		{
			$.messager.alert('��ʾ','����ѡ��!','warning');
		} 
  }
  
  function caculateAllPrice()
  {
//   		var count=document.getElementById("listcount").value;
//   		alert(count);
//   		var sum = 0;
//   		for (var i=0;i<count;i++)
//   		{
//   			var price,rate,num;
//   			price = document.getElementById(i+"#4");
//   			rate = document.getElementById(i+"#6").innerHTML;
//   			num = document.getElementById(i+"#5").innerHTML;
//   			alert(i+":price "+price+",rate "+rate+",num "+num);
//   			sum += (price*rate)*num;
//   			alert(sum);
//   		}
// 		document.getElementById("countTotal").innerHTML = sum;
  } 
  
</script>
</html>
