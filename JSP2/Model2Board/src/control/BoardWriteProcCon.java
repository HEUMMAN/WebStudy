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
 * Servlet implementation class BoardWriteProcCon
 */
@WebServlet("/BoardWriteProcCon.do")
public class BoardWriteProcCon extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqPro(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqPro(request,response);
	}

	protected void reqPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("EUC-KR");
		//��Ŭ������ �����͸� �о����
		BoardBean bean = new BoardBean();
		bean.setWriter(request.getParameter("writer"));
		bean.setSubject(request.getParameter("subject"));
		bean.setEmail(request.getParameter("email"));
		bean.setPassword(request.getParameter("password"));
		bean.setContent(request.getParameter("content"));
		
		BoardDAO bdao = new BoardDAO();
		bdao.insertBoard(bean);		//�ϳ��� �Խñ��� bean���� ���� ��� �����ϴ� �޼���
		
		RequestDispatcher dis = request.getRequestDispatcher("BoardListCon.do");
		dis.forward(request, response);
	}
}
