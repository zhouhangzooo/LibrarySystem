<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册页面</title>
</head>
<body>
	<script src="./static/js/jquery-3.2.1.js"></script>
	<h4>学生注册页面</h4>
	<form>
		学号：<input id="id" maxlength="18"><br> 姓名：<input id="name"
			maxlength="5"><br> 密码：<input id="password"
			type="password" maxlength="18"><br> 性别：<input id="sex"
			maxlength="2"><br> 年龄：<input id="age" maxlength="3"><br>
		专业：<input id="profession" maxlength="15"><br> 年级：<input
			id="grade" maxlength="15"><br>
	</form>

	<button id="registerButton" type="submit" style="margin-top: 20px">注册</button>

	<p id="info" style="font-size: 14px; color: red"></p>

	<script type="text/javascript">
		$("#registerButton").click(function() {
			var id = $("#id").val(); //jqury val()方法返回元素value属性
			var password = $("#password").val();
			var name = $("#name").val();
			var age = $("#age").val();
			var sex = $("#sex").val();
			var grade = $("#grade").val();
			var profession = $("#profession").val();
			if (id == '') {
				$("#info").text("提示:学生ID不能为空");
			} else if (name == '') {
				$("#info").text("提示:姓名不能为空");
			} else if (password == '') {
				$("#info").text("提示:密码不能为空");
			} else {
				$.ajax({
					type : "POST",
					url : "/books/StudentRegisterServlet",
					data : {
						id : id,
						password : password,
						name : name,
						age : age,
						sex : sex,
						grade : grade,
						profession : profession
					},
					dataType : "json",
					success : function(data) {
						if (data.code == "000000") {
							$("#info").text("提示:注册成功");
							setTimeout(function() {
								window.location.href = "login.jsp"
							}, 800);
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