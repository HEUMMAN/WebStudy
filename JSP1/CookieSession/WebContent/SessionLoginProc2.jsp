<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<body>


<center>
	<h2>���� �α��� ó��2</h2>
<%
	request.setCharacterEncoding("EUC-KR");
//�� ���������� ����ó���� �����Ƿ� request�� ���� �ʿ� ����
//	String id = request.getParameter("id");
//	String pass = request.getParameter("pass");

	String id = (String)session.getAttribute("id");	//Object������ �ޱ⶧���� String������ ĳ�����ʿ�
	String pass = (String)session.getAttribute("pass");
%>
	<!-- �̰� �������� ������ id�� -->
	<h2>����� ���̵�� <%= id %>�Դϴ�</h2>
	
</center>

</body>
</html>