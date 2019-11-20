<%@ page contentType="text/html; charset=EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<title>상품 목록조회</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">

<script type="text/javascript">
function fncGetUserList(currentPage, listing) {
	document.getElementById("currentPage").value = currentPage;
	document.getElementById("listing").value = listing;
   	document.detailForm.submit();	
}
</script>
</head>

<body bgcolor="#ffffff" text="#000000">

<div style="width:98%; margin-left:10px;">

<form name="detailForm" action="/product/listProduct?menu=${param.menu}<%--=menu--%>"method="post">

<table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0">
	<tr>
		<td width="15" height="37">
			<img src="/images/ct_ttl_img01.gif" width="15" height="37"/>
		</td>
		<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left:10px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
				<c:if test="${param.menu.equals('manage') }">
					<td width="93%" class="ct_ttl01"> 상품 관리 </td>
				</c:if>
				<c:if test="${param.menu.equals('search') }">
					<td width="93%" class="ct_ttl01"> 상품 목록조회 </td>
				</c:if>
				</tr>
			</table>
		</td>
		<td width="12" height="37">
			<img src="/images/ct_ttl_img03.gif" width="12" height="37"/>
		</td>
	</tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
		<td align="right">
			<select name="searchCondition" class="ct_input_g" style="width:80px">
				
				<option value="0" ${search.searchCondition.equals("0") ? "selected" : "" }>상품번호</option>
				<option value="1" ${search.searchCondition.equals("1") ? "selected" : "" }>상품명</option>
				<option value="2" ${search.searchCondition.equals("2") ? "selected" : "" }>상품가격</option>
				</select>
			<input type="text" name="searchKeyword" value="${! empty search.searchKeyword ? search.searchKeyword : '' }<%--= searchKeyword --%>" class="ct_input_g" style="width:200px; height:19px" />
			</td>
		<td align="right" width="70">
			<table border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="17" height="23">
						<img src="/images/ct_btnbg01.gif" width="17" height="23">
					</td>
					<td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top:3px;">
						<a href="javascript:fncGetUserList('1','${search.listing}');">검색</a>
					</td>
					<td width="14" height="23">
						<img src="/images/ct_btnbg03.gif" width="14" height="23">
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
		<td align="left" colspan="11" >전체 ${resultPage.totalCount}<%--= resultPage.getTotalCount() --%> 건수, 현재 ${resultPage.currentPage}<%--= resultPage.getCurrentPage()--%> 페이지</td>
	</tr>
	<tr>
		<td align="right" colspan="11"> <a href="javascript:fncGetUserList('1', 'high');"> 높은가격순▲</a>
		 <a href="javascript:fncGetUserList('1', 'low');"> 낮은가격순▼</a>
		 <a href="javascript:fncGetUserList('1','')"> 원래대로 </a>
		 </td>
		<input type="hidden" id="listing" name="listing" value=""/>
	</tr>
	<tr>
		<td class="ct_list_b" width="100">No</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">상품명</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">가격</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">상품이미지</td>	
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="200">현재상태</td>	
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="100">등록일</td>	
	</tr>
	<tr>
		<td colspan="11" bgcolor="808285" height="1"></td>
	</tr>
	<c:set var="no" value="1"/>
	<c:forEach var="i" items="${list}">
	<c:set var="product" value="${i}"/>
	
	<tr class="ct_list_pop">
		<td align="center">${no}<%--=no--%></td>
		<c:set var="no" value="${no+1}"/>	
		<td></td>
		<c:if test="${product.prodQuantity != 0}">
		<td align="left"><a href="/product/getProduct?prodNo=${product.prodNo}<%--= product.getProdNo() --%>&menu=${param.menu}<%--= menu--%>">${product.prodName}<%--= product.getProdName() --%></a></td>
		</c:if>
		<c:if test="${product.prodQuantity == 0}">
			<c:if test="${param.menu.equals('search')}">
			<td align="left">${product.prodName}</td>
			</c:if>
			<c:if test="${param.menu.equals('manage')}">
			<td align="left"><a href="/product/getProduct?prodNo=${product.prodNo}<%--= product.getProdNo() --%>&menu=${param.menu}<%--= menu--%>">${product.prodName}<%--= product.getProdName() --%></a></td>
			</c:if>
		</c:if>
		<td></td>
		<td align="center">${product.price}<%--= product.getPrice() --%></td>
		<td></td>
		<td align="center">
		<c:forEach var="i" items="${product.fileNameList}">
			<img src="../images/uploadFiles/${i}" width="200" height="200"/>
		</c:forEach>
		</td>
		<td></td>
		<td align="center">
			<c:if test="${product.prodQuantity != 0 }">
				판매중 (남은 수량 : ${product.prodQuantity})
			</c:if>
			<c:if test="${product.prodQuantity == 0}">
				재고없음
			</c:if>
		</td>
		<td></td>
		<td align="center">${product.regDate}<%--= product.getRegDate() --%></td>
	</tr>
	
	<tr>
		<td colspan="11" bgcolor="D6D7D6" height="1"></td>
	</tr>	
	</c:forEach>

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
		<td align="center">
		 <input type="hidden" id="currentPage" name="currentPage" value=""/>
		 <jsp:include page="../common/pageNavigator.jsp"/>
    	</td>
	</tr>
</table>
<!--  페이지 Navigator 끝 -->
</form>
</div>
</body>
</html>