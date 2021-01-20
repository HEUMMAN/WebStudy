package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import model.MemberBean;
import model.MemberDAO;


@WebServlet("/proc.do")
public class MemberJoinProc extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqPro(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqPro(request,response);
	}

	protected void reqPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("EUC-KR");
		
		MemberBean bean = new MemberBean();
		bean.setId(request.getParameter("id"));
		String pass1 = request.getParameter("pass1");
		String pass2 = request.getParameter("pass2");
		bean.setPass1(pass1);
		bean.setPass2(pass2);
		bean.setEmail(request.getParameter("email"));
		bean.setTel(request.getParameter("tel"));
		String[] arr = request.getParameterValues("hobby");	//hobby�� �������ϼ� ������ �迭�� �޾ƿ�
		String data = "";
		for(String string : arr) {
			data += string+" ";	//�ϳ��� ��Ʈ������ �����͸� ����
		}
		bean.setHobby(data); 	//�ϳ��� ��Ʈ������ ���� data�� ����
		bean.setJob(request.getParameter("job"));
		bean.setAge(request.getParameter("age"));
		bean.setInfo(request.getParameter("info"));
		//������� bean����
		
		
		//�н����尡 ���� ��쿡�� �����ͺ��̽��� ����
		if(pass1.equals(pass2)) {	//��й�ȣ�� ���ٸ�
			//�����ͺ��̽� ��ü ����
			MemberDAO mdao = new MemberDAO();
			mdao.insertMember(bean);
			//��Ʈ�ѷ����� �� �ٸ� ��Ʈ�ѷ��� ȣ���ؾ��Ѵ�.
			RequestDispatcher dis = request.getRequestDispatcher("MemberlistCon.do"); 	//�α��� �� ������ ���ο� ��Ʈ�ѷ� ȣ�� ��� ����Ʈ ��������ϱ⶧��
			dis.forward(request, response);
		}else {
			request.setAttribute("msg", "�н����尡 ��ġ���� �ʽ��ϴ�."); 	//��ũ��Ʈ �±׸� ���� �ȵȴ�. msg�� �Ѱ������
			RequestDispatcher dis = request.getRequestDispatcher("LoginError.jsp"); 	//LoginError.jsp�� ���ѱ��
			dis.forward(request, response);
		}
	}
}
