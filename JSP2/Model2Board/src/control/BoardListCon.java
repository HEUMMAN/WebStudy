package control;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.BoardBean;
import model.BoardDAO;


@WebServlet("/BoardListCon.do")
public class BoardListCon extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqPro(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqPro(request,response);
	}

	protected void reqPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		//ȭ�鿡 ������ �Խñ��� ���� ����
		int pageSize = 10;
		//���� �������� �ִ� �������� �ѹ����� �о�帲
		String pageNum = request.getParameter("pageNum");
		//�ʱ� ������ ���Խ� nulló��
		if(pageNum == null) {
			pageNum = "1";
		}
		//��ü �Խñ��� ����
		int count = 0;
		//jsp������ ������ ������ �ѹ��� ���ڰ� �����ϴ� ����
		int number = 0;
		
		//���� �������� �ִ� ������ ���ڸ� ���ڷ� ��ȯ
		int currentPage = Integer.parseInt(pageNum);
		//��ü �Խñ��� ������ �����;��ϱ⿡ �����ͺ��̽� ��ü����
		BoardDAO bdao = new BoardDAO();
		count = bdao.getAllCount();
		
		//���� ������ ������ ���۹�ȣ ����
		int startRow = (currentPage-1)*pageSize+1;
		int endRow = currentPage*pageSize;
		
		//�ֽű� 10���� �������� �Խñ� ���Ϲ޾��ִ� �޼��� ȣ��
		Vector<BoardBean> v = bdao.getAllBoard(startRow, endRow);	//startRow���� endRow���� �־���� �� ������ �´� �Խù��� �����´�
		number = count - (currentPage-1) * pageSize;
		
		
		///////////////////////////////����, ���� �� ��й�ȣ�� Ʋ�ȴٸ�
		String msg = (String) request.getAttribute("msg");
		
		
		///////////////////////////////������ �����ͺ��̽����� ������ �������� BoardList.jsp������ request��ü�� ��Ƽ� �Ѱ��ָ� �ȴ�.
		//�ϴ� request��ü�� �Ʊ�(���)
		request.setAttribute("v",v);	//�ֽű� 10���� ������ ���� ���͸� "v"��� �̸������� request��ü�� �Ǿ jsp�� ����
		request.setAttribute("number", number);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("count", count);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("msg", msg);
		
		//request��ü�� �Ѱ���
		RequestDispatcher dis = request.getRequestDispatcher("BoardList.jsp");	//����� �����ٴ�
		dis.forward(request, response);
	}
}





