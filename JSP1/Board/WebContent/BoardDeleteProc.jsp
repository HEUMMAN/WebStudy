<%@page import="model.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<body>

<%
	String pass = request.getParameter("password");		//Delete������ ����ڰ� �Է��� ��й�ȣ
	int num = Integer.parseInt(request.getParameter("num"));	//�������� �Ѿ�°ǰ�?
			
	//�����ͺ��̽� ����
	BoardDAO bdao = new BoardDAO();
	String password = bdao.getPass(num);	//��� ������ִ� �ùٸ� �н�����
	
	//�Է¹��� �н������ ��񿡼� ������ �н����带 ��
	if(pass.equals(password)){		//���ٸ�
		bdao.deleteBoard(num);			//��������
		response.sendRedirect("BoardList.jsp");	//������� �̵�
	}
	else{	//�ٸ��ٸ�
	
%>
	<script>
		alert("�н����尡 Ʋ���ϴ�. �ٽ� Ȯ�����ּ���.");
		history.go(-1);
	</script>
<%
	}
%>

</body>
</html>