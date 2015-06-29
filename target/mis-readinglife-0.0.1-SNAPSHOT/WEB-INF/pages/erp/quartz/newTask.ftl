<!DOCTYPE html>
<html>
	<head>
		<sj:head jqueryui="true" />
		<meta http-equiv="Content-Type" content="text/html; charset=urf-8">
		<title>添加任务</title>
		<#include "../../framework/comm/meta.ftl">
        <link rel="stylesheet" type="text/css" href="${basePath}/static/css/style.css">
		<script type="text/javascript" src="${basePath}/static/js/js/plugins/jquery.form.min.js"></script>
	    <script type="text/javascript" src="${basePath}/static/js/job.js"></script>
	    <script type="text/javascript" src="${basePath}/static/js/example.js"></script>
	    <script type="text/javascript" src="${basePath}/static/datepicker/WdatePicker.js"/></script>
		<script type="text/javascript">
	    $(document).ready(function(){
		 var triggerName =  '${schedulerTrigger.triggerName}';
		 var triggerType = '${schedulerTrigger.triggerType}';
		 var selectedGroup = '${schedulerTrigger.triggerGroup}';
		 var expressions = '${schedulerTrigger.triggerExpressions}';
		 var editFlag = $("#schedulerTrigger\\.editFlag").val();
		 //初始化添加页面的数据
		 $("#schedulerTrigger\\.originalGroup").val(selectedGroup);
		 $('#cron_triggerGroup').combobox('setValue', selectedGroup);
		 //for edit the jsp
		 if(triggerName!=null&&triggerName!=''&&editFlag!=''){
			 $("#cron_triggerName").attr("readonly", true);
			 $("#cron_triggerGroup").attr("value",selectedGroup);//设置value=selectedGroup的项目为当前选中项
			 if(triggerType=='CRON'){
			 
			 $("#checkTime").attr("checked", true);
			 /**
			  *  回写时间
			  */
			 var time = expressions.split(" ");
			 //回写分钟
			 var minute = time[1];
			 if(minute.indexOf('/')!=-1){
				 $("#cron_minute_rad").attr("checked",true);
				 var allMinute = minute.split('/');
				 $("#cron_minute_b").val(allMinute[0]);
				 $("#cron_minute_e").val(allMinute[1]);
			 }else{
				 $("#cron_minute_assign").attr("checked",true);
				 $("#cron_minute").val(minute);
			 }
			 //回写小时
			 var hour = time[2];
			 if(hour.indexOf('*')!=-1){
				 $("#cron_hour_rad").attr("checked",true);
			 }else{
				 $("#cron_hour_assign").attr("checked",true);
				 $("#cron_hour").val(hour);
			 }
			 
			 //rewrite day information
			 var day = time[3];
			 if(day.indexOf('?')!=-1){
				 //没有启用天，则说明启用星期设置
				 var week = time[5];
				 //如果星期是*或者数字，则回写星期信息
				 $("#cron_week_rad_active").attr("checked",true);
				 if(week.indexOf('*')!=-1){
					 $("#cron_week_rad").attr("checked",true);
				 }else{
					 $("#cron_week_assign").attr("checked",true);
					 $("#cron_week").val(week);
				 }
				
			 }else{
				 if(day.indexOf('*')!=-1){
					 $("#cron_day_rad").attr("checked",true);
				 }else{
					 $("#cron_day_assign").attr("checked",true);
					 $("#cron_day").val(day);
				 }
			 }
			 
			 //config month
			 var month = time[4];
			 if(month.indexOf('*')!=-1){
				 $("#cron_month_rad").attr("checked",true);
			 }else{
				 $("#cron_month_assign").attr("checked",true);
				 $("#cron_month").val(month);
			 } 
			 $("#timeConfig").show();
		 }else{
			 $("#timeConfig").hide();
		 }
			 }
	  });
	//
	function showTimeConfig(){
		$("#timeConfig").toggle(1000);
		//设置分钟的初始值
		$("#cron_minute_b").val(0);
		$("#cron_minute_e").val(5);
	
	}
	
	//设置星期信息
	function setWeek(){
		$("#cron_week_rad").attr("checked",true);
		if($("#cron_week_rad_active").attr("checked")==false){
			$("#cron_week_rad").attr("checked",false);
			$("#cron_week_assign").attr("checked",false);
		}

	}
	
	$(document).ready(function(){
		 //validate时候是否选中,时间设置区域是否显示
		 var flag = $("#checkTime").attr("checked");
			if(flag){
			  $("#timeConfig").show();
			}else{
				$("#timeConfig").hide();
			}
	});
	
	//重置
	function resetFiled(){
		var editFlag = $("#schedulerTrigger\\.editFlag").val();
		if(editFlag==""){
		$("#cron_triggerName").val('');
		$("#cron_triggerGroup").val('');
		  }
		$("#startTime").val('');
		$("#endTime").val('');
		//重置分钟设置
		$("#cron_minute_rad").attr("checked",true);
		$("#cron_minute_b").val(0);
		$("#cron_minute_e").val(5);
		$("#cron_minute").val('');
		//重置小时
		$("#cron_hour_rad").attr("checked",true);
		$("#cron_hour").val('');
		//重置天
		$("#cron_day_rad").attr("checked",true);
		$("#cron_day").val('');
		//重置月
		$("#cron_month_rad").attr("checked",true);
		$("#cron_month").val('');
		//重置星期
		$("#cron_week_rad_active").attr("checked",false);
		$("#cron_week_rad").attr("checked",false);
		$("#cron_week_assign").attr("checked",false);
		$("#cron_week").val('');
	}
	
	//提交表单验证时间规则
	function submitForm(){
		//targetForm=document.forms[0];
		//检验分钟
		//如果分钟为循环的格式
		if($("#cron_minute_rad").attr("checked")){
			var minutebegin = $("#cron_minute_b").val();
			 //判断分钟是否为正整数
			 var type="minute";
			 if(checkNum(minutebegin,type)){
			   if(minutebegin>59){
				 jQuery.messager.alert('系统提示','分钟不能大于59');
				 return;
			  }
			 }
			var endMinute = $("#cron_minute_e").val();
			 if(checkNum(endMinute,type)){
				   if(endMinute>59){
					 jQuery.messager.alert('系统提示','分钟不能大于59');
					 return;
				  }
				 }
		}
		if($("#cron_minute_assign").attr("checked")){
			var minute = $("#cron_minute").val();
			var type="minute";
			if(minute.indexOf(",")!=-1||minute.indexOf("-")!=-1||checkNum(minute,type)){
				//判断逗号的格式
				if(minute.indexOf(",")!=-1){
				 var minuteTime = minute.split(",");
				 for(i=0;i<minuteTime.length;i++){
					 //判断分钟是否为正整数
					 if(checkNum(minuteTime[i],type)){
					   if(minuteTime[i]>59){
						 jQuery.messager.alert('系统提示','分钟不能大于59');
						 return;
					  }
					 }else{
						 return;
					 }
				 }
				 
				 //判断为-的情况
				}else{
					
					 var minuteTime = minute.split("-");
					 //分隔符多余一个逗号
					 if(minuteTime.length>2){
						 jQuery.messager.alert('系统提示','分钟时间格式不正确');
						 return;
					 }else{
						 
						 for(i=0;i<minuteTime.length;i++){
							 if(checkNum(minuteTime[i],type)){
								 if(minuteTime[i]>59){
									 jQuery.messager.alert('系统提示','分钟不能大于59');
									 return;
								 }
							 }else{
								 //不是正整数返回
								 return;
							 }
						 }
						 
					 }
				}
			}else{
				jQuery.messager.alert('系统提示','分钟时间格式不正确');
				return;
			}
			
		}
		
		//判断小时,如果选定为制定小时
		if($("#cron_hour_assign").attr("checked")){
			var hour = $("#cron_hour").val();
			var type="hour";
			if(hour.indexOf(",")!=-1||hour.indexOf("-")!=-1||checkNum(hour,type)){
				
				if(hour.indexOf(",")!=-1){
					var hourTime = hour.split(",");
					 for(i=0;i<hourTime.length;i++){
						 if(checkNum(hourTime[i],type)){
						 if(hourTime[i]>23){
							 jQuery.messager.alert('系统提示','小时不能大于23');
							 return;
						 }}else{
							 return;
						 }
					 }
				}else{
					
					 var hourTime = hour.split("-");
					 //分隔符多余一个逗号
					 if(hourTime.length>2){
						 jQuery.messager.alert('系统提示','分钟时间格式不正确');
						 return;
					 }else{
						 
						 for(i=0;i<hourTime.length;i++){
							 if(checkNum(hourTime[i],type)){
							 if(hourTime[i]>23){
								 jQuery.messager.alert('系统提示','小时不能大于23');
								 return;
							 }}else{
								 return;
							 }
						 }
					 }
				}
				
			}else{
				jQuery.messager.alert('系统提示','小时时间格式不正确');
				return;
			}
		}
		
		//判断天
		if($("#cron_day_assign").attr("checked")){
			var day = $("#cron_day").val();
			var type="day";
			if(day.indexOf(",")!=-1||day.indexOf("-")!=-1||checkNum(day,type)){
				
				if(day.indexOf(",")!=-1){
					var dayTime = day.split(",");
					for(i=0;i<dayTime.length;i++){
						if(checkNum(dayTime[i],type)){
						 if(dayTime[i]>getdays()){
							 jQuery.messager.alert('系统提示','天不能大于'+getdays());
							 return;
						 }
						}else{
							return;
						}  
					 }
				}else{
					 var dayTime = day.split("-");
					 //分隔符多余一个逗号
					 if(dayTime.length>2){
						 alert("天时间格式不正确");
						 jQuery.messager.alert('系统提示','天时间格式不正确');
						 return;
					 }else{
						 for(i=0;i<dayTime.length;i++){
							 if(checkNum(dayTime[i],type)){
							 if(dayTime[i]>getdays()){
								 jQuery.messager.alert('系统提示','天不能大于'+getdays());
								 return;
							 }
							 }else{
								 return;
							 }
						 }
						 
					 }
				}
				
			}else{
				alert("天时间格式不正确");
				jQuery.messager.alert('系统提示','天时间格式不正确');
				return;
			}
			
		}
		
		//判断月
		if($("#cron_month_assign").attr("checked")){
			var month = $("#cron_month").val();
			var type="month";
			if(month.indexOf(",")!=-1||month.indexOf("-")!=-1||checkNum(month,type)){
				
				if(month.indexOf(",")!=-1){
					var monthTime = month.split(",");
					for(i=0;i<monthTime.length;i++){
						if(checkNum(monthTime[i],type)){
						if(monthTime[i]>12){
							 jQuery.messager.alert('系统提示','月不能大于12');
							 return;
						 }
						}else{
							return;
						}
					 }
				}else{
					 var monthTime = month.split("-");
					 //分隔符多余一个逗号
					 if(month.length>2){
						 jQuery.messager.alert('系统提示','月时间格式不正确');
						 return;
					 }else{
						 
						 for(i=0;i<monthTime.length;i++){
							 if(checkNum(monthTime[i],type)){
							 if(monthTime[i]>12){
								 jQuery.messager.alert('系统提示','月不能大于12');
								 return;
							 }
							 }else{
								 return;
							 }
						 }
					 }
				}
				
			}else{
				jQuery.messager.alert('系统提示','月时间格式不正确');
				return;
			}
			
		}
		
		//判断星期
		if($("#cron_week_assign").attr("checked")){
			var week = $("#cron_week").val();
			var type="week";
			if(week.indexOf(",")!=-1||week.indexOf("-")!=-1||checkNum(week,type)){
				
				if(week.indexOf(",")!=-1){
					var weekTime = week.split(",");
					for(i=0;i<weekTime.length;i++){
						 if(checkNum(weekTime[i],type)){
						 if(weekTime[i]>7){
							 jQuery.messager.alert('系统提示','星期不能大于7');
							 return;
						 }
					   }else{
						   return;
					   } 
					 }
				}else{
					 var weekTime = week.split("-");
					 //分隔符多余一个逗号
					 if(week.length>2){
						 jQuery.messager.alert('系统提示','月时间格式不正确');
						 return;
					 }else{
						 
						 for(i=0;i<weekTime.length;i++){
							 if(checkNum(weekTime[i],type)){
							 if(weekTime[i]>7){
								 jQuery.messager.alert('系统提示','星期不能大于7');
								 return;
							 }
							 
							 }else{return;}
						 }
					 }
				}
				
			}else{
			    jQuery.messager.alert('系统提示','星期时间格式不正确');
				return;
			}
			
		}
		
		var ajaxCallUrl = "${basePath}/taskManagementController/add";
		$.ajax({
                cache: true,
                type: "POST",
                url:ajaxCallUrl,
                data:$('#newTaskForm').serialize(),// 你的formid
                async: false,
                error: function(request) {
                    alert("Connection error");
                },
                success: function(data) {
                    var msg = eval('('+data+')');
                    if(msg.success=='falseName'){
                      $('#cron_triggerNameInfo').html("");
				      $('#cron_triggerNameInfo').html(msg.msg);
                    }
                   else if(msg.success=='falseGroup'){
                      $('#cron_triggerGroupInfo').html("");
				      $('#cron_triggerGroupInfo').html(msg.msg);
                    }
                   else if(msg.success=='falseTime'){
                      $('#endTimeInfo').html("");
				      $('#endTimeInfo').html(msg.msg);
                    }else{
                    window.parent.closedl(msg);
                    }
                }
            });
 
			}
	
	
	//获取当月天数
	function getdays(){
		var d = new Date();
        var curMonthDays = new Date(d.getFullYear(), (d.getMonth()+1), 0).getDate();
      //alert("本月共有 "+ curMonthDays +" 天");
        return curMonthDays;

	}
	//检查数字是否为正整数
	function checkNum(obj,type)
	{   if(obj==0){
		return true;
	}
		var reg = /^[0-9]*[1-9][0-9]*$/;
	     if (!reg.test(obj)){   
	    	if(type=="minute"){
	    		 jQuery.messager.alert('系统提示','分钟必须为正整数!');
	    	}else if(type=="hour"){
	    		 jQuery.messager.alert('系统提示','小时必须为正整数!');
	    	}else if(type=="day"){
	    		 jQuery.messager.alert('系统提示','天必须为正整数!');
	    	}else if(type=="month"){
	    		jQuery.messager.alert('系统提示','月必须为正整数!');
	    	}else{
	    		jQuery.messager.alert('系统提示','星期必须为正整数!');
	    	}
	  
	        return false;
	     }
	   return true;
	} 

	//分钟
	function assignCheck(obj){
		if(obj.id=='cron_minute'){
		$("#cron_minute_assign").attr("checked",true);
		}else if(obj.id=='cron_minute_e'||obj.id=='cron_minute_b'){
			$("#cron_minute_rad").attr("checked",true);
		}else if(obj.id=='cron_hour'){
			$("#cron_hour_assign").attr("checked",true);
		}else if(obj.id=='cron_day'){
			$("#cron_day_assign").attr("checked",true);
		}else if(obj.id=='cron_month'){
			$("#cron_month_assign").attr("checked",true);
		}else{
			$("#cron_week_assign").attr("checked",true);
		}
	}
	
