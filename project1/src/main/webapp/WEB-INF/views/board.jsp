<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Board</title>
<link rel = "stylesheet" href="./resources/css/board.css">
</head>
<body>
   <h1>Board 화면 입니다.</h1>
   <a href="./index">Index</a><br>
   <img alt="피카" src="./resources/img/picc.gif" height=200px>
   
   <table>
      <tr>
         <th>번호</th>
         <th>제목</th>
         <th>글쓴이</th>
         <th>날짜</th>
         <th>좋아요</th>
      </tr>
      <c:forEach items="${list }" var="row">
      <!-- 	onclick 자바 스크립트 페이지 이동, URL?파라미터 = 값 -->
         <tr onclick="location.href='./detail?bno=${row.bno}'">
            <td class="td1">${row.bno }</td>
            <td class="title">${row.btitle }</td>
            <td class="td1">${row.bwrite }</td>
            <td class="td2">${row.bdate }</td>
            <td class="td1">${row.blike }</td>
         </tr>
      </c:forEach>
   </table>
</body>
</html>