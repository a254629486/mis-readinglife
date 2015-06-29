$(function() {

	var coselp = $("select[class='easyui-combobox-select']");
	if (coselp == null || coselp == '' || coselp.length == 0) {
		return;
	}

	var cosel = coselp;
	var param = cosel.attr("param");//必填属性  --服务器url参数
	var url = cosel.attr("url");//必填属性 --服务器地址
	var id = cosel.attr("id");//必填属性 --id
	
	if (url == null || url == ''||id == null || id == '') {
		return;
	}
	
	var jsondata = '';//服务器数据
	/**
	 * 获取服务器数据
	 */
	$.getJSON(url, param, function(data) {
		//jsondata = data;
		$.each(data["" + id], function(index, item) {
			if (index == 0) {
				return true;
			}
			// alert(item.key+'--'+item.value);
			$("#" + id).append(
					'<option value="' + item.key + '">' + item.value
							+ '</option>');
		});
		alert(JSON.stringify(data));
		$("#"+id).attr("data",JSON.stringify(data));
		subselected(id);
	});

	/**
	 * 递归遍历select
	 */
	function subselected(pid){
		var subselect = $("#"+pid).attr("subselect");
		if (subselect != null && subselect != '' && subselect != undefined) {
			$("#" + pid).change(function() {
				chfun(pid, subselect,JSON.parse($("#"+pid).attr("data")));
			});
			subselected(subselect);
		}
	}
	/**
	 * 为子赋值
	 */
	function chfun(pid, sid,data) {
		$("#" + sid).empty();
		$.each(data["" + pid], function(index, item) {
			if (index == 0) {
				return true;
			}
			if (item["" + sid] == null || item["" + sid] == '') {
				return true;
			}
			// alert($("#leftselect").val()+'---'+item.key);
			if ($("#" + pid).val() != item.key) {
				return true;
			}
			$.each(item["" + sid], function(index, item) {
				// alert(item.key+'--'+item.value);
				$("#" + sid).append(
						'<option value="' + item.key + '">' + item.value
								+ '</option>');
			});
			alert('{\"'+sid+'\":'+JSON.stringify(item["" + sid])+'}');
			$("#"+sid).attr("data",'{\"'+sid+'\":'+JSON.stringify(item["" + sid])+'}');
		});
	}

});
