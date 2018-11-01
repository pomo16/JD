<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户名已存在</title>
</head>
<body>
	<img src="../common/image/logo.png"/>
	<h1 style="color:red;">${param.userName }，已被注册，请更换用户名并重新注册</h1>
	<a href="../register.html">重新注册</a>
</body>
</html>