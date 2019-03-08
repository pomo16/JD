$(function(){
	$("#loginBt").click(function(){
		$.post("maintain/LoginServlet", $("#loginForm").serialize(), function(data){
			if(data=="ok"){
				window.location = "manager/main.html";
			}else if(data == "userNameError") {
				$("#errorMessage").html("账号错误，请重新登录。")
			}else if(data == "passwordError") {
				$("#errorMessage").html("密码错误，请重新登录。")
			}
		});
	});
});
