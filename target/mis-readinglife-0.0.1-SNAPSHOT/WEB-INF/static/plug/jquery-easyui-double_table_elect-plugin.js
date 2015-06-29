/**
 * 移除一行
 * @param editIndex
 * @param table
 */
function removeit(editIndex,table) { 
	if (editIndex == undefined) {
		return;
	}
	$('#'+table).datagrid('cancelEdit', editIndex).datagrid('deleteRow',
			editIndex);
}

/**
 * 移动数据
 * @param val 是否全部移动 all 是 否则 否
 * @param fromt 源 表id
 * @param tot 目标 表id
 */
function tableDateMove(val,fromt,tot) {
	if(val=='all'){
		 $('#'+tot).datagrid('selectAll');
	}
	var rows = $('#'+tot).datagrid('getSelections');
	for ( var i = 0; i < rows.length; i++) {
		var row = rows[i];
		$('#'+fromt).datagrid('appendRow',row);
		removeit($('#'+tot).datagrid('getRowIndex',row),tot);
	}
}

/**
 * 获取数据
 * @param table
 * @param item
 * @returns {String}
 */
function getTableItemDate(table,item){
	 $('#'+table).datagrid('selectAll');
	 var rows = $('#'+table).datagrid('getSelections');
	 var ids = '';
		for ( var i = 0; i < rows.length; i++) {
			var row = rows[i];
			if(i!=0){ids+=',';}
		 	ids += row[item];
		}
	return ids;
}