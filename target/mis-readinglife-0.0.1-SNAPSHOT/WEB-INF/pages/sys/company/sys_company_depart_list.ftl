<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>公司组织机构列表</title>
	<#include "../../framework/comm/meta.ftl">
</head>
<body  class="easyui-layout"
	onended="initTable('sys_company_depart_dg','sys_company_depart_dg_search');">
		<div id="leftiframe" data-options="region:'west',split:true" border="true"  style="width: 300px;">
			<iframe src="${basePath}/sysCompanyDepartController/selectCompanyDepart" scrolling="yes" height="99%" width="100%" frameborder="0"></iframe>
		</div>
		<div data-options="region:'center'">
				<fieldset style="width:700px;border:solid 1px #aaa;margin-top:5px;margin-left:5px;position:relative;">
      			  <legend>部门详细信息</legend>
      			  	  	<a href="javascript:void(0)" class="easyui-linkbutton"
						iconCls="icon-add" plain="true"
						onclick="add()">添加子部门/公司</a> <a
						href="javascript:void(0)" class="easyui-linkbutton"
						iconCls="icon-edit" plain="true"
						onclick="edit()">修改</a> <a
						href="javascript:void(0)" class="easyui-linkbutton"
						iconCls="icon-remove" plain="true"
						onclick="_treegridremove()">删除</a>
		 
						<hr/>
						
					<div id="dlg-show" class="easyui-dialog2 part"
							style="width: 92%; height: auto; padding: 10px 20px" closed="true" >		
		      			  <form id="fm-show" method="post" novalidate>
		      			  <p>
								<label>所属机构:</label> <input   name="pname" readonly="readonly"
									style="border: 0px" />
							</p>
							<p>
								<label>名称:</label> <input   name="name" readonly="readonly"
									style="border: 0px" />
							</p>
							<div id="ifCD-show">
								<p>
									<label>全名:</label> <input  name="fullName" readonly="readonly"
									style="border: 0px" />
								</p>
								<p>
									<label>地址:</label> <input name="addres" readonly="readonly"
									style="border: 0px"  />
								</p>
							</div>
							<p>
								<label>编码:</label> <input   name="code" readonly="readonly"
									style="border: 0px" />
							</p>
							<p>
								<label>同级优先级:</label> <input   name="priori" readonly="readonly"
									style="border: 0px" />
							</p>
							<p>
								<label>状态:</label>   <input   class="easyui-combobox"	  readonly="readonly"
							style="border: 0px;" 
									name="status"
									data-options="
									url:'${basePath}/sysConstantsController/getTypeList/DEPART',
									method:'get',
									valueField:'id',
									textField:'text',
									panelHeight:'auto'
									">
							</p>
							<p>
								<label>备注 :</label> <input   name="remark" readonly="readonly"
									style="border: 0px" />
							</p>
				
						</form>
      			  </div>
      			  <div id="dlg" class="easyui-dialog2 part"
					style="width: 92%; height: auto; padding: 10px 20px;display: none;" closed="true"
						buttons="#dlg-buttons">		
					<form id="fm" method="post" novalidate>
						<input type="hidden" name="departId" />
						<input type="hidden" name="departPid" />
						<input type="hidden" name="companId" />
						<p id="ifCD" style="width:700px">
							 添加子部门/公司: 
								<input type="radio" name="ifCD" id="iscompay" onclick="checkCD('yes')" value="yes"  >公司
								<input type="radio" name="ifCD" id="isdepart"  onclick="checkCD('no')" value="no" >部门
						</p>
						<p>
								<label>所属机构:</label> <input   name="pname" readonly="readonly"
									style="border: 0px" />
							</p>
						<p>
							<label>名称:</label> <input  type="text" name="name" class="easyui-validatebox"
								required="true" validType="" />
						</p>
						<div id="compshow">
							<p>
								<label>全名:</label> <input  type="text" type="text" name="fullName" />
							</p>
							<p>
								<label>地址:</label> <input type="text" name="addres" class="easyui-validatebox"
									validType="" />
							</p>
						</div>
						<p>
							<label>编码:</label> <input type="text"  name="code" class="easyui-validatebox"
								required="true" validType="" />
						</p>
						<p>
							<label>同级优先级:</label> <input type="text"  name="priori"
								class="easyui-validatebox" validType="number" required="true" />
						</p>
						<p>
							<label>状态:</label> 
									<input type="text"  class="easyui-combobox easyui-validatebox"	 
									name="status"
									data-options="
									url:'${basePath}/sysConstantsController/getTypeList/DEPART',
									method:'get',
									valueField:'id',
									textField:'text',
									panelHeight:'auto' 
									">
						</p>
						<p>
							<label>备注:</label> <input type="text"  name="remark" class="easyui-validatebox"
								validType="" />
						</p>
			
					</form>
				</div>
				<div id="dlg-buttons" style="display: none;padding-left: 50px;">
					<a href="javascript:void(0)" class="easyui-linkbutton"
						iconCls="icon-ok"
						onclick="newsave('','fm','sys_privil_dg','${basePath}/sysDepartController/save',null,'tree',savecallback());">保存</a> <a
						href="javascript:void(0)" class="easyui-linkbutton"
						iconCls="icon-cancel"
						onclick="javascript:cancel();">取消</a>
				</div>
				<div id="dlg-buttons-cp" style="display: none;padding-left: 50px;">
					<a href="javascript:void(0)" class="easyui-linkbutton"
						iconCls="icon-ok"
						onclick="newsave('','fm','sys_privil_dg','${basePath}/sysCompanController/save',null,'tree',savecallback());">保存</a> <a
						href="javascript:void(0)" class="easyui-linkbutton"
						iconCls="icon-cancel"
						onclick="javascript:cancelcp();">取消</a>
				</div>
				
				</fieldset>
		</div>
	
	<script type="text/javascript">
		var _node;var _pnode;
		function add(){ 
			 cancel();
			 cancelcp();
			 var row = _node;
			if(row==null||row==''){
				$("#iscompay").attr("disabled","disabled");
				$("#isdepart").attr("disabled","disabled");
				checkCD('yes');
			}else{
				$("#iscompay").removeAttr("disabled");
				$("#isdepart").removeAttr("disabled");
				checkCD('no');
			}
		}
		function edit(){ 
			 cancel();
			 cancelcp();
			var row = _node;
			if(row==null||row==''){
				 jQuery.messager.alert('提示:','请选择父节点!');   
				 return ; 
			}
			if(row.type=='company'){
				checkCD('yes');
				$("#ifCD").hide();
				$("#dlg-show").hide();
				$("#dlg").show();
				$("#dlg-buttons-cp").show();
				$("#dlg-buttons").hide();
				$('#fm').form('clear'); 
				if (row) {
					//alert(JSON.stringify(row)); 
					$('#fm').form('load', row); 
					$('#fm').form('load', {"departPid":'',"companId":row.departId,status:'1',"pname": '无'}); 
				}
				 return ; 
			}else{
				checkCD('no');
			 	$("#ifCD").hide();
			}
			$("#dlg-show").hide();
			$("#dlg").show();
			$("#dlg-buttons").show();
			if (row) {
				//alert(JSON.stringify(row)); 
				$('#fm').form('load', {"pname": _pnode.name,"departPid":_pnode.departId}); 
				$('#fm').form('load', row); 
			}
		}
		function treegridCallBackCompanyDepart(node,pnode,cnode){
			_node = node;_pnode=pnode;_cnode=cnode;
			//alert(JSON.stringify(node));
			 var pname ;
			 if(pnode!=null&&pnode.name!=null&&pnode.name!=''){
				 pname = pnode.name;
			 }else{
				 pname = '无';
			 }
			 if(node.type=='company'){
				$("#ifCD-show").show();
			 }else{
				$("#ifCD-show").hide();
			 }
			 $('#fm-show').form('load', node); 
			 $('#fm-show').form('load', {"pname":pname}); 
			 
			 cancel();
			 cancelcp();
		}
		function cancel(){
			$("#dlg-show").show();
			$("#dlg").hide();
			$("#dlg-buttons").hide();
		}
		function cancelcp(){
			$("#dlg-show").show();
			$("#dlg").hide();
			$("#dlg-buttons-cp").hide();
		}
		function savecallback(){
			$('#leftiframe').find('iframe').attr('src','${basePath}/sysCompanyDepartController/selectCompanyDepart');
			cancel();
			cancelcp();
		}
		function _treegridremove(){
			var row = _node;
				if(row==null||row==''){
					 jQuery.messager.alert('提示:','请选择操作节点!');   
					 return ; 
				}
				if(_cnode!=null&&_cnode.length>0){
					 jQuery.messager.alert('提示:','请先删除子部门!');   
					 return ; 
				}
					$.messager.confirm('提示', '是否确定删除?', function(r) {
						if (r) {
							 	if(row.type=='company'){
									deleterow('sys_company_depart_dg','${basePath}/sysCompanController/remove',_node.departId,'tree',callback());
								}else{
									deleterow('sys_company_depart_dg','${basePath}/sysDepartController/remove',_node.departId,'tree',callback());
								}
						}
					});
		
		}
		function callback(){
			$('#leftiframe').find('iframe').attr('src','${basePath}/sysCompanyDepartController/selectCompanyDepart');
		}
		
		function checkCD(val){
			if(val=='yes'){
				$("#compshow").show();
				$("#dlg-show").hide();
					$("#ifCD").show();
					$("#dlg").show();
					$("#dlg-buttons-cp").show();
					$("#dlg-buttons").hide();
					$('#fm').form('clear'); 
					$('#fm').form('load', {"departPid":'',"companId":'',status:'1',"pname": '无'}); 
					$("#isdepart").removeAttr("checked");
					$("#iscompay").attr("checked","checked");
			}else{
				$("#compshow").hide();
				$("#dlg-show").hide();
					$("#ifCD").show();
					$("#dlg").show();
					$("#dlg-buttons").show();
					$("#dlg-buttons-cp").hide();
					$("#iscompay").removeAttr("checked");
					$('#fm').form('clear'); 
					var row = _node;
						if (row) {
							if(row.type=='company'){
								$('#fm').form('load', {"departPid":row.departId,"companId":row.departId,status:'1',"pname": row.name}); 
							}else{
								$('#fm').form('load', {"departPid":row.departId,"companId":row.companId,status:'1',"pname": row.name}); 
							}
						}
					$("#isdepart").attr("checked","checked");
			}
			 
		}
	</script>
</body>
</html>