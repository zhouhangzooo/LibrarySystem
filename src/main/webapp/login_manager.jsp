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
	<h4>管理员登录页面</h4>

	<div>
		<form>
			工作号：<input id="id" name="id" maxlength="18"><br> 密码：&#12288;<input
				id="password" name="password" type="password" maxlength="18"><br>
		</form>

		<div style="display: flex;margin-top: 20px">
			<button id="loginButton" type="submit">登录</button>
			<form action="register_manager.jsp" style="margin-left: 10px;">
				<input type="submit" value="管理员注册">
			</form>
			<form action="login.jsp" style="margin-left: 10px;">
				<input type="submit" value="学生入口">
			</form>
		</div>
		
		<p id="info" style="font-size: 14px; color: red"></p>
	</div>

	<script type="text/javascript">
		$("#loginButton").click(
				function() {
					var id = $("#id").val(); //jqury val()方法返回元素value属性
					var password = $("#password").val();

					if (id == '') {
						$("#info").text("提示：工作号不能为空");
					} else if (password == '') {
						$("#info").text("提示：密码不能为空");
					} else {
						$.ajax({
							type : "POST",
							url : "/books/ManagerLoginServlet",
							data : {
								id : id,
								password : password
							},
							dataType : "json",
							success : function(data) {
								if (data.code == "000000") {
									//$("#info").text("提示:登陆成功");
									<% session.setAttribute("isManager",true); %>
									window.location.href = "home_manager.jsp?id="
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