package control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.BoardBean;
import model.BoardDAO;

/**
 * Servlet implementation class BoardUpdateCon
 */
@WebServlet("/BoardUpdateCon.do")
public class BoardUpdateCon extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqPro(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqPro(request, response);
	}
	
	protected void reqPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//�ش� ���� ��ȣ
		int num = Integer.parseInt(request.getParameter("num"));
		
		//�����ͺ��̽����� �ϳ��� �Խñۿ� ���� ������ �����ϴ� �޼���
		BoardDAO bdao = new BoardDAO();
		//����Ÿ���� ��
		BoardBean bean = bdao.getOneUpdateBoard(num);	//��ȸ���� ������Ű�� �ʰ� ��񿡼� �ϳ� ��������
		
		request.setAttribute("bean", bean);  //�Խù� bean���� �����°� req��ü�� �ƴ´�
		
		RequestDispatcher dis = request.getRequestDispatcher("BoardUpdateForm.jsp");
		dis.forward(request, response);
 	}

}
