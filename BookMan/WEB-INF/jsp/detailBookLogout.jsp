<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/bookMan/css/detailBook.css">
<meta charset="UTF-8">
<title>BOOKMAN-detailbook</title>
</head>
<body>

<div class="header">
  <div class="header__inner">
  <form action ="/bookMan/ShelfMasterServlet" method="get">
  <input type="image" src="http://localhost:8080/bookMan/BOOKMAN.jpg" class="header__logo"  alt="TOPへ">
</form>
      <a>　[書籍詳細]</a>
      <a>　<b>ゲストユーザー様</b>、ようこそ　</a></div>

      <div class="header__user">
      <form style="display: inline" action = "/bookMan/LoginServlet" method = "get">
<input type = "submit" value="ログインページへ">
</form>
<form style="display: inline" action = "/bookMan/ShelfMasterServlet" method = "get">
<input type = "submit" value="トップへ">
</form>

      </div>
</div>

<hr><h1>書籍情報</h1>
    <table >
        <tr>

            <td style="width:300px">
                <img src="${bookInfo.thumnail}" alt="NoImage" title="画像" style="width:200px"></td>
            <td style="width:800px">
               <b>[書籍名]</b><br>${bookInfo.title}<br> <hr color="f5f5f5">
                <b>[著者]</b><br>${bookInfo.authors}<br> <hr color="f5f5f5">
                <b>[出版社]</b><br>${bookInfo.publisher}<br> <hr color="f5f5f5">
                <b>[出版日]</b><br>${bookInfo.publishDate}<br> <hr color="f5f5f5">
                <b>[詳細]</b><br>${bookInfo.description}

            </td>
        </tr>
    </table>

<hr>
<footer>
    <p>© All rights reserved by BOOKMAN.</p>
  </footer>
</body>
</html>