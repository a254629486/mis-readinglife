<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>    修改</title>
   <#include "../../../../framework/comm/meta.ftl" />
</title>
	<style>
		#container{float:left;}
	</style>
	<script>
		$(function(){
			initCombobox();
			if($('#hidden_messageType').val() == ""){
				$('#hidden_messageType').val($('#search_select_messageType').combobox('getValue'));
			}
			$('#search_select_messageType').combobox({
   				onSelect: function(record){
   					$('#hidden_messageType').val($(this).combobox('getValue'));
   					//$("#erp_msg_argument_config_dg").datagrid('options').url = BASE_PATH + "/erpMsgTemplateController/searchErpMsgArgumentConfigList/" + $(this).combobox('getValue');
   					//$("#erp_msg_argument_config_dg").datagrid('reload');
   				}
   			});
   			$('#search_select_templateType').combobox({
   				onSelect: function(record){
   					$('#hidden_type').val($(this).combobox('getValue'));
   				}
   			});
   			$('#search_select_status').combobox({
   				onSelect: function(record){
   					$('#hidden_status').val($(this).combobox('getValue'));
   				}
   			});
   			$('#erp_msg_argument_config_dg').datagrid({
   				onClickRow: function(rowIndex,rowData){
   					if($('#hidden_messageType').val() == "MSAA"){
   						var param = editor.getContentTxt()+'$'+'{'+rowData.parameter+'}';
   						editor.setContent(param);
   					}
   					if($('#hidden_messageType').val() == "MSAB"){
   						var param = editor.getContent()+'$'+'{'+rowData.parameter+'}';
   						editor.setContent(param);
   					}
   					if($('#hidden_messageType').val() == "MSAC"){
   						var param = editor.getContentTxt()+'$'+'{'+rowData.parameter+'}';
   						editor.setContent(param);
   					}
   				}
   			});
		})
		function initCombobox(){
			$('#search_select_messageType').combobox('setValue','${ErpMsgTemplate.messageType}');
			$('#search_select_templateType').combobox('setValue','${ErpMsgTemplate.type}');
			$('#search_select_status').combobox('setValue','${ErpMsgTemplate.status}');
		}
		function updateErpMsgTemplate(fm,url){
			setHiddenDataByForm();
			saveErpMsgTemplate(fm, url)
		}
		function setHiddenDataByForm(){
			if($('#hidden_messageType').val() == ""){
				$('#hidden_messageType').val($('#search_select_messageType').combobox('getValue'));
			}
			if($('#hidden_type').val() == ""){
				$('#hidden_type').val($('#search_select_templateType').combobox('getValue'));
			}
			if($('#hidden_status').val() == ""){
				$('#hidden_status').val($('#search_select_status').combobox('getValue'));
			}
			
			if($('#hidden_messageType').val() == "MSAA"){
				$('#hidden_content').val(editor.getContentTxt());
			}
			if($('#hidden_messageType').val() == "MSAB"){
				$('#hidden_content').val(editor.getContent());
			}
			if($('#hidden_messageType').val() == "MSAC"){
				$('#hidden_content').val(editor.getContentTxt());
			}
		}
		function saveErpMsgTemplate(fm, url){
			$('#'+fm).form('submit', {
				url : url,
				onSubmit : function() {
					return $(this).form('validate');
				},
				success : function(msg) {
					saveMessage(fm, msg);
				}
			});
		}
		function saveMessage(fm, msg){
			var result = eval('(' + msg + ')');
			if (result.success=='true'||result.success==true) {
				$.messager.show({ 
					title : '提示',
					msg : result.msg
				});
			} else {jQuery.messager.alert('提示:', result.msg);} 
		}
	</script>
