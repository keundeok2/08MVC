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

������ ���� ���Ű� �Ǿ����ϴ�.

<table border=1>
	<tr>
		<td>��ǰ��ȣ</td>
		<td>${purchase.purchaseProd.prodNo}<%--= purchase.getPurchaseProd().getProdNo() --%></td>
		<td></td>
	</tr>
	<tr>
		<td>�����ھ��̵�</td>
		<td>${purchase.buyer.userId} <%--= purchase.getBuyer().getUserId() --%></td>
		<td></td>
	</tr>
	<tr>
		<td>���ż���</td>
		<td>${purchase.purQuantity}</td>
		<td></td>
	</tr>
	<tr>
		<td>���Ź��</td>
		<td>
		<c:if test="${purchase.paymentOption.equals('1')}">���ݱ���</c:if>
		<c:if test="${purchase.paymentOption.equals('2')}">�ſ뱸��</c:if>
		</td>
		<td></td>
	</tr>
	<tr>
		<td>�������̸�</td>
		<td>${purchase.receiverName} <%--= purchase.getReceiverName() --%></td>
		<td></td>
	</tr>
	<tr>
		<td>�����ڿ���ó</td>
		<td>${purchase.receiverPhone} <%--= purchase.getReceiverPhone() --%></td>
		<td></td>
	</tr>
	<tr>
		<td>�������ּ�</td>
		<td>${purchase.divyAddr} <%--= purchase.getDivyAddr() --%></td>
		<td></td>
	</tr>
		<tr>
		<td>���ſ�û����</td>
		<td>${purchase.divyRequest} <%--= purchase.getDivyRequest() --%></td>
		<td></td>
	</tr>
	<tr>
		<td>����������</td>
		<td>${purchase.divyDate} <%--= purchase.getDivyDate() --%></td>
		<td></td>
	</tr>
</table>
</form>

</body>
</html>