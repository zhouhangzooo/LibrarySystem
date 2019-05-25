<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>主页</title>
</head>
<body>
	<%
		String id = request.getParameter("id");
	%>
	<div style="display: flex">
		<h3>欢迎您：<%= id %></h3>
		<button id="addBook" type="submit" style="margin-left: 50px;">添加图书</button>
		<button id="queryBook" type="submit" style="margin-left: 20px;">查询图书</button>
		<button id="queryborrow" type="submit" style="margin-left: 20px;">借阅查询</button>
	</div>
	
	<table frame=hsides width="1000" border rules=none cellspacing=0 cellpadding="5"
		style="text-align: center;margin-top: 10px">
		<thead>
			<tr>
				<th>ISBN</th>
				<th>书名</th>
				<th>作者</th>
				<th>分类</th>
				<th>出版社</th>
				<th>价格</th>
				<th>收录时间</th>
				<th>借阅状态</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody id="appAccount_list">
		</tbody>
	</table>
	<script src="./static/js/jquery-3.2.1.js"></script>
	<script type="text/javascript">
		$(function() {
			$.ajax({
				type : "POST",
				url : "/books/BookServlet",
				dataType : "json",
				success : function(data) {
					var datas = data.data;
					var rows = "";
					$.each(datas, function(i, b) {
						var borrow = b.book_borrow;
						if(borrow == 0)
						{
							borrow = "可借阅";
						}else if(borrow == 1)
						{
							borrow = "已借阅";
						}else {
							borrow = "未知状态";
						}
						rows += '<tr>';
						rows += "<td>" + b.ISBN;
						rows += "<td>" + b.book_name;
						rows += "<td>" + b.book_author;
						rows += "<td>" + b.sort_name;
						rows += "<td>" + b.book_pub;
						rows += "<td>" + b.book_price;
						rows += "<td>" + b.book_record;
						rows += "<td>" + borrow;
						rows += "<td>" + "<a href=" + 'javascript:borrow(' + b.ISBN + ',' + i + ',' + b.book_borrow + ')' + ">借阅</a>";
						rows += '<tr>';
					});
					console.log(rows);
					$("#appAccount_list").html(rows);
				}
			});
		});

		//借阅操作
		function borrow(ISBN,i,borrow) {
			if(borrow != 0)
			{
				return alert("该图书已经被借阅啦！");
			}
			var valueDate = $("#myDate" + i).val();
			if(valueDate === ""){
				return alert("请输入归还日期");
			}
			// 比较日期
			//var mDate = new Date(valueDate);
			//console.log(mDate.getFullYear() + "===" + mDate.getMonth() + 1 + '---' + mDate.getDate());
			var curDate = new Date();
			var borrow_date = curDate.getFullYear() + '-' + (curDate.getMonth() + 1) + '-' + curDate.getDate();
			$.ajax({
				type : "POST",
				url : "/books/UpdateBookServlet",
				data : {
					ISBN : ISBN,
					id : <%= id %>,
					borrow_date :borrow_date,
					expect_return_date: valueDate
				},
				dataType : "json",
				success : function(data) {
					if (data.code == "000000") {
						alert("借阅成功！");
					} else {
						alert("借阅失败！");
					}
				}
			});
		}
	</script>
</body>
</html>