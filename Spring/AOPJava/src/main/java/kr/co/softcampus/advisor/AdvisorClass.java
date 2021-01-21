package kr.co.softcampus.advisor;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AdvisorClass {

	@Before("execution(* method1())")
	public void beforeMethod() {
		System.out.println("before�޼��� ȣ��");
	}
	
	@After("execution(* method1())")
	public void afterMethod() {
		System.out.println("after�޼��� ȣ��");
	}
	
	@Around("execution(* method1())")
	public Object aroundMethod(ProceedingJoinPoint pjp)throws Throwable {
		System.out.println("Around�޼���1 ȣ��");
		Object result = pjp.proceed();
		System.out.println("Around�޼���2 ȣ��");
		return result;
	}
	
	@AfterReturning("execution(* method1())")
	public void afterReturningMethod() {
		System.out.println("afterReturning�޼��� ȣ��");
	}
	
	@AfterThrowing("execution(* method1())")
	public void afterThrowingMethod() {
		System.out.println("afterThrowing�޼��� ȣ��");
	}
}
