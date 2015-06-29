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
	    <a href="javascript:save()" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true">保 存</a>
	    <a href="javascript:cancel()" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true">取 消</a>
	</div>
	<div class="part-form">
            <div >
                <p><label>编码</label><input name="keyId" id="keyId"   class="easyui-validatebox" 
                	data-options="	required:true,
                					validType:[
                						'length[3,3]',
                						'remote[&quot;./validateKeyId/&quot;,&quot;keyId&quot;]'
                					]" type="text" /></p>
                <p><label>名称</label><input name="keyName" class="easyui-validatebox"  
                	required="true" type="text" /></p>
                <p><label>规则</label><input name="strategy" class="easyui-validatebox readonly" 
                	required="true" type="text" value="yyyyMM"  readonly /></p>
                <p><label>当前值</label><input name="val" class="easyui-validatebox " 
                	 data-options="	required:true,
                					validType:[
                						'length[0,7]',
                						'number'
                					]"  type="text" value="0"  /></p>
                <p><label>最大值</label><input name="maxVal" class="easyui-validatebox readonly" 
                	required="true" validType="number" value="9999999" readonly type="text" /></p>
            </div>
    </div>
</body>
</html>
<script type="text/javascript">
$(document).ready(function(){
	
});
function save(){
	
	$.post("./saveKey",
			{	keyId:$("[name='keyId']").val(),
				keyName:$("[name='keyName']").val(),
				strategy:$("[name='strategy']").val(),
				val:$("[name='val']").val(),
				maxVal:$("[name='maxVal']").val(),
			},
			function(result){
   	 			closeWindow('新增规则');
  			});
}
function cancel(){
	closeWindow('编辑规则');
}	 	
</script>