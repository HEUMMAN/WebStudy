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
 * Servlet implementation class BoardInfoControl
 */
@WebServlet("/BoardInfoControl.do")
public class BoardInfoControl extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqPro(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqPro(request,response);
	}

	protected void reqPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//BoardList.jsp���� bean.num�� �Ѿ��. �̰Ÿ� �޾ƾ��Ѵ�
		int num = Integer.parseInt(request.getParameter("num"));
		
		//�����ͺ��̽��� ����
		BoardDAO bdao = new BoardDAO();
		//�ϳ��� �Խñۿ� ���� ������ ����. � �Խñ������� num�� �ĺ�
		BoardBean bean = bdao.getOneBoard(num);	//num�� �������� ��񿡼� �Խñ۰�������
		
		//��񿡼� �Խù� �Ѱ��� ������ bean���� �޾ƿ°��� jsp�� �Ѱ��ش�
		request.setAttribute("bean", bean);	//req��ü�� �ø���
		RequestDispatcher dis = request.getRequestDispatcher("BoardInfo.jsp");
		dis.forward(request, response);
		
		
		

	}
}
