package com.caps.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.caps.service.TestService;

@Controller
@RequestMapping(value = "/demo")
public class DemoController {
	
	@Autowired
	TestService testService;
	
	@RequestMapping("/finduser")
	@ResponseBody
	public ModelAndView insertCustomers(Model model) {
		ModelAndView mav = new ModelAndView("index");
		mav.addObject("Text", testService.findUser());
		mav.addObject("Text2", testService.findCourse());
		mav.addObject("Text3", testService.findEnrollment());
		return mav;
	}

}
