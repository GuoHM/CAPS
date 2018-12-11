package com.caps.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.caps.entity.Account;
import com.caps.entity.Enrollment;
import com.caps.service.TestService;

@Controller
@RequestMapping(value = "/demo")
public class DemoController {
	
	@Autowired
	TestService testService;
	
	@RequestMapping("/finduser")
	
	public ModelAndView insertCustomers(Model model) {
		ModelAndView mav = new ModelAndView("index");
	
		return mav;
	}
	
	@RequestMapping("/api/enrollment")
	@ResponseBody
	public List<Account> listEnrollment() {
		return testService.findUser();
	}
	


}
