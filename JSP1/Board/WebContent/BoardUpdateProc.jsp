<%@page import="model.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<body>

<%
	//useBean�̳� request�� �����͸� �о���ϴµ� 4���� useBean���� �ѹ��� ����
	//DB���� �н����� �޾ƿͼ� �ǹٸ� �н����忩�� ���������ϵ���
	request.setCharacterEncoding("EUC-KR");
%>
<!-- ����� ������ �о���̴� BeanŬ���� ���� -->
<jsp:useBean id="boardbean" class="model.BoardBean">
	<jsp:setProperty name="boardbean" property="*"/>	<!-- *�� �ȵ����� ���ºκ��� null -->
</jsp:useBean>

<%
	//�����ͺ��̽��� ����
	BoardDAO bdao = new BoardDAO();
	//�ش� �Խñ��� �н����尪�� ����
	String pass = bdao.getPass(boardbean.getNum());	//��񿡼� �н����尪 �޾ƿ���
	
	//���� �н����尪��(��񿡼� �����°�) ������Ʈproc���� �Ѿ�� �н����尪�� ������ ��
	if(pass.equals(boardbean.getPassword())){
		//���ٸ� ������ �����޼��� ȣ��
		bdao.updateBoard(boardbean);
		//������ �Ϸ�Ǹ� ��ü�Խù� ����� ��
		response.sendRedirect("BoardList.jsp");
	}else{	//�Է¹��� �н����尡 Ʋ���ٸ�
%>
	<script type="text/javascript">
		alert("�н����尡 ��ġ���� �ʽ��ϴ�. �ٽ� Ȯ�� �� �õ��ϼ����!!!!!!!!!!!!!");
		history.go(-1);
	</script>
<%
	}
%>

</body>
</html>