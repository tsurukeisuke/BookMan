<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/bookMan/css/detailBook.css">
<meta charset="UTF-8">
<title>BOOKMAN-myShelf</title>
</head>
<body>

<div class="header">
  <div class="header__inner">
  <form action ="/bookMan/ShelfManagerServlet" method="get">
        <input type="image" src="http://localhost:8080/bookMan/BOOKMAN.jpg"  class="header__logo"  alt="TOPへ">
</form>
      <a>　[登録書籍一覧]</a>
      <a>ログイン中 : <b>${account.userId}様</b>、ようこそ</a> </div>

      <div class="header__user">
<c:if test="${not empty userBookList}">

</c:if>
<form style="display: inline" action = "/bookMan/AccountManagerServlet" method = "get">
<input type = "submit" value="アカウント情報">
</form>

<form style="display: inline" action = "/bookMan/LogoutServlet" method = "get">
<input type = "submit" value="ログアウト">
</form>

 </div>
</div>

<hr>
<br>
<div style="display:inline">
        <form style="display:inline" action="/bookMan/ShelfManagerServlet" method="post">
			 タイトル<input type = "text" size="35" name ="key"><br>

			 持っている
			<SELECT NAME="have">
			<OPTION SELECTED VALUE="-1">&nbsp;
			<OPTION VALUE="1">〇
			<OPTION VALUE="0">×
			</SELECT>
			興味がある
			<SELECT NAME="wish">
			<OPTION SELECTED VALUE="-1">&nbsp;
			<OPTION VALUE="1">〇
			<OPTION VALUE="0">×
			</SELECT>
			読んだ
			<SELECT NAME="read">
			<OPTION SELECTED VALUE="-1">&nbsp;
			<OPTION VALUE="1">〇
			<OPTION VALUE="0">×
			</SELECT>
<br>
			<input type="hidden" name="mode"  value="2">
           <input type="submit" value ="絞り込み検索">
        </form>

        <form style="display:inline" action="/bookMan/ShelfManagerServlet" method="get">
            <input type="submit" value ="条件リセット"></form>
</div>
<br>







    <table >

        <% int i=2; %>
                <tr><td style="width:300px"><br>
                 <form style="display: inline" action = "/bookMan/SearchBookServlet" method = "get">
<input type = "image" src="http://localhost:8080/bookMan/plus.jpg" style="width:150px"><br><br>
新しい本を探す
</form></td>
            <c:forEach var="book"  items="${userBookList}">

                    <td style="width:300px">

                    <!-- </td>
                    <td> -->
                        <form method="post" action="/bookMan/ShelfManagerServlet">
                        <input type = "image" src ="${book.thumnail}" alt ="NoImage">
                        <input type="hidden" name="isbn"  value="${book.isbn}">
                        <input type="hidden" name="mode"  value="3">
                        </form>

                    <br>
                       ${book.title}
                    </td>
                    <% if(i%5 == 0){ %>
                        </tr><tr>
                    <% } i++;%>
            </c:forEach>
                </tr>
    </table>


<footer>
    <p>© All rights reserved by BOOKMAN.</p>
  </footer>

</body>
</html>