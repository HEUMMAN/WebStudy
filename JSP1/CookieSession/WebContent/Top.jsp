<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>

<%
	//�α׾ƿ� Ŭ���� �Ķ���͸� ���ؼ� �α׾ƿ����� ���θ� Ȯ�� 
	String logout = request.getParameter("logout");
	
	//�α׾ƿ��� �ȵƴٸ�
	if(logout != null){
		//�α׾ƿ��� �� ������ �����ð��� �����Ű�°�
		session.setAttribute("id",null);	//id�� null������
		session.setMaxInactiveInterval(0);	//���� �����ð��� ���̴°�
	}


	//������ ���� id�� �о����
	String id = (String)session.getAttribute("id");

	//�α����� �Ǿ����� �ʴٸ�(id�Ѿ�°� ���ٸ�) nulló�� ����
	if(id == null){
		id="�մ�";
	}

%>



	<!-- Top -->
	<table width="800">
		<tr height="100">
		<!-- �ΰ��̹��� -->
			<td colspan="2" align="center" width="200">
			<img src="Image/logo.jpg" width="200" height="70">
			</td>
			<td colspan="5" align="center"> 
			<font size="10">���� ķ��</font>
			</td>
		</tr>
		<tr height="50">
			<td width="100" align="center">��Ʈ</td>
			<td width="100" align="center">����</td>
			<td width="100" align="center">�ı��</td>
			<td width="100" align="center">ħ��</td>
			<td width="100" align="center">���̺�</td>
			<td width="100" align="center">ȭ�Դ�</td>
			<td width="200" align="center">
		<%
			if(id.equals("�մ�")){
		%>
			<%= id %>��<button onclick="location.href='SessionMain.jsp?center=SessionLoginForm.jsp'">�α���</button>
		<%		
			}else{
		%>
			<%= id %>��<button onclick="location.href='SessionMain.jsp?logout=1'">�α׾ƿ�</button>
		<%
			}
		%>
		</td>
		</tr>
		
	</table>


</body>
</html>