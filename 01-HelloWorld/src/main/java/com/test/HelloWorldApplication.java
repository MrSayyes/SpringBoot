package com.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SpringBootApplication 标注一个主程序类说明SpringBoot应用
 * 
 * @author sayyes
 */
@SpringBootApplication
public class HelloWorldApplication {
	public static void main(String[] args) {
		// Spring应用启动起来
		SpringApplication.run(HelloWorldApplication.class, args);
	}
}
