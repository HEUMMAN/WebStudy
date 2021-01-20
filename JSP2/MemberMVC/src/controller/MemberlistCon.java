package controller;

import java.io.IOException;
import java.util.Vector;

import javax.print.attribute.standard.PresentationDirection;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.MemberBean;
import model.MemberDAO;


@WebServlet("/MemberlistCon.do")
public class MemberlistCon extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqPro(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqPro(request, response);
	}
	protected void reqPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//�����ͺ��̽��� �����Ͽ� ȸ���� ��� ������ ����
		MemberDAO mdao = new MemberDAO();
		Vector<MemberBean> v = mdao.getAllMember();
		
		request.setAttribute("v", v);//bean�� ��� ���͸� jsp�� �Ѱ��ָ� jsp���� ��� ȸ�� ����Ʈ ��� ����
		RequestDispatcher dis = request.getRequestDispatcher("MemberList.jsp");
		dis.forward(request, response);
	}
}
