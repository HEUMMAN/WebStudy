package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BoardDAO {

	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	
	//�����ͺ��̽��� Ŀ�ؼ�Ǯ�� ����ϵ��� �����ϴ� �޼ҵ�
	public void getCon() {
		try {
			Context initctx = new InitialContext();
			Context envctx = (Context) initctx.lookup("java:comp/env");
			DataSource ds = (DataSource) envctx.lookup("jdbc/pool");
			//�����ͼҽ��� ���ؼ� 
			con = ds.getConnection();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//�ϳ��� ���ο� �Խñ��� �Ѿ�ͼ� ����Ǵ� �޼ҵ�
	public void insertBoard(BoardBean bean) {
		//��Ŭ������ �Ѿ���� �ʾҴ� �����͵��� �ʱ�ȭ ������Ѵ�.
		int ref = 0;	//�� �׷��ǹ�(������ ������Ѽ� ���� ū ref�� ������ �� +1�ϸ��)
		int re_step = 1;	//���ο� ���̱⿡ 1(�ֻ��� �θ�)
		int re_level = 1;	//���ο� ���̱⿡ 1(�ֻ��� �θ�)
		try {
			//�����ͺ��̽��� ����
			getCon();
			
			//���� ū ref���� �о���� ���� �غ�
			String refsql = "select max(ref) from board";	//ref�߿��� ���� ū���� �����϶� ��¶�
			//�������� ��ü
			pstmt = con.prepareStatement(refsql);
			//���� ���� �� ����� ����
			rs = pstmt.executeQuery();	//rs�� �������
			
			if(rs.next()) {		//�������� ������� �ִٸ� ����
				ref = rs.getInt(1)+1;	//�ִ밪�� +1�� ���ؼ� �� �׷��� ����
			}
			//������ �Խñ� ��ü���� ���̺� �����ϴ� �ҽ�
			//board_seq.NEXTVAL�� �Խù� ��ȣ�� �ڵ������� 1�� �߰��ǰ� ����� ����Ŭ�� �������̴� board_seq�� ��忡�� ������ �������̸�
			//sysdate�� ���� �ý����� ��¥
			//values�ȿ� ������� (num, writer, email, subject, password, reg_date, ref, re_step, re_level, readcount(������ ù���̴� ��ȸ�� 0����), content)���� ���� ���ο���
			String sql = "insert into board values(board_seq.NEXTVAL,?,?,?,?,sysdate,?,?,?,0,?)";
			pstmt = con.prepareStatement(sql);
			//?�� ���� pstmt�� ����, board_seq.NEXTVAL�� ���(����Ŭ)���� ������ 1������ ������ ����
			//setString(index, value)���� �ε����� ?�� ������ �ǹ�, sql���� �߰��߰� ������� ���Ծȵ�.
			pstmt.setString(1, bean.getWriter());	//1��± ?���� writer����
			pstmt.setString(2, bean.getEmail());
			pstmt.setString(3, bean.getSubject());
			pstmt.setString(4, bean.getPassword());
			pstmt.setInt(5, ref);
			pstmt.setInt(6, re_step);
			pstmt.setInt(7, re_level);
			pstmt.setString(8, bean.getContent());
			
			//��������
			pstmt.executeUpdate();
			//�ڿ��ݳ�
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//�����ͺ��̽����ִ� ��� �Խñ��� ��������, ī���͸��Ұű⶧���� 10���� �����´� start���� end����
	public Vector<BoardBean> getAllBoard(int start, int end) {
		//������ ��ü ����
		Vector<BoardBean> v = new Vector<>();
		//�������
		getCon();
		
		try {
			//�����ۼ� �̰Ŵ� �׳� ��ü �� �������� ����
			//String sql = "select * from board order by ref desc, re_step asc";	//ref�� ������������,r re_step�� �������������ؼ� board�� �ִ°� ��ü ��������
																				//�̷��� �ϸ� �ֽű��� ���� ���ο´�
			
			String sql = "select * from (select A.* ,Rownum Rnum from (select *from board order by ref desc, re_step asc)A)" + "where Rnum >= ? and Rnum <= ?";
			//(select *from board order by ref desc, re_step asc)�̷��� ���� �� �̰Ÿ� A��� �θ�����Ѵ�. 
			//�� ���� A.* �� A������ ���Ͱ� Rnum�� �Բ� select(�˻�)�� ��
			//(where Rnum>= ? and Rnum <= ?), �� Rnum�� �������� ���ǰ˻��� �Ͽ� �װ��� select�Ѵٶ�¶�
			
			//���������� ��ü ����
			pstmt = con.prepareStatement(sql);
			//?�� �� ����
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			//�������� �� �������
			rs = pstmt.executeQuery();
			//�����Ͱ����� �𸣱⿡ �ݺ����� ���� ������ ����
			while(rs.next()) {
				//�����͸� BoardBeanŬ������ ��Ű¡
				BoardBean bean = new BoardBean();
				//rs�� ù��° ��Ҹ� setNum�� �־��ֱ� �̷���
				bean.setNum(rs.getInt(1));
				bean.setWriter(rs.getString(2));
				bean.setEmail(rs.getString(3));
				bean.setSubject(rs.getString(4));
				bean.setPassword(rs.getString(5));
				bean.setReg_date(rs.getDate(6).toString());
				bean.setRef(rs.getInt(7));
				bean.setRe_step(rs.getInt(8));
				bean.setRe_level(rs.getInt(9));
				bean.setReadcount(rs.getInt(10));
				bean.setContent(rs.getString(11));
				
				//��Ű¡�� �����͸� ���Ϳ� ����
				v.add(bean);
			}
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return v;
	}
	
	//�����ͺ��̽����� �ϳ��� �Խñ� �����ϴ� �޼ҵ�(BoardInfo���� ���, �����ϱ��Ҷ��� �ƴ� �׳� ��ȸ�Ҷ� ����ϴ¾�)
	public BoardBean getOneBoard(int num) {
		//����Ÿ�Լ���
		BoardBean bean = new BoardBean();
		//��񿬰�
		getCon();

		try {
			//��ȸ�� ���� ����
								//������Ʈ�ض�, board�� readcount�� = readcount+1��, num�� ?�� �Խñ���
								//��,  num�� ?�� �Խñ��� readcount�� readcount+1�� ������Ʈ�϶�
			String readsql = "update board set readcount = readcount+1 where num=?";
			//���������� ��ü ����
			pstmt = con.prepareCall(readsql);
			pstmt.setInt(1, num);	//1��°?���� num(BoardList���� �ٹ������ �Ѱ���)�� �־��ְڴ�
			pstmt.executeUpdate();	//�ݿ�

			//�����غ�
			String sql = "select * from board where num=?";	//board�� �ִ� ��� �Խñ��߿� num�� ?�� �Խñ۸� �������� 
			//�������ఴü����
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,num);	//1��°?���� num(BoardList���� �ٹ������ �Ѱ���)�� �־��ְڴ�
			//�������� �� �������
			rs = pstmt.executeQuery();
			if(rs.next()) {	//������ ������ 1���ۿ� �����Ƿ� if��
				bean.setNum(rs.getInt(1));
				bean.setWriter(rs.getString(2));
				bean.setEmail(rs.getString(3));
				bean.setSubject(rs.getString(4));
				bean.setPassword(rs.getString(5));
				bean.setReg_date(rs.getDate(6).toString());
				bean.setRef(rs.getInt(7));
				bean.setRe_step(rs.getInt(8));
				bean.setRe_level(rs.getInt(9));
				bean.setReadcount(rs.getInt(10));
				bean.setContent(rs.getString(11));
			}
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return bean;
	}
	
	//��۴ޱ� �޼���
	public void reWriteBoard(BoardBean bean) {
		//�θ� �۱׷�,�۽���,�۷����� �о����
		int ref = bean.getRef();
		int re_step = bean.getRe_step();
		int re_level = bean.getRe_level();
		
		//��񿬰�
		getCon();
		try {
			//////////////////�ٽ��ڵ�//////////////////
			//�θ�ۺ��� ū re_level�� ���� ���� 1�� ������Ű��
			String levelsql = "update board set re_level=re_level+1 where ref=? and re_level > ?";
						//�����ض�, �������̺���, re_level�� re_level�� 1���Ѱ�����, ref�� ?�̸鼭 re_level�� ?���� ū�ֵ鸸
			//�������ఴü ����
			pstmt = con.prepareStatement(levelsql);
			pstmt.setInt(1, ref);		//ù��° ����ǥ�� ref�ְ�
			pstmt.setInt(2, re_level);	//�ι�° ����ǥ�� re_level�ֱ�
			
			//��������
			pstmt.executeUpdate();
		
			//�亯�� �����͸� ��� ����
			String sql = "insert into board values(board_seq.NEXTVAL,?,?,?,?,sysdate,?,?,?,0,?)";
			//board_seq.NEXTVAL�� ���(����Ŭ)���� ������ 1������ ������ ����
			pstmt = con.prepareStatement(sql);
			//?�� ���� ����
			pstmt.setString(1, bean.getWriter());	//1��± ?���� writer����
			pstmt.setString(2, bean.getEmail());
			pstmt.setString(3, bean.getSubject());
			pstmt.setString(4, bean.getPassword());
			pstmt.setInt(5, ref);		//�θ��� ref�� �־���
			pstmt.setInt(6, re_step+1);		//����̱⿡ �θ����re_step�� 1�� ���ؼ� �־���
			pstmt.setInt(7, re_level+1);		//levelsql������ �θ�ۺ��� ū�ֵ��� 1�� ����������� ���δ޸� ��(�ڽ�)�� �θ𺸴� 1Ŀ���ϴϱ� +1���ش�
			//0�κκ��� readcount, ������ ���ο� ���̴� ��ȸ�� 0���� ����
			pstmt.setString(8, bean.getContent());
			
			//��������
			pstmt.executeUpdate();
			con.close();			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//BoardUpdate�� Delelte���� ����ϴ� �Խñ� �Ѱ� ���� �������� �޼���
	//�����ִ� getOneBoard���� ��ȸ�� +1���� �κи� ������
		public BoardBean getOneUpdateBoard(int num) {
			//����Ÿ�Լ���
			BoardBean bean = new BoardBean();
			//��񿬰�
			getCon();

			try {

				//�����غ�
				String sql = "select * from board where num=?";	//board�� �ִ� ��� �Խñ��߿� num�� ?�� �Խñ۸� �������� 
				//�������ఴü����
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1,num);	//1��°?���� num(BoardList���� �ٹ������ �Ѱ���)�� �־��ְڴ�
				//�������� �� �������
				rs = pstmt.executeQuery();
				if(rs.next()) {	//������ ������ 1���ۿ� �����Ƿ� if��
					bean.setNum(rs.getInt(1));
					bean.setWriter(rs.getString(2));
					bean.setEmail(rs.getString(3));
					bean.setSubject(rs.getString(4));
					bean.setPassword(rs.getString(5));
					bean.setReg_date(rs.getDate(6).toString());
					bean.setRef(rs.getInt(7));
					bean.setRe_step(rs.getInt(8));
					bean.setRe_level(rs.getInt(9));
					bean.setReadcount(rs.getInt(10));
					bean.setContent(rs.getString(11));
				}
				con.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
			return bean;
		}
		
		//update�� delete�� �ʿ��� �н����带 �޾ƿ��� �޼���
		public String getPass(int num) {
			//������ ���� ��ü ����
			String pass = "";
			getCon();
			
			try {
				String sql = "select password from board where num=?";	//board���� num�� ?�� ���� �н����尪�� ��������
				//�������ఴü ����
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, num);
				//��������
				rs = pstmt.executeQuery();
				//�н����� ���� ����
				if(rs.next()) {	//�н����尡 ��񿡼� �� ã�����ٸ�
					pass = rs.getString(1);
				}
				//�ڿ��ݳ�
				con.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
			return pass;
		}
	
		//�Խñ� ����(update)�ϴ� �޼���
		public void updateBoard(BoardBean bean) {
			//���
			getCon();
			
			try {
				//�����غ�
				String sql = "update board set subject=? , content=? where num=?";
							//subject�� ?�� �ٲٰ� content�� ?�� �ٲ��. ������ num�� ?�� ���߿�
				pstmt = con.prepareStatement(sql);
				
				//?�� ����
				pstmt.setString(1, bean.getSubject());
				pstmt.setString(2, bean.getContent());
				pstmt.setInt(3, bean.getNum());
				
				pstmt.executeUpdate();
				
				con.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		}
		
		
		//�ϳ��� �Խù��� �����ϴ� �޼���
		public void deleteBoard(int num) {
			getCon();
			try {
				//�����غ�
				String sql = "delete from board where num=?";	//board���̺��� num�� ?�� �ָ� �����϶�
				pstmt = con.prepareStatement(sql);
				//?����
				pstmt.setInt(1, num);
				//��������
				//select���� �ƴҶ���(����, �����϶���) executeUpdate�ϴµ�
				pstmt.executeUpdate();
				//�ڿ��ݳ�
				con.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		//��ü ���� ������ȯ�ϴ� �޼���
		public int getAllCount() {
			getCon();
			//�Խñ� ��ü ���� �����ϴ� ��������
			int count = 0;
			
			try {
				//�����غ�
				String sql = "select count(*) from board";	//board���̺��� ��ü(*)�� count��
				//���������� ��ü ����
				pstmt = con.prepareStatement(sql);
				//�������� �� �������
				rs = pstmt.executeQuery();
				if(rs.next()) {	//�����Ͱ� �ִٸ�
					count = rs.getInt(1);	//ù��°��Ҹ� �־���
				}
				con.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
			return count;
		}
}
