<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>

<link rel="stylesheet" href="/bookMan/css/detailBook.css">
<meta charset="UTF-8">
<title>BOOKMAN-searchBook</title>
</head>
<body>

<div class="header">
  <div class="header__inner">
<form action ="/bookMan/ShelfManagerServlet" method="get">
<input type="image" src="http://localhost:8080/bookMan/BOOKMAN.jpg"  class="header__logo"  alt="TOPへ">
</form>
      <a>　[書籍検索]</a>
      <a>ログイン中 : <b>${account.userId}様</b>、ようこそ</a> </div>

        <div class="header__user">
<form action = "/bookMan/ShelfManagerServlet" method = "get">
<input type = "submit" value="書籍一覧に戻る">
</form>
      </div>
</div>

<form action="/bookMan/SearchBookServlet" method="post">
 <ul>

            <li><label>書籍名</label> <input type="text" name="title" placeholder="書籍名を入力"></li><br>

            <li><label>ISBN</label> <input type="text" name="isbn"  placeholder="ISBN番号を入力"></li><br>



</ul>


<input type="hidden" name="mode" value="1">
<input type ="submit" value ="検索">
</form>

<p id="info">該当する本が登録されていない場合、何も表示されません<p>

<hr>
<c:if test="${not empty bookList}">
    <table border="1" style="font-size: 10pt;">
    <thead>
        <tr><th>画像</th><th>タイトル</th><th>著者</th><th>詳細</th><th>追加</th></tr>
</thead>
        <c:forEach var="book"  items="${bookList}">

        <tr>
        <td><img src="${book.smallThumnail}" alt="NoImage" title="画像"></td>
        <td style="width:80px;"> ${book.title}</td>
        <td style="width:40px;">${book.authors}</td>
        <td>${book.description}</td>
        <td style="text-align:center" >
        <form  action="/bookMan/SearchBookServlet" method="post">
        <input type="checkbox" name="have" value="1" >買った&nbsp;&nbsp;&nbsp;<br>
        <input type="checkbox" name="wish" value="2" >興味あり<br>
        <input type="checkbox" name="read" value="4" >読んだ&nbsp;&nbsp;&nbsp;<br>

        <div class="fiveStar">
        <c:forEach var="i" begin="1" end="5" step="1">
            <input id="star${i}" type="radio" value="${i}" name="star" <c:if test="${userBookInfo.review == i}">checked="checked"</c:if>>
            <label for="star${i}" class="starsearch">★</label>
        </c:forEach>

        </div>
        <input type ="submit" value ="書籍一覧に追加">
        <input type ="hidden" name="isbn" value ="${book.isbn}">
        <input type ="hidden" name="mode" value ="2"></form></td></tr>

        </c:forEach>
    </table>
</c:if>

<footer>
    <p>© All rights reserved by BOOKMAN.</p>
  </footer>

</body>
</html>