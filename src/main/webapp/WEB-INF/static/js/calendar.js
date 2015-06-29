var YEAR = "";
var MONTH = "";
var DAY = "";
function creatcalendar(dlg, fm, table, url) {
	if(!getLen('startyear')&&!getLen('endyear')){
		alert('输入值必须为年份且每次生成最多为10年！');
		return false;
	}
	var args = arguments;
	$('#' + fm).form('submit', {
		url : url,
		onSubmit : function() {
			return $(this).form('validate');
		},
		success : function(data) {
			var result = eval('(' + data + ')');
			if (result.success) {
				$.messager.show({
					title : '提示',
					msg : result.msg
				});
				$('#'+dlg).dialog('close'); 
			} else {
				$.messager.show({
					title : '提示',
					msg : "保存失败"
				});
				$('#'+dlg).dialog('close'); // close the dialog
			}
			showCalendar();
		}
	});
	$("#dlg").dialog("close");
}

function showCalendar() {
	var url = "./show";
	$.ajax({
	   type: "GET",
	   url: url,
	   async:false,
	   success: function(data){
		   ff(data);
	   }		
	});
};

function ff(data){
	var lihtml = "";
	for ( var i = 0; i < data.length; i++) {
		lihtml = lihtml
				+ "<li class='"
				+ data[i].cssclass
				+ "' onclick='newinserts(\"addholiday\",\"newfm\",\"添加假期\","+ data[i].year + ","+ data[i].month + ","+ data[i].day + ",\""+data[i].holidayName+"\","+data[i].holidayFlag+")'><div style='height:30px;line-height:30px;width:99%;;'>" + data[i].day + "</div>"
				+ "<div style='height:20px;width:99%;font-weight:normal;font-size:12px;'>" + (data[i].holidayName==null ? "" : data[i].holidayName)
				+ "</div>" + "</li>";
	}
	$("#day").empty();
	$("#day").append(lihtml);
	var $lis = $("#day").find("li");
	for ( var j = 0; j < $lis.length; j++) {
		var $li = $($lis[j]);
		console.log($li.attr("class").indexOf("today"));
		if ($li.attr("class").indexOf("today") == -1) {
			$li.on({
				mouseover : function() {
					$(this).css({
						"background-color" : "#CCFF99"
					});
				},
				mouseout : function() {
					$(this).css({
						"background-color" : "#fff"
					});
				}
			});
		}
		;
	}
	$("#selectyear").combobox({
		url : "./getYearList",
		valueField : 'year',
		textField : 'year',
	});
	$("#selectmonth").combobox({
		url : "./searchMonthList",
		valueField : 'month',
		textField : 'month',
	});
	
}
function init(){
	$("#selectyear").combobox({
		url : "./getYearList",
		valueField : 'year',
		textField : 'year',
		editable:false,
		onSelect : function(rec) {
			var selectyear = $("#selectyear").combobox("getValue");
			var selectmonth = $("#selectmonth").combobox("getValue");
			// alert(selectyear+","+selectmonth);
			getyearmonth(selectyear, selectmonth);
			initHoliday();
		},
		onLoadSuccess:initHoliday
	});
	$("#selectmonth").combobox({
		url : "./searchMonthList",
		valueField : 'month',
		textField : 'month',
		editable:false,
		onSelect : function(rec) {
			var selectyear = $("#selectyear").combobox("getValue");
			var selectmonth = $("#selectmonth").combobox("getValue");
			// alert(selectyear+","+selectmonth);
			getyearmonth(selectyear, selectmonth);
		}
	});

	
}

function initHoliday(){
	$("#selectholiday").combobox({
		url:"./selectholidayList?selectyear=" + $("#selectyear").combobox("getValue"),
		valueField : 'holidayName',
		textField : 'holidayName',
		editable:false,
		onSelect:function(rec){
			var selectyear = $("#selectyear").combobox("getValue");
			var selectholiday=$("#selectholiday").combobox("getValue");
			getyearholiday(selectyear,selectholiday);
		}
	});				

}



$(document).ready(function() {
	init();
	showCalendar();
	
});
//$(function() {});

