<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="../common/css/jd.css">
<title>商品管理</title>
<script type="text/javascript">
	function deleteProduct(id, productName, picture){
		if(confirm("你确定要删除【" + productName + "】商品信息吗？")){
			window.location = "DeleteProductServlet?id=" + id + "&picture=" + picture;	
		}
	}
</script>
</head>
<body>
	<div class="jd_font_2">商品信息表</div>
	<a href="addProduct.jsp" class="jd_link_5">添加商品</a>
	<table width="1000" border="0" class="jd_tb_1">
		<tr>
			<th width="60" class="jd_td_1">序号</th>
			<th width="100" class="jd_td_1">商品编号</th>
			<th width="100" class="jd_td_1">商品名称</th>
			<th width="80" class="jd_td_1">价格(&yen;)</th>
			<th width="80" class="jd_td_1">重量</th>
			<th width="80" class="jd_td_1">产地</th>
			<th width="300" class="jd_td_1">描述</th>
			<th width="100" class="jd_td_1">图片</th>
			<th width="100" class="jd_td_1">操作</th>
		</tr>
		<c:forEach items="${productList }" varStatus="status" var="productDto">
			<tr>
				<td align="center" class="jd_td_1">${status.index + 1 + param.page * rows }</td>
				<td class="jd_td_1">${productDto.productCode }</td>
				<td class="jd_td_1">${productDto.productName }</td>
				<td class="jd_td_1">${productDto.price }</td>
				<td class="jd_td_1">${productDto.weight }</td>
				<td class="jd_td_1">${productDto.place }</td>
				<td class="jd_td_1">${productDto.description }</td>
				<td class="jd_td_1">
					<c:choose>
						<c:when test="${productDto.picture != null }">
							<img style="width:90px;" src="../common/image/product/${productDto.picture }?${time }">
						</c:when>
					</c:choose>
				</td>
				<td align="center" class="jd_td_1">
					<a class="jd_link_5" href="ToUpdateProductServlet?id=${productDto.id }">修改</a>
					<a class="jd_link_5" href="javascript:deleteProduct(${productDto.id }, '${productDto.productName }', '${productDto.picture }')">删除</a>
				</td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan = "9" align="center">
				第${param.page + 1 }页/共${totalPage }页
				<a href="QueryProductServlet?page=0" class="jd_link_5">首页</a>
				<c:choose>
					<c:when test="${param.page-1>=0 }">
						<a href="QueryProductServlet?page=${param.page-1 }" class="jd_link_5">上一页</a>
					</c:when>
					<c:otherwise>
						上一页					
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${param.page+1<totalPage }">
						<a href="QueryProductServlet?page=${param.page+1 }" class="jd_link_5">下一页</a>
					</c:when>
					<c:otherwise>
						下一页					
					</c:otherwise>
				</c:choose>
				<a href="QueryProductServlet?page=${totalPage-1 }" class="jd_link_5">尾页</a>
			</td>
		</tr>
	</table>
</body>
</html>