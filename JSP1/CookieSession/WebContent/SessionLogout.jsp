<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<body>

<%
	//�α׾ƿ��� �� ������ �����ð��� �����Ű�°�
	session.setAttribute("id",null);	//id�� null������
	session.setMaxInactiveInterval(0);	//���� �����ð��� ���̴°�
	
	response.sendRedirect("SessionMain.jsp");
%>

</body>
</html>