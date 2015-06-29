<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>    列表</title>
   <#include "../../framework/comm/meta.ftl" />
</head>
<body>
		<div class="part">
				 <form id="erp_msg_warning_rule_dg_search">
					 		<p><label>规则名称:</label><input type="text" id="search_ruleName"  
					 			/></p>
							<div class="part-buttons"> 
					 			<a href="#" onclick="datagridSearch('erp_msg_warning_rule_dg','erp_msg_warning_rule_dg_search')" class="easyui-linkbutton"  >查询</a>
								<a href="#" onclick="resetPage('erp_msg_warning_rule_dg_search')" class="easyui-linkbutton"  >重置</a>
							</div>
				</form>
		</div>
	<table id="erp_msg_warning_rule_dg" title="预警规则列表" class="easyui-datagrid"
		style="height: 500px;width: auto;" url="./searchList"
		toolbar="#erp_msg_warning_rule_toolbar" pagination="true" rownumbers="false"
		fitColumns="true" singleSelect="false" selectOnCheck="true" checkOnSelect="true"
		>
		<thead>
			<tr>
						  <th data-options="field:'ruleId',checkbox:true"></th>		 
				    	  <th data-options="field:'ruleName',align:'center',width:100" >规则名称</th>
				    	  <th data-options="field:'dectorName',align:'center',width:100" >检测器名称</th>
				    	  <th data-options="field:'noticeType',align:'center',width:100,formatter:formatterType" >通知方式</th>
				    	  <th data-options="field:'status',align:'center',width:100,formatter:formatterStatus" >是否启用</th>
				    	  <th data-options="field:'arguments',align:'center',width:100" >配置参数</th>
				    	  <th data-options="field:'frequency',align:'center',width:100" >执行频率</th>
				    	  <th data-options="field:'createDate',align:'center',width:100" >创建时间</th>
			</tr>
		</thead>
	</table>
	<div id="erp_msg_warning_rule_toolbar">
	  <div  id="menubar"  style="margin-bottom:5px">
			<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-add" plain="true" onclick="createTab('新建预警规则','./erpMsgWarningRuleController/toSave')">新建预警规则</a> 
			<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-edit" plain="true" onclick="updateRule();">修改预警规则</a> 
			<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-remove" plain="true" onclick="newremove('erp_msg_warning_rule_dg','./remove','ruleId')">删除预警规则</a>
			<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-edit" plain="true" onclick="updateRule();">查看预警规则</a> 
			</div>
	</div>
	
</body>
<script>
   function formatterType(value,row,index){
      var result = '';
      if(value.indexOf('MSAA') != -1){
         result += '短信通知';
         if(value.indexOf('MSAB') != -1)
            result += ',email通知';
      }else{
         if(value.indexOf('MSAB') != -1)
            result += 'email通知';
      }
      return result;
   }
   
   function formatterStatus(value,row,index){
      if(value == 'MSBA')
         return '启用';
      return '禁用';
   }
   
   function updateRule(){
     var rows = $('#erp_msg_warning_rule_dg').datagrid('getSelections');
	 if(rows==null||rows==''||rows.length==0){
		 jQuery.messager.alert('提示:','请选择操作记录!');   
		 return ; 
	 }
	 if(rows.length>1){
		 jQuery.messager.alert('提示:','只能操作一条记录!');   
		 return ;
	 }
	 var row = $('#erp_msg_warning_rule_dg').datagrid('getSelected');
	 createTab('修改预警规则','./erpMsgWarningRuleController/toUpdate?ruleId=' + row.ruleId);
   }
</script>
</html>