package com.test.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// @ResponseBody
// @org.springframework.stereotype.Controller
// => @RestController
@RestController
public class Controller {
	@RequestMapping("/hello")
	public String hello() {
		return "Hello World Quick";
	}
}
