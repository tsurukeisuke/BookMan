<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BOOKMAN-logout</title>
<link rel="stylesheet" href="/bookMan/css/style2.css">
</head>
<body>
<div class="header">
  <div class="header__inner">
  <form action ="/bookMan/ShelfMasterServlet" method="get">
  <input type="image" src="http://localhost:8080/bookMan/BOOKMAN.jpg" class="header__logo"  alt="TOPへ">
</form>
      <a>　[ログアウト]</a>
      <a>　<b>ゲストユーザー様</b>、ようこそ　</a></div>

      <div class="header__user">

<form style="display: inline" action = "/bookMan/ShelfMasterServlet" method = "get">
<input type = "submit" value="トップへ">
</form>

      </div>
</div>
<h1>ログアウトしました。</h1>
<a href="/bookMan/index.jsp"></a>


<footer>
    <p>© All rights reserved by BOOKMAN.</p>
  </footer>
</body>
</html>