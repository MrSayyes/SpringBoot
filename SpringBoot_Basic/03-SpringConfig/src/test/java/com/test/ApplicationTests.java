package com.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.test.bean.Person;

/**
 * SpringBoot单元测试
 * 可以注入其他容器内容进行测试
 * @author sayyes
 */
@SpringBootTest
class ApplicationTests {
	//@Autowired注入Person对象
	@Autowired
	Person person;

	@Test
	void contextLoads() {
		System.out.println(person);
	}

}
