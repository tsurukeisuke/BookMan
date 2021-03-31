<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/bookMan/css/detailBook.css">
<meta charset="UTF-8">
<title>BOOKMAN</title>
</head>
<body>


<div class="header">
  <div class="header__inner">
  <a href="/bookMan/myShelf"><!-- ログアウト中は、Topへのリンク -->
      <img class="header__logo" src="http://localhost:8080/bookMan/BOOKMAN.jpg">
      </a>
      <a>　書籍詳細ページ</a> </div>

      <div class="header__user">
      <a>ログイン中 : ${account.userId}さん</a><!-- ログアウト中は、アカウント作成かログインページへのリンク -->
                                </div>
</div>




<footer>
    <p>© All rights reserved by BOOKMAN.</p>
  </footer>
</body>
</html>