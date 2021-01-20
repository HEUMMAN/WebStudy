package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class RentcarDAO {

	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	
	//Ŀ�ؼ�Ǯ�� �̿��� �����ͺ��̽� ����
	public void getCon() {
		try {
			//�ܺ� �����κ��� ������ �о���϶� Ŀ�ؼ�Ǯ�� jdbc/pool�̰� �������� �����Ƿ� ����ó��(NamingException)
			Context initctx = new InitialContext();	
			//lookup�޼��带 ����ؼ� java:comp/env�� ã�� �װ� Object���̴� Context�� ĳ����
			Context envctx = (Context) initctx.lookup("java:comp/env");
			//DataSource�̸��� jdbc/pool�� ã�� ĳ����
			DataSource ds = (DataSource) envctx.lookup("jdbc/pool");
			con = ds.getConnection();	//con��ü����
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//�ֽż� 3���� �ڵ����� �����ϴ� �޼���
	public Vector<CarListBean> getSelectCar(){
		Vector<CarListBean> v = new Vector<>();
		try {
			getCon();
			String sql = "select * from rentcar order by no desc";	//no�� �������� ���������ؼ� �� ��������
			pstmt = con.prepareStatement(sql);
			//�������� �� ����� ResultŸ������ ��ȯ
			rs = pstmt.executeQuery();
			int count = 0;
			while(rs.next()) {	//������ ������
				CarListBean bean = new CarListBean();
				bean.setNo(rs.getInt(1));
				bean.setName(rs.getString(2));
				bean.setCategory(rs.getInt(3));
				bean.setPrice(rs.getInt(4));
				bean.setUserpeople(rs.getInt(5));
				bean.setCompany(rs.getString(6));
				bean.setImage(rs.getString(7));
				bean.setInfo(rs.getString(8));
				//���Ϳ� ��Ŭ���� ����
				v.add(bean);
				count++;
				if(count > 2) {	//3���ݺ��� �ݺ��� Ż��
					break;	//3���� ���Ϳ� ����
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return v;
	}
	
	//ī�װ��� �ڵ��� ����Ʈ�� �����ϴ� �޼���
	public Vector<CarListBean> getCategoryCar(int cate){
		Vector<CarListBean> v = new Vector<>();
		//�����͸� ������ ��Ŭ���� ����
		CarListBean bean = null;
		getCon();
		
		try {
			String sql = "select * from rentcar where category=?";
			pstmt = con.prepareStatement(sql);
			//?�� ����
			pstmt.setInt(1, cate);
			//�������
			rs = pstmt.executeQuery();
			//�ݺ����� ���� �����͸� ����(����)
			while(rs.next()) {	//������ ������
				//�����͸� ������ beanŬ���� ����
				bean = new CarListBean();
				bean.setNo(rs.getInt(1));
				bean.setName(rs.getString(2));
				bean.setCategory(rs.getInt(3));
				bean.setPrice(rs.getInt(4));
				bean.setUserpeople(rs.getInt(5));
				bean.setCompany(rs.getString(6));
				bean.setImage(rs.getString(7));
				bean.setInfo(rs.getString(8));
				//���Ϳ� ��Ŭ���� ����
				v.add(bean);
				//�� �Ѵ븶�� bean�Ѱ����̹Ƿ� �ݺ������� where���ǿ� �´� ������ ������� bean���� ���Ϳ� �����
			}
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return v;
	}
	
	
	//��� ������ �˻��ϴ� �޼���
	public Vector<CarListBean> getAllCar(){
		
		Vector<CarListBean> v = new Vector<>();
		//�����͸� ������ ��Ŭ���� ����
		CarListBean bean = null;
		getCon();
		
		try {
			String sql = "select * from rentcar";
			pstmt = con.prepareStatement(sql);
			//�������
			rs = pstmt.executeQuery();
			//�ݺ����� ���� �����͸� ����(����)
			while(rs.next()) {	//������ ������
				//�����͸� ������ beanŬ���� ����
				bean = new CarListBean();
				bean.setNo(rs.getInt(1));
				bean.setName(rs.getString(2));
				bean.setCategory(rs.getInt(3));
				bean.setPrice(rs.getInt(4));
				bean.setUserpeople(rs.getInt(5));
				bean.setCompany(rs.getString(6));
				bean.setImage(rs.getString(7));
				bean.setInfo(rs.getString(8));
				//���Ϳ� ��Ŭ���� ����
				v.add(bean);
				//�� �Ѵ븶�� bean�Ѱ����̹Ƿ� �ݺ������� where���ǿ� �´� ������ ������� bean���� ���Ϳ� �����
			}
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return v;
	}
	
	
	//������ ��ȣ(no)�� �ش��ϴ� �ڵ��� �Ѵ��� ������ �����ϴ� �޼���
	public CarListBean getOneCar(int no) {
		//����Ÿ�԰�ü
		CarListBean bean = new CarListBean();
		getCon();
		
		try {
			String sql = "select * from rentcar where no=?";
			pstmt = con.prepareStatement(sql);
			//?����
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			if(rs.next()) {	//������ �� ����ƴٸ�
				bean.setNo(rs.getInt(1));
				bean.setName(rs.getString(2));
				bean.setCategory(rs.getInt(3));
				bean.setPrice(rs.getInt(4));
				bean.setUserpeople(rs.getInt(5));
				bean.setCompany(rs.getString(6));
				bean.setImage(rs.getString(7));
				bean.setInfo(rs.getString(8));
			}
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return bean;	//�� �Ѱ� ��ȯ
	}

	
	//ȸ�������� �����ϴ� ȸ������ Ȯ��
	public int getMember(String id, String pass) {
		int result = 0;	//0�̸� ȸ����Ͼȵ�����
		getCon();
		try {
			String sql = "select count(*) from member where id=? and pass1=?";	
						//*(����)�� ī��Ʈ�϶�. id�� ? �̰� �н����尡 ?�� �ֵ��߿�
						//1�� ������ ȸ���� ����, 0�̸� ����x
						//1�� �ش� id�� �н����带 ���� ȸ���� 1���̱⿡ 1�� ��ȯ
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pass);
			//�������
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);	// 1�Ǵ� 0�� �����
			}
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	//�ϳ��� ���������� �����ϴ� �޼���
	public void setReserveCar(CarReserveBean bean) {
		getCon();
		try {
			String sql = "insert into carreserve values(reserve_seq.NEXTVAL,?,?,?,?,?,?,?,?,?)";
														//�������̸��� reserve_seq�̰� NEXTVAL�� ���� 1�� ����
			pstmt = con.prepareStatement(sql);
			//?����
			pstmt.setInt(1, bean.getNo());
			pstmt.setString(2, bean.getId());
			pstmt.setInt(3, bean.getQty());
			pstmt.setInt(4, bean.getDday());
			pstmt.setString(5, bean.getRday());
			pstmt.setInt(6, bean.getUsein());
			pstmt.setInt(7, bean.getUsewifi());
			pstmt.setInt(8, bean.getUseseat());
			pstmt.setInt(9, bean.getUsenavi());
			pstmt.executeUpdate();
			con.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	//ȸ���� ���������� �����ϴ� �޼���
	public Vector<CarViewBean> getAllReserve(String id){
		Vector<CarViewBean> v = new Vector<>();
		CarViewBean bean = null;
		getCon();
		try {
			String sql = "select * from rentcar natural join carreserve where sysdate < to_date(rday,'YYYY-MM-DD') and id=?";
			pstmt = con.prepareStatement(sql);
			//?����
			pstmt.setString(1, id);
			//�������
			rs = pstmt.executeQuery();	//���� �ȹ޴°Ŵ� executeUpdate()
			//�ѻ���� �������� ������ �Ҽ��ֱ⿡ 
			while(rs.next()) {
				bean = new CarViewBean();
				bean.setName(rs.getString(2));
				bean.setPrice(rs.getInt(4));
				bean.setImg(rs.getString(7));
				bean.setQty(rs.getInt(11));
				bean.setDday(rs.getInt(12));
				bean.setRday(rs.getString(13));
				bean.setUsein(rs.getInt(14));
				bean.setUsewifi(rs.getInt(15));
				bean.setUseseat(rs.getInt(16));
				bean.setUsenavi(rs.getInt(17));
				//��Ŭ������ ���Ϳ� ����
				v.add(bean);
			}
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return v;
	}
	
	
	//�ϳ��� ���� ����
	public void carRemoveReserve(String id,  String rday) {
		getCon();
		try {
			String sql = "delete from carreserve where id=? and rday=?";
						//carreserve���̺��� id��? �̰� rday�� ?�� �ָ� ����
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, rday);
			pstmt.executeUpdate();
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
