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
	   					$('#search_messageType').val($(this).combobox('getValue'));
	   				}
	   			});
	   			$('#search_select_status').combobox({
	   				onSelect: function(record){
	   					$('#search_status').val($(this).combobox('getValue'));
	   				}
	   			});
   			})
   		</script>
	</head>
	<body>
		<div class="part">
			<form id="erp_msg_send_record_dg_search">
				<input type="hidden" id="search_messageType" name="messageType"/>
				<input type="hidden" id="search_status" name="status"/>
		 		<p><label>消息类型:</label>
		 			<select id="search_select_messageType" class="easyui-combobox" style="width:160px;" size="36" editable="false" data-options="panelHeight:'auto'">
					    <option value="MSAA">短信</option>
					    <option value="MSAB">邮件</option>
					    <option value="MSAC">站内消息</option>
					</select>
		 		</p>
		 		<p><label>发送状态:</label>
		 			<select id="search_select_status" class="easyui-combobox" style="width:160px;" size="36" editable="false" data-options="panelHeight:'auto'">
					    <option value="MSEA">待发送</option>
					    <option value="MSEB">发送中</option>
					    <option value="MSEC">发送成功</option>
					    <option value="MSED">发送失败</option>
					</select>
		 		</p>
		 		<p>
		 			<label>调用方法名称:</label>
		 			<input type="text" id="search_methodName"/>
		 		</p>
		 		<p>
		 			<label>被通知方联系方式:</label>
		 			<input type="text" id="search_toTelephone"/>
		 		</p>
				<div class="part-buttons"> 
		 			<a href="#" onclick="datagridSearch('erp_msg_send_record_dg','erp_msg_send_record_dg_search')" class="easyui-linkbutton"  >查询</a>
					<a href="#" onclick="resetPage('erp_msg_send_record_dg_search')" class="easyui-linkbutton"  >重置</a>
				</div>
			</form>
		</div>
		<table id="erp_msg_send_record_dg" title="    列表" class="easyui-datagrid"
			style="height: 500px;width: auto;" url="./searchList" pagination="true" rownumbers="false"
			fitColumns="true" singleSelect="false" selectOnCheck="true" checkOnSelect="true"
			>
			<thead>
				<tr>
				  <th data-options="field:'recordId',checkbox:true"></th>		 
		    	  <th field="messageType"  width="100"
		    	  			sortable="true" formatter="message_type_formatter"
		    	  		 >消息类型</th>
		    	  <th field="methodName"  width="100" formatter="str_formatter"
		    	  			sortable="true" 
		    	  		 >调用方法</th>
		    	  <th field="arguments"  width="100" formatter="str_formatter"
		    	  			sortable="true" 
		    	  		 >方法参数</th>
		    	  <th field="toTelephone"  width="100" formatter="str_formatter"
		    	  			sortable="true" 
		    	  		 >被通知方联系方式</th>
		    	  <th field="content"  width="100" formatter="str_formatter"
		    	  			sortable="true" 
		    	  		 >发送内容</th>
		    	  <th field="sendTime"  width="200"
		    	  			sortable="true" 
		    	  		 >发送时间</th>
		    	  <th field="caller"  width="100"
		    	  			sortable="true" 
		    	  		 >调用方</th>
		    	  <th field="status"  formatter="send_status_formatter" width="100"
		    	  			sortable="true" 
		    	  		 >发送状态</th>
		    	  <th field="count"  width="100"
		    	  			sortable="true" 
		    	  		 >发送次数</th>
		    	  <th field="invokeMessage"  width="100"
		    	  			sortable="true" 
		    	  		 >调用信息</th>
		    	  <th field="startDate"  width="200"
		    	  			sortable="true" 
		    	  		 >开始日期</th>
		    	  <th field="endDate"  width="200"
		    	  			sortable="true" 
		    	  		 >结束日期</th>
		    	  <th field="startTime"  width="100"
		    	  			sortable="true" 
		    	  		 >开始时间</th>
		    	  <th field="endTime"  width="100"
		    	  			sortable="true" 
		    	  		 >结束时间</th>
		    	  <#-- <th field="createStaffId"  width="100"
		    	  			sortable="true" 
		    	  		 >创建人</th> -->
		    	  <th field="createDate"  width="200"
		    	  			sortable="true" 
		    	  		 >创建时间</th>
		    	  <th field="remark"  width="100"
		    	  			sortable="true" formatter="str_formatter"
		    	  		 >备注</th>
				</tr>
			</thead>
		</table>
	</body>
</html>