<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新经典平台</title>
 		<!-- css -->
	<#assign basePath=request.contextPath >
	<script type="text/javascript" src="${basePath}/static/plug/jquery/jquery-1.8.3.min.js"></script>
	<link rel="stylesheet" type="text/css" href="${basePath}/static/css/login.css">

</head>
<body>
	<form id="ff" method="post" action="./login">
	<div class="context">
    <div class="top">
        	<div class="logo fleft"></div>
            <div class="title fleft">
            	<h1>新经典综合管理平台v.1.0.0</h1>
            <p><img src="${basePath}/static/images/login/login-423-4.png"></p>
            <p><img src="${basePath}/static/images/login/title-311-28.png"/></p> 
		</div>
        </div>
        <div class="bottom">
        	<div class="input fleft">
            	<font color="#FFFFFF">用户名：</font><input autofocus="autofocus" name="loginName" type="text" value="admin"></input><br/>
                <font color="#FFFFFF">密&nbsp 码：</font><input name="loginPwd" type="password" value="admin"></input>
            </div>
            <div class="button fleft">
            	<a href="javascript:$('#ff').submit()">
	            	<div>
	                	<font color="#FFFFFF">登&nbsp 录</font>
	                </div>
                </a>
            </div>
            <div class="checked fleft">
                <input name="" type="checkbox" value="" checked="checked" />
                <font color="#424758">Remember  me  on  this  computer</font>
            </div>
            <div class="one fleft">
                <div><a href="#"><font color="#424758">忘记密码？</font></a></div>
            </div>
      </div>
      </div>
    </div>
    </form>

<script type="text/javascript">
$(function(){   
	$('#kaptchaImage').click(function () {   
        $(this).attr('src', './kaptcha?' + Math.floor(Math.random()*100) );   
          });
   });    
function getCode(){
	var kaptchaImage = document.getElementById("kaptchaImage");
	kaptchaImage.src = './kaptcha?' + Math.floor(Math.random()*100);
}
function keyEnter(event){   
    if   (event.keyCode   ==   13)   { 
    	$('#ff').submit();
    }   
} 
document.onkeydown =keyEnter; 
</script>
</body>
</html>
