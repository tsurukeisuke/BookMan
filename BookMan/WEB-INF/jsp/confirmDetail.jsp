<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BOOKMAN-confirm</title>
<link rel="stylesheet" href="/bookMan/css/style2.css">
</head>
<body>

<div class="header">
  <div class="header__inner">
<form action ="/bookMan/ShelfManagerServlet" method="get">
<input type="image" src="http://localhost:8080/bookMan/BOOKMAN.jpg"  class="header__logo"  alt="TOPへ">
</form>
      <a>　[アカウント更新]</a>
      <a>ログイン中 : <b>${account.userId}様</b>、ようこそ</a>
      </div>

      <div class="header__user">
<form action = "/bookMan/AccountManagerServlet" method = "get">
<input type = "submit" value="アカウント情報に戻る">
</form>
      </div>
</div>

    <h1>アカウント更新</h1>

    <div style="display: inline-flex">
        <form action="/bookMan/AccountManagerServlet" method="post">

            <input type="hidden" name="mode" value="3"> <input type="hidden" name="confirm" value="1"><input
                type="submit" value="はい">
        </form>

        <form action="/bookMan/AccountManagerServlet" method="post">
            <input type="hidden" name="mode" value="3"> <input type="hidden" name="confirm" value="2"> <input
                type="submit" value="いいえ">
        </form>
    </div>
   <footer>
    <p>© All rights reserved by BOOKMAN.</p>
  </footer>

</body>
</html>