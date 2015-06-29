<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>主键生成器</title>
<#include "../../framework/comm/meta.ftl">
</head>
<body>
<div id="menubar">
    <a href="javascript:openWindow('新增规则','./keyAdd',600,300,true)" 
    	class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">新增规则</a>
    <a href="javascript:modifyKey()" 
    class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'">编辑规则</a>
   
</div>
<table id="keys" class="easyui-datagrid" style="height:550px" data-options="singleSelect:true,
            collapsible:true,
            url:'./key/json',
            method:'get',
            fitColumns: true,
            striped:true,
            toolbar:'#menubar'" rownumbers="true" pagination="false">
    <thead>
        <tr>
        	<th data-options="field:'keyName',width:200">主键名称</th>
            <th data-options="field:'keyId',width:50">主键编码</th>
            <th data-options="field:'date',width:100">当前日期</th>
            <th data-options="field:'val',width:100">当前序列值</th>
            <th data-options="field:'currentKey',width:200">当前主键值</th>
            <th data-options="field:'strategy',width:100">日期规则</th>
            <th data-options="field:'maxVal',width:100">序列最大值</th>
            
            
        </tr>
    </thead>
</table>

</body>
</html>
<script type="text/javascript">
function modifyKey(){
	var row = $("#keys").datagrid("getSelected");
	
	if(row){
		
		var url='./keyModify/'+row.keyId;
		openWindow('编辑规则',url,600,300,true);
	}else{
		alert("请选择编辑行");
	}
	
	
}		 	
</script>