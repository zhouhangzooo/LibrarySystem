<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>主页</title>
<style type="text/css">
#addBookDiv input {
	margin-top: 0.5em;
}

#addBookDiv select {
	margin-top: 0.5em;
}
</style>
</head>
<body>
	<%
		String id = request.getParameter("id");
	%>
	<div style="display: flex">
		<h3>
			欢迎您：<%=id%></h3>
		<button id="allBook" type="submit" style="margin-left: 50px;">所有图书</button>
		<button id="addBook" type="submit" style="margin-left: 20px;">添加图书</button>
		<button id="queryBook" type="submit" style="margin-left: 20px;">查询图书</button>
		<button id="queryborrow" type="submit" style="margin-left: 20px;">借阅查询</button>
	</div>

	<!-- 所有图书界面 -->
	<table id="tableBook" frame=hsides width="1000" border rules=none
		cellspacing=0 cellpadding="5"
		style="display: none; text-align: center; margin-top: 10px">
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

	<!-- 添加图书界面 -->
	<div id="addBookDiv" style="display: none; margin: 20px;">
		<form>
			ISBN：&#12288;&#8197;&#8197;&#8197;<input id="book_ISBN"
				maxlength="18"><br> 图书名：&#12288;<input id="book_name"
				maxlength="20"><br> 图书作者：<input id="book_author"
				maxlength="18"><br> 出版社：&#12288;<input id="book_pub"
				maxlength="30"><br> 图书分类： <select
				style="width: 149.24px" id="booksort_list">
			</select> <br>收录时间：<input type="date" id="book_record"
				style='width: 149.24px; text-align: center;'><br> 图书价格：<input
				id="book_price" maxlength="8"><br> 借阅状态： <label><input
				name="book_borrow" type="radio" value="0" checked="checked" />可借阅</label> <label><input
				name="book_borrow" type="radio" value="1" />已借阅</label>
		</form>

		<div style="display: flex; margin-top: 20px">
			<button id="addBookButton" type="submit">提交</button>
			<button id="resetButton" type="reset" style="margin-left: 10px;">取消</button>
		</div>

		<p id="info" style="font-size: 14px; color: red"></p>

	</div>

	<script src="./static/js/jquery-3.2.1.js"></script>
	<script type="text/javascript">
		$("#allBook").click(
				function() {
					$.ajax({
						type : "POST",
						url : "/books/BookServlet",
						dataType : "json",
						success : function(data) {
							var datas = data.data;
							var rows = "";
							$.each(datas, function(i, b) {
								var borrow = b.book_borrow;
								if (borrow == 0) {
									borrow = "可借阅";
								} else if (borrow == 1) {
									borrow = "已借阅";
								} else {
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
								rows += "<td>" + "<a href="
										+ 'javascript:edit(' + i + ',' + b
										+ ')' + ">编辑</a>";
								rows += '&nbsp;';
								rows += "<a href=" + 'javascript:deleteBook(' + i
										+ ',' + b + ')' + " style='color:red'>删除</a>";
								rows += '<tr>';
							});
							console.log(rows);

							$("#addBookDiv").hide();
							$("#tableBook").show();
							$("#appAccount_list").html(rows);
						}
					});
				});

		//编辑操作
		function edit(i, b) {

			$.ajax({
				type : "POST",
				url : "/books/",
				data : {

				},
				dataType : "json",
				success : function(data) {
					if (data.code == "000000") {
						alert("编辑成功！");
					} else {
						alert("编辑失败！");
					}
				}
			});
		};
		
		//删除操作
		function deleteBook(i, b) {

			$.ajax({
				type : "POST",
				url : "/books/",
				data : {

				},
				dataType : "json",
				success : function(data) {
					if (data.code == "000000") {
						alert("删除成功！");
					} else {
						alert("删除失败！");
					}
				}
			});
		};

		//打开增加图书页面
		$("#addBook").click(
				function() {
					$("#tableBook").hide();
					$("#addBookDiv").show();

					//图书分类赋值
					$.ajax({
						type : "POST",
						url : "/books/BookSortServlet",
						dataType : "json",
						success : function(data) {
							var datas = data.data;
							var rows = "";
							$.each(datas, function(i, b) {
								rows += "<option value='" + b.id + "'>"
										+ b.sort_name + "</option>";
							});
							$("#booksort_list").html(rows);
						}
					});
				});

		$("#addBookButton").click(function() {

			var book_ISBN = $("#book_ISBN").val(); //jqury val()方法返回元素value属性
			var book_name = $("#book_name").val();
			var book_author = $("#book_author").val();
			var book_pub = $("#book_pub").val();
			var book_record = $("#book_record").val();
			var book_price = $("#book_price").val();
			var book_sort = $('#booksort_list option:selected').val();
			var book_borrow = $('input[name="book_borrow"]:checked').val();
			console.log('==' + book_ISBN + '\r\n' + book_record);
			if (book_ISBN == '') {
				$("#info").text("提示:ISBN唯一编号不能为空");
			} else if (book_name == '') {
				$("#info").text("提示:图书名不能为空");
			} else if (book_author == '') {
				$("#info").text("提示:图书作者不能为空");
			} else if (book_price == '') {
				$("#info").text("提示:价格不能为空");
			} else {
				$.ajax({
					type : "POST",
					url : "/books/addBookServlet",
					data : {
						book_ISBN : book_ISBN,
						book_name : book_name,
						book_author : book_author,
						book_pub : book_pub,
						book_record : book_record,
						book_price : book_price,
						book_sort : book_sort,
						book_borrow : book_borrow
					},
					dataType : "json",
					success : function(data) {
						if (data.code == "000000") {
							$("#info").text("提示:添加图书成功");
							setTimeout(function() {
								$("#allBook").trigger("click");
							}, 800);
						} else {
							$("#info").text(data.message);
						}
					}
				});
			}
		});
	</script>
</body>
</html>