<%@ page contentType="text/html; charset=euc-kr" %>
<%@ page import="com.model2.mvc.service.domain.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	User vo=(User)session.getAttribute("user");
	String role="";
	
	if(vo != null) {
		role=vo.getRole();
	}
%>

<html>
<head>
<title>Model2 MVC Shop</title>

<link href="/css/left.css" rel="stylesheet" type="text/css">

<script type="text/javascript">
function history(){
	popWin = window.open("/product/getHistory","popWin","left=300, top=200, width=300, height=200, marginwidth=0, marginheight=0, scrollbars=no, scrolling=no, menubar=no, resizable=no");
}
</script>

</head>

<body background="/images/left/imgLeftBg.gif" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0"  >

<table width="159" border="0" cellspacing="0" cellpadding="0">

<!--menu 01 line-->
<tr>
<td valign="top"> 
	<table  border="0" cellspacing="0" cellpadding="0" width="159" >	
		<%-- 	if(vo != null){ --%>
		<c:if test="${!empty user}">
		<tr>
			<td class="Depth03">
				<a href="/user/getUser?userId=<%=vo.getUserId() %>" target="rightFrame">개인정보조회</a>
			</td>
		</tr>
		</c:if>
		<%--	}  --%>
		<%-- if(role.equals("admin")){--%>
		<c:if test="${user.role.equals('admin')}">
		<tr>
			<td class="Depth03" >
				<a href="/user/listUser" target="rightFrame">회원정보조회</a>
			</td>
		</tr>
		</c:if>
		<%-- } --%>
		<tr>
			<td class="DepthEnd">&nbsp;</td>
		</tr>
	</table>
</td>
</tr>

<%--	if(role.equals("admin")){ --%>
<c:if test="${user.role.equals('admin')}">
<!--menu 02 line-->
<tr>
	<td valign="top"> 
		<table  border="0" cellspacing="0" cellpadding="0" width="159">
			<tr>
				<td class="Depth03">
					<a href="../product/addProductView.jsp;" target="rightFrame">판매상품등록</a>
				</td>
			</tr>
			<tr>
				<td class="Depth03">
					<a href="/product/listProduct?menu=manage"  target="rightFrame">판매상품관리</a>
				</td>
			</tr>
			<tr>
				<td class="Depth03">
					<a href="/purchase/listSale"  target="rightFrame">판매이력조회</a>
				</td>
			</tr>
			<tr>
				<td class="DepthEnd">&nbsp;</td>
			</tr>
		</table>
	</td>
</tr>
</c:if>
<%-- } --%>

<!--menu 03 line-->
<tr>
	<td valign="top"> 
		<table  border="0" cellspacing="0" cellpadding="0" width="159">
			<tr>
				<td class="Depth03">
					<a href="/product/listProduct?menu=search" target="rightFrame">상 품 검 색</a>
				</td>
			</tr>
			<%--	if(vo != null && role.equals("user")){--%>
			<c:if test="${!empty user and user.role.equals('user')}">
			<tr>
				<td class="Depth03">
					<a href="/purchase/listPurchase"  target="rightFrame">구매이력조회</a>
				</td>
			</tr>
			<tr>
				<td class="DepthEnd">&nbsp;</td>
			</tr>
			<tr>
				<td class="Depth03">
					<a href="/basket/listBasket"  target="rightFrame">장바구니</a>
				</td>
			</tr>
			<tr>
				<td class="DepthEnd">&nbsp;</td>
			</tr>
			<tr>
				<td class="Depth03">
					<a href="/point/getListPoint?userId=${user.userId}" target="rightFrame">적립금내역</a>
				</td>
			</tr>
			</c:if>
			<%--  }--%>
			<tr>
				<td class="DepthEnd">&nbsp;</td>
			</tr>
			<tr>
				<td class="Depth03">
					<a href="javascript:history()">최근 본 상품</a>
				</td>
			</tr>
		</table>
	</td>
</tr>
</table>
</body>
</html>
