//static
var NORTH_HEIGHT=50;
var SOUTH_HEIGHT=20+27;
var WEST_WIDTH=242;
var PAGE_TAB_WIDTH=43;
var TAB_HEIGHT=37;
var WEST_MENU_HEIGHT=29;
//窗口改变大小
$(window).resize(function(){
	resizeHeightWidth();
	$('.easyui-datagrid').datagrid('resize');
});
//页面加载完成后
$(document).ready(function(){
	initHtml();
	resizeHeightWidth();
	$('.easyui-datagrid').datagrid('resize');
	
});

//重置长宽
function resizeHeightWidth(){
	var win_height=$(window).height();
	var win_width=$(window).width();
	

	//设置FORM高度
	$(".part-form").each(function(index){
		var $this=$(this);
		var part_form_height= win_height-40;
		$this.css("height",part_form_height);
	}); 
	//设置左侧菜单高度
	var accordion_height=win_height-NORTH_HEIGHT-SOUTH_HEIGHT-20;
	var $accordion_cons=$(".layout-west").find(".accordion-con");
	$.each($accordion_cons,function(i,$accordion_con){
		$($accordion_con).css("height",accordion_height);
		$.parser.parse($accordion_con);
	});
	
	//$(".easyui-tabs").tabs('resize',{width:400,height:400});
	
	//设置PAGE TAB PANEL高度
	var $page_tabs=$(".page-tabs-panel");
	var page_tab_height=win_height-NORTH_HEIGHT-SOUTH_HEIGHT;
	$.each($page_tabs,function(i,$page_tab){
		$($page_tab).css("height",page_tab_height);
	});
	//设置PAGE TAB IFRAME高度 宽度
	var $tabifms=$(".page-iframes-panel");
	var tabifm_height=win_height-NORTH_HEIGHT-SOUTH_HEIGHT;
	var tabifm_width=win_width-WEST_WIDTH-PAGE_TAB_WIDTH-200;
	$.each($tabifms,function(i,$tabifm){
		$($tabifm).css("height",tabifm_height);
		$($tabifm).css("width",tabifm_width);
	});
	//设置 PAGE TAB IFRAME 标签高度
	var if_height=win_height-NORTH_HEIGHT-SOUTH_HEIGHT-2;
	var if_width=win_width-WEST_WIDTH-PAGE_TAB_WIDTH-200;
	var $tabiframes=$(".tab-iframe");
	for(var i=0;i<$tabiframes.length;i++){
		var $tabiframe=$($tabiframes[i]);
		$tabiframe.attr("height",if_height);
		$tabiframe.attr("width",if_width);	
	}
	var $tabiframes2=$(".tab-iframe-2");
	for(var i=0;i<$tabiframes2.length;i++){
		var $tabiframe=$($tabiframes2[i]);
		$tabiframe.attr("height",if_height);
		$tabiframe.attr("width",win_width);	
	}
	
	
	$.parser.parse();
}
//设置百分比宽度
function getWidth(num){
	return (($(window).width()-20)*num);
}
//自动初始化页面
function initHtml(){
	var $partforms=$("#part-form");
	$(".part-form").each(function(index){
		var $this=$(this);
		var $divs=$this.children("div");
		for(var i=0;i<$divs.length;i++){
			$div=$($divs[i]);
			$div.addClass("part");
		}
	}); 
	
	
	var $parts=$(".part");
	for(var i=0;i<$parts.length;i++){
		var $part=$($parts[i]);
		$part.append("<div style='clear:both'></div>");
		//设置input样式
		var $inputtexts=$part.find("input[type='text']");
		for(var j=0;j<$inputtexts.length;j++){
			$($inputtexts[j]).addClass("input-text");
		}
		//设置select样式
		var $selects=$part.find("select");
		for(var j=0;j<$selects.length;j++){
			$($selects[j]).addClass("select");
		}
		//设置file样式
		var $files=$part.find("input[type='file']");
		for(var j=0;j<$files.length;j++){
			$($files[j]).addClass("input-file");
		}
		
		
		//设置按钮样式事件
		var $buttons=$part.find("input[type='button']");
		for(var j=0;j<$buttons.length;j++){
			$($buttons[j]).addClass("default-button");
			$($buttons[j]).bind("mouseover",function(){
				$(this).removeClass("default-button");
				$(this).addClass("default-button-hover");
			});
			$($buttons[j]).bind("mouseout",function(){
				$(this).removeClass("default-button-hover");
				$(this).addClass("default-button");
			});
		}
		//设置TITLE
		var title=$part.attr("title");
		if(typeof(title) != "undefined"){
			$part.prepend("<div class='part-title'>"+title+"</div>");
		}
	}
}
//选中菜单
function checkMenu(menuid){
	alert();
	//菜单选择
	var $li_checked=$("#"+menuid);
	var $lis=$li_checked.parent().children();
	$.each($lis, function(i,$li){      
      	$($li).removeClass("active");
  	});
	$li_checked.addClass("active"); 
	 
	//tab区选择
	var $tabs_checked=$("#tabs_"+menuid);
	var $tabss=$tabs_checked.parent().children();
	$.each($tabss,function(i,$tabs){
		$($tabs).addClass("hidden");
		$($tabs).removeClass("maintabs");
	});
	$tabs_checked.removeClass("hidden");
	$tabs_checked.addClass("maintabs");
	
	//左侧菜单选择
	var $accordion_checked=$("#accordion_"+menuid);
	var $accordions=$accordion_checked.parent().children();
	$.each($accordions,function(i,$accordion){
		$($accordion).addClass("hidden");
	});
	$accordion_checked.removeClass("hidden");
	$.parser.parse($accordion_checked.parent());
	//换肤
	
	//var $scanCss=$("#scanCss");
	//$scanCss.attr("href",BASE_PATH+"/static/plug/jquery-easyui/themes/"+getScan(menuid)+"/easyui.css");
	
}
function getScan(menuid){
	if(menuid=='1000'){
		return 'default';
	}else if(menuid=='1001'){
		return 'bootstrap';
	}else if(menuid=='1002'){
		return 'metro';
	}else if(menuid=='1003'){
		return 'gray';
	}else {
		return 'black';
	}
}

