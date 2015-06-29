<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>    列表</title>
   <#include "../../framework/comm/meta.ftl" />
   <script>
      $.extend($.fn.validatebox.defaults.rules, {
		phone: {
			validator: function(value,param){
			    var phoneReg = /^(((1[0-9]{1}[0-9]{1}))+\d{8})$/;
				return phoneReg.test(value);
			},
			message: '请输入有效的手机号格式'
		}
	  });
   </script>
</head>
<body>
		<div class="part">
				 <form id="erp_msg_warning_notice_user_dg_search">
					 		<p><label>用户姓名:</label><input type="text" id="search_staffName"  
					 			/></p>
							<div class="part-buttons"> 
					 			<a href="#" onclick="datagridSearch('erp_msg_warning_notice_user_dg','erp_msg_warning_notice_user_dg_search')" class="easyui-linkbutton"  >查询</a>
								<a href="#" onclick="resetPage('erp_msg_warning_notice_user_dg_search')" class="easyui-linkbutton"  >重置</a>
							</div>
				</form>
		</div>
	<table id="erp_msg_warning_notice_user_dg" title="用户列表" class="easyui-datagrid"
		style="height: 500px;width: auto;" url="./searchList"
		toolbar="#erp_msg_warning_notice_user_toolbar" pagination="true" rownumbers="false"
		fitColumns="true" singleSelect="false" selectOnCheck="true" checkOnSelect="true"
		>
		<thead>
			<tr>
						  <th data-options="field:'noticeUserId',checkbox:true"></th>		 
				    	  <th field="staffName"  
				    	  			sortable="true"  width="100"
				    	  		 >用户名称</th> 
				    	  <th field="telephone"  
				    	  			sortable="true"  width="100"
				    	  		 >手机号</th>   
				    	  <th field="email"  
				    	  			sortable="true"  width="100"
				    	  		 >邮箱地址</th> 
			</tr>
		</thead>
	</table>
	<div id="erp_msg_warning_notice_user_toolbar">
	  <div  id="menubar"  style="margin-bottom:5px">
			<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-add" plain="true" onclick="newinsert('dlg','fm','新建新建用户')">新建用户</a> 
			<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-edit" plain="true" onclick="newedit('dlg','fm','erp_msg_warning_notice_user_dg','修改用户')">修改用户</a> 
			<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-remove" plain="true" onclick="newremove('erp_msg_warning_notice_user_dg','./remove','noticeUserId')">删除用户</a>
			<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-edit" plain="true" onclick="newshow('showdlg','showfm','erp_msg_warning_notice_user_dg','查看用户')">查看用户</a> 
			</div>
	</div>
	
	<div id="dlg" class="easyui-dialog"
		style="width: 500px; height: auto; padding: 10px 20px" closed="true" >
		<div class="part-toolbar">
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true"
			 onclick="newsave('dlg','fm','erp_msg_warning_notice_user_dg','./save')">保存</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"  data-options="iconCls:'icon-save',plain:true"
			  onclick="javascript:$('#dlg').dialog('close')">取消</a>
		</div>
		<div class="part">
		<div class="part-title">用户详细信息</div>
		<form id="fm" method="post" novalidate>
							<input type="hidden" name="noticeUserId" /> 
									<p><label>用户姓名:</label> 
										<input type="text" name="staffName"   
											class="easyui-validatebox"
											 validType=""
									 /></p>
									<p><label>手机号:</label> 
										<input type="text" name="telephone"   
											class="easyui-validatebox"
											 validType="phone"
									 /></p>
									<p><label>邮箱地址:</label> 
										<input type="text" name="email"   
											class="easyui-validatebox"
											 validType="email"
									 /></p>
		</form>
		</div>
	</div>
	
	
	<div id="showdlg" class="easyui-dialog"
		style="width: 500px; height: auto; padding: 10px 20px" closed="true" >
		<div class="part-toolbar">
		 	 <a href="javascript:void(0)" class="easyui-linkbutton"  data-options="iconCls:'icon-save',plain:true"
				 onclick="javascript:$('#showdlg').dialog('close')">关闭</a>
		</div>
		<div class="part">
		<div class="part-title">用户详细信息</div>
		<form id="showfm" method="post" novalidate>
							<input type="hidden" name="noticeUserId" /> 
									<p><label>用户姓名:</label> 
										<input  type="text" name="staffName"  readonly="readonly" style="border: 0px"/></p>
									<p><label>手机号:</label> 
										<input  type="text" name="telephone"  readonly="readonly" style="border: 0px"/></p>
									<p><label>邮箱地址:</label> 
										<input  type="text" name="email"  readonly="readonly" style="border: 0px"/></p>
			
		</form>
		</div>
	</div>
</body>
</html>