/**
*zh-jcatalog
*by zhanghui 2014-3-24
**/
(function ($) { 
	$.fn.jcatalog = function (options) {//options 经常用这个表示有许多个参数。 
		var defaults = { 
		width: 400, 
		height: 600
	};
	//默认值 
	var opts= $.extend(defaults , options); 
	return this.each(function () { 
			var $this = $(this);//获取当前对象 
			var $items=$this.find(".j-item");
			$items.find('.j-item-body').css({"width":opts.width,"height":opts.height});
			for(var i=0;i<$items.length;i++){
				var $item=$($items[i]);
				$item.attr("pror",i);
				$item.bind({mouseover:function(){
					var top = 20;
					//var left = $(this).offset().left+$(this).width()+14;
					var left = $(this).offset().left+$(this).width()-6;
					
					//if(($(this).offset().top)>($(this).next('.j-item-body').height())){
						top = $(this).offset().top-(parseInt($(this).attr("pror"))*34);
					//}
					//$(this).css('border-right','0');
					$(this).find('.j-item-body').show().css({'left':left+'px',top:top+'px'});
					$(this).find(".j-item-title").addClass('focus');
					$(this).find('.j-item-body').addClass('check');
					
				},mouseout:function(){
					$(this).find('.j-item-body').hide();
					$($(this).find(".j-item-title")).removeClass('focus');
				}});
			}
			
			
		}); 
	};
	//
	function initUploadHtml($this,opts) {    
		
	};
	//
	function initSwfUplod(opts){
	
	}	
})(jQuery); 



/**
*zh-jslide
*by zhanghui 2014-3-24
**/
var $jslider; //全局slider变量
var jtimer; //全局计时器
(function ($) { 
	$.fn.jslide = function (options) {//options 经常用这个表示有许多个参数。 
		var defaults = { 
		
	};
	//默认值 
	var opts= $.extend(defaults , options); 
	return this.each(function () { 
			var $this = $(this);//获取当前对象 
			$jslider=$this;
			var $item=$($this.find(".j-slide-item")[0]);
			var $pager=$($this.find(".j-slide-pager")[0]);
			
			var $itemlis=$($item.find("li"));
			var $pagerlis=$($pager.find("li"));
			
			
			for(var i=0;i<$pagerlis.length;i++){
				var $pagerli=$($pagerlis[i]);
				$pagerli.bind({
					click:function(){
						//to add code
					},
					mouseover:function(){
						$pagerlis.removeClass("checked");
						$(this).addClass("checked");
						
						var num=parseInt($(this).text())-1;
						$itemlis.hide();
						$($itemlis[num]).fadeIn(); 
						clearInterval(jtimer);
						jtimer=window.setInterval(jIntervalTimer, 5000); 
					}
				});
			}
			 
			jtimer=window.setInterval(jIntervalTimer, 5000); 
		}); 
	};	
	//定时轮播
	function jIntervalTimer(){
		var $item=$($jslider.find(".j-slide-item")[0]);
		var $pager=$($jslider.find(".j-slide-pager")[0]);
		
		var $itemlis=$($item.find("li"));
		var $pagerlis=$($pager.find("li"));
		
		var $pagerchecked=$($pager.find(".checked"));
		var num=parseInt($pagerchecked.text())-1;
		var next=0;
		if(num<5){
			next=num+1;	
		}else{
			next=0;
		}
		
		$pagerlis.removeClass("checked");
		$($pagerlis[next]).addClass("checked");
		
		$itemlis.hide();
		$($itemlis[next]).fadeIn(); 
	}
})(jQuery); 

/**
*zh-jscroll
*by zhanghui 2014-3-24
**/
var jstart=0; //起始编号 全局变量
(function ($) { 
	$.fn.jscroll = function (options) {//options 经常用这个表示有许多个参数。 
		var defaults = { 
		
	};
	//默认值 
	var opts= $.extend(defaults , options); 
	return this.each(function () { 
		var $this = $(this);//获取当前对象 
		var $prev = $this.find(".j-scroll-prev");
		var $next= $this.find(".j-scroll-next");
		var $item=$this.find(".j-scroll-item");
		var $itemlis=$item.find("li");
		
		for(var i=0;i<$itemlis.length;i++){
			$itemli=$($itemlis[i]);
			$itemli.attr("num",i);
			var rslt = (i+1)%3;
			if(rslt!=0){
				$itemli.addClass("line");
			}
		}
		
		$($itemlis[0]).css("display","list-item");
		$($itemlis[1]).css("display","list-item");
		$($itemlis[2]).css("display","list-item");
		
		
		
		$prev.bind({
				click:function(){
					var $itemshow=$($itemlis[jstart]);
					var nextarr;
					if((jstart+2)<=14){
						nextarr=[jstart+3,jstart+4,jstart+5];
					}else{
						nextarr=[0,1,2];
					}
					$($itemlis[jstart]).css("display","none");
					$($itemlis[jstart+1]).css("display","none");
					$($itemlis[jstart+2]).css("display","none");

					$($itemlis[nextarr[0]]).fadeIn();
					$($itemlis[nextarr[1]]).fadeIn();
					$($itemlis[nextarr[2]]).fadeIn();
					jstart=nextarr[0];//重置计数
				}
			});
			
		$next.bind({
				click:function(){
					var $itemshow=$($itemlis[jstart]);
					var nextarr;
					if(jstart==0){
						nextarr=[15,16,17];
					}else{
						nextarr=[jstart-3,jstart-2,jstart-1];
					}
					$($itemlis[jstart]).css("display","none");
					$($itemlis[jstart+1]).css("display","none");
					$($itemlis[jstart+2]).css("display","none");

					$($itemlis[nextarr[0]]).fadeIn();
					$($itemlis[nextarr[1]]).fadeIn();
					$($itemlis[nextarr[2]]).fadeIn();
					jstart=nextarr[0];//重置计数
				}
			});
			
		}); 
	};	

})(jQuery);


