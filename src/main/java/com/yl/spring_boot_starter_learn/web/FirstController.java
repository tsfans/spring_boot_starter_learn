package com.yl.spring_boot_starter_learn.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yl.spring_boot_starter_learn.domain.Person;

@Controller
public class FirstController {

	@RequestMapping("/toIndex")
	public String toIndex(Model model) {
		Person p1 = new Person(1,"aa",11,"shenzheng");
		Person p2 = new Person(2,"bb",22,"shenzheng");
		Person p3 = new Person(3,"cc",33,"shenzheng");
		Person p4 = new Person(4,"dd",44,"shenzheng");
		Person p5 = new Person(5,"ee",55,"shenzheng");
		List<Person> plist = new ArrayList<Person>();
		plist.add(p1);
		plist.add(p2);
		plist.add(p3);
		plist.add(p4);
		plist.add(p5);
		model.addAttribute("person", p1);
		model.addAttribute("people", plist);
		return "thymeleaf1";
	}
}
