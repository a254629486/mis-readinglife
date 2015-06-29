<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>公司组织机构列表</title>
	<#include "../../framework/comm/meta.ftl">
	<link rel="stylesheet" type="text/css" href="${basePath}/static/css/treegrid.css">
</head>
<body
	onended="initTable('sys_company_depart_dg','sys_company_depart_dg_search');">
	
		<table id="sys_company_depart_dg" class="easyui-treegrid"
			style="width: 300px; height: auto;"
			data-options="
					    rownumbers: false,
					    animate: false,
					    collapsible: true,
					    lines:true,
					    fitColumns: true,
					    url: './searchList', 
					    idField: 'departId',
					    treeField: 'name', 
					    onClickRow:function(node){
					    	//alert(JSON.stringify(node));
					    	var pnode = $(this).treegrid('find',node._parentId);
					    	var cnode = $(this).treegrid('getChildren',node.departId);
					    	
					    	<#if method?? > 
					    		window.parent.${method}(node,pnode);
					    	<#else>	
					    		window.parent.treegridCallBackCompanyDepart(node,pnode,cnode);
					    	</#if>
					    },
					    loadFilter:_loadFilter
					    ">
			<thead>
				<tr>
					<th data-options="field:'name',width:180">公司组织机构列表</th>
				</tr>
			</thead>
		</table>
	
	<script type="text/javascript">
		function _loadFilter(data,parentId){
	    	//alert(JSON.stringify(data));
	    	var str = JSON.stringify(data);
	    	str = str.replace(/departPid/g,'_parentId');
	    	var jsondata = eval('(' + str + ')');
	    	 var rows = jsondata.rows;
	    	for(var i=0;i<rows.length;i++){
	    		var row = rows[i];
	    		if(row.type=='depart'){//部门
	    		  	row['iconCls'] = 'icon-menu_department';
	    		}else if(row.type=='company'){//公司
	    		 	row['iconCls'] = 'icon-menu_folderClosed';
	    		} 
		      }
	    	//alert(JSON.stringify(jsondata));
	    	return jsondata;
		}
	
	</script>
</body>
</html>