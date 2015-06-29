<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>岗位列表</title>
<#include "../../framework/comm/meta.ftl">
</head>
<body  class="easyui-layout"
	onended=" ">
		<div id="leftiframe" data-options="region:'west',split:true" border="true"  style="width: 280px;">
			<iframe src="${basePath}/sysCompanyDepartPositiController/selectCompanyDepartPositi" scrolling="yes" height="99%" width="100%" frameborder="0"></iframe>
		</div>
		<div data-options="region:'center'">
		<fieldset style="width:1050px;border:solid 1px #aaa;margin-top:5px;margin-left:5px;position:relative;">
      			  <legend>岗位详细信息</legend>
      			  
      		<a id="btnadd"  href="javascript:void(0)" class="easyui-linkbutton" 
						iconCls="icon-add" plain="true"
						onclick="add()">添加子岗位</a>
						 <a id="btnedit"
						href="javascript:void(0)" class="easyui-linkbutton"
						iconCls="icon-edit" plain="true"
						onclick="edit()">修改</a>
						 <a id="btndel"
						href="javascript:void(0)" class="easyui-linkbutton"
						iconCls="icon-remove" plain="true"
						onclick="_treegridremove()">删除</a>
		 	
						<hr/>
      		<div>
			 <div id="dlg" class="easyui-dialog2 part"
				style="width: 600px; height: auto; padding: 10px 20px;display: none" closed="true"
				buttons="#dlg-buttons">
				<form id="fm" method="post" novalidate>
					<input type="hidden" name="addorupdate" />
					<input type="hidden" name="departId" />
					<input type="hidden" name="positiId" />
					<input type="hidden" name="positiPid" />
					<input type="hidden" name="positiPrivilId" id="positiPrivilId" />
					<input type="hidden" name="positiRoleId" id="positiRoleId" />
					<input type="hidden" name="positiRoleDataId" id="positiRoleDataId" />
					<p>
						<label>上级岗位:</label> <input type="text"  name="pname" readonly="readonly"
							style="border: 0px" />
					</p>
					<p>
						<label>名称:</label> <input type="text"  name="name" class="easyui-validatebox"
							required="true" validType="" />
					</p>
					<p>
						<label>同级优先级:</label> <input type="text"  name="priori"
							class="easyui-validatebox" required="true" validType="number" />
					</p>
					<p>
						<label>状态:</label> <input type="text"  class="easyui-combobox"	   
							style="border: 0px;" 
									name="status"
									data-options="
									url:'${basePath}/sysConstantsController/getTypeList/POSITI',
									method:'get',
									valueField:'id',
									textField:'text',
									panelHeight:'auto'
									">
					</p>
		
				</form>
			</div>
			<div id="dlg-buttons" style="display: none">
				<a href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-ok"
					onclick="saveall()">保存</a> <a
					href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-cancel" onclick="javascript:cancel();">取消</a>
			</div>
		
		
			<div id="showdlg" class="easyui-dialog2 part"
				style="width: 600px; height: auto; padding: 10px 20px" closed="true"
				buttons="#showdlg-buttons">
				<form id="showfm" method="post" novalidate>
					<p>
						<label>上级岗位:</label> <input   name="pname" readonly="readonly"
							style="border: 0px" />
					</p>
					<p>
						<label>名称:</label> <input  name="name" readonly="readonly"
							style="border: 0px" />
					</p>
					<p>
						<label>同级优先级:</label> <input   name="priori" readonly="readonly"
							style="border: 0px" />
					</p>
					<p>
						<label>状态:</label>  <input  class="easyui-combobox"	  readonly="readonly"
							style="border: 0px;" 
									name="status"
									data-options="
									url:'${basePath}/sysConstantsController/getTypeList/POSITI',
									method:'get',
									valueField:'id',
									textField:'text',
									panelHeight:'auto'
									">
					</p>
		
				</form>
			</div>
			</div>
			<div>
		 	<div style="float: left;">
		           		 <fieldset style="width:500px;border:solid 1px #aaa;margin-top:5px;margin-left:5px;position:relative;">
			      			  <legend>岗位角色信息</legend>
			      			  <div>
				      			   <div style="float: left;">
									  <table id="left_data" class="easyui-datagrid" title="待选角色"
											style="height: auto; width: 210px;" 
											  rownumbers="false" fitColumns="true"
											singleSelect="true" selectOnCheck="true" checkOnSelect="true">
											<thead>
												<tr>
													<th data-options="field:'roleId',hidden:true">角色主键</th>
													<th field="name" sortable="true">角色名称</th>
													<th field="code" sortable="true">角色代码</th>
													<th field="status" sortable="true" data-options=" formatter:typeFormatter " >角色状态</th>
												</tr>
											</thead>
									</table>
									</div>
									 <div style="float: left;height:150px;padding-top: 30px;padding-left: 10px;padding-right: 10px;">
										<button onclick="tableDateMove('all','left_data','right_data')">&lt;&lt;</button><br/>
										<button onclick="tableDateMove('','left_data','right_data')">&lt;&nbsp;</button><br/>
										<button onclick="tableDateMove('','right_data','left_data')">&gt;&nbsp;</button><br/>
										<button onclick="tableDateMove('all','right_data','left_data')">&gt;&gt;</button>
									</div>
									 <div style="float: left;">
										  <table id="right_data" class="easyui-datagrid" title="已有角色"
										style="height: auto; width: 210px;"  
										  rownumbers="false" fitColumns="true"
										singleSelect="true" selectOnCheck="true" checkOnSelect="true">
										<thead>
											<tr>
												<th data-options="field:'roleId',hidden:true">角色主键</th>
												<th field="name" sortable="true">角色名称</th>
												<th field="code" sortable="true">角色代码</th>
												<th field="status" sortable="true" data-options=" formatter:typeFormatter " >角色状态</th> 
											</tr>
										</thead>
									</table>
									</div>
			      			  </div>
			      		</fieldset>
			</div>
			<div style="float: left;">
		           		 <fieldset style="width:500px;border:solid 1px #aaa;margin-top:5px;margin-left:5px;position:relative;">
			      			  <legend>岗位数据角色配置</legend>
			      			  <div>
				      			   <div style="float: left;">
									  <table id="left_db_data" class="easyui-datagrid" title="待选数据角色"
											style="height: auto; width: 210px;" 
											  rownumbers="false" fitColumns="true"
											singleSelect="true" selectOnCheck="true" checkOnSelect="true">
											<thead>
												<tr>
													<th data-options="field:'roleId',hidden:true">角色主键</th>
													<th field="name" sortable="true">角色名称</th>
													<th field="code" sortable="true">角色代码</th>
													<th field="status" sortable="true" data-options=" formatter:typeFormatter " >角色状态</th>
												</tr>
											</thead>
									</table>
									</div>
									 <div style="float: left;height:150px;padding-top: 30px;padding-left: 10px;padding-right: 10px;">
										<button onclick="tableDateMove('all','left_db_data','right_db_data')">&lt;&lt;</button><br/>
										<button onclick="tableDateMove('','left_db_data','right_db_data')">&lt;&nbsp;</button><br/>
										<button onclick="tableDateMove('','right_db_data','left_db_data')">&gt;&nbsp;</button><br/>
										<button onclick="tableDateMove('all','right_db_data','left_db_data')">&gt;&gt;</button>
									</div>
									 <div style="float: left;">
										  <table id="right_db_data" class="easyui-datagrid" title="已有数据角色"
										style="height: auto; width: 210px;"  
										  rownumbers="false" fitColumns="true"
										singleSelect="true" selectOnCheck="true" checkOnSelect="true">
										<thead>
											<tr>
												<th data-options="field:'roleId',hidden:true">角色主键</th>
												<th field="name" sortable="true">角色名称</th>
												<th field="code" sortable="true">角色代码</th>
												<th field="status" sortable="true" data-options=" formatter:typeFormatter " >角色状态</th> 
											</tr>
										</thead>
									</table>
									</div>
			      			  </div>
			      		</fieldset>
			</div>
			<div style="float: left;">
				 <fieldset style="width:300px;border:solid 1px #aaa;margin-top:5px;margin-left:5px;position:relative;">
	      			  <legend>岗位权限信息</legend>
								<ul id="tt" class="easyui-tree"
											data-options=" 
															method:'get',
															animate:true,
															checkbox:true,
															loadFilter:_loadFilter"></ul>
	      		</fieldset> 
			</div>
			</div>
		</fieldset>
		</div>

	<script type="text/javascript">
		var _node;var _pnode;
 		function treegridCallBackPositi(node,pnode){
 			_node = node;_pnode=pnode;
			//alert(JSON.stringify(node)); //_parentId
			 $('#showfm').form('load', node); 
			 var pname ;
			 if(pnode!=null&&pnode.name!=null&&pnode.name!=''){
				 pname = pnode.name;
			 }else{
				 pname = '无';
			 }
			 $('#showfm').form('load', {"pname":pname}); 
			 
			 if(node.type=='depart'){//部门
    		  		 $("#btnedit").hide();
    		  		 $("#btndel").hide();
    		  		 $("#btnadd").show();
	    		}else if(node.type=='company'){//公司
	    		 	 $("#btnedit").hide();
    		  		 $("#btndel").hide();
    		  		 $("#btnadd").hide();
	    		} else if(node.type=='positi'){//岗位
	    		 	 $("#btnedit").show();
    		  		 $("#btndel").show();
    		  		 $("#btnadd").hide();
	    		} 
			 
			// alert(node.departId );
			//刷新角色 、权限
			$("#left_data").datagrid('options').queryParams = eval('({"positiId":"'+ node.departId + '"})');
			$('#left_data').datagrid({
				url:'${basePath}/sysPositiRoleController/searchList',
				queryParams: $("#left_data").datagrid('options').queryParams 
			});
			$("#right_data").datagrid('options').queryParams = eval('({"positiId":"'+ node.departId + '"})');
			$('#right_data').datagrid({
				url:'${basePath}/sysPositiRoleController/searchSelectedList',
				queryParams: $("#right_data").datagrid('options').queryParams 
			});
			
			//刷新数据角色 、权限
			$("#left_db_data").datagrid('options').queryParams = eval('({"positiId":"'+ node.departId + '"})');
			$('#left_db_data').datagrid({
				url:'${basePath}/sysPositiRoleController/searchDbList',
				queryParams: $("#left_data").datagrid('options').queryParams 
			});
			$("#right_db_data").datagrid('options').queryParams = eval('({"positiId":"'+ node.departId + '"})');
			$('#right_db_data').datagrid({
				url:'${basePath}/sysPositiRoleController/searchSelectedDbList',
				queryParams: $("#right_data").datagrid('options').queryParams 
			});
			
		    $('#tt').tree({
		        url:'${basePath}/sysPositiRoleController/searchPositiPrivilList?positiId='+node.departId 
		        });
		    cancel();
 		}
 		function add(){ 
			var row = _node;
			if(row==null||row==''){
				 jQuery.messager.alert('提示:','请选择父节点!');   
				 return ; 
			}
			
			$.ajax({
				   type: "POST",
				   url: '${basePath}/sysPositiController/getInsertPk',
				   success: function(msg){
					   var pk =  eval('(' + msg + ')');
				      // alert( "Data Saved: " + status );
						$("#showdlg").hide();
						$("#dlg").show();
						$("#dlg-buttons").show();
						$('#fm').form('clear'); 
						if (row) {
							//alert(JSON.stringify(row)); 
							$('#fm').form('load', {"addorupdate":"add","positiId":pk.pk,"positiPid":row.positiId,status:'1',"pname": row.name,"departId":row.departId}); 
						}
				   }
				}); 
		}
		function edit(){ 
			var row = _node;
			if(row==null||row==''){
				 jQuery.messager.alert('提示:','请选择父节点!');   
				 return ; 
			}
			if(row.type=='company'){
				 jQuery.messager.alert('提示:','只能修改部门');   
				 return ; 
			}
			$("#showdlg").hide();
			$("#dlg").show();
			$("#dlg-buttons").show();
			if (row) {
				//alert(JSON.stringify(row)); 
				$('#fm').form('load', row); 
				$('#fm').form('load', {"addorupdate":"update","pname": _node.name,"positiPid":_node._parentId,"positiId":_node.departId,"departId":_node._parentId}); 
			}
		}
		function cancel(){
			$("#showdlg").show();
			$("#dlg").hide();
			$("#dlg-buttons").hide();
		}
		function saveall(){
			$("#positiRoleId").val(getTableItemDate('right_data','roleId'));
			$("#positiPrivilId").val(getChecked());
			$("#positiRoleDataId").val(getTableItemDate('right_db_data','dataRoleId'));
			newsave('dlg','fm','sys_positi_dg','${basePath}/sysPositiRoleController/save',savecallback());
		}
		function savecallback(){
			$('#leftiframe').find('iframe').attr('src','${basePath}/sysCompanyDepartPositiController/selectCompanyDepartPositi');
			cancel();
		}
		function _treegridremove(){
			deleterow('','${basePath}/sysPositiController/remove',_node.departId,'tree',callback());
		}
		function callback(){
			$('#leftiframe').find('iframe').attr('src','${basePath}/sysCompanyDepartPositiController/selectCompanyDepartPositi');
		}
		
		
		/*********************岗位权限*******************************/
		 function _loadFilter(data,parent){
			 var rows = data.rows;
			// alert(JSON.stringify(rows));
			 var json = '[{"id":"1000","text":"系统根菜单","iconCls":"icon-root","children":['+listtotree(rows,'1000')+']}]';
			  json = json.replace(/'/g,'"');
			 // alert(json);
			  return  eval('(' + json + ')');;
		}
		 
		 function listtotree(data,pid){
			 var treejson='';
			 for(var i=0;i<data.length;i++){
				 if(data[i].privilPid==pid){
					 if(treejson!=''){treejson+=',';}
					 treejson+="{'id':'"+data[i].privilId+"','text':'"+data[i].name+"'"+changeicon(data[i]);
					 var children = listtotree(data,data[i].privilId);
					 if(children!=''){
						 treejson+=",'children':["+children+"]";
					 }else if(data[i].positiId!=null&&data[i].positiId!=''){
						 treejson+=",'checked':true";
					 }
					 treejson+="}";
				 }
			  }
			 return treejson;
		 }
		 
		 function changeicon(row){
				if(row.type=='1'){//根节点
	    		  	return ",'iconCls':'icon-root'";
	    		}else if(row.type=='2'){//导航
	    			return ",'iconCls':'icon-menu_folderClosed'";
	    		}else if(row.type=='3'){//菜单
	    			return ",'iconCls':'icon-menu'";
	    		}else if(row.type=='4'||row.type=='6'||row.type=='7'
	    				||row.type=='10'||row.type=='9'||row.type=='8'
	    				||row.type=='11'){//操作元素
	    			return ",'iconCls':'icon-operate'";
	    		} 
		        
		        if(row.status=='2'){//未启用
		        	return ",'iconCls':'icon-leaf'";
		        }
		        return '';
		 }
		 function getChecked(){
			 var nodes = $('#tt').tree('getChecked',['checked','indeterminate']);
			 var s = '';
			 for(var i=0; i<nodes.length; i++){
				 if (s != '') s += ',';
				 s += nodes[i].id;
			 }
			 return s;
		}
		/***********岗位角色******************/
		
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