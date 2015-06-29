function newinsert(dlg,fm,title) {
	$('#'+dlg).dialog('open').dialog('setTitle', title);
	if(arguments.length>3){
		$('#'+fm).form('clear'); 
		$('#'+fm).form('load', arguments[3]); 
	}else{
		$('#'+fm).form('clear'); 
	}
}
function newshow(dlg,fm,table,title) {
	 var rows = $('#'+table).datagrid('getSelections');
	 if(rows==null||rows==''||rows.length==0){
		 jQuery.messager.alert('提示:','请选择操作记录!');   
		 return ; 
	 }
	 if(rows.length>1){
		 jQuery.messager.alert('提示:','只能操作一条记录!');   
		 return ;
	 }
	var row = $('#'+table).datagrid('getSelected');
	if (row) {
		$('#'+dlg).dialog('open').dialog('setTitle', title);
		$('#'+fm).form('load', row); 
	}
}
function newedit(dlg,fm,table,title) {
	 var rows = $('#'+table).datagrid('getSelections');
	 if(rows==null||rows==''||rows.length==0){
		 jQuery.messager.alert('提示:','请选择操作记录!');   
		 return ; 
	 }
	 if(rows.length>1){
		 jQuery.messager.alert('提示:','只能操作一条记录!');   
		 return ;
	 }
	var row = $('#'+table).datagrid('getSelected');
	if (row) {
		$('#'+dlg).dialog('open').dialog('setTitle', title);
		$('#'+fm).form('load', row); 
	}
}
function newsave(dlg,fm,table,url) {
	var args = arguments;
	$('#'+fm).form('submit', {
		url : url,
		onSubmit : function() {
			return $(this).form('validate');
		},
		success : function(msg) {
			//alert(results);
		/*	var result = eval('(' + results + ')');
			if (result.success) {
				$('#'+table).datagrid('reload');
				$.messager.show({
					title : '提示',
					msg : result.msg
				});
				$('#'+dlg).dialog('close'); 
			} else {
				$('#'+dlg).dialog('close'); // close the dialog
				$('#'+table).datagrid('reload'); // reload the user data
			}*/
			if(args.length>5&&args[5]=='tree'){
				doMessage(msg,table,dlg,args[4],args[5]);
			}else if(args.length>4){
				doMessage(msg,table,dlg,args[4]);
			}else{
				doMessage(msg,table,dlg);
			}
			
		}
	});
}
 
function newremove(table,url,keyid) {
	var row = $('#'+table).datagrid('getSelected');
	 if(row==null||row==''||row.length==0){
		 jQuery.messager.alert('提示:','请选择操作记录!');   
		 return ; 
	 }
	if (row) {
		$.messager.confirm('提示', '是否确定删除?', function(r) {
			if (r) {
				 var rows = $('#'+table).datagrid('getSelections');
				 var ids = '';
					for ( var i = 0; i < rows.length; i++) {
						var row = rows[i];
						if(i!=0){ids+=',';}
					 	ids += row[keyid];
					}
				deleterow(table,url,ids,null);
			}
		});
	}
}

function treegridremove(table,url,keyid) {
	var args = arguments;
	var row = $('#'+table).treegrid('getSelected');
	 if(row==null||row==''||row.length==0){
		 jQuery.messager.alert('提示:','请选择操作记录!');   
		 return ; 
	 }
	if (row) {
		$.messager.confirm('提示', '是否确定删除?', function(r) {
			if (r) {
				 var rows = $('#'+table).treegrid('getSelections');
				 var ids = '';
					for ( var i = 0; i < rows.length; i++) {
						var row = rows[i];
						if(i!=0){ids+=',';}
					 	ids += row[keyid];
					}
					if(args.length>3){
						deleterow(table,url,ids,'tree',args[3]);
					}else{
						deleterow(table,url,ids,'tree');
					}
			
			}
		});
	}
}

function deleterow(table,url,ids,type){
	var args = arguments;
	$.ajax({
		type : "POST",
		url : url,
		data : "pkId=" + ids,
		success : function(msg) {
			doMessage(msg,table,null,null,type);
			if(args.length>3){ 
				eval( "var _function = " + args[3] );
				_function();
			}
		}
	});
}
/*function doMessage(msg,table,dlg){
	var result = eval('(' + msg + ')');
	//alert(result.success);
	if (result.success=='true'||result.success==true) {
		//alert(1==true);
		//reloadPage();
		$('#'+table).datagrid('reload'); 
		$.messager.show({ 
			title : '提示',
			msg : result.msg
		});
		if(dlg!=null&&''!=dlg){
			$('#'+dlg).dialog('close'); 
		}
	} else {jQuery.messager.alert('提示:', result.msg);}   
		 
}*/
function formatTodo(value, rowData, rowIndex) {
	return '<a href="#' + rowData['tId'] + '">修改</a>&nbsp;|&nbsp;<a onclick="" href="#'
			+ rowData['tId'] + '">删除</a>';
}

