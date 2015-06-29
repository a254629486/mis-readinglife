<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>岗位列表</title>
<#include "../../framework/comm/meta.ftl">
<link rel="stylesheet" type="text/css" href="${basePath}/static/css/treegrid.css">
</head>
<body onended="initTable('sys_positi_dg','sys_positi_dg_search');">
		<table id="sys_positi_dg" class="easyui-treegrid"
			data-options="
					    rownumbers: false,
					    animate: false,
					    collapsible: true,
					    lines:true,
					    fitColumns: true,
					    url: './searchList', 
					    idField: 'positiId',
					    treeField: 'name', 
					    onClickRow:function(node){
					    	//alert(JSON.stringify(node));
					    	var pnode = $(this).treegrid('find',node._parentId);
						 	window.parent.treegridCallBackPositi(node,pnode);
					    },
					    loadFilter:_loadFilter
					    ">
			<thead>
				<tr>
					<th data-options="field:'name',width:180">系统岗位</th>
				</tr>
			</thead>
		</table>
	
	<script type="text/javascript">
		function _loadFilter(data,parentId){
	    	//alert(JSON.stringify(data));
	    	var str = JSON.stringify(data);
	    	str = str.replace(/positiPid/g,'_parentId');
	    	var jsondata = eval('(' + str + ')');
	    	 var rows = jsondata.rows;
	    	for(var i=0;i<rows.length;i++){
	    		var row = rows[i];
	    		  	row['iconCls'] = 'icon-menu_position';
		      }
	    	//alert(JSON.stringify(jsondata));
	    	return jsondata;
		}
	
	</script>
</body>
</html>