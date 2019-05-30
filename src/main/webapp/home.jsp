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
		<h3>
			欢迎您：<%=id%></h3>
		<button id="borrowBtn" onclick="queryBorrow()" type="submit"
			style="margin-left: 50px;">我的借阅</button>
		<button onclick="displayAllBook()" type="submit"
			style="margin-left: 20px;">查询图书</button>
	</div>

	<!-- 我的借阅界面 -->
	<div id="queryBorrowDiv" style="display: none; margin: 20px;">

		<p id="info_query_borrow" style="font-size: 14px; color: red"></p>

		<table id="tableQueryBorrowDiv" frame=hsides width="1000" border
			rules=none cellspacing=0 cellpadding="5"
			style="display: none; text-align: center; margin-top: 10px">
			<thead>
				<tr>
					<th>ISBN</th>
					<th>学号</th>
					<th>书名</th>
					<th>价格</th>
					<th>借阅时间</th>
					<th>预计归还时间</th>
					<th>归还时间</th>
					<th>借阅状态</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody id="queryBorrow_list">
			</tbody>
		</table>

	</div>

	<!-- 图书查询界面 -->
	<div id="queryBookDiv" style="display: none; margin: 20px;">
		<form>
			<div style="display: flex; margin-top: 20px;">
				按ISBN查询： <input id="query_book_ISBN_input" maxlength="18"><br>
				<button type="button" style="margin-left: 10px"
					onclick="queryByISBN()">提交</button>
			</div>

			<div style="display: flex; margin-top: 20px;">
				按书名查询：&#8197; <input id="query_book_name_input" maxlength="18"><br>
				<button type="button" style="margin-left: 10px"
					onclick="queryByName()">提交</button>
			</div>

			<p id="info_query" style="font-size: 14px; color: red"></p>

			<table id="tableQueryBookDiv" frame=hsides width="1000" border
				rules=none cellspacing=0 cellpadding="5"
				style="text-align: center; margin-top: 10px">
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
						<th>预计归还日期</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody id="appAccount_list">
				</tbody>
			</table>

		</form>
	</div>

	<script src="./static/js/jquery-3.2.1.js"></script>
	<script type="text/javascript">
		function queryBorrow() {

			$("#queryBorrowDiv").show();
			$("#queryBookDiv").hide();

			$.ajax({
				type : "POST",
				url : "/books/QueryBorrowByIdServlet",
				data : {
					id :
	<%=id%>
		},
				dataType : "json",
				success : function(data) {
					if (data.code == "000000") {
						var datas = data.data;
						if (datas.length == "0" || datas.length == 0) {
							alert("暂无数据");
							return;
						}
						var rows = "";
						$.each(datas,
								function(i, b) {
									var borrow = b.book_borrow;
									if (borrow == 0) {
										borrow = "可借阅";
									} else if (borrow == 1) {
										borrow = "已借阅";
									} else if (borrow == 2) {
										borrow = "已归还";
									} else {
										borrow = "未知状态";
									}

									rows += '<tr>';
									rows += "<td>" + b.ISBN;
									rows += "<td>" + b.s_id;
									rows += "<td>" + b.book_name;
									rows += "<td>" + b.price;
									rows += "<td>" + b.borrow_date;
									rows += "<td>" + b.expect_return_date;
									if (undefined == b.return_date) {
										var return_date = "未归还";
										rows += "<td>" + return_date;
									} else {
										rows += "<td>" + b.return_date;
									}
									rows += "<td>" + borrow;
									rows += "<td>" + "<a href="
											+ 'javascript:returnBook(' + b.ISBN
											+ ',' + i + ',' + b.book_borrow
											+ ')' + ">归还</a>";
									rows += '<tr>';
								});
						$("#queryBorrow_list").html(rows);
						$("#tableQueryBorrowDiv").show();
					} else {
						$("#info_query_borrow").text(data.message);
					}
				}
			});
		}

		//首次启动或页面刷新都会执行，显示学生借阅情况
		$("#borrowBtn").click(queryBorrow());

		function displayAllBook() {
			$("#queryBorrowDiv").hide();
			$("#queryBookDiv").show();
			$
					.ajax({
						type : "POST",
						url : "/books/BookServlet",
						dataType : "json",
						success : function(data) {
							if (data.code == "000000") {
								var b = data.data;
								if (b.length == "0" || b.length == 0) {
									alert("暂时没有查询到该图书");
									return;
								}
								var datas = data.data;
								var rows = "";
								$
										.each(
												datas,
												function(i, b) {
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
													rows += "<td>"
															+ b.book_name;
													rows += "<td>"
															+ b.book_author;
													rows += "<td>"
															+ b.sort_name;
													rows += "<td>" + b.book_pub;
													rows += "<td>"
															+ b.book_price;
													rows += "<td>"
															+ b.book_record;
													rows += "<td>" + borrow;
													rows += "<td>"
															+ "<input type ='date' id='myDate" + i + "'" + " style='text-align:center;'>";
													rows += "<td>"
															+ "<a href="
															+ 'javascript:borrow('
															+ b.ISBN + ',' + i
															+ ','
															+ b.book_borrow
															+ ')' + ">借阅</a>";
													rows += '<tr>';
												});
								$("#appAccount_list").html(rows);
								$("tableQueryBorrowDiv").show();
							} else {
								$("#info_query").text(data.message);
							}

						}
					});
		};

		//借阅操作
		function borrow(ISBN, i, borrow) {
			if (borrow != 0) {
				return alert("该图书已经被借阅啦！");
			}
			var valueDate = $("#myDate" + i).val();
			if (valueDate === "") {
				return alert("请输入归还日期");
			}
			// 比较日期
			//var mDate = new Date(valueDate);
			//console.log(mDate.getFullYear() + "===" + mDate.getMonth() + 1 + '---' + mDate.getDate());
			var curDate = new Date();
			var borrow_date = curDate.getFullYear() + '-'
					+ (curDate.getMonth() + 1) + '-' + curDate.getDate();
			$.ajax({
				type : "POST",
				url : "/books/BorrowBookServlet",
				data : {
					ISBN : ISBN,
					id :
	<%=id%>
		,
					borrow_date : borrow_date,
					expect_return_date : valueDate
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
		
		//归还操作
		function returnBook(ISBN, i, borrow) {
			if (borrow != 1) {
				return alert("当前图书不是借阅状态！");
			}
			var valueDate = $("#myDate" + i).val();
			if (valueDate === "") {
				return alert("请输入归还日期");
			}
			// 比较日期
			//var mDate = new Date(valueDate);
			//console.log(mDate.getFullYear() + "===" + mDate.getMonth() + 1 + '---' + mDate.getDate());
			var curDate = new Date();
			var return_date = curDate.getFullYear() + '-'
					+ (curDate.getMonth() + 1) + '-' + curDate.getDate();
			$.ajax({
				type : "POST",
				url : "/books/ReturnBookServlet",
				data : {
					ISBN : ISBN,
					return_date : return_date
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

		//查询图书div--通过搜索图书ISBN查询
		function queryByISBN() {
			var book_ISBN = $("#query_book_ISBN_input").val();
			if (book_ISBN == '') {
				$("#info_query").text("提示:ISBN唯一编号不能为空");
				return;
			}
			$
					.ajax({
						type : "POST",
						url : "/books/QueryBookByISBNServlet",
						dataType : "json",
						data : {
							book_ISBN : book_ISBN
						},
						success : function(data) {
							if (data.code == "000000") {
								var b = data.data;
								if (b.ISBN == undefined || b.ISBN == "") {
									alert("暂时没有查询到该图书");
									return;
								}
								var rows = "";
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
								rows += "<td>"
										+ "<input type ='date' id='myDate0' style='text-align:center;'>";
								rows += "<td>" + "<a href="
										+ 'javascript:borrow(' + b.ISBN + ','
										+ 0 + ',' + b.book_borrow + ')'
										+ ">借阅</a>";
								rows += '<tr>';

								$("#appAccount_list").html(rows);
							} else {
								$("#info_query").text(data.message);
							}
						}
					});
		}

		//查询图书div--通过搜索图书名查询
		function queryByName() {
			var book_name = $("#query_book_name_input").val();
			if (book_name == '') {
				$("#info_query").text("提示:图书名不能为空");
				return;
			}
			$
					.ajax({
						type : "POST",
						url : "/books/QueryBookByNameServlet",
						data : {
							book_name : book_name
						},
						dataType : "json",
						success : function(data) {
							if (data.code == "000000") {
								var datas = data.data;
								console.log(datas);
								if (datas.length == "0" || datas.length == 0) {
									alert("暂时没有查询到该图书");
									return;
								}
								var datas = data.data;
								var rows = "";
								$
										.each(
												datas,
												function(i, b) {
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
													rows += "<td>"
															+ b.book_name;
													rows += "<td>"
															+ b.book_author;
													rows += "<td>"
															+ b.sort_name;
													rows += "<td>" + b.book_pub;
													rows += "<td>"
															+ b.book_price;
													rows += "<td>"
															+ b.book_record;
													rows += "<td>" + borrow;
													rows += "<td>"
															+ "<input type ='date' id='myDate" + i + "'" + " style='text-align:center;'>";
													rows += "<td>"
															+ "<a href="
															+ 'javascript:borrow('
															+ b.ISBN + ',' + i
															+ ','
															+ b.book_borrow
															+ ')' + ">借阅</a>";
													rows += '<tr>';
												});
								$("#appAccount_list").html(rows);
							} else {
								$("#info_query").text(data.message);
							}
						}
					});
		}
	</script>
</body>
</html>