function resetPage(fm) {
	document.getElementById(fm).reset();
	window.location.reload();
}

function reloadPage() {
	window.location.reload();
}


function showview(table,dlg,fm,title){
//	alert(table+dlg+fm);
	fm = 'fm';
	var b = $("#" + fm + " input");
	alert(b.length);
	for ( var i = 0; i < b.length; i++) {
		//b[i].attr("readonly","readonly");
		b[i].css({ border: 0 }); 
	}
	var row = $('#'+table).datagrid('getSelected');
	if (row) {
		$('#'+dlg).dialog('open').dialog('setTitle', title);
		$('#'+fm).form('load', row); 
	}
	
}

function datagridSearch(table,sfm){
	 if(arguments.length>2){
		 $("#"+table).datagrid('options').queryParams = eval('('+ arguments[2] + ')');
	 }else{
		 $("#"+table).datagrid('options').queryParams = paramValue(sfm);
	 }
	   
	$('#'+table).datagrid('loadData', {
		total : 0,
		rows : []
	});
	$('#'+table).datagrid({
		queryParams: $("#"+table).datagrid('options').queryParams, 
		pagination : true
	});
}

function initTable(table,sfm) {
	var pg = $("#" + table).datagrid("getPager");
	if (pg) {
		$(pg).pagination(
				{

					pageSize : 10,// 每页显示的记录条数，默认为10
					// pageList: [5,10,15],//可以设置每页记录条数的列表

					onBeforeRefresh : function() {
					},
					onRefresh : function(pageNumber, pageSize) {
					},
					onChangePageSize : function() {
					},
					onSelectPage : function(pageNumber, pageSize) {

						$('#' + table).datagrid('loadData', {
							total : 0,
							rows : []
						});

						$('#' + table).datagrid(
								{
									queryParams : $("#" + table).datagrid(
											'options').queryParams,
								});

					}

				});
	}

	datagridSearch(table,sfm);
}

function paramValue(sfm) {
	var b = $("#" + sfm + " input");
	var param = '';//alert(b.length);
	for ( var i = 0; i < b.length; i++) {
		var bname = b[i].id;
		if (bname == null || bname == '') {
			bname = b[i].name;
		}
		if (bname != null && bname != '') {
			var sb = bname.split('search_');
			if (sb.length <= 1) {
				return;
			}
			if(b[i].style.display == 'none'){
					try{
						var arr = b[i].parentNode.childNodes[2].childNodes[2];
						if (arr.type == 'hidden'){
							param += ',\"' + sb[1] + '\":\"' +arr.value+'\"';
						}
					}catch(e){}
			}else if (b[i].type == 'text' || b[i].type == 'hidden') {
				param += ',\"' + sb[1] + '\":\"' + b[i].value+'\"';
			}else if (b[i].type == 'radio' || b[i].type == 'radio') {
				if (b[i].checked)
					param += ',\"' + sb[1] + '\":\"' + b[i].value+'\"';
			}
		}

	}
	var s = $("#" + sfm + ">select");
	for ( var i = 0; i < s.length; i++) {
		var sname = s[i].id;
		if (sname == null || sname == '') {
			sname = s[i].name;
		}
		if (sname != null && sname != '') {
			var ssb = sname.split('search_');
			if (ssb.length > 1) {
				param += ',\"' + ssb[1] + '\":\"' + s[i].value+'\"';
			}
		}
	}
	
	return eval('({'+ param.substring(1, param.length) + '})');
}

/*
function paramValue(sfm){
	var values;
	values = $("#" + sfm).serializeArray();
	if(!values){
		 alert("搜索表单不存在!");
		 return ;
	}
	//var x=document.getElementById(sfm).serializeArray();
	
	alert($("#" + sfm).serializeArray());
	
//	alert(convertArray(x));
	  $.each(x, function(i, field){
		  alert(field.name + ":" + field.value + " "); 
	  });
	
	var param = '';
	for (var i = 0; i < values.length; i++) {
		var obj = values[i];
		var bname = obj.id;
		if (bname == null || bname == '') {
			bname = obj.name;
		}
		if (bname != null && bname != '' && bname!=undefined) {
			var sb = bname.split('search_');
			if (sb.length <= 1) {
				continue;
			}
			if (obj.type == 'text' || obj.type == 'hidden') {
				param += ',\"' + bname + '\":\"' + b[i].value+'\"';
			}
			if (obj.type == 'radio' || obj.type == 'radio') {
				if (obj.checked)
					param += ',\"' + bname + '\":\"' + obj.value+'\"';
			}
		}
	}
	// alert(param);
	return eval('({'+ param.substring(1, param.length) + '})');;
}

function convertArray(o) {
	var v = {};
	for ( var i in o) {
		if (typeof (v[o[i].name]) == 'undefined')
			v[o[i].name] = o[i].value;
		else
			v[o[i].name] += "," + o[i].value;
	}
	return v;
}*/
