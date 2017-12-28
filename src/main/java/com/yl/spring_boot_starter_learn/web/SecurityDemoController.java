package com.yl.spring_boot_starter_learn.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yl.spring_boot_starter_learn.domain.Message;

@Controller
public class SecurityDemoController {

	@RequestMapping("/")
	public String index(Model model) {
		Message msg = new Message("测试标题","测试内容","额外信息，只对管理员显示");
		model.addAttribute("msg", msg);
		return "home";
	}
}
