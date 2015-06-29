<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>人员列表</title>
<#include "../../framework/comm/meta.ftl">
</head>
<body onended="initTable('sys_person_dg','sys_person_dg_search');">
	<table id="sys_person_dg"  class="easyui-datagrid"
		style="height: auto; width: auto;" url="${basePath}/sysPersonController/searchList"
		toolbar="#sys_person_toolbar" pagination="true" rownumbers="false"
		fitColumns="true" singleSelect="true" selectOnCheck="true"
		checkOnSelect="true">
		<thead>
			<tr>
				<th data-options="field:'personId',hidden:true,width:80">人员标识</th>
				<th data-options="field:'loginName',sortable:true,width:80"  >登陆名</th>
				<th data-options="field:'name',sortable:true,width:80" >姓名</th>
				<th field="status"  data-options=" formatter:typeFormatter,width:80 " sortable="true">状态</th>
				<th field="qq"  data-options=" width:80, formatter:function(val,row){
															return '<a href=\'javascript:void(0)\'  onclick=window.parent.${methodparam}('+JSON.stringify(row)+') >选择确认</a>';													
													}
							   " sortable="true">操作</th>
			</tr>
		</thead>
	</table>
	<div id="sys_person_toolbar">
		<div style="margin-bottom: 5px">
			 
		</div>
		<div>
			<form id="sys_person_dg_search">
				登陆名:<input id="search_loginName" /> 姓名:<input
					id="search_name" /> 
				<a href="#"
					onclick="datagridSearch('sys_person_dg','sys_person_dg_search')"
					class="easyui-linkbutton" iconCls="icon-search">查询</a> <a href="#"
					onclick="resetPage('sys_person_dg_search')"
					class="easyui-linkbutton" iconCls="icon-reload">重置</a>
			</form>
		</div>
	</div>

			<script type="text/javascript">
				var _status =null;
				$(function(){
					$.ajax({
						   type: "POST",
						   url: '${basePath}/sysConstantsController/getTypeList/ROLE',
						   success: function(msg){
							   _status =  eval('(' + msg + ')');
						      // alert( "Data Saved: " + status );
						   }
						}); 
				});
				function typeFormatter (value,row,index){
						//alert(_status.length);
						for(var i=0;i<_status.length;i++){
				     		 val = _status[i]['id'];
				     		// alert((val!=null&&val!=''&&val==value));
				     		if(val!=null&&val!=''&&val==value){
				     			 return _status[i]['text'];
				     		}
				     	}
				}
			
			</script>
</body>
</html>