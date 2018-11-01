<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="../common/css/jd.css">
<title>京东后台管理页面</title>
<style type="text/css">
	.manager_header{width:100%;border-bottom: 1px solid #ccc;}
	.left_menu{width:120px;float:left;padding:10px;}
	.right_content{width:1000px;height:450px;float:left;border-left:1px solid #ccc;padding:10px;}
	.content_frame{width:100%;height:100%;border:none;}
</style>
</head>
<body>
	<div class="page">
		<div class="manager_header">
			<img src="../common/image/logo.png" style="width:120px;height:40px;"/>
			<span class="jd_font_2" style="margin-left:20px;vertical-align: super;">京东后台管理</span>
			<a href="../maintain/ExitServlet" class="jd_link_3" style="float:right;margin:15px 15px 0px 0px;">退出</a>
			<span style="float:right;margin:15px 15px 0px 0px;">${userInfoDto.userName },您好。</span>
		</div>
		<div class="main" style="border-bottom: 1px solid #ccc;overflow:hidden;">
			<div class="left_menu">
				<a href="QueryUserServlet?page=0" target="content_frame" class="jd_link_2">用户管理</a>
				<br>
				<a href="QueryProductServlet?page=0" target="content_frame" class="jd_link_2">商品管理</a>
			</div>
			<div class="right_content">
				<iframe src="QueryUserServlet?page=0" class="content_frame" name="content_frame"></iframe>
			</div>
		</div>
	</div>
	<div class="footer">
		<div class="copyright_div">Copyright &copy; 2004-2018  京东JD.com 版权所有</div>
	</div>
</body>
</html>