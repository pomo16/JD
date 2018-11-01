<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="../common/css/jd.css">
<style type="text/css">
	#product_ul{line-height: 30px;width:600px;}
</style>
<script type="text/javascript">
	function uploadError() {
		var url = window.location.href;
		if(url.indexOf("flag=uploadError") != -1) {
			var errorMessage = document.getElementById("errorMessage");
			errorMessage.style.display = "block";
		}
	}
</script>
<title>添加商品</title>
</head>
<body onload="loginError()">
	<h3>添加商品</h3>
	<div id="errorMessage" class="jd_font_4" style="display:none">添加商品失败，图片超过2MB或图片格式错误</div>
	<form action="AddProductServlet" method="post" enctype="multipart/form-data">
		<ul id="product_ul">
			<li><span>商品编号:</span><input type="text" name="productCode"/></li>
			<li><span>商品名称:</span><input type="text" name="productName"/></li>
			<li><span style="margin-left:9px">价格(&yen;):</span><input type="text" name="price"/></li>
			<li><span style="margin-left:2px">重量(kg):</span><input type="text" name="weight"/></li>
			<li><span style="margin-left:24px">产地:</span><input type="text" name="place"/></li>
			<li><span style="margin-left:24px;vertical-align:top;">描述:</span><textarea cols="50" rows="4" name="description"></textarea></li>
			<li><span>图片上传：</span><input type="file" name="picture"/><span>只能上传2MB以内的jpg、png图片</span></li>
			<li style="text-align:center;">
				<input type="submit" value="添加"/>
				<input type="reset" value="重置"/>
				<a href="QueryProductServlet?page=0" class="jd_link_5">取消</a>
			</li>
		</ul>
	</form>
</body>
</html>