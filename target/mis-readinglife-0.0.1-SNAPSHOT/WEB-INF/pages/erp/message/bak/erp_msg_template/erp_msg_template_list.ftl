<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>    列表</title>
   <#include "../../../../framework/comm/meta.ftl" />
   <script type="text/javascript" src="${basePath}/static/js/message/common.js"></script>
   <script>
   		$(function(){
   			$('#search_select_messageType').combobox({
   				onSelect: function(record){
   					$('#search_messageType').val($(this).combobox('getValue'));
   				}
   			});
   			$('#search_select_templateType').combobox({
   				onSelect: function(record){
   					$('#search_type').val($(this).combobox('getValue'));
   				}
   			});
   			$('#search_select_status').combobox({
   				onSelect: function(record){
   					$('#search_status').val($(this).combobox('getValue'));
   				}
   			});
   		})
   		function toUpdatePage(table){
   			var rows = $('#'+table).datagrid('getSelections');
		 	if(rows==null||rows==''||rows.length==0){
				jQuery.messager.alert('提示:','请选择操作记录!');   
				return ; 
		 	}
			if(rows.length>1){
				jQuery.messager.alert('提示:','只能操作一条记录!');   
				return ;
			}
			var row = $('#'+table).datagrid('getSelected');
			createTab('修改模板','${basePath}/erpMsgTemplateController/toErpMsgTemplateupdate/'+row.templateId);
   		}
   </script>
