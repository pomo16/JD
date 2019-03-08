$(function(){
	//协议框
	$.colorbox({
		href:"agreement.html",
		width:"80%", 
		height:"80%",
		iframe:true,
		overlayClose:false,
		close:"×",
		opacity:0.5,
		onClosed:function(){
			window.location = "login.html";
		}
	});
	
	//验证码刷新按钮
	$(".createValidateCode").click(function(){
		$("#validateCode").val("");
		$("#validateCodeMsg").html("");
		$("img.createValidateCode").attr("src","manager/validateCode/createValidateCode.do?" + new Date().getTime());
	});
	
	$("#registerBt").click(function(){
		if($("#registerForm").valid()){
			$.ajax({
				url : "manager/userInfo/addUserInfo.do",
				data : $("#registerForm").serialize(),
				type : "POST",
				success : function(result) {
					//console.log(result);
					if(result.code == 200){
						var errorStr = "非法内容：" + "<br/>";
						for( field in result.extend.errorFields){
							errorStr = errorStr + "<i class='fas fa-exclamation-circle fa-lg text-danger'></i>"
												+ field + ":" + result.extend.errorFields[field]
												+ "<br/>";
						}
						$.prompt( errorStr, {
							title: "后端校验未通过，请刷新后重新提交合法表单！",
							buttons: { "确认": true},
							submit: function(e,v,m,f){
								window.location = "register.html"
							}
						});
					} else {
						$.prompt($("#userName").val() + "注册成功！", {
							buttons: { "继续注册": true, "登录": false },
							submit: function(e,v,m,f){
								if(v==false){
									window.location = "login.html";
								}else{
									window.location = "register.html";
								}
							}
						});	
					}
				}
			});
		}
	});
	
	$("#registerForm").validate({
		rules:{
			userName:{
				required:true,
				rangelength:"[4,20]",
				notNum:true,
				userNameRule:true,
				remote:"manager/userInfo/userNameValidate.do"
			},
			password:{
				required:true,
				rangelength:"[6,20]",
				passwordRule:true
			},
			repassword:{
				required:true,
				equalTo:"#password"
			},
			phone:{
				required:true,
				rangelength:"[11,11]",
				digits:true,
				phoneRule:true
			},
			validateCode:{
				required:true,
				rangelength:"[4,4]",
				validateCodeRule:true,
				remote:"manager/validateCode/checkValidateCode.do"
			}
		},
		messages:{
			userName:{
				required:"用户名不能为空",
				rangelength:"长度只能在4-20个字符之间",
				notNum:"用户名不能是纯数字，请重新输入！",
				userNameRule:"格式错误，仅支持汉字、字母、数字、“-”、“_”的组合",
				remote:"该用户名已经注册"	
			},
			password:{
				required:"密码不能为空",
				rangelength:"长度只能在6-20个字符之间",
				passwordRule:"有被盗风险,建议使用字母、数字和符号两种及以上组合"
			},
			repassword:{
				required:"确认密码不能为空",
				equalTo:"两次密码输入不一致"
			},
			phone:{
				required:"手机不能为空",
				rangelength:"手机号必须为11位",
				digits:"手机号必须由数字组成",
				phoneRule:"手机号格式错误"
			},
			validateCode:{
				required:"验证码不能为空",
				rangelength:"验证码必须为4位",
				validateCodeRule:"验证码必须由数字、字母组成",
				remote:"验证码输入错误"
			}
		},
		errorPlacement: function ( error, element ) {
			error.appendTo(element.parent().next());
		},
		errorElement: "span",
		success: function ( label, element ) {
			//$(element).next().html("ok");
		},
		highlight: function ( element, errorClass, validClass ) {
			if($(element).attr("id")=="validateCode"){
				$(element).next().next().html("<i class='fas fa-times fa-lg text-danger'></i>");
			}else{
				$(element).next().html("<i class='fas fa-times fa-lg text-danger'></i>");
			}			
		},
		unhighlight: function ( element, errorClass, validClass ) {
			if($(element).attr("id")=="validateCode"){
				$(element).next().next().html("<i class='fas fa-check fa-lg text-success'></i>");
			}else{
				$(element).next().html("<i class='fas fa-check fa-lg text-success'></i>");
			}
		}
	});
});
	
$("#password").keyup(function(){
	if(/^(?![^0-9]+$)(?![^a-zA-Z]+$)(?![0-9a-zA-Z]+$)/.test($("#password").val())){
		$("#passwordRight").html("你的密码很安全");
		$("#passwordRight").css("color","#43c75a");
	}else if(/^(?![0-9]+$)(?![a-zA-Z]+$)(?![^0-9a-zA-Z]+$)/.test($("#password").val())){
		$("#passwordRight").html("安全强度适中，可以使用三种以上的组合来提高安全强度");
		$("#passwordRight").css("color","#cfdaed");
	}else{
		$("#passwordRight").html("");
	}
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