<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
		<button id="allBook" type="submit" style="margin-left: 50px;"
			onclick="displayAllBook()">所有图书</button>
		<button id="addBook" type="submit" style="margin-left: 20px;">添加图书</button>
		<button type="submit" onclick="queryBookClick()"
			style="margin-left: 20px;">查询图书</button>
		<button type="submit" style="margin-left: 20px;"
			onclick="queryborrowClick()">借阅查询</button>
	</div>

	<!-- 所有图书界面 -->
	<table id="tableBookDiv" frame=hsides width="1000" border rules=none
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

			<div style="display: flex; margin-top: 20px; height: 30px">
				<button id="addBookButton" type="button" style="margin-top: 6.6px">提交</button>
				<input id="retAddBookValue" type="reset" style="margin-left: 10px;"
					value="重置" />
			</div>

		</form>

		<p id="info" style="font-size: 14px; color: red"></p>
	</div>

	<!-- 图书编辑界面 -->
	<div id="editBookDiv" style="display: none; margin: 20px;">
		<form>
			ISBN：&#12288;&#8197;&#8197;&#8197;<input id="edit_book_ISBN"
				maxlength="18"><br> 图书名：&#12288;<input
				id="edit_book_name" maxlength="20"><br> 图书作者：<input
				id="edit_book_author" maxlength="18"><br> 出版社：&#12288;<input
				id="edit_book_pub" maxlength="30"><br> 图书分类： <select
				style="width: 149.24px" id="edit_booksort_list">
			</select> <br>收录时间：<input type="date" id="edit_book_record"
				style='width: 149.24px; text-align: center;'><br> 图书价格：<input
				id="edit_book_price" maxlength="8"><br> 借阅状态： <label><input
				name="edit_book_borrow" type="radio" value="0" checked="checked" />可借阅</label>
			<label><input name="edit_book_borrow" type="radio" value="1" />已借阅</label>

			<div style="display: flex; margin-top: 20px; height: 30px">
				<button id="editBookButton" type="button" style="margin-top: 6.6px">提交</button>
				<input type="reset" style="margin-left: 10px;" value="取消" />
			</div>

		</form>

		<p id="info_edit" style="font-size: 14px; color: red"></p>

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
				<tbody id="queryBook_list">
				</tbody>
			</table>

		</form>
	</div>

	<!-- 借阅查询界面 -->
	<div id="queryBorrowDiv" style="display: none; margin: 20px;">
		<form>
			<div style="display: flex; margin-top: 20px;">
				按ISBN查询： <input id="query_borrow_ISBN_input" maxlength="18"><br>
				<button type="button" style="margin-left: 10px"
					onclick="queryBorrowByISBN()">提交</button>
			</div>

			<div style="display: flex; margin-top: 20px;">
				按学号查询：&#8197; <input id="query_borrow_id_input" maxlength="18"><br>
				<button type="button" style="margin-left: 10px"
					onclick="queryBorrowById()">提交</button>
			</div>

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

		</form>
	</div>

	<script src="./static/js/jquery-3.2.1.js"></script>
	<script type="text/javascript">
		var divArrays = new Array();
		divArrays[0] = "tableBookDiv";
		divArrays[1] = "addBookDiv";
		divArrays[2] = "editBookDiv";
		divArrays[3] = "queryBookDiv";
		divArrays[4] = "tableQueryBookDiv";
		divArrays[5] = "queryBorrowDiv";
		divArrays[6] = "tableQueryBorrowDiv";

		function hiddenDiv(index) {
			$("#info").text(""); //切换页面的时候简单地隐藏了info信息
			$("#info_edit").text("");
			$("#info_query").text("");
			$("#info_query_borrow").text("");

			for (i = 0; i < divArrays.length; i++) {
				$("#" + divArrays[i]).hide();
			}
			$("#" + divArrays[index]).show();
		}

		//打开查询图书div
		function queryBookClick() {
			hiddenDiv(3);
		}

		//打开查询借阅div
		function queryborrowClick() {
			hiddenDiv(5);
			queryBorrow(1, null);
		}

		//查询图书div--通过搜索图书ISBN查询
		function queryByISBN() {
			var book_ISBN = $("#query_book_ISBN_input").val();
			if (book_ISBN == '') {
				$("#info_query").text("提示:ISBN唯一编号不能为空");
				return;
			}
			$.ajax({
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
							alert("暂无数据");
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
						rows += '<tr>';

						$("#queryBook_list").html(rows);
						$("#tableQueryBookDiv").show();
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
			$.ajax({
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
							alert("暂无数据");
							return;
						}
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
							rows += '<tr>';
						});
						$("#queryBook_list").html(rows);
						$("#tableQueryBookDiv").show();
					} else {
						$("#info_query").text(data.message);
					}
				}
			});
		}

		//查询借阅div--通过搜索图书ISBN查询
		function queryBorrowByISBN() {
			var book_ISBN = $("#query_borrow_ISBN_input").val();
			if (book_ISBN == '') {
				$("#info_query_borrow").text("提示:ISBN唯一编号不能为空");
				return;
			}
			$.ajax({
				type : "POST",
				url : "/books/QueryBorrowByISBNServlet",
				dataType : "json",
				data : {
					ISBN : book_ISBN
				},
				success : function(data) {
					if (data.code == "000000") {
						var b = data.data;
						if (b.id == "" || b.id == 0) {
							alert("暂无数据");
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
						rows += '<tr>';

						$("#queryBorrow_list").html(rows);
						$("#tableQueryBorrowDiv").show();
					} else {
						$("#info_query_borrow").text(data.message);
					}
				}
			});
		}

		//查询借阅div--通过搜索学号查询
		function queryBorrowById() {
			var s_id = $("#query_borrow_id_input").val();
			if (s_id == '') {
				$("#info_query_borrow").text("提示:学号不能为空");
				return;
			}
			queryBorrow(null, s_id);
		}

		//复用函数，查询所有借阅和查询指定id学号的借阅
		function queryBorrow(type, s_id) {
			if (type == 1) {
				var url = "/books/BorrowServlet";
			} else {
				var url = "/books/QueryBorrowByIdServlet";
			}
			$.ajax({
				type : "POST",
				url : url,
				data : {
					id : s_id
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

		function displayAllBook() {
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
						var arrays = new Array();
						arrays[0] = b.ISBN;
						arrays[1] = b.book_name;
						//rows += "<td>" + "<a href=" + 'javascript:editBook(' + b.ISBN + ','+ b.book_name+ ','+
						//b.book_author + ',' + b.sort_name + ',' + b.book_pub + ',' + b.book_price + ',' +
						//b.book_record + ',' + borrow + ')' + ">编辑</a>";
						rows += "<td><button onclick=editBook(" + arrays
								+ ")>编辑</button>";
						rows += '&nbsp;';
						rows += "<a href=" + 'javascript:deleteBook(' + b.ISBN
								+ ')' + " style='color:red'>删除</a>";
						rows += '<tr>';
					});
					console.log(rows);
					hiddenDiv(0);
					$("#appAccount_list").html(rows);
				}
			});
		};

		//首次启动或页面刷新都会执行，显示所有图书情况
		$("#allBook").click(displayAllBook());

		//编辑操作
		//function editBook(ISBN,name,book_author,sort_name,book_pub,book_record,book_price,borrow) {
		function editBook(name) {
			console.log('=======name' + name);
			hiddenDiv(2);

			$("#edit_book_ISBN").val(ISBN); //jqury val()方法返回元素value属性
			$("#edit_book_name").val(book_name);
			$("#edit_book_author").val(book_author);
			$("#edit_book_pub").val(book_pub);
			$("#edit_book_record").val(book_record);
			$("#edit_book_price").val(book_price);
			$('#edit_booksort_list option:selected').val(sort_name);
			$('input[name="edit_book_borrow"]:checked').val(borrow);

			//编辑图书-提交操作
			$("#editBookButton")
					.click(
							function() {
								var book_ISBN = $("#edit_book_ISBN").val(); //jqury val()方法返回元素value属性
								var book_name = $("#edit_book_name").val();
								var book_author = $("#edit_book_author").val();
								var book_pub = $("#edit_book_pub").val();
								var book_record = $("#edit_book_record").val();
								var book_price = $("#edit_book_price").val();
								var book_sort = $(
										'#edit_booksort_list option:selected')
										.val();
								var book_borrow = $(
										'input[name="edit_book_borrow"]:checked')
										.val();
								console.log('======book_borrow' + book_borrow);
								return;
								$.ajax({
									type : "POST",
									url : "/books/EditBookServlet",
									data : {
										ISBN : ISBN,
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
											alert("编辑成功！");
											$("#allBook").trigger("click");
										} else {
											alert("编辑失败！");
										}
									}
								});
							})

		};

		//删除操作
		function deleteBook(ISBN) {
			var r = confirm("是否要删除该图书");
			if (r != true) {
				return;
			}
			$.ajax({
				type : "POST",
				url : "/books/DeleteBookServlet",
				data : {
					ISBN : ISBN
				},
				dataType : "json",
				success : function(data) {
					if (data.code == "000000") {
						alert("删除成功！");
						location.reload();
					} else {
						alert("删除失败！");
					}
				}
			});
		};

		//打开增加图书页面
		$("#addBook").click(
				function() {
					hiddenDiv(1);
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
							$("#edit_booksort_list").html(rows);
						}
					});
				});

		//添加图书-提交操作
		$("#addBookButton").click(function() {
			var book_ISBN = $("#book_ISBN").val(); //jqury val()方法返回元素value属性
			var book_name = $("#book_name").val();
			var book_author = $("#book_author").val();
			var book_pub = $("#book_pub").val();
			var book_record = $("#book_record").val();
			var book_price = $("#book_price").val();
			var book_sort = $('#booksort_list option:selected').val();
			var book_borrow = $('input[name="book_borrow"]:checked').val();
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
					url : "/books/AddBookServlet",
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
								$("#retAddBookValue").trigger("click"); //重置添加图书div的数据
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