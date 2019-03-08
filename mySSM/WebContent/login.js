$(function(){
	$("#loginBt").click(function(){				
		$.post("manager/userInfo/login.do",$("#loginForm").serialize(),function(data){
			// console.log(data);
			if(data.loginMessage=="ok"){
				window.location = "manager/main.html";
			}else if(data.loginMessage == "loginCodeError"){
				$("#errorMessage").html("账号错误，请重新登录。")
			}else if(data.loginMessage == "passwordError"){
				$("#errorMessage").html("密码错误，请重新登录。")
			}
		});
	});
});
