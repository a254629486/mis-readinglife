<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>    列表</title>
   <#include "../../framework/comm/meta.ftl" />
   
<style type="text/css">
	.info{
		color:red;
		padding-left:5px;
	}
</style>   
<script type="text/javascript">
	 function datagridSearch2(table,sfm){
		$('#'+sfm).form('submit', {
			url:"./allTaskList",
			onSubmit: function(){
			// do some check
			// return false to prevent submit;
			},
			success:function(data){
				var j =eval("("+data+")");
				//console.info(j);
				$('#'+table).datagrid('loadData',j);
			}
		});
    }
    function newremove(table,url,keyid) {
	var row = $('#'+table).datagrid('getSelected');
	
	
	if(row==null||row==''||row.length==0){
		 jQuery.messager.alert('提示:','请选择操作记录!');   
		 return ; 
	}
	
	var rows = $('#'+table).datagrid('getSelections');
	if(rows.length==1){
		if(row.responseState=='运行中'){
			jQuery.messager.alert('提示:','该任务正在运行，不能被删除!');
			return ;
		}
	}else{
		for ( var i = 0; i < rows.length; i++) {
			var row = rows[i];
			if(row.responseState=='运行中'){
				jQuery.messager.alert('提示:','有的任务正在运行，不能被删除!');
				return ;
			}
		}
	}
	if (row) {
		$.messager.confirm('提示', '是否确定删除?', function(r) {
			if (r) {
				 var rows = $('#'+table).datagrid('getSelections');
				 var ids = '';
					for ( var i = 0; i < rows.length; i++) {
						var row = rows[i];
						if(i!=0){ids+=',';}
					 	ids += row[keyid];
					}
				deleterow(table,url,ids,null);
			}
		});
	}
}
</script>  
 
</head>
<body>
		
		<div class="part">
				 <form id="dms_merchant_dg_search">
				 <p><label>任务名称:</label><input type="text" name="triggerName"  
		 			/></p>
		 		 <p><label>功能名称:</label><input type="text" name="triggerGroup"  
		 		 	
		 			/></p>
		 		 <p><label>任务状态:</label>
		 		 	    <select id="cc" class="easyui-combobox" name="responseState" panelHeight="auto" style="width:100px;">
						    <option value="">请选择</option>
						    <option value="ACQUIRED">运行中</option>
						    <option value="PAUSED">暂停中</option>
						    <option value="WAITING">等待中</option>
						    <option value="ERROR">任务出错</option>
					    </select>
		 		 </p>
				 <div class="part-buttons" style="padding-right:400px"> 
		 			<a href="#" onclick="datagridSearch2('dms_merchant_dg','dms_merchant_dg_search')" class="easyui-linkbutton" >查询</a>
					<a href="#" onclick="resetPage('dms_merchant_dg_search')" class="easyui-linkbutton">重置</a>
				 </div>
				 </form>
		</div>
		
		<table id="dms_merchant_dg" title="历史任务列表" class="easyui-datagrid"
			style="height:500px; width: auto;" url="./allTaskList"
			toolbar="#dms_merchant_toolbar" pagination="true" rownumbers="false"
			fitColumns="true" singleSelect="false" selectOnCheck="true"
			checkOnSelect="true">
			<thead>
				<tr>
					<th data-options="field:'id',checkbox:true">主键ID</th>		 
		    	    <th data-options="field:'triggerName',width:120, sortable:true">任务名称</th>
		    	    <th data-options="field:'triggerGroup',width:150, sortable:true">功能名称</th>
		    	    <th data-options="field:'responseState',width:120, sortable:true">任务状态</th>
		    	    <th data-options="field:'responseTime',width:120, sortable:true">创建时间</th>
				</tr>
			</thead>
		</table>
	
	<div id="dms_merchant_toolbar">
	  <div  id="menubar"  style="margin-bottom:5px">
			<a href="javascript:void(0)" class="easyui-linkbutton"                  
			iconCls="icon-remove" plain="true" onclick="newremove('dms_merchant_dg','./deleteHistoryTask','id')">删除</a>
			<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-edit" plain="true" onclick="newshow('showdlg','showfm','dms_merchant_dg','查看')">查看</a> 
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
							<p><label>任务名称:</label> 
								<input  type="text" name="triggerName"  readonly="readonly" style="border: 0px"/></p>
							<p><label>功能名称:</label> 
								<input  type="text" name="triggerGroup"  readonly="readonly" style="border: 0px"/></p>
							<p><label>任务状态:</label> 
								<input  type="text" name="responseState"  readonly="readonly" style="border: 0px"/></p>
							<p><label>任务结果:</label> 
								<input  type="text" name="responseResult"  readonly="readonly" style="border: 0px"/></p>
							<p><label>创建时间:</label> 
								<input  type="text" name="responseTime"  readonly="readonly" style="border: 0px"/></p>
			
		</form>
		</div>
	</div>
</body>
</html>