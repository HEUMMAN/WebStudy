<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>

<center>
	<h2>���� �α��� ó��1</h2>
<%
	request.setCharacterEncoding("EUC-KR");

	//����� ������ �ޱ�
	String id = request.getParameter("id");
	String pass = request.getParameter("pass");
	
	//�������� ���̵�� �н����� ����
	session.setAttribute("id",id);
	session.setAttribute("pass",pass);
	
	//���� �����ð� ����
	session.setMaxInactiveInterval(60*2);	//60�ʵ��� ���� ����
	
	response.sendRedirect("SessionMain.jsp");
%>
	
	<!-- �̷��� id�� �Ѱ��� ���� ������ �ּ�â�� �� ����ǹǷ� ���� �ʴ� --> 
	<!--<a href="SessionLoginProc2.jsp?id=<%=id%>">������������ �̵�</a>                        -->
	
	<!-- �̷��� ������ ���°��� ���� -->
	<!--<a href="SessionLoginProc2.jsp">������������ �̵�</a>		--> 
	
	
	
</center>
</body>
</html>