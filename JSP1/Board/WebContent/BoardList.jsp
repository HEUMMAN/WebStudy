<%@page import="model.BoardBean"%>
<%@page import="java.util.Vector"%>
<%@page import="model.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<body>
<%
	//��ü �Խñ� ������ jsp������ ��������
	//BoardDAO bdao = new BoardDAO();

	//��ü �Խñ��� ���� �޾��ִ� �ҽ��ۼ�
	//1������, 2������... �̷������� ������ ī���͸��� �ʿ�
	//Vector<BoardBean> vec = bdao.getAllBoard();
%>
<!-- ���� ���� -->
<h2>��ü �Խñ� ����</h2>

<!-- �Խñۺ��⿡ ī���͸� �����ϱ� ���� ������ ����(ī���͸��̶� �Խñ��� ���Ƽ� 1������ 2������ �̷��� �Ѱų����� -->
<%

	//�� �Խù��� 184����� �����ϰ� ����

	//ȭ�鿡 ������ �Խñ��� �Լ� ����
	//�Խñ��� 10���� ��� ȭ�鿡 �����ٰŴ�.
	int pageSize = 10;

	//���� ī���͸� Ŭ���� ��ȣ���� �о����
	String pageNum = request.getParameter("pageNum");  
	
	//���� ó�� BoardList.jsp�� Ŭ���ްų� ���� �����ϴٰ� ����� �Ѿ���� pageNum���� ���⿡ nulló�� �ʿ�
	//�� pageNum���� ���ٴ°��� �Խ��ǿ� ���������̶�� ��. �������ӽÿ��� 1���̺��� �����ִϱ� 1
	if(pageNum==null){
		pageNum = "1";
	}
	int count = 0;	//��ü ���� ������ �����ϴ� ����
	int number = 0;	//�۹�ȣ
	
	//���� �������� �ǹ�.(pageNum�� �Ľ�)
	int currentPage = Integer.parseInt(pageNum);
	
	//�����ͺ��̽����� ��ü �Խñ� ������ jsp������ ��������
	BoardDAO bdao = new BoardDAO();

	//��ü ���ù��� ������ �о���̴� �޼ҵ�, �츮�� ��ü ���� ������ �˾ƾ��Ѵ�.
	count = bdao.getAllCount();	//�ּ��� ������ �̰� 184��� �����Ѵ�
	
	//���� �������� ������ ���۹�ȣ ���� = �����ͺ��̽����� �ҷ��� ���۹�ȣ
	//���� ���� 76�������� 76��° ���� �ֽű��̴� 76��° �ۺ��� �����ֱ�
	int startRow = (currentPage-1)*pageSize+1;	//���� �������� �� ����(�� ���������� �����ֽű�)		//(1-1)*10+1=1 �� 1�������� ��� 1���ۺ���
	int endRow = currentPage*pageSize;			//���� �������� �� �Ʒ���(�� ���������� ���������)	//1*10=10  10���۱��� �ִ�.
	
	//��ü �Խñ��� ���� �޾��ִ� �ҽ��ۼ�
	//�ֽű� 10���� �������� �Խù��� ���Ϲ޾��ִ� �޼��� ȣ��
	Vector<BoardBean> vec = bdao.getAllBoard(startRow, endRow);
	
	//���̺� ǥ���� ��ȣ�� ����
	number = count-(currentPage-1)*pageSize;	//184-(1-1)*10=184 �� 1������������ 184���ۺ��� �ϳ��� �پ��� ������ �۹�ȣ �Ű���
%>



<table width="700" border="1" bgcolor="skyblue">
	<tr height="40">
		<td align="right" colspan="5">
		<input type="button" value="�۾���" onclick="location.href='BoardWriteForm.jsp'"></td>
	</tr>
	<tr height="40">
		<td width="50" align="center">��ȣ</td>
		<td width="320" align="center">����</td>
		<td width="100" align="center">�ۼ���</td>
		<td width="150" align="center">�ۼ���</td>
		<td width="80" align="center">��ȸ��</td>
	</tr>
<%
	//�� ���κ��� �Խ����� 1��, �� ��ȣ ���� �ۼ��� �ۼ��� ��ȸ����� ���� ����
	//�� ������ �Խñ��� ���̴� �ҽ��ڵ� �ۼ�
	for(int i=0;i<vec.size();i++){
		BoardBean bean = vec.get(i);	//���Ϳ� ������ִ� ��Ŭ������ �ϳ��� ����(���� ��ĭ���� ��Ŭ������ ��ҵ��� �� �ѹ��� �ٴ´�. ���ʹ� �������̴ϱ� �� ���ϼ��ִ�.)
%>
	<tr height="40">
		<td width="50" align="center"> <%= number-- %></td>	<!-- �۹�ȣ(i+1)�� ref_step���� �����϶� �� ���̹Ƿ� �׳� �������� 1������ �����ϵ��� -->
		<td width="320" align="left"> <a href="BoardInfo.jsp?num=<%= bean.getNum() %>" style="text-decoration:none"> 	<!-- style="text-decoration:none���پ��ֱ� -->
	<%
		if(bean.getRe_step()>1){	//�亯���� ������ �鿩�������ֱ�
			for(int j=0;j<(bean.getRe_step()-1)*5;j++){	//-1 ���������� �θ���� �ѿ����� ����, 2���� �ڽı��̹ǷΤ���, *5�� 5ĭ�������� �鿩���� 
	%>
			&nbsp;
	<%
			}
		}
	%>
		<%= bean.getSubject() %></a> </td>
		<td width="100" align="center"> <%= bean.getWriter() %></td>
		<td width="150" align="center"> <%= bean.getReg_date() %></td>
		<td width="80" align="center"> <%= bean.getReadcount() %></td>
	</tr>
<%
	}	
%>
</table>

<p>
<!-- ������ ī���͸� �ҽ� -->
<%
	if(count > 0){	//��ü �Խñ� ���� 0��Ÿ ũ�� ����, ���� �Ѱ��� �־�� �ؿ� 1��������� �����ϱ�
		int pageCount = count / pageSize +(count%pageSize == 0 ? 0 : 1);	//184/10 + 1 = 19 ��, 184���� ���� 10���� ������ 18�������� ������ ������ ���� 1������ �߰��ؼ� ������
		
		//���������� ���ڸ� ����(1���Խñۺ�������, 11���Խñۺ�����, 21���ۺ�������)
		 int startPage = 1;
		 if(currentPage % 10 != 0){	//���� �������� 10�� ����� �ƴ϶��
			 startPage = (int)(currentPage/10)*10+1;	//(1/10)*10+1=1  ��, 1���������� 1���� ������������ ���� , 11�����Ұų� 21�����Ұų� 31�����Ұų� ....
		 }
		 else{	//10�� ������
			 startPage = ((int)(currentPage/10)-1)*10+1;	//���� 20��������� => (20/10-1)*10+1 = 11, �� 11~20�� ������ 
		 }
		 int pageBlock = 10;	//ī���͸� ó�� ����, �� �ϴ� 10���� ��Ƴ���?
		 int endPage = startPage+pageBlock-1;	//1+10-1=10 ��, ȭ�鿡 ������ ������ ����������
		 
		 if(endPage > pageCount) endPage = pageCount;	//10>19  ����x
		 
		 //�����̶�� ��ũ�� ������� �ľ�(1~10������ ���������� ���������� �ȳ���. ������������ư�� �ִ�.  11~20�������� ���������� ��ư�� ����)
		 if(startPage > 10){	//11~20������ �������� [����������] ��ư�� ����
%>
			<a href="BoardList.jsp?pageNum=<%= startPage-10 %>">[����]</a>
<%
		 }
		 //startPage���� endPage���� �� ����
		 for(int i= startPage; i<=endPage; i++){	//i<=endPage���� <=�̱⿡ 1~10���������� ����
%>
			<a href="BoardList.jsp?pageNum=<%= i %>">[<%= i %>]</a>
<%
		 }

		//���������� ��ư�� ������� �ľ�(���� �������� 10������ ������ ������������ư�� ����)
		if(endPage < pageCount){
%>
			<a href="BoardList.jsp?pageNum=<%= startPage+10 %>">[����]</a>	
<%			//������ ������ startPage�� 10�� ���ؼ� ������������ �ٽ� ����(BoardList.jsp�� ȣ�������ϱ�)
		}
	}
	
%>



<!-- ���� ���� -->
</body>
</html>