<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>部门列表</title>
<#include "../../framework/comm/meta.ftl">
</head>
<body onended="initTable('sys_depart_dg','sys_depart_dg_search');">


<table title="Products" class="easyui-treegrid" style="width:700px;height:300px"
		url="treegrid3_getdata.php"
		rownumbers="true"
		idField="id" treeField="name">
		<thead>
			<tr>
				<th data-options="field:'departId',checkbox:true">部门标识</th>
				<th field="departPid" sortable="true">部门父标识</th>
				<th field="companId" sortable="true">公司标识</th>
				<th field="name" sortable="true">名称</th>
				<th field="code" sortable="true">编码</th>
				<th field="priori" sortable="true">同级优先级</th>
				<th field="status" sortable="true">状态</th>
				<th field="remark" sortable="true">备注</th>
			</tr>
		</thead>
</table>

	<table id="sys_depart_dg" title="部门列表" class="easyui-datagrid"
		style="height: 500px; width: auto;" url="./searchList"
		toolbar="#sys_depart_toolbar" pagination="true" rownumbers="false"
		fitColumns="true" singleSelect="false" selectOnCheck="true"
		checkOnSelect="true">
		<thead>
			<tr>
				<th data-options="field:'departId',checkbox:true">部门标识</th>
				<th field="departPid" sortable="true">部门父标识</th>
				<th field="companId" sortable="true">公司标识</th>
				<th field="name" sortable="true">名称</th>
				<th field="code" sortable="true">编码</th>
				<th field="priori" sortable="true">同级优先级</th>
				<th field="status" sortable="true">状态</th>
				<th field="remark" sortable="true">备注</th>
			</tr>
		</thead>
	</table>
	<div id="sys_depart_toolbar">
		<div style="margin-bottom: 5px">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-add" plain="true" onclick="newinsert('dlg','fm','新建')">新建</a>
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-edit" plain="true"
				onclick="newedit('dlg','fm','sys_depart_dg','修改')">修改</a> <a
				href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-remove" plain="true"
				onclick="newremove('sys_depart_dg','./remove','departId')">删除</a> <a
				href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-edit" plain="true"
				onclick="newshow('showdlg','showfm','sys_depart_dg','查看')">查看</a>
		</div>
		<div>
			<form id="sys_depart_dg_search">
				名称:<input id="search_name" /> 编码:<input
					id="search_code" /> <a href="#"
					onclick="datagridSearch('sys_depart_dg','sys_depart_dg_search')"
					class="easyui-linkbutton" iconCls="icon-search">查询</a> <a href="#"
					onclick="resetPage('sys_depart_dg_search')"
					class="easyui-linkbutton" iconCls="icon-reload">重置</a>
			</form>
		</div>
	</div>

	<div id="dlg" class="easyui-dialog"
		style="width: 500px; height: auto; padding: 10px 20px" closed="true"
		buttons="#dlg-buttons">
		<div class="ftitle">部门详细</div>
		<form id="fm" method="post" novalidate>
			<input type="hidden" name="departId" />
			<div class="fitem">
				<label>部门父标识:</label> <input name="departPid"
					class="easyui-validatebox" validType="" />
			</div>
			<div class="fitem">
				<label>公司标识:</label> <input name="companId"
					class="easyui-validatebox" validType="" />
			</div>
			<div class="fitem">
				<label>名称:</label> <input name="name" class="easyui-validatebox"
					required="true" validType="" />
			</div>
			<div class="fitem">
				<label>编码:</label> <input name="code" class="easyui-validatebox"
					required="true" validType="" />
			</div>
			<div class="fitem">
				<label>同级优先级:</label> <input name="priori"
					class="easyui-validatebox" validType="" />
			</div>
			<div class="fitem">
				<label>状态:</label> <input name="status" class="easyui-validatebox"
					validType="" />
			</div>
			<div class="fitem">
				<label>备注:</label> <input name="remark" class="easyui-validatebox"
					validType="" />
			</div>

		</form>
	</div>
	<div id="dlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-ok"
			onclick="newsave('dlg','fm','sys_depart_dg','./save')">保存</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">取消</a>
	</div>


	<div id="showdlg" class="easyui-dialog"
		style="width: 500px; height: auto; padding: 10px 20px" closed="true"
		buttons="#showdlg-buttons">
		<div class="ftitle">部门详细</div>
		<form id="showfm" method="post" novalidate>
			<input type="hidden" name="departId" />
			<div class="fitem">
				<label>部门父标识:</label> <input name="departPid" readonly="readonly"
					style="border: 0px" />
			</div>
			<div class="fitem">
				<label>公司标识:</label> <input name="companId" readonly="readonly"
					style="border: 0px" />
			</div>
			<div class="fitem">
				<label>名称:</label> <input name="name" readonly="readonly"
					style="border: 0px" />
			</div>
			<div class="fitem">
				<label>编码:</label> <input name="code" readonly="readonly"
					style="border: 0px" />
			</div>
			<div class="fitem">
				<label>同级优先级:</label> <input name="priori" readonly="readonly"
					style="border: 0px" />
			</div>
			<div class="fitem">
				<label>状态:</label> <input name="status" readonly="readonly"
					style="border: 0px" />
			</div>
			<div class="fitem">
				<label>备注:</label> <input name="remark" readonly="readonly"
					style="border: 0px" />
			</div>

		</form>
	</div>
	<div id="showdlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-cancel"
			onclick="javascript:$('#showdlg').dialog('close')">关闭</a>
	</div>
</body>
</html>