<%@ page import="java.sql.*" language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>注册</title>
</head>
<body>
	<div style="text-align: center;">
        <h1>添加学生</h1>
        <form method="get" action="${pageContext.request.contextPath}/addStudent">
				<label for="id">学号：</label>
				<input type="text" name="id" id="id"><br><br>

				<label for="name">姓名：</label>
				<input type="text" name="name" id="name"><br><br>

				<label for="age">年龄：</label>
				<input type="text" name="age" id="age"><br><br>

				<label for="QQ">QQ：</label>
				<input type="text" name="QQ" id="QQ"><br><br>
			<br>
			<input type="submit" value="保存">
			<input type="button" value="返回"
				   onclick="window.location.href='${pageContext.request.contextPath}/showStudent'">
		</form>
	</div>
</body>
</html>