<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.menu.model.MenuType"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>添加菜谱类型信息</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="../../css/admin_main.css" rel="stylesheet" type="text/css" />
	<link href="../../js/artDialog4.1.6/skins/default.css" rel="stylesheet" type="text/css" />
	<script src="../../js/jquery-1.7.js" language="javascript" type="text/javascript"></script>
	<script src="../../js/artDialog4.1.6/jquery.artDialog.js" language="javascript" type="text/javascript"></script>
</head>
<body>
<div class="mtitle"><h1>添加菜谱类型信息</h1></div>
<table class="tlist" style="width:80%">
	
<tbody >
	<tr>
		<td align='right'>菜谱类型编号：</td>
		<td><input id='menuType_No' name="menuType_No" type="text" disabled/></td>
		<td id="menuType_No_checkresult" style="color:blue">*菜谱类型由系统自动生成</td>
	</tr>
	<tr>
		<td align='right'>菜谱类型名称：</td>
		<td><input id='menuType_Name' type="text"/></td>
		<td id="menuType_Name_checkresult" style="color:blue"></td>
	</tr>

	<tr>
		<td></td>
		<td>
			<button type="submit" class="btn1" onclick="addition()">提交</button>
			<button type="submit" class="btn1" onclick="location.href='../../menuType/menuTypeManagePage?menuTypeForm.query=false';">返回</button>
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
	function initMenuType_No() {
		$.ajax({  
				type: "POST",  
				url: "../../menuType/getMenuType_No",  
				cache:false,
				data: {},  
				dataType: 'text',  
				contentType: "application/x-www-form-urlencoded",  
				success: function(data){
					//清空显示层中的数据 
					$("#menuType_No").val(data);
					}
				}//success函数结束                                  
			);//ajax请求结束	 
	}

	$(document).ready(function(){
		initMenuType_No();
		
		//检查菜类名称
		$("#menuType_Name").blur(function(){
			if($("#menuType_Name").val().trim()==''){
			    $("#menuType_Name_checkresult").css("color","red");
				$("#menuType_Name_checkresult").text("菜谱类型名称不能为空！");
			}else{
				$("#menuType_Name_checkresult").css("color","blue");
				$("#menuType_Name_checkresult").text("系统检测中...");
				$.ajax({  
					type: "POST",  
					url: "../../menuType/menuType_NameExist",  
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
			"../../menuType/addMenuType",
			{
				"menuType.menuType_No":$("#menuType_No").val().trim(),
				"menuType.menuType_Name":$("#menuType_Name").val().trim(),
			},
			function(data){
				art.dialog( {
					time :2,
					content :data
				});
				$("#menuType_Name").val("");
				var menuType_No = parseInt($("#menuType_No").val().trim()) + 1;
				if(menuType_No > 9)
				{
					$("#menuType_No").val(menuType_No);
				}
				else
				{
					$("#menuType_No").val("0"+menuType_No);
				}
				
			});//ajax请求结束
	}
	
</script>
</html>