$(function(){
	$("#loginBt").click(function(){
		$.ajax({
			url : "manager/userInfo/login.do",
			data : $("#loginForm").serialize(),
			type : "POST",
			success : function(result) {
				if(result.extend.loginState == "ok"){
					window.location = "manager/main.html";
				}else if(result.extend.loginState == "loginCodeError"){
					$("#errorMessage").html("账号错误，请重新登录。")
				}else if(result.extend.loginState == "passwordError"){
					$("#errorMessage").html("密码错误，请重新登录。")
				}
			}
		});
	});
});