package kr.co.softcampus.beans;

public class TestBean3 {
	public TestBean3() {
		System.out.println("TestBean3�� ������");
	}
	
	public void default_init() {
		System.out.println("TestBean3�� default-init�޼���");
	}
	
	public void default_destroy() {
		System.out.println("TestBean3�� default-destroy�޼���");
	}
	
	public void bean3_init() {
		System.out.println("TestBean3�� init�޼���");
	}
	
	public void bean3_destroy() {
		System.out.println("TestBean3�� destroy�޼���");
	}
}
