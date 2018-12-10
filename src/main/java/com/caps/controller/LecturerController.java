package com.caps.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/lecturer")
public class LecturerController {
	@RequestMapping("/welcome")
	public String login() {
		return "/lecturer/welcome";
	}
}
