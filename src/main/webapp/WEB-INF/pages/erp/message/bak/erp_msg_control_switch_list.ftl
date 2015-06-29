<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>    列表</title>
	   <#include "../../../../framework/comm/meta.ftl" />
	   <script type="text/javascript" src="${basePath}/static/js/message/common.js"></script>
	   <script type="text/javascript">
	   		$(function(){
	   			$('#search_select_messageType').combobox({
	   				onSelect: function(record){
	   					$('#search_type').val($(this).combobox('getValue'));
	   				}
	   			});
	   			$('#search_select_status').combobox({
	   				onSelect: function(record){
	   					$('#search_status').val($(this).combobox('getValue'));
	   				}
	   			});
	   			$('#select_type').combobox({
	   				onSelect: function(record){
	   					$('#type').val($(this).combobox('getValue'));
	   				}
	   			});
	   			$('#select_status').combobox({
	   				onSelect: function(record){
	   					$('#status').val($(this).combobox('getValue'));
	   				}
	   			});
	   			$('#fm').form({
	   				onLoadSuccess: function(data){
	   					if(data && data.switchId != ""){
	   						$('#select_type').combobox('setValue',data.type);
	   						$('#select_status').combobox('setValue',data.status);
	   					}
	   				}
	   			});
	   			$('#showfm').form({
	   				onLoadSuccess: function(data){
	   					$('#showfm input[name=type]').val(message_type_formatter(data.type));
	   					$('#showfm input[name=status]').val(status_formmater(data.status));
	   				}
	   			});
	   		})
	   </script>
	</head>
	<body>
		<div class="part">
			 <form id="erp_msg_control_switch_dg_search">
			 	<input type="hidden" id="search_type" name="type"/>
			 	<input type="hidden" id="search_status" name="status"/>
		 		<p>
		 			<label>消息类型:</label>
		 			<select id="search_select_messageType" class="easyui-combobox" style="width:160px;" size="36" editable="false" data-options="panelHeight:'auto'">
					    <option value="MSAA">短信</option>
					    <option value="MSAB">邮件</option>
					    <option value="MSAC">站内消息</option>
					</select>
		 		</p>
		 		<p>
		 			<label>是否开启:</label>
		 			<select id="search_select_status" class="easyui-combobox" style="width:160px;" size="36" editable="false" data-options="panelHeight:'auto'">
					    <option value="MSBA">是</option>
					    <option value="MSBB">否</option>
					</select>
		 		</p>
				<div class="part-buttons"> 
		 			<a href="#" onclick="datagridSearch('erp_msg_control_switch_dg','erp_msg_control_switch_dg_search')" class="easyui-linkbutton"  >查询</a>
					<a href="#" onclick="resetPage('erp_msg_control_switch_dg_search')" class="easyui-linkbutton"  >重置</a>
				</div>
			</form>
		</div>
		<table id="erp_msg_control_switch_dg" title="    列表" class="easyui-datagrid"
			style="height: 500px;width: auto;" url="./searchList"
			toolbar="#erp_msg_control_switch_toolbar" pagination="true" rownumbers="false"
			fitColumns="true" singleSelect="false" selectOnCheck="true" checkOnSelect="true"
			>
			<thead>
				<tr>
				  <th data-options="field:'switchId',checkbox:true"></th>		 
		    	  <th field="type" formatter="message_type_formatter" sortable="true">消息类型</th>
		    	  <th field="status" formatter="status_formmater" sortable="true">是否开启</th>
		    	  <#-- <th field="createStaffId" sortable="true">创建人</th> -->
		    	  <th field="createDate" sortable="true">创建时间</th>
		    	  <#-- <th field="updateStaffId" sortable="true">更新人</th> -->
		    	  <th field="updateDate" sortable="true">更新时间</th>
		    	  <th field="remark" sortable="true" formatter="str_formatter">备注</th>
				</tr>
			</thead>
		</table>
		<div id="erp_msg_control_switch_toolbar">
			<div  id="menubar"  style="margin-bottom:5px">
				<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-add" plain="true" onclick="newinsert('dlg','fm','新建')">新建</a> 
				<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-edit" plain="true" onclick="newedit('dlg','fm','erp_msg_control_switch_dg','修改')">修改</a> 
				<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-remove" plain="true" onclick="newremove('erp_msg_control_switch_dg','./remove','switchId')">删除</a>
				<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-edit" plain="true" onclick="newshow('showdlg','showfm','erp_msg_control_switch_dg','查看')">查看</a> 
			</div>
		</div>
	
		<div id="dlg" class="easyui-dialog" style="width: 500px; height: auto; padding: 10px 20px" closed="true" >
			<div class="part-toolbar">
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true"
				 onclick="newsave('dlg','fm','erp_msg_control_switch_dg','./save')">保存</a> <a
				href="javascript:void(0)" class="easyui-linkbutton"  data-options="iconCls:'icon-save',plain:true"
				  onclick="javascript:$('#dlg').dialog('close')">取消</a>
			</div>
			<div class="part">
				<div class="part-title">    详细</div>
				<form id="fm" method="post" novalidate>
					<input type="hidden" name="switchId" /> 
					<input type="hidden" id="type" name="type" /> 
					<input type="hidden" id="status" name="status" /> 
					<p>
						<label>消息类型:</label> 
						<select id="select_type" class="easyui-combobox" style="width:100px;" editable="false" class="easyui-validatebox" required="true" data-options="panelHeight:'auto'">
						    <option value="MSAA">短信</option>
						    <option value="MSAB">邮件</option>
						    <option value="MSAC">站内消息</option>
						</select>
					</p>
					<p>
						<label>是否开启:</label> 
						<select id="select_status" class="easyui-combobox" style="width:100px;" editable="false" class="easyui-validatebox" required="true" data-options="panelHeight:'auto'">
						    <option value="MSBA">是</option>
						    <option value="MSBB">否</option>
						</select>
					</p>
					<p>
						<label>备注:</label> 
						<textarea  name="remark" class="textarea-bigtext" style="width:330px;height:45px"></textarea>
					</p>
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
							<input type="hidden" name="switchId" /> 
									<p><label>消息类型:</label> 
										<input  type="text" name="type"  readonly="readonly" style="border: 0px"/></p>
									<p><label>是否开启:</label> 
										<input  type="text" name="status"  readonly="readonly" style="border: 0px"/></p>
									<#-- <p><label>创建人:</label> 
										<input  type="text" name="createStaffId"  readonly="readonly" style="border: 0px"/></p> -->
									<p><label>创建时间:</label> 
										<input  type="text" name="createDate"  readonly="readonly" style="border: 0px"/></p>
									<#-- <p><label>更新人:</label> 
										<input  type="text" name="updateStaffId"  readonly="readonly" style="border: 0px"/></p> -->
									<p><label>更新时间:</label> 
										<input  type="text" name="updateDate"  readonly="readonly" style="border: 0px"/></p>
									<p><label>备注:</label> 
										<textarea  name="remark" class="textarea-bigtext" style="width: 330px;height: 45px;border: 0px" readonly="readonly"></textarea></p>
			
				</form>
			</div>
		</div>
	</body>
</html>