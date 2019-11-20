<%@ page contentType="text/html; charset=EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

 
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>

<c:if test="${empty list}">
${user.userName}님의 현재 적립금은 0 입니다.
</c:if>
<c:if test="${!empty list}">
${list[0].userId}님의 현재 적립금은 ${list[0].currPoint}입니다. <br/><br/>
</c:if>

<c:forEach var="i" items="${list}">
	<c:if test="${i.changePoint != 0}">
		총적립금 : ${i.currPoint} &nbsp; 적립금이용내역 : ${i.changePoint } &nbsp; 적용일자 : ${i.changeDate} <br/>
	</c:if>
</c:forEach>

</body>
</html>