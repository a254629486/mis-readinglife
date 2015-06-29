<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>数据权限</title>
<#include "../../framework/comm/meta.ftl">
</head>
<body >

	<table id="t1"  class="easyui-datagrid"
		style="height: auto; width: auto;" url="${basePath}/sysData/dataList"
		toolbar="#sys_person_toolbar" pagination="true" rownumbers="false"
		fitColumns="true" singleSelect="true" selectOnCheck="true"
		checkOnSelect="true">
		<thead>
			<tr>
				<th data-options="field:'roleName',width:80">数据角色名</th>
				<th data-options="field:'TABLE_CODE',sortable:true,width:100">表名</th>
				<th data-options="field:'COLUMN_CODE',sortable:true,width:180" >列名</th>
				<th data-options="field:'ROLE',sortable:true,width:380" >规则</th>
				<th data-options="field:'STATUS',sortable:true,width:80,formatter:function(value,row,index){
					if(row.STATUS == '1') return '启用';
					return '未启用';
				}" >状态</th>
			</tr>
		</thead>
	</table>
	<div id="sys_person_toolbar">
		<div style="padding:20px 0px 10px 0px;border:solid 0px red;">
			<form id="sys_person_dg_search">
				数据角色:<input id="roleName" maxLength="30" class="input-text" /> 表名:<input id="tableName"  maxLength="30" class="input-text" /> 
				<a href="#" onclick="datagridSearch()" class="easyui-linkbutton" iconCls="icon-search">查询</a> 
				<a href="#"	onclick="javascript:document.getElementById('sys_person_dg_search').reset();"	class="easyui-linkbutton" iconCls="icon-reload">重置</a>
			</form>
			<div id="toolbar">
				<a href="#" onclick="$('#form1').form('clear');$('#w').window('open');flag=1;"; class="easyui-linkbutton" iconCls="icon-add" plain="true" ">新建</a>
				<a href="javascript:edit()" class="easyui-linkbutton" iconCls="icon-edit" plain="true" >修改</a>
				<a href="javascript:remove();" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
			</div>
		</div>
	</div>
	<div id="w" class="easyui-window" title="数据权限配置" data-options="closed:true,modal:true"  style="width:600px;height:400px;padding:10px;" >
		<form id="form1" name="form1"  method="post" >
		<input type="hidden" name="dataPrivilId" id="dataPrivilId" />
		<input type="hidden" name="tableCode" id="tableCode" />
		<input type="hidden" name="tableName" id="tableName" />
		<input type="hidden" name="columnCode" id="columnCode" />
		<input type="hidden" name="columnName" id="columnName" />
		<table>
			<tr>
				<td>数据角色：</td>
				<td>
					<select class="easyui-combobox" name="dataRoleId" id="dataRoleId" style="width:200px;">
						<#list roleList as s>
							<option value="${s.dataRoleId}">${s.name}</option>
						</#list>
					</select>
				</td>
			</tr>
			<tr>
				<td>约束表：</td>
				<td>
					<select id="s"  class="easyui-combogrid" style="width:250px" data-options="
						panelWidth: 500,
						url: '${basePath}/sysData/tableList',
						method: 'get',
						editable:false,
						idField:'id',
						textField:'id',
						columns: [[
						{field:'TABLE_SCHEMA',title:'数据库名称',width:80,align:'center'},
						{field:'TABLE_NAME',title:'表名',width:80,align:'center'},
						{field:'TABLE_COMMENT',title:'表名描述',width:100,align:'center'},
						]],
						fitColumns: true
						">
					</select>
				</td>
			</tr>
			<tr>
				<td>约束列：</td>
				<td>
					<select id="r" class="easyui-combogrid" name="r" style="width:200px;">
					</select>
				</td>
			</tr>
			<tr>
				<td>规则：</td>
				<td>
					<textarea name="role" id="role" col=50 rows=90></textarea>
				</td>
			</tr>
		</table>
		<a href="#" onclick="save();" class="easyui-linkbutton" iconCls="icon-search">保存</a>
		<a href="#" onclick="javascript:$('#w').window('close');" class="easyui-linkbutton" iconCls="icon-search">取消</a>
		<p>
								#ISNULL:当前字段 is null<br>
						#NOTNULL:当前字段 is not null<br>
						&: and<br>
						||:or<br>
						#gt: ><br>
						#lt: <<br>
						#this: 当前字段<br>
						#sql: 自定义sql<br>
					示例：#this #gt 100 & #this = 50 || #this < 20 & #ISNULL || #NOTNULL 
		</p>
		</form>
	</div>
