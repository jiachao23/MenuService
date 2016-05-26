<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.menu.model.MenuType"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>更新餐桌信息</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="../css/admin_main.css" rel="stylesheet" type="text/css" />
	<link href="../js/artDialog4.1.6/skins/default.css" rel="stylesheet" type="text/css" />
	<script src="../js/jquery-1.7.js" language="javascript" type="text/javascript"></script>
	<script src="../js/artDialog4.1.6/jquery.artDialog.js" language="javascript" type="text/javascript"></script>
</head>
<body>
<div class="mtitle"><h1>更新餐桌信息</h1></div>
<table class="tlist" >
	
<tbody >
	<tr style="display:none">
		<td width='150px' align='right'>ID：</td>
		<td><input id='id' type="text" value="<s:property value="#request.updateTable.id"/>"/></td>
		<td id="id_checkresult" style="color:blue"></td>
	</tr>
	<tr>
		<td width='150px' align='right'>餐桌编号：</td>
		<td><input id='table_No' name="table_No" type="text" value="<s:property value='#request.updateTable.table_No'/>" disabled/></td>
		<td id="table_No_checkresult" style="color:blue">*不可修改</td>
	</tr>
	
	<tr>
		<td width='150px' align='right'>容纳人数：</td> 
		<td><input id='table_Volum' type="text" value="<s:property value='#request.updateTable.table_Volum'/>"/></td>
		<td id="table_Volum_checkresult" style="color:blue"></td>
	</tr>

	<tr>
		<td></td>
		<td>
			<button type="submit" class="btn1" onclick="addition()">提交</button>
			<button type="submit" class="btn1" onclick="location.href='../table/tablesManagePage?tablesForm.query=false';">返回</button>
		</td>
		<td></td>
	</tr>
</tbody>
	<tfoot>
		<tr>
			<td></td>
		</tr><tr><td valign="top"><br></td><td valign="top"><br></td><td valign="top"><br></td></tr>
	</tfoot>
</table>
</body>

<script type="text/javascript">

	$(document).ready(function(){
	
		//检查菜价
		$("#table_Volum").blur(function(){
			if($("#table_Volum").val().trim().length==0){
				$("#table_Volum_checkresult").css("color","red");
				$("#table_Volum_checkresult").text("请您输入餐桌容纳人数！");
			}else if(isNaN($("#table_Volum").val().trim())) {
				$("#table_Volum_checkresult").css("color","red");
				$("#table_Volum_checkresult").text("请输入正确的容纳人数！");
		   	}
		});

	});
	
	
	
	//提交button处理
	function addition(){
		
			
		//检测菜的价格
		if($("#table_Volum").val().trim().length==0){
			art.dialog( {
				time :2,
				content :'请输入容纳人数！'
			});
			$("#table_Volum").focus();
			return;		
		}else if(isNaN($("#table_Volum").val().trim())) {
			art.dialog( {
				time :2,
				content :'请输入正确的容纳人数！'
			});
			$("#table_Volum").focus();
			return;
		}
		$.post(  
			"../table/updateTable",
			{
				"tables.id":$("#id").val().trim(),
				"tables.table_No":$("#table_No").val().trim(),
				"tables.table_Volum":$("#table_Volum").val().trim(),
			},
			function(data){
				art.dialog( {
					time :2,
					content :data
				});
				
				window.setTimeout(function(){  
  					window.location.href="tablesManagePage?tablesForm.query=false";
                }, 1500); 
			});//ajax请求结束
	}
	
</script>
</html>