</script>
<style type="text/css">
<!--
body {
	font-size: 12px
}

.STYLE1 {
	font-size: 12px
}

.STYLE3 {
	font-size: 12px;
	color: #CCCCCC;
}

.STYLE2 {
	font-size: 12px;
	font-weight: bold;
}
-->
</style>
	</head>
	<body>
		<br>
		<form id="newTaskForm" method="post" novalidate>
			<input type="hidden" name="editFlag" id="schedulerTrigger.editFlag" value="${schedulerTrigger.editFlag}" />
			<input type="hidden" name="originalGroup" id="schedulerTrigger.originalGroup" value="" />
			<table width="583" border="0" cellpadding="0" cellspacing="1" bgcolor="#efefef">
				<tr>
					<td height="50" width="100" bgcolor="#FFFFFF">
						<span class="STYLE1"> &nbsp;任务名称：</span>
					</td>
					<td bgcolor="#FFFFFF">
						<input type="text" class="easyui-validatebox" required="true" id="cron_triggerName" name="triggerName" value="${schedulerTrigger.triggerName}" size="20" maxlength= "20" onblur="validate('cron_triggerName','1','newTaskForm')"/>
						&nbsp;&nbsp;
						<font color="red"><span id="cron_triggerNameInfo" class="info"></span><b>*</b>
						</font>
					</td>
				</tr>
				<tr>
					<td height="50" bgcolor="#FFFFFF">
						<span class="STYLE1"> &nbsp;功能名称：</span>
					</td>
					<td bgcolor="#FFFFFF">
						<input id="cron_triggerGroup" name="triggerGroup" required="true" style="width:100px" url="${basePath}/taskManagementController/getGroupList" class="easyui-combobox" valueField="groupName" textField="groupName" ></input>
						&nbsp;&nbsp;
						<font color="red"><span id="cron_triggerGroupInfo" class="info"></span><b>*</b>
						</font>
					</td>
				</tr>
				<tr>
					<td height="30" bgcolor="#FFFFFF">
						<span class="STYLE1"> &nbsp;时间设置：</span>
					</td>
					<td height="30" bgcolor="#FFFFFF">
						<input id="checkTime" name="timeConfig" type="checkbox" onclick="showTimeConfig()" <#if schedulerTrigger.timeConfig == 'on'>checked</#if>/>
						<font color="red">注：如果设置时间，任务将按照时间规则执行，不设置时间，任务只执行一次</font>
					</td>
				</tr>
			</table>
			<div id="timeConfig">
				<table width="583" border="0" cellpadding="0" cellspacing="1" bgcolor="#efefef">
					<tr>
						<td height="50" width="100" bgcolor="#FFFFFF">
							<span class="STYLE1"> &nbsp;开始时间：</span>
						</td>
						<td bgcolor="#FFFFFF">
						    <input id="startTime" name="startTime" class="easyui-validatebox" type="text" value="${schedulerTrigger.startTime}"/>
                            <img onclick="WdatePicker({el:'startTime',readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'%y-%M-%d %H:%m:%s'})" src="${basePath}/static/datepicker/skin/datePicker.gif" width="16" height="22" align="middle">
						</td>
					</tr>
					<tr>
						<td height="50" bgcolor="#FFFFFF">
							<span class="STYLE1"> &nbsp;结束时间：</span>
						</td>
						<td bgcolor="#FFFFFF">
						    <input id="endTime" name="endTime" class="easyui-validatebox" type="text" value="${schedulerTrigger.endTime}"/>
                            <img onclick="WdatePicker({el:'endTime',readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'%y-%M-%d %H:%m:%s'})" src="${basePath}/static/datepicker/skin/datePicker.gif" width="16" height="22" align="middle">
                            <font color="red"><span id="endTimeInfo" class="info"></span>
						</font>
						</td>
					</tr>
					<tr>
						<td colspan="3">
							<div id="page-wrap">
								<div id="organic-tabs">
									<ul id="explore-nav">
										<li id="ex-featured1">
											<a rel="featured1" href="#" class="current">分钟</a>
										</li>
										<li id="ex-featured2">
											<a rel="featured2" href="#">小时</a>
										</li>
										<li id="ex-featured3">
											<a rel="featured3" href="#">天</a>
										</li>
										<li id="ex-featured4">
											<a rel="featured4" href="#">月</a>
										</li>
										<li id="ex-featured5" class="last">
											<a rel="featured5" href="#">星期</a>
										</li>
									</ul>
									<div id="all-list-wrap">
										<ul id="featured1">
											<table cellpadding="2" cellspacing="2">
												<tr>
													<td>
														<input type="radio" id="cron_minute_rad" name="cron_minute_rad" value="cron_cycle" checked="checked" />
														循环:&nbsp;
													</td>
													<td>
														从
														<input type="text" id="cron_minute_b" class="easyui-validatebox" name="cron_minute_b" value="${schedulerTrigger.cron_minute_b}" size="5" onclick="assignCheck(this)"/>
														分开始,&nbsp;&nbsp;每
														<input type="text" id="cron_minute_e" class="easyui-validatebox" name="cron_minute_e" value="${schedulerTrigger.cron_minute_e}" size="5" onclick="assignCheck(this)"/>
														分执行一次
													</td>
												</tr>
												<tr>
													<td>
														<input type="radio" id="cron_minute_assign" name="cron_minute_rad" value="assign" <#if schedulerTrigger.cron_minute_rad == 'assign'>checked</#if>/>
														指定:&nbsp;
													</td>
													<td>
														<input type="text" id="cron_minute" name="cron_minute" class="easyui-validatebox" value="${schedulerTrigger.cron_minute}" size="26" onclick="assignCheck(this)"/>
														&nbsp;(数字以"," "-"分格)
													</td>
												</tr>
											</table>
										</ul>
										<ul id="featured2">
											<table cellpadding="2" cellspacing="2">
												<tr>
													<td>
														<input type="radio" id="cron_hour_rad" name="cron_hour_rad" value="per" checked="checked" />
														每小时&nbsp;
													</td>
													<td>
														&nbsp;
													</td>
												</tr>
												<tr>
													<td>
														<input type="radio" id="cron_hour_assign" name="cron_hour_rad"  value="assign" <#if schedulerTrigger.cron_hour_rad == 'assign'>checked</#if>/>
														指定:&nbsp;
													</td>
													<td>
														<input type="text" id="cron_hour" name="cron_hour" class="easyui-validatebox" value="${schedulerTrigger.cron_hour}" size="26" onclick="assignCheck(this)"/>
														&nbsp;(数字以"," "-"分格)
													</td>
												</tr>
											</table>
										</ul>
										<ul id="featured3">
											<table cellpadding="2" cellspacing="2">
												<tr>
													<td>
														<input type="radio" id="cron_day_rad" name="cron_day_rad" value="per" checked="checked" />
														每天&nbsp;
													</td>
													<td>
														&nbsp;
													</td>
												</tr>
												<tr>
													<td>
														<input type="radio" id="cron_day_assign" name="cron_day_rad" value="assign" <#if schedulerTrigger.cron_day_rad == 'assign'>checked</#if>/>
														指定:&nbsp;
													</td>
													<td>
														<input type="text" id="cron_day" name="cron_day" value="${schedulerTrigger.cron_day}" class="easyui-validatebox" size="26" onclick="assignCheck(this)"/>
														&nbsp;(数字以"," "-"分格)
													</td>
												</tr>
											</table>
										</ul>
										<ul id="featured4">
											<table cellpadding="2" cellspacing="2">
												<tr>
													<td>
														<input type="radio" id="cron_month_rad" name="cron_month_rad" value="per" checked="checked" />
														每月&nbsp;
													</td>
													<td>
														&nbsp;
													</td>
												</tr>
												<tr>
													<td>
														<input type="radio" id="cron_month_assign" name="cron_month_rad" value="assign" <#if schedulerTrigger.cron_month_rad == 'assign'>checked</#if>/>
														指定:&nbsp
													</td>
													<td>
														<input type="text" id="cron_month" name="cron_month" value="${schedulerTrigger.cron_month}" class="easyui-validatebox" size="26" onclick="assignCheck(this)"/>
														&nbsp;(数字以"," "-"分格)
													</td>
												</tr>
											</table>
										</ul>
										<ul id="featured5">
											<table cellpadding="2" cellspacing="2">
												<tr>
													<td>
														<input type="checkbox" id="cron_week_rad_active" name="cron_week_rad_active" value="active" onclick="setWeek()" <#if schedulerTrigger.cron_week_rad_active == 'active'>checked</#if>/>
														启用&nbsp;
													</td>
													<td>
														&nbsp;
													</td>
												</tr>
												<tr>
													<td>
														<input type="radio" id="cron_week_rad" name="cron_week_rad" value="per" />
														每星期&nbsp
													</td>
													<td>
														&nbsp;
													</td>
												</tr>
												<tr>
													<td>
														<input type="radio" id="cron_week_assign" name="cron_week_rad" value="assign" <#if schedulerTrigger.cron_week_rad == 'assign'>checked</#if>/>
														指定:&nbsp
													</td>
													<td>
														<input type="text" id="cron_week" class="easyui-validatebox" name="cron_week" value="${schedulerTrigger.cron_week}" size="26" onclick="assignCheck(this)"/>
														&nbsp;(数字以"," "-"分格)
													</td>
												</tr>
											</table>
										</ul>
									</div>
									<!-- END List Wrap -->
								</div>
								<!-- END Organic Tabs -->
							</div>
						</td>
					</tr>
				</table>
			</div>
			<br>
			<table width="583" border="0" cellpadding="0" cellspacing="1">
				<tr>
					<td colspan="3">
						&nbsp;&nbsp;&nbsp;
                        <a href="#" onclick="submitForm()" class="easyui-linkbutton">确定</a>						
						&nbsp;&nbsp;&nbsp;
						<a href="#" onclick="resetFiled()" class="easyui-linkbutton">重置</a>	
					</td>
				</tr>
			</table>
		</form>
		<br>
	</body>
</html>
