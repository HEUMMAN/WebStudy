package kr.co.softcampus.advisor;

import org.aspectj.lang.ProceedingJoinPoint;

public class AdvisorClass {

	public void beforeMethod() {
		System.out.println("beforeMethod ȣ��");
	}
	
	public void afterMethod() {
		System.out.println("afterMethod ȣ��");
	}
	
	public int aroundMethod(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("aroundMethod ȣ��1");
		//������ �޼��� ȣ��
		int a1 = (Integer) pjp.proceed();
		System.out.println("aroundMethod ȣ��2");
		return a1;
	}
	
	public void afterReturningMethod() {
		System.out.println("afterReturningMethod ȣ��");
	}
}
