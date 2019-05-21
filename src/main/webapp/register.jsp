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
	<form>
		学生ID：<input id="id"><br> 姓名：<input id="name"><br>
		密码：<input id="password" type="password"><br> 性别：<input id="sex"><br>
		年龄：<input id="age"><br> 专业：<input id="profession"><br>
		年级：<input id="grade"><br>
	</form>

	<p id="info"></p>
	<button id="registerButton" type="submit">注册</button>

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
						$("#info").text(data.code);
						if (data.code == "000000") {
							$("#info").text("提示:注册成功");
							window.location.href = "login.jsp";
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