package com.caps.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/student")
public class StudentController {
	@RequestMapping("/welcome")
	public String login() {
		return "/student/welcome";
	}
}
