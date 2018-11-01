<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="../common/css/jd.css">
<title>用户管理</title>
</head>
<body>
	<div class="jd_font_2">用户信息表</div>
	<table width="800" border="0" class="jd_tb_1">
		<tr>
			<th width="60" class="jd_td_1">序号</th>
			<th width="200" class="jd_td_1">用户名</th>
			<th width="400" class="jd_td_1">手机</th>
			<th width="140" class="jd_td_1">操作</th>
		</tr>
		<c:forEach items="${userList }" varStatus="status" var="userInfoDto">
			<tr>
				<td align="center" class="jd_td_1">${status.index + 1 + param.page * rows }</td>
				<td class="jd_td_1">${userInfoDto.userName }</td>
				<td class="jd_td_1">${userInfoDto.phone }</td>
				<td align="center" class="jd_td_1"><a class="jd_link_5" href="QueryLoginInfoServlet?userId=${userInfoDto.id }&page=0">登录详情</a></td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan = "4" align="center">
				第${param.page + 1 }页/共${totalPage }页
				<a href="QueryUserServlet?page=0" class="jd_link_5">首页</a>
				<c:choose>
					<c:when test="${param.page-1>=0 }">
						<a href="QueryUserServlet?page=${param.page-1 }" class="jd_link_5">上一页</a>
					</c:when>
					<c:otherwise>
						上一页					
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${param.page+1<totalPage }">
						<a href="QueryUserServlet?page=${param.page+1 }" class="jd_link_5">下一页</a>
					</c:when>
					<c:otherwise>
						下一页					
					</c:otherwise>
				</c:choose>
				<a href="QueryUserServlet?page=${totalPage-1 }" class="jd_link_5">尾页</a>
			</td>
		</tr>
	</table>
	
</body>
</html>