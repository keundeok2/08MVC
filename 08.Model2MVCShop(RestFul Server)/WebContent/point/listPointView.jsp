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
${user.userName}���� ���� �������� 0 �Դϴ�.
</c:if>
<c:if test="${!empty list}">
${list[0].userId}���� ���� �������� ${list[0].currPoint}�Դϴ�. <br/><br/>
</c:if>

<c:forEach var="i" items="${list}">
	<c:if test="${i.changePoint != 0}">
		�������� : ${i.currPoint} &nbsp; �������̿볻�� : ${i.changePoint } &nbsp; �������� : ${i.changeDate} <br/>
	</c:if>
</c:forEach>

</body>
</html>