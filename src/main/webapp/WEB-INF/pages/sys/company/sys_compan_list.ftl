<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>公司列表</title>
	<#include "../../framework/comm/meta.ftl">
</head>
<body> 
	<div class="part">
			<form id="sys_compan_dg_search">
				<p><label>名称:</label><input type="text" id="search_name" /></p>
				<p><label>全名:</label><input type="text" id="search_fullName" /></p>
				<p><label>编码:</label><input type="text" id="search_code" /></p>
				<p><label>地址:</label><input type="text" id="search_addres" /></p>
				<div class="part-buttons"> 
				 <a href="#" onclick="datagridSearch('sys_compan_dg','sys_compan_dg_search')"
					class="easyui-linkbutton" >查询</a>
			     <a href="#" onclick="resetPage('sys_compan_dg_search')"
					class="easyui-linkbutton" >重置</a>
				</div>
			</form>
		</div>
 	<table id="sys_compan_dg" title="公司列表" class="easyui-datagrid"
		style="height: 500px; width: auto;" url="./searchList"
		toolbar="#sys_compan_toolbar" pagination="true" rownumbers="false"
		fitColumns="true" singleSelect="false" selectOnCheck="true"
		checkOnSelect="true">
		<thead>
			<tr>
				<th data-options="field:'companId',checkbox:true">公司标识</th>
				<th field="name" sortable="true">名称</th>
				<th field="fullName" sortable="true">全名</th>
				<th field="code" sortable="true">编码</th>
				<th field="addres" sortable="true">地址</th>
				<th field="priori" sortable="true">同级优先级</th>
				<th field="status"  data-options=" formatter:typeFormatter " sortable="true">状态</th>
				<th field="remark" sortable="true">备注</th>
			</tr>
		</thead>
	</table>
	<div id="sys_compan_toolbar">
		<div id="menubar" style="margin-bottom: 5px">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-add" plain="true" onclick="newinsert('dlg','fm','新建',{status:'1'})">新建</a>
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-edit" plain="true"
				onclick="newedit('dlg','fm','sys_compan_dg','修改')">修改</a> <a
				href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-remove" plain="true"
				onclick="newremove('sys_compan_dg','./remove','companId')">删除</a> <a
				href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-edit" plain="true"
				onclick="newshow('showdlg','showfm','sys_compan_dg','查看')">查看</a>
		</div>
	</div>

	<div id="dlg" class="easyui-dialog"
		style="width: 500px; height: auto; padding: 10px 20px" closed="true" >
		<div class="part-toolbar">
			<a href="javascript:void(0)"  class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true"
			onclick="newsave('dlg','fm','sys_compan_dg','./save')">保存</a> 
			<a href="javascript:void(0)"   class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true"
			 onclick="javascript:$('#dlg').dialog('close')">取消</a>
		</div>
	    <div class="part">
	    <div class="part-title">公司详细</div>
		<form id="fm" method="post" novalidate>
			<input type="hidden" name="companId" />
			  <p><label>名称:</label><input type="text" name="name" class="easyui-validatebox"
					required="true" validType="" />
			  </p>
			<p>
				<label>全名:</label> <input  type="text" type="text" name="fullName" class="easyui-validatebox"
					required="true" validType="" />
			</p>
			<p>
				<label>编码:</label> <input type="text" name="code" class="easyui-validatebox"
					required="true" validType="" />
			</p>
			<p>
				<label>地址:</label> <input type="text" name="addres" class="easyui-validatebox"
					validType="" />
			</p>
			<p>
				<label>同级优先级:</label> <input type="text" name="priori" required="true"
					class="easyui-validatebox" validType="number" />
			</p>
			<p>
				<label>状态:</label>  <input class="easyui-combobox easyui-validatebox"	 
									name="status"
									data-options="
									url:'${basePath}/sysConstantsController/getTypeList/COMPANY',
									method:'get',
									valueField:'id',
									textField:'text',
									panelHeight:'auto' 
									">
			</p>
			<p>
				<label>备注:</label> 
					<textarea rows="3" cols="10" style="width: 200px;" name="remark" class="easyui-validatebox">
					</textarea>
			</p>

		</form>
		</div>
	</div>

	<div id="showdlg" class="easyui-dialog"
		style="width: 500px; height: auto; padding: 10px 20px" closed="true" >
		<div class="part-toolbar">
		 <a href="javascript:void(0)"   class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true"
			onclick="javascript:$('#showdlg').dialog('close')">关闭</a>
		</div>
		<div class="part">
	    <div class="part-title">公司详细</div>
		<form id="showfm" method="post" novalidate>
			<input type="hidden" name="companId" />
			<div class="fitem">
				<label>名称:</label> <input name="name" readonly="readonly"
					style="border: 0px" />
			</div>
			<div class="fitem">
				<label>全名:</label> <input name="fullName" readonly="readonly"
					style="border: 0px" />
			</div>
			<div class="fitem">
				<label>编码:</label> <input name="code" readonly="readonly"
					style="border: 0px" />
			</div>
			<div class="fitem">
				<label>地址:</label> <input name="addres" readonly="readonly"
					style="border: 0px" />
			</div>
			<div class="fitem">
				<label>同级优先级:</label> <input name="priori" readonly="readonly"
					style="border: 0px" />
			</div>
			<div class="fitem show-combobox" >
				<label>状态:</label><input class="easyui-combobox"	  readonly="readonly"
									name="status"
									data-options="
									url:'${basePath}/sysConstantsController/getTypeList/COMPANY',
									method:'get',
									valueField:'id',
									textField:'text',
									panelHeight:'auto'
									">
			</div>
			<div class="fitem">
				<label>备注:</label> <input name="remark" readonly="readonly"
					style="border: 0px" />
			</div>

		</form>
		</div>
	</div>
 
	
		<script type="text/javascript">
				var _status =null;
				$(function(){
					$.ajax({
						   type: "POST",
						   url: '${basePath}/sysConstantsController/getTypeList/COMPANY',
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