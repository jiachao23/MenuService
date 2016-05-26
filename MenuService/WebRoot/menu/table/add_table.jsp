<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.menu.model.MenuType"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>添加餐桌信息</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="../../css/admin_main.css" rel="stylesheet" type="text/css" />
	<link href="../../js/artDialog4.1.6/skins/default.css" rel="stylesheet" type="text/css" />
	<script src="../../js/jquery-1.7.js" language="javascript" type="text/javascript"></script>
	<script src="../../js/artDialog4.1.6/jquery.artDialog.js" language="javascript" type="text/javascript"></script>
</head>
<body>
<div class="mtitle"><h1>添加餐桌别信息</h1></div>
<table class="tlist" >
	
<tbody >
	<tr>
		<td width='150px' align='right'>餐桌编号：</td>
		<td><input id='table_No' name="table_No" type="text" disabled/></td>
		<td id="table_No_checkresult" style="color:blue">*自动生成</td>
	</tr>
	<tr>
		<td width='150px' align='right'>餐桌容纳人数：</td>
		<td><input id='table_Volum' type="text"/></td>
		<td id="table_Volum_checkresult" style="color:blue"></td>
	</tr>

	<tr>
		<td></td>
		<td>
			<button type="submit" class="btn1" onclick="addition()">提交</button>
			<button type="submit" class="btn1" onclick="location.href='../../table/tablesManagePage?tablesForm.query=false';">返回</button>
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

	//初始化菜的类型编号
	function initTable_No() {
		$.ajax({  
				type: "POST",  
				url: "../../table/getTable_No",  
				cache:false,
				data: {},  
				dataType: 'text',  
				contentType: "application/x-www-form-urlencoded",  
				success: function(data){
					//清空显示层中的数据 
					$("#table_No").val(data);
					}
				}//success函数结束                                  
			);//ajax请求结束	 
	}

	$(document).ready(function(){
		initTable_No();
		
		//检查餐桌容纳人数
		$("#table_Volum").blur(function(){
			if($("#table_Volum").val().trim().length==0){
				$("#table_Volum_checkresult").css("color","red");
				$("#table_Volum_checkresult").text("请您输入菜的价格！");
			}else if(isNaN($("#table_Volum").val().trim())) {
				$("#table_Volum_checkresult").css("color","red");
				$("#table_Volum_checkresult").text("请输入正确的菜价！！");
		   	}
		});

	});
	
	
	
	//提交button处理
	function addition(){
		
			
		//检测菜的价格
		if($("#table_Volum").val().trim().length==0){
			art.dialog( {
				time :2,
				content :'请输入餐桌容纳人数！'
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
			"../../table/addTable",
			{
				"tables.table_No":$("#table_No").val().trim(),
				"tables.table_Volum":$("#table_Volum").val().trim(),
			},
			function(data){
				art.dialog( {
					time :2,
					content :data
				});
				$("#table_Volum").val("");
				var dishType_No = parseInt($("#table_No").val().trim()) + 1;
				if(table_No < 10)
				{
					$("#table_No").val("00"+dishType_No);
				}
				else
				{
					$("#table_No").val("0"+dishType_No);
				}
				
				/*window.setTimeout(function(){  
  					window.location.href="adminuser_manage_page.action?adminUserForm.query=false&adminUserForm.adminRoleId=1";
                }, 1500);*/
			});//ajax请求结束
	}
	
</script>
</html>