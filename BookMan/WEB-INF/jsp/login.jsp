<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BOOKMAN-login</title>
<link rel="stylesheet" href="/bookMan/css/style2.css">
</head>
<body>

<div class="header">
  <div class="header__inner">
  <form action ="/bookMan/ShelfMasterServlet" method="get">
  <input type="image" src="http://localhost:8080/bookMan/BOOKMAN.jpg" class="header__logo"  alt="TOPへ">
</form>
      <a>　[ログイン]</a>
      <a>　<b>ゲストユーザー様</b>、ようこそ　</a>
      </div>

       <div class="header__user">
       <form style="display: inline" action = "/bookMan/RegisterAccountServlet" method = "post">
<input type = "submit" value="アカウント作成">
<input type = "hidden" name="mode" value="1">
</form>
<form style="display: inline" action = "/bookMan/ShelfMasterServlet" method = "get">
<input type = "submit" value="トップへ">
</form>

      </div>
</div>

<c:if test="${not empty errMsg}">
<p style="color:red">${errMsg}</p>
</c:if>

<form style="display: inline" action="/bookMan/LoginServlet" method="post">
        <ul>

            <li class="ID"><label>ID</label> <input type="text" name="id" placeholder="ID入力"></li><br>

            <li class="PASS"><label>PASS</label> <input type="password" name="pass" value="" maxlength="30"
                placeholder="パスワード入力"></li><br>



</ul>
<br>


<input type="hidden" name="mode" value="1">
<input type="submit" value="ログイン">
</form>



<footer>
    <p>© All rights reserved by BOOKMAN.</p>
  </footer>


</body>
</html>