//选择BODY TAB
function checkPageTab(obj){
	var type=$(obj).attr("type");
	var $ifm_checked=$(obj).parent().parent().next().find("iframe[type='"+type+"']");
	var $ifms=$(obj).parent().parent().next().find("iframe");
	$.each($ifms,function(i,$ifm){
		$($ifm).addClass("hidden");
	});
	$ifm_checked.removeClass("hidden");
	// 这里似乎还是有问题。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。
	//body tab
	var $bodytab_checked=$(obj);
	var $bodytabs=$bodytab_checked.parent().children();
	$.each($bodytabs,function(i,$bodytab){
		$($bodytab).removeClass("checked");
	});
	$bodytab_checked.addClass("checked");
	
}

//创建TAB
function createTab(title,url){
	//var $tabsbar=$("#easyui-tabs");
	var $tabsbar=$(".maintabs");
	
	//不是当前tabbar
	if($tabsbar.length<1){
		window.parent.createTab(title,url);
	//是当前tabbar
	}else{
		var win_height=$(window).height();
		var win_width=$(window).width();
		var $tab=$tabsbar.tabs("getTab",title);
		if(null==$tab){
			var tabifm_height=win_height-NORTH_HEIGHT-SOUTH_HEIGHT-2;
			var tab_height=win_height-NORTH_HEIGHT-SOUTH_HEIGHT;
			var tabifm_width=win_width-WEST_WIDTH-PAGE_TAB_WIDTH;
			var ifm_html="<iframe type=\"list\" class=\"tab-iframe\" src=\""+url+"\"  width=\""+tabifm_width+"\" height=\""+tabifm_height+"\" frameborder=\"no\""+
                     "border=\"0\" marginwidth=\"0\" marginheight=\"0\" scrolling=\"auto\" allowtransparency=\"yes\"></iframe>";
			var bodytabs_html="<li class=\"icons-tab-menu-list checked\" type=\"list\" onclick=\"checkPageTab(this)\"  ><span></span></li>";		 
					 
			var page_tab_iframe_html="<div style=\"height:"+tab_height+"px;\" class=\"page-tabs-panel\"><ul>"+bodytabs_html+"</ul></div>"+
			"<div class=\"page-iframes-panel\">"+ifm_html+"</div>";
			

			$tabsbar.tabs('add',{   
				title:title,   
				content:page_tab_iframe_html,
				selected:true,
				closable:true  
			});  
		}else{
			$tabsbar.tabs("select",title);
			/**
			var ifms=$tabsbar.find(".tabs-panels .page-iframes-panel iframe");
			$.each($ifms,function(i,$ifm){
				$($ifm).addClass("hidden");
			});
			$(ifms[0]).removeClass("hidden");
			var src=$(ifms[0]).attr("src");
			$(ifms[0]).attr("src",src);
			**/
		}
	}
}
//关闭TAB页面
function closeTab(title,loadtitle){
	var $tabsbar=$(".maintabs");
	//不是当前tabbar
	if($tabsbar.length<1){
		window.parent.closeTab(title,loadtitle);
	//是当前tabbar
	}else{
		$(".maintabs").tabs("close",title);
		if($(".maintabs").tabs('exists', loadtitle)){
			//表格重新加载
			$(".maintabs").tabs("select",loadtitle);
			
			
			var $top_tabs=$(".maintabs").find("ul.tabs").children();
			var num=0;
			for(var i=0;i<$top_tabs.length;i++){
				var $top_tab=$($top_tabs[i]);
				if($top_tab.hasClass("tabs-selected")){
					num=i;
					break;
				}
			}
			var $top_tabpanels=$(".maintabs").find(".tabs-panels").children();
			var $top_tabpanel=$($top_tabpanels[num]);
			
			var $pagetab=$top_tabpanel.find("li[type='"+type+"']");
			if($pagetab.length==0){
				var $pageiframes=$top_tabpanel.find("iframe");
				$.each($pageiframes,function(i,$pageiframe){
					$($pageiframe).addClass("hidden");
				});
				
			}
				
			$($pageiframes[0]).removeClass("hidden");
			var src=$($pageiframes[0]).attr("src");
			$($pageiframes[0]).attr("src",src);
		}else{
			//nothing to do
		}
		

	}
}
//创建页面TAB
function createPageTab(url,type){
	var $pagetabs=$(".maintabs").find(".page-tabs-panel");
	//不是当前tabbar
	if($pagetabs.length<1){
		window.parent.createPageTab(url,type);
	//是当前tabbar
	}else{
		var win_height=$(window).height();
		var win_width=$(window).width();
		
		var $top_tabs=$(".maintabs").find("ul.tabs").children();
		var num=0;
		
		for(var i=0;i<$top_tabs.length;i++){
			var $top_tab=$($top_tabs[i]);
			if($top_tab.hasClass("tabs-selected")){
				num=i;
				break;
			}
		}
		var $top_tabpanels=$(".maintabs").find(".tabs-panels").children();
		var $top_tabpanel=$($top_tabpanels[num]);
		
		var $pagetab=$top_tabpanel.find("li[type='"+type+"']");
		if($pagetab.length==0){
			var $pageiframes=$top_tabpanel.find("iframe");
			$.each($pageiframes,function(i,$pageiframe){
				$($pageiframe).addClass("hidden");
			});
			var iframe_height=win_height-NORTH_HEIGHT-SOUTH_HEIGHT-2;
			var tabifm_width=win_width-WEST_WIDTH-PAGE_TAB_WIDTH;
			var iframe_html="<iframe type=\""+type+"\" class=\"tab-iframe\" src=\""+url+"\"  width=\""+tabifm_width+"\" height=\""+iframe_height+"\" frameborder=\"no\" border=\"0\" marginwidth=\"0\" marginheight=\"0\" scrolling=\"auto\" allowtransparency=\"yes\"></iframe>";
			$top_tabpanel.find(".page-iframes-panel").append(iframe_html);
			
			var $pagetabs=$top_tabpanel.find(".page-tabs-panel").find("li");	
			$.each($pagetabs,function(i,$pagetab){
				$($pagetab).removeClass("checked");
			});
			var pagetab_html= "<li class=\"icons-tab-menu-"+type+" checked\" type=\""+type+"\"  onclick=\"checkPageTab(this)\" >"+
                        	"<div onClick=\"closePtab('"+type+"',this)\" class=\"icons-tab-menu-close\"></div><span></span></li>";
			$top_tabpanel.find(".page-tabs-panel").find("ul").append(pagetab_html);

		}else{
			checkPageTab($pagetab);
		}
	}
}
//关闭窗口
function closePtab(type,obj){
	var $this=$(obj);
	var $tabs=$this.parent().parent().parent().find("li");
	var $iframes=$this.parent().parent().parent().next().find("iframe");
	var $iframe=$this.parent().parent().parent().next().find("iframe[type='"+type+"']");
	
	//处理IFRAME
	$iframe.remove();
	$($iframes[0]).removeClass("hidden");
	//处理TAB
	$this.parent().remove();
	$($tabs[0]).addClass("checked");
}
//关闭页面TAB
function closePageTab(type){
	
	var $pagetabs=$(".maintabs").find(".page-tabs-panel");
	//不是当前tabbar
	if($pagetabs.length<1){
		window.parent.closePageTab(type);
	//是当前tabbar
	}else{
		var $top_tabs=$(".maintabs").find("ul.tabs").children();
		var num=0;
		for(var i=0;i<$top_tabs.length;i++){
			var $top_tab=$($top_tabs[i]);
			if($top_tab.hasClass("tabs-selected")){
				num=i;
				break;
			}
		}
		var $top_tabpanels=$(".maintabs").find(".tabs-panels").children();
		var $top_tabpanel=$($top_tabpanels[num]);
		var $pagetab=$top_tabpanel.find("li[type='"+type+"']");
		var $pageiframe=$top_tabpanel.find("iframe[type='"+type+"']");
		
		$top_tabpanel.find("li[type='list']").addClass("checked");
		
		var src=$($top_tabpanel.find("iframe[type='list']")).attr("src");
		$($top_tabpanel.find("iframe[type='list']")).attr("src",src);
		$top_tabpanel.find("iframe[type='list']").removeClass("hidden");
		$pagetab.remove();
		$pageiframe.remove();
	}
	
}


