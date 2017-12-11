package com.yl.GeneralProject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yl.spring_boot_starter_hello.HelloService;

@RestController
public class HelloController {

	@Autowired
	private HelloService hs;
	
	@RequestMapping("/")
	public String say() {
		return hs.say();
	}
}
