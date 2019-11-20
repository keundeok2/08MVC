<%@ page contentType="text/html; charset=EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<title>��ǰ �����ȸ</title>

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
					<td width="93%" class="ct_ttl01"> ��ǰ ���� </td>
				</c:if>
				<c:if test="${param.menu.equals('search') }">
					<td width="93%" class="ct_ttl01"> ��ǰ �����ȸ </td>
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
				
				<option value="0" ${search.searchCondition.equals("0") ? "selected" : "" }>��ǰ��ȣ</option>
				<option value="1" ${search.searchCondition.equals("1") ? "selected" : "" }>��ǰ��</option>
				<option value="2" ${search.searchCondition.equals("2") ? "selected" : "" }>��ǰ����</option>
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
						<a href="javascript:fncGetUserList('1','${search.listing}');">�˻�</a>
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
		<td align="left" colspan="11" >��ü ${resultPage.totalCount}<%--= resultPage.getTotalCount() --%> �Ǽ�, ���� ${resultPage.currentPage}<%--= resultPage.getCurrentPage()--%> ������</td>
	</tr>
	<tr>
		<td align="right" colspan="11"> <a href="javascript:fncGetUserList('1', 'high');"> �������ݼ���</a>
		 <a href="javascript:fncGetUserList('1', 'low');"> �������ݼ���</a>
		 <a href="javascript:fncGetUserList('1','')"> ������� </a>
		 </td>
		<input type="hidden" id="listing" name="listing" value=""/>
	</tr>
	<tr>
		<td class="ct_list_b" width="100">No</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">��ǰ��</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">����</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">��ǰ�̹���</td>	
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="200">�������</td>	
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="100">�����</td>	
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
				�Ǹ��� (���� ���� : ${product.prodQuantity})
			</c:if>
			<c:if test="${product.prodQuantity == 0}">
				������
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
<!--  ������ Navigator �� -->
</form>
</div>
</body>
</html>