<%@page import="model.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<body>

<%
	request.setCharacterEncoding("EUC-KR");
%>
	<jsp:useBean id="mbean" class="model.MemberBean">
		<jsp:setProperty name="mbean" property="*"/>
	</jsp:useBean>
	
<%
	
	MemberDAO mdao = new MemberDAO();
	//mbean�� �ִ� id ������
	String pass = mdao.getPass(mbean.getId());		//�����ͺ��̽����� ������ �н����尪
	
	//�����ϱ� ���ؼ� �Է��� �н����尪�� ���� �����ͺ��̽��� ����� �ִ� �н����带 �񱳷��� �Ǿƾ� ��������
	if(mbean.getPass1().equals(pass)){	//���ٸ�
		//������̺� ����
		//MemberDAOŬ������ ȸ������ �޼��� �ۼ� �� ȣ��
		mdao.deleteMember(mbean.getId());	//id�� �������� ����
		response.sendRedirect("MemberList.jsp");	//�����ϰ��� ��ü������� �̵�
	}else{	//�ٸ��ٸ�
		//�������̺�� ������
%>
	<script type="text/javascript">
		alert("�н����尡 ���� �ʽ��ϴ�. �ٽ� Ȯ���� �ּ���.");
		history.go(-1);
	</script>
<%
	}
%>

</body>
</html>