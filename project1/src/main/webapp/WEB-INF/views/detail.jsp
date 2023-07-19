<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="./css/detail.css">
<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon">
<link rel="icon" href="/favicon.ico" type="image/x-icon">

<script type="text/javascript">
	function del() {
		let chk = confirm("삭제하시겠습니까?");
		if (chk) {
			location.href = "./delete?bno=${dto.bno }";
		}
	}
</script>

</head>
<body>

	<%@ include file="menu.jsp"%>

	<br>
	<br>
	<div class="neontitle">DETAIL</div>
	<br>
	<br>
	<br>
	<table border="1">
		<tr>
			<td class="neonText">no.${dto.bno }</td>
			<td><img alt="edit" src="./img/edit.png"></td>
			<td><img alt="delete" src="./img/delete.png" onclick="del()"></td>
		</tr>
	</table>
	<div class="wrapper">
		<div class="neonText" style="float: left">제목 : ${dto.btitle }</div>
		<div class="neonText" style="text-align: right">좋아요 :
			${dto.blike }</div>
		<br>
		<div class="neonText" style="float: left">${dto.bwrite }님</div>
		<div class="neonText" style="text-align: right">날짜 : ${dto.bdate }</div>
		<div class="neonText" style="text-align: right;">${dto.bip }</div>
		<hr>
		<div class="neonText">

			<div class="neonText">${dto.bcontent }</div>
		</div>
	</div>
	<div class="neontitle" style="text-align: center; font-size: xx-large;">
		<a href="./board">BACK</a>
	</div>
</body>
</html>