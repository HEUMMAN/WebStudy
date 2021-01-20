package control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.BoardDAO;

@WebServlet("/BoardUpdateProcCon.do")
public class BoardUpdateProcCon extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqPro(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqPro(request, response);
	}
	
	protected void reqPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//jsp������ �Ѿ�� �����͸� �޾���
		int num = Integer.parseInt(request.getParameter("num"));
		String password = request.getParameter("password");	//�̰� ����ڰ� �Է��� �н�����
		String pass = request.getParameter("pass");	//�̰� �������� �Ѿ�°�. ���� �����ͺ��̽��� ������ִ� �н�����
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		
		//password�� pass�� ���ؾ��Ѵ�
		if(password.equals(pass)) {		//�ùٸ� �н����带 �޾������ �����޼��� ����
			BoardDAO bdao = new BoardDAO();
			bdao.updateBoard(num,subject,content); 	//��ҿ��� num,subject,content�� ���� ��ҵ��� Bean���� ��� ���°����� 3���ۿ� ������ �׳� ���ڷ� ����
			request.setAttribute("msg", "1");	//1������ ���� �Ϸ�
			//�������� �� ����ȯ��Ѵٸ� ��ü�Խñ� ����� �̵�
			RequestDispatcher dis = request.getRequestDispatcher("BoardListCon.do");
			dis.forward(request, response);
		}
		else {
			//�Է¹��� ��й�ȣ�� Ʋ�ȴٸ� ������������ �̵�
			request.setAttribute("msg", "2");	//2������ ���Ʋ�Ǵٴ¶�
		}
	}
}
