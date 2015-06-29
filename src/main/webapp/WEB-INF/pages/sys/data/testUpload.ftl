<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>数据权限</title>
<#include "../../framework/comm/meta.ftl">
</head>
<body >
	<div class="part-toolbar">
	    <a href="javascript:next()" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-group'">推送</a>
	    <a href="javascript:view()" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-start'">查看工作流</a>
	</div>
<form id="formId" method="post"> 
--
--${sid}<br>
--${Request.sid}--<br>
--${Session["serialVersionUID"]}--<br>
--
	<div class="part-form">
		<br/>
	    <div>
	        <p><label class='fwb'>当前项目：</label>
	        	<select id="project" class="easyui-combobox" name="epmBugVO.projectId" onchange="changePorjectPaincipal()">
	        		<c:forEach var="epmProjectPO" items="${epmProjectPOList}">
					    <option value="${epmProjectPO.projectId}"> ${epmProjectPO.name}</option>
					</c:forEach>
	        	</select></p>
	        <p style="width: 200px"><label class='fwb'>项目组长： </label><span id="paincipal">${personName}</span></p>
	        <p><label class='fwb'>问题类型：</label>缺陷</p>
	    </div>
	    <div>
	    	<input type="hidden" name="bugId" id="bugId"  value="${epmBugVO.bugId}"></input>
	    	<input type="hidden" name="createStaffId"  value="${epmBugVO.createStaffId}"></input>
	    	
	         <p><label class='fwb'>BUG ID：</label>${epmBugVO.bugId}</p><br/>
	         
	         <p style="width:500px"><label>缺陷标题：</label><input onblur="hasTitle($(this).val());"  name="title" id="title" type="text" style="width:400px"></input></p><br/>
	         
	         <p><label>影响版本</label><input name="influenceVersion"  type="text"></p>
	         <p><label>优先级</label>
	         	<select id="priority" class="easyui-combobox" name="priority" >
	        		<c:forEach var="priority" items="${priorityList}">
					    <option value="${priority.lokcde}"> ${priority.lokcdeName}</option>
					</c:forEach>
	        	</select>
	         </p><br/>
	         
	         <p><label>报告人</label><input name="createPersonName"  readonly="readonly" type="text" value="${epmBugVO.createPersonName}" /></p>
	         <p style="width: 270px"><label>到期时间</label><input class="easyui-datebox"  name="expireDate"  type="text"></p><br/>
	         
	         <p><label>重要问题</label><input name="importanceFlag"  type="checkbox"></input></p><br/>
	         
	         <p><label>修复版本</label><input name="repairVersion"  type="text"></p><br/>
	         <p><label>附件</label>
	         	<a id="btnAttr" href="javascript:void(0)" >添加附件</a>
	         </p><br/>
	         
	         <p style="width:500px"><label>描述</label>
	         	<textarea name="bugDescription"  style="width:400px;height: 80px;"></textarea>
	         </p><br/>
	    </div>
    </div>	
    

    <div id="dlg" class="easyui-dialog" title="请选择操作" style="width:400px;height:200px;padding:10px"
            data-options="
                iconCls: 'icon-save',
                closed:true,
                modal:true,
                buttons: '#dlg-buttons'
            ">
        
        	<!-- <p><input checked="checked" name="type" type="radio"></input> 不合格bug</p> -->
        	<p><input checked="checked" name="type" type="radio"></input> 分配任务  &nbsp;  &nbsp;&nbsp;
        		经办人&nbsp;&nbsp;&nbsp;&nbsp;
        		 <select id="nextStaffCode" class="easyui-combobox"
        		 		 data-options="panelHeight:80" name="state" style="width:100px;">
        		 	<c:forEach var="sysStaffVO" items="${sysStaffVOList}">
					    <option value="${sysStaffVO.loginName}"> ${sysStaffVO.personName}</option>
					</c:forEach>
				 </select>
        		
        	</p>
        	<!-- 
        	<p><input name="type" type="radio"></input> 问题已解决&nbsp;
        		处理结果<select><option>--请选择--</option></select>
        	</p>
	    	 -->
    </div>
    <div id="dlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:save()">确定</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:$('#dlg').dialog('close')">关闭</a>
    </div>
</form>
</body>
</html>
<script type="text/javascript">
$(document).ready(function(){
	//上传下载插件
	$("#btnAttr").jupload({
		sessionId:"${sid}",
		size:1024*10,
		types:"*.jpg;*.jpeg;*.png;*.bmp;*.gif",
		params:{"orderId":'222'}
	});
	validateForm();
});
function next(){
	$('#dlg').dialog('open');
}
function view(){
	
}
function save(){
	if($("#formId").form('validate')){
		if($("[name='importanceFlag']").attr("checked")){
			importanceFlag=true;
		}else{
			importanceFlag=false;
		}
		
		$.post("./start", 
			{ 	bugId: $("[name='bugId']").val(), 
				createStaffId: $("[name='createStaffId']").val(), 
				title: $("[name='title']").val(), 
				influenceVersion: $("[name='influenceVersion']").val(), 
				priority:  $('#priority').combobox('getValue'),
				expireDate: $("[name='expireDate']").val(), 
				importanceFlag: importanceFlag, 
				repairVersion:  $("[name='repairVersion']").val(), 
				nextStaffCode:	$('#nextStaffCode').combobox('getValue'),
				projectId:$('#project').combobox('getValue')
			},
		  	function(data){
		     	//alert("Data Loaded: " + data);
		  		closePageTab('add');
		  	});
	}
}

function hasTitle(title){
	$.post("hasTitle",{"title":title},function(data){
		if(data=='false'){
			$("#title").val('');
			alert("数据库已经重复了");
		}
	});
}
function validateForm(){
	$("input[name='title']").validatebox({   
	    required:true ,
	    maxlength:64
	}); 
	$("input[name='influenceVersion']").validatebox({   
	    required:true ,
	    maxlength:64
	});
	$("input[name='priority']").validatebox({   
	    required:true ,
	    maxlength:8,
	    digits:true
	});
	$("input[name='expireDate']").validatebox({   
	    required:true ,
	    date:true
	});
	$("input[name='repairVersion']").validatebox({   
	    required:true,
	    maxlength:64
	});
	
}


function changePorjectPaincipal(){
	$.ajax({ 
	        type: "post", 
	        url: "./project/paincipal", 
	        data: 'projectId='+$("#project").val(),
	        dataType: "json", 
	        
	        success: function (data) { 
	        	$("#paincipal").text(data.personName);
	        }, 
	        error: function (XMLHttpRequest, textStatus, errorThrown) { 
	            alert(errorThrown); 
	        } 
	});
}
function addAccessory(){
	$("#upload-dlg").dialog('open');
}
</script>
