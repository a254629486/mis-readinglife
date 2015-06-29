<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>    列表</title>
   <#include "../framework/comm/meta.ftl" />
</head>
<body>
		<div class="part">
				 <form id="erp_msg_site_news_dg_search">
					 		<p><label>:</label><input type="text" id="search_newsId"  
					 			/></p>
					 		<p><label>:</label><input type="text" id="search_receiveStaffId"  
					 			/></p>
							<div class="part-buttons"> 
					 			<a href="#" onclick="datagridSearch('erp_msg_site_news_dg','erp_msg_site_news_dg_search')" class="easyui-linkbutton"  >查询</a>
								<a href="#" onclick="resetPage('erp_msg_site_news_dg_search')" class="easyui-linkbutton"  >重置</a>
							</div>
				</form>
		</div>
	<table id="erp_msg_site_news_dg" title="    列表" class="easyui-datagrid"
		style="height: 500px;width: auto;" url="./searchList"
		toolbar="#erp_msg_site_news_toolbar" pagination="true" rownumbers="false"
		fitColumns="true" singleSelect="false" selectOnCheck="true" checkOnSelect="true"
		>
		<thead>
			<tr>
							<th data-options="field:'newsId',checkbox:true"></th>		 
				    	  <th field="receiveStaffId"  
				    	  			sortable="true" 
				    	  		 ></th>
				    	  <th field="title"  
				    	  			sortable="true" 
				    	  		 ></th>
				    	  <th field="type"  
				    	  			sortable="true" 
				    	  		 ></th>
				    	  <th field="content"  
				    	  			sortable="true" 
				    	  		 ></th>
				    	  <th field="url"  
				    	  			sortable="true" 
				    	  		 ></th>
				    	  <th field="status"  
				    	  			sortable="true" 
				    	  		 ></th>
				    	  <th field="createStaffId"  
				    	  			sortable="true" 
				    	  		 >创建人</th>
				    	  <th field="createDate"  
				    	  			sortable="true" 
				    	  		 >创建时间</th>
				    	  <th field="updateStaffId"  
				    	  			sortable="true" 
				    	  		 >更新人</th>
				    	  <th field="updateDate"  
				    	  			sortable="true" 
				    	  		 >更新时间</th>
				    	  <th field="remark"  
				    	  			sortable="true" 
				    	  		 >备注</th>
			</tr>
		</thead>
	</table>
	<div id="erp_msg_site_news_toolbar">
	  <div  id="menubar"  style="margin-bottom:5px">
			<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-add" plain="true" onclick="newinsert('dlg','fm','新建')">新建</a> 
			<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-edit" plain="true" onclick="newedit('dlg','fm','erp_msg_site_news_dg','修改')">修改</a> 
			<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-remove" plain="true" onclick="newremove('erp_msg_site_news_dg','./remove','newsId')">删除</a>
			<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-edit" plain="true" onclick="newshow('showdlg','showfm','erp_msg_site_news_dg','查看')">查看</a> 
			</div>
	</div>
	
	<div id="dlg" class="easyui-dialog"
		style="width: 500px; height: auto; padding: 10px 20px" closed="true" >
		<div class="part-toolbar">
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true"
			 onclick="newsave('dlg','fm','erp_msg_site_news_dg','./save')">保存</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"  data-options="iconCls:'icon-save',plain:true"
			  onclick="javascript:$('#dlg').dialog('close')">取消</a>
		</div>
		<div class="part">
		<div class="part-title">    详细</div>
		<form id="fm" method="post" novalidate>
							<input type="hidden" name="newsId" /> 
									<p><label>:</label> 
										<input type="text" name="receiveStaffId"   
											class="easyui-validatebox"
											 validType=""
									 /></p>
									<p><label>:</label> 
										<input type="text" name="title"   
											class="easyui-validatebox"
											 validType=""
									 /></p>
									<p><label>:</label> 
										<input type="text" name="type"   
											class="easyui-validatebox"
											 validType=""
									 /></p>
									<p><label>:</label> 
										<input type="text" name="content"   
											class="easyui-validatebox"
											 validType=""
									 /></p>
									<p><label>:</label> 
										<input type="text" name="url"   
											class="easyui-validatebox"
											 validType=""
									 /></p>
									<p><label>:</label> 
										<input type="text" name="status"   
											class="easyui-validatebox"
											 validType=""
									 /></p>
									<p><label>创建人:</label> 
										<input type="text" name="createStaffId"   
											class="easyui-validatebox"
											 validType=""
									 /></p>
									<p><label>创建时间:</label> 
										<input type="text" name="createDate"   
							 				class="easyui-datebox easyui-validatebox"
											 validType=""
									 /></p>
									<p><label>更新人:</label> 
										<input type="text" name="updateStaffId"   
											class="easyui-validatebox"
											 validType=""
									 /></p>
									<p><label>更新时间:</label> 
										<input type="text" name="updateDate"   
							 				class="easyui-datebox easyui-validatebox"
											 validType=""
									 /></p>
									<p><label>备注:</label> 
										<input type="text" name="remark"   
											class="easyui-validatebox"
											 validType=""
									 /></p>
		</form>
		</div>
	</div>
	
	
	<div id="showdlg" class="easyui-dialog"
		style="width: 500px; height: auto; padding: 10px 20px" closed="true" >
		<div class="part-toolbar">
		 	 <a href="javascript:void(0)" class="easyui-linkbutton"  data-options="iconCls:'icon-save',plain:true"
				 onclick="javascript:$('#showdlg').dialog('close')">关闭</a>
		</div>
		<div class="part">
		<div class="part-title">    详细</div>
		<form id="showfm" method="post" novalidate>
							<input type="hidden" name="newsId" /> 
									<p><label>:</label> 
										<input  type="text" name="receiveStaffId"  readonly="readonly" style="border: 0px"/></p>
									<p><label>:</label> 
										<input  type="text" name="title"  readonly="readonly" style="border: 0px"/></p>
									<p><label>:</label> 
										<input  type="text" name="type"  readonly="readonly" style="border: 0px"/></p>
									<p><label>:</label> 
										<input  type="text" name="content"  readonly="readonly" style="border: 0px"/></p>
									<p><label>:</label> 
										<input  type="text" name="url"  readonly="readonly" style="border: 0px"/></p>
									<p><label>:</label> 
										<input  type="text" name="status"  readonly="readonly" style="border: 0px"/></p>
									<p><label>创建人:</label> 
										<input  type="text" name="createStaffId"  readonly="readonly" style="border: 0px"/></p>
									<p><label>创建时间:</label> 
										<input  type="text" name="createDate"  readonly="readonly" style="border: 0px"/></p>
									<p><label>更新人:</label> 
										<input  type="text" name="updateStaffId"  readonly="readonly" style="border: 0px"/></p>
									<p><label>更新时间:</label> 
										<input  type="text" name="updateDate"  readonly="readonly" style="border: 0px"/></p>
									<p><label>备注:</label> 
										<input  type="text" name="remark"  readonly="readonly" style="border: 0px"/></p>
			
		</form>
		</div>
	</div>
</body>
</html>