package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

//����Ŭ �����ͺ��̽��� �����ϰ� select �۾�, insert�۾� update�۾� delete�۾��� ������ �ִ� Ŭ�����Դϴ�.
//�� ����Ŭ�� �ƴϾ ����. �ٸ� �����ͺ��̽��� �̷� ������ ������
public class MemberDAO {
	//����Ŭ�� �����ϴ� �ҽ� �ۼ�
	/*
	 * String id = "system"; //���� ���̵� String pass = "1882"; String url =
	 * "jdbc:oracle:thin:@localhost:1521:XE"; //���� url
	 */
	Connection con;	//�����ͺ��̽��� ������ �� �ֵ��� ����
	PreparedStatement pstmt;	//�����ͺ��̽����� ������ ��������ִ� ��ü
	ResultSet rs;	//�����ͺ��̽��� ���̺��� ���� ����� ���Ϲ޾� �ڹٿ� �������ִ� ��ü 
		
	//�����ͺ��̽��� ������ �� �ֵ��� �����ִ� �޼ҵ�. �̰Ŵ� �ݺ������� ����Ұ��̱⿡ �޼ҵ�� ����
	public void getCon() {
		
		//Ŀ�ؼ�Ǯ�� �̿��Ͽ� �����ͺ��̽��� ����(server.xml�� �̸� ����ص�)
		try {
			Context initctx = new InitialContext(); 
			//��Ĺ������ ������ ��Ƴ��� ������ �̵�
			Context envctx = (Context) initctx.lookup("java:comp/env");
			//�����ͼҽ� ��ü�� ����
			DataSource ds = (DataSource) envctx.lookup("jdbc/pool");
			//������ �ҽ��� �������� Ŀ�ؼ��� �������ֽÿ�
			con = ds.getConnection();
		}catch(Exception e) {
			
		}
		
		
//		
//		try {
//			//1. �ش� �����ͺ��̽��� ����Ѵٰ� ����(Ŭ������ ���=����Ŭ���� ���)
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//			//2. �ش� �����ͺ��̽��� ����(����Ŭ�� ����)
//			con = DriverManager.getConnection(url,id,pass);
//		}catch(Exception e) {
//			
//		}
	}
	
