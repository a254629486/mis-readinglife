
	//页面加载完成后
	$(document).ready(function(){
		var $pres=$("code");
		for(var i=0;i<$pres.length;i++){
			var $pre=$($pres[i]);
			var wds=$pre.html();
			var wds_html=wds;
			//REG
			var reg_zs=new RegExp("<!--","g");
			var reg_zs_right=new RegExp("--&gt;","g");
			var reg_gt=new RegExp(">","g");
			var reg_function=new RegExp("function ","g");

			var reg_yh_left=new RegExp('="',"g");
			var reg_yh_right=new RegExp('"<font',"g");
			//REPLACE
			

			wds=wds.replace(reg_gt,"<font color='#b28850'>&gt;</font>"); 
			wds=wds.replace(reg_zs,"<font color='#999'>&lt;!--"); 
			wds=wds.replace(reg_zs_right,"--&gt;</font>");
			
			var attrs=['class','data-options','href','id','title','rownumbers','style','type','colspan','rowspan','value','pagination','iconcls','plain','sortable'];
			for(var j=0;j<attrs.length;j++){
				var attr=attrs[j];
				var reg_attr=new RegExp(" "+attr,"g");
				wds=wds.replace(reg_attr,"<font color=red > "+attr+"</font>");
			}
			
			var tags=['a','ul','li','div','input','button','span','table','thead','tbody','tr','th','td','p','label','script','body','html'];
			for(var j=0;j<tags.length;j++){
				var tag=tags[j];
				var tag_html="<"+tag;
				var tag_html_end="</"+tag;
				var reg_tag=new RegExp(tag_html,"g");
				var reg_tag_end=new RegExp(tag_html_end,"g");
				if(tag=='script'){
					wds=wds.replace(reg_tag,"<font color='#ec6941' >&lt;"+tag+"</font>");
					wds=wds.replace(reg_tag_end,"<font color='#ec6941' >&lt;/"+tag+"</font>");
				}else{
					wds=wds.replace(reg_tag,"<font color='#996c33' >&lt;"+tag+"</font>");
					wds=wds.replace(reg_tag_end,"<font color='#996c33' >&lt;/"+tag+"</font>");
				}
			}
			
			wds=wds.replace(reg_function,"<font color='#438eb9' >function </font>"); 
			wds=wds.replace(reg_yh_left,"=\"<font color=blue>"); 
			wds=wds.replace(reg_yh_right,"</font>\"<font"); 
			
			$pre.empty();
			$pre.append("<pre>"+wds+"</pre>");
		}
		
	});