</head>
<body class="easyui-layout">
	<div data-options="region:'west',title:'参数配置',split:true,collapsible:false" style="width: 380px;">
		<table id="erp_msg_argument_config_dg" title="参数配置信息" class="easyui-datagrid" url="${basePath}/erpMsgTemplateController/searchErpMsgArgumentConfigList/${ErpMsgTemplate.messageType}"
		toolbar="#catch_rule_toolbar" pagination="true" rownumbers="true" pageList="[10,20,30,40,50]"
		fitColumns="true" singleSelect="false" selectOnCheck="true" checkOnSelect="true"
		>
			<thead>
				<tr>
					<th data-options="field:'name'" width="100">参数名称</th>
					<th data-options="field:'parameter'" width="100">参数变量</th>
				</tr>
			</thead>
		</table>
   	</div>
	<div data-options="region:'center',title:'修改模板'" style="padding:5px; width: 1600px;">
		<div id="dlg">
			<div class="part-toolbar">
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true"
				 onclick="updateErpMsgTemplate('fm','${basePath}/erpMsgTemplateController/erpMsgTemplateupdate')">保存</a>
			</div>
			<form id="fm" method="post">
				<div class="part">
					<div class="part-title">
						抓取规则详细
					</div>
					<input type="hidden" name="templateId" value="${ErpMsgTemplate.templateId}"/>
					<input type="hidden" id="hidden_messageType" name="messageType"/>
					<input type="hidden" id="hidden_type" name="type"/>
					<input type="hidden" id="hidden_status" name="status" />
					<input type="hidden" id="hidden_content" name="content" />
					<p><label>消息类型:</label> 
						<select id="search_select_messageType" class="easyui-combobox" style="width:160px" editable="false" class="easyui-validatebox" required="true" data-options="panelHeight:'auto'">
						    <option value="MSAA">短信</option>
						    <option value="MSAB">邮件</option>
						    <option value="MSAC">站内消息</option>
						</select>
					</p>
					<p><label>模板名称:</label> 
						<input type="text" name="name"   
							class="easyui-validatebox" required="true" value="${ErpMsgTemplate.name}"
					 /></p><br/>
					<p>
						<label>模板类型:</label> 
						<select id="search_select_templateType" class="easyui-combobox" style="width:160px" editable="false" class="easyui-validatebox" required="true" data-options="panelHeight:'auto'">
						    <option value="MSDA">测试模板</option>
						</select>
					</p>
					<p>
						<label>是否启用:</label> 
						<select id="search_select_status" class="easyui-combobox" editable="false" class="easyui-validatebox" required="true" data-options="panelHeight:'auto'">
						    <option value="MSBA">是</option>
						    <option value="MSBB">否</option>
						</select>
					</p><br/><br/>
					<p><label>标题:</label> 
						<input type="text" name="title"  style="width:510px;" value="${ErpMsgTemplate.title}"
					 /></p><br/><br/>
					<p><label>信息头:</label> 
						<input type="text" name="header" style="width:510px;" value="${ErpMsgTemplate.header}"
					 /></p><br/><br/>
					<p><label>正文:</label> 
						<!-- 加载编辑器的容器 -->
						<script id="container" type="text/plain">${ErpMsgTemplate.content}</script>
					</p><br/><br/>
					<p><label>结尾:</label> 
						<input type="text" name="end"   style="width:510px;" value="${ErpMsgTemplate.end}"
					 /></p><br/><br/>
					<p>
						<label>备注:</label> 
						<textarea  name="remark" class="textarea-bigtext" style="width:825px;height:100px">${ErpMsgTemplate.remark}</textarea>
					</p>
		
				</div>
			</form>
		</div>
	</div>
	<!-- 配置文件 -->
    <script type="text/javascript" src="${basePath}/static/ueditor/ueditor.config.js"></script>
    <!-- 编辑器源码文件 -->
    <script type="text/javascript" src="${basePath}/static/ueditor/ueditor.all.js"></script>
    <!-- 实例化编辑器 -->
    <script type="text/javascript">
    	 var editor = UE.getEditor('container',{
    	 	toolbars: [
        		[
	        		'anchor', //锚点
			        'undo', //撤销
			        'redo', //重做
			        'bold', //加粗
			        'indent', //首行缩进
			        'italic', //斜体
			        'formatmatch', //格式刷
			        'pasteplain', //纯文本粘贴模式
        			'selectall', //全选
        			'horizontal', //分隔线
			        'removeformat', //清除格式
			        'time', //时间
			        'date', //日期
			        'unlink', //取消链接
			        'fontfamily', //字体
        			'fontsize', //字号
        			'link', //超链接
        			'spechars', //特殊字符
					'searchreplace', //查询替换
					'help', //帮助
					'justifyleft', //居左对齐
			        'justifyright', //居右对齐
			        'justifycenter', //居中对齐
			        'justifyjustify', //两端对齐
			        'forecolor', //字体颜色
			        'backcolor', //背景色
					'attachment', //附件
        			'imagecenter', //居中
					'touppercase', //字母大写
        			'tolowercase', //字母小写
        			'background', //背景
 					'drafts' // 从草稿
		        ]
    		],
    	 	initialFrameHeight:200,initialFrameWidth:825 
    	 });  
         //editor.render('container'); 
    </script>
</body>
</html>