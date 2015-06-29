$(function() {

	//验证用户名
	$("#ctl00_CenterContent_ClientName")
			.blur(
					function() {
						var patrn = /[`~!@#$%^&*()_+<>?:"{},.\/;'[\]]/im;
						var name = $("#ctl00_CenterContent_ClientName").val();

						if (name == "") {
							$("#spanName").html(
									"<font color=green>用户名不能为空</font>");
							return;
						}
						if (patrn.exec(name)) {
							$("#spanName").html(
									"<font color=green>包含特殊字符</font>");
							return;
						}
						if (name.length > 12) {
							$("#spanName").html(
									"<font color=green>用户名长度在4-12位之间</font>");
							return;
						}
						if (name.length < 4) {
							$("#spanName").html(
									"<font color=green>用户名长度在4-12位之间</font>");
							return;
						}
						$
								.post(
										"/xjd-test/userRegisterController/ajax_getNamecheckvalidate",
										{
											"userName" : name
										},
										function(item) {
											if (item == "false") {
												$("#spanName")
														.html(
																"<font color=green>用户名重复</font>");
											} else {
												$("#spanName").html("");
											}
										});
					});

	//验证邮箱
	$("#ctl00_CenterContent_E_Mail")
			.blur(
					function() {

						var reg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
						//var reg = ("^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$");
						var Email = $("#ctl00_CenterContent_E_Mail").val();
						if (Email == "") {
							$("#spanEmail").html(
									"<font color=green>邮箱不能为空</font>");
							return;
						}
						if (!reg.test(Email)) {
							$("#spanEmail").html(
									"<font color=green>邮箱格式不正确</font>");
							return;
						}

						$
								.post(
										"/xjd-test/userRegisterController/ajax_getEmailvalidate",
										{
											"email" : Email
										},
										function(item) {

											if (item == "false") {
												$("#spanEmail")
														.html(
																"<font color=green>邮箱重复</font>");
												$("#spansEmails").html("");
											} else {
												$("#spanEmail").html("");
												$("#spansEmails").html("<font color=green>邮箱不存在</font>");
											}
										});

					});
	//验证手机号
	$("#ctl00_CenterContent_phone").blur(function() {
		var phone = $("#ctl00_CenterContent_phone").val();
		var reg = /^0{0,1}(13[0-9]|145|15[7-9]|153|156|18[0-9])[0-9]{8}$/i;
		if (phone == "") {
			$("#spanPhone").html("<font color=green>手机号不能为空</font>");
			return;
		}
		if (!reg.test(phone)) {
			$("#spanPhone").html("<font color=green>请输入11位的手机号</font>");
			return;
		} else {
			$("#spanPhone").html("");
		}
	});
	//验证密码
	$("#ctl00_CenterContent_Password").blur(function() {
		var pwd = $("#ctl00_CenterContent_Password").val();
		if (pwd == "") {
			$("#spanPwd").html("<font color=green>密码不能为空</font>");
			return;
		}
		if (pwd.length < 4) {
			$("#spanPwd").html("<font color=green>请输入4到18位之间的密码</font>");
			return;
		}
		if (pwd.length > 18) {
			$("#spanPwd").html("<font color=green>请输入4到18位之间的密码</font>");
			return;
		} else {
			$("#spanPwd").html("");
		}
	});
	//验证确认密码
	$("#ctl00_CenterContent_PasswordCheck").blur(function() {
		var pwd = $("#ctl00_CenterContent_Password").val();
		var true_pwd = $("#ctl00_CenterContent_PasswordCheck").val();
		if (true_pwd == "") {
			$("#spanTrue_Pwd").html("<font color=green>确认密码不能为空</font>");
			return;
		}
		if (pwd != true_pwd) {
			$("#spanTrue_Pwd").html("<font color=green>确认密码和密码不相同</font>");
			return;
		} else {
			$("#spanTrue_Pwd").html("");
		}
	});
});

function MySubmit() {
	var ClientName = document.getElementById("ctl00_CenterContent_ClientName");
    var prtrn = /^[a-zA-Z]{1}([a-zA-Z0-9]|[._]){4,19}$/;
	if (ClientName.value == "") {
		alert("请输入用户名。");
		
		return false;
	}

	

	var E_Mail = document.getElementById("ctl00_CenterContent_E_Mail");
	var reg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
	if (E_Mail.value == "") {
		alert("请输入电子邮件。");
		
		return false;
	}

	if (!reg.test(E_Mail.value)) {
		alert("邮箱格式不正确");
		
		return false;
	}
	var Password = document.getElementById("ctl00_CenterContent_Password");
	var szTemp = Password.value;
	if (szTemp == "") {
		alert("请输入密码。");
		
		return false;
	}

	if (szTemp.length < 4) {
		alert("请至少输入4个(含)以上字符的密码。");
		
		return false;
	}

	var PasswordCheck = document
			.getElementById("ctl00_CenterContent_PasswordCheck");
	if (PasswordCheck.value == "") {
		alert("请输入确认密码。");
		
		return false;
	}

	if (Password.value != PasswordCheck.value) {
		alert("确认密码跟密码不一致");
		
		return false;
	}

	var Reg_Check = document.getElementById("ctl00_CenterContent_Reg_Check");
	if (!Reg_Check.checked) {
		alert("请选择接受“新经典阅读俱乐部章程”");
		return false;
	}

	return true;
}
