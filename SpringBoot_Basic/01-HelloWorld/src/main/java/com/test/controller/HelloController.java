package com.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Controller 处理主程序请求
 * @author sayyes
 */
@Controller
public class HelloController {

	// 接收来自浏览器的hello请求
	// 把返回返回给浏览器
	@RequestMapping("/hello")
	@ResponseBody
	public String hello() {
		return "Hello World";
	}
}
