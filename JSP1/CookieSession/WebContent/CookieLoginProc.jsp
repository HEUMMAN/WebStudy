<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<body>
<%
	request.setCharacterEncoding("EUC-KR");

	
	//���̵� �о����
	String id = request.getParameter("id");
	//���̵����� üũ�ڽ��� üũ�Ǿ����� Ȯ��
	String save = request.getParameter("save");
	
	//üũ�ƴ��� �Ǵ�
	if(save!=null){
		//��Ű�� ����Ϸ��� ��ŰŬ������ �����ؾ���
		//������ ���� id�� value�� ����
		Cookie cookie = new Cookie("id",id);	//��Ʈ������ (Key, Value) �ѽ�
		
		//��Ű ��ȿ�ð� ����
		cookie.setMaxAge(60*3);	//10�а� ��ȿ(�ʴ���)
		
		//��������� ��Ű �Ѱ��ֱ�
		response.addCookie(cookie);
		out.write("��Ű���� �Ϸ�");
	}

	
%>
</body>
</html>