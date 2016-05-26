<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>菜单信息管理</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<script src="../js/jquery-1.7.js" language="javascript" type="text/javascript"></script>
		<link href="../css/admin_main.css" rel="stylesheet" type="text/css" />
		<link href="../css/pagination.css" rel="stylesheet" type="text/css" />
		<link href="../css/jquery.loadmask.css" rel="stylesheet" type="text/css" />
		<link href="../js/artDialog4.1.6/skins/default.css" rel="stylesheet" type="text/css" />

		<script src="../js/jquery.pagination.js" language="javascript" type="text/javascript"></script>
		<script src="../js/jquery.loadmask.min.js" language="javascript" type="text/javascript"></script>
		<script src="../js/artDialog4.1.6/jquery.artDialog.js" language="javascript" type="text/javascript"></script>
		<script src="../js/tools.js" language="javascript" type="text/javascript"></script>
	</head>
	<body>
		<div class="mtitle">
			<h1>
				菜单信息管理
			</h1>
		</div>
		<!--  搜索表单  -->
		<form action="<s:url />" id="selectForm" name="selectForm">
			<table width='100%' border='0' cellpadding='1' cellspacing='1'
				align="center" style="margin-top: 8px">

				<tr bgcolor='#f8f8f8'>
					<td align='center'>
						<table border='0' cellpadding='0' cellspacing='0'>
							<tr>
								<td>
									菜名：
								</td>
								<td width='160'>
									<input id="dish_Name" name='dishForm.dish_Name' type='text'
										class="txt" style='width: 150px'
										value="<s:property value='#request.dishForm.dish_Name' />" />
									<!-- 隐藏属性  说明 是 要查询数据 -->
									<input id="dishForm.query" name='dishForm.query' type='hidden' value="true" />
								</td>
								<td>
									<button class="btn1"
										onclick="window.location.href = creatUrlByForm(document.getElementsByTagName('FORM')[0]);">
										搜索
									</button>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</form>
		<div class="tform" style="width:80%">
			<button type='button' class="btn1" onclick="location.href='../dish/getDishTypeList'">
				新增菜名
			</button>
			<button type='button' class="btn1" onclick="deleteDish();">
				删除菜名
			</button>
		</div>


		<table class="tlist" id="recordlist" style="width:80%">
			<thead>
				<tr class="title">
					<th align="left" style="width:10%">
						全选
						<input type="checkbox" onclick="SelectAllCheckBox('wait_checked',this)" class="allCheck" />
					</th>
					<th align="left" style="width:10%">
						序号
					</th>
					<th align="left" style="width:10%">
						菜名编号
					</th>
					<th align="left" style="width:10%">
						菜名
					</th>
					<th align="left" style="width:10%">
						价格
					</th>
					<th align="left" style="width:10%">
						所属种类
					</th>
					<th align="left" style="width:10%">
						操作
					</th>
				</tr>
			</thead>
			<tbody>

			</tbody>
		</table>
		<div class="pagelist" style="width:90%">
			<div id="Pagination" style="float: right"></div>
		</div>
	</body>
	<script type="text/javascript">
	
	</script>


</html>