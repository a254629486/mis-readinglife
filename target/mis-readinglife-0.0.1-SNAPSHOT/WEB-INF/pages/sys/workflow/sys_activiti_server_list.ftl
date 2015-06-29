<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>工作流服务器列表</title>
	<#include "../../framework/comm/meta.ftl">
</head>
<body
	onended="initTable('sys_activiti_server_dg','sys_activiti_server_dg_search');">
	<div class="part">
			<form id="sys_activiti_server_dg_search">
				<p><label>服务器地址:</label><input type="text"  id="search_serverIp" /></p>
				<p><label>服务器名称:</label><input type="text"  id="search_name" /> </p>
				<div class="part-buttons"> 	
				<a href="#"
					onclick="datagridSearch('sys_activiti_server_dg','sys_activiti_server_dg_search')"
					class="easyui-linkbutton"  >查询</a> <a href="#"
					onclick="resetPage('sys_activiti_server_dg_search')"
					class="easyui-linkbutton" >重置</a>
				</div>
			</form>
		</div>
	<table id="sys_activiti_server_dg" title="工作流服务器列表"
		class="easyui-datagrid" style="height: 500px; width: auto;"
		url="./searchList" toolbar="#sys_activiti_server_toolbar"
		pagination="true" rownumbers="false" fitColumns="true"
		singleSelect="false" selectOnCheck="true" checkOnSelect="true">
		<thead>
			<tr>
				<th data-options="field:'serverId',checkbox:true"></th>
				<th field="serverIp" sortable="true">服务器地址</th>
				<th field="name" sortable="true">服务器名称</th>
				<th field="status" data-options=" formatter:typeFormatter " sortable="true">状态</th>
			</tr>
		</thead>
	</table>
	<div id="sys_activiti_server_toolbar">
		<div style="margin-bottom: 5px">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-add" plain="true" onclick="newinsert('dlg','fm','新建')">新建</a>
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-edit" plain="true"
				onclick="newedit('dlg','fm','sys_activiti_server_dg','修改')">修改</a> <a
				href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-remove" plain="true"
				onclick="newremove('sys_activiti_server_dg','./remove','serverId')">删除</a>
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-edit" plain="true"
				onclick="newshow('showdlg','showfm','sys_activiti_server_dg','查看')">查看</a>
		</div>
	</div>

	<div id="dlg" class="easyui-dialog"
		style="width: 500px; height: auto; padding: 10px 20px" closed="true" >
	<div class="part-toolbar">
		<a href="javascript:void(0)" class="easyui-linkbutton"  data-options="iconCls:'icon-save',plain:true"
			onclick="newsave('dlg','fm','sys_activiti_server_dg','./save')">保存</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true"
			 onclick="javascript:$('#dlg').dialog('close')">取消</a>
	</div>
		
		 <div class="part">
			    <div class="part-title">工作流服务器详细</div>
		<form id="fm" method="post" novalidate>
			<input type="hidden" name="serverId" />
			<p>
				<label>服务器地址:</label> <input type="text"  name="serverIp"
					class="easyui-validatebox" required="true" validType="" />
			</p>
			<p>
				<label>服务器名称:</label> <input type="text"  name="name" class="easyui-validatebox"
					required="true" validType="" />
			</p>
			<p>
				<label>状态:</label> <input type="text"  class="easyui-combobox easyui-validatebox"	 
									name="status"
									data-options="
									url:'${basePath}/sysConstantsController/getTypeList/WORKFLOW',
									method:'get',
									valueField:'id',
									textField:'text',
									panelHeight:'auto' 
									">
			</p>

		</form>
		</div>
	</div>



	<div id="showdlg" class="easyui-dialog"
		style="width: 500px; height: auto; padding: 10px 20px" closed="true" >
	<div class="part-toolbar">
		<a href="javascript:void(0)" class="easyui-linkbutton"  data-options="iconCls:'icon-cancel',plain:true"
			onclick="javascript:$('#showdlg').dialog('close')">关闭</a>
	</div>
		
		<div class="part">
			    <div class="part-title">工作流服务器详细</div>
		<form id="showfm" method="post" novalidate>
			<input type="hidden" name="serverId" />
			<p>
				<label>服务器地址:</label> <input type="text"  name="serverIp" readonly="readonly"
					style="border: 0px" />
			</p>
			<p>
				<label>服务器名称:</label> <input type="text"  name="name" readonly="readonly"
					style="border: 0px" />
			</p>
			<p>
				<label>状态:</label> <input type="text"  class="easyui-combobox"	  readonly="readonly"
									name="status"
									data-options="
									url:'${basePath}/sysConstantsController/getTypeList/WORKFLOW',
									method:'get',
									valueField:'id',
									textField:'text',
									panelHeight:'auto'
									">
			</p>

		</form>
	</div>

	
	<script type="text/javascript">
				var _status =null;
				$(function(){
					$.ajax({
						   type: "POST",
						   url: '${basePath}/sysConstantsController/getTypeList/WORKFLOW',
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