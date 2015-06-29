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
	$(function(){
		  
	});
	//新建
	function newinsert(dlg,fm,title) {
	    //清空校验信息
		$('#groupNameInfo').html("*(必填)");
		$('#classNameInfo').html("*(必填)");
		$('#methodNameInfo').html("*(必填)");
		$('#methodArgsInfo').html("");
		
	
		$('#'+dlg).dialog('open').dialog('setTitle', title);
		if(arguments.length>3){
			$('#'+fm).form('clear'); 
			$('#'+fm).form('load', arguments[3]); 
		}else{
			$('#'+fm).form('clear'); 
		}
	}
	//新建，采用ajax校验,id为input的id,flag为标记
	function validate(id,flag,fm){
		$.ajax({
			type : "POST",
			async : false,              //取消异步请求
			url : "./validate?flag="+flag,
			data : $("#"+fm).serialize(),
			dataType : "text",             
			success : function(r) {
				var result = eval('('+ r +')');
				$("#"+id+"Info").html("");
				$("#"+id+"Info").html(result.msg);
			},
			error:function(r){
				
			}
		});
	}
	//修改
	function newedit(dlg,fm,table,title) {
		 //清空校验信息
		 $('#groupName1Info').html("");
		 $('#className1Info').html("");
		 $('#methodName1Info').html("");
		 $('#methodArgs1Info').html("");
		 
		 var rows = $('#'+table).datagrid('getSelections');
		 
		 if(rows==null||rows==''||rows.length==0){
			 jQuery.messager.alert('提示:','请选择操作记录!');   
			 return ; 
		 }
		 if(rows.length>1){
			 jQuery.messager.alert('提示:','只能操作一条记录!');   
			 return ;
		 }
		var row = $('#'+table).datagrid('getSelected');
		if (row) {
			$('#'+dlg).dialog('open').dialog('setTitle', title);
			$('#'+fm).form('load', row); 
		}
	}
	//修改，采用ajax校验,id为提示信息span对应的id,flag为标记
	function validate2(id,flag,fm){
		$.ajax({
			type : "POST",
			async : false,              //取消异步请求
			url : "./validate?flag="+flag,
			data : $("#"+fm).serialize(),
			dataType : "text",             
			success : function(r) {
				var result = eval('('+ r +')');
				$("#"+id+"Info").html("");
				$("#"+id+"Info").html(result.msg);
			},
			error:function(r){
				
			}
		});
	}
	//保存方法，覆盖js中的，这里需要校验，需要特殊处理
	function newsave(dlg,fm,table,url) {
		var args = arguments;
		$('#'+fm).form('submit', {
			url:url,
			onSubmit : function() {
				//return $(this).form('validate');
				if(url=="./addGroup"){
					var groupnameInfo = $('#groupNameInfo').html().trim();
					var classnameInfo = $('#classNameInfo').html().trim();
					var methodnameInfo = $('#methodNameInfo').html().trim();
					var methodArgsInfo = $('#methodArgsInfo').html().trim();
					
					var groupname = $('#groupName').val().trim();
					var classname = $('#className').val().trim();
					var methodname = $('#methodName').val().trim();
					var methodArgs = $('#methodArgs').val().trim();
					
					
					//如果methodArgs为空，后台再校验一次
					if(methodnameInfo == ""){
						if(methodArgsInfo==""){
							validate("methodArgs","4",fm);  //先后台验证，这里需要取消ajax的异步请求，async : false
							methodArgsInfo = $('#methodArgsInfo').html().trim();
						}
					}
					if(groupname=="" && classname=="" && methodname==""){
						validate("groupName","1");
						return false;
					}
					if(groupnameInfo=="" && classnameInfo=="" && methodnameInfo=="" && methodArgsInfo==""){
						return true;
					}else{
						return false;
					}
					if(methodArgs.length>64){
						return false;
					}else{
						return true;
					}
				}
				if(url=="./updateGroup"){
				
					var groupnameInfo = $('#groupName1Info').html().trim();
					var classnameInfo = $('#className1Info').html().trim();
					var methodnameInfo = $('#methodName1Info').html().trim();
					var methodArgsInfo = $('#methodArgs1Info').html().trim();
					
					var groupname = $('#groupName1').val().trim();
					var classname = $('#className1').val().trim();
					var methodname = $('#methodName1').val().trim();
					var methodArgs = $('#methodArgs1').val().trim();
					
					//如果methodArgs为空，后台再校验一次
					if(methodnameInfo == ""){
						if(methodArgsInfo==""){
							validate2('methodArgs1','4','fm2');  //先后台验证，这里需要取消ajax的异步请求，async : false
							methodArgsInfo = $('#methodArgs1Info').html().trim();
						}
					}
					
					if(groupname=="" && classname=="" && methodname==""){
						validate2("groupName","1");
						return false;
					}
					if(groupnameInfo=="" && classnameInfo=="" && methodnameInfo=="" && methodArgsInfo==""){
						return true;
					}else{
						return false;
					}
					if(methodArgs.length>64){
						return false;
					}else{
						return true;
					}
				}
				
			},
			success : function(msg) {
				//alert(msg);
				/*	var result = eval('(' + results + ')');
				if (result.success) {
					$('#'+table).datagrid('reload');
					$.messager.show({
						title : '提示',
						msg : result.msg
					});
					$('#'+dlg).dialog('close'); 
				} else {
					$('#'+dlg).dialog('close'); // close the dialog
					$('#'+table).datagrid('reload'); // reload the user data
				}*/
				if(args.length>5&&args[5]=='tree'){
					doMessage(msg,table,dlg,args[4],args[5]);
				}else if(args.length>4){
					doMessage(msg,table,dlg,args[4]);
				}else{
					doMessage(msg,table,dlg);
				}
				
			}
	});
}
</script>  
 
