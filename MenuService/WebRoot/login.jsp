<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>login</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="css/login_style.css" rel="stylesheet" type="text/css" />
		<script src="js/jquery-1.7.js" language="javascript" type="text/javascript"></script>
	</head>
	<body>

		
		<div class="login-form">
		<p style="line-height:10px;padding-bottom:10px;font-size:12px;color:red" align="center">
			<strong id='errorinfo'>&nbsp;</strong>
		</p>				
			<fieldset>
				<legend>
					Log in
				</legend>
				<label for="login">
					用户名：
				</label>
				<input type="text" id="username" name="adminUser.username" value="admin"/>
				<div class="clear"></div>
				<label for="password">
					密&nbsp;&nbsp;码：
				</label>
				<input type="password" id="password" name="adminUser.password" value="123456"/>
				<div class="clear"></div>
				<div class="clear"></div>
				<br />
				<input type="button" onclick="login();" style="margin: -20px 0 0 287px;" class="button" name="commit"
					value="登&nbsp&nbsp陆" />
			</fieldset>
		</div>


	</body>

	<script type="text/javascript">
	function login() {

		var username = $("#username").val().trim();
		var password = $("#password").val().trim();
		
		//检测用户名是否为空
		if(username == "" || password == ""){
			$("#errorinfo").text("用户名和密码不能为空！");
			return;		
		}
		$.ajax( {
			type :"POST",
			url :"user/adminuser_checklogin",
			data :"adminUser.username=" + username + "&adminUser.password="
					+ password,
			dataType :'text',
			contentType :"application/x-www-form-urlencoded",
			success : function(data) {
				if (data == "账号或密码错误！") {
					$("#errorinfo").text(data);
				} else {
					location.href = "index.jsp";
				}
			}//回调函数结束                                  
		});//ajax请求结束
	}
</script>
</html>