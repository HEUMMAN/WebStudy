package kr.co.softcampus.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class TestBeanPostProcessor implements BeanPostProcessor{
	//init�޼��� ȣ�� ���� ȣ��ȴ� 
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("before");
		switch(beanName){
		case "t1":
			System.out.println("�̰� t1��ü ����");
			break;
		case "t2":
			System.out.println("�̰� t2��ü ����");
		}
		
		return bean;	//�� bean�� �Ű������� t1�� �����µ� �� bean�̴�
	}
	
	//init�޼��� ȣ�� �Ŀ� ȣ��ȴ�
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("after");
		switch(beanName){
		case "t1":
			System.out.println("�̰� t1��ü ����");
			break;
		case "t2":
			System.out.println("�̰� t2��ü ����");
		}
		return bean;
	}
	
	
}
