<%@page import="model.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<body>
<%
	request.setCharacterEncoding("EUC-KR");
%>
<!-- �����͸� �ѹ��� �޾ƿ��� beanŬ���� ��� -->
<jsp:useBean id="boardbean" class="model.BoardBean">
	<jsp:setProperty name="boardbean" property="*"/>	<!-- ��۴ޱ����� �θ���� �������� num, ref,re_step,re_level ���� -->
</jsp:useBean>

<%
	//�����ͺ��̽� ��ü ����
	BoardDAO bdao = new BoardDAO();
	bdao.reWriteBoard(boardbean);	//DAO�� �޼��� ����
	
	//�亯�� �����͸� ��� ���� �� ��ü �Խñ� ����
	response.sendRedirect("BoardList.jsp");
%>
</body>
</html>