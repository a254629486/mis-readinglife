<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>选择权限树</title>
<#include "../../framework/comm/meta.ftl">
<link rel="stylesheet" type="text/css" href="${basePath}/static/css/treegrid.css">
</head>
<body  onended="initTable('sys_privil_dg_select','sys_privil_dg_search');">
<table id="sys_privil_dg_select" class="easyui-treegrid"  
						data-options="
								    iconCls: 'tree-folder',
								    rownumbers: false,
								    animate: false,
								    collapsible: true,
								    lines:true,
								    fitColumns: true,
								    url: './searchList',
								    method: 'get',
								    idField: 'privilId',
								    treeField: 'name',
								    showFooter: true,
								    onClickRow:function(node){
								    	//alert(JSON.stringify(node));
								    	window.parent.treegridCallBackPrivil(node);
								    },  
								    loadFilter:_loadFilter
								    ">
							<thead>
								<tr>
									<th data-options="field:'name',width:180">资源名称</th>
								</tr>
							</thead>
						</table>

</body>
</html>
<script type="text/javascript">
function _loadFilter(data,parentId){
	//alert(JSON.stringify(data));
	var str = JSON.stringify(data);
	str = str.replace(/privilPid/g,'_parentId');
	var jsondata = eval('(' + str + ')');
	var rows = jsondata.rows;
	for(var i=0;i<rows.length;i++){
		var row = rows[i];
		if(row.type=='1'){//根节点
		  	row['iconCls'] = 'icon-root';
		}else if(row.type=='2'){//导航
		 	row['iconCls'] = 'icon-menu_folderClosed';
		}else if(row.type=='3'){//菜单
		 	row['iconCls'] = 'icon-menu';
		}else if(row.type=='4'){//操作元素
		 	row['iconCls'] = 'icon-operate';
		}
        
        if(row.status=='2'){//未启用
        	row['iconCls'] = 'icon-leaf';
        }
        
      }
	//alert(JSON.stringify(jsondata));
	return jsondata;
}

</script>