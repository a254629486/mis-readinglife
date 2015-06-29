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
			style="width: 300px; height: auto;" singleSelect="false"
			selectOnCheck="true" checkOnSelect="true"
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
					    	if(node.type!='positi'){
					    		$(this).treegrid('unselect',node.departId);
					    	}
					    	var node = $('#sys_company_depart_dg').treegrid('getSelections');
					    			//var pnode = $(this).treegrid('find',node._parentId);
						 	window.parent.treegridCallBackPositi(node);
					    },
					    loadFilter:_loadFilter,
					    onLoadSuccess:function(row, data){
					    	fmtSelect();
					    }
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
	    		} else if(row.type=='positi'){//岗位
	    		 	row['iconCls'] = 'icon-menu_position';
	    		} 
		      }
	    	//alert(JSON.stringify(jsondata));
	    	return jsondata;
		}
		
		
		function fmtSelect(){
			/**
			$.ajax({
				   type: "GET",
				   url: '${basePath}/sysPersonStaffController/searchStaffPositiList',
				   success: function(msg){
					 var  list =  eval('(' + msg + ')');
				     //  alert( "Data Saved: " + list.rows[0].departId );
				       for(var i=0;i<list.rows.length;i++){
				      		$("#sys_company_depart_dg").treegrid('select',list.rows[i].positiId);
				       }
				   }
				});
				**/
				var cc = '${positiId}';
				if(cc==null||cc==''){
					return ;
				}
				var positiIds = cc.split(',');
				 for(var i=0;i<positiIds.length;i++){
			      		$("#sys_company_depart_dg").treegrid('select',positiIds[i]);
			       }
		}
	
	</script>
</body>
</html>