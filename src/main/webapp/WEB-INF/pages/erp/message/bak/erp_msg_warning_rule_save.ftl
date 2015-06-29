<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>    列表</title>
   <#include "../../framework/comm/meta.ftl" />
   <script type="text/javascript" src="${basePath}/static/js/message/jquery.json-2.4.js"></script>
</head>
<body>
	
		<div class="part-toolbar">
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true"
			 onclick="saveWaringRule();">保存</a>
		</div>
		<div class="part">
			<div class="part-title">预警规则详细</div>
			<form id="fm" method="post" novalidate>
										<p><label>规则名称:</label> 
											<input type="text" name="ruleName"   
												class="easyui-validatebox"
												required="true"
										 /></p> <br/>
										<p><label>检查器:</label> 
										    <select name="dectorName" class="easyui-combobox" style="width:164px;" data-options="required:true">
										        <#list detectorList as detector>
										           <#if detector_index == 0>
												     <option value="${detector.value}" selected="selected">${detector.name}</option>
												   <#else>
												     <option value="${detector.value}">${detector.name}</option>
												   </#if>
												</#list>
										    </select>
										 </p> <br/>
										 <p>
										    <label>接收者:</label> 
										    <div style="float:left;border:solid 1px #33CCFF;width:800px;height:200px;margin-top:10px;margin-left:-5px;">
										       <div>
										         <p>
										             <label>管理员账号:</label>
										             <input type="text" name="name" class="easyui-validatebox" />
										         </p> 
										         <div class="part-buttons">
										 		 	<a class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="selectUser();">搜索</a>
										 		 </div>
										 	   </div> 
										 	   <br/>
										 	   <table id="user_item_list_dlg" class="easyui-datagrid" style="height:161px;">
										 	      <thead>
													<tr>
								                        <th data-options="field:'staffName',align:'center',width:150"  >管理员账号</th>
								                        <th data-options="field:'telephone',align:'center',width:150"  >手机号</th> 
								                        <th data-options="field:'email',align:'center',width:150"  >email</th> 
								                        <th data-options="field:'staffId',align:'center',width:100,formatter:userOperation">操作</th> 
								                    </tr>
								                  </thead>
										 	   </table>
										    </div>
										    <input type="hidden" name="userJson" id="userJson"/> <#-- 存放user的json信息  -->
										</p> <br/>
										
										<p><label>配置参数:</label> 
										    <div style="float:left;border:solid 1px #33CCFF;width:800px;height:200px;margin-top:10px;margin-left:-5px;">
										       <div>
										         <p>
										             <label>参数名称:</label>
										             <input type="text" name="argumentName" class="easyui-validatebox" />
										         </p> 
										         <div class="part-buttons">
										 		 	<a class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="selectArgument();">搜索</a>
										 		 </div>
										 	   </div> 
										 	   <br/>
										 	   <table id="argument_item_list_dlg" class="easyui-datagrid" style="height:161px;">
										 	      <thead>
													<tr>
								                        <th data-options="field:'name',align:'center',width:150" >参数名称</th>
					   									<th data-options="field:'parameter',align:'center',width:150" >参数变量</th>
					   									<th data-options="field:'value',align:'center',width:150,editor:'text'" >参数值</th>
					   									<th data-options="field:'configId',align:'center',width:150,formatter:argumentOperation" >操作</th>
								                    </tr>
								                  </thead>
										 	   </table>
										    </div>
										    <input type="hidden" name="argumentJson" id="argumentJson"/> <#-- 存放user的json信息  -->
										</p><br/>
										
										<p><label>通知方式:</label> 
										    <input type="checkbox" id="noticeType_MSAA" name="noticeType" value="MSAA"/> 短信通知
										    <input type="checkbox" id="noticeType_MSAB" name="noticeType" value="MSAB"/> email通知
										 </p> <br/>
										<p><label>是否启用:</label> 
										    <input type="radio" id="noticeType_MSBA" name="status" value="MSBA"/> 启用
										    <input type="radio" id="noticeType_MSBB" name="status" value="MSBB"/> 禁用
										 </p> <br/>
										<p><label>执行频率:</label> 
											<input type="text" name="frequency"   
												class="easyui-validatebox"
												 validType=""
										 /></p>
										
										
			</form>
		</div>
	
	<div id="selectUserDlg" style="width: 600px; height: 433px; padding: 10px 20px;display:none;" data-options="modal:true, closed: true">
      <table id="selectUserTb" style="width: 545px;height: 350px;display:none;" >
	     <thead>
			  <tr>
			        <th checkbox="true" align="center">选择</th>
				    <th field="name" width="300">用户</th>
				    <th field="mphoneNo" width="300">手机号</th>
				    <th field="email" width="300">email</th>
			  </tr>
		 </thead>
	  </table>
	</div>
	
	<div id="selectArgumentDlg" style="width: 600px; height: 433px; padding: 10px 20px;display:none;" data-options="modal:true, closed: true">
      <table id="selectArgumentTb" style="width: 545px;height: 350px;display:none;" >
	     <thead>
			  <tr>
			        <th checkbox="true" align="center">选择</th>
				    <th field="name" width="300">参数名称</th>
				    <th field="parameter" width="300">参数变量</th>
			  </tr>
		 </thead>
	  </table>
	</div>
	
