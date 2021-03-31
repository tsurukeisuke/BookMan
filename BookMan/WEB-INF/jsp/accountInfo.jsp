<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.time.LocalDateTime"%>
<%
    LocalDateTime ldf = LocalDateTime.now();
int thisYear = ldf.getYear();
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:useBean id="today" class="java.util.Date" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BOOKMAN-account</title>
<link rel="stylesheet" href="/bookMan/css/style2.css">

</head>

<div class="header">
    <div class="header__inner">
        <form action="/bookMan/ShelfManagerServlet" method="get">
            <input type="image" src="http://localhost:8080/bookMan/BOOKMAN.jpg" class="header__logo" alt="TOPへ">
        </form>

            <a> [アカウント変更・削除]</a> <a>ログイン中 : <b>${account.userId}様</b>、ようこそ</a>

    </div>
    <div class="header__user">
        <form action="/bookMan/ShelfManagerServlet" method="get">
            <input type="submit" value="書籍一覧に戻る">
        </form>
    </div>
</div>


<c:if test="${not empty errMsg}">
    <p style="color: red">${errMsg}</p>
</c:if>
<h1>アカウント変更・削除</h1>
<hr>
<br>
<body>

    <form style="display: inline" action="/bookMan/AccountManagerServlet" method="post">
        <ul>

            <li class="item"><label>ID</label>${account.userId}</li>
            <br>
            <li class="item"><label>PASS</label> <input type="text" name="passward" value="${account.pass}"
                maxlength="30"></li>
            <br>
            <li class="item"><label>E-MAIL</label> <input type="email" name="email-address" value="${account.mail}"></li>
            <br>
            <li class="item"><label>BIRTHDAY</label> <input type="date" name="date"
                value="<fmt:formatDate value="${account.birthDay}" pattern="yyyy-MM-dd" />"></li>
            <br>

            <li class="GENDER"><label>GENDER</label> <c:choose>
                    <c:when test="${account.gender == 0}">
                        <input type="radio" name="gender" value=0 checked="checked">MEN
            <input type="radio" name="gender" value=1>WOMEN
            </c:when>
                    <c:otherwise>
                        <input type="radio" name="gender" value=0>MEN
            <input type="radio" name="gender" value=1 checked="checked">WOMEN
            </c:otherwise>
                </c:choose></li>
        </ul>
        <br> <input type="submit" value="上書き">



    </form>
    <form style="display: inline" action="/bookMan/UnregisterAccountServlet" method="post">

        <input type="submit" value="削除">
    </form>












    <footer>
        <p>© All rights reserved by BOOKMAN.</p>
    </footer>
</body>



</html>