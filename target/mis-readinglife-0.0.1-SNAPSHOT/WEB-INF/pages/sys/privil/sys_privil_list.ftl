<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>权限列表</title>
<#include "../../framework/comm/meta.ftl">
<link rel="stylesheet" type="text/css" href="${basePath}/static/css/treegrid.css">
</head>
<body class="easyui-layout" onended="initTable('sys_privil_dg','sys_privil_dg_search');initTable('sys_privil_dg_select','sys_privil_dg_search');">
		<div data-options="region:'west',split:true" border="true"  style="width: 300px;">

		<table id="sys_privil_dg" class="easyui-treegrid"
			style="width: 300px; height: auto;"
			data-options="
					    iconCls:'tree-folder',
					    rownumbers:false,
						animate:false,
						lines:false,
						collapsible:true,
						fitColumns:true,
					    url: './searchList',
					    method: 'get',
					    idField: 'privilId',
					    treeField: 'name',
					    showFooter: true,
					    onClickRow:function(node){
					    	//alert(JSON.stringify(node));
					    	var pnode = $(this).treegrid('find',node._parentId);
							//alert(pnode);
							var name = '无';
					    	if(pnode!=null&&pnode!=''){
					    		name = pnode.name;
					    	}
					        $('#showfm').form('load', node); 
					        $('#showfm').form('load', {'_parentId':name}); 
					        nodeid = node.privilId; 
					        cancel();
					        
					        //刷新按钮列表
				        	$('#button_grid').datagrid('loadData', {
								total : 0,
								rows : []
							});
							if(node.type=='3'){//功能菜单才显示
								$('#buttondiv').show();
								var url = './searchButtonList?privilPid='+node.privilId;
								$('#button_grid').datagrid({
									url:url 
								});
							}else{
								$('#buttondiv').hide();
							}
					        
					    },
					    onLoadSuccess:reladedata,
					    loadFilter:_loadFilter
					    ">
			<thead>
				<tr>
					<th data-options="field:'name',width:180">资源名称</th>
				</tr>
			</thead>
		</table>


	</div>
		<div data-options="region:'center'">
		
           		<fieldset style="width:800px;border:solid 1px #aaa;margin-top:5px;margin-left:5px;position:relative;">
      			  <legend>权限详细信息</legend>
      		 	  
      		 	  	<a href="javascript:void(0)" class="easyui-linkbutton"
						iconCls="icon-add" plain="true"
						onclick="add();">添加子权限</a> <a
						href="javascript:void(0)" class="easyui-linkbutton"
						iconCls="icon-edit" plain="true"
						onclick="edit()">修改</a> <a
						href="javascript:void(0)" class="easyui-linkbutton"
						iconCls="icon-remove" plain="true"
						onclick="treegridremove('sys_privil_dg','./remove','privilId')">删除</a>
		 
						<hr/>
 
			<div id="dlg" class="easyui-dialog1 part"
				style="width: 760px; height: auto; padding: 10px 20px;display: none;" closed="true"
				buttons="#dlg-buttons">
				<form id="fm" method="post" novalidate>
					<input type="hidden" class="easyui-validatebox" name="privilPid" />
					<p>
						<label>权限标识:</label> <input name="privilId"readonly="readonly" 
							style="border: 0px;" class="easyui-validatebox" validType="" />
					</p>
					<p>
						<label>权限父标识:</label> <input  name="_parentId"readonly="readonly"
							style="border: 0px;" class="easyui-validatebox" validType="" /> 
						 <a id="dd" href="javascript:void(0)" class="easyui-tooltip"
							data-options="
									hideEvent: 'mouseleave',
									showEvent: 'click',
									position: 'right',
									content: function(){
										$('#toolbar').css({'margin-top':'0px'}); 
										return $('#toolbar');
									},
									onShow: function(){
										$('#toolbar').find('iframe').attr('src','./selectPrivil');
										$('#toolbar').css({'margin-top':'0px'}); 
									 
										var t = $(this);
										t.tooltip('tip').unbind().bind('mouseenter', function(){
											t.tooltip('show');
										}).bind('mouseleave', function(){
											t.tooltip('hide');
										});
									}
									">更改父节点</a> 
									 
					</p>
					<p>
						<label>名称:</label> <input type="text"  name="name" class="easyui-validatebox"
							required="true" validType="" />
					</p>
					<p>
						<label>类型:</label>  
							  <input  type="text"  class="easyui-combobox easyui-validatebox"	 
									name="type"
									data-options="
									url:'${basePath}/sysConstantsController/getTypeList/PRIVIL_TYPE',
									method:'get',
									valueField:'id',
									textField:'text',
									panelHeight:'auto' 
									">
					</p>
					<p>
						<label>程序代码:</label> <input  type="text"   name="prograCode"  class="easyui-validatebox" required="true"  
							style="width: 425px" />
					</p>
					<p>
						<label>同级优先级:</label> <input type="text"  name="priori"
							class="easyui-validatebox" required="true" validType="number" />
					</p>
					<p>
						<label>图标样式:</label> <input type="text"  name="icon"
							class="easyui-validatebox"  validType="" />
					</p>
					<p>
						<label>状态:</label>   <input type="text"  class="easyui-combobox easyui-validatebox"	 
									name="status"
									data-options="
									url:'${basePath}/sysConstantsController/getTypeList/PRIVIL',
									method:'get',
									valueField:'id',
									textField:'text',
									panelHeight:'auto' 
									">
					</p>

				</form>
			</div>
			<div id="dlg-buttons" style="display: none;padding-left: 50px;">
				<a href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-ok"  
					onclick="newsave('','fm','sys_privil_dg','./save',null,'tree');">保存</a> <a
					href="javascript:void(0)" class="easyui-linkbutton"  iconCls="icon-cancel" 
					onclick="javascript:cancel();">取消</a>
			</div>


			<div id="showdlg" class="easyui-dialog1 part"
				style="width: 700px; height: auto; padding: 20px 50px;" closed="true" buttons="#showdlg-buttons">
				<form id="showfm" method="post" novalidate>
					<p>
						<label>权限标识:</label> <input  name="privilId"readonly="readonly" id="privilId"
							style="border: 0px;" class="easyui-validatebox" validType="" />
					 </p>
					<p>
						<label>权限父标识:</label> <input  name="_parentId" readonly="readonly"
							style="border: 0px;width: 200px;" />
					</p>
					<p>
						<label>名称:</label> <input  name="name" readonly="readonly"
							style="border: 0px" />
					</p>
					<p>
						<label>类型:</label> 
							<input  class="easyui-combobox"	  readonly="readonly"
							style="border: 0px;" 
									name="type"
									data-options="
									url:'${basePath}/sysConstantsController/getTypeList/PRIVIL_TYPE',
									method:'get',
									valueField:'id',
									textField:'text',
									panelHeight:'auto'
									">
					</p>
					<p>
						<label>程序代码:</label> <input name="prograCode" readonly="readonly"
							style="border: 0px;width: 400px" />
					</p>
					<p>
						<label>同级优先级:</label> <input  name="priori" readonly="readonly"
							style="border: 0px" />
					</p>
					<p>
						<label>图标样式:</label> <input  name="icon" readonly="readonly" style="border: 0px;width: 300px" 
							style="border: 0px" />
					</p>
					<p>
						<label>状态:</label><input  class="easyui-combobox"	  readonly="readonly"
							style="border: 0px;width: 123px;" 
									name="status"
									data-options="
									url:'${basePath}/sysConstantsController/getTypeList/PRIVIL',
									method:'get',
									valueField:'id',
									textField:'text',
									panelHeight:'auto'
									">
					</p>

				</form>
			</div>


			<div id="toolbar"
				style="position: absolute; z-index: 9999999; margin-top: -800px; border-width: 1px; border-style: solid; border-color: #D4D4D4;background-color: white;">
				<iframe scrolling="yes" height="300px" width="260px" frameborder="0"></iframe>
			</div>
		</fieldset>
			<fieldset id="buttondiv" style="d;width:800px;border:solid 1px #aaa;margin-top:5px;margin-left:5px;padding-bottom:5px;position:relative;">
      			  <legend>操作级权限</legend>
      			  	<table id="button_grid" class="easyui-datagrid"
							style="height: 400px; width: 800px;" url=""
							toolbar="#button_grid_toolbar" pagination="false" rownumbers="false"
							fitColumns="true" singleSelect="false" selectOnCheck="true"
							checkOnSelect="true">
							<thead>
								<tr>
									<th data-options="checkbox:true"></th>
									<th field="privilId" sortable="true">权限标示</th>
									<th field="name" sortable="true">名称</th>
									<th field="type" data-options=" formatter:typeFormatter " sortable="true">类型</th>
									<th field="prograCode" sortable="true">程序代码</th>
									<th field="priori" sortable="true">同级优先级</th>
									<th field="icon" sortable="true">图标样式</th>
									<th field="status"  data-options=" formatter:statusFormatter "  sortable="true">状态</th>
								</tr>
							</thead>
						</table>
						<div id="button_grid_toolbar">
							<div style="margin-bottom: 5px">
								<a href="javascript:void(0)" class="easyui-linkbutton"
									iconCls="icon-add" plain="true" onclick="addORupdate(1)">新建</a>
								<a href="javascript:void(0)" class="easyui-linkbutton"
									iconCls="icon-edit" plain="true"
									onclick="addORupdate(2)">修改</a> <a
									href="javascript:void(0)" class="easyui-linkbutton"
									iconCls="icon-remove" plain="true"
									onclick="newremove('button_grid','./removebutton','privilId')">删除</a> 
							</div>
						</div>
					      			  
      			  <div id="button_grid_dlg" class="easyui-dialog"
						style="width: 500px; height: auto; padding: 10px 20px" closed="true" >

					<div class="part-toolbar">
						<a href="javascript:void(0)" class="easyui-linkbutton"  data-options="iconCls:'icon-save',plain:true"
							onclick="newsave('button_grid_dlg','button_grid_fm','button_grid','./save')">保存</a> <a
							href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true"
							onclick="javascript:$('#button_grid_dlg').dialog('close')">取消</a>
					</div>

					<div class="part">
						<div class="part-title">操作极权限</div>
						<form id="button_grid_fm" method="post" novalidate>
							<input  type="hidden" class="easyui-validatebox" name="privilPid" />
							<p>
								<label>权限标识:</label> <input type="text"  name="privilId"readonly="readonly"
									style="border: 0px;width: 200px;" class="easyui-validatebox" validType="" />
							</p>
							<p>
								<label>名称:</label> <input type="text"  name="name" class="easyui-validatebox"
									required="true" validType="" />
							</p>
							<p>
								<label>类型:</label>  
									  <input type="text"  class="easyui-combobox easyui-validatebox"	 
											name="type"
											data-options="
											url:'${basePath}/sysConstantsController/getTypeList/PRIVIL_TYPE_BUTTON',
											method:'get',
											valueField:'id',
											textField:'text',
											panelHeight:'auto' 
											">
							</p>
							<p>
								<label>程序代码:</label> <input type="text"  name="prograCode"
									class="easyui-validatebox" required="true" validType="" />
							</p>
							<p>
								<label>同级优先级:</label> <input type="text"  name="priori"
									class="easyui-validatebox" required="true" validType="number" />
							</p>
							<p>
								<label>图标样式:</label> <input type="text"  name="icon"
									class="easyui-validatebox"  validType="" />
							</p>
							<p>
								<label>状态:</label>   <input type="text"  class="easyui-combobox easyui-validatebox"	 
											name="status"
											data-options="
											url:'${basePath}/sysConstantsController/getTypeList/PRIVIL',
											method:'get',
											valueField:'id',
											textField:'text',
											panelHeight:'auto' 
											">
							</p>
		
						</form>
					</div>
					</div>
					 
      		</fieldset>
		</div>
		<script type="text/javascript">
			var nodeid="";
			function edit(){
				var row = $('#sys_privil_dg').treegrid('getSelected');
				 if(row==null||row==''||row.length==0){
					 jQuery.messager.alert('提示:','请选择操作记录!');   
					 return ; 
				 }
				if (row) {
					 $('#fm').form('load', row); 
					 var pnode = $('#sys_privil_dg').treegrid('find',row._parentId);
					 var name = '无',privilId='';
				     if(pnode!=null&&pnode!=''){
				    		name = pnode.name;
				    		privilId = pnode.privilId;
				      }
					 $('#fm').form('load', {"_parentId":name,"privilPid":privilId}); 
				}
				$("#showdlg").hide();
				$("#dlg").show();
				$("#dlg-buttons").show();
			}
			function add(){
				var row = $('#sys_privil_dg').treegrid('getSelected');
				if(row==null||row==''||row.length==0){
					 jQuery.messager.alert('提示:','请选择父节点!');   
					 return ; 
				}
				$("#showdlg").hide();
				$("#dlg").show();
				$("#dlg-buttons").show();
				$('#fm').form('clear'); 
				if (row) {
					//alert(JSON.stringify(row)); 
					$('#fm').form('load', {"_parentId":row.name,"privilPid":row.privilId,type:'3',status:'1'}); 
				}
			
			}
			function cancel(){
				$("#showdlg").show();
				$("#dlg").hide();
				$("#dlg-buttons").hide();
			}
			function reladedata(){//alert(nodeid);
				closetree();
				if(nodeid==null||nodeid==''){
					return;
				}
				$('#sys_privil_dg').treegrid('select',nodeid);
				var row = $('#sys_privil_dg').treegrid('getSelected');
			    $('#showfm').form('load', row); 
			    var pnode = $('#sys_privil_dg').treegrid('find',row._parentId);
				 var name = '无',privilId='';
			     if(pnode!=null&&pnode!=''){
			    		name = pnode.name;
			    		privilId = pnode.privilId;
			      }
				 $('#showfm').form('load', {"_parentId":name,"privilPid":privilId}); 
			    cancel();
			}
			function _loadFilter(data,parentId){
		    	//alert(JSON.stringify(data));
		    	var str = JSON.stringify(data);
		    	str = str.replace(/privilPid/g,'_parentId');
		    	var jsondata = eval('(' + str + ')');
		    	var rows = jsondata.rows;
		    	for(var i=0;i<rows.length;i++){
		    		var row = rows[i];
		    		if(row.type=='1'){//根节点
		    		  	row['iconCls'] = 'icon-root';
		    		}else if(row.type=='2'){//导航
		    		 	row['iconCls'] = 'icon-menu_folderClosed';
		    		}else if(row.type=='3'){//菜单
		    		 	row['iconCls'] = 'icon-menu';
		    		}else if(row.type=='4'||row.type=='6'||row.type=='7'
		    				||row.type=='10'||row.type=='9'||row.type=='8'
		    				||row.type=='11'){//操作元素
		    		 	row['iconCls'] = 'icon-operate';
		    		} 
			        
			        if(row.status=='2'){//未启用
			        	row['iconCls'] = 'icon-leaf';
			        }
			        
			      }
		    	//alert(JSON.stringify(jsondata));
		    	return jsondata;
			}
		 function treegridCallBackPrivil(node){
			$('#fm').form('load', {'_parentId':node.name,'privilPid':node.privilId}); 
		 }
		
		 
		 function closetree(){
				//显示一级左侧树形
			    $('#sys_privil_dg').treegrid('collapseAll');
				$('#sys_privil_dg').treegrid('expandTo','049E16D3D1D24971A967496EBC08246A');
				
				$("#buttondiv").hide();
			}
		 
		 /******************buttoon grid***************************/
		 		
		var _status =null;
		$(function(){
			$.ajax({
				   type: "POST",
				   url: '${basePath}/sysConstantsController/getTypeList/PRIVIL',
				   success: function(msg){
					   _status =  eval('(' + msg + ')');
				      // alert( "Data Saved: " + status );
				   }
				}); 
		});
		function statusFormatter (value,row,index){
				//alert(_status.length);
				for(var i=0;i<_status.length;i++){
		     		 val = _status[i]['id'];
		     		// alert((val!=null&&val!=''&&val==value));
		     		if(val!=null&&val!=''&&val==value){
		     			 return _status[i]['text'];
		     		}
		     	}
		}
		var _type =null;
		$(function(){
			$.ajax({
				   type: "POST",
				   url: '${basePath}/sysConstantsController/getTypeList/PRIVIL_TYPE_BUTTON',
				   success: function(msg){
					   _type =  eval('(' + msg + ')');
				      // alert( "Data Saved: " + status );
				   }
				}); 
		});
		function typeFormatter (value,row,index){
				//alert(_status.length);
				for(var i=0;i<_type.length;i++){
		     		 val = _type[i]['id'];
		     		// alert((val!=null&&val!=''&&val==value));
		     		if(val!=null&&val!=''&&val==value){
		     			 return _type[i]['text'];
		     		}
		     	}
		}
		
		function addORupdate(val){
			if(val==1){
				var privilId = $("#privilId").val();
				if(privilId==null||privilId==''){
					 jQuery.messager.alert('提示:','请先保存功能菜单!');   
					return ;
				}
				newinsert('button_grid_dlg','button_grid_fm','新建',{type:4,status:1,privilPid:privilId});
			}else{
				newedit('button_grid_dlg','button_grid_fm','button_grid','修改');
			}
			
		}
		</script>
		
		 
</body>
</html>