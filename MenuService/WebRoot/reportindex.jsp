<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>点菜系统报表浏览中心</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/layout.css">
	 <link rel="stylesheet" type="text/css" href="css/easyui_themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="css/easyui_themes/icon.css">
<script type="text/javascript" src="js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
 <script language="javascript" src="js/LodopFuncs.js"></script>
 <script language="javascript" src="js/jquery.jPrintArea.js"></script>
<!--  <script language="javascript" src="js/json-to-table.js"></script>
  <script language="javascript" src="js/json2.js"></script> -->
  </head>
  <!-- datatable属性配置 -->
  
	<!-- 日期选择框的样式设计 -->
	<script type="text/javascript">
		
        function myformatter(date){
            var y = date.getFullYear();
            var m = date.getMonth()+1;
            var d = date.getDate();
            return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);
        }
        function myparser(s){
            if (!s) return new Date();
            var ss = (s.split('-'));
            var y = parseInt(ss[0],10);
            var m = parseInt(ss[1],10);
            var d = parseInt(ss[2],10);
            if (!isNaN(y) && !isNaN(m) && !isNaN(d)){
                return new Date(y,m-1,d);
            } else {
                return new Date();
            }
        }
    </script>
 
  <body>
  <div id="container">
  <!-- 头部开始 -->
  <div id="header">
  		<div class="easyui-panel" style="padding:5px;">      
			  报表类型：
			  <select id="cmbtype" class="easyui-combobox" name="state" style="width:200px;">
			    <option value="DP1">日报表-餐桌浏览</option>
        		<option value="DP2">日报表-餐桌汇总</option>
        		<option value="MP1">月报表-餐桌浏览</option>
        		<option value="MP2">月报表-餐桌汇总</option>  	
        	</select>
                                   起始时间：<input id="fromdate" class="easyui-datebox" data-options="formatter:myformatter,parser:myparser"></input>
    		截止时间：<input id="todate" class="easyui-datebox" data-options="formatter:myformatter,parser:myparser"></input>
    		<div id="searchbtn" class="easyui-linkbutton" data-options="plain:'true',iconCls:'icon-search'">浏览</div>
            </div>
    		 <div class="easyui-panel" style="padding:5px;">
        <div id="biuuu_button" class="easyui-linkbutton" data-options="iconCls:'icon-print'">打印</div> 
        <!-- <a id="printme"href="javascript:printme()" class="easyui-linkbutton" data-options="iconCls:'icon-print'">打印预览</a>
        <a href="javascript:prn1_print()" class="easyui-linkbutton" data-options="iconCls:'icon-print'">打印</a> -->
        <!-- <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">导出</a> -->
        <div id="refresh"  class="easyui-linkbutton" data-options="iconCls:'icon-edit'">刷新</div>
        <a href="#" id="help" class="easyui-linkbutton" data-options="iconCls:'icon-help'">帮助</a>
    </div>
  </div><!-- 头部结束 -->
    <!-- 主体开始 -->
  <div id="mainContent">  
  <table id="dg" class="easyui-datagrid" title="查询结果" style="width:1000px;height:500px"
        	 data-options="singleSelect:true,collapsible:false,pagination:true,rownumbers:true">
        	<thead>
           <!--  <tr>                 
                <th field="id" width="150" align="center" >序号</th> 
                <th field="桌号" width="160" align="center" sortable="true">桌号</th>
                <th field="订单时间" width="160" align="center" sortable="true">订单时间</th>
                <th field="消费金额" width="160" align="center" sortable="true">消费金额</th>
                <th field="消费人数" width="160" align="center">消费人数</th>         
            </tr> -->
        </thead>
    </table>
  </div><!-- 主体结束 -->
</div>
<div >
<table id="jsonTable">
</table>
</div>
  </body>
  <script language="javascript" type ="text/javascript">  
  $("div#biuuu_button").click(function(){ 
	$("div#mainContent").printArea(); 
	});
	$("div#refresh").click(function(){ 
		 $("#dg").datagrid('reload');
		/* alert("up");  */
		
	});
  </script>
  <script language="javascript" type ="text/javascript">  
 		function loadDataToGrid(type,fromdate,todate){
 			if(type=='DP1')
 			{
 			var urlstring = "client/getSearchResult?type=DP1&formdate="+fromdate+"&todate="+todate;
 			$("#dg").datagrid({
    			url:urlstring,
    			columns:[[
        		{field:'桌号',title:'桌号',width:200,align:'center'},
        		{field:'订单时间',title:'订单时间',width:200,align:'center'},
        		{field:'消费金额',title:'消费金额',width:200,align:'center'},
        		{field:'消费人数',title:'消费人数',width:200,align:'center'}
    		]]
			});
			}/* if(type=='DP1')结束 */
			
			if(type=='DP2')
 			{
 			var urlstring = "client/getSearchResult?type=DP2&formdate="+fromdate+"&todate="+todate;
 			$("#dg").datagrid({
    			url:urlstring,
    			columns:[[
        		{field:'日期',title:'日期',width:200,align:'center'},
        		{field:'桌号',title:'桌号',width:200,align:'center'},
        		{field:'消费总额',title:'消费总额',width:200,align:'center'}     		
    		]]
			});
			}/* if(type=='DP2')结束 */
			
			if(type=='MP1')
 			{
 			var urlstring = "client/getSearchResult?type=MP1&formdate="+fromdate+"&todate="+todate;
 			$("#dg").datagrid({
    			url:urlstring,
    			columns:[[
        		{field:'月份',title:'月份',width:200,align:'center'},
        		{field:'日期',title:'日期',width:200,align:'center'},
        		{field:'桌号',title:'桌号',width:200,align:'center'},
        		{field:'日消费总额',title:'日消费总额',width:200,align:'center'}      		
    		]]
			});
			}/* if(type=='DP3')结束 */
			
			if(type=='MP2')
 			{
 			var urlstring = "client/getSearchResult?type=MP2&formdate="+fromdate+"&todate="+todate;
 			$("#dg").datagrid({
    			url:urlstring,
    			columns:[[
        		{field:'月份',title:'月份',width:200,align:'center'},        		
        		{field:'桌号',title:'桌号',width:200,align:'center'},
        		{field:'月消费总额',title:'月消费总额',width:200,align:'center'}      		
    		]]
			});
			}/* if(type=='DP4')结束 */
			$("#dg").datagrid('reload');
 		}
  </script>
     <script language="javascript">
		$("div#searchbtn").click(function(){               
			var type = $('#cmbtype').combobox('getValue');
			var fromdate = $('#fromdate').datebox('getValue');
			var todate = $('#todate').datebox('getValue');
			if(fromdate==""||todate==""){	
			 	$.messager.alert('提示','请输入起始日期','warning'); 
			 	return; 	
			 }
			else
			{
			 	/* alert(type); 	 */
			 	loadDataToGrid(type,fromdate,todate);
			} 
		});		
</script>
<script language="javascript">
/* $(function(){
  			//设置分页控件  
    		var p = $('#dg').datagrid('getPager');  
    		$(p).pagination({  
        	pageSize: 10,//每页显示的记录条数，默认为10  
        	pageList: [5,10,15],//可以设置每页记录条数的列表  
        	beforePageText: '第',//页数文本框前显示的汉字  
        	afterPageText: '页    共 {pages} 页',  
        	displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录',  
        });  
	}); */
</script>
</html>