	//�����ͺ��̽��� �ѻ���� ȸ�� ������ �������ִ� �޼ҵ�
	public void insertMember(MemberBean mbean) {	//mbean��ü�� ���ڷ� �޾Ƽ� �ؿ��մ� getId�� ���� �޼ҵ�� ������ ����
		try{
//			//1. �ش� �����ͺ��̽��� ����Ѵٰ� ����(Ŭ������ ���=����Ŭ���� ���)
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//			//2. �ش� �����ͺ��̽��� ����(����Ŭ�� ����)
//			Connection con = DriverManager.getConnection(url,id,pass);
			//1�� 2����� getCon�޼ҵ� ȣ��
			getCon();
			//3. ���� �� ���� �غ�
			String sql = "insert into member values(?,?,?,?,?,?,?,?)";
			//������ ����ϵ��� ����
			PreparedStatement pstmt = con.prepareStatement(sql);	//PreparedStatement��ü�� jsp���� ������ ����ϵ��� �����ϴ°�
			//?�� �°� �����͸� ����
			pstmt.setString(1, mbean.getId());		//�� 8������ MemberBean�� getter�޼ҵ�� �̰��� (?,?,?,?,?,?,?,?)���⿡ �ϳ��� ������
			pstmt.setString(2, mbean.getPass1());
			pstmt.setString(3, mbean.getEmail());
			pstmt.setString(4, mbean.getTel());
			pstmt.setString(5, mbean.getHobby());
			pstmt.setString(6, mbean.getJob());
			pstmt.setString(7, mbean.getAge());
			pstmt.setString(8, mbean.getInfo());
			pstmt.executeUpdate();	//insert, update, delete �� ����ϴ� �޼���
			//5. �ڿ� �ݳ�
			con.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	//��� ȸ���� ������ ������ �ִ� �޼ҵ� ȣ��
	public Vector<MemberBean> allSelectMember(){
		//�����ͺ��̽��� ������ �������� �ڷ����� ���ͷ� �����ϱ�
		Vector<MemberBean> v = new Vector<>();
		
		//�����ͺ��̽��� �׻� ����ó��
		try {
			//����Ŭ�� �����ϱ� �޼ҵ� ȣ��
			getCon();	
			//�����غ�
			String sql = "select * from member";
			//������ ��������ִ� ��ü ����
			pstmt = con.prepareStatement(sql);	//�̷����ϸ� prepareStatement�� ����Ŭ���� ������ ���� ����
			//������ �����Ų ����� �����ؼ� �޾���(����Ŭ ���̺� �˻��� ����� �ڹ� ��ü�� ����)
			rs = pstmt.executeQuery();
			//�ݺ����� ����ؼ� rs(ResultSet)�� ����� �����͸� �����س��ƾ���
			
			while(rs.next()) {	//����� �����͸�ŭ�� �ݺ�
				MemberBean bean = new MemberBean();	//�÷����� �������� �����͸� ��Ŭ������ ����
				bean.setId(rs.getString(1));		//�÷��ε����� 1���� �����Ѵ�
				bean.setPass1(rs.getString(2));
				bean.setEmail(rs.getString(3));
				bean.setTel(rs.getString(4));
				bean.setHobby(rs.getString(5));
				bean.setJob(rs.getString(6));
				bean.setAge(rs.getString(7));
				bean.setInfo(rs.getString(8));
				
				//��Ű¡�� MemberBeanŬ������ ���Ϳ� ����
				v.add(bean);
			}
			//�ڿ� �ݳ�
			con.close();
		}catch(Exception e) {
			//e.printStackTrace();
		}
		//�� ����� ���͸� ����
		return v;
	}
	
	
	//�� ����� ���� ������ �����ϴ� �޼ҵ� �ۼ�
	public MemberBean oneSelectMember(String id) {
		//�� ����� ���� ������ �����ϴϱ� bean��ü ����(���� �� �ʿ� ����)
		MemberBean bean = new MemberBean();
		
		try {
			//����Ŭ�� �����ϱ�
			getCon();
			//�� ����� �������� �����ۼ�, ���ڷ� �Ѿ�� id�� �´� ȸ��ã��
			String sql = "select * from member where id=?";
			pstmt = con.prepareStatement(sql);
			//?�� ���� ����
			pstmt.setString(1,id);	//ù��° ����ǥ�� ����, �� id=?�� ����ǥ�� ����
			//��������
			rs = pstmt.executeQuery();	//�˻������ rs�� �����
			
			if(rs.next()) {	//�˻��� ���(���ڵ�)�� �ִٸ�
				bean.setId(rs.getString(1));		//�� id�� �ش��ϴ� ����� ������ ���� bean�� �ֱ�
				bean.setPass1(rs.getString(2));
				bean.setEmail(rs.getString(3));
				bean.setTel(rs.getString(4));
				bean.setHobby(rs.getString(5));
				bean.setJob(rs.getString(6));
				bean.setAge(rs.getString(7));
				bean.setInfo(rs.getString(8));
			}
			//�ڿ� �ݳ�
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return bean;
	}
	
	
	//�� ȸ���� �н����� ���� �����ϴ� �޼ҵ�
	public String getPass(String id) {
		//String���� ������ �ؾ��ϱ⿡ String���� ����
		String pass = "";
		try {
			getCon();
			String sql = "select pass1 from member where id=?";	//���ڷ� ���� id�� �ش�Ǵ� �ٽ����常 �޾ƿ��� �Ǵϱ�
			pstmt = con.prepareStatement(sql);
			
			//?�� ���� ����
			pstmt.setString(1,id);	//ù��° ����ǥ�� ���� �� id=?�� ����ǥ
			//��������
			rs = pstmt.executeQuery();	//�˻������ rs�� �����
			
			if(rs.next()) {
				pass = rs.getString(1);	//1���� �н����尪�� ����� �÷� �ε���
			}
			//�ڿ��ݳ�
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return pass;
	}
	
	//�� ȸ���� ������ �����ϴ� �޼ҵ�
	public void updateMember(MemberBean bean) {
		try {
			getCon();
			String sql = "update member set email=?,tel=? where id=?";	//id�� ?�϶� �̸��ϰ� ��ȭ��ȣ�� ����
			//���� ���� ��ü ����
			pstmt = con.prepareStatement(sql);
			
			//?�� ���� ����
			pstmt.setString(1,bean.getEmail());	//ù��° ����ǥ�� ����
			pstmt.setString(2,bean.getTel());	//�ι�° ����ǥ
			pstmt.setString(3,bean.getId());	//������ ����ǥ
			
			//��������
			pstmt.executeUpdate();	//excuteQuery�� �ƴ� ������Ʈ��, ��ȯ���� ����
		
			//�ڿ��ݳ�
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//�� ȸ���� �����ϴ� �޼ҵ�
	public void deleteMember(String id) { 
		try {
			getCon();
			String sql = "delete from member where id=?";	//id�� ?�� ȸ���� ���� ����
			//���� ���� ��ü ����
			pstmt = con.prepareStatement(sql);
			
			//?�� ���� ����
			pstmt.setString(1,id);	//ù��° ����ǥ�� ����
			
			//��������
			pstmt.executeUpdate();	//excuteQuery�� �ƴ� ������Ʈ��, ��ȯ���� ����
		
			//�ڿ��ݳ�
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
