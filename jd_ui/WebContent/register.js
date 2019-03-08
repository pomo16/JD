$(function(){
	/*$("#userName").blur(function(){
		$.get("maintain/CheckUserNameServlet",{userName:$("#userName").val()},function(data){
			if(data=="ok"){
				$("#userNameMsg").html("该用户可以注册");
				$("#userNameMsg").css("color","green");
				$("#registerBt").attr("disabled", false);
			} else {
				$("#userNameMsg").html("该用户已被注册");
				$("#userNameMsg").css("color","red");
				$("#registerBt").attr("disabled", true);
			}
		});
	});
	
	$("#registerBt").click(function(){
//		var userName = $("#userName").val();
//		var registerData = {userName:userName, password:$("#password").val(), phone:$("#phone").val()};
		$.post("maintain/RegisterServlet", $("#registerForm").serialize(), function(data){
			if(data=="ok"){
				alert($("#userName").val() + "注册成功");
			}
		});
	});*/
	
	$("#password").keyup(function() {
		if(/^(?![^0-9]+$)(?![^a-zA-Z]+$)(?![0-9a-zA-Z]+$)/.test($("#password").val())){
			$("#passwordRight").html("你的密码很安全");
			$("#passwordRight").css("color","#43c75a");
		}else if(/^(?![0-9]+$)(?![a-zA-Z]+$)(?![^0-9a-zA-Z]+$)/.test(password.value)){
			$("#passwordRight").html("安全强度适中，可以使用三种以上的组合来提高安全强度");
			$("#passwordRight").css("color","#cfdaed");
		}else {
			$("#passwordRight").html("");
		}
	});
	
	$.colorbox({
		href:"agreement.html",
		width:"80%",
		height:"80%",
		iframe:true,
		overlayClose:false,
		close:"&times;",
		opacity:0.5,
		onClosed:function(){
			window.location = "login.html";
		}
	});
	
	$("#registerForm").validate({
		submitHandler: function () {
			$.post("maintain/RegisterServlet", $("#registerForm").serialize(), function(data){
				if(data=="ok"){
					$.prompt($("#userName").val() + "注册成功", {
						buttons: { "继续注册": true, "登录": false },
						submit: function(e,v,m,f){
							if(v == false){
								window.location = "login.html";
							}else{
								window.location = "register.html";
							}
						}
					});
				}
			});
		},
		rules:{
			userName:{
				required: true,
				rangelength: "[4,12]",
				notNum: true,
				userNameRule: true,
				remote: "maintain/CheckUserNameServlet"
			},
			password:{
				required: true,
				rangelength: "[6,20]",
				passwordRule: true
			},
			repassword:{
				required: true,
				equalTo:"#password"
			},
			phone:{
				required: true,
				rangelength: "[11,11]",
				digits: true,
				phoneRule: true
			},
			validateCode:{
				required: true,
				rangelength: "[4,4]",
				validateCodeRule: true
			}
		},
		messages:{
			userName:{
				required: "用户名不能为空",
				rangelength: "长度只能在4~12个字符之间",
				notNum: "用户名不能是纯数字，请重新输入！",
				userNameRule: "格式错误，仅支持汉字、字母、数字、“-”、“_”的组合",
				remote: "该用户名已经注册"
			},
			password:{
				required: "密码不能为空",
				rangelength: "长度只能在6~20个字符之间",
				passwordRule: "有被盗风险，建议使用字母、数字和符号两种及以上组合"
			},
			repassword:{
				required: "确认密码不能为空",
				equalTo: "两次密码输入不一致"
			},
			phone:{
				required: "手机不能为空",
				rangelength: "手机号必须为11位",
				digits: "手机号必须由数字组成",
				phoneRule: "手机号格式错误"
			},
			validateCode:{
				required: "验证码不能为空",
				rangelength: "验证码必须为4位",
				validateCodeRule: "验证码必须由数字、字母组成"
			}
		},
		errorPlacement: function ( error, element) {
			error.appendTo(element.parent().next());
		},
		errorElement: "span",
		success: function ( label, element) {
			//$(element).next().html("ok");
		},
		highlight: function ( element, errorClass, validClass ) {
			if($(element).attr("id")=="validateCode"){
				$(element).next().next().html("<i class='fas fa-times fa-lg text-danger'></i>");	
			} else {
				$(element).next().html("<i class='fas fa-times fa-lg text-danger'></i>");		
			}
		},
		unhighlight: function ( element, errorClass, validClass ) {
			if($(element).attr("id")=="validateCode"){
				$(element).next().next().html("<i class='fas fa-check fa-lg text-success'></i>");	
			} else {
				$(element).next().html("<i class='fas fa-check fa-lg text-success'></i>");		
			}
		}
	});
});

$.validator.addMethod( "notNum", function( value, element ) {
	return this.optional( element ) || !/^\d+$/.test( value );
});

$.validator.addMethod( "userNameRule", function( value, element ) {
	return this.optional( element ) || /^[\u4e00-\u9fa5\w-]+$/.test( value );
});

$.validator.addMethod( "passwordRule", function( value, element ) {
	return this.optional( element ) || !/^([0-9]+|[a-zA-Z]+|[^0-9a-zA-Z]+)$/.test( value );
});

$.validator.addMethod( "phoneRule", function( value, element ) {
	return this.optional( element ) || /^1[38][0-9]{9}|15[012356789][0-9]{8}|14[57][0-9]{8}|17[678][0-9]{8}$/.test( value );
});

$.validator.addMethod( "validateCodeRule", function( value, element ) {
	return this.optional( element ) || /^[0-9a-zA-Z]+$/.test( value );
});