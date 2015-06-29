<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>主键生成器</title>
<#include "../../framework/comm/meta.ftl">
<style>
.readonly{
	background-color:#eee;
}
</style>
</head>
<body>
	<div class="part-toolbar">
	    <a href="javascript:update()" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true">更新</a>
	    <a href="javascript:cancel()" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true">取 消</a>
	</div>
	<div class="part-form">
            <div >
                <p><label>编码</label><input name="keyId" id="keyId" value="${keyVO.keyId}"   class="easyui-validatebox readonly" 
                	data-options="	required:true,
                					validType:[
                						'length[3,3]',
                						'remote[&quot;../validateKeyId/&quot;,&quot;keyId&quot;]'
                					]" type="text" readonly /></p>
                <p><label>名称</label><input name="keyName" value="${keyVO.keyName}" class="easyui-validatebox"  
                	required="true" type="text" /></p>
                <p><label>规则</label><input name="strategy" class="easyui-validatebox readonly" 
                	required="true" type="text" value="${keyVO.strategy}"  readonly  /></p>
                <p><label>当前值</label><input name="val" class="easyui-validatebox " 
                	 data-options="	required:true,
                					validType:[
                						'length[0,7]',
                						'number'
                					]" type="text" value="${keyVO.val}"  /></p>
                <p><label>最大值</label><input name="maxVal" class="easyui-validatebox readonly" 
                	required="true" validType="number" value="${keyVO.maxVal}" readonly type="text" /></p>
            </div>
    </div>
</body>
</html>
<script type="text/javascript">
$(document).ready(function(){
	
});
function update(){
	
	$.post("../updateKey",
			{	keyId:$("[name='keyId']").val(),
				keyName:$("[name='keyName']").val(),
				strategy:$("[name='strategy']").val(),
				val:$("[name='val']").val(),
				maxVal:$("[name='maxVal']").val(),
			},
			function(result){
   	 			closeWindow('编辑规则');
  			});
}

function cancel(){
	closeWindow('编辑规则');
}
		 	
</script>