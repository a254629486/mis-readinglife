<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>    列表</title>
   <#include "../../../../framework/comm/meta.ftl" />
   <script type="text/javascript" src="${basePath}/static/js/message/common.js"></script>
   <script>
   		$(function(){
   			/*$('#search_select_type').combobox({
   				onSelect: function(record){
   					$('#search_type').val($(this).combobox('getValue'));
   				}
   			});*/
   			/*$('#select_type').combobox({
   				onSelect: function(record){
   					$('#type').val($(this).combobox('getValue'));
   				}
   			});
   			$('#fm').form({
   				onLoadSuccess: function(data){
   					if(data && data.configId != ""){
   						$('#select_type').combobox('setValue',data.type);
   					}
   				}
   			});
   			$('#showfm').form({
   				onLoadSuccess: function(data){
   					$('#showfm input[name=type]').val(message_type_formatter(data.type));
   				}
   			});*/
   		})
   </script>
</head>
<body>
		<div class="part">
				 <form id="erp_msg_argument_config_dg_search">
				 			<input id="search_type" type="hidden" name="type"/>
				 			<#--
					 		<p><label>消息类型:</label>
					 		<select id="search_select_type" class="easyui-combobox" size="36">
							    <option value="MSAA">短信</option>
							    <option value="MSAB">邮件</option>
							    <option value="MSAC">站内消息</option>
							</select></p>
							-->
					 		<p><label>参数名称:</label><input type="text" id="search_name"  
					 			/></p>
				 			<p><label>参数变量:</label><input type="text" id="search_parameter"  
				 			/></p>
							<div class="part-buttons"> 
					 			<a href="#" onclick="datagridSearch('erp_msg_argument_config_dg','erp_msg_argument_config_dg_search')" class="easyui-linkbutton"  >查询</a>
								<a href="#" onclick="resetPage('erp_msg_argument_config_dg_search')" class="easyui-linkbutton"  >重置</a>
							</div>
				</form>
		</div>
	<table id="erp_msg_argument_config_dg" title="    列表" class="easyui-datagrid"
		style="height: 500px;width: auto;" url="./searchList"
		toolbar="#erp_msg_argument_config_toolbar" pagination="true" rownumbers="false"
		fitColumns="true" singleSelect="false" selectOnCheck="true" checkOnSelect="true"
		>
		<thead>
			<tr>
			  <th data-options="field:'configId',checkbox:true"></th>	
			  <#--	 
	    	  <th field="type"  
	    	  			sortable="true" formatter="message_type_formatter"
	    	  		 >消息类型</th>
	    	  -->
	    	  <th field="name"  
	    	  			sortable="true" 
	    	  		 >参数名称</th>
	    	  <th field="parameter"  
	    	  			sortable="true" 
	    	  		 >参数变量</th>
	    	 <#-- <th field="createStaffId"  
	    	  			sortable="true" 
	    	  		 >创建人</th> -->
	    	  <th field="createDate"  
	    	  			sortable="true" 
	    	  		 >创建时间</th>
	    	  <#-- <th field="updateStaffId"  
	    	  			sortable="true" 
	    	  		 >更新人</th> -->
	    	  <th field="updateDate"  
	    	  			sortable="true" 
	    	  		 >更新时间</th>
	    	  <th field="remark"  
	    	  			sortable="true" 
	    	  		 >备注</th>
			</tr>
		</thead>
	</table>
	<div id="erp_msg_argument_config_toolbar">
	  <div  id="menubar"  style="margin-bottom:5px">
			<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-add" plain="true" onclick="newinsert('dlg','fm','新建')">新建</a> 
			<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-edit" plain="true" onclick="newedit('dlg','fm','erp_msg_argument_config_dg','修改')">修改</a> 
			<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-remove" plain="true" onclick="newremove('erp_msg_argument_config_dg','./remove','configId')">删除</a>
			<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-edit" plain="true" onclick="newshow('showdlg','showfm','erp_msg_argument_config_dg','查看')">查看</a> 
			</div>
	</div>
	
	<div id="dlg" class="easyui-dialog"
		style="width: 500px; height: auto; padding: 10px 20px" closed="true" >
		<div class="part-toolbar">
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true"
			 onclick="newsave('dlg','fm','erp_msg_argument_config_dg','./save')">保存</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"  data-options="iconCls:'icon-save',plain:true"
			  onclick="javascript:$('#dlg').dialog('close')">取消</a>
		</div>
		<div class="part">
		<div class="part-title">    详细</div>
		<form id="fm" method="post" novalidate>
							<input type="hidden" name="configId" />
							<#--
							<input id="type" type="hidden" name="type" /> 
									<p>
										<label>消息类型:</label> 
										<select id="select_type" class="easyui-combobox" style="width:164px;" editable="false" class="easyui-validatebox" required="true" data-options="panelHeight:'auto'">
										    <option value="MSAA">短信</option>
										    <option value="MSAB">邮件</option>
										    <option value="MSAC">站内消息</option>
										</select>
									</p>
									-->
									<p><label>参数名称:</label> 
										<input type="text" name="name"   
											class="easyui-validatebox" required="true"
									 /></p>
									<p><label>参数变量:</label> 
										<input type="text" name="parameter"   
											class="easyui-validatebox" required="true"
									 /></p>
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
							<input type="hidden" name="configId" /> 
									<#--
									<p><label>消息类型:</label> 
										<input  type="text" name="type"  readonly="readonly" style="border: 0px"/></p>
									-->
									<p><label>参数名称:</label> 
										<input  type="text" name="name"  readonly="readonly" style="border: 0px"/></p>
									<p><label>参数变量:</label> 
										<input  type="text" name="parameter"  readonly="readonly" style="border: 0px"/></p>
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