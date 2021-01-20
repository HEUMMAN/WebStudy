package control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/LoginProc.do")	//jsp�� action="����"�� ���ƾ��Ѵ�. �������ϸ��� �޶󵵵�
public class LoginProc extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqPro(request, response);	//doGet����̵� doPost����̵� reqPro�� �޾��ְڴ� �̸�.
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqPro(request, response);	//doGet����̵� doPost����̵� reqPro�� �޾��ְڴ� �̸�.
	}

	
	public void reqPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");	//doGet�̵� doPost�� ����� ���Ѱ� ���
		String pass = request.getParameter("password");
		
		request.setAttribute("id", id); 	//request��ü�� �����͸� ����, ���ǿ� �����ϸ� ��� ���������� ��밡���ϰ� request�� �����ϸ� �� ���������������� ��� ����
		request.setAttribute("pass", pass);

		//jsp������ requset��ü�� ���Ѱ��ش�
		RequestDispatcher dis = request.getRequestDispatcher("LoginProc.jsp");	//���Ѱ��� ��ü�� "LoginProc.jsp"
		dis.forward(request, response);	//"LoginProc.jsp"������ �������Ŵ, �̶� request�� response�� ���Ѱ���
		//�׷� ���� "LoginProc.jsp"�� �����
		
	}
}
