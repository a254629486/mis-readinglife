<!DOCTYPE HTML>
<html>
	<head>
		<title>日历管理</title>
		<#include "../../framework/comm/meta.ftl">
		 <link rel="stylesheet" type="text/css" href="${basePath}/static/css/calendar.css">
   		 <script type="text/javascript" src="${basePath}/static/js/calendar.js"></script>
		<style type="text/css"></style>
	</head>
	<body>
		<input class="buttons" type="button" value="日历生成" class="easyui-linkbutton"
		onclick="newinsert('dlg','fm','生成日历')">
		<input class="buttons" type="button" value="刷新" class="easyui-linkbutton"
		onclick="showCalendar()">
	
		<div id="dlg" class="easyui-dialog" style="width: 500px; height: auto; padding: 10px 20px" closed="true"
		buttons="#dlg-buttons">
			<form id="fm" method="post" novalidate>
							 	<div class="fitem">
									<label>开始年份：</label> 
										<input id = "startyear" name="startyear" maxLength="4" required="true"  
											class="easyui-numberbox"
											 validType=""/></br></br>
									<label>结束年份：</label> 
										<input id = "endyear" name="endyear"  maxLength="4" required="true"
											class="easyui-numberbox"
											 validType=""/>
								</div>
			</form>
			</div>
		<div id="dlg-buttons">
			<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-ok" onclick="creatcalendar('dlg','fm','calendar','./creat')">生成</a> 
			<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">取消</a>
		</div>
	
		
		<div class="background">
			<div>
				<div style="margin:10px 0px 0px 0px;">
					年份<select  id="selectyear" style="margin:0px 0px 0px 20px;" >
					</select>
					月份<select  id="selectmonth">
					</select>
					节假日<select id="selectholiday" >
					</select>
					<input type="button" id="menu" value="返回今天"  onclick="showCalendar()">
				</div>
				<hr style="margin:10px 0px 0px 0px;">
				<div>
				<ul>
					<li class="week">一</li>
					<li class="week">二</li>
					<li class="week">三</li>
					<li class="week">四</li>
					<li class="week">五</li>	
					<li class="week">六</li>
					<li class="week">日</li>
				</ul>
				<ul id="day">
				</ul>
			</div>
		</div>
		
		<div id="addholiday" class="easyui-dialog" style="width: 500px; height: auto; padding: 10px 20px" closed="true"
		buttons="#add-buttion">
			<form id="newfm" method="post" novalidate>
							 	<div class="fitem">
							 		<div style="text-align:center;">
							 		日期：
							 		<span id="transyear"></span>-
							 		<span id="transmonth"></span>-
							 		<span id="transday"></span>
							 		</div></br>
							 		<input type="checkbox" name="holiday" id="holidayFlag" value = '1'><label>节假日</label> </br></br>
									<label>假日名称</label> 
										<input name="holidayname" id="holidayName"  
											class="easyui-validatebox" maxLength="4"
											 validType=""/>
								</div>
			</form>
		</div>
		
		
		<div id="add-buttion">
			<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-ok" onclick="newholiday('addholiday','newfm','day')">保存</a> 
			<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-cancel" onclick="javascript:$('#addholiday').dialog('close')">取消</a>
		</div>
		
	</body>
</html>
