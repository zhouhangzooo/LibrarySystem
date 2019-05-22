<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>主页</title>
</head>
	<link rel="stylesheet" href="./static/css/jHsDate.css" />
	<style>
			input {
				height: 20px;
				padding: 5px;
				width: 200px;
				border: 1px solid #3ab2d0;
			}
	</style>
<body>
	<%
		String id = request.getParameter("id");
		out.print("欢迎您！" + id);
	%>
	<div style="text-align: center;">
		<input name="jHsDateInput" id="date" type="text">
	</div>
	<table width="1000" border="1" cellpadding="0"
		style="text-align: center;">
		<thead>
			<tr>
				<th>ISBN</th>
				<th>书名</th>
				<th>作者</th>
				<th>分类</th>
				<th>出版社</th>
				<th>价格</th>
				<th>收录时间</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody id="appAccount_list">
		</tbody>
	</table>
	<script src="./static/js/jquery-3.2.1.js"></script>
	<script type="text/javascript" src="./static/js/jHsDate.js"></script>
	<script type="text/javascript">
		$('#date').jHsDate();
		$(function() {
			$.ajax({
				type : "POST",
				url : "/books/BookServlet",
				dataType : "json",
				success : function(data) {
					var datas = data.data;
					var rows = "";

					$.each(datas, function(i, b) {
						rows += '<tr>';
						rows += "<td>" + b.ISBN;
						rows += "<td>" + b.book_name;
						rows += "<td>" + b.book_author;
						rows += "<td>" + b.sort_id;
						rows += "<td>" + b.book_pub;
						rows += "<td>" + b.book_price;
						rows += "<td>" + b.book_record;
						rows += "<td>" + "<a href=" + 'javascript:borrow('
								+ b.ISBN + ')' + ">借阅</a>";
						rows += '<tr>';

					});
					$("#appAccount_list").html(rows);
				}
			});
		});

		//借阅操作
		function borrow(id) {

		}
	</script>
	<!--
		$.ajax({
			type : "POST",
			url : "/books/UpdateBookServlet",
			dataType : "json",
			success : function(data) {
				if (data.code == "000000") {
					alert("借阅成功！");
				} else {
					alert("借阅失败！");
				}
			}
		});
		-->
</body>
</html>