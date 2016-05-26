<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.menu.model.MenuType"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>添加菜谱信息</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="../css/admin_main.css" rel="stylesheet" type="text/css" />
	<link href="../js/artDialog4.1.6/skins/default.css" rel="stylesheet" type="text/css" />
	<script src="../js/jquery-1.7.js" language="javascript" type="text/javascript"></script>
	<script src="../js/artDialog4.1.6/jquery.artDialog.js" language="javascript" type="text/javascript"></script>
</head>
<body>
<div class="mtitle"><h1>添加菜谱信息</h1></div>
<table class="tlist" >
	
<tbody >
	<tr>
		<td width='150px' align='right'>所属类别：</td>
		<td>
			<select id="menuType"  name="menuType" class="formText">
				<option value="-1" >
					请选择...
				</option>
				<%
					List<MenuType> menuTypeList = (List<MenuType>)(request.getAttribute("menuTypeList"));
							int menuType_count = menuTypeList.size();
							for(int i = 0; i < menuType_count;i++)
							{
								MenuType menuType = menuTypeList.get(i);
				%>
						<option value=<%out.print(menuType.getMenuType_No());%>>
						<%
						String menuType_Name = menuType.getMenuType_Name();
							out.print(menuType_Name);
						%>
							
						</option>
				 <% }%>
			</select>
		</td>
		<td id="menuType_checkresult" style="color:blue"></td>
	</tr>
	<tr>
		<td width='150px' align='right'>菜谱编号：</td>
		<td><input id='menu_No' name="menu_No" type="text" disabled/></td>
		<td id="menu_No_checkresult" style="color:blue"></td>
	</tr>
	<tr>
		<td width='150px' align='right'>菜谱名称：</td>
		<td><input id='menu_Name' type="text"/></td>
		<td id="menu_Name_checkresult" style="color:blue"></td>
	</tr>
	<tr>
		<td width='150px' align='right'>价格：</td>
		<td><input id='menu_Price' type="text"/></td>
		<td id="menu_Price_checkresult" style="color:blue"></td>
	</tr>
	<tr>
		<td width='150px' align='right'>图片：</td>
		<td><s:file name="file"></s:file></td>
		<td id="menu_Image_checkresult" style="color:blue"></td>
	</tr>
	<tr>
		<td></td>
		<td>
			<button type="submit" class="btn1" onclick="addition()">提交</button>
			<button type="submit" class="btn1" onclick="location.href='menuManagePage?menuForm.query=false';">返回</button>
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
		
		$("#menuType").focus();
		//$("#ddlregtype").find("option:selected").text();取下来菜单的值
		//检查菜的所属类型
		$("#menuType").change(function(){
			if($("#menuType").val().trim() == -1) {
				$("#menuType_checkresult").css("color","red");
				$("#menuType_checkresult").text("请选择菜谱的类别！");
			}else{
				$("#menuType_checkresult").text("系统设置菜谱编号中...");
				$.ajax({  
					type: "POST",  
					url: "getMenu_No",  
					data: "menu.menuType_No="+$("#menuType").val().trim(),  
		 			dataType: 'text',  
					contentType: "application/x-www-form-urlencoded",  
					success: function(data){
						$("#menu_No").val(data);
						$("#menuType_checkresult").text(" ");
					}
				});//ajax请求结束
			}
		});
		
		//检查菜名
		$("#menu_Name").blur(function(){
			if($("#menu_Name").val().trim()==''){
			    $("#menu_Name_checkresult").css("color","red");
				$("#menu_Name_checkresult").text("请您输入菜谱名称！");
			}else{
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

		$("#menu_Name").focus(function(){
			$("#menu_Name_checkresult").css("color","blue");
			$("#menu_Name_checkresult").text('');
		});
		
		//检查菜价
		$("#menu_Price").blur(function(){
			if($("#menu_Price").val().trim().length==0){
				$("#menu_Price_checkresult").css("color","red");
				$("#menu_Price_checkresult").text("请您输入菜的价格！");
			}else if(isNaN($("#menu_Price").val().trim())) {
				$("#menu_Price_checkresult").css("color","red");
				$("#menu_Price_checkresult").text("请输入正确的菜价！！");
		   	}
		});
		
		$("#menu_Price").focus(function(){
			$("#menu_Price_checkresult").css("color","blue");
			$("#menu_Price_checkresult").text('');
		});
	});
	
	
	
	//提交button处理
	function addition(){
		
		//检查菜的类型
		if($("#menuType").val().trim() == -1) {
				art.dialog( {
				time :2,
				content :'请选择菜的类型！'
			});
			$("#menuType").focus();
			return;	
		}
			
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
			"addMenu",
			{
				"menu.menuType_No":$("#menuType").val().trim(),
				"menu.menu_No":$("#menu_No").val().trim(),
				"menu.menu_Name":$("#menu_Name").val().trim(),
				"menu.menu_Price":$("#menu_Price").val().trim(),
			},
			function(data){
				art.dialog( {
					time :2,
					content :data
				});
				$("#menuType").val("");
				$("#menu_No").val("");
				$("#menu_Name").val("");
				$("#menu_Price").val("");
				$("#menu_Name_checkresult").text("");
				$("#menu_Price_checkresult").text("");
			});//ajax请求结束
	}
	
</script>
</html>