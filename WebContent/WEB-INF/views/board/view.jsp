<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>mysite</title>
	<meta http-equiv="content-type" content="text/html; charset=utf-8">
	<link href="/mysite/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">

			<c:import url="/WEB-INF/views/includes/header.jsp"/>
			<c:import url="/WEB-INF/views/includes/navigation.jsp"/>
		
		<div id="content">
		<div id="board" class="board-form">
			<form action="bbs" method="get">
			<input type="hidden" name="a" value="view">
			<input type='hidden'name="no" value="${boardList.no }">
				<table class="tbl-ex">
					<tr>
						<th colspan="2">글보기</th>
					</tr>
					<tr>
						<td class="label">제목</td>
						<td>${boardVo.title }</td>
					</tr>
					<tr>
						<td class="label">내용</td>
						<td>
							<div class="view-content">
								${boardVo.contetnt }<br>
							</div>
						</td>
					</tr>
				</table>
				</form>
				<div class="bottom">
					<a href="/mysite/bbs?a=list">글목록</a>
					<a href="/mysite/bbs?a=modify">글수정</a>
				</div>
			</div>
		</div>

		<c:import url="/WEB-INF/views/includes/footer.jsp"/>
	</div>
</body>
</html>