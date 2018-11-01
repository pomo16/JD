<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="../common/css/jd.css">
<style type="text/css">
	#product_ul{line-height: 30px;width:600px;}
</style>
<title>修改商品</title>
</head>
<body>
	<h3>修改商品</h3>
	<c:choose>
		<c:when test="${param.flag != null }">
			<div class="jd_font_4">修改商品失败，图片超过2MB或图片格式错误</div>
		</c:when>
	</c:choose>
	<form action="UpdateProductServlet?id=${productDto.id }" method="post" enctype="multipart/form-data">
	<input type="hidden" name="id" value="${productDto.id }"/>
	<input type="text" name="picture" value="${productDto.picture }"/>
		<ul id="product_ul">
			<li><span>商品编号:</span><input type="text" name="productCode" value="${productDto.productCode }"/></li>
			<li><span>商品名称:</span><input type="text" name="productName" value="${productDto.productName }"/></li>
			<li><span style="margin-left:9px">价格(&yen;):</span><input type="text" name="price" value="${productDto.price }"/></li>
			<li><span style="margin-left:2px">重量(kg):</span><input type="text" name="weight" value="${productDto.weight }"/></li>
			<li><span style="margin-left:24px">产地:</span><input type="text" name="place" value="${productDto.place }"/></li>
			<li><span style="margin-left:24px;vertical-align:top;">描述:</span><textarea cols="50" rows="4" name="description">${productDto.description }</textarea></li>
			<c:choose>
				<c:when test="${productDto.picture != null }">
					<li><img style="width:90px;" src="../common/image/product/${productDto.picture }?${time }"></li>
				</c:when>
			</c:choose>
			<li><span>重新上传图片：</span><input type="file" name="pictureUpdate"/><span>只能上传2MB以内的jpg、png图片</span></li>
			<li style="text-align:center;">
				<input type="submit" value="添加"/>
				<input type="reset" value="重置"/>
				<a href="QueryProductServlet?page=0" class="jd_link_5">取消</a>
			</li>
		</ul>
	</form>
</body>
</html>