//创建窗口
function openWindow(title,url,width,height,modal){
	var $win=$("#"+title);
	if($win.length==0){
		
	}else{
		 $win.remove();
	}
		var ifm_html="<iframe id=\"ifm_"+title+"\" class=\"tab-iframe\" src=\""+url+"\"  width=\"100%\" height=\""+(height-40)+"\" frameborder=\"no\" border=\"0\" marginwidth=\"0\" marginheight=\"0\" scrolling=\"auto\" allowtransparency=\"yes\"></iframe>";
		$("body").append("<div id="+title+" class='easyui-window'>"+ifm_html+"</div>");
		$("#"+title).window({
			modal:true,
			closed:true,
			width:width,
			height:height,
			title:title,
			resizable:false,
			iconCls:'icon-save'
		});

	$("#"+title).window("open");
}

//关闭窗口
function closeWindow(title){
	var $win=$("#"+title);
	if($win.length<1){
		window.parent.closeWindow(title);
	}else{
		//表格重新加载
		$(".easyui-datagrid").each(function(index){
			var $this=$(this);
			$this.datagrid('reload');  
		});
		$("#"+title).window("close");
	}
}
//创建普通TAB
function buildTab(title,url){
	var $indextab=$("#indextab");
	//不是当前indextab
	if($indextab.length<1){
		window.parent.buildTab(title,url);
	//是当前tabbar
	}else{
		//创建TAB	
		var win_height=$(window).height();
		var win_width=$(window).width();
		var $tab=$indextab.tabs("getTab",title);
		if(null==$tab){
			var tabifm_height=win_height-NORTH_HEIGHT-SOUTH_HEIGHT-2;
			var tabifm_width=win_width;
			var ifm_html="<iframe  class=\"tab-iframe-2\" src=\""+url+"\"  width=\""+tabifm_width+"\" height=\""+tabifm_height+"\" frameborder=\"no\""+
                     "border=\"0\" marginwidth=\"0\" marginheight=\"0\" scrolling=\"auto\" allowtransparency=\"yes\"></iframe>";
			$indextab.tabs('add',{   
				title:title,   
				content:ifm_html,
				selected:true,
				closable:true  
			});  
		}else{
			$indextab.tabs("select",title);
		}
		
	}
}


