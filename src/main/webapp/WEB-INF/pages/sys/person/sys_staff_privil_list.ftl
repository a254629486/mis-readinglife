<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>定制权限列表</title>
<#include "../../framework/comm/meta.ftl">
</head>
<body  style="padding-left:  10px;">
	<div style="margin:10px 0;">
		<a href="#" class="easyui-linkbutton" onclick="javascript:window.parent.saveprivil(getChecked())">保存</a> 
	</div>
	<div>
	<div style="float: left;">
           		<fieldset style="width:auto;border:solid 1px #aaa position:relative;">
      			  <legend>已有岗位角色权限</legend>
      			  
				<ul id="tt" class="easyui-tree"
					data-options="url:'${basePath}/sysStaffCsmrolController/searchStaffHavePrivilList?positiId=${positiId }',
									method:'get',
									animate:true,
									checkbox:false,
									loadFilter:_loadFilter"></ul>
							
				</fieldset>
	</div>
	<div style="float: left;">
			<fieldset style="width:auto;border:solid 1px #aaa;margin-left:10px;position:relative;">
		      <legend>定制权限</legend>
					<ul id="tt2" class="easyui-tree"
						data-options="url:'${basePath}/sysStaffCsmrolController/searchStaffPrivilList?staffId=${staffId }',
										method:'get',
										animate:true,
										checkbox:true,
										loadFilter:_loadFilter"></ul>
			 </fieldset>
	</div>
	</div>

	<script type="text/javascript">
				 
				 function _loadFilter(data,parent){
					 var rows = data.rows;
					 //alert(JSON.stringify(rows));
					 var json = '[{"id":"1000","text":"系统根菜单","iconCls":"icon-root","children":['+listtotree(rows,'1000')+']}]';
					  json = json.replace(/'/g,'"');
					 // alert(json);
					  return  eval('(' + json + ')');
				}
				 
				 function listtotree(data,pid){
					 var treejson='';
					 for(var i=0;i<data.length;i++){
						 if(data[i].privilPid==pid){
							 if(treejson!=''){treejson+=',';}
							 treejson+="{'id':'"+data[i].privilId+"','text':'"+data[i].name+"'"+changeicon(data[i]);
							 var children = listtotree(data,data[i].privilId);
							 if(children!=''){
								 treejson+=",'children':["+children+"]";
							 }else if(data[i].staffId!=null&&data[i].staffId!=''){
								 treejson+=",'checked':true";
							 }
							 treejson+="}";
						 }
					  }
					 return treejson;
				 }
				 
				 function changeicon(row){
						if(row.type=='1'){//根节点
			    		  	return ",'iconCls':'icon-root'";
			    		}else if(row.type=='2'){//导航
			    			return ",'iconCls':'icon-menu_folderClosed'";
			    		}else if(row.type=='3'){//菜单
			    			return ",'iconCls':'icon-menu'";
			    		}else if(row.type=='4'||row.type=='6'||row.type=='7'
			    				||row.type=='10'||row.type=='9'||row.type=='8'
			    				||row.type=='11'){//操作元素
			    			return ",'iconCls':'icon-operate'";
			    		} 
				        
				        if(row.status=='2'){//未启用
				        	return ",'iconCls':'icon-leaf'";
				        }
				        return '';
				 }
				 function getChecked(){
					 var nodes = $('#tt2').tree('getChecked',['checked','indeterminate']);
					 var s = '';
					 for(var i=0; i<nodes.length; i++){
						 if (s != '') s += ',';
						 s += nodes[i].id;
					 }
					 return s;
				}
				 
			</script>
</body>
</html>