<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/bookMan/css/detailBook.css">
<meta charset="UTF-8">
<title>BOOKMAN-top</title>
</head>
<body>

<div class="header">
  <div class="header__inner">
  <form action ="/bookMan/ShelfMasterServlet" method="get">
  <input type="image" src="http://localhost:8080/bookMan/BOOKMAN.jpg" class="header__logo"  alt="TOPへ">
</form>
      <a>　[書籍一覧]</a>
      <a>　<b>ゲストユーザー様</b>、ようこそ　</a>
      </div>

      <div class="header__user">
      <form style="display: inline" action = "/bookMan/RegisterAccountServlet" method = "post">
<input type = "submit" value="アカウント作成">
<input type = "hidden" name="mode" value="1">
</form>
<form style="display: inline" action = "/bookMan/LoginServlet" method = "get">
<input type = "submit" value="ログイン">
</form>
      </div>
</div>

<hr>
<br>
<div style="display:inline">
        <form style="display:inline" action="/bookMan/ShelfMasterServlet" method="post">
             タイトル<input type = "text" size="35" name ="key"><br><br>
            <input type="hidden" name="mode"  value="2">
           <input type="submit" value ="絞り込み検索">
        </form>

        <form style="display:inline" action="/bookMan/ShelfMasterServlet" method="get">
            <input type="submit" value ="条件リセット"></form>
</div>
<br>

<c:if test="${not empty bookList}">
     <!-- <table border="1"> -->
    <table >
        <!-- <tr><th>書籍名</th><th>画像</th></tr> -->
        <% int i=1; %>
                <tr>
            <c:forEach var="book"  items="${bookList}">

                    <td style="width:300px">

                    <!-- </td>
                    <td> -->
                        <form method="post" action="/bookMan/ShelfMasterServlet">
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
</c:if>

<footer>
    <p>© All rights reserved by BOOKMAN.</p>
  </footer>

</body>
</html>