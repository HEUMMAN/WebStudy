<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<body>

<%
	//Center�������� ��� ���ؾ��Ѵ�.
	//Center���� �������ֱ� ���ؼ� request��ü�� �̿��Ͽ� center���� �޾ƿ�
	String center = request.getParameter("center");

	//ó�� SessionMain.jsp�� �����ϸ� request���� null���� �Ѿ���⿡ nulló�� �ʿ�
	if(center== null){
		center = "Center.jsp";
	}
	

%>


<center>
	<table border="1" width="800">
	<!-- Top -->
	<tr height="150">
		<td align="center" colspan="2">
		<jsp:include page="Top.jsp"/>
		</td>
	</tr>
	
	<!-- Left -->
	<tr height="400">
		<td align="center">
		<jsp:include page="Left.jsp"/> 
		</td> 
	
	<!-- Center -->

	<td align="center" width="600">
		<jsp:include page="<%=center%>"/>
		</td>
	</tr>
	
	<!-- Bottom -->
	<tr height="100">
		<td align="center"colspan="2">
		<jsp:include page="Bottom.jsp"/>
		</td>
	</tr>
	
	</table>
</center>

</body>
</html>