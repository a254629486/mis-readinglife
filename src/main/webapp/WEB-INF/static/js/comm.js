/***
 * common js
 * 
 */
function formatFileSize(size){
	var unit = "B";
	if(size>(1024*1024*1024)){
		unit="GB";
		size /=(1024*1024*1024);
	}else if(size>(1024*1021)){
		unit="MB";
		size /=(1024*1024);
	}else if(size>1024){
		unit="KB";
		size /=1024;
	}
	var fsize=size.toFixed(2)+" "+unit;
	return fsize;
}
function removeAccessory(accessoryId,orderId){
	  var url=BASE_PATH+'/accessory/accessorys/'+accessoryId+"/delete";
	  $.post(url,
	  function(data,status){
		  	var queryParams = $('#accessorys').datagrid('options').queryParams;  
		    queryParams.orderId=orderId;    
		    $("#accessorys").datagrid('reload'); 
	  });
}
function showAccessory(accessoryId){
	
}
function downloadAccessory(accessoryId){
	 var url=BASE_PATH+'/accessory/download/'+accessoryId+"";
	  $.post(url,
	  function(data,status){
		  	//
	  });
}
/**
 * jquery swfupload plug
 * by zhanghui
 */
var swfu;
(function ($) { 
	$.fn.jupload = function (options) {//options 经常用这个表示有许多个参数。 
		var defaults = { 
		sessionId:'',
		url: BASE_PATH+"/accessory/upload", 
		size: 1024*100, 
		types: "*.jpg;*.jpeg;*.png;*.bmp;*.gif",
		params:{"orderId":''},
		show:false //定义是否只显示和下载附件
	};
	//默认值 
	var opts= $.extend(defaults , options); 
	return this.each(function () { 
			var $this = $(this);//获取当前对象 
			if(!opts.show){
				$($this).linkbutton({
					iconCls:"icon-attachment" ,
					plain:true
				});
			}
			initUploadHtml($this,opts);
			if(!opts.show){
				initSwfUplod(opts);
				$($this).bind("click",function(){ 
					$("#fsUploadProgress").empty();
					$("#upload-dlg").dialog('open');
				});
			}
			
			
		}); 
	};
	//easyui html
	function initUploadHtml($this,opts) {    
		if($("#accessorys").length==0){
			
			 $($this).parent().append("<br/><div style='padding:0px 0px 0px 85px'><table style='width:680px;' id='accessorys' ></table></div>");
			 $('#accessorys').datagrid({   
			    url:BASE_PATH+'/accessory/accessorys/json', 
			    queryParams: opts.params,
			    singleSelect:true,
			    rownumbers:true,
			    columns:[[   
			        {field:'name',title:'名称',width:400, 
			        	formatter:function(value,rec){  
				        		return value+"."+rec.suffix;  
	                 	}  
			        },   
			        {field:'size',title:'大小',width:80,
				        formatter:function(value,rec){  
				        		return formatFileSize(value);  
	                 	} 
			        },   
			        {field:'accessoryId',title:'操作',width:120,
				        formatter:function(value,rec){  
				        		var operate="";
				        		if(!opts.show){
				        			operate=operate+"<a href=\"javascript:removeAccessory('"+value+"','"+opts.params.orderId+"')\">删除</a>";
				        		}
				        		//operate=operate+"&nbsp;|&nbsp;<a href=\"javascript:showAccessory('"+value+"')\">查看</a>";
				        		//operate=operate+"&nbsp;|&nbsp;<a href=\"javascript:downloadAccessory('"+value+"')\">下载</a>";
				        		operate=operate+"&nbsp;|&nbsp;<a href=\""+BASE_PATH+'/accessory/download/'+value+"\">下载</a>";
				        		return operate;  
	                 	}
			        }   
			        
			    ]]   
			});
		}
		if($("#win-toolbar").length==0){
			var toolBarHtml="<div id='win-toolbar' style='padding:2px;'>"+
								"<span id='spanButtonPlaceHolder'></span>"+
								"<a id='btnCancel' href='javascript:swfu.cancelQueue();'>取消所有上传</a>"+
								"<span id=\"divStatus\" style=\"float: right;color:green;padding:5px 5px 0px 0px\">0 个文件已上传</span> "+
							"</div>";
		
			$("body").append(toolBarHtml);
			$("#btnCancel").linkbutton({
				iconCls:"icon-cancel",
				plain:true
			});
		}
	
		if($("#upload-dlg").length==0){
			var dlgHtml="<div id='upload-dlg' style='width:650px;height:400px;padding:0px;'>"+
							"<div class=\"swfuoload-progress\">"+
								"<div class=\"progress-head\">"+
									"<div class=\"uploadFileName\">文件名</div>"+
									"<div class=\"uploadFileSize\">文件大小</div>"+
									"<div class=\"uploadProgress\">进度</div>"+
									"<div class=\"uploadStatus\">状态</div>"+
									"<div class=\"uploadOperate\" style=\"width: 60px;\">操作</div>"+
									"<div style=\"clear:both;\"></div>"+
								"</div>"+
								"<div id=\"fsUploadProgress\" class=\"progress-body\"></div>"+
							"</div>"+
						"</div>";
			$("body").append(dlgHtml);
			
			$("#upload-dlg").dialog({
				iconCls:'icon-save',
				closed:true,
				modal:true,
				title:"上传附件",
				toolbar: '#win-toolbar',
				onClose: function(){ 
					var params=opts.params;
					var queryParams = $('#accessorys').datagrid('options').queryParams;  
				    queryParams.orderId=params.orderId;    
				    $("#accessorys").datagrid('reload'); 
		        } 
			});
		}
	};
	//swfupload
	function initSwfUplod(opts){
		//document.cookie=";JSESSIONID=16f83e22-81b9-4275-866c-9e6b5a74e669";
		window.onload = function() {
			var settings = {
				flash_url : BASE_PATH+"/static/js/swfupload/swfupload/swfupload.swf",
				upload_url: opts.url,	
				post_params: opts.params ,
				file_size_limit : opts.size,
				file_types : opts.types,
				file_types_description : "",
				file_upload_limit : 10,  //配置上传个数
				file_queue_limit : 0,
				custom_settings : {
					progressTarget : "fsUploadProgress",
					cancelButtonId : "btnCancel"
				},
				debug: true,

				// Button settings
				button_image_url: BASE_PATH+"/static/js/swfupload/images/button_bg_104_26.png",
				button_width: "104",
				button_height: "26",
				button_placeholder_id: "spanButtonPlaceHolder",
				button_text: '<span class="theFont">添加文件....</span>',
				button_text_style: ".theFont { font-size:12px; }",
				button_text_left_padding: 25,
				button_text_top_padding: 5,
				
				file_queued_handler : fileQueued,
				file_queue_error_handler : fileQueueError,
				file_dialog_complete_handler : fileDialogComplete,
				upload_start_handler : uploadStart,
				upload_progress_handler : uploadProgress,
				upload_error_handler : uploadError,
				upload_success_handler : uploadSuccess,
				upload_complete_handler : uploadComplete,
				queue_complete_handler : queueComplete	
			};
			settings.upload_url = settings.upload_url+";JSESSIONID="+opts.sessionId;
			swfu = new SWFUpload(settings);
	     };
	}	
})(jQuery); 

