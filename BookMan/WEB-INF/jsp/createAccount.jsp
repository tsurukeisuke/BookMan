<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.time.LocalDateTime"%>
<%
    LocalDateTime ldf = LocalDateTime.now();
int thisYear = ldf.getYear();
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BOOKMAN-registration</title>
<link rel="stylesheet" href="/bookMan/css/style2.css">
</head>
<body>

    <div class="header">
        <div class="header__inner">
            <form action="/bookMan/ShelfMasterServlet" method="get">
                <input type="image" src="http://localhost:8080/bookMan/BOOKMAN.jpg" class="header__logo" alt="TOPへ">
            </form>
            <a> [アカウント作成]</a> <a>　<b>ゲストユーザー様</b>、ようこそ　</a>
        </div>

        <div class="header__user">
            <form style="display: inline" action="/bookMan/ShelfMasterServlet" method="get">
                <input type="submit" value="トップへ">
            </form>

        </div>
    </div>

    <c:if test="${not empty errMsg}">
        <p style="color: red">${errMsg}</p>
    </c:if>

    <h1>アカウント作成</h1>
    <hr>
    <br>

    <form action="/bookMan/RegisterAccountServlet" method="post">
        <ul>

            <li class="item"><label>ID</label> <input type="text" name="ID" placeholder="ID入力"></li>
            <br>
            <li class="item"><label>PASS</label> <input type="password" name="passward" value="" maxlength="30"
                placeholder="パスワード入力"></li>
            <br>
            <li class="item"><label>E-MAIL</label> <input type="email" name="email-address" value=""
                placeholder="xxxx@example.com"></li>
            <br>
            <li class="item"><label>BIRTHDAY</label> <input type="date" name="date" value="1980-01-01"></li>
            <br>

            <li class="GENDER"><label>GENDER</label><input type="radio" name="gender" value=0>MEN <input
                type="radio" name="gender" value=1>WOMEN</li>
            <br>

        </ul>
        <input type="hidden" name="mode" value="2">
        <div>
            <input type="submit" value="登録">
        </div>

    </form>
    <footer>
        <p>© All rights reserved by BOOKMAN.</p>
    </footer>
</body>


</html>