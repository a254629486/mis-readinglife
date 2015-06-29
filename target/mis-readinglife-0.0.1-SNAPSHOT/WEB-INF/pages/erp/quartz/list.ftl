<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title></title>
	<#include "../../framework/comm/meta.ftl">
	<script type="text/javascript">
	   function doCmd(state, triggerName, group, triggerState) {
		if (state == 'pause' && triggerState == 'PAUSED') {
			jQuery.messager.alert('系统提示','该任务己经暂停！');
			return;
		}

		if (state == 'resume' && triggerState != 'PAUSED') {
			jQuery.messager.alert('系统提示','该任务己经暂停！');
			return;
		}

		//客户端两次编码，服务端再解码，否测中文乱码 
		triggerName = encodeURIComponent(encodeURIComponent(triggerName));
		group = encodeURIComponent(encodeURIComponent(group));
		$.ajax({
			url : './operateTask?action=' + state + '&triggerName=' + triggerName + '&group=' + group,
			type : 'post',
			error : function() {
				jQuery.messager.alert('系统提示','操作失败！');
			},
			success : function(msgs) {
			var msg = eval('('+msgs+')');
				if (msg.success) {
					jQuery.messager.alert('系统提示',msg.msg);
				$('#task_list_dg').datagrid('reload');
				} else {
					jQuery.messager.alert('系统提示',msg.msg);
					}
			}
		});
	}
	
	function edit(triggerName,triggerState,triggerType){
	     if (triggerState != 'PAUSED') {
			jQuery.messager.alert('系统提示','该任务正在运行中，请先暂停！');
			return;
		}
		//客户端两次编码，服务端再解码，否测中文乱码 
		triggerName = encodeURIComponent(encodeURIComponent(triggerName));
		triggerType = encodeURIComponent(encodeURIComponent(triggerType));
		//$("#operateForm")[0].action = "./edit.do?triggerName="+triggerName+"&triggerType="+triggerType;
		//$("#operateForm")[0].submit();
		$('#dlg-new-task').find('iframe').attr('src','./edit.do?triggerName='+triggerName+'&triggerType='+triggerType);
	    $('#dlg-new-task').dialog('open').dialog('setTitle', '编辑定时任务');
	}
	
	function newTask(){
			$('#dlg-new-task').find('iframe').attr('src','${basePath}/taskManagementController/toNewTask');
			$('#dlg-new-task').dialog('open').dialog('setTitle', '添加定时任务');
		}
		
   function closedl(msg){
       jQuery.messager.alert('系统提示',msg.msg);
       $('#task_list_dg').datagrid('reload');
	   $('#dlg-new-task').dialog('close');
	}
	
	function operateFormatter(value,row,index){
	     return "<a href='#' onClick=\"doCmd('pause','"+row.triggerName+"','"+row.triggerGroup+"','"+row.statusEnglish+"')\">暂停</a>&nbsp;&nbsp;<a href='#' onClick=\"doCmd('resume','"+row.triggerName+"','"+row.triggerGroup+"','"+row.statusEnglish+"')\">恢复</a>&nbsp;&nbsp;<a href='#' onClick=\"doCmd('remove','"+row.triggerName+"','"+row.triggerGroup+"','"+row.statusEnglish+"')\">删除</a>&nbsp;&nbsp;<a href='#' onClick=\"edit('"+row.triggerName+"','"+row.statusEnglish+"','"+row.triggerType+"')\">编辑</a>";
	  }
	  
</script>
	</head>
	<body>
		<form id="operateForm" method="post">
		<table id="task_list_dg" title="定时任务列表" class="easyui-datagrid"
		style="height:500px;width:auto;" url="./querySchedulerTask" toolbar="#quartz_task_toolbar" 
		pagination="true" rownumbers="true"
		fitColumns="true" singleSelect="false" selectOnCheck="true" checkOnSelect="true"
		>
		<thead>
			<tr>
		    	  <th field="triggerName" sortable="true">任务名称</th>
		    	  <th field="triggerGroup" sortable="true">功能名称</th>
		    	  <th field="triggerExpressions" sortable="true">执行规则</th>
		    	  <th field="nextFireTime" sortable="true">下次执行时间</th>
		    	  <th field="preFireTime" sortable="true">上次执行时间</th>
		    	  <th field="status" sortable="true">状态</th>
		    	  <th field="startTime" sortable="true">开始时间</th>
		    	  <th field="endTime" sortable="true">结束时间</th>
		    	  <th field="test" data-options="formatter:operateFormatter">操作</th>
			</tr>
		</thead>
	</table>
	</form>
	<div id="quartz_task_toolbar">
	        <div  id="menubar"  style="margin-bottom:5px">
			<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-add" plain="true" onClick="newTask()">创建定时</a> 
			</div>
	</div>
    
    <div id="dlg-new-task" class="easyui-dialog"
		style="width: 583px; height: auto; " closed="true"  >
			<iframe scrolling="no" height="550px" width="583px" frameborder="0"></iframe>
	</div>
		
		</div>
	</div>
	</body>
</html>
