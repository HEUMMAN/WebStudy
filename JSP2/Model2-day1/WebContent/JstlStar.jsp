<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<body>

<!-- 5���� �ݺ����� ���� �ϴ� outerfor�� -->
<c:forEach var="i" begin="0" end="4">

	<!-- ��������ϴ� �ݺ��� -->
	<c:forEach var="j" begin="${i+1}" end="4">
		&nbsp;
	</c:forEach>
	
	<!-- *�� ����ϴ� �ݺ��� -->
	<c:forEach var="j" begin="1" end="${1+(i*2)}">
	*
	</c:forEach>
<br>
</c:forEach>

</body>
</html>