<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>更新菜谱信息</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="../css/admin_main.css" rel="stylesheet" type="text/css" />
	<link href="../js/artDialog4.1.6/skins/default.css" rel="stylesheet"type="text/css" />
	<script src="../js/jquery-1.7.js" language="javascript" type="text/javascript"></script>
	<script src="../js/artDialog4.1.6/jquery.artDialog.js" language="javascript" type="text/javascript"></script>
</head>
<body>
<div class="mtitle"><h1>更新菜谱信息</h1></div>
<table class="tlist" >
<tbody>
	<tr style="display:none">
		<td width='150px' align='right'>ID：</td>
		<td><input id='id' type="text"/></td>
		<td id="id_checkresult" style="color:blue"></td>
	</tr>
	<tr>
		<td width='150px' align='right' >菜谱编号：</td>
		<td><input id='menu_No' disabled="disabled" name="menu_No" type="text"/></td>
		<td id="menu_No_checkresult" style="color:blue"><a style="color:red">*</a>菜谱编号不可修改</td>
	</tr>
	<tr>
		<td width='150px' align='right'>所属类别：</td>
		<td><input id='menuType_Name' disabled="disabled" type="text"/></td>
		<td id="menuType_Name_Price_checkresult" style="color:blue"></td>
	</tr>
	<tr style="display:none">
		<td width='150px' align='right'>所属类别编号：</td>
		<td><input id='menuType_No' type="text"/></td>
		<td id="menuType_No_checkresult" style="color:blue"></td>
	</tr>
	<tr style="display:none">
		<td width='150px' align='right'>老菜名：</td>
		<td><input id='old_menu_Name' type="text"/></td>
		<td id="old_menu_Name_checkresult" style="color:blue"></td>
	</tr>
	<tr>
		<td width='150px' align='right'>菜名：</td>
		<td><input id='menu_Name' type="text"/></td>
		<td id="menu_Name_checkresult" style="color:blue"></td>
	</tr>
	<tr>
		<td width='150px' align='right'>菜价：</td>
		<td><input id='menu_Price' type="text"/></td>
		<td id="menu_Price_checkresult" style="color:blue"></td>
	</tr>
	
	<tr>
		<td></td>
		<td>
			<button type="submit" class="btn1" onclick="update()">提交</button>
			<button type="submit" class="btn1" onclick="location.href='menuManagePage?menuForm.query=false';">返回</button>
		</td>
		<td></td>
	</tr>
</tbody>
	<tfoot>
		<tr>
			<td></td>
		</tr>
	</tfoot>
</table>
</body>

<script type="text/javascript">
	function initUpdateData(){
		$("#id").val('<s:property value="#request.updateMenu.id"/>');
		$("#menu_No").val('<s:property value="#request.updateMenu.menu_No"/>');
		$("#menuType_No").val('<s:property value="#request.updateMenu.menuType_No"/>');
		$("#menuType_Name").val('<s:property value="#request.menuType_Name"/>');
		$("#old_menu_Name").val('<s:property value="#request.updateMenu.menu_Name"/>');
		$("#menu_Name").val('<s:property value="#request.updateMenu.menu_Name"/>');
		$("#menu_Price").val('<s:property value="#request.updateMenu.menu_Price"/>');
	}

	$(document).ready(function(){
		initUpdateData();
		//检查菜名
		$("#menu_Name").blur(function(){
			if($("#menu_Name").val().trim()==''){
			    $("#menu_Name_checkresult").css("color","red");
				$("#menu_Name_checkresult").text("菜谱名称不能为空！");
			}else if($("#menu_Name").val().trim() != $("#old_menu_Name").val().trim()){
				$("#menu_Name_checkresult").text("系统检测中...");
				$.ajax({  
					type: "POST",  
					url: "menu_NameExist",  
					data: "menu.menu_Name="+$("#menu_Name").val().trim(),  
		 			dataType: 'text',  
					contentType: "application/x-www-form-urlencoded",  
					success: function(data){
						data=="此菜谱名称已经存在！"?$("#menu_Name_checkresult").css("color","red"):$("#menu_Name_checkresult").css("color","blue");
						$("#menu_Name_checkresult").text(data);
					}
				});//ajax请求结束
			}
		});
		
		//检查菜价
		$("#menu_Price").blur(function(){
			if($("#menu_Price").val().trim().length==0){
				$("#menu_Price_checkresult").css("color","red");
				$("#menu_Price_checkresult").text("请您输入菜的价格！");
			}else if(isNaN($("#dish_Price").val().trim())) {
				$("#menu_Price_checkresult").css("color","red");
				$("#menu_Price_checkresult").text("请输入正确的菜价！！");
		   	}
		});
	});
	
	//提交button处理
	function update(){
	
		//检测菜的名称
		if($("#menu_Name").val().trim()==''){
			art.dialog( {
				time :2,
				content :'请输入菜的名称！'
			});
			$("#menu_Name").focus();
			return;		
		}

		//检测菜的价格
		if($("#menu_Price").val().trim().length==0){
			art.dialog( {
				time :2,
				content :'请输入菜价！'
			});
			$("#menu_Price").focus();
			return;		
		}else if(isNaN($("#menu_Price").val().trim())) {
			art.dialog( {
				time :2,
				content :'请输入正确的菜价！'
			});
			$("#menu_Price").focus();
			return;
		}
		$.post(  
			"updateMenu",
			{
				"menu.id":$("#id").val().trim(),
				"menu.menu_No":$("#menu_No").val(),
				"menu.menu_Name":$("#menu_Name").val().trim(),
				"menu.menu_Price":$("#menu_Price").val().trim(),
				"menu.menuType_No":$("#menuType_No").val().trim(),
			},
			function(data){
				art.dialog( {
					time :2,
					content :data
				});
				
				
				window.setTimeout(function(){  
  					window.location.href="menuManagePage?menuForm.query=false";
                }, 1500);  
			});//ajax请求结束
	}
	
</script>
</html>