</body>
</html>
<script type="text/javascript">
	var flag = 0;
	$("#s").combogrid({
		onClickRow:function(rowIndex, rowData){
			//$("#s").combogrid('setValue',rowData.TABLE_SCHEMA + "," +rowData.TABLE_NAME);
			$("#s").combogrid('setText',rowData.TABLE_SCHEMA + "  --> " +rowData.TABLE_NAME);
			$("#tableCode").val(rowData.TABLE_NAME);
			$("#tableName").val(rowData.TABLE_COMMENT);
			getColumn(rowData.TABLE_SCHEMA,rowData.TABLE_NAME);
		}
	});
	function getColumn(dbname,tablename){
		$("#r").combogrid({
			 panelWidth: 300,
			 method: 'get',
			 editable:false,
			 url:'${basePath}/sysData/coloumnList?dbname='+dbname+'&tablename='+tablename,
		 	 columns: [[
				{field:'COLUMN_NAME',title:'字段名',width:80,align:'center'},
				{field:'COLUMN_COMMENT',title:'字段描述',width:100,align:'center'},
			 ]],
			 fitColumns: true,
			 idField:'COLUMN_NAME',
			 textField:'COLUMN_NAME',
			 onClickRow:function(rowIndex, rowData){
			 	$("#columnCode").val(rowData.COLUMN_NAME);
				$("#columnName").val(rowData.COLUMN_COMMENT);
			 }
		});	
	}
	function datagridSearch(){
	    $('#t1').datagrid('load', 
	    	{
	    		roleName:$("#roleName").val(),
	    		tableName:$("#tableName").val()
	    	}
	    );
	}
	function save(){
		var url = "";
		if(flag==1){
			url = "${basePath}/sysData/save";
		}else if(flag==2){
			url = "${basePath}/sysData/update";
			setValue();
		}
	
		$('#form1').form('submit',{
			url:url,
			success:function(data){
				var data = eval('('+data+')');
				if(data.success){
					$('#w').window('close');
					 $.messager.show({
							title:'消息提示',
							msg:data.msg,
							timeout:3000,
							showType:'show'
						});				
				}else{
					 $.messager.alert('消息提示','操作失败!','error');
				}
			}
		
		});
	}
	function edit(){
		flag=2;
		var row = $("#t1").datagrid('getSelected');
		var dataPrivilId = row.DATA_PRIVIL_ID;
		var roleName = row.roleName;
		var column = row.COLUMN_CODE;
		var tName = row.TABLE_CODE;
		var dbname = "b2b_"+(tName.split("_"))[0];
		$("#s").combogrid('setValue',dbname+","+tName);	
		$("#dataPrivilId").val(dataPrivilId);	
		getColumn(dbname,tName);
		$("#r").combogrid('setValue',column);
		$("#role").val(row.ROLE);
		//setValue();
		$('#w').window('open');
	}
	function setValue(){
		var g = $('#s').combogrid('grid')
		var row = g.datagrid('getSelected');
		$("#tableCode").val(row.TABLE_NAME);
		$("#tableName").val(row.TABLE_COMMENT);
		var cg = $('#r').combogrid('grid');
		var cRow = cg.datagrid('getSelected');
		$("#columnCode").val(cRow.COLUMN_NAME);
		$("#columnName").val(cRow.COLUMN_COMMENT);
	}
	
	function remove(){
		var row = $("#t1").datagrid('getSelected');
		var dataPrivId = row.DATA_PRIVIL_ID;
		$('#form1').form('submit',{
			url:"${basePath}/sysData/remove?dataPrivId="+dataPrivId,
			success:function(data){
				var data = eval('('+data+')');
				if(data.success){
					$('#w').window('close');
					 $.messager.show({
							title:'消息提示',
							msg:data.msg,
							timeout:3000,
							showType:'show'
						});				
				}else{
					 $.messager.alert('消息提示','操作失败!','error');
				}
			}
		
		});			
	}	

</script>
