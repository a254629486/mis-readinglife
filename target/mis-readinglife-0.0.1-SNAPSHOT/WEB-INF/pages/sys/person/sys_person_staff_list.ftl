<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>员工列表</title>
<#include "../../framework/comm/meta.ftl">
</head>
<body class="easyui-layout"
	onended="initTable('sys_person_staff_dg','sys_person_staff_dg_search');">
	<div data-options="region:'west',split:true" border="true"
		style="width: 300px;">

		<iframe src="${basePath}/sysCompanyDepartController/selectCompanyDepart" scrolling="yes" height="99%" width="100%" frameborder="0"></iframe>
	</div>
	<div data-options="region:'center'">


		<table id="sys_person_staff_dg" title="员工列表" class="easyui-datagrid"
			fit="true" 
			toolbar="#sys_person_staff_toolbar" pagination="true"
			rownumbers="false" fitColumns="true" singleSelect="false"
			selectOnCheck="true" checkOnSelect="true">
			<thead>
				<tr>
					<th data-options="field:'staffId',checkbox:true"></th>
					<th field="loginName" sortable="true">员工登录名</th>
					<th field="loginPwd" hidden="true"></th>
					<th field="name" sortable="true">员工姓名</th>
					<th field="gender" hidden="true"></th>
					<th field="age" hidden="true"></th>
					<th field="email" hidden="true"></th>
					<th field="qq" hidden="true"></th>
					<th field="mphoneNo" hidden="true"></th>
					<th field="tphoneNo" hidden="true"></th>
					<th field="extensNo" hidden="true"></th>
					<th field="departId" hidden="true"></th>
					<th field="positiId" hidden="true"></th>
					<th field="personId" hidden="true"></th>
					<th field="status"  data-options=" formatter:typeFormatter " sortable="true">状态</th>
					<th field="companName" sortable="true">所属公司</th>
					<th field="departName" sortable="true">所属部门</th>
					<th field="positiName" sortable="true" data-options=" formatter:positiFormatter " >系统岗位</th>
				</tr>
			</thead>
		</table>
		<div id="sys_person_staff_toolbar">
			<div style="margin-bottom: 5px">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-add" plain="true" onclick="newperson('dlg','fm','新建')">添加员工</a>
				<a href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-edit" plain="true"
					onclick="neweditperson('dlg','fm','sys_person_staff_dg','修改')">修改</a>
				<a href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-ok" plain="true"
					onclick="persondepart('dlg-depart','fm-depart','sys_person_staff_dg','调换部门')">调换部门</a>
				<!--
				<a href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-ok" plain="true"
					onclick="changeperson()">调换人员</a>
					-->
				<a href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-ok" plain="true"
					onclick="makerole()">定制角色</a>
				<a href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-ok" plain="true"
					onclick="makeprivil()">定制权限</a>
				<a href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-ok" plain="true"
					onclick="makedatarole()">定制数据角色</a>
				<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-remove" plain="true"
				onclick="newremove('sys_person_staff_dg','./remove','staffId')">删除</a>
			</div>
			<div>
				<form id="sys_person_staff_dg_search"></form>
			</div>
		</div>

	 <div id="dlg" class="easyui-dialog"
		style="width: 800px; height: auto; padding: 10px 20px" closed="true" >
	<div id="part-toolbar">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true"
			onclick="newsave('dlg','fm','sys_person_staff_dg','./save')">保存</a> <a
			href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true"
			onclick="javascript:$('#dlg').dialog('close')">取消</a>
	</div>
		<div class="part">
			  
		<form id="fm" method="post" >
			<input type="hidden" name="staffId" />
			<input type="hidden" name="personId" />
			<input type="hidden" name="departId" />
			<input type="hidden" name="status" />
			<input type="hidden" name="positiId" id="positiId" />
				<p>
					<label>上级机构:</label> <input name="companyName" readonly="readonly" class="easyui-validatebox"
						style="border: 0px;" /> 
				</p>
				<p>
					<label style="margin-left: 150px">所属部门:</label> 
					<input  name="departName"  readonly="readonly" style="border: 0px;" class="easyui-validatebox" />
				</p>
				<p>
				<label>员工系统岗位:</label> <input type="text"  id="positiName" name="positiName" readonly="readonly"
					class="easyui-validatebox" required="true" />
					
					 <a id="dd" href="javascript:void(0)" class="easyui-tooltip" style="margin-left: 120px;margin-right: 120px;"
							data-options="
									hideEvent: 'mouseleave',
									showEvent: 'click',
									position: 'right',
									content: function(){
										$('#toolbar').css({'margin-top':'0px'}); 
										return $('#toolbar');
									},
									onShow: function(){
										$('#toolbar').find('iframe').attr('src','${basePath}/sysCompanyDepartPositiController/personSelectCompanyDepartPositi?positiId='+$('#positiId').val());
										$('#toolbar').css({'margin-top':'0px'}); 
									 
										var t = $(this);
										t.tooltip('tip').unbind().bind('mouseenter', function(){
											t.tooltip('show');
										}).bind('mouseleave', function(){
											t.tooltip('hide');
										});
									}
									">配置系统岗位</a> 
					
				</p>
				<!--
			<div id="addpersontype" class="fitem">
			<p style="margin-right: 220px;">
				<label>新增类型：</label>
				<input  type="radio" id="isNewPerson" name="isNewPerson" onclick="checkNewPerson('yes')" value="yes" checked="checked"/>新员工注册
				<input   type="radio" name="isNewPerson" onclick="checkNewPerson('no')" value="no" />系统员工分配
			</p>
			</div>
			-->
			<div id="addpersondiv__">
				<p>
					<label>姓名:</label> <input type="text"  name="name" 
						class="easyui-validatebox" required="true"/>
				</p>
				<p>
					<label  style="margin-left: 150px">登陆名:</label> <input type="text"  name="loginName" 
						class="easyui-validatebox" required="true" validType="" />
				</p>
				<p style="margin-right: 420px;">
					<label>设置密码:</label> 
						<input type="radio" name="isResetPassword" id="isResetPassword" onclick="checkRestPassword('yes')" value="yes" >是
						<input type="radio" name="isResetPassword" id="isResetPassword2"  onclick="checkRestPassword('no')" value="no" >否
				</p>
				<div id="passworddiv">
				<p>
					<label>登陆密码:</label> 
					<input  name="loginPwd" id="loginPwd" type="password" style="border: 1px solid #ccc; width: 160px;height: 20px;"
						class="easyui-validatebox" required="true"  />
				</p>
				<p>
					<label  style="margin-left: 150px">确认密码:</label> 
					<input  name="reloginPwd" type="password"  style="border: 1px solid #ccc; width: 160px;height: 20px;"
						class="easyui-validatebox" required="true" validType="equalTo['#loginPwd']" />
				</p>
				</div>
				<p>
					<label>性别:</label>   <input type="text"  class="easyui-combobox easyui-validatebox"	 
										name="gender"
										data-options="
										url:'${basePath}/sysConstantsController/getTypeList/SEX',
										method:'get',
										valueField:'id',
										textField:'text',
										panelHeight:'auto' 
										">
				</p>
				<p>
					<label style="margin-left: 150px">年龄:</label> <input type="text"  name="age" class="easyui-validatebox"
						validType="number" />
				</p>
				<p>
					<label>邮箱:</label> <input type="text"  name="email" class="easyui-validatebox" required="true" 
						validType="email" />
				</p>
				<p>
					<label style="margin-left: 150px">QQ:</label> <input type="text"  name="qq" class="easyui-validatebox"
						validType="QQ" />
				</p>
				<p>
					<label>手机号:</label> <input type="text"  name="mphoneNo"
						class="easyui-validatebox" validType="mobile" />
				</p>
				<p>
					<label style="margin-left: 150px">电话:</label> <input type="text"  name="tphoneNo" class="easyui-validatebox"
						validType="" />
				</p>
				<p>
					<label>分机:</label> <input type="text"  name="extensNo" class="easyui-validatebox" 
						validType="" />
				</p>
			</div>
			<div id="addsyspersondiv">
				<p>
					<label>姓名:</label> <input type="text"  name="name" class="easyui-validatebox" id="name2" readonly="readonly" 
						required="true" validType="" />
						 <a  href="javascript:void(0)" style="margin-left: 120px" onclick="sysperson()" style="margin-left: 20px" >选择员工</a> 
				</p>
			</div>
		</form>
		</div>
	</div>

		 
	 <div id="dlg-depart" class="easyui-dialog"
		style="width: 400px; height: auto; padding: 10px 20px" closed="true"
		buttons="#dlg-depart-buttons">
		<div class="ftitle">员工详细</div>
		<form id="fm-depart" method="post" novalidate>
			<input type="hidden" name="staffId" />
			<input type="hidden" name="personId" />
			<input type="hidden" name="departId" />
			<input type="hidden" name="positiId" id="positiId" />
				<p>
					<label>上级机构:</label> <input name="companyName" readonly="readonly" class="easyui-validatebox"
						style="border: 0px;" /> 
					</p>
			<p>
					<label>所属部门:</label> 
					<input name="departName"  readonly="readonly" style="border: 0px;" class="easyui-validatebox" />
						 <a id="dd-depart" href="javascript:void(0)" class="easyui-tooltip" style="margin-left: 0px"
							data-options="
									hideEvent: 'mouseleave',
									showEvent: 'click',
									position: 'right',
									content: function(){
										$('#toolbar-depart').css({'margin-top':'0px'}); 
										return $('#toolbar-depart');
									},
									onShow: function(){
										$('#toolbar-depart').find('iframe').attr('src','${basePath}/sysCompanyDepartController/selectCompanyDepart?method=changedepart');
										$('#toolbar-depart').css({'margin-top':'0px'}); 
									 
										var t = $(this);
										t.tooltip('tip').unbind().bind('mouseenter', function(){
											t.tooltip('show');
										}).bind('mouseleave', function(){
											t.tooltip('hide');
										});
									}
									">调换部门</a> 
					
				</p>
			<p>
				<label>姓名:</label> <input name="name" readonly="readonly" class="easyui-validatebox"
						style="border: 0px;" />
					</p>
			<p>
				<label>登陆名:</label> <input name="loginName"
					readonly="readonly" class="easyui-validatebox"
						style="border: 0px;"  />
			</p>
		<!-- 	<p>
						<label>状态:</label> <input class="easyui-combobox"	  readonly="readonly"
									style="border: 0px;width: 123px;" 
											name="status"
											data-options="
											url:'${basePath}/sysConstantsController/getTypeList/PERSON',
											method:'get',
											valueField:'id',
											textField:'text',
											panelHeight:'auto'
											">
					</p> -->
		</form>
	</div>
	<div id="dlg-depart-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-ok"
			onclick="newsave('dlg-depart','fm-depart','sys_person_staff_dg','./changeDepart')">保存</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-cancel" onclick="javascript:$('#dlg-depart').dialog('close')">取消</a>
	</div>	 
	
	 <div id="dlg-person" class="easyui-dialog"
		style="width: 600px; height: auto; " closed="true"  >
			<iframe scrolling="no" height="350px" width="585px" frameborder="0"></iframe>
	</div>
	
	<div id="dlg-staff" class="easyui-dialog"
		style="width: 550px; height: auto; " closed="true"  >
			<iframe scrolling="yes" height="550px" width="535px" frameborder="0"></iframe>
	</div>
	
	<div id="dlg-staff-data" class="easyui-dialog"
		style="width: 550px; height: auto; " closed="true"  >
			<iframe scrolling="yes" height="550px" width="535px" frameborder="0"></iframe>
	</div>
	
	<div id="dlg-privil" class="easyui-dialog"
		style="width: 600px; height: auto; " closed="true"  >
			<iframe scrolling="yes" height="550px" width="585px" frameborder="0"></iframe>
	</div>

	<div id="toolbar">
		<iframe scrolling="yes" height="300px" width="260px" frameborder="0"></iframe>
	</div>
	
	<div id="toolbar-depart">
		<iframe scrolling="yes" height="300px" width="260px" frameborder="0"></iframe>
	</div>
	</div>
	
	<script type="text/javascript">
		var depart;
		var isdepart=false;
		function newperson(dlg,fm,title) {
			if(!isdepart){
				 jQuery.messager.alert('提示:','请选择部门');   
				 return;
			}
			$('#'+dlg).dialog('open').dialog('setTitle', title);
			$('#'+fm).form('clear'); 
			$('#fm').form('load', depart); 
			$('#fm').form('load', {status:'1',gender:'1'}); 
			
			
			//密码处理
			$("#isResetPassword").attr("checked","checked");
			$("#isResetPassword2").attr("disabled","disabled");
			$("#loginPwd").attr("class","easyui-validatebox");
			$("#loginPwd").attr("required","true");
			checkRestPassword('yes');
			
			//新增员工方式
			$("#addpersontype").show();
			$("#addsyspersondiv").hide();
			$("#isNewPerson").attr("checked","checked");
			checkNewPerson('yes');
 
		}
		function treegridCallBackCompanyDepart(node,pnode,cnode){
			//alert(JSON.stringify(node));
			var param = '';
			if(node.type=='depart'){//部门
				depart =  {'companyName':pnode.name,'departName':node.name,'departId':node.departId };
				//param = '"departId":"'+node.departId+'"';
				 param = '"companId":"';
				 var p = getParam(node);
    			 param += (p==''?'---':p);
    			 param += '"';
				isdepart = true;
    		}else if(node.type=='company'){//公司
    			/*  var rows = node.children;
    			 if(rows!=undefined&&rows!='undefined'&&rows.length>0){
    					param = '"companId":"';
    					for(var i=0;i<rows.length;i++){
        		    		var row = rows[i];
        		    		if(i!=0){
        		    			param += ',';
        		    		}
        		    		param += row.departId;
        			      }
    					param += '"';
    			 } */
    			 
    			 param = '"companId":"';
    			 var p = getParam(node.children);
    			 param += (p==''?'---':p);
    			 param += '"';
    			 isdepart = false;
    		}
			$('#sys_person_staff_dg').datagrid('loadData', {
				total : 0,
				rows : []
			});
			if(param!=''){
				//alert(param);
				$("#sys_person_staff_dg").datagrid('options').queryParams = eval('({'+ param + '})');
				$('#sys_person_staff_dg').datagrid({
					url:'${basePath}/sysPersonStaffController/searchList',
					queryParams: $("#sys_person_staff_dg").datagrid('options').queryParams, 
					pagination : true
				});
			}
		}
		
		function getParam(node){
			var rows = node;
			var param = '';
			if(rows!=undefined&&rows!='undefined'&&rows.length>0){
				for(var i=0;i<rows.length;i++){
		    		var row = rows[i];
		    		if(i!=0){
		    			param += ',';
		    		}
		    		param += row.departId;
		    		param +=','+getParam(row.children);
			      }
			 }else if(rows!=undefined&&rows!='undefined'){
				 param += ','+rows.departId;
				 param +=','+getParam(rows.children);
			 }
			return param;
		}
		
		function treegridCallBackPositi(node){
			var name = '',value = '';
			for(var i=0;i<node.length;i++){
				if(i!=0){name+=',';}
				name += node[i].name;
				if(i!=0){value+=',';}
				value += node[i].departId;
			}
			$('#fm').form('load', {'positiName':name,'positiId':value}); 
		}
		var passwordval;
		function neweditperson(dlg,fm,table,title) {
			if(!isdepart){
				 jQuery.messager.alert('提示:','请选择部门');   
				 return;
			}
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
				passwordval = row.loginPwd;
			}
			$('#fm').form('load', depart); 
			
			//密码处理
			$("#isResetPassword2").attr("checked","checked");
			$("#isResetPassword2").removeAttr("disabled");
			$("#loginPwd").removeAttr("class");
			$("#reloginPwd").removeAttr("class");
			checkRestPassword('no');
			//岗位匹配
			//agaxMachData('${basePath}/sysPositiController/searchList','positiId','positiId','name','positiName');
			//新增员工方式
			$("#addpersontype").hide();
			$("#addsyspersondiv").hide();
		}
		
		function persondepart(dlg,fm,table,title) {
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
				passwordval = row.loginPwd;
			}
			$('#fm-depart').form('load', depart); 
		}
		
		function checkRestPassword(val){
			if(val=='yes'){
				$("#loginPwd").val('');
				$("input[name='reloginPwd']").val('');
				$("#passworddiv").show();
			}else{
				$("#loginPwd").val(passwordval);
				$("#passworddiv").hide();
			}
		}
		
		function agaxMachData(url,id,vid,name,vname){
			$.ajax({
				   type: "POST",
				   url: url,
				   success: function(msg){
					   var data =  eval('(' + msg + ')');
				       //alert( "Data Saved: " + data.rows );
				       var idval = $("#"+vid).val();
				       var val = '';
				     	for(var i=0;i<data.rows.length;i++){
				     		 val = data.rows[i][id];
				     		// alert((val!=null&&val!=''&&val==idval));
				     		if(val!=null&&val!=''&&val==idval){
				     			$("#"+vname).val(data.rows[i][name]);
				     			break;
				     		}
				     	}
				   }
				}); 
		}
		
		function changedepart(node,pnode){
			var depart =  {'companyName':pnode.name,'departName':node.name,'departId':node.departId};
			$('#fm-depart').form('load', depart); 
		}
		
		var _status =null;
		$(function(){
			$.ajax({
				   type: "POST",
				   url: '${basePath}/sysConstantsController/getTypeList/PERSON',
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
		
		function positiFormatter (value,row,index){
			return ''+value.replace(new RegExp(/(,)/g),'、');
		}
		
		function sysperson(){
			$('#dlg-person').find('iframe').attr('src','${basePath}/sysPersonController/selectPerson/maketrue_sysperson');
			$('#dlg-person').dialog('open').dialog('setTitle', '选择人员');
		}
		
		function maketrue_sysperson(oldrow){
			$('#fm').form('load', {name:oldrow.name,personId:oldrow.personId}); 
			$('#dlg-person').dialog('close');
		}
		
		function changeperson(){
			var rows = $('#sys_person_staff_dg').datagrid('getSelections');
			 if(rows==null||rows==''||rows.length==0){
				 jQuery.messager.alert('提示:','请选择操作记录!');   
				 return ; 
			 }
			 if(rows.length>1){
				 jQuery.messager.alert('提示:','只能操作一条记录!');   
				 return ;
			 }
			$('#dlg-person').find('iframe').attr('src','${basePath}/sysPersonController/selectPerson/maketrue_changeperson');
			$('#dlg-person').dialog('open').dialog('setTitle', '调换人员');
		}
		function maketrue_changeperson(oldrow){
			var row = $('#sys_person_staff_dg').datagrid('getSelected');
			if (row) {
				var data = 'staffId='+row.staffId+'&personId='+oldrow.personId+'&departId='+row.departId+'&positiId='+row.positiId+'&status='+row.status;
				$.ajax({
					   type: "POST",
					   url: './save',
					   data: data,
					   success: function(msg){
					      // alert( "Data Saved: " + status );
							$('#sys_person_staff_dg').datagrid('reload');  
							$.messager.show({ 
								title : '提示',
								msg : '操作成功'
							});
							$('#dlg-person').dialog('close');
					   }
					}); 
			}
		}
		function checkNewPerson(val){
			if(val=='yes'){
				$("#addpersondiv").show();
				$("#addsyspersondiv").hide();
				
				$("#loginName").removeAttr("class");
				$("#name").removeAttr("class");
				$("#email").removeAttr("class");
				$("#loginPwd").removeAttr("class");
				$("#reloginPwd").removeAttr("class");
				$("#name2").attr("class","easyui-validatebox");
			}else{
				$("#addpersondiv").hide();
				$("#addsyspersondiv").show();
				
				$("#name2").removeAttr("class");
				$("#loginName").attr("class","easyui-validatebox");
				$("#name").attr("class","easyui-validatebox");
				$("#email").attr("class","easyui-validatebox");
				$("#loginPwd").attr("class","easyui-validatebox");
				$("#reloginPwd").attr("class","easyui-validatebox");
			}
		}
		
		function makerole(){
			var rows = $('#sys_person_staff_dg').datagrid('getSelections');
			 if(rows==null||rows==''||rows.length==0){
				 jQuery.messager.alert('提示:','请选择操作记录!');   
				 return ; 
			 }
			 if(rows.length>1){
				 jQuery.messager.alert('提示:','只能操作一条记录!');   
				 return ;
			 }
			 var row = $('#sys_person_staff_dg').datagrid('getSelected');
				if (row) {
					$('#dlg-staff').find('iframe').attr('src','${basePath}/sysStaffCsmrolController/toSearchList?staffId='+row.staffId);
					$('#dlg-staff').dialog('open').dialog('setTitle', '定制角色');
				}
		}
		
		function makedatarole(){
			var rows = $('#sys_person_staff_dg').datagrid('getSelections');
			 if(rows==null||rows==''||rows.length==0){
				 jQuery.messager.alert('提示:','请选择操作记录!');   
				 return ; 
			 }
			 if(rows.length>1){
				 jQuery.messager.alert('提示:','只能操作一条记录!');   
				 return ;
			 }
			 var row = $('#sys_person_staff_dg').datagrid('getSelected');
				if (row) {
					$('#dlg-staff-data').find('iframe').attr('src','${basePath}/sysStaffCsmrolController/toDataSearchList?staffId='+row.staffId);
					$('#dlg-staff-data').dialog('open').dialog('setTitle', '定制数据角色');
				}
		}
		
		function makeprivil(){
			var rows = $('#sys_person_staff_dg').datagrid('getSelections');
			 if(rows==null||rows==''||rows.length==0){
				 jQuery.messager.alert('提示:','请选择操作记录!');   
				 return ; 
			 }
			 if(rows.length>1){
				 jQuery.messager.alert('提示:','只能操作一条记录!');   
				 return ;
			 }
			 var row = $('#sys_person_staff_dg').datagrid('getSelected');
				if (row) {
					$('#dlg-privil').find('iframe').attr('src','${basePath}/sysStaffCsmrolController/toStaffPrivil?staffId='+row.staffId+'&positiId='+row.positiId);
					$('#dlg-privil').dialog('open').dialog('setTitle', '定制权限');
				}
		}
		
		function saverole(val){
				//alert(val);
			 var row = $('#sys_person_staff_dg').datagrid('getSelected');
			$.ajax({
				   type: "POST",
				   url: '${basePath}/sysStaffCsmrolController/saveRole',
				   data: 'staffId='+row.staffId+'&roleId='+val,
				   success: function(msg){
						$.messager.show({
							title : '提示',
							msg : '操作成功'
						});
						$('#dlg-staff').dialog('close');
				   }
				});
		}
		function savedatarole(val){
				//alert(val);
			 var row = $('#sys_person_staff_dg').datagrid('getSelected');
			$.ajax({
				   type: "POST",
				   url: '${basePath}/sysStaffCsmrolController/saveDataRole',
				   data: 'staffId='+row.staffId+'&roleId='+val,
				   success: function(msg){
						$.messager.show({
							title : '提示',
							msg : '操作成功'
						});
						$('#dlg-staff-data').dialog('close');
				   }
				});
		}
		function saveprivil(val){
			 var row = $('#sys_person_staff_dg').datagrid('getSelected');
				$.ajax({
					   type: "POST",
					   url: '${basePath}/sysStaffCsmrolController/savePrivil',
					   data: 'staffId='+row.staffId+'&privilId='+val,
					   success: function(msg){
							$.messager.show({
								title : '提示',
								msg : '操作成功'
							});
							$('#dlg-privil').dialog('close');
					   }
					});
		}
	</script>
</body>
</html>