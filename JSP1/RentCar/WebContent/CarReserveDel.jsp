<%@page import="db.RentcarDAO"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<body>

<%
	String id = request.getParameter("id");
	String rday = request.getParameter("rday");
	
	RentcarDAO rdao = new RentcarDAO();
	//������� �޼��� ȣ��
	rdao.carRemoveReserve(id,rday);
	
	response.sendRedirect("RentcarMain.jsp");
%>

</body>
</html>