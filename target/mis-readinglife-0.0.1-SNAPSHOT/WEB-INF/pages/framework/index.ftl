<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新经典后台</title>
 		<#include "../../framework/comm/meta.ftl">
   <style type="text/css">
			*{
				font-size:12px;
			}
			#header a{
				font-size:14px;
			}
			#mainwrap{
				margin:0;
			}
			#content{
				width:100%;
				padding:0;
				border:0;
			}
		</style>
		<style>
    		.panel-body{overflow:hidden;}
    	</style>
</head>
<body  class="easyui-layout layout-area" >
	<!--顶部布局-->
    <div data-options="region:'north',border:false"  class="layout-north">
       <!--<div class="sys-name">新经典综合管理平台</div>-->
        <ul>
        	<#list menuList as menu>
        		<li class="<#if menu.menuOne.icon!=null >${menu.menuOne.icon }</#if><#if menu.menuOne.icon==null||menu.menuOne.icon==''  >icon-menu-home</#if> <#if menu_index==0 >active</#if>" id='${menu.menuOne.privilId }' onClick="checkMenu('${menu.menuOne.privilId }')">${menu.menuOne.name }</li>
			</#list>
        </ul>
        <!--right 流 颠倒-->
        <div id="muser" class="muser icon-menu-user" rel="muser-menus">
        	<div class="icon-menu-point-down">
                <p>${positiName }</p>
                <p>${name }</p>
            </div>
        </div>
   </div>
   <!--end 顶部布局--> 
   <div id="muser-menus" class=" target_box dn muser-menus">
      <div class="icon-edit icons"><a href="javascript:void(0)" onclick="changePwd()">修改密码</a></div> 
      <div class="icon-group icons"><a href="javascript:void(0)" onclick="changePositi()">切换身份</a></div> 
      <div class="icon-cog icons"><a href="javascript:void(0)">系统配置</a></div>
      <div class="icon-cancel icons"><a href="${basePath}/logout">退出系统</a></div>   
    </div>
    <!--左侧布局-->
    <div data-options="region:'west',split:false,border:true,iconCls:'icon-feed'" class="layout-west" >
    <#list menuList as menu>
    		<div id="accordion_${menu.menuOne.privilId }" data-options="border:false" fit="true" class="<#if menu_index!=0 >hidden</#if> accordion-menu " >
	           <div class="quick-menu"></div>
	           <div class="accordion-con">
	               <div class="easyui-accordion" style="width:215px" fit="true">
	               <#list menu.menuTwo as menuTwo>
	                 <div title="${menuTwo.name }" data-options="iconCls:'icon-app-view'" style="overflow:auto;padding:0px;">
	                    <ul id="leftmenu" class="easyui-tree"
											data-options="  method:'get',
															url:'${basePath}/leftmenu',
															animate:true,
															lines:true,
															loadFilter:function(data,parent){
																//alert(alert(JSON.stringify(data)));
														    	return filtertree(data,'${ menuTwo.privilId }');
															}
															 "></ul>
					
	                </div>
	                </#list>
	               </div>
	            </div>
	         </div>
    </#list>
            
    </div>
    <!--end 左侧布局-->
    
	<!--中心布局-->
    <div data-options="region:'center',border:false" class="layout-center">
    	<#list menuList as menuList>
    		 <div id="tabs_${menuList.menuOne.privilId }" class="easyui-tabs  <#if menuList_index!=0 >hidden</#if><#if menuList_index==0 >maintabs</#if> " fit="true" >
	            <div title="${menuList.menuOne.name }" data-options="closable:false" style="padding:0px;">
	             	<div class="page-tabs-panel">
	                	<ul>
	                    	<li class="icons-tab-menu-list  <#if menuList_index!=1 >checked</#if>" type="list" onclick="checkPageTab(this)"  ><span></span></li>
	                    </ul>
	                </div>
	            	<div class="page-iframes-panel">
	                    <iframe type="list" class="tab-iframe" src="./welcome"  width="100%" height="600" frameborder="no"
	                    		 border="0" marginwidth="0" marginheight="0" scrolling="auto" allowtransparency="yes"></iframe>
	                </div>
	            </div>
	        </div>
    	</#list>
    	 
    </div>
    <!--end 中心布局-->
    
    <!--底部布局-->
    <div data-options="region:'south',border:false" class="layout-south">
        版权所有&copy;新经典文化有限公司 2013-2015
    </div>
    <!--end 底部布局-->
    
    <!-- 用户操作 -->
		<div id="change_positi_dlg" class="easyui-dialog"
			style="width: 350px; height: auto; " closed="true" >
			 <table id="sys_person_positi_dg"  class="easyui-datagrid"
				style="height: 200px; width: auto;" url="${basePath}/sysPersonPositiController/searchList?personId=${personId }"
				  singleSelect="true"
				selectOnCheck="true" checkOnSelect="true">
				<thead>
					<tr>
						<th field="staffId" hidden="true" sortable="true"></th>
						<th field="departId" hidden="true" sortable="true"></th>
						<th field="positiId" hidden="true" sortable="true"></th>
						<th field="name" width="150"  sortable="true">岗位名称</th>
						<th field="status" width="150"  data-options="formatter:function(val,row){
																if(row.positiId=='${positiId }'){
																	return '<font color=#F00>当前岗位</font>';
																}else{
																	 var positiId = row.positiId;
																	return '<a href=\'javascript:void(0)\'  onclick=maketrue(\''+positiId+'\') >确认切换</a>';	
																}
													}
							   " sortable="true">操作</th>
					</tr>
				</thead>
			</table>
		</div>
		
		<div id="change_pwd_dlg" class="easyui-dialog"
			style="width: 350px; height: auto; " closed="true" 
			buttons="#dlg-buttons">
			  	<form id="fm" method="post" novalidate>
					<input type="hidden" name="personId" value="${personId }" />
					<div class="fitem">
						<label>原密码:</label> <input name="loginOldPwd"
							class="easyui-validatebox" required="true"  type="password"    />
					</div>
					<div class="fitem">
						<label>新密码:</label> <input name="loginPwd" id="loginPwd"
							class="easyui-validatebox" required="true"  type="password"    />
					</div>
					<div class="fitem">
						<label>确认密码:</label> 
							<input id="reloginPwd" name="reloginPwd" type="password"  
						class="easyui-validatebox" required="true" validType="equalTo['#loginPwd']" />
					</div>
				</form>
		</div>
		<div id="dlg-buttons">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-ok"
				onclick="savepwd()">保存</a> <a
				href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-cancel" onclick="javascript:$('#change_pwd_dlg').dialog('close')">取消</a>
		</div>
	<!-- 用户操作end -->
    
