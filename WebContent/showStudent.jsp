<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>showStudent</title>
</head>
<body>
		<h2>学生信息</h2>
		
	<div style="float: left">
		<form method="post" action="${pageContext.request.contextPath}/getStudent">
			<input type="submit" value="搜索">
			<label for="id"></label>
			<input type="text" name="id" id="id" value="${id}">
		</form>
	</div>
	<input type="button" value="添加" style="float:right"
		   onclick="window.location.href='${pageContext.request.contextPath}/addStudent.jsp'">
		<table border="1" width="100%" align="center" cellspacing="0">
		<tr>
			<th>学号</th>
			<th>姓名</th>
			<th>年龄</th>
			<th>QQ</th>
			<th>操作</th>
		<tr>
			<jsp:useBean id="students" scope="request" type="java.util.List"/>
			<c:forEach items="${students}" var="student">
		<tr>
			<td>${student.getId()}</td>
			<td>${student.getName()}</td>
			<td>${student.getAge()}</td>
			<td>${student.getQq()}</td>
			<td>
				<input type="button" value="编辑"
					   onclick="window.location.href='${pageContext.request.contextPath}/editStudent?id=${student.getId()}'">

				<input type="button" value="删除"
					   onclick="window.location.href='${pageContext.request.contextPath}/delStudent?id=${student.getId()}'">

			</td>
		</tr>
		</c:forEach>

	</table>

	
</body>
</html>