<%@page import="db.RentcarDAO"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<body>

<%
	request.setCharacterEncoding("EUC-KR");

	String id = request.getParameter("id");
	String pass = request.getParameter("pass");
	
	//ȸ�� id�� �н����尡 ��ġ�ϴ��� ��
	RentcarDAO rdao = new RentcarDAO();
	
	//�ش� ȸ���� �ִ��� ���θ� ���ڷ� ǥ��
	int result = rdao.getMember(id,pass);	//ȸ���� ��� ����� ȸ������
	if(result==0){
%>		<script> alert("ȸ�������� ��ġ���� �ʽ��ϴ�!");
			location.href='RentcarMain.jsp?center=MemberLogin.jsp';
		</script>
<%
	}else{
		//�α���ó���� �ƴٸ�
		session.setAttribute("id",id);	//id�� ���ǿ� ����
		response.sendRedirect("RentcarMain.jsp");
	}
%>

</body>
</html>