<%@ page language="java" contentType="text/html; charset=EUC-KR" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>구매 목록조회</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">

<script type="text/javascript">
	function fncGetUserList(currentPage) {
		document.getElementById("currentPage").value = currentPage;
	   	document.detailForm.submit();	
	}
</script>
</head>

<body bgcolor="#ffffff" text="#000000">

<div style="width: 98%; margin-left: 10px;">

<form name="detailForm" action="/basket/listBasket" method="post">

<table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0">
	<tr>
		<td width="15" height="37"><img src="/images/ct_ttl_img01.gif"width="15" height="37"></td>
		<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left: 10px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="93%" class="ct_ttl01">장바구니</td>
				</tr>
			</table>
		</td>
		<td width="12" height="37"><img src="/images/ct_ttl_img03.gif"	width="12" height="37"></td>
	</tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0"	style="margin-top: 10px;">
	<tr>
		<td colspan="11">전체 ${resultPage.totalCount}<%--=resultPage.getTotalCount()--%> 건수, 현재 ${resultPage.currentPage}<%--=resultPage.getCurrentPage()--%> 페이지</td>
	</tr>
	<tr>
		<td class="ct_list_b" width="100">상품명</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">상품이미지</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">가격</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" >담은 날짜</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" >구매하기</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" >삭제하기</td>
	</tr>
	<tr>
		<td colspan="11" bgcolor="808285" height="1"></td>
	</tr>
		<c:forEach var="i" items="${list}">
	<tr class="ct_list_pop">
		<td align="center">${i.basketProd.prodName}</td>
		<td></td>
		<td align="center">
			<c:forEach var="j" items="${i.basketProd.fileNameList}">
			<img src="../images/uploadFiles/${j}" width="200" height="200"/>
		</c:forEach>
		</td>
		<td></td>
		<td align="center">
		${i.basketProd.price}원
		</td>
		<td></td>
		<td align="center">
		${i.addDate}
		</td>
		<td></td>
		<td align="center">
		<a href="/purchase/addPurchase?prodNo=${i.basketProd.prodNo}"> 구매하기 </a> 
		</td>
		<td></td>
		<td align="center">
		<a href="/basket/deleteBasket?userId=${i.userId}&prodNo=${i.basketProd.prodNo}"> 삭제하기 </a> 
		</td>
	</tr>
	
	<tr>
		<td colspan="11" bgcolor="D6D7D6" height="1"></td>
	</tr>
		</c:forEach>
	<%-- } --%>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top: 10px;">
	<tr>
		<td align="center">
		<input type="hidden" id="currentPage" name="currentPage" value=""/>
			<jsp:include page="../common/pageNavigator.jsp"></jsp:include>
		
		</td>
	</tr>
</table>

<!--  페이지 Navigator 끝 -->
</form>

</div>

</body>
</html>