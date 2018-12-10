package com.caps.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
	
	@RequestMapping("/welcome")
	public String login() {
		return "/admin/welcome";
	}

}