function getyearmonth(selectyear, selectmonth) {
	// alert(selectyear+","+selectmonth);
	var url = "./getyearmonth?selectyear=" + selectyear + "&selectmonth="
			+ selectmonth;
	$.get(
					url,
					function(data) {
						var lihtml = "";
						for ( var i = 0; i < data.length; i++) {
							lihtml = lihtml
							+ "<li class='"
							+ data[i].cssclass
							+ "' onclick='newinserts(\"addholiday\",\"newfm\",\"添加假期\","+ data[i].year + ","+ data[i].month + ","+ data[i].day + ",\""+data[i].holidayName+"\","+data[i].holidayFlag+")'><div style='height:30px;line-height:30px;width:99%;;'>" + data[i].day + "</div>"
							+ "<div style='height:20px;width:99%;font-weight:normal;font-size:12px;'>" + (data[i].holidayName==null ? "" : data[i].holidayName)
							+ "</div>" + "</li>";
						}
						$("#day").empty();
						$("#day").append(lihtml);
						var $lis = $("#day").find("li");
						for ( var j = 0; j < $lis.length; j++) {
							var $li = $($lis[j]);
							if ($li.attr("class").indexOf("today") == -1) {
								$li.on({
									mouseover : function() {
										$(this).css({
											"background-color" : "#CCFF99"
										});
									},
									mouseout : function() {
										$(this).css({
											"background-color" : "#fff"
										});
									}
								});
							}
							;
						}
					});
}
function newholiday(addholiday, newfm, day) {
	var year = YEAR;
	var month = MONTH;
	var day = DAY;
	var selectyear = $("#selectyear").combobox("getValue");
	var selectmonth = $("#selectmonth").combobox("getValue");
	
	var url = "./addholiday?year=" + year + "&month=" + month + "&day=" + day;
	$('#' + newfm).form('submit', {
		url : url,
		onSubmit : function() {
			return $(this).form('validate');
		},
		success : function(data) {
			getyearmonth(selectyear, selectmonth);
			initHoliday();
		}
	});
	$("#addholiday").dialog("close");
}

function newinserts(dlg, fm, title,year,month, day, holidayName,holidayFlag) {
	YEAR = year;
	MONTH = month;
	DAY = day;
	$('#' + dlg).dialog('open').dialog('setTitle', title);
	$("#transyear").text(year);
	$("#transmonth").text(month);
	$("#transday").text(day);
	if (arguments.length > 3) {
		$('#' + fm).form('clear');
		if(holidayFlag!=null && holidayFlag != ""){
			document.getElementById("holidayFlag").checked=true;
		}
		if(holidayName=="null"){
			$("#holidayName").val("");
		}
		else{
			$("#holidayName").val(holidayName);
		}
		$('#' + fm).form('load', arguments[3]);
	} else {
		$('#' + fm).form('clear');
	}
}
function getyearholiday(selectyear,selectholiday){
	var url = "./getyearholiday?selectyear=" + selectyear + "&selectholiday="
			+ selectholiday;
	$.get(	url,
			function(datainfo) {
				var data = datainfo.list;
				var month = datainfo.month ;
				$("#selectmonth").combobox("setValue",month);
				var lihtml = "";
				for ( var i = 0; i < data.length; i++) {
					lihtml = lihtml
					+ "<li class='"
					+ data[i].cssclass
					+ "' onclick='newinserts(\"addholiday\",\"newfm\",\"添加假期\","+ data[i].year + ","+ data[i].month + ","+ data[i].day + ",\""+data[i].holidayName+"\","+data[i].holidayFlag+")'><div style='height:30px;line-height:30px;width:99%;;'>" + data[i].day + "</div>"
					+ "<div style='height:20px;width:99%;font-weight:normal;font-size:12px;'>" + (data[i].holidayName==null ? "" : data[i].holidayName)
					+ "</div>" + "</li>";
				}
				$("#day").empty();
				$("#day").append(lihtml);
				var $lis = $("#day").find("li");
				for ( var j = 0; j < $lis.length; j++) {
					var $li = $($lis[j]);
					if ($li.attr("class").indexOf("today") == -1) {
						$li.on({
							mouseover : function() {
								$(this).css({
									"background-color" : "#CCFF99"
								});
							},
							mouseout : function() {
								$(this).css({
									"background-color" : "#fff"
								});
							}
						});
					}
					;
				}
				
			});
	
}


function getLen(s){
	var StartY=$('#startyear').val();
	var EndY=$('#endyear').val();
	var startend=StartY-EndY;
	var len = $('#'+s).val().length;
	if(len==4&&startend<=10&&StartY>=1900){
		return true;
	}
	else{
		return false;
	}
}