/**
*zh-jtab
*by zhanghui 2014-3-24
**/
(function ($) { 
	$.fn.jtab = function (options) {//options 经常用这个表示有许多个参数。 
		var defaults = { 
		
	};
	//默认值 
	var opts= $.extend(defaults , options); 
	return this.each(function () { 
			var $this = $(this);//获取当前对象 
			var $titles=$this.find(".jtab-titles");
			var $items=$this.find(".jtab-items");
			var $titlelis=$titles.find("li");
			var $tabitems=$items.find(".tabitem");
			var wapper_width=$this.width();
			var titleli_width=wapper_width/$titlelis.length;
			$titlelis.css("width",titleli_width);	
			for(var i=0;i<$titlelis.length;i++){
				var $titleli=$($titlelis[i]);
				$titleli.attr("num",i);
			}
			var tit_height=$titles.height();
			var wapper_height=$this.height();
			var items_height=wapper_height-tit_height;
			var items_width=$this.width();
			$items.css({width:items_width,height:items_height});
			for(var i=0;i<$tabitems.length;i++){
				var $tabitem=$($tabitems[i]);
				$tabitem.css({width:items_width,height:items_height});
			}
			
			for(var i=0;i<$titlelis.length;i++){
				var $titleli=$($titlelis[i]);
				$titleli.bind({
					mouseover:function(){
						$titlelis.removeClass("checked");
						$(this).addClass("checked");
						var num=parseInt($(this).attr("num"));
						$tabitems.css("display","none");
						$($tabitems[num]).css("display","block");
					}	
				});
			}
			
		}); 
	};	

})(jQuery);

/**
*zh-jcombo
*by zhanghui 2014-4-17
**/
(function ($) { 
	$.fn.jcombo = function (options) {//options 经常用这个表示有许多个参数。 
		var defaults = { 
		url:""
	};
	//默认值 
	var opts= $.extend(defaults , options); 
	return this.each(function () { 
			
	
	
			var $this = $(this);//获取当前对象 
			var top=$this.offset().top;
			var left=$this.offset().left;
			var height=$this.height();
			var width=$this.width();
			
			var lstop=top+height+2;
			var lsleft=left;
			var lswidth=width-0;
			
			var htl="<ul class=\"zh-combo-ls\" style=\"display:none\"></ul>";
			$this.after(htl);
			var $combols=$this.next();
			
			
			$combols.css({
  				left: lsleft,
  				top: lstop,
				width:lswidth
			});
			
			$this.bind({
				focus:function(){
					
					$combols.show().css({
						left: lsleft,
						top: lstop,
						width:lswidth
					});
				},
				propertychange:function(){
					
				},
				blur:function(){
					window.setTimeout(function(){
						$combols.hide();
						},500); 
					//$("#hotwords").hide();
				},
				keyup:function(){
					var val=$this.val();
					$.ajax({
						 type: "post",
						 url: opts.url,
						 data: {keywords:val},
						 async:false,
						 success: function(data){
									 $combols.empty();
									 var html = ''; 
									 $.each(data, function(index, words){
										   html += " <li title=\""+words.name+"\">"+words.name+"</li>";
									 });
									$combols.html(html);
									 
										var $lis=$combols.find("li");
										for(var i=0;i<$lis.length;i++){
											var $li=$($lis[i]);
											$li.bind({
												click:function(){
													var text=$(this).attr("title");
													$this.attr("value",text);
												}	
											});	
										}
									 
								  }
					 });
					
					
				}
			});
			var $lis=$(".zh-combo-ls").find("li");
			for(var i=0;i<$lis.length;i++){
				var $li=$($lis[i]);
				$li.bind({
					click:function(){
						var text=$(this).attr("title");
						$this.attr("value",text);
					}	
				});	
			}
		}); 
	};
		
})(jQuery); 



/**
*zh-jtab
*by zhanghui 2014-4-21
**/
function showhidden(obj){
		
		var $prev=$(obj).parent().prev(".search-more");
		var dis=$prev.css("display");
		if(dis=="none"){
			$prev.show();
			$(obj).text("hidden search");
		}else{
			$prev.hide();
			$(obj).text("show search");
		}
		
	}
(function ($) { 
	$.fn.jsearch = function (options) {//options 经常用这个表示有许多个参数。 
		var defaults = { 
		isShow:false
	};
	//默认值 
	var opts= $.extend(defaults , options); 
	return this.each(function () { 
			//var bt_html="<p><a href=\"javascript:void(0)\" > 显示 高级查询</a></p>";
			$this=$(this);
			if(opts.isShow){
				$this.css({display:"block"});
				$this.after("<p><a href=\"javascript:void(0)\" onclick=\"showhidden(this)\" > hidden search</a></p>");
			}else{
				$this.css({display:"none"});
				$this.after("<p><a href=\"javascript:void(0)\" onclick=\"showhidden(this)\" > show search</a></p>");
			}
			
		}); 
	};	
	

})(jQuery);