</head>
<body>
		
	<table id="dms_merchant_dg" title="功能列表" class="easyui-datagrid"
		style="height: 500px;width: auto;" url="./queryGroupList"
		toolbar="#dms_merchant_toolbar" pagination="true" rownumbers="false"
		fitColumns="true" singleSelect="false" selectOnCheck="true" checkOnSelect="true">
		<thead>
			<tr>
				<th data-options="field:'id',checkbox:true">主键ID</th>		 
	    	    <th data-options="field:'groupName',width:100, sortable:true">功能名称</th>
	    	    <th data-options="field:'className',width:100, sortable:true">Class对象名</th>
	    	    <th data-options="field:'methodName',width:100, sortable:true">方法名称</th>
	    	    <th data-options="field:'methodArgs',width:100, sortable:true">方法参数</th>
	    	    <th data-options="field:'createTime',width:80, sortable:true">创建时间</th>
	    	    <th data-options="field:'updateTime',width:80, sortable:true">更新时间</th>
			</tr>
		</thead>
	</table>
	<div id="dms_merchant_toolbar">
	  <div  id="menubar"  style="margin-bottom:5px">
			<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-add" plain="true" onclick="newinsert('dlg','fm','新建')">新建</a> 
			<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-edit" plain="true" onclick="newedit('dlg2','fm2','dms_merchant_dg','修改')">修改</a> 
			<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-remove" plain="true" onclick="newremove('dms_merchant_dg','./deleteGroup','id')">删除</a>
			<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-edit" plain="true" onclick="newshow('showdlg','showfm','dms_merchant_dg','查看')">查看</a> 
			</div>
	</div>
	
	<div id="dlg" class="easyui-dialog"
		style="width: 500px; height: auto; padding: 10px 20px" closed="true" >
		<div class="part-toolbar">
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true"
			 onclick="newsave('dlg','fm','dms_merchant_dg','./addGroup')">保存</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"  data-options="iconCls:'icon-save',plain:true"
			  onclick="javascript:$('#dlg').dialog('close')">取消</a>
		</div>
		<div class="part">
		<div class="part-title">功能添加</div>
		<form id="fm" method="post" novalidate>
				<p><label>功能名称:</label> 
				<input id="groupName" type="text" name="groupName" onblur="validate('groupName','1','fm')"/>
				<span id="groupNameInfo" class="info"></span>
				</p>
				<p><label>Class对象名:</label> 
					<input id="className" type="text" name="className" onblur="validate('className','2','fm')"/>
					<span id="classNameInfo" class="info"></span>
				</p>
				<p><label>方法名称:</label> 
					<input id="methodName" type="text" name="methodName" onblur="validate('methodName','3','fm')"/>
					<span id="methodNameInfo" class="info"></span>
				</p>
				<p><label>方法参数:</label> 
					<input id="methodArgs" type="text" name="methodArgs" onblur="validate('methodArgs','4','fm')"/>
					<span id="methodArgsInfo" class="info"></span>
				</p>
		</form>
		</div>
	</div>
	
	<div id="dlg2" class="easyui-dialog"
		style="width: 500px; height: auto; padding: 10px 20px" closed="true" >
		<div class="part-toolbar">
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true"
			 onclick="newsave('dlg2','fm2','dms_merchant_dg','./updateGroup')">保存</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"  data-options="iconCls:'icon-save',plain:true"
			  onclick="javascript:$('#dlg2').dialog('close')">取消</a>
		</div>
		<div class="part">
		<div class="part-title">功能修改</div>
		<form id="fm2" method="post" novalidate>
							<input type="hidden" name="id"/>
									<p><label>功能名称:</label> 
										<input id="groupName1" type="text" name="groupName" value="" onblur="validate2('groupName1','1','fm2')"/>
										<span id="groupName1Info" class="info"></span>
									</p>
									<p><label>Class对象名:</label> 
										<input id="className1" type="text" name="className" onblur="validate2('className1','2','fm2')"/>
										<span id="className1Info" class="info"></span>
									</p>
									<p><label>方法名称:</label> 
										<input id="methodName1" type="text" name="methodName" onblur="validate2('methodName1','3','fm2')"/>
										<span id="methodName1Info" class="info"></span>
									</p>
									<p><label>方法参数:</label> 
										<input id="methodArgs1" type="text" name="methodArgs" onblur="validate2('methodArgs1','4','fm2')"/>
										<span id="methodArgs1Info" class="info"></span>
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
							<input type="hidden" name="merchantId" /> 
							<p><label>功能名称:</label> 
								<input  type="text" name="groupName"  readonly="readonly" style="border: 0px"/></p>
							<p><label>Class对象名:</label> 
								<input  type="text" name="className"  readonly="readonly" style="border: 0px"/></p>
							<p><label>方法名称:</label> 
								<input  type="text" name="methodName"  readonly="readonly" style="border: 0px"/></p>
							<p><label>方法参数:</label> 
								<input  type="text" name="methodArgs"  readonly="readonly" style="border: 0px"/></p>
							<p><label>创建时间:</label> 
								<input  type="text" name="createTime"  readonly="readonly" style="border: 0px"/></p>
							<p><label>更新时间:</label> 
								<input  type="text" name="updateTime"  readonly="readonly" style="border: 0px"/></p>
			
		</form>
		</div>
	</div>
</body>
</html>