package control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

	
@WebServlet("/HelloWorld")		//					/HelloWorld��� �ּ� url�� ǥ�����־�� �� ���� Ŭ������ ����ȴ�.
public class HelloWorld extends HttpServlet {
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqPro(request, response);	//reqPro�� ���ѱ�
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqPro(request, response);	//reqPro�� ���ѱ�
	}
	
	//post�� get������� ���°� ��� �� �޼��忡 ó��
	protected void reqPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//ȭ�鿡 HellloWorld��� ����ϰ�ʹ�... jsp������ �Ѱ��� �����͸� ����
		String msg = "Hello World �ȳ��ϼ���~";
		Integer data = 12;
		
		//jsp������ �����͸� request�� �����Ͽ� �Ѱ�����Ѵ�. forward�� ��ü�� �������ִµ�?
		request.setAttribute("msg", msg);	//"msg"������ msg(Hello World �ȳ��ϼ���~)�ѱ�ڴ�
		request.setAttribute("data", data);
		
		//�������� jsp�� ȣ���ϸ鼭 �����͸� ���� �Ѱ��ִ� ��ü�� ����
		RequestDispatcher rd = request.getRequestDispatcher("HelloWorld.jsp");	//jsp���ϸ��� ���
		rd.forward(request, response);
	}

}
