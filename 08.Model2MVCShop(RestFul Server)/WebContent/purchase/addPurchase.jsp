<%@ page contentType="text/html; charset=EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%--
<%@page import="com.model2.mvc.service.domain.Purchase"%>
<%
	Purchase purchase = (Purchase)request.getAttribute("purchase");
%>
--%>
<html>
<head>
<title>Insert title here</title>
</head>

<body>

<form name="updatePurchase" action="/purchase/updatePurchase?tranNo=0" method="post">

다음과 같이 구매가 되었습니다.

<table border=1>
	<tr>
		<td>물품번호</td>
		<td>${purchase.purchaseProd.prodNo}<%--= purchase.getPurchaseProd().getProdNo() --%></td>
		<td></td>
	</tr>
	<tr>
		<td>구매자아이디</td>
		<td>${purchase.buyer.userId} <%--= purchase.getBuyer().getUserId() --%></td>
		<td></td>
	</tr>
	<tr>
		<td>구매수량</td>
		<td>${purchase.purQuantity}</td>
		<td></td>
	</tr>
	<tr>
		<td>구매방법</td>
		<td>
		<c:if test="${purchase.paymentOption.equals('1')}">현금구매</c:if>
		<c:if test="${purchase.paymentOption.equals('2')}">신용구매</c:if>
		</td>
		<td></td>
	</tr>
	<tr>
		<td>구매자이름</td>
		<td>${purchase.receiverName} <%--= purchase.getReceiverName() --%></td>
		<td></td>
	</tr>
	<tr>
		<td>구매자연락처</td>
		<td>${purchase.receiverPhone} <%--= purchase.getReceiverPhone() --%></td>
		<td></td>
	</tr>
	<tr>
		<td>구매자주소</td>
		<td>${purchase.divyAddr} <%--= purchase.getDivyAddr() --%></td>
		<td></td>
	</tr>
		<tr>
		<td>구매요청사항</td>
		<td>${purchase.divyRequest} <%--= purchase.getDivyRequest() --%></td>
		<td></td>
	</tr>
	<tr>
		<td>배송희망일자</td>
		<td>${purchase.divyDate} <%--= purchase.getDivyDate() --%></td>
		<td></td>
	</tr>
</table>
</form>

</body>
</html>