</body>
<script>
  function selectUser(){  //查找对应的预警通知用户
	   $('#selectUserDlg').css('display','');
	   var name = $('#fm input[name="name"]').val();
	   if(name == '')
	      name = 'ALL';
	   $('#selectUserDlg').dialog({
		  title : '选择用户名为【' + (name=='ALL' ? '全部用户' : name) + '】',
		  modal : true,
		  closed: false,
		  buttons : [
				     {
				        text:'确定',
				    	handler:function(){
				    	   var rows = $('#selectUserTb').datagrid('getChecked');
				    	   var result = false,isRepeated = false; 
				    	   if(rows){
				    		  for(var i=0;i<rows.length;i++){
				    			  result = addRow('user_item_list_dlg',new WaringUser(rows[i]),'staffId');
				    			  if(result)
				    		        isRepeated = result;
				    		  }
				    	   }
				    	   if(isRepeated){
				    	      $.messager.show({
				    	        title : '提示',
								msg : '重复记录已被删除'
				    	      });
				    	   }
				    	   $('#selectUserDlg').dialog('close');
						 }
					 }
		      ]
		   });
		   $('#selectUserTb').datagrid({
			   url : '../erpMsgWarningRuleController/userSearchList?name=' + name ,
			   title : '用户列表',
			   pagination : 'true',
		       fitColumns : true,
		       singleSelect : false
		   });
  }
  
  function selectArgument(){  //查找对应的预警通知用户
	   $('#selectArgumentDlg').css('display','');
	   var name = $('#fm input[name="argumentName"]').val();
	   if(name == '')
	      name = 'ALL';
	   $('#selectArgumentDlg').dialog({
		  title : '选择参数名为【' + (name=='ALL' ? '全部参数' : name) + '】',
		  modal : true,
		  closed: false,
		  buttons : [
				     {
				        text:'确定',
				    	handler:function(){
				    	   var rows = $('#selectArgumentTb').datagrid('getChecked');
				    	   var result = false,isRepeated = false; 
				    	   if(rows){
				    		  for(var i=0;i<rows.length;i++){
				    		      var index = $('#argument_item_list_dlg').datagrid('getRows').length;
				    		      result = addRow('argument_item_list_dlg',new Argument(rows[i]),'configId');
				    		      if(result)
				    		        isRepeated = result;
				    			  editrow(index);
				    		  }
				    	   }
				    	   if(isRepeated){
				    	      $.messager.show({
				    	        title : '提示',
								msg : '重复记录已被删除'
				    	      });
				    	   }
				    	   $('#selectArgumentDlg').dialog('close');
						 }
					 }
		      ]
		   });
		   $('#selectArgumentTb').datagrid({
			   url : '../erpMsgWarningRuleController/argumentSearchList?name=' + name ,
			   title : '参数列表',
			   pagination : 'true',
		       fitColumns : true,
		       singleSelect : false
		   });
  }
  
  
  function WaringUser(row){
     this.staffId = row.staffId;
     this.staffName = row.name;
     this.telephone = row.mphoneNo;
     this.email = row.email;
  }
  
  function userOperation(value,row,index){
     return '<a href="javascript:deleteRow(\'' + row.staffId + '\');">删除</a>';
  }
  
  function deleteRow(staffId){
     var userItem = $('#user_item_list_dlg').datagrid('getRows');
     for(var i=0;i<userItem.length;i++){
        if(staffId == userItem[i].staffId)
        $('#user_item_list_dlg').datagrid('deleteRow', i);
     }
  }
  
  function argumentOperation(value,row,index){
     return '<a href="javascript:deleteArgumentRow(\'' + row.configId + '\');">删除</a>';
  }
  
  /*编辑表格*/
  function editrow(index){
	    $('#argument_item_list_dlg').datagrid('beginEdit', index);
  }
  
  function deleteArgumentRow(configId){
     var argumentItem = $('#argument_item_list_dlg').datagrid('getRows');
     for(var i=0;i<argumentItem.length;i++){
        if(configId == argumentItem[i].configId){
    	   $('#argument_item_list_dlg').datagrid('deleteRow', i);
        }
     }
  }
  
  function Argument(row){
     this.configId = row.configId;
     this.name = row.name;
     this.parameter = row.parameter;
     this.value = '';
  }
  
  function saveWaringRule(){
      $('#fm').form({
	      url : 'save',
	      onSubmit: function(){
	         var userItem = $('#user_item_list_dlg').datagrid('getRows');
	         $('#userJson').val($.toJSON(userItem)); 
	         
	         var argumentItem = $('#argument_item_list_dlg').datagrid('getRows');
	         for(var i=0;i<argumentItem.length;i++){
	            var ed = $('#argument_item_list_dlg').datagrid('getEditor', {index:i,field:'value'});
	            argumentItem[i].value = $(ed.target).val();
	         }
	         $('#argumentJson').val($.toJSON(argumentItem));
	         return $(this).form('validate');
	      },
	      success : function(data){
	         var result = eval('(' + data + ')');
	         if(!result.success){
	            $.messager.alert('警告',result.msg,'warning');
	            return;
	         }
	         $.messager.show({ 
				title : '提示',
				msg : result.msg
			 });
	      }
	   });
	   
	   $('#fm').submit();
  }
  
  function addRow(table,row,pkId){
      var rows = $('#'+table).datagrid('getRows');
     
      for(var i=0;i<rows.length;i++){
          if(rows[i][pkId] == row[pkId]) {
             return true;
          }
      }
      
      $('#'+table).datagrid('appendRow',row);
  }
  
</script>
</html>