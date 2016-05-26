<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.menu.model.MenuType"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>更新菜谱类型信息</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="../css/admin_main.css" rel="stylesheet" type="text/css" />
	<link href="../js/artDialog4.1.6/skins/default.css" rel="stylesheet" type="text/css" />
	<script src="../js/jquery-1.7.js" language="javascript" type="text/javascript"></script>
	<script src="../js/artDialog4.1.6/jquery.artDialog.js" language="javascript" type="text/javascript"></script>
</head>
<body>
<div class="mtitle"><h1>更新菜谱类型信息</h1></div>
<table class="tlist" style="width:80%">
	
<tbody >
	<tr style="display:none">
		<td align='right'>ID：</td>
		<td><input id='id' type="text"/></td>
		<td id="id_checkresult" style="color:blue"></td>
	</tr>
	<tr>
		<td align='right'>菜谱类型编号：</td>
		<td><input id='menuType_No' name="menuType_No" type="text"  disabled/></td>
		<td id="menuType_No_checkresult" style="color:blue">*不可修改</td>
	</tr>
	
	<tr style="display:none">
		<td align='right'>旧菜谱类型名称：</td> 
		<td><input id='old_menuType_Name' type="text" /></td>
		<td id="old_menuType_Name_checkresult" style="color:blue"></td>
	</tr>
	<tr>
		<td  align='right'>菜谱类型名称：</td> 
		<td><input id='menuType_Name' type="text" /></td>
		<td id="menuType_Name_checkresult" style="color:blue"></td>
	</tr>

	<tr>
		<td></td>
		<td>
			<button type="submit" class="btn1" onclick="addition()">提交</button>
			<button type="submit" class="btn1" onclick="location.href='../menuType/menuTypeManagePage?menuTypeForm.query=false';">返回</button>
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

	function initUpdateData()
	{
		$("#id").val('<s:property value="#request.updateMenuType.id"/>');
		$("#menuType_No").val('<s:property value="#request.updateMenuType.menuType_No"/>');
		$("#old_menuType_Name").val('<s:property value="#request.updateMenuType.menuType_Name"/>');
		$("#menuType_Name").val('<s:property value="#request.updateMenuType.menuType_Name"/>');
	}
	$(document).ready(function(){
	
		initUpdateData();
		//检查菜类名称
		$("#menuType_Name").blur(function(){
			if($("#menuType_Name").val().trim()==''){
			    $("#menuType_Name_checkresult").css("color","red");
				$("#menuType_Name_checkresult").text("菜类型名称不能为空！");
			}else if($("#menuType_Name").val().trim() != $("#old_menuType_Name").val().trim()){
				$("#menuType_Name_checkresult").text("系统检测中...");
				$.ajax({  
					type: "POST",  
					url: "../menuType/menuType_NameExist",  
					data: "menuType.menuType_Name="+$("#menuType_Name").val().trim(),  
		 			dataType: 'text',  
					contentType: "application/x-www-form-urlencoded",  
					success: function(data){
						if(data == "此菜谱类型名称已经存在！")
						{
							$("#menuType_Name_checkresult").css("color","red");
						}
						else
						{
							$("#menuType_Name_checkresult").css("color","blue");
						}
						$("#menuType_Name_checkresult").text(data);
					}
				});//ajax请求结束
			}
		});
		
		$("#menuType_Name").focus(function(){
			$("#menuType_Name_checkresult").text('');
		});
	});
	
	
	
	//提交button处理
	function addition(){
		
			
		//检测菜类型的名称
		if($("#menuType_Name").val().trim()==''){
			art.dialog( {
				time :2,
				content :'请输入菜的名称！'
			});
			$("#menuType_Name").focus();
			return;		
		}
		$.post(  
			"../menuType/updateMenuType",
			{
				"menuType.id":$("#id").val().trim(),
				"menuType.menuType_No":$("#menuType_No").val().trim(),
				"menuType.menuType_Name":$("#menuType_Name").val().trim(),
			},
			function(data){
				art.dialog( {
					time :2,
					content :data
				});
				
				window.setTimeout(function(){  
  					window.location.href="menuTypeManagePage?menuTypeForm.query=false";
                }, 1500); 
			});//ajax请求结束
	}
	
</script>
</html>