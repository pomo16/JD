<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册成功</title>
</head>
<body>
	<img src="../common/image/logo.png"/>
	<h1>${param.userName }注册成功</h1>
	<h1>姓名：${param.userName }</h1>
	<h1>手机：${param.phone }</h1>
	<h1>验证码：${param.validateCode }</h1>
</body>
</html>