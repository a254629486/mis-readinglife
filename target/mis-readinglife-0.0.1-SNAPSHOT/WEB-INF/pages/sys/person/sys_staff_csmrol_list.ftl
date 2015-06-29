<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>定制权限列表</title>
<#include "../../framework/comm/meta.ftl">
</head>
<body
	onended="initTable('sys_staff_csmrol_dg','sys_staff_csmrol_dg_search');"  style="padding-left: 10px;">
	<div style="margin:10px 0;">
		<a href="#" class="easyui-linkbutton" onclick="javascript:window.parent.saverole(getTableItemDate('right_data','roleId'))">保存</a> 
	</div>
  <div style="float: left;">
  <table id="left_data" class="easyui-datagrid" title="待选角色"
		style="height: auto; width: 210px;" url="./searchList?staffId=${staffId }"
		  rownumbers="false" fitColumns="true"
		singleSelect="true" selectOnCheck="true" checkOnSelect="true">
		<thead>
			<tr>
				<th data-options="field:'roleId',hidden:true">角色主键</th>
				<th field="name" sortable="true">角色名称</th>
				<th field="code" sortable="true">角色代码</th>
				<th field="status" sortable="true" data-options=" formatter:typeFormatter " >角色状态</th>
			</tr>
		</thead>
	</table>
	</div>
	 <div style="float: left;height:150px;padding-top: 30px;padding-left: 10px;padding-right: 10px;">
		<button onclick="tableDateMove('all','left_data','right_data')">&lt;&lt;</button><br/>
		<button onclick="tableDateMove('','left_data','right_data')">&lt;&nbsp;</button><br/>
		<button onclick="tableDateMove('','right_data','left_data')">&gt;&nbsp;</button><br/>
		<button onclick="tableDateMove('all','right_data','left_data')">&gt;&gt;</button>
	</div>
	 <div style="float: left;">
		  <table id="right_data" class="easyui-datagrid" title="已有角色"
		style="height: auto; width: 210px;" url="./searchSelectedList?staffId=${staffId}"
		  rownumbers="false" fitColumns="true"
		singleSelect="true" selectOnCheck="true" checkOnSelect="true">
		<thead>
			<tr>
				<th data-options="field:'roleId',hidden:true">角色主键</th>
				<th field="name" sortable="true">角色名称</th>
				<th field="code" sortable="true">角色代码</th>
				<th field="status" sortable="true" data-options=" formatter:typeFormatter " >角色状态</th>
			</tr>
		</thead>
	</table>
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