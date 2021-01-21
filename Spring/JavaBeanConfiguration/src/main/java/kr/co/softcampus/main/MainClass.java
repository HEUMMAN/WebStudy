package kr.co.softcampus.main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import kr.co.softcampus.beans.TestBean1;
import kr.co.softcampus.beans.TestBean2;
import kr.co.softcampus.config.BeanConfigClass;

public class MainClass {

	public static void main(String[] args) {
		//xml�� ����ϴ� ���
		ClassPathXmlApplicationContext ctx1 = new ClassPathXmlApplicationContext("kr/co/softcampus/config/beans.xml");
		TestBean1 xml1 = ctx1.getBean("xml1",TestBean1.class);
		System.out.printf("xml1: %s\n",xml1);
		//�̱����̱⿡ �Ȱ��� �� �����ϸ� ���� �ּҰ� ���´�
		TestBean1 xml11 = ctx1.getBean("xml1",TestBean1.class);
		System.out.printf("xml11: %s\n",xml11);
		System.out.println("============================================");
		
		TestBean2 xml2 = ctx1.getBean("xml2",TestBean2.class);
		System.out.printf("xml2: %s\n",xml2);
		TestBean2 xml22 = ctx1.getBean("xml2",TestBean2.class);
		System.out.printf("xml22: %s\n",xml22);
		
		ctx1.close();
		System.out.println("============================================");
		
		//java������ ����ϴ� ���
		AnnotationConfigApplicationContext ctx2 =  new AnnotationConfigApplicationContext(BeanConfigClass.class);
		TestBean1 java1 = ctx2.getBean("java1",TestBean1.class);
		System.out.printf("java1: %s\n",java1);
		TestBean1 java11 = ctx2.getBean("java1",TestBean1.class);
		System.out.printf("java11: %s\n",java11);
		TestBean1 java500 = ctx2.getBean("java600",TestBean1.class);
		System.out.printf("java500: %s\n",java500);
		System.out.println("============================================");
		
		TestBean2 java2 = ctx2.getBean("java2",TestBean2.class);
		System.out.printf("java2: %s\n",java2);
		TestBean2 java22 = ctx2.getBean("java2",TestBean2.class);
		System.out.printf("java22: %s\n",java22);
		ctx2.close();

	}

}
