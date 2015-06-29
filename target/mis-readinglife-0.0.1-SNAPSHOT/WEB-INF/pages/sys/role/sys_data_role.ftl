<!DOCTYPE html>
<html>
	<head>
		<title>pds-platform</title>
		<#include "../../framework/comm/meta.ftl">
	</head>
	<body>
		<div class="part">
			<form id="dg_search">
				<p><label>名称:</label><input  type="text"  id="search_name"  /></p>
				<p><label>编码:</label><input  type="text"  id="search_code" /></p>
				<p><label>状态:</label>
					<input id ="search_status"  type="text"  class="easyui-combobox easyui-validatebox"	 
							name="search_status"
							data-options="
							url:'${basePath}/sysConstantsController/getTypeList/ROLE?select=true',
							method:'get',
							valueField:'id',
							textField:'text',
							panelHeight:'auto',
							editable:false
							"></p>
				<div class="part-buttons"> 	
					<a href="#" onclick="datagridSearch('dg','dg_search')" class="easyui-linkbutton" >查询</a> 
					<a href="#" onclick="resetPage('dg_search')" class="easyui-linkbutton"  >重置</a>
				</div>
			</form>
		</div>
	    <div id="dg_toolbar">
		        <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newinsert('dlg','fm','新建')">新增</a>
				<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="dataedit()">修改</a>
				<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="dataremove()">删除</a>
				<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true"onclick="newshow('showdlg','showfm','dg','查看')">查看</a>
		</div>
	    
		<table id="dg" toolbar="#dg_toolbar" class="easyui-datagrid" data-options="singleSelect:true"></table> 
		
		<div id="dlg" class="easyui-dialog" style="width: 500px; height: auto; padding: 10px 20px" closed="true" buttons="#dlg-buttons">
			<div class="part">
			<div class="part-title">新增数据详细</div></br>
			<form id="fm" method="post" novalidate>
				<input name="dataRoleId"  id="dataRoleId"  type="hidden"/>
				<p>
					<label>名称：</label> 
						
						<input id="name"  type="text"  name="name" class="easyui-validatebox"
							required="true" validType="" maxLength="64"/>
				</p>
				<p>
					<label>编码：</label> 
						<input id="code"  type="text"  name="code" class="easyui-validatebox"
							required="true" validType="" maxLength="32"/>
				</p>
				<p>
						<label>状态：</label>
   							  <input  type="text"  class="easyui-combobox easyui-validatebox"	 
									name="status"
									id="status"
									data-options="
									url:'${basePath}/sysConstantsController/getTypeList/ROLE',
									method:'get',
									valueField:'id',
									textField:'text',
									panelHeight:'auto',
									editable:false
									">
					</p>
			</form></div>
	</div>
	<div id="dlg-buttons">
		<a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="addsave('dlg','fm','dg','./addsave')">保存</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">取消</a>
	</div>
	
	<div id="showdlg" class="easyui-dialog"
				style="width: 500px; height: auto; padding: 10px 20px" closed="true" >
			 	
				 <div class="part">
			    <div class="part-title">数据角色详细</div>
				<form id="showfm" method="post" novalidate>
					<input type="hidden" name="roleId" />
					<p>
						<label>名称:</label> <input  type="text"  name="name" readonly="readonly"
							style="border: 0px" />
					</p>
					<p>
						<label>编码:</label> <input  type="text"  name="code" readonly="readonly"
							style="border: 0px" />
					</p>
					<p>
						<label>状态:</label>  
							<input class="easyui-combobox"	 type="text"   readonly="readonly"
							style="border: 0px;width: 123px;" 
									name="status"
									id="status"
									data-options="
									url:'${basePath}/sysConstantsController/getTypeList/ROLE',
									method:'get',
									valueField:'id',
									textField:'text',
									panelHeight:'auto'
									">
					</p>

				</form></div>
	</div>
	</body>
	<script type="text/javascript">
		$('#dg').datagrid({   
			title:'数据角色列表',
		    url:'./dataRoleList', 
		    pagination:true,//分页控件  
		    rownumbers:true,
		    pageList: [10,20,50,100],
		    columns:[[
		    	{field:'dataRoleId',title:'数据角色标识',width:0,hidden:'true'},
		    	{field:'name',title:'名称',width:100},
		    	{field:'code',title:'编码',width:100},
		    	{field:'status',title:'状态',width:100,
		    		formatter : function(value,row,index){
		    			if(row.status==1){
		    		 		return '启用';
		    			 }else{
		    		 		return '关闭';
		    		 }
		    		 }
		    	},
		    ]]   
		}); 
		function addsave(dlg,fm,table,url) {
			var status = $('#status').combobox('getValue');
			var name1=$('#name').val();
			var name=name1.trim();
			var code1=$('#code').val();
			var code=code1.trim();
			if(name == "" || name == null||code == "" || code == null||status == "" || status == null){
				alert("输入框不能为空！");
				return;
			}
			if(name.length>64||code.length>32){
				alert("输入长度过长！");
				return;
			}
			$('#'+fm).form('submit', {
				url : url,
				onSubmit : function() {
					return $(this).form('validate');
				},
				success : function(msg) {
					$('#'+dlg).dialog('close');
					$('#dg').datagrid('load');
				}
			});
		}
		function dataedit(){ 
			var datarole=$('#dg').datagrid('getSelected');
			if(datarole==null||datarole==''){
				 jQuery.messager.alert('提示:','请选择一条数据!');   
				 return ; 
			}
			if (datarole) {
				$('#dlg').dialog('open').dialog('setTitle', '修改');
				$('#fm').form('load', datarole); 
			}
		}
	function dataremove(){
		var datarole=$('#dg').datagrid('getSelected');
		if(datarole==null||datarole==''){
			 jQuery.messager.alert('提示:','请选择一条数据!');   
			 return ; 
		}
		var dataroleid=datarole.dataRoleId;
		if(confirm("是否删除此条数据")){
			var url="./dataremove/"+dataroleid;
			$.post(url, {}, function(results) {
			var result = eval('(' + results + ')');
			if (result.success) {
				$('#dg').datagrid('reload');
				$.messager.show({
					title : '提示',
					msg : result.msg
				});
			} else {
				$('#dg').datagrid('reload');
				$.messager.show({
					title : '提示',
					msg : '此条数据有主外键关联，不能删除。'
				});
			}
		});
	}
}
	</script>
</html>