</body>
</html>
<script type="text/javascript">
$(document).ready(function(){
	//通过默认rel属性加载
	$("#muser").powerFloat();
});
//窗口改变大小
$(window).resize(function(){
	resizeHeightWidth();
	$('.easyui-datagrid').datagrid('resize');
});
//页面加载完成后
$(document).ready(function(){
	resizeHeightWidth();	
});

/*****菜单******/
 function filtertree(data,val){
	var rows = data.rows;
	//alert(val);
	//alert(JSON.stringify(rows));
	 var json = '['+listtotree(rows,val)+']';
	  json = json.replace(/'/g,'"');
	  //alert(json);
	  return  eval('(' + json + ')');
}

 function listtotree(data,pid){
	 var treejson='';
	 for(var i=0;i<data.length;i++){
		 if(data[i].privilPid==pid&&
				 (data[i].type!=4&&data[i].type!=6&&data[i].type!=7&&data[i].type!=8&&data[i].type!=9&&data[i].type!=10&&data[i].type!=11)){//操作元素子集后都不显示 
			 if(treejson!=''){treejson+=',';}
				 var clickstr;
				 if(data[i].type==3){
					 clickstr = "<a href=javascript:createTab(\\\'"+data[i].name+"\\\',\\\'"+data[i].prograCode+"\\\')  >"+data[i].name+"<a>";
				 }else{
					 clickstr = ""+data[i].name;
				 }
			 treejson+="{'id':'"+data[i].privilId+"','text':'"+clickstr+"'";
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
 
 
 /********更改岗位**************/
 function changePositi(){
		$("#change_positi_dlg").dialog("open").dialog("setTitle", '切换岗位');
 }
 
 function maketrue(positiId){
	 window.location.href='${basePath}/changePositi?positiId='+positiId;
 }
 /*********更改密码*****************/
 function changePwd(){
	 $('#fm').form('load', {'loginOldPwd':'','loginPwd':'','reloginPwd':''}); 
	 $("#change_pwd_dlg").dialog("open").dialog("setTitle", '修改密码');
 }
 function savepwd() {
		$("#fm").form("submit", {
			url : '${basePath}/changePwd',
			onSubmit : function() {
				return $(this).form("validate");
			},
			success : function(e) {
				var result = eval('('+e+')');
				if(result.success){
					$('#change_pwd_dlg').dialog('close');
				}else{
					jQuery.messager.alert('提示:', result.msg);
				}
				
			}
		});
	}
 
</script>
