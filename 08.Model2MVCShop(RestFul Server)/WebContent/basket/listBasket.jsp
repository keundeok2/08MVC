<%@ page language="java" contentType="text/html; charset=EUC-KR" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>���� �����ȸ</title>

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
					<td width="93%" class="ct_ttl01">��ٱ���</td>
				</tr>
			</table>
		</td>
		<td width="12" height="37"><img src="/images/ct_ttl_img03.gif"	width="12" height="37"></td>
	</tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0"	style="margin-top: 10px;">
	<tr>
		<td colspan="11">��ü ${resultPage.totalCount}<%--=resultPage.getTotalCount()--%> �Ǽ�, ���� ${resultPage.currentPage}<%--=resultPage.getCurrentPage()--%> ������</td>
	</tr>
	<tr>
		<td class="ct_list_b" width="100">��ǰ��</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">��ǰ�̹���</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">����</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" >���� ��¥</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" >�����ϱ�</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" >�����ϱ�</td>
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
		${i.basketProd.price}��
		</td>
		<td></td>
		<td align="center">
		${i.addDate}
		</td>
		<td></td>
		<td align="center">
		<a href="/purchase/addPurchase?prodNo=${i.basketProd.prodNo}"> �����ϱ� </a> 
		</td>
		<td></td>
		<td align="center">
		<a href="/basket/deleteBasket?userId=${i.userId}&prodNo=${i.basketProd.prodNo}"> �����ϱ� </a> 
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

<!--  ������ Navigator �� -->
</form>

</div>

</body>
</html>