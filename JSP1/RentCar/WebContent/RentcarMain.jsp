<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>

<%
	//���ʹ� ��� �ٲ�ϱ� request�� �޴´�
	String center = request.getParameter("center");

	//�������ӽ� �ʱ� ���ͼ���
	if(center == null){
		center = "Center.jsp";	//Default���Ͱ� �ο�
	}
%>



<table width="1000">
	<!-- Top�κ� -->
	<tr height="140" align="center">
		<td align="center"> <jsp:include page="Top.jsp" /> </td>
	</tr>
	
	<!-- Center�κ� -->
	<tr height="500" align="center">
		<td align="center"> <jsp:include page="<%= center %>" /> </td>
	</tr>
	
	<!-- Bottom�κ� -->
	<tr height="100" align="center">
		<td align="center"> <jsp:include page="Bottom.jsp" /> </td>
	</tr>
</table>

</body>
</html>