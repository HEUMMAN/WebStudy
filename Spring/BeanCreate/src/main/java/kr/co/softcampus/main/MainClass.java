package kr.co.softcampus.main;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import kr.co.softcampus.beans.TestBean;

public class MainClass {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("kr/co/softcampus/config/beans.xml");
		TestBean t1 = ctx.getBean("t1",TestBean.class);
		System.out.printf("t1 : %s\n",t1);
		TestBean t2 = ctx.getBean("t1",TestBean.class);
		System.out.printf("t2 : %s\n",t2);
		
		//id�� t2�� ��ü�� �ּҰ� �޾ƿ���
		TestBean t3 = ctx.getBean("t2",TestBean.class);
		System.out.printf("t3 : %s\n",t3);
		
		TestBean t4 = ctx.getBean("t2",TestBean.class);
		System.out.printf("t4 : %s\n",t4);
		ctx.close();
	}

}
