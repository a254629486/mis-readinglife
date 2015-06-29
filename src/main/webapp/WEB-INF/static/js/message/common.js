/*****************formatter******************/
function message_type_formatter(val){
	var str = "";
	if(val == "MSAA"){
		str = "短信";
	}
	if(val == "MSAB"){
		str = "邮件";
	}
	if(val == "MSAC"){
		str = "站内消息";
	}
	return str;
}
function template_type_formatter(val){
	var str = "";
	if(val == "MSDA"){
		str = "测试模板";
	}
	return str;
}
function status_formmater(val){
	var str = "";
	if(val == "MSBA"){
		str = "是";
	}
	if(val == "MSBB"){
		str = "否";
	}
	return str;
}
function send_status_formatter(val){
	var str = "";
	if(val == "MSEA"){
		str = "待发送";
	}
	if(val == "MSEB"){
		str = "发送中";
	}
	if(val == "MSEC"){
		str = "发送成功";
	}
	if(val == "MSED"){
		str = "发送失败";
	}
	return str;
}
function str_formatter(val){
	if(val && val.length > 0){
		var str = val;
		if(val.length > 11){
			str = val.substring(0,10)+"...";	
		}
		return "<span title='"+val+"'>"+str+"</span>";
	}else{
		return "";
	}
}
function content_formatter(val){
	var str2 = "";
	var str = $('<div />').html(val).text();
	if(str.length > 11){
		str2 = str.substring(0,10)+"...";	
	}
	return "<span title='"+str+"' style='width:auto'>"+str2+"</span>";
}