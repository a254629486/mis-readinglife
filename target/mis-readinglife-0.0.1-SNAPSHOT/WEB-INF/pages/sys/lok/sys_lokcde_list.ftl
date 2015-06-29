<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>二级码表列表</title>
	<#include "../../framework/comm/meta.ftl">
</head>
<body onended="initTable('sys_lokcde_dg','sys_lokcde_dg_search');">
	<div class="part">
			<form id="sys_lokcde_dg_search">
				<p><label>二级编码:</label><input   type="text"  id="search_lokcde" /></p>
				<p><label> 一级编码:</label><input   type="text" id="search_loktpe" /> </p>
				<p><label>二级编码名称:</label><input  type="text"  id="search_lokcdeName" /> </p>
				<div class="part-buttons"> 	
				<a href="#"
					onclick="datagridSearch('sys_lokcde_dg','sys_lokcde_dg_search')"
					class="easyui-linkbutton" >查询</a> <a href="#"
					onclick="resetPage('sys_lokcde_dg_search')"
					class="easyui-linkbutton" >重置</a>
				</div>
			</form>
		</div>
	<table id="sys_lokcde_dg" title="二级码表列表" class="easyui-datagrid"
		style="height: 500px; width: auto;" url="./searchList"
		toolbar="#sys_lokcde_toolbar" pagination="true" rownumbers="false"
		fitColumns="true" singleSelect="false" selectOnCheck="true"
		checkOnSelect="true">
		<thead>
			<tr>
				<th data-options="checkbox:true">二级编码</th>
				<th field="lokcde" sortable="true">二级编码</th>
				<th field="loktpe" sortable="true">一级编码</th>
				<th field="lokcdeName" sortable="true">二级编码名称</th>
			</tr>
		</thead>
	</table>
	<div id="sys_lokcde_toolbar">
		<div style="margin-bottom: 5px">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-add" plain="true" onclick="newinsert('dlg','fm','新建')">新建</a>
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-edit" plain="true"
				onclick="newedit('dlg','fm','sys_lokcde_dg','修改')">修改</a> <a
				href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-remove" plain="true"
				onclick="newremove('sys_lokcde_dg','./remove','lokcde')">删除</a> <a
				href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-edit" plain="true"
				onclick="newshow('showdlg','showfm','sys_lokcde_dg','查看')">查看</a>
		</div>
	</div>

	<div id="dlg" class="easyui-dialog"
		style="width: 500px; height: auto; padding: 10px 20px" closed="true" >
		<div class="part-toolbar">
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true"
				onclick="newsave('dlg','fm','sys_lokcde_dg','./save')">保存</a> <a
				href="javascript:void(0)" class="easyui-linkbutton"   data-options="iconCls:'icon-cancel',plain:true"
				onclick="javascript:$('#dlg').dialog('close')">取消</a>
		</div>
			
		 <div class="part">
			    <div class="part-title">二级码表详细</div>
		<form id="fm" method="post" novalidate>
			<p>
				<label>二级编码:</label> <input  type="text"  name="lokcde" class="easyui-validatebox"
					required="true" validType="maxLength[3]" />
			</p>
			<p>
				<label>一级编码:</label>  
					<input  type="text"  class="easyui-combobox"
							style="border: 0px;" 
									name="loktpe"
									data-options="
									url:'${basePath}/sysLoktpeController/getLokcode',
									method:'get',
									valueField:'loktpe',
									textField:'loktpeName',
									panelHeight:'auto'
									">
			</p>
			<p>
				<label>二级编码名称:</label> <input  type="text"  name="lokcdeName"
					class="easyui-validatebox" required="true" validType="" />
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
			    <div class="part-title">二级码表详细</div>
		<form id="showfm" method="post" novalidate>
			<p>
				<label>二级编码:</label> <input  type="text"  name="lokcde" readonly="readonly"
					style="border: 0px" />
			</p>
			<p>
				<label>一级编码:</label> <input  type="text"  name="loktpe" readonly="readonly"
					style="border: 0px" />
			</p>
			<p>
				<label>二级编码名称:</label> <input  type="text"  name="lokcdeName" readonly="readonly"
					style="border: 0px" />
			</p>

		</form>
		</div>
	</div>

	
	<script type="text/javascript">
		
		
		
	</script>
</body>
</html>