<%@page import="db.CarListBean"%>
<%@page import="java.util.Vector"%>
<%@page import="db.RentcarDAO"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<body>
<%
	//ī�װ� �з� ���� �޾ƿ;��Ѵ�.
	int category = Integer.parseInt(request.getParameter("category"));
	String temp = "";
	if(category==1){
		temp = "����";
	}
	else if(category==2){
		temp = "����";
	}
	else if(category==3){
		temp = "����";
	}
%>
<!-- center -->
<table width="1000">
	<tr height="60">
		<td align="center"colspan="3">
		<font size="6" color="gray"> <%=temp %> �ڵ���</font>
		</td>
	</tr>
<%
	RentcarDAO rdao = new RentcarDAO();
	Vector<CarListBean> v = rdao.getCategoryCar(category);	//�޾ƿ��� �����Ͱ� ����� �𸣴ϱ� ���ͷι޴´�
	//tr�� 3���� �����ְ� �ٽ� tr�� ������ �� �ֵ����ϴ� ���� ����
	int j=0;
	//ȭ�鿡 ������ ���ٿ� 3�� ������ �����ٿ� �� 3�������� �̷������� �������Ѵ�.
	for(int i=0;i<v.size();i++){
		//���Ϳ� ������ִ� beanŬ������ ����
		CarListBean bean = v.get(i);	//bean�� ���ͷ� �Ѿ�� �͵��� �ٽ� bean���� ������
		if(j%3==0){
%>
		<tr height="220">

		<%}%>
		<td width="333" align="center">
		<a href="RentcarMain.jsp?center=CarReserveInfo.jsp?no=<%= bean.getNo() %>">
			<img alt="" src="img/<%= bean.getImage() %>" width="300" height="200">
		</a>
		<p>
		<font size="3" color="gray"><b>������ : <%= bean.getName() %><br></b></font>
		<font size="3" color="gray"><b>�ݾ� : <%= bean.getPrice() %></b></font></p></td>
<%		j=j+1;	//j���� �����Ͽ� �ϳ��� �࿡ �� 3���� ���������� �����ֱ� ���Ͽ�
	}
%>


</table>
<!-- center -->
</body>
</html>