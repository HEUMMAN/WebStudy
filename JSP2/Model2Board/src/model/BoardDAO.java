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
	
	//�����ͺ��̽��� ����
	public void getCon() {
		try {
			Context initctx = new InitialContext();
			Context envctx = (Context) initctx.lookup("java:comp/env");
			DataSource ds = (DataSource) envctx.lookup("jdbc/pool");
			con = ds.getConnection();	//Ŀ�ؼ� �������ִ� �޼���
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	//��ü ���� ���� ��ȯ�ϴ� �޼���
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
			//������ �Ѱ��� �������°��̱⿡ while�� �Ƚᵵ �ȴ�.
			if(rs.next()) {	//�����Ͱ� �ִٸ�
				count = rs.getInt(1);	//ù��°��Ҹ� �־���
			}
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	//ȭ�鿡 ������ �����͸� 10���� �����ؼ� �����ϴ� �޼���
	public Vector<BoardBean> getAllBoard(int start, int end) {
		//������ ��ü ����
		Vector<BoardBean> v = new Vector<>();
		//�������
		getCon();
		
		try {
			//�����ۼ� �̰Ŵ� �׳� ��ü �� �������� ����
			//String sql = "select * from board order by ref desc, re_step asc";	
			//ref�� ������������(�ֽű��� ����), re_step�� ������������(��۱ׂ����� ��������)�ؼ� board�� �ִ°� ��ü ��������
			//�̷��� �ϸ� �ֽű��� ���� ���ο´�
			
			//Rownum�̶�� ���� ����Ŭ���� ��ü������ ������ִ� �׷���. ���� �˾ƺ��� Roqnum�� Rnum���� ��Ī
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

	public void insertBoard(BoardBean bean) {
		getCon();
		int ref = 0;
		int re_step = 1;	//�� ���� ����ϴ°��̹Ƿ�
		int re_level = 1;	//�� ���� ����ϴ°��̹Ƿ� 
		try {
			//�����ۼ�
			String refsql = "select max(ref) from board";	//ref�߿� ���� ū�� �����´�
			pstmt = con.prepareStatement(refsql);
			//���ӽ��� �� ����� ����
			rs = pstmt.executeQuery();
			if(rs.next()) {
				ref = rs.getInt(1)+1;	//���� ū ���� 1�� �����ذ�
			}
			//�����͸� �����ϴ� ����
			//������ �Խñ� ��ü���� ���̺� �����ϴ� �ҽ�
			//board_seq.NEXTVAL�� �Խù� ��ȣ�� �ڵ������� 1�� �߰��ǰ� ����� ����Ŭ�� �������̴� board_seq�� ��忡�� ������ �������̸�
			//sysdate�� ���� �ý����� ��¥
			//values�ȿ� ������� (num, writer, email, subject, password, reg_date, ref, re_step, re_level, readcount(������ ù���̴� ��ȸ�� 0����), content)���� ���� ���ο���
			String sql = "insert into board values(board_seq.NEXTVAL,?,?,?,?,sysdate,?,?,?,0,?)";
			pstmt = con.prepareStatement(sql);
			//?����
			pstmt.setString(1, bean.getWriter());
			pstmt.setString(2, bean.getEmail());
			pstmt.setString(3, bean.getSubject());
			pstmt.setString(4, bean.getPassword());
			pstmt.setInt(5, ref);
			pstmt.setInt(6, re_step);
			pstmt.setInt(7, re_level);
			pstmt.setString(8, bean.getContent());
			//��������
			pstmt.executeUpdate();
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	//�ϳ��� �Խñ��� �о���̴� �޼���
	public BoardBean getOneBoard(int num) {
		getCon();
		BoardBean bean = null;
		
		try {
			//�ϳ��� �Խñ��� �о��ٴ� ��ȸ�� ����
			String countsql = "update board set readcount = readcount+1 where num=?";	//num�� ?�� �Խñ��� ��ȸ���� 1������Ŵ
			pstmt = con.prepareStatement(countsql);
			//?����
			pstmt.setInt(1, num);
			//��������
			pstmt.executeUpdate();
			
			//�� �Խñۿ� ���� ������ �������ִ� ���� �ۼ�
			String sql = "select * from board where num=?";	//nunm�� ?�� �Խñ��� ��񿡼� �����´�
			pstmt = con.prepareStatement(sql);
			//?����
			pstmt.setInt(1, num);
			//���������� �Խñ� ���Ϲ޴´�
			rs = pstmt.executeQuery();
			//�Խñ��� ���Ϲ޾Ҵٸ�
			while(rs.next()) {
				//�����͸� BoardBeanŬ������ ��Ű¡
				bean = new BoardBean();
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
				
			}
			con.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bean;
	}

	//�亯���� �����ϴ� �޼���
	public void reInsertBoard(BoardBean bean) {
		getCon();
		int ref = bean.getRef();
		int re_step = bean.getRe_step();
		int re_level = bean.getRe_level();
		try {
			/////////////////////�ٽ��ڵ�
			//�����ۼ�
			String levelsql = "update board set re_level = re_level+1 where ref=? and re_level > ?";	//ref�߿� ���� ū�� �����´�
								//re_level�� 1�� ����, ������ �ش� �� �׷��� �Խñ۸� �׸��� ���� ���� re_level ���� ū ���鸸
			pstmt = con.prepareStatement(levelsql);
			//?����
			pstmt.setInt(1, ref);
			pstmt.setInt(2, re_level);
			
			pstmt.executeUpdate();
		
			//�����͸� �����ϴ� ����
			//������ �Խñ� ��ü���� ���̺� �����ϴ� �ҽ�
			//board_seq. NEXTVAL�� �Խù� ��ȣ�� �ڵ������� 1�� �߰��ǰ� ����� ����Ŭ�� �������̴� board_seq�� ��忡�� ������ �������̸�
			//sysdate�� ���� �ý����� ��¥
			//values�ȿ� ������� (num, writer, email, subject, password, reg_date, ref, re_step, re_level, readcount(������ ù���̴� ��ȸ�� 0����), content)���� ���� ���ο���
			String sql = "insert into board values(board_seq.NEXTVAL,?,?,?,?,sysdate,?,?,?,0,?)";
			pstmt = con.prepareStatement(sql);
			//?����
			pstmt.setString(1, bean.getWriter());
			pstmt.setString(2, bean.getEmail());
			pstmt.setString(3, bean.getSubject());
			pstmt.setString(4, bean.getPassword());
			pstmt.setInt(5, ref);
			pstmt.setInt(6, re_step+1);	//���� �θ���� step���� 1�� �������Ѿ��ϱ⶧����
			pstmt.setInt(7, re_level+1);	//���ϵ���
			pstmt.setString(8, bean.getContent());
			//��������
			pstmt.executeUpdate();
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//��ȸ���� ������Ű�� �ʴ� �ϳ��� �޼��� �����ϴ� �޼���(�� ����)
		public BoardBean getOneUpdateBoard(int num) {
			getCon();
			BoardBean bean = null;
			
			try {
				//�� �Խñۿ� ���� ������ �������ִ� ���� �ۼ�
				String sql = "select * from board where num=?";	//nunm�� ?�� �Խñ��� ��񿡼� �����´�
				pstmt = con.prepareStatement(sql);
				//?����
				pstmt.setInt(1, num);
				//���������� �Խñ� ���Ϲ޴´�
				rs = pstmt.executeQuery();
				//�Խñ��� ���Ϲ޾Ҵٸ�
				while(rs.next()) {
					//�����͸� BoardBeanŬ������ ��Ű¡
					bean = new BoardBean();
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
				}
				con.close();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			return bean;
		}

		
		//�ϳ��� �Խñ��� �����ϴ� �޼���
		public void updateBoard(int num, String subject, String content) {
			getCon();
			try {
				//�����غ�
				String sql = "update board set subject=? , content=? where num=?";
							//������Ʈ�ض� board�� set�ض� subject�� ?��, content��?��, num�� ?�� �Խñ���
				//���������� ��ü ����
				pstmt = con.prepareStatement(sql);
				//?����
				pstmt.setString(1, subject);
				pstmt.setString(2, content);
				pstmt.setInt(3, num);
				//��������
				pstmt.executeUpdate();
				
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		
		//�ϳ��� �Խñ��� �����ϴ� �޼���
		public void deleteBoard(int num) {
			getCon();
			try {
				//�����غ�
				String sql = "delete from board where num=?";
				pstmt = con.prepareStatement(sql);
				//?����
				pstmt.setInt(1, num);
				pstmt.executeUpdate();
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
}