</head>
<body>
		<div class="part">
				 <form id="erp_msg_template_dg_search">
				 			<input type="hidden" id="search_messageType" name="messageType"/>
				 			<input type="hidden" id="search_type" name="type"/>
				 			<input type="hidden" id="search_status" name="status"/>
					 		<p><label>消息类型:</label>
					 			<select id="search_select_messageType" class="easyui-combobox" style="width:160px;" size="36" editable="false" data-options="panelHeight:'auto'">
								    <option value="MSAA">短信</option>
								    <option value="MSAB">邮件</option>
								    <option value="MSAC">站内消息</option>
								</select>
					 		</p>
					 		<p>
					 			<label>模板类型:</label>
					 			<select id="search_select_templateType" class="easyui-combobox" style="width:160px;" size="36" editable="false" data-options="panelHeight:'auto'">
								    <option value="MSDA">测试模板</option>
								</select>
					 		</p>
					 		<p>
					 			<label>是否启用:</label>
					 			<select id="search_select_status" class="easyui-combobox" editable="false" size="36" data-options="panelHeight:'auto'">
								    <option value="MSBA">是</option>
								    <option value="MSBB">否</option>
								</select>
					 		</p>
					 		<p>
					 			<label>模板名称:</label>
					 			<input type="text" id="search_name" name="name"/>
					 		</p>
					 		<p>
					 			<label>模板标题:</label>
					 			<input type="text" id="search_title" name="title"/>
					 		</p>
					 		
							<div class="part-buttons"> 
					 			<a href="#" onclick="datagridSearch('erp_msg_template_dg','erp_msg_template_dg_search')" class="easyui-linkbutton"  >查询</a>
								<a href="#" onclick="resetPage('erp_msg_template_dg_search')" class="easyui-linkbutton"  >重置</a>
							</div>
				</form>
		</div>
	<table id="erp_msg_template_dg" title="    列表" class="easyui-datagrid"
		style="height: 500px;width: auto;" url="./searchList"
		toolbar="#erp_msg_template_toolbar" pagination="true" rownumbers="false"
		fitColumns="true" singleSelect="false" selectOnCheck="true" checkOnSelect="true"
		>
		<thead>
			<tr>
							<th data-options="field:'templateId',checkbox:true"></th>		 
				    	  <th field="messageType"  
				    	  			sortable="true" width="100" formatter="message_type_formatter"
				    	  		 >消息类型</th>
				    	  <th field="name"  
				    	  			sortable="true" width="100" formatter="str_formatter"
				    	  		 >模板名称</th>
				    	  <th field="type"  
				    	  			sortable="true" width="100" formatter="template_type_formatter"
				    	  		 >模板类型</th>
				    	  <th field="status"  
				    	  			sortable="true" width="50" formatter="status_formmater"
				    	  		 >是否启用</th>
				    	  <th field="title"  
				    	  			sortable="true" width="100" formatter="str_formatter"
				    	  		 >标题</th>
				    	  <th field="header"  
				    	  			sortable="true" width="100" formatter="str_formatter"
				    	  		 >信息头</th>
				    	  <th field="content"  
				    	  			sortable="true" width="200" formatter="content_formatter"
				    	  		 >正文</th>
				    	  <th field="end"  
				    	  			sortable="true" width="100" formatter="str_formatter"
				    	  		 >结尾</th>
				    	  <#-- 
				    	  <th field="createStaffId"  width="100"
				    	  			sortable="true" 
				    	  		 >创建人</th> 
				    	  -->
				    	  <th field="createDate"  width="100"
				    	  			sortable="true" 
				    	  		 >创建时间</th>
				    	  <#-- 
				    	  <th field="updateStaffId"  width="100"
				    	  			sortable="true" 
				    	  		 >更新人</th>
				    	  -->
				    	  <th field="updateDate"  width="100"
				    	  			sortable="true" 
				    	  		 >更新时间</th>
				    	  <th field="remark"  width="100"
				    	  			sortable="true" formatter="str_formatter"
				    	  		 >备注</th>
			</tr>
		</thead>
	</table>
	<div id="erp_msg_template_toolbar">
	  <div  id="menubar"  style="margin-bottom:5px">
			<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-add" plain="true" onclick="createTab('新建模板','${basePath}/erpMsgTemplateController/toErpMsgTemplateadd')">新建</a> 
			<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-edit" plain="true" onclick="toUpdatePage('erp_msg_template_dg')">修改</a> 
			<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-remove" plain="true" onclick="newremove('erp_msg_template_dg','./remove','templateId')">删除</a>
			<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-edit" plain="true" onclick="toUpdatePage('erp_msg_template_dg')">查看</a> 
			</div>
	</div>
	
	<div id="dlg" class="easyui-dialog"
		style="width: 500px; height: auto; padding: 10px 20px" closed="true" >
		<div class="part-toolbar">
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true"
			 onclick="newsave('dlg','fm','erp_msg_template_dg','./save')">保存</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"  data-options="iconCls:'icon-save',plain:true"
			  onclick="javascript:$('#dlg').dialog('close')">取消</a>
		</div>
		<div class="part">
		<div class="part-title">    详细</div>
		<form id="fm" method="post" novalidate>
							<input type="hidden" name="templateId" /> 
									<p><label>:</label> 
										<input type="text" name="messageType"   
											class="easyui-validatebox"
											 validType=""
									 /></p>
									<p><label>:</label> 
										<input type="text" name="name"   
											class="easyui-validatebox"
											 validType=""
									 /></p>
									<p><label>:</label> 
										<input type="text" name="type"   
											class="easyui-validatebox"
											 validType=""
									 /></p>
									<p><label>:</label> 
										<input type="text" name="status"   
											class="easyui-validatebox"
											 validType=""
									 /></p>
									<p><label>:</label> 
										<input type="text" name="title"   
											class="easyui-validatebox"
											 validType=""
									 /></p>
									<p><label>:</label> 
										<input type="text" name="header"   
											class="easyui-validatebox"
											 validType=""
									 /></p>
									<p><label>:</label> 
										<input type="text" name="content"   
											class="easyui-validatebox"
											 validType=""
									 /></p>
									<p><label>:</label> 
										<input type="text" name="end"   
											class="easyui-validatebox"
											 validType=""
									 /></p>
									<#-- 
									<p><label>创建人:</label> 
										<input type="text" name="createStaffId"   
											class="easyui-validatebox"
											 validType=""
									 /></p> -->
									<p><label>创建时间:</label> 
										<input type="text" name="createDate"   
							 				class="easyui-datebox easyui-validatebox"
											 validType=""
									 /></p>
									<#-- <p><label>更新人:</label> 
										<input type="text" name="updateStaffId"   
											class="easyui-validatebox"
											 validType=""
									 /></p> -->
									<p><label>更新时间:</label> 
										<input type="text" name="updateDate"   
							 				class="easyui-datebox easyui-validatebox"
											 validType=""
									 /></p>
									<p><label>备注:</label> 
										<input type="text" name="remark"   
											class="easyui-validatebox"
											 validType=""
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
		<div class="part-title">    详细</div>
		<form id="showfm" method="post" novalidate>
							<input type="hidden" name="templateId" /> 
									<p><label>:</label> 
										<input  type="text" name="messageType"  readonly="readonly" style="border: 0px"/></p>
									<p><label>:</label> 
										<input  type="text" name="name"  readonly="readonly" style="border: 0px"/></p>
									<p><label>:</label> 
										<input  type="text" name="type"  readonly="readonly" style="border: 0px"/></p>
									<p><label>:</label> 
										<input  type="text" name="status"  readonly="readonly" style="border: 0px"/></p>
									<p><label>:</label> 
										<input  type="text" name="title"  readonly="readonly" style="border: 0px"/></p>
									<p><label>:</label> 
										<input  type="text" name="header"  readonly="readonly" style="border: 0px"/></p>
									<p><label>:</label> 
										<input  type="text" name="content"  readonly="readonly" style="border: 0px"/></p>
									<p><label>:</label> 
										<input  type="text" name="end"  readonly="readonly" style="border: 0px"/></p>
									<#-- <p><label>创建人:</label> 
										<input  type="text" name="createStaffId"  readonly="readonly" style="border: 0px"/></p> -->
									<p><label>创建时间:</label> 
										<input  type="text" name="createDate"  readonly="readonly" style="border: 0px"/></p>
									<#-- <p><label>更新人:</label> 
										<input  type="text" name="updateStaffId"  readonly="readonly" style="border: 0px"/></p> -->
									<p><label>更新时间:</label> 
										<input  type="text" name="updateDate"  readonly="readonly" style="border: 0px"/></p>
									<p><label>备注:</label> 
										<input  type="text" name="remark"  readonly="readonly" style="border: 0px"/></p>
			
		</form>
		</div>
	</div>
</body>
</html>