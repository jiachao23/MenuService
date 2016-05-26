<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'reportindex.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="css/layout.css">
	 <link rel="stylesheet" type="text/css" href="css/easyui_themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="css/easyui_themes/icon.css">
<!-- <link rel="stylesheet" type="text/css" href="css/jquery.dataTables/jquery.dataTables.css" />
 <link rel="stylesheet" type="text/css" href="css/jquery.dataTables/jquery.dataTables.min.css" />
 <link rel="stylesheet" type="text/css" href="css/jquery.dataTables/jquery.dataTables_themeroller.css" /> -->
<script type="text/javascript" src="js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
<!-- <script type="text/javascript" language="javascript" src="js/jquery.dataTables/jquery.js"></script>
	<script type="text/javascript" language="javascript" src="js/jquery.dataTables/jquery.dataTables.js"></script> -->
 <script language="javascript" src="js/LodopFuncs.js"></script>
 <script language="javascript" src="js/jquery.jPrintArea.js"></script>
 <!-- <script language="javascript" src="js/jquery.js"></script> -->
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
 
 <!-- <script language="javascript">
 	function search(){
			alert("qq");
	}
 </script> -->
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
        <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">导出</a>
        <a href="#" id="help" class="easyui-linkbutton" data-options="iconCls:'icon-help'">帮助</a>
  <!--  <a href="javascript:printme()">打印111</a>
    <a href="javascript:prn1_print()">打印222</a> 
    <a href="javascript:prn1_preview()">打印222预览</a> -->
    </div>
  </div><!-- 头部结束 -->
  
    <!-- 主体开始 -->
  <div id="mainContent">  
  <table id="dg" class="easyui-datagrid" title="查询结果" style="width:900px;height:500px"
            data-options="singleSelect:true,collapsible:false,url:'client/getOrderItem',method:'get'">
        	<thead>
            <tr>                 
                <th field="id" width="150" align="center" >序号</th>
                <th field="table_No" width="160" align="center" sortable="true">桌号</th>
                <th field="table_Volum" width="160" align="center" sortable="true">人数</th>
                <th field="table_Status" width="160" align="center" sortable="true">状态</th>
                <th field="table_Name" width="160" align="center">桌名</th>         
            </tr>
        </thead>
    </table>
  </div><!-- 主体结束 -->
</div>
  </body>
  <script language="javascript" type ="text/javascript">  
  $("div#biuuu_button").click(function(){ 
	$("div#mainContent").printArea(); 
	});
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
			 	alert("ok"); 	
			 	/* 开始异步请求 */
			 	$.ajax({  
					 url:"client/getSearchResult",  
					 type: "GET",  
					 dataType: "json",  
					 data: { "type":type,"formdate":fromdate,"todate":todate},
					 success: function(data){  
					 alert("dddd");  
					//调用创建表和填充动态填充数据的方法.  
					 //检查后台返回的数据               
					//createShowingTable(data)  
					},  
					error: function() {  						  
						alert("error");  
					}  
				});  /* 结束异步ajax请求   */		 	
			} 
		});		
</script>
  <!-- <script language="javascript" type ="text/javascript">  
        var LODOP; //声明为全局变量
       function prn1_preview() {  
             CreateOneFormPage();       
             LODOP.PREVIEW();    
       };
        function prn1_print() {          
             CreateOneFormPage();
             LODOP.PRINT();      
       };
        function prn1_printA() {         
             CreateOneFormPage();
             LODOP.PRINTA();     
       };     
        function CreateOneFormPage(){
        	alert("");
             LODOP=getLodop(); 
             LODOP.PRINT_INIT( "打印控件功能演示_Lodop功能_表单一" );
           /*   var strBodyStyle="<style>table { border:1px;width: 100%;margin: 0 auto;clear: both;border-collapse:separate;border-spacing: 0; }</style>"; */
             
		      /* var strStyleCSS="<link href='css/jquery.dataTables/jquery.dataTables.css' type='text/css' rel='stylesheet'>"; */ 
               //tableStyle的与要打印的内容Form的样式id一致
               var strBodyStyle="<style>"+document.getElementById( "dg" ).innerHTML+"</style>";   
               //formContent的与要打印的内容Form的id一致 
              var strFormHtml=strBodyStyle+ "<body>" +document.getElementById("dg").innerHTML+ "</body>" ;
             LODOP.PRINT_INIT( "打印控件功能演示_Lodop功能_样式风格" );    
              //LODOP.ADD_PRINT_TEXT(50,50,260,39,"打印与显示样式一致：");
             LODOP.ADD_PRINT_HTM(88,50,300,200,strFormHtml);
              //LODOP.PREVIEW();
 };   
                   
</script> -->
  
</html>
