<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>人员列表</title>
<#include "../../framework/comm/meta.ftl">
</head>
<body onended="initTable('sys_person_dg','sys_person_dg_search');">
		<div class="part">
			<form id="sys_person_dg_search">
				<p><label>登陆名:</label><input type="text"  id="search_loginName" /></p>
				<p><label> 姓名:</label><input type="text"  id="search_name" /> </p>
				<p><label>状态:</label><input type="text"  class="easyui-combobox easyui-validatebox"	 
									name="search_status"
									data-options="
									url:'${basePath}/sysConstantsController/getTypeList/PERSON?select=true',
									method:'get',
									valueField:'id',
									textField:'text',
									panelHeight:'auto' 
									"></p>
				<div class="part-buttons"> 						
				<a href="#"
					onclick="datagridSearch('sys_person_dg','sys_person_dg_search')"
					class="easyui-linkbutton"  >查询</a> <a href="#"
					onclick="resetPage('sys_person_dg_search')"
					class="easyui-linkbutton" >重置</a>
					</div>
			</form>
		</div>
	<table id="sys_person_dg" title="人员列表" class="easyui-datagrid"
		style="height: auto; width: auto;" url="./searchList"
		toolbar="#sys_person_toolbar" pagination="true" rownumbers="false"
		fitColumns="true" singleSelect="false" selectOnCheck="true"
		checkOnSelect="true">
		<thead>
			<tr>
				<th data-options="field:'personId',checkbox:true">人员标识</th>
				<th field="loginName" sortable="true">登陆名</th>
				<th field="loginPwd" hidden="true">登陆密码</th>
				<th field="name" sortable="true">姓名</th>
				<th field="gender" hidden="true">性别</th>
				<th field="age" hidden="true">年龄</th>
				<th field="email" sortable="true">邮箱</th>
				<th field="qq" hidden="true">QQ</th>
				<th field="mphoneNo" sortable="true">手机号</th>
				<th field="tphoneNo" sortable="true">电话</th>
				<th field="extensNo" hidden="true">分机</th>
				<th field="status"  data-options=" formatter:typeFormatter " sortable="true">状态</th>
			</tr>
		</thead>
	</table>
	<div id="sys_person_toolbar">
		<div style="margin-bottom: 5px">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-add" plain="true" onclick="newinsert('dlg','fm','新建',{personId:'',loginName:'',loginPwd:'',name:'',gender:'1',age:'',email:'',qq:'',mphoneNo:'',tphoneNo:'',extensNo:'',status:'1'})">新建</a>
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-edit" plain="true"
				onclick="newedit('dlg','fm','sys_person_dg','修改')">修改</a> <a
				href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-remove" plain="true"
				onclick="newremove('sys_person_dg','./remove','personId')">删除</a> <a
				href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-edit" plain="true"
				onclick="newshow('showdlg','showfm','sys_person_dg','查看')">查看</a>
		</div>
	</div>

	<div id="dlg" class="easyui-dialog"
		style="width: 500px; height: auto; padding: 10px 20px" closed="true" >
	<div class="part-toolbar">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true"
			onclick="newsave('dlg','fm','sys_person_dg','./save')">保存</a> <a
			href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true"
			onclick="javascript:$('#dlg').dialog('close')">取消</a>
	</div>
		
		<div class="part">
			    <div class="part-title">人员详细</div>
		<form id="fm" method="post" novalidate>
			<input type="hidden" name="personId" />
			<p>
				<label>登陆名:</label> <input name="loginName" type="text" 
					class="easyui-validatebox" required="true" validType="" />
			</p>
			<p>
				<label>登陆密码:</label> <input name="loginPwd" type="text" 
					class="easyui-validatebox" required="true" validType="" />
			</p>
			<p>
				<label>姓名:</label> <input name="name" class="easyui-validatebox" type="text" 
					required="true" validType="" />
			</p>
			<p>
				<label>性别:</label>  <input class="easyui-combobox easyui-validatebox"	 type="text"  
									name="gender"
									data-options="
									url:'${basePath}/sysConstantsController/getTypeList/SEX',
									method:'get',
									valueField:'id',
									textField:'text',
									panelHeight:'auto' 
									">
			</p>
			<p>
				<label>年龄:</label> <input name="age" class="easyui-validatebox" type="text" 
					validType="number" />
			</p>
			<p>
				<label>邮箱:</label> <input name="email" class="easyui-validatebox" required="true"  type="text" 
					validType="email" />
			</p>
			<p>
				<label>QQ:</label> <input name="qq" class="easyui-validatebox" type="text" 
					validType="QQ" />
			</p>
			<p>
				<label>手机号:</label> <input name="mphoneNo" type="text" 
					class="easyui-validatebox" validType="mobile" />
			</p>
			<p>
				<label>电话:</label> <input name="tphoneNo" class="easyui-validatebox" type="text" 
					validType="" />
			</p>
			<p>
				<label>分机:</label> <input name="extensNo" class="easyui-validatebox" type="text" 
					validType="" />
			</p>
			<p>
				<label>状态:</label>  <input class="easyui-combobox easyui-validatebox"	 type="text"  
									name="status"
									data-options="
									url:'${basePath}/sysConstantsController/getTypeList/PERSON',
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
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true"
			onclick="javascript:$('#showdlg').dialog('close')">关闭</a>
	</div>
		
		<div class="part">
			    <div class="part-title">人员详细</div>
		<form id="showfm" method="post" novalidate>
			<input type="hidden" name="personId" />
			<p>
				<label>登陆名:</label> <input name="loginName" readonly="readonly" type="text" 
					style="border: 0px" />
			</p>
			<p>
				<label>登陆密码:</label> <input name="loginPwd" readonly="readonly" type="text" 
					style="border: 0px" />
			</p>
			<p>
				<label>姓名:</label> <input name="name" readonly="readonly" type="text" 
					style="border: 0px" />
			</p>
			<p>
				<label>性别:</label>  <input class="easyui-combobox"	  readonly="readonly" type="text" 
							style="border: 0px;width: 123px;" 
									name="gender"
									data-options="
									url:'${basePath}/sysConstantsController/getTypeList/SEX',
									method:'get',
									valueField:'id',
									textField:'text',
									panelHeight:'auto'
									">
			</p>
			<p>
				<label>年龄:</label> <input name="age" readonly="readonly" type="text" 
					style="border: 0px" />
			</p>
			<p>
				<label>邮箱:</label> <input name="email" readonly="readonly" type="text" 
					style="border: 0px" />
			</p>
			<p>
				<label>QQ:</label> <input name="qq" readonly="readonly" type="text" 
					style="border: 0px" />
			</p>
			<p>
				<label>手机号:</label> <input name="mphoneNo" readonly="readonly" type="text" 
					style="border: 0px" />
			</p>
			<p>
				<label>电话:</label> <input name="tphoneNo" readonly="readonly" type="text" 
					style="border: 0px" />
			</p>
			<p>
				<label>分机:</label> <input name="extensNo" readonly="readonly" type="text" 
					style="border: 0px" />
			</p>
			<p>
				<label>状态:</label> <input class="easyui-combobox"	  readonly="readonly" type="text" 
							style="border: 0px;width: 123px;" 
									name="status"
									data-options="
									url:'${basePath}/sysConstantsController/getTypeList/PERSON',
									method:'get',
									valueField:'id',
									textField:'text',
									panelHeight:'auto'
									">
			</p>

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