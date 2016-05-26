<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>更新服务员信息</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="../css/admin_main.css" rel="stylesheet" type="text/css" />
	<link href="../js/artDialog4.1.6/skins/default.css" rel="stylesheet"type="text/css" />
	<script src="../js/jquery-1.7.js" language="javascript" type="text/javascript"></script>
	<script src="../js/artDialog4.1.6/jquery.artDialog.js" language="javascript" type="text/javascript"></script>
</head>
<body>
<div class="mtitle"><h1>更新服务员信息</h1></div>
<table class="tlist" >
<tbody>
	<tr style="display:none">
		<td width='150px' align='right'>ID:</td>
		<td><input id='id' type="text" /></td>
		<td id="id_checkresult" style="color:blue"></td>
	</tr>
	<tr>
		<td width='150px' align='right' >编号：</td>
		<td><input id='waiter_No' name="waiter_No" type="text" disabled/></td>
		<td id="waiter_No_checkresult" style="color:blue">服务员编号由系统内置</td>
	</tr>
	<tr>
		<td width='150px' align='right'>姓名：</td>
		<td><input id='waiter_Name' name="waiter_Name" type="text"/></td>
		<td id="waiter_Name_checkresult" style="color:blue"></td>
	</tr>
	<tr>
		<td width='150px' align='right'>性别：</td>
		<td>
			<select id="waiter_Sex" class="formText">
				<option value="-1">
					请选择...
				</option>
				<option value="0">
					男
				</option>
				<option value="1">
					女
				</option>
			</select>
		</td>
		<td id="waiter_Sex_checkresult" style="color:blue"></td>
	</tr>
	<tr>
		<td width='150px' align='right'>年龄：</td>
		<td><input id='waiter_Age' type="text"/></td>
		<td id="waiter_Age_checkresult" style="color:blue"></td>
	</tr>

	<tr>
		<td width='150px' align='right'>手机：</td>
		<td><input id='waiter_Telphone' type="text"/></td>
		<td id="waiter_Telphone_checkresult" style="color:blue"></td>
	</tr>
	<tr>
		<td></td>
		<td>
			<button type="submit" class="btn1" onclick="update()">提交</button>
			<button type="submit" class="btn1" onclick="location.href='waiterManagePage?waiterForm.query=false';">返回</button>
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

	function initWaiter() {
		$("#id").val("<s:property value="#request.updateWaiter.id"/>");
		$("#waiter_No").val("<s:property value="#request.updateWaiter.waiter_No"/>");
		$("#waiter_Name").val("<s:property value="#request.updateWaiter.waiter_Name"/>");
		$("#waiter_Sex").val("<s:property value="#request.updateWaiter.waiter_Sex"/>");
		$("#waiter_Age").val("<s:property value="#request.updateWaiter.waiter_Age"/>");
		$("#waiter_Telphone").val("<s:property value="#request.updateWaiter.waiter_Telphone"/>");
	}
	
	$(document).ready(function(){
		initWaiter();
		//年龄输入框绑定验证事件
		$("#waiter_Age").blur(function(){
			if(isNaN($("#waiter_Age").val().trim())) {
				$("#waiter_Age_checkresult").css("color","red");
				$("#waiter_Age_checkresult").text("请输入正确的年龄！");
			}
		});
		
		
		//手机输入框绑定验证事件
		$("#waiter_Telphone").blur(function(){
			if(!$("#waiter_Telphone").val().match(/^1[3|4|5|8][0-9]\d{4,8}$/)){
				$("#waiter_Telphone_checkresult").css("color","red");
				$("#waiter_Telphone_checkresult").text("手机格式不正确！");
			}else{
				$("#waiter_Telphone_checkresult").text("  ");
			}
		});
	});
	
	//提交button处理
	function update(){
	
		if(isNaN($("#waiter_Age").val().trim())) {
			art.dialog( {
				time :2,
				content :'请输入正确的年龄！'
			});
			return;	
		}
		
		//检测手机号码格式
		if(!$("#waiter_Telphone").val().match(/^1[3|4|5|8][0-9]\d{4,8}$/)){
			art.dialog( {
				time :2,
				content :'手机号码格式不正确！'
			});
			return;				
		}
		
		$.post(  
			"updateWaiter",
			{
				"waiter.id":$("#id").val().trim(),
				"waiter.waiter_No":$("#waiter_No").val().trim(),
				"waiter.waiter_Name":$("#waiter_Name").val().trim(),
				"waiter.waiter_Sex":$("#waiter_Sex").val().trim(),
				"waiter.waiter_Age":$("#waiter_Age").val().trim(),
				"waiter.waiter_Telphone":$("#waiter_Telphone").val().trim(),
			},
			function(data){
				art.dialog( {
					time :2,
					content :data
				});
				
				
				window.setTimeout(function(){  
  					window.location.href="waiterManagePage?waiterForm.query=false";
                }, 1500);  
			});//ajax请求结束
	}
	
</script>
</html>