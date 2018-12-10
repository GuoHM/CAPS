package com.caps.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/student")
public class StudentController {
	@RequestMapping("/welcome")
	public String login() {
		return "/student/welcome";
	}
}
