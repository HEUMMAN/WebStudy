<%@page import="model.MemberDAO"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.PreparedStatement"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<body>
<%
	request.setCharacterEncoding("EUC-KR");
	//��� �κ��� ������ �о�鿩�� �ٽ� ��Ŭ������ ����
	String[] hobby = request.getParameterValues("hobby");
	//�迭�� �ִ� ������ �ϳ��� ��Ʈ������ ����
	String texthobby = "";
	for(int i=0;i<hobby.length;i++){
		texthobby += hobby[i]+" ";
	}
%>
	<!-- useBean �̿��ؼ� �Ѳ����� �����͸� �޾ƿ��� -->
	<jsp:useBean id="mbean" class="model.MemberBean">
		<jsp:setProperty name="mbean" property="*" />	<!-- ���ν�Ű�� -->
	</jsp:useBean> 
	
<%
	mbean.setHobby(texthobby);	//��̿��� �ּҰ� ����Ǳ⿡ MemberBeanŬ������ ����޼ҵ��� setHobby�� �ٽ� ����
	
	//�����ͺ��̽� Ŭ���� ��ü ���� 
	MemberDAO mdao = new MemberDAO();
	mdao.insertMember(mbean);
	
	//ȸ�������� �Ǿ��ٸ� ȸ�������� �����ִ� �������� �̵��ñ�
	response.sendRedirect("MemberList.jsp");
%>
	����Ŭ ���� �Ϸ�~
</body>
</html>