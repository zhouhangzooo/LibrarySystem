<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>主页</title>
</head>
<body>
	<script src="./static/js/jquery-3.2.1.js"></script>
	<%
		String id = request.getParameter("id");
		out.print("欢迎您！" + id);
	%>
	<script type="text/javascript">
		function() {
			$.ajax({
				type : "POST",
				url : "/books/BookServlet",
				dataType : "json",
				success : function(data) {
					var datas = data.data;
				}
			});
		}
	</script>
	<table  width="600" border="1" cellpadding="0" >
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
     <c:forEach var="b" items="${datas}"  > 
      <form action="UpdateServlet" method="post"> 
       <tr>
	       <td><input type="text" value="${b.ISBN}" name="ISBN" ></td>
	       <td><input type="text" value="${b.book_name}" name="book_name"></td>
	       <td><input type="text" value="${b.book_author}" name="book_author"></td>
	       <td><input type="text" value="${b.sort_id}" name="book_author"></td>
	       <td><input type="text" value="${b.book_pub}" name="book_pub"></td>
	       <td><input type="text" value="${b.book_price}" name="book_price"></td>
	       <td><input type="text" value="${b.book_record}" name="book_record"></td>
	       <td><a href="DeleteServlet?id=${b.ISBN}">删除</a>  <input type="submit" value="更新"/></td>
	   </tr>
    </form> 
    </c:forEach>  
    </table>
</body>
</html>