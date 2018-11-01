// JavaScript Document
function loadDialog(){
	var registerDialog = document.getElementById("register_dialog");
	registerDialog.style.display = "block";
	var bgDiv = document.getElementById("bg_div");
	bgDiv.style.display = "block";
}

function cancelDialog(){
	window.location = "login.html";
}

function agreeDialog(){
	var registerDialog = document.getElementById("register_dialog");
	registerDialog.style.display = "none";
	var bgDiv = document.getElementById("bg_div");
	bgDiv.style.display = "none";
	var userName = document.getElementById("userName")
	userName.focus();
}

function validate(){
	var userName = document.getElementById("userName");
	var userNameMsg = document.getElementById("userNameMsg");
	var userNameSuccess = document.getElementById("userNameSuccess");
	/*if(userName.value == ""){
		//alert("用户名不能为空");
		userNameMsg.innerHTML = "<img src='common/image/error.png'/>用户名不能为空";
		userName.focus();
		return false;
	}else if(userName.value.length<4 || userName.value.length>20){
		//alert("长度只能在4~20个字符之间");
		userNameMsg.innerHTML = "<img src='common/image/error.png'/>长度只能在4~20个字符之间";
		userName.focus();
		return false;
	}else if(!isNaN(userName.value)){
		//alert("用户名不能是纯数字，请重新输入！");
		userNameMsg.innerHTML = "<img src='common/image/error.png'/>用户名不能是纯数字，请重新输入！";
		userName.focus();
		return false;
	}*/
	if(!/^.+$/.test(userName.value)){
		userNameMsg.innerHTML = "<img src='common/image/error.png'/>用户名不能为空";
		userName.focus();
		return false;
	}else if(!/^.{4,20}$/.test(userName.value)){
		userNameMsg.innerHTML = "<img src='common/image/error.png'/>长度只能在4~20个字符之间";
		userName.focus();
		return false;
	}else if(/^\d+$/.test(userName.value)){
		userNameMsg.innerHTML = "<img src='common/image/error.png'/>用户名不能是纯数字，请重新输入！";
		userName.focus();
		return false;
	}else if(!/^[\u4e00-\u9fa5\w-]+$/.test(userName.value)){
		userNameMsg.innerHTML = "<img src='common/image/error.png'/>格式错误，仅支持汉字、字母、数字、“-”、“_”的组合";
		userName.focus();
		return false;
	}
	else{
		//alert("用户名格式正确");
		userNameSuccess.style.display = "inline";
		userNameMsg.innerHTML = "&nbsp;";
	}
	
	var password = document.getElementById("password");
	var passwordMsg = document.getElementById("passwordMsg");
	var passwordSuccess = document.getElementById("passwordSuccess");
	passwordMsg.style.color = "#ff8801";
	if(password.value == ""){
		//alert("密码不能为空");
		passwordMsg.innerHTML = "<img src='common/image/error.png'/>密码不能为空";
		password.focus();
		return false;
	}else if(password.value.length<6 || password.value.length>20){
		//alert("长度只能在6~20个字符之间");
		passwordMsg.innerHTML = "<img src='common/image/error.png'/>长度只能在6~20个字符之间";
		password.focus();
		return false;
	}else if(/^([0-9]+|[a-zA-Z]+|[^0-9a-zA-Z]+)$/.test(password.value)){
		passwordMsg.innerHTML = "<img src='common/image/password1.png'/>有被盗风险，建议使用字母、数字和符号两种及以上组合";
		password.focus();
		return false;
	}else if(/^(?![^0-9]+$)(?![^a-zA-Z]+$)(?![0-9a-zA-Z]+$)/.test(password.value)){
		passwordMsg.innerHTML = "<img src='common/image/password3.png'/>你的密码很安全";
		passwordSuccess.style.display = "inline";
		passwordMsg.style.color = "#43c57a";
	}else if(/^(?![0-9]+$)(?![a-zA-Z]+$)(?![^0-9a-zA-Z]+$)/.test(password.value)){
		passwordMsg.innerHTML = "<img src='common/image/password2.png'/>安全强度适中，可以使用三种以上的组合来提高安全强度";
		passwordSuccess.style.display = "inline";
		passwordMsg.style.color = "#cfdaed";
	}

	var repassword = document.getElementById("repassword");
	var repasswordMsg = document.getElementById("repasswordMsg");
	var repasswordSuccess = document.getElementById("repasswordSuccess");
	if(repassword.value == ""){
		//alert("确认密码不能为空");
		repasswordMsg.innerHTML = "<img src='common/image/error.png'/>确认密码不能为空";
		repassword.focus();
		return false;
	}else if(password.value != repassword.value){
		//alert("两次密码输入不一致");
		repasswordMsg.innerHTML = "<img src='common/image/error.png'/>两次密码输入不一致";
		repassword.focus();
		return false;
	}else{
		//alert("确认密码格式正确");
		repasswordSuccess.style.display = "inline";
		repasswordMsg.innerHTML = "&nbsp;";
	}

	var phone = document.getElementById("phone");
	var phoneMsg = document.getElementById("phoneMsg");
	var phoneSuccess = document.getElementById("phoneSuccess");
	if(phone.value == ""){
		//alert("手机不能为空");
		phoneMsg.innerHTML = "<img src='common/image/error.png'/>手机不能为空";
		phone.focus();
		return false;
	}else if(phone.value.length != 11){
		//alert("手机号必须为11位");
		phoneMsg.innerHTML = "<img src='common/image/error.png'/>手机号必须为11位";
		phone.focus();
		return false;
	}else if(isNaN(phone.value)){
		//alert("手机号必须由数字组成");
		phoneMsg.innerHTML = "<img src='common/image/error.png'/>手机号必须由数字组成";
		phone.focus();
		return false;
	}else if(!/^1[38][0-9]{9}|15[012356789][0-9]{8}|14[57][0-9]{8}|17[678][0-9]{8}$/.test(phone.value)){
		phoneMsg.innerHTML = "<img src='common/image/error.png'/>手机号格式错误";
		phone.focus();
		return false;
	}else{
		//alert("手机格式正确");
		phoneSuccess.style.display = "inline";
		phoneMsg.innerHTML = "&nbsp;";
	}

	var validateCode = document.getElementById("validateCode");
	var validateCodeMsg = document.getElementById("validateCodeMsg");
	if(validateCode.value == ""){
		//alert("验证码不能为空");
		validateCodeMsg.innerHTML = "<img src='common/image/error.png'/>验证码不能为空";
		validateCode.focus();
		return false;
	}else if(validateCode.value.length != 4){
		//alert("验证码必须为4位");
		validateCodeMsg.innerHTML = "<img src='common/image/error.png'/>验证码必须为4位";
		validateCode.focus();
		return false;
	}else if(!/^[0-9a-zA-Z]+$/.test(validateCode.value)){
		//alert("验证码必须由数字组成");
		validateCodeMsg.innerHTML = "<img src='common/image/error.png'/>验证码必须由数字、字母组成";
		validateCode.focus();
		return false;
	}
	else{
		//alert("验证码格式正确");
		validateCodeMsg.innerHTML = "<img src='common/image/correct.png'/>验证码格式正确";
		validateCodeMsg.style.color = "#43e75a";
	}

	return true;
}

//动态获取验证码
function getValidateCode(){
	var validateCodeImg = document.getElementById("validateCodeImg");
	validateCodeImg.setAttribute("src", "maintain/ValidateCodeServlet?" + new Date().getTime());
}