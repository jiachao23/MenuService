<%@ page import="com.menu.model.*,com.menu.web.formBean.*"%>
<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>菜谱</title>
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
        if (confirm("确认要结账？")) {
            window.open("../orderItem/getOrderItemFormForCounter?order.table_No=<%=tableid%>",'_blank');
        } 
        <%-- $.messager.confirm('菜单提示', '是否确认要结账此桌单?', function(r){
		 if (r){		 
		   		window.location.href="../orderItem/getOrderItemFormForCounter?order.table_No=<%=tableid%>"; 
    			window.open("../orderItem/getOrderItemFormForCounter?order.table_No=<%=tableid%>",'_blank');
    		}
    	}); --%>
    	
    }
</script>
<div id="all">
 <!--左边-->
 <div id="menu">
 <div id="count">
  <form id="form1" name="form1" method="post">

  <button type="button" class="easyui-linkbutton" onclick=";">&nbsp;刷新&nbsp;</button>
  <button type="button" class="easyui-linkbutton" onclick="deletemenu();">&nbsp;删除&nbsp;</button>
  <button type="button" class="easyui-linkbutton" onclick="alterNum();">&nbsp;改数量&nbsp;</button>
  <button type="button" class="easyui-linkbutton" onclick="alterRate();">&nbsp;改折扣&nbsp;</button>
    <table width="690px"  >
    <tr height="40" >
           <td>编号</td>
           <td>菜名</td>
           <td>口味</td>
           <td>价格</td>
           <td>份数</td>         
           <td>折扣率</td>
           <td>选择?</td>
           
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
    	<!-- 编号 --><td id="<%=i+1%>#1" ><%=i+1 %></td>
    	<!-- 菜名 --><td id="<%=i+1%>#2"><%=item.getMenu_Name() %></td>
    	<!-- 口味 --><td id="<%=i+1%>#3"><%=item.getRemark()==null?"无":item.getRemark() %></td>
    	<!-- 价格 --><td id="<%=i+1%>#4"><%=item.getMenu_price() %></td>
    	<!-- 份数 --><td id="<%=i+1%>#5"><%=item.getMenu_Num() %></td>
    	<!-- 折扣率 --><td id="<%=i+1%>#6">1.0</td>
    	<td id="<%=i+1%>#7"><input type='checkbox' name='menu_<%=i+1%>' value='<%=item.getOrderItem_Id()%>'> 是</td>
    	<td id="<%=i+1%>#8" style="display:none"><input type='text' id='listcount' value='<%=count%>'></td>
    </tr>
    <%
   		 request.setAttribute("sum_price", sum_price);
    	}
    
    %>
    </table>
    </div>
    <table >
     <tr> <td colspan="6"  id="count1" >不参加折扣：<input type='checkbox' name='jiushui' value="酒水">酒水&nbsp;&nbsp;<input type='checkbox' name='海鲜' value="海鲜">海鲜</td></tr>
     <tr> <td colspan="6"  id="count1" >总单折扣率：<input type='text' name='jiushui' value=""></td></tr>
     <tr><td colspan="6"  id="count1">收款方式：<input type="radio" value="byCash" checked="checked" name="paytype"/>现金<input type="radio" value="byCard" name="paytype"/>刷卡</td></tr>
     <tr  align="center" ><td colspan="6" id="count1">总计:<label id="countTotal" >&yen;<%=sum_price %></label></td>
     </tr>  
    </table>
  </form>
 
 </div>
 <!--右边-->
 <div id="dish_class">
  <div id="dish_top">
  <p></p>
  <table>
  <tr><td>&nbsp;&nbsp;</td></tr>
  <tr><td>桌号：&nbsp;&nbsp;<%=tableid %></td></tr>
  <tr><td>服务员：&nbsp;&nbsp;</td></tr>
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
			 $.messager.confirm('菜品维护', '是否确认要删除这些菜品?', function(r){
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
				    	}//回调函数结束                                  
		     		});//ajax请求结束
				}
		  });
		}		
		else
		{
			 $.messager.alert('提示','请先选中!','warning');
		}
	}//删除结束
	
  //修改数量
  function alterNum(){
  //使用tools.js获取名为“menu_”的复选框的value值
  var idlist = getCheckBoxsValue("menu_") ;
		if(idlist.length!=0)
		{
			// 如果超过2个以上的条目被选中，则提示
			if(idlist.indexOf("##",0)>=0)
			{			
				$.messager.alert('提示','请先选中单个菜品进行操作!','warning');
				return;
			}
			else
			{
				//使用tools.js获取名为“menu_”的复选框的name值
				 var idlist1 = getCheckBoxsNames("menu_") ;
				 //去掉前缀，只保留有效数字值
				idlist1 = idlist1.substr(5);
				var td_id = idlist1+"#5";	//数量单元格对应第5列，故按此拼凑id号				
				// 提示框，输入数量值
				var content =document.getElementById(td_id).innerHTML
				$.messager.prompt('输入数量', "原数量："+content, function(r){
                if (r){               
                    if(isNaN(r))
                    {
                    	$.messager.alert('提示','请输入有效数字!','warning');
                    	return;
                    }
                    //设置对应单元格内容
                    document.getElementById(td_id).innerHTML = r;
                	}
            	});
			}
		}
		else
		{
			//alert("请先选中");
			$.messager.alert('提示','请先选中!','warning');
		}
  }
  
  function alterRate()
  {
  	
   	 var idlist = getCheckBoxsValue("menu_") ;
  		if(idlist.length!=0)
		{
			if(idlist.indexOf("##",0)>=0)
			{
				$.messager.alert('提示','请先选中单个菜品进行操作!','warning');
				return;
			}
			else
			{
				//使用tools.js获取名为“menu_”的复选框的name值
				 var idlist1 = getCheckBoxsNames("menu_") ;
				 //去掉前缀，只保留有效数字值
				idlist1 = idlist1.substr(5);	
				var td_id = idlist1+"#6";	//数量单元格对应第5列，故按此拼凑id号			
				// 提示框，输入数量值
				var content = document.getElementById(td_id).innerHTML;
				$.messager.prompt('输入折扣率', "原折扣率："+content, function(r){
                if (r){   
                	         
                    if(isNaN(r))
                    {
                    	$.messager.alert('提示','请输入0~1之间有效数字!','warning');
                    	return;
                    }
                    /* float fr = parseFloat(r);
                    if(fr>1||fr<0)
                    {
                    	$.messager.alert('提示','请输入0~1之间有效数字!','warning');
                    	return;
                    }      */                                
                    //设置对应单元格内容
                    document.getElementById(td_id).innerHTML = r;
                    //caculateAllPrice();
                    window.location.reload(); 
                	}
            	});
			}
		}
		else
		{
			$.messager.alert('提示','请先选中!','warning');
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
