package kr.co.softcampus.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import kr.co.softcampus.beans.TestBean1;
import kr.co.softcampus.beans.TestBean2;


@Configuration
//������ ��Ű���� Bean Ŭ�������� ������̼��� �м��Ͽ� Bean�� ��Ȥ�϶�� �����Ѵ�
@ComponentScan(basePackages = "kr.co.softcampus.beans2")
public class BeanConfigClass {

	@Bean
	public TestBean1 java1() {
		return new TestBean1();
	}
	
	@Bean
	public TestBean2 java2() {
		return new TestBean2();
	}
	
	@Bean
	public TestBean2 java3() {
		return new TestBean2();
	}
}
