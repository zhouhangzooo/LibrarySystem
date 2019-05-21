<%@page import="com.book.util.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>图书管理系统</title>
</head>
<body>
	<script src="./static/js/jquery-3.2.1.js"></script>

	<form>
		学生ID：<input id="id" name="id"><br> 密码：<input
			id="password" name="password" type="password"><br>
	</form>

	<p id="info"></p>
	<button id="loginButton" type="submit">提交</button>

	<form action="register.jsp">
		<input type="submit" value="注册">
	</form>

	<script type="text/javascript">
		$("#loginButton").click(
				function() {
					var id = $("#id").val(); //jqury val()方法返回元素value属性
					var password = $("#password").val();

					if (id == '') {
						$("#info").text("提示:学生ID不能为空");
					} else if (password == '') {
						$("#info").text("提示:密码不能为空");
					} else {
						$.ajax({
							type : "POST",
							url : "/books/StudentLoginServlet",
							data : {
								id : id,
								password : password
							},
							dataType : "json",
							success : function(data) {
								$("#info").text(data.code);
								if (data.code == "000000") {
									$("#info").text("提示:登陆成功");
									window.location.href = "home.jsp?id="
											+ data.data.id;
								} else {
									$("#info").text(data.message);
								}
							}
						});
					}
				})
	</script>
</body>
</html>