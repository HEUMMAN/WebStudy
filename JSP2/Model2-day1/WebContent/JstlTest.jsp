<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<!-- �������� -->
<c:set var="sum" value="0"/>

<c:if test="${i>3}">
	�ȳ��ϼ��� 
</c:if>


<!-- �ݺ��� -->
<c:forEach var="i" begin="1" end="10" >
	<c:set var="sum" value="${sum = sum+i }"/>
	${sum }
</c:forEach>

</body>
</html>