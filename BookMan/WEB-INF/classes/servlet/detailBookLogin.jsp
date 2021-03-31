<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/bookMan/css/detailBook.css">
<meta charset="UTF-8">
<title>BOOKMAN-detailbook</title>
<script type="text/javascript">
function checkdiv(){
	var checkbox = document.getElementsByName("read");
if(checkbox.checked) {
	var tbox = document.getElementsById("starChange");
	tbox.style = "";

}else {
	var tbox = document.getElementsById("starChange");
	 tbox.style = "";
}
}
</script>
</head>
<body>


<div class="header">
  <div class="header__inner">
<form action ="/bookMan/ShelfManagerServlet" method="get">
<input type="image" src="http://localhost:8080/bookMan/BOOKMAN.jpg"  class="header__logo"  alt="TOPへ">
</form>
      <a>　[書籍詳細]</a>
      <a>ログイン中 : <b>${account.userId}様</b>、ようこそ</a></div>

        <div class="header__user">
<form action = "/bookMan/ShelfManagerServlet" method = "get">
<input type = "submit" value="書籍一覧に戻る">
</form>
      </div>
</div>

<hr>
<h1>書籍情報</h1>
    <table border="1">
        <tr><th>画像</th><th style="width:100px;">書籍名</th><th style="width:50px;">著者</th><th>詳細</th></tr>
        <tr><td><img src="${userBookInfo.thumnail}" alt="NoImage" title="画像"></td><td>${userBookInfo.title}</td><td>${userbookInfo.authors}</td><td>${userBookInfo.description}</td></tr></table>
<hr>

<br><h1>登録情報</h1>


<form action="/bookMan/ShelfManagerServlet" method="post">


<c:choose>
<c:when test="${(userBookInfo.states%2) == 1}">
<input type="checkbox" name="have" value="1" checked="checked" >購入済み
</c:when>
<c:otherwise>
<input type="checkbox" name="have" value="1" >購入済み
</c:otherwise>
</c:choose>
<c:choose>
<c:when test= "${userBookInfo.states/4%1 >= 0.5}">
<input type="checkbox" name="wish" value="2" checked="checked" >興味あり
</c:when>
<c:otherwise>
<input type="checkbox" name="wish" value="2" >興味あり
</c:otherwise>
</c:choose>
<c:choose>
<c:when test="${userBookInfo.states/4 >= 1}">
<input type="checkbox" name="read" value="4" checked="checked" onclick="checkdiv()">既読済み

</c:when>
<c:otherwise>
<input type="checkbox" name="read" value="4" onclick="checkdiv()" >既読済み
</c:otherwise>
</c:choose>
<div id="starChange">
<dl class="cf">

    <dd class="data multi3">
        <div class="fiveStar">
        <c:forEach var="i" begin="1" end="5" step="1">
            <input id="star${i}" type="radio" value="${i}" name="star" <c:if test="${userBookInfo.review == i}">checked="checked"</c:if>>
            <label for="star${i}" class="star">★</label>
        </c:forEach>

        </div>

    </dd>
</dl>
</div>
<br>レビューコメント<br>
<textarea style="width:200px; height:100px;" cols="40" rows="8" name="comment">
${userBookInfo.comment}
</textarea>
<br>

<input type="hidden" name="mode" value="4">

<input type="submit" value="登録情報を編集する">
</form>

<form style="display: inline" action = "/bookMan/DeleteBookManager" method = "get">
<br>
<input type = "submit" value="この書籍の情報を削除">
</form>

<hr>

<footer>
    <p>© All rights reserved by BOOKMAN.</p>
  </footer>
</body>
</html>